package prv.jarkchen.utils;

import java.util.UUID;

public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");    // 去除 -
    }
}
