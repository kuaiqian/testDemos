package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import service.UserService;
import service.model.Message;
import service.model.User;

public class UserServiceImpl implements UserService {
	Map<Integer,List<Message>> map=new HashMap<Integer,List<Message>>();
	public UserServiceImpl() {
		List<Message> messages=new ArrayList<Message>();
		messages.add(new Message(1, "你好", new Date()));
		messages.add(new Message(2, "我很好", new Date()));
		messages.add(new Message(3, "你好", new Date()));
		map.put(111, messages);
	}
	public User getUser(Integer userId) {
		if(111==userId) {
			User user=new User();
			user.setUserId(111);
			user.setUserName("chengchen");
			return user;
		}else {
			return null;
		}
	}

	public List<Message> getMessages(Integer userId) {
		return map.get(userId);
	}
	public void deleteMessage(Integer userId,Integer messageId) {
		List<Message> list=map.get(userId);
		for (Iterator<Message> it=list.iterator();it.hasNext();) {
			Message message=it.next();
			if(message.getMessageId().equals(messageId))
				it.remove();
		}		
	}

}
