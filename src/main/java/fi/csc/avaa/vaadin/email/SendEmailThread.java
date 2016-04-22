/**
 * 
 */
package fi.csc.avaa.vaadin.email;

import java.util.function.Supplier;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;

/**
 * Base class for a thread that is responsible for running the action
 * 
 * @author jmlehtin
 *
 */
public abstract class SendEmailThread<T> extends NotifyingThread {

	protected String toEmailAddress;
	protected Supplier<T> actionSupplier;
	private boolean mailSent;
	
	public SendEmailThread() {
		
	}
	
	public SendEmailThread(String toEmailAddress, Supplier<T> actionSupplier) {
		this.toEmailAddress = toEmailAddress;
		this.actionSupplier = actionSupplier;
		this.mailSent = false;
	}
	
	protected void sendMail(String fromEmailAddress, String subject, String bodyText, boolean useHtmlInBody) {
		InternetAddress from = null;
		InternetAddress to = null;
		try {
			from = new InternetAddress(fromEmailAddress);
			to = new InternetAddress(toEmailAddress);
	      } catch (AddressException e) {
	         e.printStackTrace();
	      }
		MailMessage mm = new MailMessage(from, to, subject, bodyText, useHtmlInBody);
		MailServiceUtil.sendEmail(mm);
		mailSent = true;
	}
	
	public boolean mailHasBeenSent() {
		return mailSent;
	}
	
}
