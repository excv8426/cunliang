package javasrc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javasrc.component.ObjectUtils;
import javasrc.entity.Loginuser;
import javasrc.service.LoginuserService;

@Controller
public class LoginuserController {
	@Autowired
	private LoginuserService loginuserService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@RequestMapping("loginuser/system/addLoginuser")
	@ResponseBody
	public Map<String, Object> addLoginuser(Loginuser loginuser){
		Map<String, Object> map=new HashMap<>();
		if (ObjectUtils.nullpropertycount(loginuser)<=3) {
			loginuserService.addLoginuser(loginuser);
			map.put("success", "1");
		} else {
			map.put("error", "nullproperty");
		}
		return map;
	}
	
	@RequestMapping("checkLoginuser")
	@ResponseBody
	public Map<String, Object> checkLoginuser(Loginuser loginuser){
		Map<String, Object> map=new HashMap<>();
		String namepsd=loginuser.getLoginname()+loginuser.getPassword();
		loginuser.setPassword(DigestUtils.md5DigestAsHex(namepsd.getBytes()));
		Loginuser loginuser2=loginuserService.checkLoginuser(loginuser);
		if (loginuser2==null) {
			map.put("error", "fail");
		} else if (loginuser2.getErrorcount()>=10) {
			map.put("error", "locked");
		} else if (!loginuser2.getPassword().equals(loginuser.getPassword())) {
			map.put("error", "fail");
		} else {
			map.put("success", "success");
			map.put("authority", loginuser2.getAuthority());
			HttpSession session=httpServletRequest.getSession(true);
			session.setAttribute("loginname", loginuser2.getLoginname());
			session.setAttribute("authority", loginuser2.getAuthority());
			session.setAttribute("personname", loginuser2.getPersonname());
		}
		return map;
	}
	
	@RequestMapping("loginuser/system/resetpwd")
	@ResponseBody
	public Map<String, Object> resetPassword(Loginuser loginuser){
		Map<String, Object> map=new HashMap<>();
		map.put("success", loginuserService.updatepassword(loginuser));
		return map;
	}
	
	@RequestMapping("loginuser/system/addLoginusers")
	@ResponseBody
	public Map<String, Object> addLoginusers(@RequestParam("file") MultipartFile[] files){
		Map<String, Object> map=new HashMap<>();
		Integer count=0;
		for (int i = 0; i < files.length; i++) {
			try {
				count=count+loginuserService.addLoginuser(files[i].getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("success", count);
		return map;
	}
	
	@RequestMapping("loginuser/default/updatepassword")
	@ResponseBody
	public Map<String, Object> updatepassword(Loginuser loginuser){
		Map<String, Object> map=new HashMap<>();
		HttpSession session=httpServletRequest.getSession(false);
		loginuser.setLoginname(session.getAttribute("loginname").toString());
		if (StringUtils.hasText(loginuser.getPassword())) {
			int count=loginuserService.updatepassword(loginuser);
			map.put("success", count);
		} else {
			map.put("error", "nullproperty");
		}
		return map;
	}
	
	@RequestMapping("loginuser/system/getLoginusers")
	@ResponseBody
	public List<Loginuser> getLoginusers(Loginuser loginuser){
		return loginuserService.getLoginusers(loginuser);
	}
	
	@RequestMapping("loginuser/system/getLoginuserscount")
	@ResponseBody
	public Integer getLoginuserscount(){
		return loginuserService.getLoginuserscount();
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
