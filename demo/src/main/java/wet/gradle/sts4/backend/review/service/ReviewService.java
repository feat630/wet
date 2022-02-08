package wet.gradle.sts4.backend.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wet.gradle.sts4.backend.review.dao.ReviewDAO;
import wet.gradle.sts4.backend.review.vo.ReviewVO;

@Service
public class ReviewService {

	@Autowired ReviewDAO reviewDAO;

	public List<ReviewVO> getList(ReviewVO vo) {
		return reviewDAO.getList(vo);
	}

	public List<ReviewVO> getOne(ReviewVO vo) {
		return reviewDAO.getOne(vo);
	}
	
	public int getCount(ReviewVO vo) {
		return reviewDAO.getCount(vo);
	}
	
	@Transactional
	public int write(ReviewVO vo) {
		return reviewDAO.insertPost(vo);
	}
}
