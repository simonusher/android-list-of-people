package com.a235040.listofpeople;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class InputActivity extends Activity {
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText dateEditText;
    private static final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initializeFields();
        addListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAccept:
                addPerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeFields(){
        this.nameEditText = findViewById(R.id.nameEditText);
        this.surnameEditText = findViewById(R.id.surnameEditText);
        this.dateEditText = findViewById(R.id.dateEditText);
    }

    private void addListeners(){
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateField();
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(InputActivity.this,
                        dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    private void updateDateField(){
        dateEditText.setText(Utils.formatDate(calendar));
    }

    private void addPerson(){
        if(fieldsNotEmpty()){
            if(nameFieldsCorrect()){
                try{
                    int diffInYearsFromNow = Utils.getDifferenceInYearsFromNow(calendar.getTime());
                    try{
                        Person person = Person.createPerson(nameEditText.getText().toString(), surnameEditText.getText().toString(), diffInYearsFromNow);
                        MainActivity.listOfPeople.add(person);
                        ToastUtils.showToast(getString(R.string.addedPerson), this);
                        finish();
                    }catch (IllegalArgumentException e){
                        ToastUtils.showToast(getString(R.string.incorrectPersonAge), this);
                    }
                } catch (IllegalArgumentException e){
                    ToastUtils.showToast(getString(R.string.errorDateFromTheFuture), this);
                }
            }
            else {
                ToastUtils.showToast(getString(R.string.errorFieldsContainSpecialCharacters), this);
            }
        } else {
            ToastUtils.showToast(getString(R.string.errorFieldsEmpty), this);
        }
    }

    private boolean fieldsNotEmpty(){
        return Utils.stringNotEmpty(nameEditText.getText().toString())
                && Utils.stringNotEmpty(surnameEditText.getText().toString())
                && Utils.stringNotEmpty(dateEditText.getText().toString());
    }

    private boolean nameFieldsCorrect(){
        return Utils.isAlphabetic(nameEditText.getText().toString()) && Utils.isAlphabetic(surnameEditText.getText().toString());
    }
}
