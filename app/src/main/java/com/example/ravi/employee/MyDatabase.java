package com.example.ravi.employee;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Ravi on 28-07-2018.
 */
@Database(entities = {Employee.class}, version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract employeesDAO employeeDAO();

    private static MyDatabase INSTANCE;

    public static MyDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "office.db")
                           
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }



}
