package wet.gradle.sts4.backend.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wet.gradle.sts4.backend.user.dao.UserDAO;
import wet.gradle.sts4.backend.user.vo.UserVO;

@Service
public class UserService {

	@Autowired UserDAO userDAO;
	
	@Transactional
	public int signIn(UserVO vo) {
		return userDAO.signIn(vo);
	}

	public List<UserVO> login(UserVO vo) {
		return userDAO.login(vo);
	}
}
