package cd.cdn.zawaashop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Central wrapper of our logging library. Gives possibility to improve logging, e.g. for usage in AWS monitoring.
 */
public class ZawaaLogger {

private static final String AWS_SEPARATOTR = " ";
	
	/**
	 * Don't change this values because they are used in our AWS filters.
	 */
	private static enum AWS_FILTER_PATTERN{
		AWS_FILTER_PATTERN_MEMORY_MAX,
		AWS_FILTER_PATTERN_MEMORY_COMMITTED,
		AWS_FILTER_PATTERN_MEMORY_USED,		
		AWS_FILTER_PATTERN_MEMORY_USED_PERCENTAGE,
	}
	
	//private static Logger LOG;
	private Logger LOG;
	
	
	private ZawaaLogger(Class<?> clazz) {
		LOG = LoggerFactory.getLogger(clazz);
	}	
	private ZawaaLogger(String name) {
		LOG = LoggerFactory.getLogger(name);
	}	
	public static ZawaaLogger getLogger(Class<?> clazz) {
		return new ZawaaLogger(clazz);
	}
	public static ZawaaLogger getLogger(String name) {
		return new ZawaaLogger(name);
	}
	

	/** AWS metric filter: JVM maximal memory in megabyte */
	public void awsMetric_memoryMax(String memoryMax) {
		awsUnit(AWS_FILTER_PATTERN.AWS_FILTER_PATTERN_MEMORY_MAX, memoryMax);
	}
	/** AWS metric filter: JVM committed memory in megabyte */
	public void awsMetric_memoryCommitted(String memoryCommitted) {
		awsUnit(AWS_FILTER_PATTERN.AWS_FILTER_PATTERN_MEMORY_COMMITTED, memoryCommitted);
	}
	/** AWS metric filter: JVM used memory in megabyte */
	public void awsMetric_memoryUsed(String memoryUsed) {
		awsUnit(AWS_FILTER_PATTERN.AWS_FILTER_PATTERN_MEMORY_USED, memoryUsed);
	}
	/** AWS metric filter: JVM used memory in percentage */
	public void awsMetric_memoryUsedPercentage(String memoryUsedPercentage) {
		awsUnit(AWS_FILTER_PATTERN.AWS_FILTER_PATTERN_MEMORY_USED_PERCENTAGE, memoryUsedPercentage);
	}
	
	
	/**
	 * This method makes a log entry which can be detected by AWS metric filters, especially with the metric unit "count".
	 * 
	 * @param filterPattern
	 */
	private void awsCount(AWS_FILTER_PATTERN filterPattern) {
		info(filterPattern.toString());
	}
	
	/**
	 * This method makes a log entry which can be detected by AWS metric filters. The AWS filter allows to select the
	 * proper unit. <br />
	 * <br />
	 * Example: The following call can be used to register the file size in the AWS log and to use it in the dashboard:
	 * 
	 * <pre>
	 * <code>
	 * DsfetchLogger.awsUnit(FILE_SIZE, file.getBytes().length);
	 * </code>
	 * </pre>
	 * 
	 * @param filterPattern
	 * @param amountOfUnit
	 */
	public void awsUnit(AWS_FILTER_PATTERN filterPattern, String amountOfUnit) {
		info(filterPattern + AWS_SEPARATOTR + amountOfUnit);
	}



	/** see {@link Logger#info(String)} */
	public void info(String string) {
		LOG.info(string);
	}
	/** see {@link Logger#debug(String)} */
	public void debug(String string) {
		LOG.debug(string);
	}
	/** see {@link Logger#debug(String, Object)} */
	public void debug(String format, Object arg) {
		LOG.debug(format, arg);
	}
	/** see {@link Logger#error(String)} */
	public void error(String string) {
		LOG.error(string);
	}
	/** see {@link Logger#error(String, Throwable)} */
	public void error(String string, Throwable ex) {
		LOG.error(string, ex);
	}
	/** see {@link Logger#error(String, Throwable)} */
	public void warn(String string) {
		LOG.warn(string);
	}
	/** see {@link Logger#warn(String, Throwable)} */
	public void warn(String string, Throwable ex) {
		LOG.warn(string, ex);
	}
	
	
}
