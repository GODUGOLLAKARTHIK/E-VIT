package com.example.E_VIT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.E_VIT.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    Button login;
    Button register;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    String emailPattern1 = "^[a-z0-9+_.-]+@vitstudent.ac.in+$";
    String emailPattern2 = "^[a-z0-9+_.-]+@vit.ac.in+$";


    ProgressDialog progressDialog;
    FirebaseAuth loginAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.Login_Return);
        register = findViewById(R.id.Registerbtn);

        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        email  = findViewById(R.id.RegisterEmail);
        password = findViewById(R.id.RegisterPassword);

        progressDialog = new ProgressDialog(this);
        loginAuth = FirebaseAuth.getInstance();
        mUser = loginAuth.getCurrentUser();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoAuth();
            }
        });

    }

    private void DoAuth() {

        String S_email = email.getText().toString();
        String S_password = password.getText().toString();

        if(!S_email.matches(emailPattern1))
        {
            if(!S_email.matches(emailPattern2))
            {
                email.setError("Enter proper email");
            }
            else if(S_password.isEmpty() || S_password.length()<5)
            {
                password.setError("Enter correct Password");
            }
            else{
                progressDialog.setMessage("Registering...");
                progressDialog.setTitle("Registration");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                loginAuth.createUserWithEmailAndPassword(S_email,S_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            NextActivityOnSuccess();
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Registration SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressDialog.dismiss();;
                            Toast.makeText(RegisterActivity.this, "Error in Registration", Toast.LENGTH_SHORT).show();
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
            progressDialog.setMessage("Registering...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            loginAuth.createUserWithEmailAndPassword(S_email,S_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        NextActivityOnSuccess();
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();;
                        Toast.makeText(RegisterActivity.this, "Error in Registration", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void NextActivityOnSuccess() {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}