package io.mlundela.rxjava.ex3;

import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;

public class Sample {
    public static void main(String[] args) {

        List<String> symbols = Arrays.asList("AMZN", "GOOG", "ORCL");

        Observable<StockInfo> feed = StockServer.getFeed(symbols);

        feed.subscribe(System.out::println, System.out::println);

        System.out.println("---------------------------------------");
        //or
        feed.subscribe(new Subscriber<StockInfo>() {
            @Override
            public void onCompleted() {
                System.out.println("Done...");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onNext(StockInfo stockInfo) {
                System.out.println(stockInfo);
            }
        });
        System.out.println("---------------------------------------");

        //Upon error the stream terminates. That way, we don't get bombarded with values when we should be
        //focused on dealing with the error. If an error is going to take some effort or time to fix,
        //we don't worsen the situation with uncontrolled retires.

        //However, if we want to continue on error, we can, using an intermediary.

        feed.onErrorResumeNext(throwable -> {
            System.out.println("The original feed failed....");
            System.out.println(throwable);
            return StockServer.getFeed(symbols);
        }).subscribe(System.out::println, System.out::println);

        System.out.println("---------------------------------------");
    }
}