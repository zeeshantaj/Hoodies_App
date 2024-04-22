package com.example.leaarn_kotlin_for_android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaarn_kotlin_for_android.Models.ProductModel;
import com.example.leaarn_kotlin_for_android.R;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ViewHolder> {

    private List<ProductModel> productModelList;

    public ProductRVAdapter(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ProductRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_rec_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.ViewHolder holder, int position) {
        ProductModel model = productModelList.get(position);
        holder.name.setText(model.getName());
        holder.storeName.setText(model.getStoreName());

        holder.rating.setText(String.valueOf(model.getRating()));
        holder.productImg.setImageResource(model.getProductImg());

        holder.price.setText(String.format("$ %4.2f",model.getProductPrice()));

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,storeName,rating,price;
        private ImageView productImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            storeName = itemView.findViewById(R.id.productStoreName);
            rating = itemView.findViewById(R.id.productRating);
            price = itemView.findViewById(R.id.productPrice);
            productImg = itemView.findViewById(R.id.productImg);
        }
    }
}
