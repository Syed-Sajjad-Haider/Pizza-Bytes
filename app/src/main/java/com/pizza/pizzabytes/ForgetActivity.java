package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class ForgetActivity extends AppCompatActivity
{

    EditText editText;
    Button button;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        editText = (EditText) findViewById(R.id.forgetemail);
        button = (Button) findViewById(R.id.forgetnext);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout2);

        linearLayout.setTranslationX(-1000);
        linearLayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), SelectionActivity.class);
                startActivity(i);
            }
        });

    }
}