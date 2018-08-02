package com.example.ravi.employee;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ravi on 15-07-2018.
 */

public class EmployeeContracts  {

    private EmployeeContracts(){}

    public static final String CONTENT_AUTHORITY = "com.example.ravi.employee";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_EMPLOYEES = "employees";



    public static final class EmployeeEntry implements BaseColumns{


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EMPLOYEES);

        public static final String TABLE_NAME = "employees";


    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_EMPLOYEE_NAME = "name";
    public static final String COLUMN_EMPLOYEE_POST = "post";
    public static final String COLUMN_EMPLOYEE_GENDER = "gender";
    public static final String COLUMN_EMPLOYEE_AGE = "age";

    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;


}


}
