package com.a235040.listofpeople;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class InputActivity extends Activity {
    EditText nameEditText;
    EditText surnameEditText;
    EditText dateEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initializeFields();
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

    private boolean fieldsNotEmpty(){
        return Utils.stringNotEmpty(nameEditText.getText().toString())
                && Utils.stringNotEmpty(surnameEditText.getText().toString())
                && Utils.stringNotEmpty(dateEditText.getText().toString());
    }

    private void addPerson(){
        if(fieldsNotEmpty()){
            try{
                int diffInYearsFromNow = Utils.getDifferenceInYearsFromNow(dateEditText.getText().toString());
                try{
                    Person person = Person.createPerson(nameEditText.getText().toString(), surnameEditText.getText().toString(), diffInYearsFromNow);
                    MainActivity.listOfPeople.add(person);
                    ToastUtils.showToast(getString(R.string.addedPerson), this);
                    finish();
                }catch (IllegalArgumentException e){
                    ToastUtils.showToast(getString(R.string.incorrectPersonAge), this);
                }
            } catch (DateIncorrectException e){
                handleDateError(e);
            }
        } else {
            ToastUtils.showToast(getString(R.string.errorFieldsEmpty), this);
        }
    }

    private void handleDateError(DateIncorrectException e){
        switch (e.getErrorType()){
            case DATE_FORMAT_ERROR:
                ToastUtils.showToast(getString(R.string.errorDateFormatIncorrect), this);
                break;
            case DATE_INCORRECT_ERROR:
                ToastUtils.showToast(getString(R.string.errorDateIncorrect), this);
                break;
            case FUTURE_DATE_ERROR:
                ToastUtils.showToast(getString(R.string.errorDateFromTheFuture), this);
                break;
        }
    }
}
