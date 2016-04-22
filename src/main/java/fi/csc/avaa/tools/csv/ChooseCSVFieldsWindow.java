/**
 * 
 */
package fi.csc.avaa.tools.csv;

import java.util.Collection;

import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Window;

import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * @author jmlehtin
 *
 */
public abstract class ChooseCSVFieldsWindow<T> extends Window {

	private static final long serialVersionUID = 1L;
	protected Translator translator;
	protected Collection<T> data;
	
	public ChooseCSVFieldsWindow(Translator translator, Collection<T> data) {
		super();
		this.translator = translator;
		this.data = data;
		setWindowMode(WindowMode.NORMAL);
		setWidth(400, Unit.PIXELS);
		setResizable(true);
		setModal(true);
		setImmediate(true);
		setHeight(500, Unit.PIXELS);
		setDraggable(true);
		setClosable(true);
		center();
		init();
	}
	
	public abstract void init();
	
}
