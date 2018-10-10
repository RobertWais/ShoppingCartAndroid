package Adapter;


import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import Model.Item;
import com.example.robertwais.shoppingcart.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private  Context context;
    private List<Item> itemList;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name, price,description;
        public ImageView imageView;

        public ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.itemName);
            price = (TextView) view.findViewById(R.id.priceLabel);
            description = (TextView) view.findViewById(R.id.descriptionField);
            imageView = (ImageView) view.findViewById(R.id.imageView);

            //SET VARIABLES
            //title = (TextView) view.findViewById(R.id.title);
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
        holder.price.setText("$  "+String.valueOf(item.getPrice()));
        holder.description.setText(item.getDescription());
        holder.imageView.setImageResource(R.drawable.android1);
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }


}

