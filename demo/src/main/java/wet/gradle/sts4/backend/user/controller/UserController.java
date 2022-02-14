package wet.gradle.sts4.backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
