package com.example.headyinterviewtest.Utility;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.ID;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.ORDERS;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.SHARES;
import static com.example.headyinterviewtest.Utility.Constants.ProductSortBy.VIEWS;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Constants {

    public static final String INTENT_DATA = "intent_data";

    @Retention(SOURCE)
    @StringDef({ID, VIEWS, ORDERS, SHARES})
    public @interface ProductSortBy {
        String ID = "ID";
        String VIEWS = "Most Viewed Products";
        String ORDERS = "Most OrdeRed Products";
        String SHARES = "Most ShaRed Products";
    }
}
