package wet.gradle.sts4.backend.user.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import common.dao.CommonDAO;
import wet.gradle.sts4.backend.user.vo.UserVO;

@Repository
public class UserDAO extends CommonDAO {

	public int signIn(UserVO vo) {
		StringBuilder sb = new StringBuilder();

		sb
		.append(" insert into ")
			.append(" wet_user(user_id, user_pw) ")
		.append(" values ")
			.append(" (:userId, :userPw) ")
		;

		return getJdbcTemplate().update(sb.toString()
			, new BeanPropertySqlParameterSource(vo)
		);
	}
}
