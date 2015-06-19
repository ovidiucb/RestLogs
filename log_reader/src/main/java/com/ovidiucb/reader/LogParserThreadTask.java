package com.ovidiucb.reader;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.LogEntry;
import persistence.LogEntryRepository;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by ovidiucb
 */
public class LogParserThreadTask implements Runnable {
    private LogEntryRepository repository;

    private String fileName;

    public LogParserThreadTask(String fileName, LogEntryRepository repository) {
        this.fileName = fileName;
        this.repository = repository;
    }

    public void run() {
        BufferedReader br = null;

        try {
            GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(fileName));
            br = new BufferedReader(new InputStreamReader(gzip));
            String line = new String(br.readLine());

            while (line != null) {
                LogEntry entry = LogParser.parseEntry(line);
                //System.out.println(entry);

                if (entry != null) {
                    repository.save(entry);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
