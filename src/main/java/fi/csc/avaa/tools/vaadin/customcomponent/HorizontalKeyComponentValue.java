package fi.csc.avaa.tools.vaadin.customcomponent;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Helper class for creating a custom Vaadin component that is a key-value based Vaadin Label component pair. Value is a layout.
 * 
 * @author jmlehtin
 *
 */
public class HorizontalKeyComponentValue extends CustomComponent {

	private static final long serialVersionUID = 1L;
	private HorizontalLayout baseLayout;
	private Label value;
	
	/**
	 * @param keyCmp is the lefthand-side component. Required.
	 * @param keyExpandRatio ratio of how much key component takes space in the base layout. Required.
	 * @param valueStr is the righthand-side string value. Required.
	 * @param valueExpandRatio ratio of how much value component takes space in the base layout. Required.
	 * @param useBoldValue if value needs to be bolded
	 * @param keyStyles is an additional value for key label class name. Not required (null).
	 * @param valueStyles is an additional value for value component class name. Not required (null).
	 * @param valueIcon value can have an icon. Not required (null).
	 */
	public HorizontalKeyComponentValue(Component keyCmp, float keyExpandRatio, String valueStr, float valueExpandRatio, boolean useBoldValue, String keyStyles, String valueStyles, FontAwesome valueIcon) {
		this.baseLayout = new HorizontalLayout();
		this.baseLayout.setSizeFull();
		this.baseLayout.setSpacing(true);
		
		if(keyStyles != null) {
			keyCmp.addStyleName(keyStyles);
		}
		
		this.value = new Label();
		this.value.setWidth(100, Unit.PERCENTAGE);
		this.value.addStyleName("key-value-pair value" + (valueStyles == null ? "" : " " + valueStyles));
		this.value.setContentMode(ContentMode.HTML);
		
		if(useBoldValue) {
			this.value.setValue("<b>" + valueStr + "</b>");
		} else {
			this.value.setValue(valueStr);
		}
		
		if(valueIcon != null) {
			this.value.setIcon(valueIcon);
			this.value.addStyleName("inline");
		}
		
		this.baseLayout.addComponent(keyCmp);
		this.baseLayout.setExpandRatio(keyCmp, keyExpandRatio);
		this.baseLayout.addComponent(this.value);
		this.baseLayout.setExpandRatio(this.value, valueExpandRatio);
		setCompositionRoot(baseLayout);
	}
}
