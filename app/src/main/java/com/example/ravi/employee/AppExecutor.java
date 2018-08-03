package com.example.ravi.employee;

/**
 * Created by Ravi on 03-08-2018.
 */



import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppExecutor {





        // For Singleton instantiation
        private static final Object LOCK = new Object();
        private static AppExecutor sInstance;
        private final Executor diskIO;

        private AppExecutor(Executor diskIO) {
            this.diskIO = diskIO;

        }

        public static AppExecutor getInstance() {
            if (sInstance == null) {
                synchronized (LOCK) {
                    sInstance = new AppExecutor(Executors.newSingleThreadExecutor());
                }
            }
            return sInstance;
        }

        public Executor diskIO() {
            return diskIO;
        }



        private static class MainThreadExecutor implements Executor {
            private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(@NonNull Runnable command) {
                mainThreadHandler.post(command);
            }
        }

}
