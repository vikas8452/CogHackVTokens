package titan.trio.coghackvtokens;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AmountAvail extends AppCompatActivity {


    CardView wallet,gift,credit,apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_avail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wallet=findViewById(R.id.wallet);
        gift=findViewById(R.id.gift);
        credit=findViewById(R.id.credit);


        final Button apply =findViewById(R.id.apply);


        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AmountAvail.this,"Thank you for applying for credit ! We will send a confirmation after verifying your bank details !",Toast.LENGTH_LONG).show();
                apply.setVisibility(View.INVISIBLE);
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AmountAvail.this,"Thank you for applying for credit ! We will send a confirmation after verifying your bank details !",Toast.LENGTH_LONG).show();
                //apply.setVisibility(View.INVISIBLE);
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(AmountAvail.this,AddMoney.class);
                startActivity(intent);

            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(AmountAvail.this,GiftvouchersActivity.class);
                startActivity(intent);

            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
