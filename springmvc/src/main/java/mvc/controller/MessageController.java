package mvc.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.UserService;
import service.model.Message;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
	private Logger logger = LogManager.getLogger(MessageController.class);
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/list/{userId}")
	public @ResponseBody Message getMessages(@PathVariable(value = "userId") Integer userId) throws Exception {
		List<Message> list=userService.getMessages(userId);
		return list.get(0);
	}
	
//	@RequestMapping(value = "/list/{userId}")
//	public ModelAndView getMessages(@PathVariable(value = "userId") Integer userId) throws Exception {
//		List<Message> list=userService.getMessages(userId);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("messages", list);
//		modelAndView.addObject("userId", userId);
//		modelAndView.setViewName("messages");
//		return modelAndView;
//	}
	
//	@RequestMapping(value = "/delete/{userId}/{messageId}")
//	public ModelAndView deleteMessage(@PathVariable(value = "userId") Integer userId,
//			@PathVariable(value = "messageId") Integer messageId) throws Exception {
//		userService.deleteMessage(userId, messageId);
//		return getMessages(userId);
//	}
}
