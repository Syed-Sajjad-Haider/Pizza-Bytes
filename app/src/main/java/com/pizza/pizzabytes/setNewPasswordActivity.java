package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class setNewPasswordActivity extends AppCompatActivity
{
    Button button;
    EditText newpass,newcpass;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        button = (Button) findViewById(R.id.newpasswordbtn);

        newpass = (EditText) findViewById(R.id.newpassword);
        newcpass = (EditText) findViewById(R.id.newconfirmpassword);

        linearLayout = (LinearLayout) findViewById(R.id.setLinear);
        linearLayout.setTranslationY(2200);
        linearLayout.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),SuccessActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}