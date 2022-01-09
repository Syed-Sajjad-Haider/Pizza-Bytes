package com.pizza.pizzabytes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pizza.pizzabytes.DatabaseClasses.ReadWriteUserDetails;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class verifyOTP extends AppCompatActivity
{

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    TextView numberotp,pizzaname,pizzatag;
    Button button;
    ImageView imageView;
    ConstraintLayout constraintLayout;
    PinView pinFromUser;
    String codeBySystem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        numberotp = (TextView) findViewById(R.id.otpnumbertext);
        pizzaname = (TextView) findViewById(R.id.otppizzaname);
        pizzatag = (TextView) findViewById(R.id.pizzatagg);

        constraintLayout = (ConstraintLayout)findViewById(R.id.optcons);
        pinFromUser = (PinView) findViewById(R.id.pinView);

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
        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        String PhoneNumber = intent.getStringExtra("phonenumber");
        String password = intent.getStringExtra("password");
        String repassword = intent.getStringExtra("repeatpassword");
        String email = intent.getStringExtra("email");
        String country = intent.getStringExtra("country");
        String city = intent.getStringExtra("city");
        String location = intent.getStringExtra("location");
        String gendername = intent.getStringExtra("gender");
        String dob = intent.getStringExtra("dob");

//        insert(fullname,PhoneNumber,password,repassword,email,country,city,location,gendername,dob);

//        Toast.makeText(getApplicationContext(), "Name Is : " + fullname, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Phone Number Is : " + PhoneNumber, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Password Is : " + password, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Repeat Password Is : " + repassword, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Email Is : " + email, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Country Is : " + country, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "City Is : " + city, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Location Is : " + location, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Gender Is : " + gendername, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Date Of Birth Is : " + dob, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Welcome to OTP Verification", Toast.LENGTH_SHORT).show();


        numberotp.setText("Enter the 6 Digit Verification Code we just sent\non your Number: "+PhoneNumber);
        sendVerificationCodeToUser(PhoneNumber);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String code = pinFromUser.getText().toString();
                if (!code.isEmpty())
                {
                    verifyCode(code);
//                    insertDataIntoDb(fullname,PhoneNumber,password,repassword,email,country,city,location,gendername,dob);
                }
                else
                {
                    Toast.makeText(verifyOTP.this,"NOt Found Anything",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendVerificationCodeToUser(String phoneNo)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
        {
            super.onCodeSent(s, forceResendingToken);
            codeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
        {
            String code = phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                pinFromUser.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e)
        {
            Toast.makeText(verifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String code)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(verifyOTP.this, "CODE VERIFY SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            String fullname = intent.getStringExtra("fullname");
                            String PhoneNumber = intent.getStringExtra("phonenumber");
                            String password = intent.getStringExtra("password");
                            String repassword = intent.getStringExtra("repeatpassword");
                            String email = intent.getStringExtra("email");
                            String country = intent.getStringExtra("country");
                            String city = intent.getStringExtra("city");
                            String location = intent.getStringExtra("location");
                            String gendername = intent.getStringExtra("gender");
                            String dob = intent.getStringExtra("dob");
                            String valueget = intent.getStringExtra("checkUpdate");
                            String forgetphone = getIntent().getStringExtra("phonenumber");
                            if (valueget.equals("UpdateValue"))
                            {
                                Toast.makeText(verifyOTP.this, "Coming From Forget Activity", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(verifyOTP.this,setNewPasswordActivity.class);
                                intent1.putExtra("phonenumber",forgetphone);
                                startActivity(intent1);
                            }
                            else
                                {
                                    insertDataIntoDb(fullname, PhoneNumber, password, repassword, email, country, city, location, gendername, dob);
                                }
                        }
                        else
                            {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            {
                                Toast.makeText(verifyOTP.this, "You Entered Wrong CODE", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }



    private void insertDataIntoDb(String fullname, String PhoneNumber, String password, String repassword,
                        String email, String country, String city, String location, String gendername, String dob)
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(verifyOTP.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(verifyOTP.this, "Register Successfully", Toast.LENGTH_SHORT).show();
//                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    firebaseUser.sendEmailVerification();
                    ReadWriteUserDetails readWriteUserDetails = new ReadWriteUserDetails(fullname,PhoneNumber,password,email,country,city,location,gendername,dob);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().
                            getReference("Registered Users");
                    databaseReference.child(PhoneNumber).setValue(readWriteUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(verifyOTP.this, "Data Instered Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),otpVerficatioDone.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(verifyOTP.this, "Errorrrr", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(verifyOTP.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else
                {
                    Toast.makeText(verifyOTP.this, "Something Get Wrong", Toast.LENGTH_SHORT).show();

                }
//                else
//                {
//                    try
//                    {
//                        throw task.getException();
//                    }
//                    catch (FirebaseAuthInvalidCredentialsException e)
//                    {
//                        Toast.makeText(verifyOTP.this, "Your Email is Already in Use/Invalid", Toast.LENGTH_SHORT).show();
//                    }
//                    catch (FirebaseAuthEmailException e)
//                    {
//                        Toast.makeText(verifyOTP.this, "Your Email is Already in Use", Toast.LENGTH_SHORT).show();
//                    }
//                    catch (FirebaseAuthUserCollisionException e)
//                    {
//                        Toast.makeText(verifyOTP.this, "User Is Already registered with this email", Toast.LENGTH_SHORT).show();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }).addOnFailureListener(verifyOTP.this, new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(verifyOTP.this, "Not Register", Toast.LENGTH_SHORT).show();
            }
        });
    }
}