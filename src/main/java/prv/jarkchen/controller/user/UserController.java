package prv.jarkchen.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import prv.jarkchen.service.user.UserService;
import prv.jarkchen.vo.RespBean;
import prv.jarkchen.vo.RespBeanEnum;
import prv.jarkchen.vo.user.LoginVo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	String salt = "jarkchen";
	
	@PostMapping(value="/login")
	@ResponseBody
	public RespBean userLogin(@RequestBody LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
		return userService.doLogin(loginVo, request, response);
	}
	
	@CrossOrigin
	@PostMapping(value="/regist")
	@ResponseBody
	public RespBean registUser(@RequestBody LoginVo loginVo) {
		if(loginVo == null || !loginVo.getSalt().equals(salt)) {
			return RespBean.error(RespBeanEnum.USER_SALT_NOT_INPUT, loginVo);
		}
		return userService.registUser(loginVo);
	}
}
