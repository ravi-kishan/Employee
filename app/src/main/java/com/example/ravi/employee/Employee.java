package com.example.ravi.employee;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ravi on 13-07-2018.
 */

@Entity(tableName = "employees")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "post")
    private String post;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "gender")
    private int gender;

    @Ignore
    public Employee(String name,int age,String post,int gender){
        this.name = name;
        this.age = age;
        this.post = post;
        this.gender = gender;


    }


    public Employee(String name,int age,String post,int gender,int id){
        this.name = name;
        this.age = age;
        this.post = post;
        this.gender = gender;
        this.id = id;


    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }



}
