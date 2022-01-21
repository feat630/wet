package wet.gradle.sts4.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import wet.gradle.sts4.backend.service.WetService;
import wet.gradle.sts4.backend.vo.WetVO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/wet")
public class WetController {

	@Autowired WetService wetService;
	
	@RequestMapping(value="/list")
	public Map<String, Object> list(WetVO vo) {
		Map<String, Object> result = Maps.newHashMap();
		
		result.put("data", wetService.getList(vo));

		return result;
	}
	
	@RequestMapping(value="/one")
	public Map<String, Object> one(WetVO vo) {
		Map<String, Object> result = Maps.newHashMap();

		result.put("data", wetService.getOne(vo));

		return result;
	}
	
	@RequestMapping(value="/count")
	public int count(WetVO vo) {

		return wetService.getCount(vo);
	}
}
