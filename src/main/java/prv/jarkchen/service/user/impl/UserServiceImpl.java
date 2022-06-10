package prv.jarkchen.service.user.impl;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import prv.jarkchen.mapper.user.UserMapper;
import prv.jarkchen.pojo.user.User;
import prv.jarkchen.service.user.UserService;
import prv.jarkchen.utils.CookieUtil;
import prv.jarkchen.utils.MD5Util;
import prv.jarkchen.utils.TokenUtil;
import prv.jarkchen.utils.UUIDUtil;
import prv.jarkchen.vo.RespBean;
import prv.jarkchen.vo.RespBeanEnum;
import prv.jarkchen.vo.user.LoginVo;

/**
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆
     */
    
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
    	if(loginVo == null) {
			return RespBean.error(RespBeanEnum.USER_NOT_EXIST, loginVo);
		}
		if(StringUtils.isEmpty(loginVo.getAcount())) {
			return RespBean.error(RespBeanEnum.USER_ACOUNT_NOT_INPUT, loginVo);
		}
		if(StringUtils.isEmpty(loginVo.getPassword())) {
			return RespBean.error(RespBeanEnum.USER_PWD_NOT_INPUT, loginVo);
		}

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("acount", loginVo.getAcount());
		User user = userMapper.selectOne(queryWrapper);
		if(user == null) {
			return RespBean.error(RespBeanEnum.USER_NOT_EXIST, loginVo);
		}
		String pwd = MD5Util.inputPassToDBPass(loginVo.getPassword(), user.getSalt());
		if(!user.getPassword().equals(pwd)) {
			return RespBean.error(RespBeanEnum.USER_PASSWORD_ERROR, loginVo);
		}
		String ticket = user.getUserId();
		
        request.getSession().setAttribute(ticket, user);  // 放入 session 中
        loginVo.setToken(TokenUtil.generateToken(loginVo));
//        redisTemplate.opsForValue().set("user: "+ticket,user);
//        CookieUtil.setCookie(request, response, "userTicket", ticket);
        
        return RespBean.success(RespBeanEnum.USER_LOGIN_SUCCESS, loginVo);
    }
    

    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response){
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
//        User user = (User)redisTemplate.opsForValue().get("user: "+userTicket);
        User user = (User)request.getSession().getAttribute("userTicket");
        if(user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
    
    
//    @Override
//    public RespBean updatePassword(String userTicket, String password,HttpServletRequest request,HttpServletResponse response) {
//        User user=getUserByCookie(userTicket,request,response);
//        if(user==null){
//            return RespBean.error(RespBeanEnum.USER_LOGIN_SUCCESS);
//        }
//        user.setPassword(MD5Util.inputPassToDBPass(password,user.getSalt()));
//        int result = userMapper.updateById(user);
//        if(1==result){
////            redisTemplate.delete("user:"+userTicket);
//            return RespBean.success();
//        }
//        return RespBean.error();
//    }
    
    
	@Override
	public RespBean registUser(LoginVo loginVo) {
		if(loginVo == null) {
			return RespBean.error(RespBeanEnum.USER_NOT_EXIST, loginVo);
		}
		if(StringUtils.isEmpty(loginVo.getAcount())) {
			return RespBean.error(RespBeanEnum.USER_ACOUNT_NOT_INPUT, loginVo);
		}
		if(StringUtils.isEmpty(loginVo.getPassword())) {
			return RespBean.error(RespBeanEnum.USER_PWD_NOT_INPUT, loginVo);
		}
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("acount").eq("acount", loginVo.getAcount());
		User user = userMapper.selectOne(queryWrapper);
		if(user == null) {
			user = new User();
			user.setUserId(UUIDUtil.uuid());
			user.setAcount(loginVo.getAcount());
			
			String pwd = MD5Util.inputPassToDBPass(loginVo.getPassword(), loginVo.getSalt());
			user.setPassword(pwd);
			
			String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			String modifiedTime = createTime;
			user.setCreateTime(createTime);
			user.setModifiedTime(modifiedTime);
			user.setSalt(loginVo.getSalt());
			
			if(this.save(user)) {
				return RespBean.success(RespBeanEnum.USER_REGIST_SUCCESS);
			}else {
				return RespBean.error(RespBeanEnum.USER_REGIST_ERROR);
			}
		}
		return RespBean.error(RespBeanEnum.USER_ACOUNT_ALREADY_EXIST, loginVo);
	}   
    
}
