package Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<String> itemList;


    public class ViewHolder extends RecyclerView.ViewHolder{

        //PUBLIC TextView title,name, etc

        public ViewHolder(View view){
            super(view);
            //SET VARIABLES
            //title = (TextView) view.findViewById(R.id.title);
        }
    }

    public ItemAdapter(List<String> list){
        this.itemList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        //Change
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String string = itemList.get(position);
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }


}

