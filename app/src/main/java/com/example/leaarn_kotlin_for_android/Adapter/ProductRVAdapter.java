package com.example.leaarn_kotlin_for_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leaarn_kotlin_for_android.Details.DetailsFragment;
import com.example.leaarn_kotlin_for_android.Interface.OnProductItemClicked;
import com.example.leaarn_kotlin_for_android.Models.ProductModel;
import com.example.leaarn_kotlin_for_android.R;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ViewHolder> {

    private List<ProductModel> productModelList;
    private OnProductItemClicked onProductItemClicked;

    public ProductRVAdapter(List<ProductModel> productModelList, OnProductItemClicked onProductItemClicked) {
        this.productModelList = productModelList;
        this.onProductItemClicked = onProductItemClicked;
    }

    @NonNull
    @Override
    public ProductRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_rec_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.ViewHolder holder, int position) {
        ProductModel model = productModelList.get(position);
        holder.name.setText(model.getProductName());
        holder.storeName.setText(model.getStoreName());

        holder.rating.setText(String.valueOf(model.getRating()));

        holder.price.setText(String.format("$ %4.2f", model.getPrice()));

        //holder.productImg.setImageResource(model.getProductImg1());

        Glide.with(holder.itemView.getContext())
                .load(model.getProductImage())
                .into(holder.productImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onProductItemClicked != null) {
                    //onProductItemClicked.onProductClick(model.getProductImg1());
                }
                openFragment(v.getContext(), new DetailsFragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, storeName, rating, price;
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

    private void openFragment(Context context, Fragment fragment) {
        if (context instanceof AppCompatActivity) {
            FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainFragmentContainer, fragment); // Replace R.id.fragment_container with your fragment container id
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
