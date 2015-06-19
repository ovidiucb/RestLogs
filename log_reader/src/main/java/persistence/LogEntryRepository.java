package persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ovidiucb
 */
public interface LogEntryRepository extends CrudRepository<LogEntry, String> {
    List<LogEntry> findByIpAddress(String ipAddress);
}
