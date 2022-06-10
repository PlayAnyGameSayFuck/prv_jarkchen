package prv.jarkchen.utils;

import java.util.HashMap;
import java.util.Map;

import prv.jarkchen.vo.user.LoginVo;


public class TokenUtil {

	/*
	 *  token - user
	 */
	private static Map<String, LoginVo> tokenMap = new HashMap<>();
	
	/*
	 * 生成 token， 存储
	 */
	public static String generateToken(LoginVo loginVo) {
		String token = UUIDUtil.uuid();
		tokenMap.put(token, loginVo);
		return token;
	}
	
	/*
	 * 验证 token
	 */
	public static boolean verify(String token) {
		return tokenMap.containsKey(token);
	}
	
	/*
	 * 获取 用户
	 */
	public static LoginVo getLoginVo(String token) {
		return tokenMap.get(token);
	}
}
