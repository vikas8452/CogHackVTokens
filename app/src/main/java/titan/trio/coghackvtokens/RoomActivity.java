package titan.trio.coghackvtokens;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import titan.trio.coghackvtokens.Model.UserDetail;
import titan.trio.coghackvtokens.OrderFood.OrderFood;

public class RoomActivity extends AppCompatActivity {

    Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(RoomActivity.this);
                dialog.setContentView(R.layout.food_order_confirm);
                TextView dialogConfirm = dialog.findViewById(R.id.confirm);
                TextView dialogcancel = dialog.findViewById(R.id.cancel);
                TextView avb = dialog.findViewById(R.id.availableBalance);
                avb.setText("Confirm Booking");
                TextView payb = dialog.findViewById(R.id.payBalance);
                payb.setText("");
                // if button is clicked, close the custom dialog
                dialogcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Dismissed..!!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialogConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Booking Successful",Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.show();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
