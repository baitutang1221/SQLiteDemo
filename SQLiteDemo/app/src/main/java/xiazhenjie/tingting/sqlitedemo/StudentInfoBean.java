package xiazhenjie.tingting.sqlitedemo;

import java.io.Serializable;

public class StudentInfoBean implements Serializable {
    public String name;
    public int age;
    public String sex;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public StudentInfoBean() {
    }

    public StudentInfoBean(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
