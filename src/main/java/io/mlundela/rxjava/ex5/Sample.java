package io.mlundela.rxjava.ex5;

import io.mlundela.rxjava.StockInfo;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Sample {

    public static void printInfo(StockInfo stockInfo) {
        System.out.println(stockInfo + " --- " + Thread.currentThread());
    }

    public static void main(String[] args) throws InterruptedException {

        List<String> symbols = Arrays.asList("AMZN", "GOOG", "ORCL");

        Observable<StockInfo> feed = StockServer.getFeed(symbols);

        feed.take(15)
                .subscribe(Sample::printInfo);

        System.out.println("Let's make the client asynchronous");

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        feed.take(15)
                .subscribeOn(Schedulers.from(executorService))
                .forEach(Sample::printInfo);

        executorService.awaitTermination(2, TimeUnit.MINUTES);
        executorService.shutdown();
    }
}