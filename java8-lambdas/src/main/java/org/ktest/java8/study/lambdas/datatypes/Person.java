/**
 * file       : Person.java
 * author     : Kiran Mohan 
 * created on : Mar 27, 2014, 6:03:50 PM
 */
 

package org.ktest.java8.study.lambdas.datatypes;

import java.time.LocalDate;

/**
 *
 * @author Kiran Mohan
 */
public class Person {
    
    public enum Sex {
        MALE, FEMALE;
    }

    
    private final String name;
    private final LocalDate birthday;
    private final String email;
    private final Sex sex;

    public Person(String name, LocalDate birthday, Sex sex, String email) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge(){
        return LocalDate.now().getYear() - birthday.getYear();
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + ", sex=" + sex + name + ", age=" + getAge() + ", email=" + email + '}';
    }

}
