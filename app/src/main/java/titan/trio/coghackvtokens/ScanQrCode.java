package titan.trio.coghackvtokens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import titan.trio.coghackvtokens.Model.UserDetail;

public class ScanQrCode extends AppCompatActivity {
    private IntentIntegrator qrScan;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private UserDetail value;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(ScanQrCode.this, LoginActivity.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user").child(currentUser.getUid()).child("detail");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(UserDetail.class);
                if(value.getStatus().equals("0")){
                    Toast.makeText(ScanQrCode.this, "Please complete your KYC", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ScanQrCode.this, AmountAvail.class));
                    finish();
                }
                Log.e("Value is: ", value.getBalance());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("error", "Failed to read value.", error.toException());
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    String id=(obj.getString("id"));
                    String amount=(obj.getString("amount"));


                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("user").child(currentUser.getUid()).child("detail");

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            value = dataSnapshot.getValue(UserDetail.class);
                            Log.e("Value is: ", value.getBalance());
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.e("error", "Failed to read value.", error.toException());
                        }
                    });

                    if(id.startsWith("1")) {
                        value.setBalance(Integer.parseInt(value.getBalance().trim()) + Integer.parseInt(amount.trim()) + "");
                    }else if(id.startsWith("2")){
                        int a = Integer.parseInt(value.getBalance().trim());
                        int b = Integer.parseInt(amount.trim());
                        if(b > a){
                            Toast.makeText(ScanQrCode.this, "Your Amount is not sufficient", Toast.LENGTH_LONG).show();
                            return;
                        }
                        value.setBalance(b - a + "");
                    }
                    myRef.setValue(value);

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
