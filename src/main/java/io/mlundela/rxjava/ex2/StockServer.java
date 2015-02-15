package io.mlundela.rxjava.ex2;

import io.mlundela.rxjava.StockInfo;
import rx.Observable;

import java.util.List;

public class StockServer {
    public static Observable<StockInfo> getFeed(List<String> symbols) {
        return Observable.create(subscriber -> {
            while (!subscriber.isUnsubscribed()) {
                symbols.stream()
                        .map(StockInfo::fetch)
                        .forEach(subscriber::onNext);

                sleep(1000);
            }
        });
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
        }
    }
}
