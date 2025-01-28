package com.oscararbo.retrofitexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CocktailListAdapter extends RecyclerView.Adapter<CocktailListAdapter.CocktailViewHolder> {

    private final List<Cocktail> cocktailList;
    private final Context context;
    private final OnCocktailClickListener listener;

    public interface OnCocktailClickListener {
        void onCocktailClick(Cocktail cocktail);
    }

    public CocktailListAdapter(Context context, List<Cocktail> cocktailList, OnCocktailClickListener listener) {
        this.context = context;
        this.cocktailList = cocktailList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cocktail_list_item, parent, false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {
        Cocktail cocktail = cocktailList.get(position);
        holder.bind(cocktail);
        holder.itemView.setOnClickListener(v -> listener.onCocktailClick(cocktail));
    }

    @Override
    public int getItemCount() {
        return cocktailList.size();
    }

    static class CocktailViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;

        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cocktailImage);
            textView = itemView.findViewById(R.id.cocktailName);
        }

        public void bind(Cocktail cocktail) {
            textView.setText(cocktail.getStrDrink());
            Glide.with(imageView.getContext()).load(cocktail.getStrDrinkThumb()).into(imageView);
        }
    }
}
