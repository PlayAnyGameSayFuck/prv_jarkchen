package prv.jarkchen.vo.LeftContent;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeftNavigationVo {

	@TableId(type = IdType.AUTO)
    private Integer id;
	
	private String navName;
	private String navUrl;
	private String navUrl2;
	private String navTo;
	private String active;
}
