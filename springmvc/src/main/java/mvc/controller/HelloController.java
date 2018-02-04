package mvc.controller;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import exception.MyAppBizException;
import service.UserService;
import service.model.User;
@Controller
public class HelloController {
	private Logger logger=LogManager.getLogger(HelloController.class);
	@Autowired
	private UserService userService;
	@RequestMapping(value="/login")
	public String  getMessage(@RequestParam Integer userId,Model model) throws Exception {
		User user=userService.getUser(userId);
		if(user!=null) {
			logger.info("user login success");
			model.addAttribute("user", user);
			return "hello";
		}else {
			return "redirect:/";
		}
	}
	@RequestMapping(value="/upload")
	public String  upload(@RequestParam("pic") MultipartFile pic,@RequestParam String picName) throws Exception {
//		pic.write("D:\\test.png");
		pic.transferTo(new File("d:\\test.png"));
		return "hello";
	}
	
	@RequestMapping(value="/exception")
	public String  exception(String name) throws Exception {
//		throw new HttpMediaTypeNotAcceptableException("aaaa");
		if(name==null) {
			throw new MyAppBizException();
		}
		return "hello";
	}
}
