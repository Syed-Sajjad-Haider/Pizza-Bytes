package com.pizza.pizzabytes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity
{
    EditText name, number, password, repeatpass, email, country;
    Spinner  city, location;
    DatePicker datePicker;
    Button continuebtn;
    TextView login,spizzaname,stagline,citytext,locationtext,nametxt,phonetxt,passtxt,repasstxt,emailtxt,radiotxt,agetxt;
    ConstraintLayout sconstraintLayout;
    ImageView imageView;
    String selectCity,selectLocation;
    private ArrayAdapter<CharSequence> cityAdaptor, locationAdaptor;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nametxt = (TextView) findViewById(R.id.nametext);
        phonetxt = (TextView) findViewById(R.id.phonetext);
        passtxt = (TextView) findViewById(R.id.passtext);
        repasstxt = (TextView) findViewById(R.id.repasstext);
        emailtxt = (TextView) findViewById(R.id.emailtext);
        radiotxt = (TextView) findViewById(R.id.radiotext);
        agetxt = (TextView) findViewById(R.id.agetext);

        name = (EditText) findViewById(R.id.signupname);
        number = (EditText) findViewById(R.id.signupnumber);
        password = (EditText) findViewById(R.id.signuppassword);
        repeatpass = (EditText) findViewById(R.id.repeatpassword);
        email = (EditText) findViewById(R.id.signupemail);

        country = (EditText) findViewById(R.id.countryspin);

        city = (Spinner) findViewById(R.id.cityspin);
        location = (Spinner) findViewById(R.id.locationspin);

        radioGroup = (RadioGroup) findViewById(R.id.gendergroup);
//        male = (RadioButton) findViewById(R.id.maleradio);
//        female = (RadioButton) findViewById(R.id.femaleradio);
//        order = (RadioButton) findViewById(R.id.otherradio);

        datePicker = (DatePicker) findViewById(R.id.datepikker);

        citytext = (TextView) findViewById(R.id.city);
        locationtext = (TextView) findViewById(R.id.location);



        cityAdaptor = ArrayAdapter.createFromResource(this, R.array.array_pakistan_city,
                R.layout.spinner_layout);
        cityAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdaptor);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCity = city.getSelectedItem().toString();
                int parentID = parent.getId();
                if (parentID == R.id.cityspin)
                {
                    switch (selectCity)
                    {
                        case "Select Your City":
                            locationAdaptor = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_city_location, R.layout.spinner_layout);
                            break;
                        case "Lahore":
                            locationAdaptor = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_city_Lahore_location, R.layout.spinner_layout);
                            break;
                        case "Karachi":
                            locationAdaptor = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_city_Karachi_location, R.layout.spinner_layout);
                            break;
                        case "Islamabad":
                            locationAdaptor = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_city_Islamabad_location, R.layout.spinner_layout);
                            break;
                        case "Sailkot":
                            locationAdaptor = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_city_Sailkot_location, R.layout.spinner_layout);
                            break;

                        default:break;
                    }
                    locationAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(locationAdaptor);

                    location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                        {
                            selectLocation = location.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        imageView = (ImageView) findViewById(R.id.signuppizza);

        continuebtn = (Button) findViewById(R.id.mainbtn);

        login = (TextView) findViewById(R.id.login);
        spizzaname = (TextView) findViewById(R.id.signuppizzaname);
        stagline = (TextView) findViewById(R.id.signuppizzatag);

        sconstraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout3);

        spizzaname.setTranslationX(1000);
        stagline.setTranslationX(-1000);
        imageView.setTranslationY(2300);
        sconstraintLayout.setTranslationX(-1500);
        login.setTranslationX(500);

        imageView.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        sconstraintLayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        login.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();

        spizzaname.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        stagline.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        continuebtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selectedGenderId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedGenderId);

                String fname = name.getText().toString().trim();
                String phonenumber = number.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = repeatpass.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String coun = country.getText().toString().trim();
                String cit = selectCity;
                String locat = selectLocation;
                String gendername = radioButton.getText().toString();


                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int userAge = datePicker.getYear();
                int isAgeValid = currentYear-userAge;

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth()+1;
                int year = datePicker.getYear();

                String date = day+"/"+month+"/"+year;

