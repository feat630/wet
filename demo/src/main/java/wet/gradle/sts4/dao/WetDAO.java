package wet.gradle.sts4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import common.dao.CommonDAO;
import wet.gradle.sts4.vo.WetVO;

@Repository
public class WetDAO extends CommonDAO {

	public List<WetVO> getList(WetVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" SELECT ")
			.append(" * ")
		.append(" FROM ")
			.append(" wlist ")
		;
		
		return getJdbcTemplate().query(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> getWetVO(rs, true)
			);
	}
	
	public List<WetVO> getOne(WetVO vo) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		
		sb
		.append(" SELECT ")
			.append(" * ")
		.append(" FROM ")
			.append(" wlist ")
		.append(" WHERE ")
			.append(" res_id = ")
			.append((rand.nextInt(343)+1))
		;
		
		return getJdbcTemplate().query(sb.toString()
				, new BeanPropertySqlParameterSource(vo)
				, (rs, rowNum) -> getWetVO(rs, false)
			);
	}

	private WetVO getWetVO(ResultSet rs, boolean isList) throws SQLException {
		if( isList ) {
			return WetVO.builder()
					.resId( rs.getInt("res_id") )
					.resName( rs.getString("res_name") )
					.resType( rs.getString("res_type") )
					.resAddrRoad( rs.getString("res_addr_road") )
					.resAddrJibun( rs.getString("res_addr_jibun") )
					.resHoliday( rs.getString("res_holiday") )
					.resLat( rs.getString("res_lat") )
					.resLong( rs.getString("res_long") )
					.resFamousMenu( rs.getString("res_famous_menu") )
				.build();
		} else {
			return WetVO.builder()
					.resId( rs.getInt("res_id") )
					.resName( rs.getString("res_name") )
					.resType( rs.getString("res_type") )
					.resAddrRoad( rs.getString("res_addr_road") )
					.resAddrJibun( rs.getString("res_addr_jibun") )
					.resHoliday( rs.getString("res_holiday") )
					.resLat( rs.getString("res_lat") )
					.resLong( rs.getString("res_long") )
					.resFamousMenu( rs.getString("res_famous_menu") )
				.build();
		}
	}
}
