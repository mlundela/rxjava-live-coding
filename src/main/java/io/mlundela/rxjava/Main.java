package io.mlundela.rxjava;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<String> symbols = Arrays.asList("AMZN", "GOOG", "ORCL");

        Observable<StockInfo> feed = StockServer.getFeed(symbols);
        
        

    }
}
