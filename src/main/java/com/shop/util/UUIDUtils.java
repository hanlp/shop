package com.shop.util;

import java.util.UUID;

/**
 * ���ɼ�����
 * @author 
 *
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
