package javasrc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javasrc.component.ObjectUtils;
import javasrc.entity.Feedback;
import javasrc.service.FeedbackService;

@Controller
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	private HttpSession session;
	
	private Map<String, Object> map;
	
	@ModelAttribute
	public void init(){
		map=new HashMap<>();
		session=httpServletRequest.getSession(false);
	}
	
	@RequestMapping("loginuser/adminmanager/getFeedbacks")
	@ResponseBody
	public List<Feedback> getFeedbacks(Feedback feedback){
		if (session.getAttribute("authority").equals("2")) {
			feedback.setFankuirenyuangonghao(session.getAttribute("loginname").toString());
		}
		return feedbackService.getFeedbacks(feedback);
	}
	
	@RequestMapping("loginuser/adminmanager/getFeedbackscount")
	@ResponseBody
	public Integer getFeedbackscount(Feedback feedback){
		if (session.getAttribute("authority").equals("2")) {
			feedback.setFankuirenyuangonghao(session.getAttribute("loginname").toString());
		}
		return feedbackService.getFeedbackscount(feedback);
	}
	
	@RequestMapping("loginuser/adminmanager/extractFeedback")
	@ResponseBody
	public String extractFeedback(Feedback feedback){
		if (session.getAttribute("authority").equals("2")) {
			feedback.setFankuirenyuangonghao(session.getAttribute("loginname").toString());
		}
		return feedbackService.extractFeedback(feedback);
	}
	
	@RequestMapping("loginuser/adminmanager/saveorupdateFeedback")
	@ResponseBody
	public Map<String, Object> saveorupdateFeedback(Feedback feedback){
		feedback.setFankuirenyuangonghao(session.getAttribute("loginname").toString());
		String rs=feedbackService.saveorupdateFeedback(feedback);
		if (rs.equals("can not modify others feedback")) {
			map.put("error", rs);
		}else {
			map.put("success", rs);
		}
		return map;
	}
	
	@RequestMapping("loginuser/adminmanager/callbyClient")
	@ResponseBody
	public Map<String, Object> callbyClient(Feedback feedback){
		feedback.setFankuirenyuangonghao(session.getAttribute("loginname").toString());
		String key=feedbackService.callbyClient(feedback);
		map.put("success", key);
		return map;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> exceptionhandler(Exception exception){
		String exceptionmessage="";
		exception.printStackTrace();
		Throwable cause=ObjectUtils.findcause(exception);
		if (cause!=null) {
			exceptionmessage=cause.getMessage();
		}else {
			exceptionmessage=exception.getMessage();
		}
		map.put("exception", exceptionmessage);
		return map;
	}
}
