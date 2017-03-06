package com.dxfjyygy.domain;

import java.io.Serializable;

/**
 * Created by longjinwen on 2017/3/3.
 */
public class Student implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private  int id;
    private String name;
    private int age;
}
