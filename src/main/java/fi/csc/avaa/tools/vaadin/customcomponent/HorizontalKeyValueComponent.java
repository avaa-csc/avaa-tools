package fi.csc.avaa.tools.vaadin.customcomponent;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Helper class for creating a custom Vaadin component that is a key-value based Vaadin Label component pair. Value is a layout.
 * 
 * @author jmlehtin
 *
 */
public class HorizontalKeyValueComponent extends CustomComponent {

	private static final long serialVersionUID = 1L;
	private HorizontalLayout baseLayout;
	private Label key;
	
	/**
	 * @param key is the lefthand-side Label component String value. Required.
	 * @param keyExpandRatio ratio of how much key component takes space in the base layout. Required.
	 * @param valueLayout is the righthand-side component layout value. Required.
	 * @param valueExpandRatio ratio of how much value component takes space in the base layout. Required.
	 * @param useBoldKey if key needs to be bolded
	 * @param keyStyles is an additional value for key label class name. Not required (null).
	 * @param valueStyles is an additional value for value component class name. Not required (null).
	 * @param keyIcon key can have an icon. Not required (null).
	 */
	public HorizontalKeyValueComponent(String key, float keyExpandRatio, HorizontalLayout valueLayout, float valueExpandRatio, boolean useBoldKey, String keyStyles, String valueStyles, FontAwesome keyIcon) {
		this.baseLayout = new HorizontalLayout();
		this.baseLayout.setSizeFull();
		this.baseLayout.setSpacing(true);
		
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
		this.baseLayout.addComponent(valueLayout);
		this.baseLayout.setExpandRatio(valueLayout, valueExpandRatio);
		setCompositionRoot(baseLayout);
	}
	
	public Label getKey() {
		return key;
	}
}
