package wet.gradle.sts4.backend.review.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewVO {
	private int resId;
	private String userId;
	private String image;
	private int rating;
	private String userReview;
}
