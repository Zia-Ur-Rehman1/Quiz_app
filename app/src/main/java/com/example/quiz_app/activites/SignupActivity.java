package com.example.quiz_app.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    String email,pass,confirm_pass;
    EditText user_email,password,confirm_password;
    Button btn_signup;
    FirebaseAuth user;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user= FirebaseAuth.getInstance();
        login=findViewById(R.id.txtSignup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });




        btn_signup=findViewById(R.id.btnSignup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup_user();
            }
        });

    }
    private void signup_user(){
        user_email=findViewById(R.id.etEmailAddress);
        password=findViewById(R.id.etPassword);
        confirm_password=findViewById(R.id.etConfirmPassword);
        email=user_email.getText().toString();
        pass=password.getText().toString();
        confirm_pass=confirm_password.getText().toString();

        if(email.isEmpty() || pass.isEmpty() || confirm_pass.isEmpty())
        {
            Toast.makeText(SignupActivity.this, "Email Password can't be blank",
                    Toast.LENGTH_SHORT).show();
            return;

        }
        if(!pass.equals(confirm_pass))
        {
            Log.d("ZIA", pass);
            Log.d("ZIA", confirm_pass);
            Toast.makeText(SignupActivity.this, "Password do not match",
                    Toast.LENGTH_SHORT).show();
            return;
        }
            user.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignupActivity.this, "Signup Successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                            Log.d("ZIA", "createUserWithEmail:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ZIA", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}