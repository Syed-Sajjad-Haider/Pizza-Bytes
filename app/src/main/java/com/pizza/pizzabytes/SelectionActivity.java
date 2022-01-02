package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class SelectionActivity extends AppCompatActivity
{

    TextView sms,email;
    Button smsbtn,emailbtn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout1);

        sms = (TextView) findViewById(R.id.mobilenumberdesc);
        email = (TextView) findViewById(R.id.emaildesc);

        smsbtn = (Button) findViewById(R.id.smsbutton);
        emailbtn = (Button) findViewById(R.id.emailbutton);

        linearLayout.setTranslationX(1000);
        linearLayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        smsbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),pinThroughSmsOrMail.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Your Are Using Sms Method",Toast.LENGTH_SHORT).show();
            }
        });

        emailbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),pinThroughSmsOrMail.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Your Are Using Email Method",Toast.LENGTH_SHORT).show();
            }
        });

    }
}