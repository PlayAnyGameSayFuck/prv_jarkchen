package prv.jarkchen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private Integer code;
    private String message;
    private Object obj;

    // 成功返回结果
    public static RespBean success(RespBeanEnum respBeanEnum){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), respBeanEnum.getMessage(), null);
    }

    // 成功返回结果
    public static RespBean success(RespBeanEnum respBeanEnum, Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), respBeanEnum.getMessage(), obj);
    }

    // 失败返回结果
    public static RespBean error(RespBeanEnum respBeanEnum){
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), null);
    }

    // 失败返回结果
    public static RespBean error(RespBeanEnum respBeanEnum, Object obj){
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), obj);
    }
}
