package titan.trio.coghackvtokens.OrderFood;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

import titan.trio.coghackvtokens.LoginActivity;
import titan.trio.coghackvtokens.MainActivity;
import titan.trio.coghackvtokens.Model.UserDetail;
import titan.trio.coghackvtokens.R;
import titan.trio.coghackvtokens.ScanQrCode;

public class OrderFood extends AppCompatActivity {

    private ArrayList<Integer> OfferImages = new ArrayList<>();
    private ArrayList<Integer> itemImages = new ArrayList<>();
    private ArrayList<String> itemName = new ArrayList<>();
    private ArrayList<String> itemPrice = new ArrayList<>();

    private FirebaseAuth mAuth;
    private UserDetail value;
    private FirebaseUser currentUser;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(OrderFood.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        getImages();
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderFood.this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView offterRecyclerView = findViewById(R.id.offter_recyclerView);
        offterRecyclerView.setLayoutManager(layoutManager);
        OfferAdapter adapter = new OfferAdapter(OrderFood.this, OfferImages);
        offterRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManagerForItems = new LinearLayoutManager(OrderFood.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView itemRecyclerView = findViewById(R.id.items_recyclerView);
        itemRecyclerView.setLayoutManager(layoutManagerForItems);
        ItemAdapter adapterforItem = new ItemAdapter(OrderFood.this, itemImages, itemName, itemPrice);
        itemRecyclerView.setAdapter(adapterforItem);

        offterRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(),
                offterRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(OrderFood.this, "Showing Position  (Single Press) : " + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        itemRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                itemRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(OrderFood.this, "Showing Position  (Single Press) : " + position,
                        Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(OrderFood.this);
                dialog.setContentView(R.layout.food_order_confirm);
                TextView dialogConfirm = dialog.findViewById(R.id.confirm);
                TextView dialogcancel = dialog.findViewById(R.id.cancel);
                TextView avb = dialog.findViewById(R.id.availableBalance);
                TextView payb = dialog.findViewById(R.id.payBalance);
                avb.setText("Available Balance : ");
                payb.setText("Amount to be paid : " + itemPrice.get(position));
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
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef = database.getReference("user").child(currentUser.getUid()).child("detail");

                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                value = dataSnapshot.getValue(UserDetail.class);
                                int a = Integer.parseInt(value.getBalance().trim());
                                int b = Integer.parseInt(itemPrice.get(position).trim().substring(3));
                                if(a < b){
                                    Toast.makeText(OrderFood.this, "Your Amount is not sufficient", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                value.setBalance(a - b + "");
                                Toast.makeText(getApplicationContext(),"Please wait while we prepare..!!",Toast.LENGTH_SHORT).show();

                                myRef.setValue(value);
                                Log.e("Value is: ", value.getBalance());

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.e("error", "Failed to read value.", error.toException());
                            }
                        });

                    }
                });
                dialog.show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

    private void addRecyclerTouchListner(RecyclerView recyclerView) {

    }

    private void getImages() {
        OfferImages.add(R.drawable.offerimageone);
        OfferImages.add(R.drawable.offerimagetwo);
        OfferImages.add(R.drawable.offerimageone);
        OfferImages.add(R.drawable.offerimagetwo);
        OfferImages.add(R.drawable.offerimageone);
        OfferImages.add(R.drawable.offerimagetwo);


        itemImages.add(R.drawable.itemone);
        itemImages.add(R.drawable.itemtwo);
        itemImages.add(R.drawable.itemthree);
        itemImages.add(R.drawable.itemfour);
        itemImages.add(R.drawable.itemone);
        itemImages.add(R.drawable.itemtwo);
        itemImages.add(R.drawable.itemthree);
        itemImages.add(R.drawable.itemfour);
        itemImages.add(R.drawable.itemone);
        itemImages.add(R.drawable.itemtwo);
        itemImages.add(R.drawable.itemthree);
        itemImages.add(R.drawable.itemfour);

        itemName.add("Veg Roll");
        itemName.add("Dosa");
        itemName.add("Sahi Paneer");
        itemName.add("Thal");
        itemName.add("Veg Roll");
        itemName.add("Dosa");
        itemName.add("Sahi Paneer");
        itemName.add("Thal");itemName.add("Veg Roll");
        itemName.add("Dosa");
        itemName.add("Sahi Paneer");
        itemName.add("Thal");

        itemPrice.add("Rs 200");
        itemPrice.add("Rs 150");
        itemPrice.add("Rs 250");
        itemPrice.add("Rs 170");
        itemPrice.add("Rs 200");
        itemPrice.add("Rs 150");
        itemPrice.add("Rs 250");
        itemPrice.add("Rs 170");
        itemPrice.add("Rs 200");
        itemPrice.add("Rs 150");
        itemPrice.add("Rs 250");
        itemPrice.add("Rs 170");
    }
    //RECYCLER VIEW ANIMATION ENDS


    //RECYCLER VIEW ONCLICK METHOND STARTS
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private OrderFood.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final OrderFood.ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
