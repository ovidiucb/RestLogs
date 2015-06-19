package com.ovidiucb.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ovidiucb
 */
@Component
public interface LogEntryRepository extends CrudRepository<LogEntry, String> {
    List<LogEntry> findByIpAddress(String ipAddress);
}
