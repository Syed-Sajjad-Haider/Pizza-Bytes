package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class otpVerficatioDone extends AppCompatActivity
{
    TextView pizza,pizzatag;
    Button verify;
    ImageView imageView;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verficatio_done);

        constraintLayout = (ConstraintLayout)findViewById(R.id.congocon);

        pizza = (TextView) findViewById(R.id.verifypizzaname);
        pizzatag = (TextView) findViewById(R.id.verifypizzatagg);

        verify = (Button) findViewById(R.id.dashboardbtn);

        imageView = (ImageView) findViewById(R.id.verifypizza);

        pizza.setTranslationX(1000);
        pizzatag.setTranslationX(1000);
        imageView.setTranslationX(-1300);
        constraintLayout.setTranslationY(2000);
        imageView.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        constraintLayout.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(100).start();
        pizza.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        pizzatag.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();



        verify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Welcome to Dashboard, Boss",Toast.LENGTH_SHORT).show();
            }
        });
    }
}