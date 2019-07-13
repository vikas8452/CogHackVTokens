package titan.trio.coghackvtokens;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import titan.trio.coghackvtokens.Model.UserDetail;

public class AmountAvail extends AppCompatActivity {


    CardView wallet,gift,credit;
    TextView balance;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private UserDetail value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_avail);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wallet=findViewById(R.id.wallet);
        gift=findViewById(R.id.gift);
        credit=findViewById(R.id.credit);
        balance = findViewById(R.id.balance);

        final Button apply =findViewById(R.id.apply);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user").child(currentUser.getUid()).child("detail");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(UserDetail.class);
                balance.setText("₹ " + value.getBalance());
                Log.e("Value is: ", value.getBalance());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("error", "Failed to read value.", error.toException());
                finish();
            }
        });

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
        finish();
        return true;
    }

}
