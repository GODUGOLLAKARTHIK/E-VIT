package com.example.E_VIT;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {


    Button login;
    Button register;
    EditText username;
    EditText password;
    String emailPattern1 = "^[a-z0-9+_.-]+@vitstudent.ac.in+$";
    String emailPattern2 = "^[a-z0-9+_.-]+@vit.ac.in+$";

    ProgressDialog progressDialog;
    FirebaseAuth loginAuth;
    FirebaseUser mUser;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.loginbtn);
        register = findViewById(R.id.Registerbtn);
        progressDialog = new ProgressDialog(this);

        loginAuth = FirebaseAuth.getInstance();
        mUser = loginAuth.getCurrentUser();



        //On click on register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        // OnClick on Login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyLogin();
            }
        });
    }

    private void verifyLogin() {

        String S_username = username.getText().toString();
        String S_password = password.getText().toString();
        if(!S_username.matches(emailPattern1))
        {
            if(!S_username.matches(emailPattern2))
            {
                username.setError("Enter proper email");
            }
            else if(S_password.isEmpty() || S_password.length()<5)
            {
                password.setError("Enter correct Password");
            }
            else{
                progressDialog.setMessage("Logging in...");
                progressDialog.setTitle("Login");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                loginAuth.signInWithEmailAndPassword(S_username, S_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login SUCCESSFUL", Toast.LENGTH_LONG).show();
                        NextActivityOnSuccess();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Enter Correct Password and Email", Toast.LENGTH_LONG).show();
                    }
                }
            });
            }


        }
        else if(S_password.isEmpty() || S_password.length()<5)
        {
            password.setError("Enter correct Password");
        }
        else{
            progressDialog.setMessage("Logging...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            loginAuth.signInWithEmailAndPassword(S_username, S_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login SUCCESSFUL", Toast.LENGTH_LONG).show();
                        NextActivityOnSuccess();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Enter Correct Password and Email", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void NextActivityOnSuccess() {
        Intent intent = new Intent(this, Upload_DownloadActivity.class);
        startActivity(intent);
    }

}















//    SensorManager sensorManager;
//
//    private float acelval;
//    private float acellast;
//    private float shake;
//
//        acelval = SensorManager.GRAVITY_EARTH;
//                acellast = SensorManager.GRAVITY_EARTH;
//                shake = 0.0f;
//
//
//                sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//              sensorManager.registerListener(accelListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_NORMAL);
//
//

   // working with accelometer sensor
//    SensorEventListener accelListener = new SensorEventListener() {
//
//        public void onAccuracyChanged(Sensor sensor, int acc) {
//        }
//
//        public void onSensorChanged(SensorEvent event) {
//            float x = event.values[0];
//            float y = event.values[1];
//            float z = event.values[2];
//
//            acellast=acelval;
//            acelval = (float)Math.sqrt((double) (x*x + y*y + z*z));
//            float ans=acelval-acellast;
//            shake= ans;
//
//            if(shake >15 && shake < 17)
//            {
//
//                openInstagram();
//            }
//
//        }
//    };
//
//    public void openInstagram()
//    {
//        Intent intent=new Intent();
//        //getPackageManager().getLaunchIntentForPackage("com.whatsapp");
//        intent.setPackage("com.instagram.android");
//        if(intent != null)
//        {
//            startActivity(intent);
//        }
//        else
//        {
//            Toast.makeText(this, "package not found", Toast.LENGTH_SHORT).show();
//        }
//    }