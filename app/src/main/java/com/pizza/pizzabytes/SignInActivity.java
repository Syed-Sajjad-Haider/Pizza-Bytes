package com.pizza.pizzabytes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity
{
    ImageView facebook, google, twitter, pizza;
    TextView pizzatittle,tagline,forget,signuptext;
    EditText user,pass;
    Button login;
    ConstraintLayout constraintLayout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();

        user = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginbtn);

        pizzatittle = (TextView) findViewById(R.id.textView6);
        tagline = (TextView) findViewById(R.id.tag);
        forget = (TextView) findViewById(R.id.forget);
        signuptext = (TextView) findViewById(R.id.signup);

        facebook = (ImageView) findViewById(R.id.fab_fb);
        google = (ImageView) findViewById(R.id.fab_google);
        twitter = (ImageView) findViewById(R.id.fab_twiter);
        pizza = (ImageView) findViewById(R.id.loginpizza);
        constraintLayout = (ConstraintLayout) findViewById(R.id.conlayout1);

        pizzatittle.setTranslationX(-1000);
        tagline.setTranslationX(-1000);

        facebook.setTranslationY(300);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        pizza.setTranslationY(-600);
        constraintLayout.setTranslationX(1000);

        facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        pizza.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        constraintLayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        pizzatittle.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tagline.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        signuptext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });

        forget.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //After Activity Done This
                Intent i = new Intent(getApplicationContext(),ForgetActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome to Forget Activity",Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String username,password;
                username = user.getText().toString();
                password = pass.getText().toString();

                if(username.equals(""))
                {
                    user.setError("Email Required");
                    user.requestFocus();
                }
                else if (password.equals(""))
                {
                    pass.setError("Password Required");
                    pass.requestFocus();
                }
                else
                    {

//                        Toast.makeText(getApplicationContext(), "Welcome to Dashboard " + username, Toast.LENGTH_SHORT).show();
                        loginUser(username,password);
//                        Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
//                        startActivity(i);
                    }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
//                startActivity(i);
                Toast.makeText(getApplicationContext(), "Sign In Against Facebook",Toast.LENGTH_SHORT).show();

            }
        });

        google.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
//                startActivity(i);
                Toast.makeText(getApplicationContext(), "Sign In Against Google",Toast.LENGTH_SHORT).show();

            }
        });

        twitter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
//                startActivity(i);
                Toast.makeText(getApplicationContext(), "Sign In Against Twitter",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loginUser(String username, String password)
    {
        auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Welcome to Dashboard " + username, Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
//                        startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}