package com.example.ravi.employee;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.provider.LiveFolders;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 28-07-2018.
 */
@Dao
public interface employeesDAO {
    @Query("SELECT * FROM employees")
   LiveData<List<Employee>> getAll();

   // @Query("SELECT * FROM product WHERE name LIKE :name LIMIT 1")
    //Product findByName(String name);

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Delete
    void deleteAll(List<Employee> employees);
}
