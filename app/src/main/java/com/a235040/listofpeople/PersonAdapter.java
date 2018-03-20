package com.a235040.listofpeople;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Szymon on 20.03.2018.
 */

public class PersonAdapter extends ArrayAdapter<Person>{
    public PersonAdapter(Context context, int resource, List<Person> people) {
        super(context, resource, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.person_list_row_layout, null);
        }

        final Person person = getItem(position);
        if(person != null){
            TextView nameTextView = view.findViewById(R.id.personNameTextView);
            TextView surnameTextView = view.findViewById(R.id.personSurnameTextView);
            TextView ageTextView = view.findViewById(R.id.personAgeTextView);

            nameTextView.setText(person.getName());
            surnameTextView.setText(person.getSurname());
            ageTextView.setText(Integer.toString(person.getAge()));
            ImageButton deleteButton = view.findViewById(R.id.imageButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove(person);
                    notifyDataSetChanged();
                }
            });
        }
        return view;
    }
}
