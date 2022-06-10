package prv.jarkchen.pojo.leftContent;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("prv_nav")
public class LeftNavigation {

	@TableId(type = IdType.AUTO)
    private Integer id;
	
	private String navName;
	private String navUrl;
	private String navUrl2;
	private String navTo;
	private String active;
}
