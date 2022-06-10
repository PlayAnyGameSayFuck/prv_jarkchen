package prv.jarkchen.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象 转为 字符串
     */

    public static String object2JsonString(Object obj){
        try{
            return objectMapper.writeValueAsString(obj);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串 转为 对象
     */

    @SuppressWarnings("unchecked")
	public static <T> T jsonString2Object(String jsonString, Class<?> clazz){
        try{
        	// 存在 不安全转型
            return (T) objectMapper.readValue(jsonString.getBytes("UTF-8"), clazz);
        }catch(JsonParseException e){
            e.printStackTrace();
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON 转为 pojo(list) 对象
     */

    public static <T> List<T> jsonToList(String jsonString, Class<T> beanType){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, beanType);
        try{
            List<T> list = objectMapper.readValue(jsonString, javaType);
            return list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
