package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Methods;
import com.example.quizapp.Persons;
import com.example.quizapp.R;
import com.example.quizapp.SingleTon;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialCardView  back_arrow_CV;
    EditText first_name_ET, last_name_ET, userName_ET,email_ET,phone_ET,password_ET,confirm_password_ET;
    TextView calenderTextView_dialog;
    Button signUp_BT;
    DatePickerDialog picker;
    Methods methods;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        methods = new Methods(this);
       sharedPreferences = getSharedPreferences("DataBase",MODE_PRIVATE);

        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        first_name_ET = findViewById(R.id.first_name_ET);
        last_name_ET = findViewById(R.id.last_name_ET);
        userName_ET = findViewById(R.id.userName_ET);
        email_ET = findViewById(R.id.email_ET);
        phone_ET = findViewById(R.id.phone_ET);
        password_ET = findViewById(R.id.password_ET);
        confirm_password_ET = findViewById(R.id.confirm_password_ET);
        calenderTextView_dialog = findViewById(R.id.calenderTextView_dialog);
        signUp_BT = findViewById(R.id.signUp_BT);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);

        back_arrow_CV.setOnClickListener(this);
        calenderTextView_dialog.setOnClickListener(this);
        signUp_BT.setOnClickListener(this);



    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_arrow_CV:
                onBackPressed();
                break;

            case R.id.calenderTextView_dialog:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calenderTextView_dialog.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();

                break;

            case R.id.signUp_BT:
                String firstName = first_name_ET.getText().toString().trim();
                String lastName = last_name_ET.getText().toString().trim();
                String userName = userName_ET.getText().toString().trim();
                String email = email_ET.getText().toString().trim();
                String phoneNumber = phone_ET.getText().toString().trim();
                String dateOfBirth = calenderTextView_dialog.getText().toString().trim();
                String password = password_ET.getText().toString().trim();
                String confirmPassword = confirm_password_ET.getText().toString().trim();

                if (firstName.isEmpty()){
                    first_name_ET.setError("Enter first name !");
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);

                }else if (lastName.isEmpty()){
                    first_name_ET.setError(null);
                    last_name_ET.setError("Enter last name !");
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);
                }else if (userName.isEmpty()){
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError("Enter username !");
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);
                }else if (email.isEmpty()){
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError("Enter email !");
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);
                } else if (!email.matches(emailPattern)) {
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError("Invalid email !");
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);
                }else if (phoneNumber.isEmpty()){
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError("Enter phone number !");
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);
                }else if (dateOfBirth.isEmpty()){
                    Toast.makeText(this, "Enter date of birth !", Toast.LENGTH_SHORT).show();
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);
                }else if (password.isEmpty()){
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError("Enter password !");
                    confirm_password_ET.setError(null);
                }else if (confirmPassword.isEmpty()){
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError("Enter confirm password !");
                } else if (!password_ET.getText().toString().equals(confirm_password_ET.getText().toString())) {
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError("Invalid password !");
                    confirm_password_ET.setError("Invalid password !");
                }else {
                    first_name_ET.setError(null);
                    last_name_ET.setError(null);
                    userName_ET.setError(null);
                    email_ET.setError(null);
                    phone_ET.setError(null);
                    password_ET.setError(null);
                    confirm_password_ET.setError(null);
                    if (SingleTon.getInstance().getPersonLists()==null){
                        Persons persons = new Persons();
                        persons.setFirstName(firstName);
                        persons.setLastName(lastName);
                        persons.setUserName(userName);
                        persons.setEmail(email);
                        persons.setPhoneNumber(phoneNumber);
                        persons.setPassword(password);
                        persons.setDateOfBirth(dateOfBirth);
                        ArrayList<Persons> personsArrayList = new ArrayList<>();
                        personsArrayList.add(persons);
                        SharedPreferences prefs = getSharedPreferences("DataBase",0);
                        SharedPreferences.Editor editor = prefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(personsArrayList);
                        editor.putString("PersonData", json);
                        Log.e("List",json);
                        editor.commit();
                        onBackPressed();
                    }else {
                        boolean checkUserName = false;
                        for (int i = 0; i < SingleTon.getInstance().getPersonLists().size(); i++) {
                            Log.e("Check", "Check1");
                            if (SingleTon.getInstance().getPersonLists().get(i).getUserName().contains(userName)) {
                                first_name_ET.setError(null);
                                Log.e("Check", "Check2");
                                last_name_ET.setError(null);
                                userName_ET.setError("Username already exist !");
                                email_ET.setError(null);
                                phone_ET.setError(null);
                                password_ET.setError(null);
                                checkUserName = false;
                            } else {
                                checkUserName = true;
                                first_name_ET.setError(null);
                                last_name_ET.setError(null);
                                userName_ET.setError(null);
                                email_ET.setError(null);
                                phone_ET.setError(null);
                                password_ET.setError(null);
                            }
                        }

                        if (checkUserName){
                            Log.e("Check", "Check5");
                            Persons persons = new Persons();
                            persons.setFirstName(firstName);
                            persons.setLastName(lastName);
                            persons.setUserName(userName);
                            persons.setEmail(email);
                            persons.setPhoneNumber(phoneNumber);
                            persons.setPassword(password);
                            persons.setDateOfBirth(dateOfBirth);
                            SingleTon.getInstance().getPersonLists().add(persons);
                            SharedPreferences prefs = getSharedPreferences("DataBase",0);
                            SharedPreferences.Editor editor = prefs.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson( SingleTon.getInstance().getPersonLists());
                            editor.putString("PersonData", json);
                            Log.e("List",json);
                            editor.commit();
                            onBackPressed();

                        }

                    }

                }

                break;
        }

    }
}