package com.example.leaarn_kotlin_for_android.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ViewHolder> {

    private List<ProductModel> productModelList;

    public ProductRVAdapter(List<ProductModel> productModelList) {
        this.productModelList = productModelList;

    }

    public void sortList(String cat){
        ProductModel model = new ProductModel();
        if (model.getProductName().equals(cat)){
            productModelList.add(model);
        }

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
                openFragment(v.getContext(), new DetailsFragment(), model.getProductId());
            }
        });
        checkIsFav(model.getProductId(),holder.productCheckBox);
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, storeName, rating, price;
        private ImageView productImg,productCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            productCheckBox = itemView.findViewById(R.id.productCheckBox);
            storeName = itemView.findViewById(R.id.productStoreName);
            rating = itemView.findViewById(R.id.productRating);
            price = itemView.findViewById(R.id.productPrice);
            productImg = itemView.findViewById(R.id.productImg);
        }
    }

    private void openFragment(Context context, Fragment fragment, String productId) {
        if (context instanceof AppCompatActivity) {
            FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putString("productId", productId);
            fragment.setArguments(bundle);

            transaction.replace(R.id.mainFragmentContainer, fragment); // Replace R.id.fragment_container with your fragment container id
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void checkIsFav(String PD,View itemView) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("favourite").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String productId = snapshot.child("productId").getValue(String.class);
                    if (productId.equals(PD)) {
                        itemView.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("MyApp", "Error " + error.getMessage());
            }
        });

    }
}
