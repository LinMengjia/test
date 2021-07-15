package com.example.demo.Thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            futures.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : futures) {
            try {
                System.out.println("*****    " + future.isDone() + "   *****   " + future.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                executorService.shutdown();
            }
        }
    }
}
