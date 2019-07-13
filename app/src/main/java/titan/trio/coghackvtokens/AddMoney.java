package titan.trio.coghackvtokens;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;


public class AddMoney extends AppCompatActivity {

    CardView pay;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pay=findViewById(R.id.addMoney);
        final String[] s = {""};
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AddMoney.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Payment");
                dialog.setCancelable(true);
                // there are a lot of settings, for dialog, check them all out!
                // set up radiobutton
                RadioButton rd1 = (RadioButton) dialog.findViewById(R.id.rd1);
                RadioButton rd2 = (RadioButton) dialog.findViewById(R.id.rd1);
                ok=dialog.findViewById(R.id.ok);
                if(rd1.isChecked())
                    s[0] = (String) rd1.getText();
                else
                    s[0] = (String) rd2.getText();

                // now that the dialog is set up, it's time to show it
                dialog.show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(s[0].equals("Cash"))
                        {


                            Toast.makeText(AddMoney.this,"Go to the counter ! Recharge and Enjoy  Have Fun",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(AddMoney.this,ScanQrCode.class);
                            startActivity(intent);
                        }
                    }
                });

            }


        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}
