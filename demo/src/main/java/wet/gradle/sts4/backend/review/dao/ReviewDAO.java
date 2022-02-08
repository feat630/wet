package wet.gradle.sts4.backend.review.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import common.dao.CommonDAO;
import wet.gradle.sts4.backend.review.vo.ReviewVO;

@Repository
public class ReviewDAO extends CommonDAO {
	int count = 0;
	public int getCount(ReviewVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" SELECT ")
			.append(" COUNT(*) AS count ")
		.append(" FROM ")
			.append(" wReview ")
		;
		
		return getJdbcTemplate().queryForObject(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> {
					return rs.getInt("count");
				}
			);
	}

	public List<ReviewVO> getList(ReviewVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" SELECT ")
			.append(" * ")
		.append(" FROM ")
			.append(" wReview ")
		;
		
		return getJdbcTemplate().query(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> getReviewVO(rs, true)
			);
	}
	
	public List<ReviewVO> getOne(ReviewVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" SELECT ")
			.append(" * ")
		.append(" FROM ")
			.append(" wReview ")
		.append(" WHERE ")
			.append(" res_id = :resId")
		;
		
		return getJdbcTemplate().query(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> getReviewVO(rs, false)
			);
	}

	private ReviewVO getReviewVO(ResultSet rs, boolean isList) throws SQLException {
		if( isList ) {
			return ReviewVO.builder()
					.resId( rs.getInt("res_id") )
					.userId( rs.getString("user_id" ))
					.image(rs.getString( "image" ))
					.rating(rs.getInt( "rating" ))
					.userReview(rs.getString("user_review" ))
				.build();
		} else {
			return ReviewVO.builder()
					.resId( rs.getInt("res_id") )
					.userId( rs.getString("user_id" ))
					.image(rs.getString( "image" ))
					.rating(rs.getInt( "rating" ))
					.userReview(rs.getString("user_review"))
				.build();
		}
	}
}
