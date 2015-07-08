package com.ovidiucb.webapp;

import com.ovidiucb.reader.LogReader;
import com.ovidiucb.webapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by ovidiucb
 */
@RestController
public class RestLogsController extends ExceptionHandlerHelper {
    private static final int SUCCESS = 200;
    private static final int NOT_FOUND = 404;
    private static final String DEFAULT_USER_ID = "admin";
    private static final String UPLOAD_DIRECTORY = "LOGS/";

    @Autowired
    LogEntryRepository repository;

    @RequestMapping(value="/logs/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        throw new InvalidMethodException();
    }

    @RequestMapping(value="/logs/upload", method=RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile[] files) {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                String name = file.getOriginalFilename();
                String fullFileName = new StringBuilder().append(UPLOAD_DIRECTORY).append(name).toString();
                File f = new File(fullFileName);

                f.getParentFile().mkdirs();

                try {
                    byte[] bytes = files[0].getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(fullFileName)));
                    stream.write(bytes);
                    stream.close();
                    LogReader.readFile(f, repository);
                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            }
        } else {
            return "You failed to upload because no files were selected.";
        }

        return new StringBuilder().append("You successfully uploaded file(s)").append("!").append("<br><br>").toString();
    }

    @RequestMapping(value = "/logs/statistics/{ipAddress}/traffic")
    public @ResponseBody String getStatistics(@PathVariable("ipAddress") String ipAddress,
                                              @RequestParam(value="userId", required=false, defaultValue="admin") String userId) {
        if (userId.compareTo(DEFAULT_USER_ID) != 0) {
            throw new InvalidUserException(userId);
        }

        List<LogEntry> entries = repository.findByIpAddress(ipAddress);
        long traffic = 0;
        int success = 0;
        int notFound = 0;

        if (entries == null || entries.size() == 0) {
            throw new IpNotFoundException(String.format("There is no entry with IP=%s", ipAddress));
        } else {
            for (LogEntry entry : entries) {
                traffic += Integer.parseInt(entry.getTraffic());
                int statusCode = Integer.parseInt(entry.getStatusCode());

                switch (statusCode) {
                    case SUCCESS: {
                        ++success;
                        break;
                    }
                    case NOT_FOUND: {
                        ++notFound;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }

        return String.format("Total traffic: %d bytes, success: %d, not found: %d", traffic, success, notFound);
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
