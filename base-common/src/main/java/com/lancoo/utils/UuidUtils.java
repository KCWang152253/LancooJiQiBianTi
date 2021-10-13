package com.lancoo.utils;

import java.util.UUID;

public class UuidUtils {
    public static String getUuId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
