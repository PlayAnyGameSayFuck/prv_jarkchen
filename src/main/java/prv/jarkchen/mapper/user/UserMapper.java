package prv.jarkchen.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import prv.jarkchen.pojo.user.User;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
