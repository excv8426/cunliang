package javasrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javasrc.service.LogService;

@Controller
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping("loginuser/system/clearMylog")
	@ResponseBody
	public Integer clearMylog(){
		return logService.clearMylog();
	}
}
