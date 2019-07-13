package titan.trio.coghackvtokens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import titan.trio.coghackvtokens.Model.UserDetail;


public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText txtPassword;
    TextView btnLogin;
    Button btnSignup;
    Spinner country;

    EditText etName;
    EditText etNumber;
    EditText etEmail;


    String countryy, name, number, email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        etNumber = findViewById(R.id.edtPhone);
        etEmail = findViewById(R.id.edtEmail);
        txtPassword = findViewById(R.id.edtPassword);
        etName = findViewById(R.id.edtName);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        country = findViewById(R.id.spinnerCountry);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }


    void goToLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    void openfirstActivity() {

    }

    void signUp() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");
        name = etName.getText().toString().trim();
        number = etNumber.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();
        if (name != null && !name.isEmpty() && !number.isEmpty() && number.length() == 10 && number != null && email != null && !email.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("SignUpSuccess", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                String id = user.getUid();
                                UserDetail usd = new UserDetail(name, number, "male", "0", "0");
                                myRef.child(id).child("detail").setValue(usd);
                                startActivity(new Intent(Register.this, MainActivity.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("failSignUp", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}