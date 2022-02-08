package wet.gradle.sts4.backend.review.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import wet.gradle.sts4.backend.review.service.ReviewService;
import wet.gradle.sts4.backend.review.vo.ReviewVO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/review")
public class ReviewController {

	@Autowired ReviewService reviewService;
	
	@RequestMapping(value="/list")
	public Map<String, Object> list(ReviewVO vo) {
		Map<String, Object> result = Maps.newHashMap();
		
		result.put("data", reviewService.getList(vo));

		return result;
	}
	
	@RequestMapping(value="/one")
	public Map<String, Object> one(HttpServletRequest request, ReviewVO vo) {
		Map<String, Object> result = Maps.newHashMap();

		vo.setResId(Integer.parseInt(request.getParameter("resId")));
		result.put("data", reviewService.getOne(vo));

		return result;
	}
	
	@RequestMapping(value="/count")
	public int count(ReviewVO vo) {

		return reviewService.getCount(vo);
	}
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public void write(ReviewVO vo) {
		
		reviewService.write(vo);
	}
	
}
