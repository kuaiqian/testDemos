package service.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	@JsonProperty(index=1,value="id",required=true) private Integer messageId;
	@JsonProperty(index=2,value="content") private String content;
	@JsonProperty(index=3,value="date") private Date createDate;
	
	public Message(Integer messageId, String content, Date createDate) {
		super();
		this.messageId = messageId;
		this.content = content;
		this.createDate = createDate;
	}
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