//                nametxt.setError(null);
//                phonetxt.setError(null);
//                passtxt.setError(null);
//                repasstxt.setError(null);
//                emailtxt.setError(null);
//                citytext.setError(null);
//                locationtext.setError(null);



//                phonenumber = phonenumber.substring(1);
//                phonenumber = "+92" + phonenumber;

                if (fname.equals(""))
                {
                    nametxt.setError("Full Name Is Required");
                    nametxt.requestFocus();
                }
                else if (fname.length()<8 || fname.length()>25)
                {
                    nametxt.setError("Full Name Should Between 8-25 Letters");
                    nametxt.requestFocus();
                }
                else if (phonenumber.equals(""))
                {
                    phonetxt.setError("Phone Number Required");
                    phonetxt.requestFocus();
                }
                else if (phonenumber.length()<11 || phonenumber.length()>11)
                {
                    phonetxt.setError("Phone Number Should be 11 digits");
                    phonetxt.requestFocus();
                }
                else if (!phonenumber.startsWith("03"))
                {
                    phonetxt.setError("Phone Number Should Start with 03");
                    phonetxt.requestFocus();
                }
                else if (pass.equals(""))
                {
                    passtxt.setError("Password is required");
                    passtxt.requestFocus();
                }
                else if (pass.length()<8)
                {
                    passtxt.setError("Password Should be 8 digits");
                    passtxt.requestFocus();
                }
                else if (repass.equals(""))
                {
                    repasstxt.setError("Repeat Password is required");
                    repasstxt.requestFocus();
                }
                else if (repass.length()<8)
                {
                    repasstxt.setError("Repeat Password Should be 8 digits");
                    repasstxt.requestFocus();
                }
                else if (!pass.equals(repass))
                {
                    passtxt.setError("Password Not Matched");
                    passtxt.requestFocus();
                    repasstxt.setError("Password Not Matched");
                    repasstxt.requestFocus();
                    passtxt.clearComposingText();
                    repasstxt.clearComposingText();
                }
                else if (mail.equals(""))
                {
                    emailtxt.setError("Email Is Required");
                    emailtxt.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                {
                    emailtxt.setError("Valid Email Is Required (abc@mail.com)");
                    emailtxt.requestFocus();
                }
                else if (selectCity.equals("Select Your City"))
                {
                    citytext.setError("City Is Required");
                    citytext.requestFocus();
                }
                else if (selectLocation.equals("Select Your Location"))
                {
                    locationtext.setError("Location Is Required");
                    locationtext.requestFocus();
                }
                else if (radioGroup.getCheckedRadioButtonId() == -1)
                {
                    radiotxt.setError("Gender Is Required");
                    radiotxt.requestFocus();
                }
                else if (isAgeValid < 14)
                {
                    agetxt.setError("Your Are Not Eligible. You Must be 14 Year Old");
                    agetxt.requestFocus();
                }
                else
                    {
                        nametxt.setError(null);
                        phonetxt.setError(null);
                        passtxt.setError(null);
                        repasstxt.setError(null);
                        emailtxt.setError(null);
                        citytext.setError(null);
                        locationtext.setError(null);
                        phonenumber = phonenumber.substring(1);
                        phonenumber = "+92" + phonenumber;
//                        Toast.makeText(getApplicationContext(), "Name Is : " + fname, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Phone Number Is : " + phonenumber, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Password Is : " + pass, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Repeat Password Is : " + repass, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Email Is : " + mail, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Country Is : " + coun, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "City Is : " + cit, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Location Is : " + locat, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Gender Is : " + gendername, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Date Of Birth Is : " + date, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "Welcome to OTP Verification", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), verifyOTP.class);
                        i.putExtra("fullname", fname);
                        i.putExtra("phonenumber", phonenumber);
                        i.putExtra("password", pass);
                        i.putExtra("repeatpassword", repass);
                        i.putExtra("email", mail);
                        i.putExtra("country", coun);
                        i.putExtra("city", cit);
                        i.putExtra("location", locat);
                        i.putExtra("gender", gendername);
                        i.putExtra("dob", date);
                        startActivity(i);
                    }
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            }
        });

    }

}