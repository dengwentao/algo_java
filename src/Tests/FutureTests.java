package Tests;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.*;

/**
 * Created by wentaod on 12/7/15.
 */

public class FutureTests <T> {


    public Future<T> sendAsync(final String key, final T value, final long currentVersion) throws Exception {
        ExecutorService threadpool = Executors.newSingleThreadExecutor();

        //Future<T> future = threadpool.submit(new AsyncSend(key, value, currentVersion));

        Future<T> future = threadpool.submit(new Callable<T>() {
            public T call() throws Exception {
                return send(key, value, currentVersion);
            }
        });

        threadpool.shutdown();
        return future;
    }

    public T send(String key, T value, long currentVersion) {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println("Sleeping for 1 second ...");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return value;
    }

    private class AsyncSend implements Callable<T> {
        String key;
        T value;
        long currentVersion;

        public AsyncSend(String key, T value, long currentVersion) {
            this.key = key;
            this.value = value;
            this.currentVersion = currentVersion;
        }

        @Override
        public T call() throws Exception {
            return send(key, value, currentVersion);
        }
    }

    public static void main(String args[]) {
        try {
            FutureTests test = new FutureTests();
            String s = "Hello, world!";
            Future future = test.sendAsync("key", s, 1);
            while(!future.isDone()) {
                System.out.println("Waiting for 1 second ...");
                Thread.sleep(1000);
            }
            System.out.println("Task is done!");
            System.out.println(future.get());
            for(char c : ((String)(future.get())).toCharArray()) {
                System.out.println(c);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}

