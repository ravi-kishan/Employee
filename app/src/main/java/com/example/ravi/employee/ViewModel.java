package com.example.ravi.employee;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Ravi on 04-08-2018.
 */

public class ViewModel extends AndroidViewModel{

    private LiveData<List<Employee>> employees;

    public ViewModel(@NonNull Application application) {
        super(application);
        MyDatabase database = MyDatabase.getAppDatabase(this.getApplication());
        employees = database.employeeDAO().getAll();
    }

    public LiveData<List<Employee>> getEmployees(){
        return employees;
    }


}
