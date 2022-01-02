package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity
{
    EditText name, number, password, repeatpass, email, country;
    Spinner  city, location;
    Button continuebtn;
    TextView login,spizzaname,stagline;
    ConstraintLayout sconstraintLayout;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.signupname);
        number = (EditText) findViewById(R.id.signupnumber);
        password = (EditText) findViewById(R.id.signuppassword);
        repeatpass = (EditText) findViewById(R.id.repeatpassword);
        email = (EditText) findViewById(R.id.signupemail);

        country = (EditText) findViewById(R.id.countryspin);
        city = (Spinner) findViewById(R.id.cityspin);
        location = (Spinner) findViewById(R.id.locationspin);

        imageView = (ImageView) findViewById(R.id.signuppizza);

        continuebtn = (Button) findViewById(R.id.mainbtn);

        login = (TextView) findViewById(R.id.login);
        spizzaname = (TextView) findViewById(R.id.signuppizzaname);
        stagline = (TextView) findViewById(R.id.signuppizzatag);

        sconstraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout3);

        spizzaname.setTranslationX(1000);
        stagline.setTranslationX(-1000);
        imageView.setTranslationY(2300);
        sconstraintLayout.setTranslationX(-1500);
        login.setTranslationX(500);

        imageView.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        sconstraintLayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        login.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();

        spizzaname.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        stagline.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        continuebtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                validateFullName();
                Toast.makeText(getApplicationContext(), "Welcome to OTP Verification",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),verifyOTP.class);
                i.putExtra("number","03039761103");
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            }
        });

    }

//    private boolean validateFullName()
//    {
//        String fullname = name.getText().toString().trim();
//        if (fullname.isEmpty())
//        {
//            name.setError("Field Can't Empty");
//            return false;
//        }
//        else
//        {
//            name.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validateEmail()
//    {
//        String emailvalue = email.getText().toString().trim();
//        String checkEmail = "[a-zA-Z0-9._-]"+"@[a-z]"+"\\."+"[a-z]+";
//        if (emailvalue.isEmpty())
//        {
//            email.setError("Field Can't Empty");
//            return false;
//        }
//        else if (!emailvalue.matches(checkEmail))
//        {
//            email.setError("Invalid Email!");
//            return false;
//        }
//        else
//        {
//            email.setError(null);
//            return true;
//        }
//    }

}