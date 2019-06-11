package com.example.headyinterviewtest.Model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.example.headyinterviewtest.Dagger.Repository;
import com.example.headyinterviewtest.Network.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> jsonApiResponse() {
        return responseLiveData;
    }

    public void hitFetchJsonApi() {
        disposables.add(repository.executeFetchJson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    public List<Product> getfinalProductList(List<Category> categories, List<Ranking> rankings) {
        List<Product> finalProducts = new ArrayList<>();
        for (Category category : categories) {
            List<Product> productList = category.getProducts();
            finalProducts.addAll(productList);
        }
        for (Ranking ranking : rankings) {
            for (Product product : ranking.getProducts()) {
                if (finalProducts.contains(product)) {
                    Product finalProduct = finalProducts.get(finalProducts.indexOf(product));
                    finalProduct.setOrderCount(product.getOrderCount());
                    finalProduct.setViewCount(product.getViewCount());
                    finalProduct.setShares(product.getShares());
                }
            }
        }
        return finalProducts;
    }


    @Override
    protected void onCleared() {
        disposables.clear();
    }

}
