package com.example.administrator.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author by JingQ on 2018/5/5.
 */

public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }
}
