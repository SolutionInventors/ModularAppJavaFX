package database.statistics;

/**
 * This is represents statistics for different {@code Bean}s in the system
 * The interface does not contain any methods but serves as a wrapper interface.
 * One key feature of all implementation of this interface is that they can only
 * be retrieved from the {@link StatisticsManager#retrieveStats(Bean) } method and
 * they must be immutable once created.<br>
 * @see  StudentStats
 * @see ModuleStats
 * @author Oguejifor Chidiebere
 *
 */
public interface Statistics{}