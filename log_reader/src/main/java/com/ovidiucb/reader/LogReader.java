package com.ovidiucb.reader;

import com.ovidiucb.webapp.LogEntryRepository;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ovidiucb
 */
public class LogReader {
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void readFilesInFolder(final File folder, LogEntryRepository repository) {
        for (final File entry : folder.listFiles()) {
            if (entry.isDirectory()) {
                readFilesInFolder(entry, repository);
            } else {
                readFile(entry, repository);
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void readFile(final File file, LogEntryRepository repository) {
        executorService.submit(new LogParserThreadTask(file.getAbsolutePath(), repository));
    }
}
