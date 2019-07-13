package titan.trio.coghackvtokens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Register extends AppCompatActivity {

    TextView txtPhone;
    TextView txtEmail;
    TextView txtPassword;
    TextView txtName;
    TextView txtGender;
    TextView btnLogin;
    Button btnSignup;
    Spinner country;

    TextView etNativeLanguage;
    EditText etName;
    EditText etAge;
    EditText etSex;
    EditText etNumber;
    EditText etEmail;
    TextView etInvalid;
    Button signUp;


    String nativeLanguage, name, age, sex, number, email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtName = findViewById(R.id.txtName);
        txtGender = findViewById(R.id.txtGender);
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

        if (name != null && !name.isEmpty() && !number.isEmpty() && number.length() == 10 && age != null && !age.isEmpty() && sex != null && !sex.isEmpty() && number != null && nativeLanguage != null && !nativeLanguage.isEmpty() && email != null && !email.isEmpty()) {

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