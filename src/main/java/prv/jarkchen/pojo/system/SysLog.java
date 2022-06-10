package prv.jarkchen.pojo.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("prv_log")
public class SysLog {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String logId;
    private String logName;
    private String logMessage;
    private String logSource;
    private String logTime;
    private String logType;
    private String logLevel;
    private String logFile;
    private String logUserId;
    private String logUserName;
}
