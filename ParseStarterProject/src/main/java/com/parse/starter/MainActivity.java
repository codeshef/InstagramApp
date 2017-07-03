/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean signUpModeActive = true;
    Button signUpButton;

   @Override
    public void onClick(View view){

       if(view.getId()== R.id.changeSignUpModeTextView){

           //Log.i("AppInfo","Change SignUp Mode");
           if(signUpModeActive){

               signUpModeActive = false;
               signUpButton.setText("LogIn");
               changeSignUpModeTextView.setText("Or,Signup");

           } else{

               signUpModeActive = true;
               signUpButton.setText("SignUp");
               changeSignUpModeTextView.setText("Or,LogIn");

           }
       }
    }

    EditText  usernameEditText;
    EditText  passwordEditText;
    TextView  changeSignUpModeTextView;

    public void signUp(View view){

       if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("") ){

           Toast.makeText(this,"Username and Password Required",Toast.LENGTH_SHORT).show();

       }else {

           if (signUpModeActive) {
               ParseUser user = new ParseUser();

               user.setUsername(usernameEditText.getText().toString());
               user.setPassword(passwordEditText.getText().toString());

               user.signUpInBackground(new SignUpCallback() {
                   @Override
                   public void done(ParseException e) {
                       if (e == null) {

                           Log.i("SignUp", "Successful");
                       } else {

                           Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }else{

                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user!=null){

                            Log.i("SignUp","Login Successful");
                        } else{

                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
           }
       }

    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      usernameEditText = (EditText) findViewById(R.id.usernameEditText);
      passwordEditText = (EditText) findViewById(R.id.passwordEditText);
      changeSignUpModeTextView =(TextView) findViewById(R.id.changeSignUpModeTextView);
      signUpButton = (Button) findViewById(R.id.signUpButton);

      changeSignUpModeTextView.setOnClickListener(this);

              ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}
