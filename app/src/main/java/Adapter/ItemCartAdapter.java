package Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robertwais.shoppingcart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;
import java.util.List;

import Model.Item;

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {

    private  Context context;
    private List<Item> itemList;
    private FirebaseDatabase db;
    private DatabaseReference database, itemRef, quantityRef;
    private FirebaseAuth mAuth;





    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public TextView name, price,quantity,total;
        public ImageView imageView;
        public Button remove, add, subtract;

        public ViewHolder(View view){
            super(view);

            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.itemCartName);
            price = (TextView) view.findViewById(R.id.priceCartLbl);
            quantity = (TextView) view.findViewById(R.id.quantityCartLbl);
            total = (TextView) view.findViewById(R.id.itemCartTotalLbl);
            imageView = (ImageView) view.findViewById(R.id.itemCartImageView);


            remove = view.findViewById(R.id.removeCartBtn);
            add = view.findViewById(R.id.itemAddBtn);
            subtract = view.findViewById(R.id.itemSubtractButton);


            //SET VARIABLES
            //title = (TextView) view.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {
            //Maybe Change Later but nothing for now..
        }

    }

    public ItemCartAdapter(Context context, List<Item> list){
        this.context = context;
        this.itemList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        //Change
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchaseditem_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemCartAdapter.ViewHolder holder, final int position){
        final Item item = itemList.get(position);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();


        if(mAuth.getCurrentUser()==null){
            itemRef = database.child("Guest").child("Cart");
        }else{
            itemRef = database.child(mAuth.getCurrentUser().getUid()).child("Cart");
        }
        quantityRef = itemRef.child("quantity");


        holder.name.setText(item.getName());
        double price = Math.round(item.getPrice()*100);
        price = price/100;
        holder.price.setText("Price: " +item.getPrice());



        holder.quantity.setText("Quantity: "+String.valueOf(item.getQuantity()));

        //Must Change Later for total for each item
        holder.total.setText(String.valueOf("Total: " +item.getQuantity() * item.getPrice()));
        String key = item.getKey();
        switch (key){
            case "-LQ1SiQFBH0LvrouzOe2":
                holder.imageView.setImageResource(R.drawable.android0);
                break;
            case "-LQ1SiQHGSBFH4yVbD8z":
                holder.imageView.setImageResource(R.drawable.android1);
                break;
            case "-LQ1SiQHGSBFH4yVbD9-":
                holder.imageView.setImageResource(R.drawable.android2);
                break;
            case "-LQ1SiQIuNPPgkqM6V2u":
                holder.imageView.setImageResource(R.drawable.android3);
                break;
            case "-LQ1SiQIuNPPgkqM6V2v":
                holder.imageView.setImageResource(R.drawable.android4);
                break;
            case "-LQ1SiQJ9wmNbtpf_sGe":
                holder.imageView.setImageResource(R.drawable.android5);
                break;
        }


        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemRef.child(item.getKey()).setValue(null);
                notifyDataSetChanged();
            }
        });


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemRef.child(item.getKey()).child("quantity").setValue(item.getQuantity()+1);
                item.setQuantity(item.getQuantity()+1);
                notifyDataSetChanged();
            }
        });

        holder.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemRef.child(item.getKey()).child("quantity").setValue(item.getQuantity()-1);
                if (item.getQuantity() - 1 <= 0) {
                    itemRef.child(item.getKey()).setValue(null);
                }
                item.setQuantity(item.getQuantity()-1);
                notifyDataSetChanged();
            }
        });

        itemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                DatabaseReference newItemRef = dataSnapshot.getRef();
//                Item newItem = dataSnapshot.getValue(Item.class);
//
//                holder.quantity.setText("Quantity: "+String.valueOf(newItem.getQuantity()));
//
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item newItem = dataSnapshot.getValue(Item.class);
                holder.quantity.setText("Quantity: "+String.valueOf(newItem.getQuantity()));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

}

