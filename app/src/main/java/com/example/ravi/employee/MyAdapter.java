package com.example.ravi.employee;

/**
 * Created by Ravi on 13-07-2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import static com.example.ravi.employee.EmployeeContracts.EmployeeEntry.GENDER_FEMALE;
import static com.example.ravi.employee.EmployeeContracts.EmployeeEntry.GENDER_MALE;
import static com.example.ravi.employee.EmployeeContracts.EmployeeEntry.GENDER_UNKNOWN;

/**
 * Created by Ravi on 13-07-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private ArrayList<Employee> mDataset;

      final private ListItemClickListener listItemClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int ClickedItemIndex);

    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Employee> myDataset,ListItemClickListener listener) {
        listItemClickListener = listener;
        mDataset = myDataset;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView name;
        public TextView post;
        public TextView age;
        public TextView gender;

        public ViewHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.name);
            post = (TextView)v.findViewById(R.id.post);
            age = (TextView)v.findViewById(R.id.age);
            gender = (TextView)v.findViewById(R.id.gender);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.onListItemClick(clickedPosition);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset.get(position).getName());
        holder.post.setText(mDataset.get(position).getPost());
        holder.age.setText(Integer.toString(mDataset.get(position).getAge()));
        int gender = mDataset.get(position).getGender();
        switch (gender)
        {
            case GENDER_UNKNOWN:
                holder.gender.setText("Unknown");
                break;
            case GENDER_MALE:
                holder.gender.setText("Male");
                break;
            case GENDER_FEMALE:
                holder.gender.setText("Female");
                break;
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
