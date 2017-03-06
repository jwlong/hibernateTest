package hibernateTest.domain;

/**
 * Created by longjinwen on 2017/3/4.
 */
public class Name {
    private String first;
    private String last;
    private Person owner; // 引用拥有该类的Person 对象
    //提供无参的构造方法
    public Name(){}
    //暂不提供构造方法
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
