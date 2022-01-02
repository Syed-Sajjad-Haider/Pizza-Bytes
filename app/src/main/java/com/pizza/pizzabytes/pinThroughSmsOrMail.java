package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pinThroughSmsOrMail extends AppCompatActivity
{

    Button button;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_through_sms_or_mail);

        button = (Button) findViewById(R.id.verifyotpbutton);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlay);


        constraintLayout.setTranslationY(-3000);
        constraintLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),setNewPasswordActivity.class);
                startActivity(i);
            }
        });


    }
}