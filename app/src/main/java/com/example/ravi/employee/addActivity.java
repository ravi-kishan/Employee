package com.example.ravi.employee;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ravi.employee.EmployeeContracts.EmployeeEntry;

public class addActivity extends AppCompatActivity {

    EditText nameEntry;
    EditText postEntry;
    EditText ageEntry;
    Spinner genderEntry;
    int mGender = 0;


    private MyDatabase mdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mdb = MyDatabase.getAppDatabase(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        nameEntry = (EditText)findViewById(R.id.nameEntry);
        postEntry = (EditText)findViewById(R.id.postEntry);
        ageEntry =  (EditText) findViewById(R.id.ageEntry);
        genderEntry = (Spinner)findViewById(R.id.genderEntry);
        setupSpinner();


    }


   void submit (View view){
        final Employee employee = new Employee(String.valueOf(nameEntry.getText()),Integer.parseInt(ageEntry.getText().toString()),String.valueOf(postEntry.getText()),mGender);

        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mdb.employeeDAO().insert(employee);
                finish();
            }
        });





   }



    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        genderEntry.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        genderEntry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.male))) {
                        mGender = EmployeeEntry.GENDER_MALE; // Male
                    } else if (selection.equals(getString(R.string.female))) {
                        mGender = EmployeeEntry.GENDER_FEMALE; // Female
                    } else {
                        mGender = EmployeeEntry.GENDER_UNKNOWN; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });
    }



}
