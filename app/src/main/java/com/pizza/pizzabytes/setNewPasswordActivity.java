package com.pizza.pizzabytes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
                String pass,repass,PhoneNumber;
                pass = newpass.getText().toString().trim();
                repass = newcpass.getText().toString().trim();
                if (pass.equals(""))
                {
                    newpass.setError("Password is required");
                    newpass.requestFocus();
                }
                else if (pass.length()<8)
                {
                    newpass.setError("Password Should be 8 digits");
                    newpass.requestFocus();
                }
                else if (repass.equals(""))
                {
                    newcpass.setError("Repeat Password is required");
                    newcpass.requestFocus();
                }
                else if (repass.length()<8)
                {
                    newcpass.setError("Repeat Password Should be 8 digits");
                    newcpass.requestFocus();
                }
                else if (!pass.equals(repass))
                {
                    newpass.setError("Password Not Matched");
                    newpass.requestFocus();
                    newcpass.setError("Password Not Matched");
                    newcpass.requestFocus();
                    newpass.clearComposingText();
                    newcpass.clearComposingText();
                }
                else
                {
                    PhoneNumber = getIntent().getStringExtra("phonenumber");
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().
                            getReference("Registered Users");
                    databaseReference.child(PhoneNumber).child("password").setValue(pass).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Intent i = new Intent(getApplicationContext(),SuccessActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(setNewPasswordActivity.this,"Error To Update"
                                ,Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });
    }
}