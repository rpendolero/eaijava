/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.bde.aps.jbs.eaijava.mail;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private Recipients recipients;
	private String from;
	private String replyTo;
	private String subject;
	private String body;
	private String documentFormat = "html";
	private List<String> attachments;

	/**
	 * 
	 */
	public Message() {
		this.recipients = new Recipients();
		this.setAttachments(new ArrayList<String>());
	}

	/**
	 * @return
	 */
	public Recipients getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients
	 */
	public void setRecipients(Recipients recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return
	 */
	public String getReplyTo() {
		return replyTo;
	}

	/**
	 * @param replyTo
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	/**
	 * @return
	 */
	public String getDocumentFormat() {
		return documentFormat;
	}

	/**
	 * @param documentFormat
	 */
	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return
	 */
	public List<String> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments
	 */
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return
	 */
	public boolean hasAttachment() {
		return !this.attachments.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((documentFormat == null) ? 0 : documentFormat.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((recipients == null) ? 0 : recipients.hashCode());
		result = prime * result + ((replyTo == null) ? 0 : replyTo.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Message other = (Message) obj;
		if (body == null) {
			if (other.body != null) {
				return false;
			}
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (documentFormat == null) {
			if (other.documentFormat != null) {
				return false;
			}
		} else if (!documentFormat.equals(other.documentFormat)) {
			return false;
		}
		if (from == null) {
			if (other.from != null) {
				return false;
			}
		} else if (!from.equals(other.from)) {
			return false;
		}
		if (recipients == null) {
			if (other.recipients != null) {
				return false;
			}
		} else if (!recipients.equals(other.recipients)) {
			return false;
		}
		if (replyTo == null) {
			if (other.replyTo != null) {
				return false;
			}
		} else if (!replyTo.equals(other.replyTo)) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		if (attachments == null) {
			if (other.attachments != null) {
				return false;
			}
		} else if (!attachments.equals(other.attachments)) {
			return false;
		}
		return true;
	}

}
