package Adapter;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import Model.Item;

import com.example.robertwais.shoppingcart.ItemActivity;
import com.example.robertwais.shoppingcart.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private  Context context;
    private List<Item> itemList;


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public TextView name, price,description;
        public ImageView imageView;

        public ViewHolder(View view){
            super(view);

            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.itemName);
            price = (TextView) view.findViewById(R.id.priceLabel);
            description = (TextView) view.findViewById(R.id.descriptionField);
            imageView = (ImageView) view.findViewById(R.id.imageView);

            //SET VARIABLES
            //title = (TextView) view.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {
            //This is where the user has tapped
            int position = getAdapterPosition();
            Item item = itemList.get(position);

            Intent intent = new Intent(context, ItemActivity.class);
            intent.putExtra("Name", item.getName());
            intent.putExtra("Description", item.getDescription());
            intent.putExtra("Price",item.getPrice());
            intent.putExtra("Position",position);
            intent.putExtra("key",item.getKey());

            context.startActivity(intent);
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
        Item item = itemList.get(position);
        holder.name.setText(item.getName());

        double price = Math.round(item.getPrice()*100);
        price = price/100;
        holder.price.setText("$ "+price);

        holder.description.setText(item.getDescription());
        switch (position){
            case 0:
                holder.imageView.setImageResource(R.drawable.android0);
                break;
            case 1:
                holder.imageView.setImageResource(R.drawable.android1);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.android2);
                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.android3);
                break;
            case 4:
                holder.imageView.setImageResource(R.drawable.android4);
                break;
            case 5:
                holder.imageView.setImageResource(R.drawable.android5);
                break;
        }
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

}

