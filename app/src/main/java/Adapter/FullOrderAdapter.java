package Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robertwais.shoppingcart.BrowseActivity;
import com.example.robertwais.shoppingcart.LogInActivity;
import com.example.robertwais.shoppingcart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Model.Item;
import Model.MyListView;
import Model.Order;

public class FullOrderAdapter extends RecyclerView.Adapter<FullOrderAdapter.ViewHolder> {

    private Context context;
    private List<Order> orderList;
    private FirebaseDatabase db;
    private DatabaseReference database, cartRef;
    private FirebaseAuth mAuth;





    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, total, shippingAddress, billingAddress, discountPrice, taxPrice, shippingPrice;
        public MyListView smallItems;
        public ArrayList mobileArray;
        public ArrayAdapter adapter;
        private List<Item> miniList;

        public ViewHolder(View view){
            super(view);

            //Fix Later
            // Array of strings...
            mobileArray = new ArrayList<String>();
            mobileArray.add("Hello");

            //All the items in that purchase
//            allItems = (RecyclerView)view.findViewById(R.id.fullOrderHistoryRecyclerView);

            //MAYBE ADD LATER - IF ISSUES
//            allItems.setHasFixedSize(true);
//            allItems.setLayoutManager(new LinearLayoutManager(this));

            smallItems = (MyListView)  view.findViewById(R.id.listView);

            total = (TextView) view.findViewById(R.id.fullOrderHistoryTotal);
            shippingAddress = (TextView) view.findViewById(R.id.fullOrderHistoryShipping);
            billingAddress = (TextView) view.findViewById(R.id.fullOrderHistoryBilling);
            discountPrice = (TextView) view.findViewById(R.id.fullOrderHistoryDiscounts);
            taxPrice = (TextView) view.findViewById(R.id.fullOrderHistoryTaxes);
            shippingPrice = (TextView) view.findViewById(R.id.fullOrderHistoryShippingPrice);

            miniList = new ArrayList<>();

            adapter = new ArrayAdapter<>(context,R.layout.item_orderhistory,mobileArray);

            smallItems.setAdapter(adapter);
        }
    }

    public FullOrderAdapter(Context context, List<Order> list){
        this.context = context;
        this.orderList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        //Change
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_orderhistory,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FullOrderAdapter.ViewHolder holder, final int position){
        final Order order = orderList.get(position);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();


        if(mAuth.getCurrentUser()==null){
            cartRef = database.child("Guest").child("OrderHistory");
        }else{
            cartRef = database.child(mAuth.getCurrentUser().getUid()).child("OrderHistory");
        }



        holder.total.setText(order.getTotal().toString());
        holder.shippingAddress.setText(order.getShippingAddress());
        holder.billingAddress.setText(order.getBillingAddress());
        holder.discountPrice.setText("Change later");
        holder.taxPrice.setText("Change later");
        holder.shippingPrice.setText("Change later");

        cartRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order allItems = dataSnapshot.getValue(Order.class);
//                holder.mobileArray.add(newItem.getName().toString());
                List<Item> items = allItems.getAllItems();
                for (Item item: items) {
                    if(item != null){
                        String line = item.getName();
                        if(s!=null){
                            holder.mobileArray.add(line);
                        }
                    }

                }


                holder.adapter.notifyDataSetChanged();
//                holder.miniList.add(newItem);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        return orderList.size();
    }

}

