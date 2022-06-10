package prv.jarkchen.service.user;

import com.baomidou.mybatisplus.extension.service.IService;

import prv.jarkchen.pojo.user.User;
import prv.jarkchen.vo.RespBean;
import prv.jarkchen.vo.user.LoginVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    User getUserByCookie(String ticket, HttpServletRequest request, HttpServletResponse response);

//    RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response);
    
    RespBean registUser(LoginVo loginVo);
}
