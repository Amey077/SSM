package SVVV.SVIIT.SSM;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "mytag" ;
    private EditText name,email,mobile_no,password,cnf_password;
    private Button submit;
    private TextView goto_login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile_no=findViewById(R.id.mobile_no);
        password = findViewById(R.id.password);
        cnf_password= findViewById(R.id.cnf_password);
        goto_login = findViewById(R.id.goto_login);
        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=name.getText().toString().trim();
                String Email=email.getText().toString().trim();
                String Mobile=mobile_no.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String cnf_Password = cnf_password.getText().toString().trim();
                if(validateAll(Name,Email,Mobile,Password,cnf_Password) == true)
                {
                    final User user = new User(Name,Email,Mobile);
                    mAuth.createUserWithEmailAndPassword(Email,Password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Log.d(TAG, "onComplete: SignUp successfull");
                                        mAuth.getCurrentUser().sendEmailVerification()
                                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Log.d(TAG, "onComplete: Verification code sent");
                                                        Toast.makeText(getApplicationContext(),"Registration successfull, check your email and verify",Toast.LENGTH_LONG).show();
                                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                                        RealTimeDBHandler dbHandler= new RealTimeDBHandler(getApplicationContext());
                                                        dbHandler.onCreateReference(user);
                                                        finish();
                                                    }
                                                });
                                    }
                                    else{
                                        Log.e(TAG, "onComplete: SignUp failed ");
                                        Toast.makeText(getApplicationContext(),"Signup failed",Toast.LENGTH_SHORT).show();
                                    }
                                    try{
                                        Exception r = task.getException();
                                        Log.d(TAG, "onComplete: Task Exception "+r.getMessage());
                                    }catch (Exception e)
                                    {
                                        Log.d(TAG, "onComplete: catch"+e.getMessage());
                                    }

                                }
                            });
                }
            }
        });
    }
    public boolean validateAll(String Name,String Email,String Mobile,String Password,String cnf_Password)
    {
        return true;
    }
}
