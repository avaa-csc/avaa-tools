package fi.csc.avaa.tools.vaadin.customcomponent;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

/**
 * Helper class for creating a custom Vaadin component that is a key-value based Vaadin Label component pair. Value is a string.
 * 
 * @author jmlehtin
 *
 */
public class HorizontalKeyValueLabel extends CustomComponent {

	private static final long serialVersionUID = 1L;
	private HorizontalLayout baseLayout;
	private Label key;
	
	/**
	 * @param key is the lefthand-side Label component String value. Required.
	 * @param keyExpandRatio ratio of how much key component takes space in the base layout. Required.
	 * @param value is the righthand-side component String value. Required.
	 * @param valueExpandRatio ratio of how much value component takes space in the base layout. Required.
	 * @param useBoldKey if key needs to be bolded
	 * @param keyStyles is an additional value for key label class name. Not required (null).
	 * @param valueStyles is an additional value for value component class name. Not required (null).
	 * @param keyIcon key can have an icon. Not required (null).
	 * @param valueClickData is a value that is set as the data for the Button link component to identify the value which was clicked. Not required (null).
	 * @param valueClickListener is the listener which is invoked when the button is clicked. Not required (null).
	 */
	public HorizontalKeyValueLabel(String key, float keyExpandRatio, String value, float valueExpandRatio, boolean useBoldKey, String keyStyles, String valueStyles, FontAwesome keyIcon, ExternalResource valueUrl, Object valueClickData, Button.ClickListener valueClickListener) {
		this.baseLayout = new HorizontalLayout();
		this.baseLayout.setSizeFull();
		this.baseLayout.setSpacing(true);
		addStyleName("key-value-row");
		
		this.key = new Label();
		this.key.setWidth(100, Unit.PERCENTAGE);
		this.key.addStyleName("key-value-pair key" + (keyStyles == null ? "" : " " + keyStyles));
		this.key.setContentMode(ContentMode.HTML);
		
		if(useBoldKey) {
			this.key.setValue("<b>" + key + "</b>");
		} else {
			this.key.setValue(key);
		}
		
		if(keyIcon != null) {
			this.key.setIcon(keyIcon);
			this.key.addStyleName("inline");
		}
		
		this.baseLayout.addComponent(this.key);
		this.baseLayout.setExpandRatio(this.key, keyExpandRatio);
		
		// If valueClickId and valueClickListener is given, create value component as button which can be clicked and which will perform the action given in valueClickListener
		if(valueClickData != null && valueClickListener != null) {
			Button valueButton = new Button(value);
			valueButton.setWidth(100, Unit.PERCENTAGE);
			valueButton.addStyleName("key-value-pair link" + (valueStyles == null ? "" : " " + valueStyles));
			// Here valueId is set as the data payload of the value button
			valueButton.setData(valueClickData);
			valueButton.addClickListener(valueClickListener);
			this.baseLayout.addComponent(valueButton);
			this.baseLayout.setExpandRatio(valueButton, valueExpandRatio);
		// If valueUrl is given, make value an URL
		} else if(valueUrl != null) {
			Link link = new Link(value, valueUrl);
			link.setTargetName("_new");
			link.setWidth(100, Unit.PERCENTAGE);
			link.addStyleName(valueStyles == null ? "" : " " + valueStyles);
			this.baseLayout.addComponent(link);
			this.baseLayout.setExpandRatio(link, valueExpandRatio);
		// Otherwise create a label component
		} else {
			Label valueLabel = new Label();
			valueLabel.setWidth(100, Unit.PERCENTAGE);
			valueLabel.addStyleName("key-value-pair value" + (valueStyles == null ? "" : " " + valueStyles));
			valueLabel.setContentMode(ContentMode.HTML);
			valueLabel.setValue(value);
			this.baseLayout.addComponent(valueLabel);
			this.baseLayout.setExpandRatio(valueLabel, valueExpandRatio);
		}
		setCompositionRoot(baseLayout);
	}
}
