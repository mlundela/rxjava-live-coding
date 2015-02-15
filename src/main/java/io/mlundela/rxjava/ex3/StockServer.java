package io.mlundela.rxjava.ex3;

import rx.Observable;

import java.util.List;

public class StockServer {
    public static Observable<StockInfo> getFeed(List<String> symbols) {
        return Observable.create(
                subscriber -> {

                    try {
                        while (!subscriber.isUnsubscribed()) {
                            symbols.stream()
                                    .map(StockInfo::fetch)
                                    .forEach(subscriber::onNext);

                            sleep(1000);
                        }
                    } catch (Exception ex) {
                        subscriber.onError(ex);
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
