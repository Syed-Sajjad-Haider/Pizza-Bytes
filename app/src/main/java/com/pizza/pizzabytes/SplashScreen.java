package com.pizza.pizzabytes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    ImageView splashimage, splashlogo;
    TextView splashtext;
    LottieAnimationView lottieAnimationView;
    Animation anim;
//    private Button nextt;
    int position = 0;

    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private ScreenSliderPageAdaptor screenSliderPageAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        splashimage = findViewById(R.id.splashimage);
        splashlogo = findViewById(R.id.splashlogo);
        splashtext = findViewById(R.id.splashtext);
        lottieAnimationView = findViewById(R.id.lottie);

        splashimage.animate().translationY(-2700).setDuration(1000).setStartDelay(4000);
        splashlogo.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
        splashtext.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

//        viewPager = (ViewPager) findViewById(R.id.pager);
//        screenSliderPageAdaptor = new ScreenSliderPageAdaptor(getSupportFragmentManager());
//        viewPager.setAdapter(screenSliderPageAdaptor);

//        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.o_b_anim);
//        viewPager.startAnimation(anim);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 5*1000); // wait for 5 seconds
    }

    private class ScreenSliderPageAdaptor extends FragmentStatePagerAdapter {



        public ScreenSliderPageAdaptor(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position)
        {

            switch (position) {
                case 0:
                    splashfragment1 tab1 = new splashfragment1();

                    return tab1;

                case 1:
                    splashfragment2 tab2 = new splashfragment2();
                    return tab2;
                case 2:
                    splashfragment3 tab3 = new splashfragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }

    }
}