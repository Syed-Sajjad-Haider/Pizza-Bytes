package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class verifyOTP extends AppCompatActivity
{

    TextView numberotp,pizzaname,pizzatag;
    Button button;
    ImageView imageView;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        numberotp = (TextView) findViewById(R.id.otpnumbertext);
        pizzaname = (TextView) findViewById(R.id.otppizzaname);
        pizzatag = (TextView) findViewById(R.id.pizzatagg);

        constraintLayout = (ConstraintLayout)findViewById(R.id.optcons);

        Intent intent = getIntent();
        String newotptext = intent.getStringExtra("number");
        numberotp.setText("Enter the 4 Digit Verification Code we just sent\non your Number: "+newotptext);

        imageView = (ImageView) findViewById(R.id.otppizza);

        button= (Button) findViewById(R.id.verifybutton);

        pizzaname.setTranslationX(1000);
        pizzatag.setTranslationX(1000);
        imageView.setTranslationX(-1300);
        constraintLayout.setTranslationY(-2000);
        imageView.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        constraintLayout.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(100).start();
        pizzaname.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        pizzatag.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),otpVerficatioDone.class);
                startActivity(i);
            }
        });

    }
}