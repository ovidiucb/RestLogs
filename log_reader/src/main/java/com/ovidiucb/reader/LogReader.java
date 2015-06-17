package com.ovidiucb.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by ovidiucb
 */
public class LogReader {
    public List<String> readGZIPFile(String fileName) {
        List<String> lines = new ArrayList<String>();
        BufferedReader br = null;

        try {
            GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(fileName));
            br = new BufferedReader(new InputStreamReader(gzip));
            String line = br.readLine();

            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return lines;
    }

    public List<LogData> readFilesInFolder(final File folder) {
        List<LogData> data = new ArrayList<LogData>();

        for (final File entry : folder.listFiles()) {
            if (entry.isDirectory()) {
                readFilesInFolder(entry);
            } else {
                List<String> lines = new ArrayList<String>(readGZIPFile(entry.getAbsolutePath()));

                for (String line : lines) {
                    LogData logData = LogParser.parseEntry(line);
                    if (logData != null) {
                        data.add(logData);
                    }
                }
            }
        }

        return data;
    }

    public static void main(String[] args) {
        LogReader lr = new LogReader();
        List<LogData> data = new ArrayList<LogData>(lr.readFilesInFolder(new File("LOGS")));

        for (LogData dataEntry : data) {
            System.out.println(dataEntry);
        }
    }
}
