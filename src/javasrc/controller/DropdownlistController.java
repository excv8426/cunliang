package javasrc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javasrc.component.ObjectUtils;
import javasrc.entity.Bubanliyuanyin;
import javasrc.entity.Yujingcelve;
import javasrc.service.DropdownlistService;

@Controller
public class DropdownlistController {
	@Autowired
	private DropdownlistService dropdownlistService;
	
	@RequestMapping("loginuser/default/getYujingcelves")
	@ResponseBody
	public List<Yujingcelve> getYujingcelves(){
		return dropdownlistService.getYujingcelves();
	}
	
	@RequestMapping("loginuser/default/getBubanliyuanyins")
	@ResponseBody
	public List<Bubanliyuanyin> getBubanliyuanyins(){
		return dropdownlistService.getBubanliyuanyins();
	}
	
	@RequestMapping("loginuser/system/addYujingcelve")
	@ResponseBody
	public String addYujingcelve(Yujingcelve yujingcelve){
		return dropdownlistService.addYujingcelve(yujingcelve);
	}
	
	@RequestMapping("loginuser/system/addBubanliyuanyin")
	@ResponseBody
	public String addBubanliyuanyin(Bubanliyuanyin bubanliyuanyin){
		return dropdownlistService.addBubanliyuanyin(bubanliyuanyin);
	}
	
	@RequestMapping("loginuser/system/deleteYujingcelve")
	@ResponseBody
	public Integer deleteYujingcelve(Yujingcelve yujingcelve){
		return dropdownlistService.deleteYujingcelve(yujingcelve);
	}
	
	@RequestMapping("loginuser/system/deleteBubanliyuanyin")
	@ResponseBody
	public Integer deleteBubanliyuanyin(Bubanliyuanyin bubanliyuanyin){
		return dropdownlistService.deleteBubanliyuanyin(bubanliyuanyin);
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
