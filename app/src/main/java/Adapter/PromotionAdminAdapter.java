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

import com.example.robertwais.shoppingcart.PromoActivity;
import com.example.robertwais.shoppingcart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import Model.Item;

public class PromotionAdminAdapter extends RecyclerView.Adapter<PromotionAdminAdapter.ViewHolder> {

    private  Context context;
    private List<Item> itemList;





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public ImageView imageView;

        public ViewHolder(View view){
            super(view);



            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.promotionListSelectedName);
            imageView = (ImageView) view.findViewById(R.id.promotionListSelectImage);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Item item = itemList.get(position);

            PromoActivity activity = (PromoActivity) context;
            activity.promoItemID.setText(item.getKey());
//            context.id = "dfd";
            //Maybe Change Later but nothing for now..
            //Send back info
        }

    }

    public PromotionAdminAdapter(Context context, List<Item> list){
        this.context = context;
        this.itemList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        //Change
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionAdminAdapter.ViewHolder holder, final int position){
        Item item = itemList.get(position);

//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseDatabase.getInstance();
//        database = db.getReference();

        holder.name.setText(item.getName());
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




    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

}

