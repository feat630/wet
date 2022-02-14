package wet.gradle.sts4.backend.restaurant.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import wet.gradle.sts4.backend.restaurant.service.RestaurantService;
import wet.gradle.sts4.backend.restaurant.vo.RestaurantVO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/restaurant")
public class RestaurantController {

	@Autowired RestaurantService RestaurantService;
	
	@RequestMapping(value="/list")
	public Map<String, Object> list(RestaurantVO vo) {
		Map<String, Object> result = Maps.newHashMap();
		
		result.put("data", RestaurantService.getList(vo));

		return result;
	}
	
	@RequestMapping(value="/one")
	public Map<String, Object> one(RestaurantVO vo) {
		Map<String, Object> result = Maps.newHashMap();

		result.put("data", RestaurantService.getOne(vo));

		return result;
	}
	
	@RequestMapping(value="/count")
	public int count(RestaurantVO vo) {

		return RestaurantService.getCount(vo);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void add(@RequestBody RestaurantVO vo) {

		RestaurantService.add(vo);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public void remove(@RequestBody RestaurantVO vo) {

		RestaurantService.remove(vo);
	}
	
}
