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

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {

    private final List<Cocktail> cocktailList;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public CocktailAdapter(Context context, List<Cocktail> cocktailList) {
        this.context = context;
        this.cocktailList = cocktailList;
    }

    // Interface para manejar clics en los ítems
    public interface OnItemClickListener {
        void onItemClick(String cocktailId);
    }

    // Configura el listener desde fuera del adaptador
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_cocktail_details, parent, false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {
        Cocktail cocktail = cocktailList.get(position);
        holder.cocktailName.setText(cocktail.getStrDrink());

        // Cargar imagen usando Glide
        Glide.with(context)
                .load(cocktail.getStrDrinkThumb())
                .centerCrop()
                .into(holder.cocktailImage);

        // Configurar clics en el ítem
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null && cocktail.getIdDrink() != null) {
                onItemClickListener.onItemClick(cocktail.getIdDrink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cocktailList.size();
    }

    // ViewHolder para los cócteles
    static class CocktailViewHolder extends RecyclerView.ViewHolder {
        TextView cocktailName;
        ImageView cocktailImage;

        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);
            cocktailName = itemView.findViewById(R.id.cocktailName);
            cocktailImage = itemView.findViewById(R.id.cocktailImage);
        }
    }
}

