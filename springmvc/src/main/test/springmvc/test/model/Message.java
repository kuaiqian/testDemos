package springmvc.test.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	@JsonProperty(index=1,value="id",required=true) private Integer messageId;
	@JsonProperty(index=2,value="content") private String content;
	@JsonProperty(index=3,value="date") private Date createDate;
	@JsonProperty(index=3,value="name") private Date name;

	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "messageId="+messageId+",content="+content+",date="+createDate;
	}
	
}
