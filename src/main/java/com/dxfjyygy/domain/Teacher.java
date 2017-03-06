package com.dxfjyygy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by longjinwen on 2017/3/3.
 */
public class Teacher implements Serializable {


    private int id;
    private String name;
    private int age;
    private List<Student> students = new ArrayList<Student>();

//    private List<Integer> students = new ArrayList<Integer>();

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
//    public List<Integer> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Integer> students) {
//        this.students = students;
//    }
}
