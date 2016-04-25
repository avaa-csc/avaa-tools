/**
 * 
 */
package fi.csc.avaa.vaadin.tools;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect;
import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect.ShowButton;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fi.csc.avaa.tools.StringTools;
import fi.csc.avaa.tools.logging.AvaaLogger;
import fi.csc.avaa.tools.vaadin.language.LanguageConst;

/**
 * 
 * @author SUI-team
 * @modified jmlehtin
 *
 */
public final class VaadinTools {

	private static AvaaLogger log = new AvaaLogger(VaadinTools.class.getName());
	
	public static NativeSelect createNativeSelect(Collection<String> itemIds, Collection<String> itemCaptions, String nullSelectionItem, int pixelWidth) {
		if(itemIds.size() != itemCaptions.size()) {
			return null;
		}
		NativeSelect select = new NativeSelect();
		if(pixelWidth > 0) {
			select.setWidth(pixelWidth, Unit.PIXELS);
		}
		Iterator<String> idIt = itemIds.iterator();
		Iterator<String> captionIt = itemCaptions.iterator();
		
		while (idIt.hasNext()) {
			String itemId = idIt.next();
			String caption = captionIt.next();
			select.addItem(itemId);
			select.setItemCaption(itemId, caption);
			if(select.getValue() == null) {
				select.setValue(itemId);
			}
		}
		select.setMultiSelect(false);
		select.setNewItemsAllowed(false);
		select.setRequired(false);
		if(nullSelectionItem != null) {
			select.setNullSelectionAllowed(true);
			select.setNullSelectionItemId(nullSelectionItem);
			select.setValue(nullSelectionItem);
		} else {
			select.setNullSelectionAllowed(false);
		}
		return select;
	}
	
	public static ComboBox createComboBox(TreeSet<String> itemIds, TreeSet<String> itemCaptions, String nullSelectionItem, String inputPrompt, int pixelWidth) {
		ComboBox cb = createComboBox(itemIds, itemCaptions, nullSelectionItem, inputPrompt);
		cb.setWidth(pixelWidth, Unit.PIXELS);
		return cb;
	}
	
	public static ComboBox createComboBox(TreeSet<String> itemIds, TreeSet<String> itemCaptions, String nullSelectionItem, String inputPrompt) {
		if(itemIds.size() != itemCaptions.size()) {
			return null;
		}
		ComboBox select = new ComboBox();
		select.setTextInputAllowed(true);
		select.setImmediate(true);

		if(nullSelectionItem != null) {
			select.setNullSelectionAllowed(true);
			select.addItem(nullSelectionItem);
			select.setNullSelectionItemId(nullSelectionItem);
		} else {
			select.setNullSelectionAllowed(false);
		}
		
		Iterator<String> idIt = itemIds.iterator();
		Iterator<String> captionIt = itemCaptions.iterator();
		
		while (idIt.hasNext()) {
			String itemId = idIt.next();
			String caption = captionIt.next();
			select.addItem(itemId);
			select.setItemCaption(itemId, caption);
		}
		select.setNewItemsAllowed(false);
		select.setRequired(false);
		if(inputPrompt != null) {
			select.setInputPrompt(inputPrompt);
		}

		if(nullSelectionItem != null) {
			select.setValue(nullSelectionItem);
		} else {
			select.setValue(itemIds.first());
		}
		return select;
	}
	
	public static ComboBoxMultiselect createMultiselectComboBox(List<String> itemIds, List<String> itemCaptions, String inputPrompt, int pixelWidth) {
		ComboBoxMultiselect cbms = createMultiselectComboBox(itemIds, itemCaptions, inputPrompt);
		cbms.setWidth(pixelWidth, Unit.PIXELS);
		return cbms;
	}
	
