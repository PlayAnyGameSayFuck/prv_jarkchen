package prv.jarkchen.config;

import java.lang.reflect.Method;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import prv.jarkchen.annotation.SysLogAnnotation;
import prv.jarkchen.controller.system.SysLogController;
import prv.jarkchen.pojo.system.SysLog;


@Aspect
@Order(100)
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogController sysLogController;

	private static final Logger log = LoggerFactory.getLogger(SysLogAspect.class);
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String LOG_START = "<-----------------------------------\n";
	private static final String LOG_END = "------------------------------------->\n";
	
	/*
	 * 不使用 注解方式
	 *  @Pointcut("execution (* com.example.demo.web..*(..))")")
	 */
	
	/**
	 * 在 使用 Log 注解的地方 切入
	 */
	@Pointcut(value="@annotation(prv.jarkchen.annotation.SysLogAnnotation)")
	public void logPointCut(){
	}
	
	// @Aound("logPointCut()")   用于 controller 层
	public Object controllerAround(ProceedingJoinPoint joinPoint) {
		try {
			return printLog(joinPoint);
		}catch(Throwable throwable) {
			log.error(throwable.getMessage(), throwable);
			return true;
		}
	}
	
	// 通知：拦截到 连接点之后 要执行的代码
	@Around("logPointCut()")
	private Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
		
		
		// 获取连接点方法 签名
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		
		// 获取 方法,类
		Method method = signature.getMethod();
		Class<?> targetClass = method.getDeclaringClass();
		
		StringBuffer classAndMethod = new StringBuffer();
		
		// 获取 目标方法上的 log 注解
		SysLogAnnotation methodAnnotation = method.getAnnotation(SysLogAnnotation.class);
		
		// 判断 是否有 log 注解，及 ignore 参数
		if(methodAnnotation != null) {
			classAndMethod.append(methodAnnotation.opretion());
			classAndMethod.append('-');
			classAndMethod.append(methodAnnotation.type());
			classAndMethod.append('-');
			classAndMethod.append(methodAnnotation.level());
		}
		
		// 拼接 类和方法
		String target = targetClass.getName() + "." + method.getName();
		
		// 请求参数 转为 json 
		String params = JSONObject.toJSONStringWithDateFormat(joinPoint.getArgs(), dateFormat, SerializerFeature.WriteMapNullValue);
		
		// 日志打印 拼接的 调用信息
		String logHead = LOG_START + classAndMethod.toString() + " " + target + " 参数 " + params;
		log.info(logHead);
		
		long startTime = System.currentTimeMillis();
		
		// 反射 - 执行目标对象 连接处的方法 
		Object result = joinPoint.proceed();
		
		long timeConSum = System.currentTimeMillis() - startTime;
		
		String logFool = "";
		if(methodAnnotation!=null && methodAnnotation.ignoreReturn()) {
			logFool = LOG_END + classAndMethod.toString() + " " + target + " 耗时: " + timeConSum + "ms";
			log.info(logFool);
			return result;
		}
		
		// 响应参数转 JSON
		logFool = LOG_END + classAndMethod.toString() + " " + target + " 返回值:" + 
				JSONObject.toJSONStringWithDateFormat(result, dateFormat, SerializerFeature.WriteMapNullValue) + " 耗时:" + timeConSum + "ms";
		log.info(logFool);
		
		if(methodAnnotation != null) {
			SysLog sysLog = new SysLog();
			sysLog.setLogName(methodAnnotation.opretion());
			sysLog.setLogMessage(logHead + "\n" + logFool);
			sysLog.setLogSource(target.toString());
			sysLog.setLogLevel(methodAnnotation.level());
			sysLog.setLogType(methodAnnotation.type());
			sysLogController.saveLog(sysLog);
		}
		
		return result;
	}
	
	/**
	 * 调用 实现
	 */
	
//	@Service
//	@Slf4j
//	public class UserServiceImpl implements UserService {
//		
//		@Autowired
//		private UserMapper userMapper;
//		
//		@Override
//		@Log
//		public User getUser(Integer id) {
//			User user = userMapper.getById(id);
//			log.info("message: {}", user);
//			return user;
//		}
//	}
}
