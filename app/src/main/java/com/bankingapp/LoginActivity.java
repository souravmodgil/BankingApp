package com.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.bankingapp.database.AppDataBase;
import com.bankingapp.helpers.InputValidation;
import com.bankingapp.sql.Users;
import com.bankingapp.utility.CreateUserGenerator;
import com.bankingapp.utility.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
    private final AppCompatActivity activity = LoginActivity.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;



    private InputValidation inputValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppDataBase database = AppDataBase.getAppDatabase(this);
        initViews();
        initListeners();
        initObjects();
        if(!isFirstime())
        {
            CreateUserGenerator.with(database).generateUser();
            CreateUserGenerator.with(database).generateBill();
        }

    }
    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);



    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
       ;
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                login();
                break;

        }
    }

    private boolean isFirstime()
    {
        SaveSharedPreference sh_Pref = new SaveSharedPreference();
        boolean check = sh_Pref.isFirstTime(this);
        if(!check)
            sh_Pref.setIsFirstTime(this,true);
        return check;
    }

    private void  setUserID(int userID ,String username)
    {
        SaveSharedPreference sh_Pref = new SaveSharedPreference();
         sh_Pref.setUserId(this,userID);
        sh_Pref.setUserName(this,username);

    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void login() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        AppDataBase database = AppDataBase.getAppDatabase(this);
        inputValidation.hideKeyboardFrom(this);

        Users login = CreateUserGenerator.with(database).isLogin(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim());

        if ( login !=null && !login.UserName.isEmpty()) {
            setUserID (login.id,login.UserName);

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else {
            // Snack Bar to show success message that record is wrong
            View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(rootView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();


        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
