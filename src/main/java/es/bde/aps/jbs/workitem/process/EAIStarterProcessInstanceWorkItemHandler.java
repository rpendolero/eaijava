package es.bde.aps.jbs.workitem.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.process.workitem.AbstractLogOrThrowWorkItemHandler;
import org.kie.api.definition.process.Process;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.bde.aps.jbs.workitem.EAIConstants;
import es.bde.aps.jbs.workitem.Messages;
import es.bde.aps.jbs.workitem.exception.JBSException;
import es.bde.aps.jbs.workitem.interfaces.IField;

public class EAIStarterProcessInstanceWorkItemHandler extends AbstractLogOrThrowWorkItemHandler {

	private static final String PROCESS_ID = "PROCESS_ID";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KieSession ksession;
	private Map<String, Object> results = new HashMap<String, Object>();

	public EAIStarterProcessInstanceWorkItemHandler(KieSession ksession) {
		this.ksession = ksession;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kie.api.runtime.process.WorkItemHandler#abortWorkItem(org.kie.api.runtime
	 * .process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kie.api.runtime.process.WorkItemHandler#executeWorkItem(org.kie.api.
	 * runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		String reference = String.valueOf(workItem.getProcessInstanceId());
		String processName = null;
		Map<String, Object> parameters = null;
		try {
			logger.info(Messages.getString("eaijava.messageExecute", reference));
			processName = (String) workItem.getParameter(EAIConstants.PARAM_PROCESS_NAME);
			verifyProcessName(reference, processName, workItem);

			parameters = getParameters(workItem);
			logger.info(Messages.getString("eaijava.messageExecuteCaseStart", reference, processName, parameters.toString()));
			ProcessInstance processInstance = ksession.startProcess(processName, parameters);
			long processInstanceId = processInstance.getId();

			results.put(PROCESS_ID, processInstance);
			manager.completeWorkItem(workItem.getId(), results);
			logger.info(Messages.getString("eaijava.messageExecuteCaseStartOK", reference, processInstanceId + "", processName, parameters.toString()));
		} catch (Exception e) {
			logger.error(Messages.getString("eaijava.errorExecuteCaseStart", processName, parameters.toString(), e.getMessage()));
			handleException(e);
		}

	}

	/**
	 * @param workItem
	 * @return
	 */
	private Map<String, Object> getParameters(WorkItem workItem) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		List<IField> listInputFields = (List<IField>) workItem.getParameter(EAIConstants.PARAM_LIST_FIELDS);
		for (IField field : listInputFields) {
			String fieldName = field.getName();
			Object value = field.getValue();
			parameters.put(fieldName, value);
		}
		return parameters;
	}

	/**
	 * @param procedureName
	 * @param workItem
	 * @throws JBSException
	 */
	private void verifyProcessName(String reference, String processNameTarjet, WorkItem workItem) throws JBSException {
		logger.info(Messages.getString("eaijava.messageExecuteCaseVerifyProcessName", reference, processNameTarjet));
		if (processNameTarjet == null)
			throw new JBSException("Nombre de proceso nulo o vacio");

		long processInstanceId = workItem.getProcessInstanceId();
		ProcessInstance processInstance = ksession.getProcessInstance(processInstanceId);
		Process process = processInstance.getProcess();
		String processNameSource = process.getId();
		if (!processNameTarjet.substring(0, 3).equals(processNameSource.substring(0, 3))) {
			throw new JBSException(Messages.getString("eaijava.errorCaseStartInvalidProcedureName", processNameTarjet, processInstanceId + "", processNameSource));
		}
		logger.info(Messages.getString("eaijava.messageExecuteCaseVerifyProcessNameOk", reference, processNameTarjet));
	}

}
