package com.example.headyinterviewtest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.headyinterviewtest.Model.Product;
import com.example.headyinterviewtest.Model.Variant;
import com.example.headyinterviewtest.R;
import com.example.headyinterviewtest.Utility.Utilities;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.example.headyinterviewtest.Dagger.Repository.getFullProduct;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private List<Product> products;
    private LayoutInflater inflater;
    public ProductsAdapter(Context context, List<Product> products) {
        inflater = LayoutInflater.from(context);
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getFullProduct(products.get(position));
        holder.product = product;
        holder.txtName.setText(product.getName());
        holder.txtViewCount.setText(product.getViewCount() + " Views");
        holder.txtOrderCount.setText(product.getOrderCount() + " Orders");
        holder.txtShares.setText(product.getShares() + " Shares");
        holder.txtDateAdded.setText(product.getDateAdded());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    class ProductViewHolder extends RecyclerView.ViewHolder  {

        Product product;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_view_count)
        TextView txtViewCount;
        @BindView(R.id.txt_order_count)
        TextView txtOrderCount;
        @BindView(R.id.txt_shares)
        TextView txtShares;
        @BindView(R.id.txt_date_added)
        TextView txtDateAdded;
        private ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}