package com.ovidiucb.webapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ovidiucb
 */
@Entity
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String ipAddress;
    private String statusCode;
    private String traffic;

    public String getStatusCode() {
        return statusCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getTraffic() {
        return traffic;
    }

    protected LogEntry() {}

    public LogEntry(String ipAddress, String statusCode, String traffic) {
        this.ipAddress = ipAddress;
        this.statusCode = statusCode;
        this.traffic = traffic;
    }

    @Override
    public String toString() {
        return String.format("Entry[ID=%d, ip=%s, statusCode='%s', traffic='%s']", id, ipAddress, statusCode, traffic);
    }
}
