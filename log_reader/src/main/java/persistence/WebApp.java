package persistence;

import com.ovidiucb.reader.LogReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

/**
 * Created by ovidiucb
 */
@SpringBootApplication
public class WebApp  implements CommandLineRunner {
    @Autowired
    public LogEntryRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        LogReader.readFilesInFolder(new File("LOGS"), repository);

        List<LogEntry> entries = repository.findByIpAddress("62.210.181.156");

        for (LogEntry e : entries) {
            System.out.println(e);
        }
    }
}
