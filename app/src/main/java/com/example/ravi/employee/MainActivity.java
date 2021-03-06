package com.example.ravi.employee;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.ListItemClickListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Employee> myDataset;

    private MyDatabase mdb;
    private MyAdapter.ListItemClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDataset = new ArrayList<>();
        listener=this;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        mdb = MyDatabase.getAppDatabase(getApplicationContext());
        setupViewModel();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu,menu);

       return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.add) {
            Intent addintent = new Intent(MainActivity.this, addActivity.class);
            startActivity(addintent);



        }

        if(id == R.id.deleteAll) {
            AppExecutor.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mdb.employeeDAO().deleteAll(myDataset);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myDataset = new ArrayList<>();
                            mAdapter = new MyAdapter(myDataset,listener );
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    });
                }

            });



        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onResume() {
        super.onResume();
       //setupViewModel();
        mAdapter = new MyAdapter(myDataset,MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

      


    }


    @Override
    public void onListItemClick(int ClickedItemIndex) {

        Intent intent = new Intent(MainActivity.this,EditActivity.class);

        intent.putExtra("name",myDataset.get(ClickedItemIndex).getName());
        intent.putExtra("post",myDataset.get(ClickedItemIndex).getPost());
        intent.putExtra("age",myDataset.get(ClickedItemIndex).getAge());
        intent.putExtra("gender",myDataset.get(ClickedItemIndex).getGender());
        intent.putExtra("id",myDataset.get(ClickedItemIndex).getId());
        startActivity(intent);



    }

    public void setupViewModel(){

               ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);
               viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
                   @Override
                   public void onChanged(@Nullable List<Employee> employees) {
                       myDataset = (ArrayList<Employee>)employees;
                       mAdapter = new MyAdapter(myDataset,listener );
                       mRecyclerView.setAdapter(mAdapter);
                   }
               });

            }



}
