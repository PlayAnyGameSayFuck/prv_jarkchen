package prv.jarkchen.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import prv.jarkchen.annotation.SysLogAnnotation;
import prv.jarkchen.pojo.system.SysLog;
import prv.jarkchen.service.system.SysLogService;
import prv.jarkchen.utils.UUIDUtil;
import prv.jarkchen.vo.RespBean;
import prv.jarkchen.vo.RespBeanEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value="/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @ResponseBody
    public RespBean saveLog(@RequestBody SysLog sysLog){
        if (StringUtils.isEmpty(sysLog.getLogMessage())){
            return RespBean.error(RespBeanEnum.LOG_MESSAGE_NULL_ERROR, sysLog);
        }
        if(StringUtils.isEmpty(sysLog.getLogSource())){
            return RespBean.error(RespBeanEnum.LOG_SOURCE_NULL_ERROR, sysLog);
        }

        String logId = UUIDUtil.uuid();
        String logTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        sysLog.setLogId(logId);
        sysLog.setLogTime(logTime);

        Boolean save = sysLogService.save(sysLog);
        if(!save){
            return RespBean.error(RespBeanEnum.ERROR, sysLog);
        }
        return RespBean.success(RespBeanEnum.SUCCESS);
    }
    
    @RequestMapping(value="/query")
    @ResponseBody
    @SysLogAnnotation(opretion = "查询日志", type = "sys", level = "log")
    public List<SysLog> queryList(){
    	List<SysLog> sysLogs = sysLogService.list();
    	return sysLogs;
    }
}
