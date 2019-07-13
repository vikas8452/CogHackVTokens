package titan.trio.coghackvtokens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    TextView txtEmailOrPhone;
    EditText edtEmailOrPhone;
    TextView forgotPassword;
    TextView register;
    Button btnLogin;
    EditText etEmail;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmailOrPhone = findViewById(R.id.txtEmailOrPhone);
        edtEmailOrPhone = findViewById(R.id.edtEmailOrPhone);
        forgotPassword = findViewById(R.id.forgotPassword);
        register = findViewById(R.id.register);
        btnLogin = findViewById(R.id.btnLogin);
        edtPassword = findViewById(R.id.edtPassword);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpActivity();
            }
        });
    }

    void goToSignUpActivity() {
        startActivity(new Intent(this, Register.class));
        finish();
    }

    void login() {
        String email = etEmail.getText().toString();
        String pass = edtPassword.getText().toString();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
