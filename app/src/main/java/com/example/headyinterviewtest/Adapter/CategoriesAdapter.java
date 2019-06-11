package com.example.headyinterviewtest.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.headyinterviewtest.Activities.ProductsActivity;
import com.example.headyinterviewtest.Model.Category;
import com.example.headyinterviewtest.R;
import java.util.List;
import static com.example.headyinterviewtest.Utility.Constants.INTENT_DATA;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private LayoutInflater inflater;
    private Activity activity;
    public CategoriesAdapter(Activity activity, List<Category> categories) {
        inflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_item_base, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.category = category;
        String name = category.getName();
        if (name != null) {
            ((TextView) holder.itemView).setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Category category;
        private CategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, ProductsActivity.class);
            intent.putExtra(INTENT_DATA, category);
            activity.startActivity(intent);
        }
    }
}