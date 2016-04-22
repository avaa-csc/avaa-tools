/**
 * 
 */
package fi.csc.avaa.vaadin.email;

/**
 * A class implementing this interface gets notified of the finishing of the threads that this class is added as a listener to.
 * 
 * @author jmlehtin
 *
 */
public interface ThreadCompleteListener {
	
	void notifyOfThreadComplete(final Thread thread);
	
}
