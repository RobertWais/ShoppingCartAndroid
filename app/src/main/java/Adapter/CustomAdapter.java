package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.robertwais.shoppingcart.R;

import java.util.ArrayList;

import Model.Item;

public class CustomAdapter extends ArrayAdapter<Item> {


    public CustomAdapter(Context context, ArrayList<Item> items){
        super(context,0, items);
    }


    @Override
    public View getView(int position, View finalView, ViewGroup parent){
        Item item = getItem(position);

        if(finalView == null){
           finalView = LayoutInflater.from(getContext()).inflate(R.layout.item_orderhistory,parent, false);
        }
        TextView name = (TextView) finalView.findViewById(R.id.nameListViewCell);
        TextView price = (TextView) finalView.findViewById(R.id.priceListViewCell);
        name.setText(item.getName());
        price.setText(item.getPrice().toString());
        return finalView;
    }
}
