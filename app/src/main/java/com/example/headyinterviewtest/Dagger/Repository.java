package com.example.headyinterviewtest.Dagger;

import com.example.headyinterviewtest.Model.JsonWrapper;
import com.example.headyinterviewtest.Model.Product;
import com.example.headyinterviewtest.Network.IApiInterface;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class Repository {

    public static List<Product> PRODUCTS = new ArrayList<>();

    private IApiInterface apiInterface;

    public Repository(IApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }
    public Observable<JsonWrapper> executeFetchJson() {
        return apiInterface.fetchJsonData();
    }

    public static Product getFullProduct(Product product) {
        for (Product p : PRODUCTS) {
            if (p.getId() == product.getId()) {
                return p;
            }
        }
        return product;
    }
}
