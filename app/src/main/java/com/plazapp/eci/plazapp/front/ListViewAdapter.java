package com.plazapp.eci.plazapp.front;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.plazapp.eci.plazapp.R;
import com.plazapp.eci.plazapp.back.ItemList;

import java.util.ArrayList;

/**
 * Created by Jeffer on 10/12/2018.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    private ArrayList<ItemList> content;


    public ListViewAdapter(Context context,ArrayList<ItemList> items){
        this.context = context;
        this.content = items;
    }

    @Override
    public int getCount() {
        return this.content.size();
    }

    @Override
    public Object getItem(int i) {
        return content.get(i);
    }

    @Override
    public long getItemId(int i) {
        return content.get(i).getOfferter().hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_view_list, null);

        TextView tittle = view.findViewById(R.id.Tittle);
        TextView offerter = view.findViewById(R.id.oferter);
        TextView price = view.findViewById(R.id.price);
        TextView quantity = view.findViewById(R.id.quantity);
        TextView term = view.findViewById(R.id.term);
        ImageView icon = view.findViewById(R.id.image);

        ItemList current = content.get(i);
        tittle.setText(current.getTittle());
        offerter.setText(current.getOfferter());
        price.setText(current.getPrice());
        term.setText(current.getTerm());
        quantity.setText(current.getQuantity());
        icon.setImageResource(current.getImage());

        return view;
    }

}
