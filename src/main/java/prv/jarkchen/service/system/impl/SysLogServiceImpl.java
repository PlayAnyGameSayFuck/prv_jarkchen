package prv.jarkchen.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import prv.jarkchen.mapper.system.SysLogMapper;
import prv.jarkchen.pojo.system.SysLog;
import prv.jarkchen.service.system.SysLogService;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
