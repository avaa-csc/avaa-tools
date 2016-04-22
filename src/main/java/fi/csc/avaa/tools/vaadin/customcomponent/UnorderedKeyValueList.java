package fi.csc.avaa.tools.vaadin.customcomponent;

import java.util.List;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Helper class for creating a custom Vaadin component that is a key-value based unordered list
 * with possible header and value click listeners and associated value data.
 * 
 * @author jmlehtin
 *
 */
/**
 * @author jmlehtin
 *
 */
public class UnorderedKeyValueList extends CustomComponent {

	private static final long serialVersionUID = 1L;
	private VerticalLayout baseLayout;
	
	/**
	 * @param keys is a list of String values which describe the textual contents of the keys in the list
	 * @param keyExpandRatio ratio of how much key component takes space in the base layout. Required.
	 * @param values is a list of String values which describe the textual contents of the values in the list
	 * @param valueExpandRatio ratio of how much value component takes space in the base layout. Required.
	 * @param header is a String value which describe the non-mandatory header label component textual contents. Not required (null).
	 * @param valueDatas is a list of Objects that are to be attached as the value data for the value components in the list. Used together with valueClickListener, which reads this data when value component is clicked. Not required (null).
	 * @param valueClickListener is the functionality that is triggered when the value component of the list is clicked. Not required (null).
	 */
	public UnorderedKeyValueList(List<String> keys, float keyExpandRatio, List<String> values, float valueExpandRatio, String header, List<Object> valueDatas, Button.ClickListener valueClickListener) {
		if(keys.size() == values.size()) {
			this.baseLayout = new VerticalLayout();
			this.baseLayout.setSizeFull();
			this.baseLayout.setSpacing(true);
			this.baseLayout.addStyleName("group-list-container");
			
			// Header for the list, above the key-value list
			if(header != null) {
				Label headerLabel = new Label("<b>" +  header + "</b>");
				headerLabel.addStyleName("group-list-header medium-font-size");
				headerLabel.setContentMode(ContentMode.HTML);
				this.baseLayout.addComponent(headerLabel);
			}
			
			// For each key-value pair (and possible valueId for value link) create a HorizontalKeyValueLabel and add it to layout
			for(int i=0; i < keys.size(); i++) {
				String key = keys.get(i);
				String value = values.get(i);
				Object valueData = null;
				if(valueDatas != null && valueDatas.size() == keys.size()) {
					valueData = valueDatas.get(i);
				}
				this.baseLayout.addComponent(new HorizontalKeyValueLabel(key + ":", keyExpandRatio, value, valueExpandRatio, false, "medium-font-size indented", "medium-font-size", FontAwesome.ANGLE_RIGHT, null, valueData, valueClickListener));
			}
			setCompositionRoot(this.baseLayout);
		}
	}
}
