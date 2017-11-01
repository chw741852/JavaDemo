package com.hong.concurrent.simple;

import com.hong.concurrent.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by caihongwei on 2015/12/29 11:17.
 */
@ThreadSafe
public class TestFinalMap {
    private final Map<String, String> locations;
    private final Map<String, String> unmodifiableMap;

    public TestFinalMap(Map<String, String> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations); // 不可变map得到了可变map的引用
    }

    public Map<String, String> getLocations() {
        return unmodifiableMap;
    }

    public String getLocation(String id) {
        return locations.get(id);
    }

    public void setLocations(String id, String point) {
        if (!locations.containsKey(id))
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        locations.replace(id, point);
    }

    public static void main(String[] args) {
        Map<String, String> locations = new HashMap<>();
        locations.put("1", "11,22");
        locations.put("2", "11,22");
        locations.put("3", "11,22");
        locations.put("4", "11,22");

        TestFinalMap test = new TestFinalMap(locations);
        test.setLocations("3", "33,44");
        for (String key : test.getLocations().keySet()) {
            System.out.println("id: " + key + ", point: " + test.getLocations().get(key));
        }
    }
}
