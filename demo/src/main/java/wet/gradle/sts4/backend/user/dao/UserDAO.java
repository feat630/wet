package wet.gradle.sts4.backend.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			.append(" wet_user(user_id, user_pw, user_email) ")
		.append(" values ")
			.append(" (:userId, :userPw, :userEmail) ")
		;

		return getJdbcTemplate().update(sb.toString()
			, new BeanPropertySqlParameterSource(vo)
		);
	}
	
	public List<UserVO> login(UserVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" SELECT ")
			.append(" user_id, user_pw ")
		.append(" FROM ")
			.append(" wet_user ")
		.append(" WHERE ")
			.append(" user_id = :userId && user_pw = :userPw ")
		;
		
		return getJdbcTemplate().query(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> getUserVO(rs, true)
			);
	}
		
		private UserVO getUserVO(ResultSet rs, boolean isLogin) throws SQLException {
			if( rs != null ) {
				return UserVO.builder()
						.userId( rs.getString("user_id") )
						.userPw( rs.getString("user_pw") )
						.loginCheck( true )
					.build();
			} else {
				return UserVO.builder()
						.loginCheck( false )
					.build();
			}
		}
	}
