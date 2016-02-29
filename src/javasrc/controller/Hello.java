package javasrc.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javasrc.entity.Client;

@Controller
public class Hello {
	
	@ModelAttribute
	public void getsession(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		System.out.println("session id:::::::::::::::::::"+session.getId());
	}
	
	@RequestMapping("/hello")
	public @ResponseBody List<Client> hello(@RequestParam("myname") String name){
		Client cunliangweihu=new Client();
		cunliangweihu.setDianhuahaoma("13001425258");
		return null;
	}
}
