package io.mlundela.rxjava.ex1;

import io.mlundela.rxjava.StockInfo;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {
    public static void main(String[] args) {

        List<String> symbols = Arrays.asList("AMZN");//, "GOOG", "ORCL");

        Observable<StockInfo> feed = StockServer.getFeed(symbols);

        feed.subscribe(System.out::println);
    }
}