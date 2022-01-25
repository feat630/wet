package wet.gradle.sts4.backend.vo;

import common.vo.PaginVO;
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
public class WetVO extends PaginVO{

	private int resId;
	private String resName;
	private String resType;
	private String resAddrRoad;
	private String resAddrJibun;
	private String resHoliday;
	private String resLat;
	private String resLong;
	private String resFamousMenu;
}
