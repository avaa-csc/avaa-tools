/**
 * 
 */
package fi.csc.avaa.tools.cache;

import com.liferay.portal.kernel.messaging.MessageListener;

import fi.csc.avaa.tools.logging.AvaaLogger;

/**
 * Note: If this class is extended, make sure to have something like below in your liferay-portlet.xml. Note, PortletSyncScheduler below should extend this class
 * 
 * <scheduler-entry>
			<scheduler-event-listener-class>fi.csc.avaa....PortletSyncScheduler</scheduler-event-listener-class>  
	        <trigger>  
	        	<cron>  
	           		<cron-trigger-value>0 0 3 ? * * *</cron-trigger-value>  
	         	</cron>   
			</trigger>  
		</scheduler-entry>
 * 
 * @author jmlehtin
 *
 */
public abstract class CacheSyncScheduler implements MessageListener {
	
	protected static AvaaLogger log = new AvaaLogger(CacheSyncScheduler.class.getName());
}
