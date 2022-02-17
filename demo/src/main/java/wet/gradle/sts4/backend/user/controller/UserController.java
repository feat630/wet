package wet.gradle.sts4.backend.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import wet.gradle.sts4.backend.user.service.UserService;
import wet.gradle.sts4.backend.user.vo.UserVO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired UserService userService;
	
	@RequestMapping(value="/signIn", method = RequestMethod.POST)
	public void signIn(@RequestBody UserVO vo) {
		
		userService.signIn(vo);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestBody UserVO vo) {
		Map<String, Object> result = Maps.newHashMap();
		
		result.put("data", userService.login(vo));

		return result;
	}
}
