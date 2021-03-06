package poms.supporter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import poms.center.entity.Advice;
import poms.supporter.service.IAdviceService;

@Controller
@RequestMapping("/support/advice")
@SessionAttributes("stationID")
public class AdviceController {
	
	@Autowired
	private IAdviceService adviceService;
	
	
	//测试登录
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public ModelAndView form(ModelMap map){
		map.addAttribute("stationID", 123);
		return new ModelAndView("form","command",new Advice());
	}
	
	@RequestMapping(value="/unreadedAdvice",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getUnreadedAdvice(ModelMap map){
		List<Advice> unreadedAdviceList = adviceService.getUnreadedAdvice(Integer.parseInt(map.get("stationID").toString())); 
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("size", unreadedAdviceList.size());
		resultMap.put("data", unreadedAdviceList);
		return resultMap;
	}
	
	@RequestMapping(value="/sendAdvice",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sendService(Advice advice,ModelMap map){
//		System.out.println(map.get("stationID"));
		int result=adviceService.sendAdvice(advice);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	@RequestMapping(value="/readedAdvice",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getReadedAdvice(ModelMap map){
		List<Advice> readedAdviceList = adviceService.getReadedAdvice(Integer.parseInt(map.get("stationID").toString())); 
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("size", readedAdviceList.size());
		resultMap.put("data", readedAdviceList);
		return resultMap;
	}
}
