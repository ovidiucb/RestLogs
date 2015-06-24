package com.ovidiucb.webapp;

import com.ovidiucb.webapp.exceptions.BadRequestException;
import com.ovidiucb.webapp.exceptions.InvalidUserException;
import com.ovidiucb.webapp.exceptions.IpNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ovidiucb
 */
@RestController
public class RestLogsController implements ErrorController {
    private static final int SUCCESS = 200;
    private static final int NOT_FOUND = 404;
    private static final String DEFAULT_USER_ID = "admin";

    @Autowired
    LogEntryRepository repository;

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

    @RequestMapping(value="/error")
    public @ResponseBody String getError() {
        throw new BadRequestException();
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
