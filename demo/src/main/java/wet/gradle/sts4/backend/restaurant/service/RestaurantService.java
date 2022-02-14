package wet.gradle.sts4.backend.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wet.gradle.sts4.backend.restaurant.dao.RestaurantDAO;
import wet.gradle.sts4.backend.restaurant.vo.RestaurantVO;

@Service
public class RestaurantService {
	
	@Autowired RestaurantDAO RestaurantDAO;

	public List<RestaurantVO> getList(RestaurantVO vo) {
		return RestaurantDAO.getList(vo);
	}

	public List<RestaurantVO> getOne(RestaurantVO vo) {
		int count = RestaurantDAO.getCount(vo);
		return RestaurantDAO.getOne(vo, count);
	}
	
	public int getCount(RestaurantVO vo) {
		return RestaurantDAO.getCount(vo);
	}
	
	@Transactional
	public int add(RestaurantVO vo) {
		return RestaurantDAO.addRestaurant(vo);
	}

	@Transactional
	public int remove(RestaurantVO vo) {
		return RestaurantDAO.removeRestaurant(vo);
	}
}
