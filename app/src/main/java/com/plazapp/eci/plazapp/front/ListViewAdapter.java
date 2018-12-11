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
import com.plazapp.eci.plazapp.back.Offert;

import java.util.ArrayList;

/**
 * Created by Jeffer on 10/12/2018.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Offert> content;

    public void setContent(ArrayList<Offert> newContent){
        content = newContent;
    }

    public ListViewAdapter(Context context,ArrayList<Offert> items){
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

        Offert current = content.get(i);
        tittle.setText(current.getProduct());
        offerter.setText("Por: "+current.getOfferter());
        price.setText("Valor: "+current.getPrice()+" $ (COP)");
        term.setText("Negociable: "+current.getTerm());
        quantity.setText("Cantidad: "+current.getQuantity()+" "+current.getUnitMeasure());

        icon.setImageResource(getImageType(current.getType()));

        return view;
    }

    private int getImageType(String type){
        int ans;
        if (type.equals("Verdura")){
            ans = R.drawable.verdura;
        }else if  (type.equals("Fruta")){
            ans = R.drawable.fruta;
        }else if (type.equals("Carne")){
            ans=R.drawable.carne;
        }else if (type.equals("Cereal")){
            ans = R.drawable.cereal;
        }else if (type.equals("Grano")){
            ans = R.drawable.grano;
        }else if (type.equals("Lácteo")){
            ans = R.drawable.lacteo;
        }else{
            ans = R.drawable.tuberculo;
        }
        return ans;
    }

}
