package com.ovidiucb.reader;

/**
 * Created by ovidiucb
 */
public class LogData {
    private String ipAddress;
    private String statusCode;
    private String traffic;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    @Override
    public String toString() {
        return getIpAddress() + " " + getStatusCode() + " " + getTraffic();
    }
}
