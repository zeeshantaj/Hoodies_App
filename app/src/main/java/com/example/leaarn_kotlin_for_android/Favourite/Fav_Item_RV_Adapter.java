package com.example.leaarn_kotlin_for_android.Favourite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leaarn_kotlin_for_android.Models.ProductModel;
import com.example.leaarn_kotlin_for_android.R;

import java.util.List;

public class Fav_Item_RV_Adapter extends RecyclerView.Adapter<Fav_Item_RV_Adapter.ViewHolder> {
    private List<ProductModel> modelList;

    public Fav_Item_RV_Adapter(List<ProductModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Fav_Item_RV_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_recycler_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Fav_Item_RV_Adapter.ViewHolder holder, int position) {
        ProductModel model = modelList.get(position);
        holder.productName.setText(model.getProductName());
        holder.price.setText(String.format("$ %4.2f", model.getPrice()));
        holder.size.setText(model.getSize());
        Glide.with(holder.itemView.getContext())
                .load(model.getProductImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName,price,size;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.fav_productPrice);
            productName = itemView.findViewById(R.id.fav_productName);
            imageView = itemView.findViewById(R.id.fav_productImg);
            size = itemView.findViewById(R.id.fav_productSize);
        }
    }
}
