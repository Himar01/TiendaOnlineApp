package com.example.tiendaonlineapp.products;

import static java.lang.String.valueOf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.data.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<ProductItem> itemList;
    private final View.OnClickListener clickListener;
    //in the Constructor, pass the context in the parametres
    private Context context;

    public ProductListAdapter(View.OnClickListener listener, Context context) {
        this.context = context;
        itemList = new ArrayList();
        clickListener = listener;
    }

    public void addItem(ProductItem item){
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<ProductItem> items){
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<ProductItem> items){
        itemList = items;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
            return itemList.size();
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_content, parent, false);
        return new ProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductListAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);
        holder.contentView.setText(itemList.get(position).product);
        //Adjusting price to clean visibility
        String priceTotal = valueOf(itemList.get(position).price);
        String[] price = priceTotal.split("\\.");
        if(price[1].equals("0")){
            price[1]="00";
        }
        holder.priceView.setText(price[0]+","+price[1]+"???");
        holder.productView.setImageResource(getResourceId(itemList.get(position).picture,
                "drawable",
                context.getPackageName()));
        //Images resources will be stored in the Activity
    }
    private int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView contentView;
        final TextView priceView;
        final ImageView productView;
        ViewHolder(View view) {
            super(view);
            contentView = view.findViewById(R.id.content);
            priceView = view.findViewById(R.id.price);
            productView = view.findViewById(R.id.product);
        }
    }

}
