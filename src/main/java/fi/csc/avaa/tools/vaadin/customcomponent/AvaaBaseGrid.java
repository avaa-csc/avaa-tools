package fi.csc.avaa.tools.vaadin.customcomponent;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * Super class for CSC usage Vaadin Grid component.
 * 
 * @author jmlehtin
 *
 */
public abstract class AvaaBaseGrid<T, U> extends Grid {

	protected static final long serialVersionUID = 1L;

	protected BeanItemContainer<T> container;
	protected GeneratedPropertyContainer wrapperContainer;
	protected Translator translator;
	protected Collection<U> models;
	
	protected abstract T convertToGridBean(U model);
	protected abstract boolean setModelsToContainer();
	protected abstract void addListeners();
	protected abstract void setColumns();
	protected abstract void setGeneratedColumns();
	protected abstract void setCustomHeaders();
	
	protected AvaaBaseGrid(Translator translator, Class<T> gridBeanClass) {
		super();
		container = new BeanItemContainer<T>(gridBeanClass);
		this.models = new ArrayList<>();
		this.translator = translator;
		setHeaderVisible(true);
		setSelectionMode(SelectionMode.SINGLE);
		setWidth(100, Unit.PERCENTAGE);
		setResponsive(true);
		setImmediate(true);
	}
	
	protected void init() {
		initContainer();
		setGeneratedColumns();
		setColumns();
		addListeners();
		setCustomHeaders();
	}
	
	protected void setGridHeightInRows(int maxRows) {
		setHeightMode(HeightMode.ROW);
		if(wrapperContainer.size() == 0) {
			setHeightByRows(1);
		} else {
			setHeightByRows(Math.min(maxRows, wrapperContainer.size()));
		}
	}
	
	protected void setGridHeightInPixels(int pixels) {
		setHeightMode(HeightMode.CSS);
		setHeight(pixels, Unit.PIXELS);
	}
	
	protected void initContainer() {
		wrapperContainer = new GeneratedPropertyContainer(container);
		setContainerDataSource(wrapperContainer);
	}
	
	public boolean populateGrid(Collection<U> data) {
		setModelData(data);
		return setModelsToContainer();
	}
	
	public int getContainerSize() {
		return wrapperContainer.size();
	}
	
	private void setModelData(Collection<U> data) {
		models = data;
	}

}
