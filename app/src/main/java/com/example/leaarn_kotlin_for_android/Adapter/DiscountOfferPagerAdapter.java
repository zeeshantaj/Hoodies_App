package com.example.leaarn_kotlin_for_android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaarn_kotlin_for_android.Models.DisOfferModel;
import com.example.leaarn_kotlin_for_android.R;

import java.util.List;

public class DiscountOfferPagerAdapter extends RecyclerView.Adapter<DiscountOfferPagerAdapter.ViewHolder> {
    private List<DisOfferModel> disOfferModelsList;

    public DiscountOfferPagerAdapter(List<DisOfferModel> disOfferModelsList) {
        this.disOfferModelsList = disOfferModelsList;
    }

    @NonNull
    @Override
    public DiscountOfferPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_offer_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountOfferPagerAdapter.ViewHolder holder, int position) {
        DisOfferModel model = disOfferModelsList.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.offerPercentage.setText(model.getOfferPercentage());
    }

    @Override
    public int getItemCount() {
        return disOfferModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView offerPercentage;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.discountOfferOutfitImg);
            offerPercentage = itemView.findViewById(R.id.discountOfferPercentageTxt);
        }
    }
}
