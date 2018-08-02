package com.example.ravi.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



public class EditActivity extends AppCompatActivity {


    EditText nameEntry;
    EditText postEntry;
    EditText ageEntry;
    Spinner genderEntry;

    int mGender = 0;
    int id;

    private MyDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mdb = MyDatabase.getAppDatabase(getApplicationContext());

        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);

        nameEntry = (EditText)findViewById(R.id.nameEntry);
        postEntry = (EditText)findViewById(R.id.postEntry);
        ageEntry =  (EditText) findViewById(R.id.ageEntry);
        genderEntry = (Spinner)findViewById(R.id.genderEntry);

        nameEntry.setText(intent.getStringExtra("name"));
        postEntry.setText(intent.getStringExtra("post"));
        ageEntry.setText(Integer.toString(intent.getIntExtra("age",0)));
        mGender = intent.getIntExtra("gender",0);

        setupSpinner();
        genderEntry.setSelection(mGender);
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
                        mGender = EmployeeContracts.EmployeeEntry.GENDER_MALE; // Male
                    } else if (selection.equals(getString(R.string.female))) {
                        mGender = EmployeeContracts.EmployeeEntry.GENDER_FEMALE; // Female
                    } else {
                        mGender = EmployeeContracts.EmployeeEntry.GENDER_UNKNOWN; // Unknown
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

    void submit (View view){
        Employee employee = new Employee(String.valueOf(nameEntry.getText()),Integer.parseInt(ageEntry.getText().toString()),String.valueOf(postEntry.getText()),mGender,id);



        mdb.employeeDAO().update(employee);
        finish();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.edit_menu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idm = item.getItemId();

        if(idm == R.id.delete) {

            Employee employee = new Employee(String.valueOf(nameEntry.getText()),Integer.parseInt(ageEntry.getText().toString()),String.valueOf(postEntry.getText()),mGender,id);

            mdb.employeeDAO().delete(employee);
            finish();

        }



        return super.onOptionsItemSelected(item);
    }
}
