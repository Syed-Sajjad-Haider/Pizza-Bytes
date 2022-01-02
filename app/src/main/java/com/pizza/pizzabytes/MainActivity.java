package com.pizza.pizzabytes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    ActionBar actionBar;
    ViewPager viewPager;
    LinearLayout linearLayout;
    TextView[] dots;
    int[] layouts;
    Button next;
    Button getst;
//    Button skip;
    TextView skip;
    welcomeMyAdaptor welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        getst.setVisibility(View.INVISIBLE);

        if (!isFirstTimeAppStart())
        {
            setAppStartStatus(false);
            startActivity(new Intent(MainActivity.this,SignInActivity.class));
            finish();
//            Toast.makeText(getApplicationContext(),"Confirmed Too the Next Activity ike dashboard",Toast.LENGTH_LONG);

        }

        setContentView(R.layout.activity_main);

//        actionBar = getSupportActionBar();
//        if (actionBar != null)
//        {
//            actionBar.hide();
//        }
        viewPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.dotsLayout);
        next = (Button) findViewById(R.id.nextbtn);
//        skip = (Button) findViewById(R.id.skipbtn);
        skip = (TextView) findViewById(R.id.skipbtn);
        getst = (Button) findViewById(R.id.getbtn);
        next.setBackgroundColor(Color.TRANSPARENT);
        next.setTextColor(Color.parseColor("#000000"));


//        statusBarTransparent();

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int currentPage = viewPager.getCurrentItem()+1;
                if (currentPage < layouts.length)
                {
                    viewPager.setCurrentItem(currentPage);
                }
                else
                {
                    setAppStartStatus(false);
                    startActivity(new Intent(MainActivity.this,SignInActivity.class));
                    finish();
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setAppStartStatus(false);
                startActivity(new Intent(MainActivity.this,SignInActivity.class));
                finish();
//                Toast.makeText(getApplicationContext(),"Confirmed Too the Next Activity ike dashboard",Toast.LENGTH_LONG);
            }
        });

        layouts = new int[] {R.layout.splashfragment1, R.layout.splashfragment2, R.layout.splashfragment3};
        welcome = new welcomeMyAdaptor(layouts, getApplicationContext());
        viewPager.setAdapter(welcome);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                if (position == layouts.length-1)
                {
//                    getst.setVisibility(View.VISIBLE);
//                    next.setVisibility(View.INVISIBLE);
                    next.setText("GET STARTED");
                    skip.setVisibility(View.GONE);
                }
                else
                {
                    next.setText("NEXT");
                    skip.setVisibility(View.VISIBLE);
                }
                setDots(position);

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        setDots(0);

    }

    private Boolean isFirstTimeAppStart()
    {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("PIZZA BYTES", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("APP_START", true);
    }

    private void setAppStartStatus(Boolean status)
    {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("PIZZA BYTES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("APP_START", status);
        editor.apply();

    }

    private void statusBarTransparent()
    {
        if(Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setDots(int page)
    {
        linearLayout.removeAllViews();
        dots = new TextView[layouts.length];
        for (int i =0; i<dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(70);
            dots[i].setTextColor(Color.parseColor("#a9b4bb"));
            linearLayout.addView(dots[i]);
        }
        if (dots.length > 0)
        {
            dots[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }
}