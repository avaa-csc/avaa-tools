package fi.csc.avaa.tools.logging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Class for logging
 * 
 * @author jmlehtin
 *
 */
public final class AvaaLogger {

	private Log log;
	
	public AvaaLogger(String className)  {
		this.log = LogFactoryUtil.getLog(className);
	}
	
	public void debug(Object msg) {
		log.debug(msg);
	}
	
	public void info(Object msg) {
		log.info(msg);
	}
	
	public void warn(Object msg) {
		log.warn(msg);
	}
	
	public void error(Object msg) {
		log.error(msg);
	}
	public void error(Object msg, Throwable th) {
		log.error(msg, th);
	}
}
