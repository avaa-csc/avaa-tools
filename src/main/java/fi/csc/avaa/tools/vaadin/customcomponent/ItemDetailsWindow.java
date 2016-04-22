/**
 * 
 */
package fi.csc.avaa.tools.vaadin.customcomponent;

import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * @author jmlehtin
 *
 */
public abstract class ItemDetailsWindow extends Window {

	protected static final long serialVersionUID = 1L;
	protected Translator translator;
	protected VerticalLayout baseLayout;
	
	public ItemDetailsWindow(Translator translator, int widthInPixels, int heightInPixels, boolean isModal, boolean isDraggable, boolean isClosable) {
		super();
		this.translator = translator;
		baseLayout = new VerticalLayout();
		baseLayout.setWidth(100, Unit.PERCENTAGE);
		baseLayout.addStyleName("modal-layout");
		baseLayout.setMargin(true);
		
		setWindowMode(WindowMode.NORMAL);
		setWidth(widthInPixels, Unit.PIXELS);
		setResizable(false);
		setModal(isModal);
		setImmediate(true);
		setHeight(heightInPixels, Unit.PIXELS);
		setDraggable(isDraggable);
		setClosable(isClosable);
		center();
		setContent(baseLayout);
	}
	
	public VerticalLayout getBaseLayout() {
		return baseLayout;
	}
	
	protected abstract void init();
}
