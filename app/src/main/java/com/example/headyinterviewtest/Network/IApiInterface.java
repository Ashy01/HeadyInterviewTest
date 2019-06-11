package com.example.headyinterviewtest.Network;

import com.example.headyinterviewtest.Model.JsonWrapper;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IApiInterface {

    @GET(Urls.FETCH_JSON)
    Observable<JsonWrapper> fetchJsonData();
}
