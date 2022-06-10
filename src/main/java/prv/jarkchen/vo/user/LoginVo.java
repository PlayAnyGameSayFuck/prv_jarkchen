package prv.jarkchen.vo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {

	@TableId(type = IdType.AUTO)
    private Integer id;
	@TableId(type = IdType.ASSIGN_UUID)
    private String userId;
    private String acount;
    private String password;
    private String salt;
    @TableField(exist = false)
    private String token;
}
