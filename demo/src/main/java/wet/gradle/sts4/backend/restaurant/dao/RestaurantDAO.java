package wet.gradle.sts4.backend.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import common.dao.CommonDAO;
import wet.gradle.sts4.backend.restaurant.vo.RestaurantVO;

@Repository
public class RestaurantDAO extends CommonDAO {
	int count = 0;
	public int getCount(RestaurantVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" SELECT ")
			.append(" COUNT(*) AS count ")
		.append(" FROM ")
			.append(" wlist ")
		;
		
		return getJdbcTemplate().queryForObject(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> {
					return rs.getInt("count");
				}
			);
	}

	public List<RestaurantVO> getList(RestaurantVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" SELECT ")
			.append(" * ")
		.append(" FROM ")
			.append(" wlist ")
		;
		
		return getJdbcTemplate().query(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> getRestaurantVO(rs, true)
			);
	}
	
	public List<RestaurantVO> getOne(RestaurantVO vo, int count) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		sb
		.append(" SELECT ")
			.append(" * ")
		.append(" FROM ")
			.append(" wlist ")
		.append(" WHERE ")
			.append(" res_id = ")
			.append(rand.nextInt(count)+1)
		;
		
		return getJdbcTemplate().query(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> getRestaurantVO(rs, false)
			);
	}
	
	public int addRestaurant(RestaurantVO vo) {
		StringBuilder sb = new StringBuilder();

		sb
		.append(" insert into ")
			.append(" wlist(res_name, res_type, res_addr_road, res_addr_jibun, res_holiday, res_lat, res_long, res_famous_menu, res_rating) ")
		.append(" values ")
			.append(" (:resName, :resType, :resAddrRoad, :resAddrJibun, :resHoliday, :resLat, :resLong, :resFamousMenu, :resRating) ")
		;

		return getJdbcTemplate().update(sb.toString()
			, new BeanPropertySqlParameterSource(vo)
		);
	}

	public int removeRestaurant(RestaurantVO vo) {
		StringBuilder sb = new StringBuilder();

		sb
		.append(" delete from ")
			.append(" wlist ")
		.append(" where ")
			.append(" res_id = :resId ")
		;

		return getJdbcTemplate().update(sb.toString()
			, new BeanPropertySqlParameterSource(vo)
		);
	}
	
	private RestaurantVO getRestaurantVO(ResultSet rs, boolean isList) throws SQLException {
		if( isList ) {
			return RestaurantVO.builder()
					.resId( rs.getInt("res_id") )
					.resName( rs.getString("res_name") )
					.resType( rs.getString("res_type") )
					.resAddrRoad( rs.getString("res_addr_road") )
					.resAddrJibun( rs.getString("res_addr_jibun") )
					.resHoliday( rs.getString("res_holiday") )
					.resLat( rs.getString("res_lat") )
					.resLong( rs.getString("res_long") )
					.resFamousMenu( rs.getString("res_famous_menu") )
					.resRating( rs.getInt( "res_rating" ))
				.build();
		} else {
			return RestaurantVO.builder()
					.resId( rs.getInt("res_id") )
					.resName( rs.getString("res_name") )
					.resType( rs.getString("res_type") )
					.resAddrRoad( rs.getString("res_addr_road") )
					.resAddrJibun( rs.getString("res_addr_jibun") )
					.resHoliday( rs.getString("res_holiday") )
					.resLat( rs.getString("res_lat") )
					.resLong( rs.getString("res_long") )
					.resFamousMenu( rs.getString("res_famous_menu") )
					.resRating( rs.getInt( "res_rating" ) )
				.build();
		}
	}
}