	public static ComboBoxMultiselect createMultiselectComboBox(List<String> itemIds, List<String> itemCaptions, String inputPrompt) {
		if(itemIds.size() != itemCaptions.size()) {
			return null;
		}
		ComboBoxMultiselect select = new ComboBoxMultiselect();
		select.setTextInputAllowed(true);
		
		ShowButton doNotShowBtn = new ShowButton() {
			
			@Override
			public boolean isShow(String filter, int page) {
				return false;
			}
		};
		select.setShowClearButton(doNotShowBtn);
		select.setShowSelectAllButton(doNotShowBtn);
		
		select.setFilteringMode(FilteringMode.CONTAINS);
		
		Iterator<String> idIt = itemIds.iterator();
		Iterator<String> captionIt = itemCaptions.iterator();
		
		while (idIt.hasNext()) {
			String itemId = idIt.next();
			String caption = captionIt.next();
			select.addItem(itemId);
			select.setItemCaption(itemId, caption);
		}
		select.setNewItemsAllowed(false);
		select.setRequired(false);
		if(inputPrompt != null) {
			select.setInputPrompt(inputPrompt);
		}

		return select;
	}
	
	
	public static TextField createTextField(float pixelWidth, String inputPrompt, int maxLength, boolean nullAllowed, String styleName) {
		TextField textField = new TextField();
		if(pixelWidth > 0) {
			textField.setWidth(pixelWidth, Unit.PIXELS);
		}
		textField.setImmediate(true);
		if(inputPrompt != null) {
			textField.setInputPrompt(inputPrompt);
		}
		if(maxLength > 0) {
			textField.setMaxLength(maxLength);
		}
		textField.setNullSettingAllowed(nullAllowed);
		if(styleName != null) {
			textField.addStyleName(styleName);
		}
		textField.setHeight(30, Unit.PIXELS);
		return textField;
	}
	
	public static TextArea createTextArea(float pixelWidth, String inputPrompt, int maxLength, boolean nullAllowed, String styleName) {
		TextArea textArea = new TextArea();
		if(pixelWidth > 0) {
			textArea.setWidth(pixelWidth, Unit.PIXELS);
		}
		textArea.setImmediate(true);
		if(inputPrompt != null) {
			textArea.setInputPrompt(inputPrompt);
		}
		if(maxLength > 0) {
			textArea.setMaxLength(maxLength);
		}
		textArea.setNullSettingAllowed(nullAllowed);
		if(styleName != null) {
			textArea.addStyleName(styleName);
		}
		return textArea;
	}
	
	public static NativeButton createLinkNativeButton(String caption, Resource icon,
			String tooltip, String styleName) {
		NativeButton retVal = createNativeButton(caption, icon, tooltip, styleName);
		retVal.addStyleName("link");
		return retVal;
	}

	public static NativeButton createNativeButton(String caption, Resource icon,
			String tooltip, String styleName) {
		NativeButton retVal;
		if(caption == null) {
			retVal = new NativeButton();
		} else {
			retVal = new NativeButton(caption);
		}
		if(styleName != null) {
			retVal.addStyleName(styleName);
		}
		if (icon != null) {
			retVal.setIcon(icon);
		}
		if (tooltip != null) {
			retVal.setDescription(tooltip);
		}
		retVal.setImmediate(true);
		retVal.setHtmlContentAllowed(true);
		return retVal;
	}
	
	public static Button createLinkButton(String caption, Resource icon,
			String tooltip, String styleName) {
		Button retVal = createButton(caption, icon, tooltip, styleName);
		retVal.addStyleName("link");
		return retVal;
	}

	public static Button createButton(String caption, Resource icon,
			String tooltip, String styleName) {
		Button retVal;
		if(caption == null) {
			retVal = new Button();
		} else {
			retVal = new Button(caption);
		}
		if(styleName != null) {
			retVal.addStyleName(styleName);
		}
		if (icon != null) {
			retVal.setIcon(icon);
		}
		if (tooltip != null) {
			retVal.setDescription(tooltip);
		}
		retVal.setImmediate(true);
		retVal.setHtmlContentAllowed(true);
		return retVal;
	}

