package com.example.headyinterviewtest.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.headyinterviewtest.Adapter.ProductsAdapter;
import com.example.headyinterviewtest.Model.Category;
import com.example.headyinterviewtest.Model.Product;
import com.example.headyinterviewtest.Model.Ranking;
import com.example.headyinterviewtest.R;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.headyinterviewtest.Dagger.Repository.getFullProduct;
import static com.example.headyinterviewtest.Utility.Constants.INTENT_DATA;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.ID;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.ORDERS;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.SHARES;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.VIEWS;


public class ProductsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    String sortBy = ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Serializable serializable = getIntent().getSerializableExtra(INTENT_DATA);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Product> products = null;
        if (serializable instanceof Category) {
            Category category = (Category) serializable;
            Objects.requireNonNull(getSupportActionBar()).setTitle(category.getName());
            products = category.getProducts();
        } else if (serializable instanceof Ranking) {
            Ranking ranking = (Ranking) serializable;
            Objects.requireNonNull(getSupportActionBar()).setTitle(ranking.getRanking());
            products = ranking.getProducts();
            sortBy = ranking.getRanking();
        }
        loadData(products);
    }

    private void loadData(List<Product> products) {
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
                Product fullProduct = getFullProduct(product);
                products.set(products.indexOf(product), fullProduct);
            }
            Collections.sort(products, (o1, o2) -> {
                int val1, val2;
                switch (sortBy) {
                    case VIEWS:
                        val1 = o1.getViewCount();
                        val2 = o2.getViewCount();
                        break;
                    case ORDERS:
                        val1 = o1.getOrderCount();
                        val2 = o2.getOrderCount();
                        break;
                    case SHARES:
                        val1 = o1.getShares();
                        val2 = o2.getShares();
                        break;
                    default:
                        val1 = o1.getId();
                        val2 = o2.getId();
                        break;
                }
                if (val1 < val2) {
                    return 1;
                } else if (val1 > val2) {
                    return -1;
                } else {
                    return 0;
                }
            });
            ProductsAdapter productsAdapter = new ProductsAdapter(this, products);
            recyclerView.setAdapter(productsAdapter);
        } else {
            Toast.makeText(this, getString(R.string.search_error_no_text), Toast.LENGTH_LONG).show();
            finish();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
