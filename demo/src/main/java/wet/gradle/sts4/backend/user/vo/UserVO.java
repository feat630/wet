package wet.gradle.sts4.backend.user.vo;

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
public class UserVO {
	private String userId;
	private String userPw;
	private String userEmail;
	private Boolean loginCheck;
}
