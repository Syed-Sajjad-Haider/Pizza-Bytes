package com.pizza.pizzabytes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


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
                String phone = editText.getText().toString().trim();

                if (phone.equals(""))
                {
                    editText.setError("Phone Number Is Required");
                    editText.requestFocus();
                }
                else if (!(phone.length()==11))
                {
                    editText.setError("Phone Number Should be 11 digits");
                    editText.requestFocus();
                }
                else if (!phone.startsWith("03"))
                {
                    editText.setError("Phone Number Should Start with 03");
                    editText.requestFocus();
                }
                else
                {
                    phone = phone.substring(1);
                    phone = "+92" + phone;

                    Query query = FirebaseDatabase.getInstance().getReference("Registered Users")
                            .orderByChild("newotptext").equalTo(phone);
                    String finalPhone = phone;
                    query.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot)
                        {
                            if (snapshot.exists())
                            {
                                Toast.makeText(getApplicationContext(), "This Phone Number is exist" , Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), verifyOTP.class);
                                i.putExtra("phonenumber", finalPhone);
                                i.putExtra("checkUpdate", "UpdateValue");
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "This Phone Number is Not Registered" , Toast.LENGTH_SHORT).show();
                                editText.setError("This Phone Number is Not Registered");
                                editText.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error)
                        {

                        }
                    });

                }

            }
        });

    }
}