	public static void showTrayMessage(String caption, String message, int delayMsecs, Position pos) {
		if (Page.getCurrent() == null) {
			log.error("Could not show tray message because Vaadin Page is null. Message was: "
					+ message);
		} else if (caption != null) {
			Notification not = null;
			if(message == null) {
				not = new Notification(caption,
						Notification.Type.TRAY_NOTIFICATION);
			} else {
				not = new Notification(caption, message,
						Notification.Type.TRAY_NOTIFICATION);
			}
			not.setHtmlContentAllowed(true);
			not.setDelayMsec(delayMsecs);
			if(pos != null) {
				not.setPosition(pos);
			}
			not.show(Page.getCurrent());
		}
	}

	public static void showError(String caption, String message) {
		if (Page.getCurrent() == null) {
			log.error("Could not show the error because Vaadin Page is null. Message was: "
					+ message);
		} else if (caption != null) {
			Notification not = null;
			if(message == null) {
				not = new Notification(caption,
						Notification.Type.ERROR_MESSAGE);
			} else {
				not = new Notification(caption, message,
						Notification.Type.ERROR_MESSAGE);
			}
			not.setHtmlContentAllowed(true);
			not.setDelayMsec(2000);
			not.show(Page.getCurrent());
		}
	}
	
	public static void showWarning(String caption, String message) {
		if (Page.getCurrent() == null) {
			log.error("Could not show the error because Vaadin Page is null. Message was: "
					+ message);
		} else if (caption != null) {
			Notification not = null;
			if(message == null) {
				not = new Notification(caption,
						Notification.Type.WARNING_MESSAGE);
			} else {
				not = new Notification(caption, message,
						Notification.Type.WARNING_MESSAGE);
			}
			not.setHtmlContentAllowed(true);
			not.setDelayMsec(2000);
			not.show(Page.getCurrent());
		}
	}

	public static void showNotification(String caption, String message) {
		if (Page.getCurrent() == null) {
			log.error("Could not show notification because Vaadin Page is null: "
					+ message);
		} else if (caption != null) {
			Notification not = null;
			if(message == null) {
				not = new Notification(caption,
						Notification.Type.HUMANIZED_MESSAGE);
			} else {
				not = new Notification(caption, message,
						Notification.Type.HUMANIZED_MESSAGE);
			}
			not.setHtmlContentAllowed(true);
			not.setDelayMsec(2000);
			not.show(Page.getCurrent());
		}
	}
	
	public static Component createErrorLayout(Exception e, String locale) {
		VerticalLayout layout = new VerticalLayout();
		String message;
		if(LanguageConst.getLocaleStrFromLocale(LanguageConst.LOCALE_FI).equals(locale)) {
			message = "Sivulla tapahtui virhe. Ota yhteyttä AVAA ylläpitoon.";
		} else {
			message = "An error occurred on the page. Please contact AVAA maintenance.";
		}
		if (e.getMessage() != null) {
			message = e.getMessage() + "\n" + message;
		}
		layout.addComponent(VaadinTools.createMessageError(message));
		layout.setSizeUndefined();
		layout.setWidth("100%");
		return layout;
	}

	private static Label createMessage(String text, String style) {
		Label label = new Label(text, ContentMode.HTML);
		label.setStyleName(style);
		label.setWidth(100, Unit.PERCENTAGE);
		return label;
	}
	
	public static Label createMessageError(String text) {
		return createMessage(text, "message-error");
	}

	public static CheckBox createCheckBox(String text, boolean selected, String style) {
		CheckBox checkBox = new CheckBox(text, selected);
		checkBox.setStyleName(style);
		return checkBox;
	}
	
	public static Image createImageFromExternalResource(String caption, String sourceURL) {
		if(StringTools.isEmptyOrNull(sourceURL)) {
			return null;
		}
		return new Image(caption, new ExternalResource(sourceURL));
	}
	
	public static Link createLink(String caption, String sourceURL, boolean isEmail) {
		if(StringTools.isEmptyOrNull(sourceURL)) {
			return null;
		}
		String src = sourceURL;
		if(isEmail) {
			src = "mailto:" + src;
		}
		Link link = new Link(caption, new ExternalResource(src));
		if(isEmail) {
			link.setTargetName("_self");
		} else {
			link.setTargetName("_blank");
		}
		return link;
	}
}
