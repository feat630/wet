package wet.gradle.sts4.backend.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wet.gradle.sts4.backend.restaurant.dao.WetDAO;
import wet.gradle.sts4.backend.restaurant.vo.WetVO;

@Service
public class WetService {
	
	@Autowired WetDAO wetDAO;

	public List<WetVO> getList(WetVO vo) {
		return wetDAO.getList(vo);
	}

	public List<WetVO> getOne(WetVO vo) {
		int count = wetDAO.getCount(vo);
		return wetDAO.getOne(vo, count);
	}
	
	public int getCount(WetVO vo) {
		return wetDAO.getCount(vo);
	}
}