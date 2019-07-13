package titan.trio.coghackvtokens;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GiftvouchersActivity extends AppCompatActivity {


    Button redeemVoucher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftvouchers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        redeemVoucher=findViewById(R.id.redeem_voucher);

        redeemVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GiftvouchersActivity.this, "No vouchers Available", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
