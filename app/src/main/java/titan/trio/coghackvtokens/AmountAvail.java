package titan.trio.coghackvtokens;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class AmountAvail extends AppCompatActivity {


    CardView wallet,gift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_avail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wallet=findViewById(R.id.wallet);
        gift=findViewById(R.id.gift);

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
