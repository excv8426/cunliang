package javasrc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javasrc.component.ObjectUtils;
import javasrc.entity.Allocation;
import javasrc.service.AllocationService;

@Controller
public class AllocationController {
	@Autowired
	private AllocationService allocationService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@RequestMapping("loginuser/default/saveorupdateAllocation")
	@ResponseBody
	public Map<String, Object> saveorupdateAllocation(Allocation allocation){
		HttpSession session=httpServletRequest.getSession(false);
		Map<String, Object> map=new HashMap<>();
		allocation.setPaidanrenyuangonghao(session.getAttribute("loginname").toString());
		String res=allocationService.saveorupdateAllocation(allocation);
		if (res.equals("feedbacked")) {
			map.put("error", res);
		} else {
			map.put("success", res);
		}
		return map;
	}
	
	@RequestMapping("loginuser/default/getAllocations")
	@ResponseBody
	public List<Allocation> getAllocations(Allocation allocation){
		HttpSession session=httpServletRequest.getSession(false);
		if (session.getAttribute("authority").equals("2")) {
			allocation.setPaidanrenyuangonghao(session.getAttribute("loginname").toString());
		}
		return allocationService.getAllocations(allocation);
	}
	
	@RequestMapping("loginuser/default/getAllocationscount")
	@ResponseBody
	public Integer getAllocationscount(Allocation allocation){
		HttpSession session=httpServletRequest.getSession(false);
		if (session.getAttribute("authority").equals("2")) {
			allocation.setPaidanrenyuangonghao(session.getAttribute("loginname").toString());
		}
		return allocationService.getAllocationscount(allocation);
	}
	
	@RequestMapping("loginuser/default/feedbackAllocation")
	@ResponseBody
	public Map<String, Object> feedbackAllocation(Allocation allocation){
		HttpSession session=httpServletRequest.getSession(false);
		Map<String, Object> map=new HashMap<>();
		allocation.setFankuirenyuangonghao(session.getAttribute("loginname").toString());
		String res=allocationService.feedbackAllocation(allocation);
		if (res.equals("can not modify others feedbackAllocation")) {
			map.put("error", res);
		} else {
			map.put("success", res);
		}
		
		return map;
	}
	
	@RequestMapping("loginuser/default/extractAllocation")
	@ResponseBody
	public String extractAllocation(Allocation allocation){
		HttpSession session=httpServletRequest.getSession(false);
		if (session.getAttribute("authority").equals("2")) {
			allocation.setPaidanrenyuangonghao(session.getAttribute("loginname").toString());
		}
		return allocationService.extractAllocation(allocation);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> exceptionhandler(Exception exception){
		Map<String, Object> map=new HashMap<>();
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
