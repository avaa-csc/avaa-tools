/**
 * 
 */
package fi.csc.avaa.tools.vaadin.helper;

import java.io.Serializable;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.Field;

/**
 * Helper class for binding Vaadin form fields to data source.
 * 
 * @author jmlehtin
 *
 */
public class FormFieldDataBinder implements Serializable {

	private static final long serialVersionUID = 6594381358506089354L;
	private FieldGroup binder;
	private PropertysetItem item;
	
	public FormFieldDataBinder() {
		this.binder = new FieldGroup();
		this.item = new PropertysetItem();
		this.binder.setItemDataSource(this.item);
	}
	
	public <T, U> void bindField(Field<T> fieldToBind, Object propertyId, Property<U> property) {
		item.addItemProperty(propertyId, property);
		binder.bind(fieldToBind, propertyId);
	}
	
	public Field<?> getField(Object propertyId) {
		return binder.getField(propertyId);
	}
}
