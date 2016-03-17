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
import javasrc.entity.Discount;
import javasrc.service.DiscountService;

@Controller
public class DiscountController {
	@Autowired
	private DiscountService discountService;
	
	@RequestMapping("loginuser/default/getDiscounts")
	@ResponseBody
	public List<Discount> getDiscounts(Discount discount){
		return discountService.getDiscounts(discount);
	}
	
	@RequestMapping("loginuser/system/reorganizeDiscounts")
	@ResponseBody
	public Map<String, Object> reorganizeDiscounts(){
		Map<String, Object> map=new HashMap<>();
		Integer s=discountService.reorganizeDiscounts();
		map.put("success", s);
		return map;
	}
	
	@RequestMapping("loginuser/system/updateDiscountrelation")
	@ResponseBody
	public Map<String, Object> updateDiscountrelation(){
		Map<String, Object> map=new HashMap<>();
		Integer s=discountService.updateDiscountrelation();
		map.put("success", s);
		return map;
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
