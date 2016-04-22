/**
 * 
 */
package fi.csc.avaa.vaadin.email;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Enables the use of thread listeners, which are informed when the thread extending this class have finished their work.
 * 
 * @author jmlehtin
 *
 */
public abstract class NotifyingThread extends Thread {

	private final Set<ThreadCompleteListener> listeners = new CopyOnWriteArraySet<ThreadCompleteListener>();

	public final void addListener(final ThreadCompleteListener listener) {
		listeners.add(listener);
	}
	
	public final void removeListener(final ThreadCompleteListener listener) {
		listeners.remove(listener);
	}
	
	private final void notifyListeners() {
		for (ThreadCompleteListener listener : listeners) {
			listener.notifyOfThreadComplete(this);
		}
	}
	
	@Override
	public final void run() {
		try {
			doRun();
		} finally {
			notifyListeners();
		}
	}
	
	public abstract void doRun();
}
