package com.szss.commons.security.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class SortCollection {


    public static String sort(String appsecret, Map<String, ?> map) {
        StringBuilder s = new StringBuilder();
        //method数组值处理
        Object method = map.get(Constants.REQUEST_HTTP_METHOD_PARAMETER_NAME);
        if (method instanceof List) {
            s.append(((List) method).get(0));
        } else if (method instanceof Object[]) {
            s.append(((String[]) method)[0]);
        } else {
            s.append(method);
        }
        //url数组值处理
        Object url = map.get(Constants.REQUEST_URI_PARAMETER_NAME);
        if (url instanceof List) {
            s.append(((List) url).get(0));
        } else if (url instanceof Object[]) {
            s.append(((String[]) url)[0]);
        } else {
            s.append(url);
        }
        //删除url和method参数
        map.remove(Constants.REQUEST_HTTP_METHOD_PARAMETER_NAME);
        map.remove(Constants.REQUEST_URI_PARAMETER_NAME);
        Map<String, Object> sortMap = SortCollection.sortMapByKey(map);
        s.append(appendMap(sortMap));
        s.append(appsecret);
        String signStr = null;
        try {
            signStr = URLEncoder.encode(s.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return signStr;
    }


    /**
     * 对Map按key进行排序，支持map和list混合嵌套
     *
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, ?> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(new MapComparator());
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                sortMap.put(entry.getKey(), sortMapByKey((Map) entry.getValue()));
                continue;
            }
            if (entry.getValue() instanceof List) {
                sortMap.put(entry.getKey(), sortListByValue((List) entry.getValue()));
                continue;
            }
            if (entry.getValue() instanceof String[]) {
                String[] temp = (String[]) entry.getValue();
                sortMap.put(entry.getKey(), sortListByValue(Arrays.asList(temp)));
                continue;
            }
            if (entry.getValue() instanceof String) {
                sortMap.put(entry.getKey(), entry.getValue());
                continue;
            }
            if (entry.getValue() instanceof Boolean) {
                sortMap.put(entry.getKey(), entry.getValue().toString());
                continue;
            }
            if (entry.getValue() instanceof Number) {
                sortMap.put(entry.getKey(), entry.getValue());
                continue;
            }
        }
        return sortMap;
    }

    /**
     * 对list进行排序，支持map和list混合嵌套
     *
     * @param list
     * @return
     */
    public static List<Object> sortListByValue(List<?> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<Object> sortList = new ArrayList<Object>();
        for (Object item : list) {
            if (item instanceof Map) {
                sortList.add(sortMapByKey((Map) item));
                continue;
            }
            if (item instanceof List) {
                sortList.add(sortListByValue((List) item));
                continue;
            }
            if (item instanceof String[]) {
                sortList.add(sortListByValue(Arrays.asList((String[]) item)));
                continue;
            }
            if (item instanceof String) {
                sortList.add(item);
                continue;
            }
            if (item instanceof Boolean) {
                sortList.add(item);
                continue;
            }
            if (item instanceof Number) {
                sortList.add(item);
                continue;
            }

        }
        Collections.sort(sortList, new MapComparator());
        return sortList;
    }

    /**
     * 获取map的key和value链接字符串，支持map和list混合嵌套
     *
     * @param map
     * @return
     */
    public static String appendMap(Map<String, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                sb.append(entry.getKey());
                sb.append(appendMap((Map) entry.getValue()));
                continue;
            }
            if (entry.getValue() instanceof List) {
                sb.append(entry.getKey());
                sb.append(appendList((List) entry.getValue()));
                continue;
            }
            if (entry.getValue() instanceof String[]) {
                sb.append(entry.getKey());
                String[] temp = (String[]) entry.getValue();
                sb.append(appendList(Arrays.asList(temp)));
                continue;
            }
            if (entry.getValue() instanceof String) {
                sb.append(entry.getKey());
                sb.append(entry.getValue());
                continue;
            }
            if (entry.getValue() instanceof Boolean) {
                sb.append(entry.getKey());
                sb.append(entry.getValue());
                continue;
            }
            if (entry.getValue() instanceof Number) {
                sb.append(entry.getKey());
                sb.append(entry.getValue());
                continue;
            }
        }
        return sb.toString();
    }

    /**
     * 获取list链接字符串，支持map和list混合嵌套
     *
     * @param list
     * @return
     */
    public static String appendList(List list) {
        StringBuilder sb = new StringBuilder();
        for (Object item : list) {
            if (item instanceof Map) {
                sb.append(appendMap((Map) item));
                continue;
            }
            if (item instanceof List) {
                sb.append(appendList((List) item));
                continue;
            }
            if (item instanceof String[]) {
                String[] temp = (String[]) item;
                sb.append(appendList(Arrays.asList(temp)));
                continue;
            }
            if (item instanceof String) {
                sb.append(item);
                continue;
            }
            if (item instanceof Boolean) {
                sb.append(item);
                continue;
            }
            if (item instanceof Number) {
                sb.append(item);
                continue;
            }
        }
        return sb.toString();
    }
}


//比较器类
class MapComparator implements Comparator<Object> {
    public int compare(Object o1, Object o2) {
        return o1.toString().compareTo(o2.toString());
    }
}
