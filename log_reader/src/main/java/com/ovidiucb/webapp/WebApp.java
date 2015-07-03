package com.ovidiucb.webapp;

import com.ovidiucb.reader.LogReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.util.List;

/**
 * Created by ovidiucb
 */
@SpringBootApplication
@ComponentScan
public class WebApp  implements CommandLineRunner {
    @Autowired
    public LogEntryRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
    }
}
