package com.shop.util;

import java.util.UUID;

/**
 * Éú³É¼¤»îÂë
 * @author 
 *
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
