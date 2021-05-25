package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Methods;
import com.example.quizapp.Persons;
import com.example.quizapp.R;
import com.example.quizapp.SingleTon;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialCardView login_back_arrow_CV;
    TextInputLayout email_TIL, password_TIL;
    TextInputEditText email_TIET, password_TIET;
    Button login_BT;
    TextView signUp_TV;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<Persons> personsArrayList = new ArrayList<>();
    Methods methods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        methods = new Methods(this);
        login_back_arrow_CV = findViewById(R.id.login_back_arrow_CV);
        email_TIL = findViewById(R.id.email_TIL);
        password_TIL = findViewById(R.id.password_TIL);
        email_TIET = findViewById(R.id.email_TIET);
        password_TIET = findViewById(R.id.password_TIET);
        login_BT = findViewById(R.id.login_BT);
        signUp_TV = findViewById(R.id.signUp_TV);

        login_BT.setOnClickListener(this);
        signUp_TV.setOnClickListener(this);
        login_back_arrow_CV.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = this.getSharedPreferences("DataBase", 0);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("PersonData", null);
        Type type = new TypeToken<ArrayList<Persons>>() {}.getType();
        ArrayList<Persons> arrayList = gson.fromJson(json, type);
        SingleTon.getInstance().personsList = arrayList;
        if (SingleTon.getInstance().getPersonLists()==(null)){
            Toast.makeText(this, "First Register account !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, SignUpActivity.class));
        }
        Log.e("ArrayList", methods.getRequestJson(SingleTon.getInstance().getPersonLists()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back_arrow_CV:
                finishAffinity();
                break;

            case R.id.login_BT:
                Log.e("ArrayList", methods.getRequestJson(SingleTon.getInstance().getPersonLists()));
                Log.e("ArrayList", String.valueOf((SingleTon.getInstance().getPersonLists().size())));

                if (SingleTon.getInstance().getPersonLists()==(null)){
                    Toast.makeText(this, "First Register account !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, SignUpActivity.class));
                }else {
                    String email = email_TIET.getText().toString();
                    String password = password_TIET.getText().toString();
                    if (email.isEmpty()) {
                        email_TIL.setError("Enter email !");
                        password_TIL.setError(null);
                    } else if (password.isEmpty()) {
                        email_TIL.setError(null);
                        password_TIL.setError("Enter password !");
                    } else {
                        email_TIL.setError(null);
                        password_TIL.setError(null);

                        for (int i = 0; i < SingleTon.getInstance().getPersonLists().size(); i++) {
                            Log.e("Check","Check1");
                            if (SingleTon.getInstance().getPersonLists().get(i).getUserName().equals(email) && SingleTon.getInstance().getPersonLists().get(i).getPassword().equals(password)) {
                                Log.e("Check","Check2");
                                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this,MenuActivity.class));
                                editor = sharedPreferences.edit();
                                editor.putString("isLoggedIn","true");
                                editor.apply();
                                break;
                            }else {
                                if (SingleTon.getInstance().getPersonLists().size()-1==i){
                                    Toast.makeText(this, "Invalid user !", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }

                }


                break;


            case R.id.signUp_TV:
                startActivity(new Intent(this, SignUpActivity.class));


                break;
        }
    }
}