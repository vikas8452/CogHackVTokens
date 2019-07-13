package titan.trio.coghackvtokens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BlackJack extends AppCompatActivity {

    EditText edtBetAmmount;
    Button btnBet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtBetAmmount = findViewById(R.id.edtBetAmmount);
        btnBet = findViewById(R.id.btnBet);

        btnBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bet();
            }
        });

    }
    private void bet(){
        if(edtBetAmmount.getText().toString().trim().equals("")){
            Toast.makeText(BlackJack.this, "Please enter betting ammount", Toast.LENGTH_SHORT).show();
            return;
        }
        long ln = Long.parseLong(edtBetAmmount.getText().toString().trim());
        processAmmount(ln);
    }
    private void processAmmount(long ammount){
        //Transfer Money to Agent
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
