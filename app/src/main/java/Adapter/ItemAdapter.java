package Adapter;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import Model.Item;

import com.example.robertwais.shoppingcart.ItemActivity;
import com.example.robertwais.shoppingcart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private  Context context;
    private List<Item> itemList;
    private FirebaseDatabase db;
    private DatabaseReference database;
    private FirebaseAuth mAuth;


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public TextView name, price,description;
        public ImageView imageView;
        public Button removeBtn;



        public ViewHolder(View view){
            super(view);

            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.itemName);
            price = (TextView) view.findViewById(R.id.priceLabel);
            description = (TextView) view.findViewById(R.id.descriptionField);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            removeBtn = view.findViewById(R.id.deleteBrowseItemBtn);

            //If admin remove cart as well as disable settings
            mAuth = FirebaseAuth.getInstance();
            db = FirebaseDatabase.getInstance();
            database = db.getReference().child("Admin");
            //
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (mAuth.getCurrentUser() != null) {
                        if (!mAuth.getCurrentUser().getUid().equals(dataSnapshot.getValue())) {
                            removeBtn.setVisibility(View.GONE);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            //SET VARIABLES
            //title = (TextView) view.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {


            //If admin remove cart as well as disable settings
            mAuth = FirebaseAuth.getInstance();
            db = FirebaseDatabase.getInstance();
            database = db.getReference().child("Admin");
            //
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!mAuth.getCurrentUser().getUid().equals(dataSnapshot.getValue())){
                        //This is where the user has tapped
                        int position = getAdapterPosition();
                        Item item = itemList.get(position);

                        Intent intent = new Intent(context, ItemActivity.class);
                        intent.putExtra("Name", item.getName());
                        intent.putExtra("Description", item.getDescription());
                        intent.putExtra("Price",item.getPrice());
                        intent.putExtra("Position",position);
                        intent.putExtra("key",item.getKey());
                        intent.putExtra("#",item.getQuantity());


                        Log.d("ThisIsHere", "");
                        context.startActivity(intent);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    public ItemAdapter(Context context, List<Item> list){
        this.context = context;
        this.itemList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        //Change
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position){
        final Item item = itemList.get(position);
        holder.name.setText(item.getName());

        double price = Math.round(item.getPrice()*100);
        price = price/100;
        holder.price.setText("$ "+price);

        holder.description.setText(item.getDescription());
        String key = item.getKey();
        switch (key){
            case "-LTQeVHIhk6iGDbKT_PT":
                holder.imageView.setImageResource(R.drawable.android0);
                break;
            case "-LTQeVHNjO-kl17TuK7D":
                holder.imageView.setImageResource(R.drawable.android1);
                break;
            case "-LTQeVHS6Psnmv87M5Bt":
                holder.imageView.setImageResource(R.drawable.android2);
                break;
            case "-LTQeVHUegrdz79jtdR9":
                holder.imageView.setImageResource(R.drawable.android3);
                break;
            case "-LTQeVHaXSrwccgFwCoF":
                holder.imageView.setImageResource(R.drawable.android4);
                break;
            case "-LTQeVHkfSqwroRHevmZ":
                holder.imageView.setImageResource(R.drawable.android5);
                break;
        }


        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //**Remove from screen remove from DB**

                db = FirebaseDatabase.getInstance();
                database = db.getReference();

                database.child("Items").child(item.getKey()).setValue(null);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }
}

