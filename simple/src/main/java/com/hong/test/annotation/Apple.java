package com.hong.test.annotation;

/**
 * 使用注解
 * <p>
 * Created by Hongwei on 2015/10/25.
 */
public class Apple {
    @FruitName("Apple")
    private String name;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String color;

    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
    private String provider;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void displayName() {
        System.out.println("水果的名字是：苹果");
    }
}
