package service;


import java.util.List;

import service.model.Message;
import service.model.User;

public interface UserService {
	public User getUser(Integer userId);
	public List<Message> getMessages(Integer userId);
	public void deleteMessage(Integer userId,Integer messageId);
}
