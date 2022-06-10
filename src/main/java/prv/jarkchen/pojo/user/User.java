package prv.jarkchen.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("prv_user")
public class User {

	@TableId(type = IdType.AUTO)
    private Integer id;
	@TableId(type = IdType.ASSIGN_UUID)
    private String userId;
    private String acount;
    private String mobile;
    private String eMail;
    private String qq;
    private String weChat;
    private String password;
    private String idCard;
    private String createTime;
    private String modifiedTime;
    private String salt;
    @TableField(exist = false)
    private String token;
}
