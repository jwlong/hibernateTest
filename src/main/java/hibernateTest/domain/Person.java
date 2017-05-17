package hibernateTest.domain;

import java.util.HashMap;
import java.util.Map;

import hibernateTest.domain.Name;

/**
 * map 的索引
 * Created by longjinwen on 2017/3/4.
 */

public class Person {
    private Integer id;
    private int age;
    private Map<Name, Integer> nickPower = new HashMap<Name, Integer>();

    public Map<Name, Integer> getNickPower() {
        return nickPower;
    }

    public void setNickPower(Map<Name, Integer> nickPower) {
        this.nickPower = nickPower;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
