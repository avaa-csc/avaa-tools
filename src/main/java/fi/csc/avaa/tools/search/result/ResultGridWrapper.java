package fi.csc.avaa.tools.search.result;

import java.util.Collection;

import com.vaadin.server.Responsive;
import com.vaadin.ui.VerticalLayout;

import fi.csc.avaa.tools.vaadin.customcomponent.AvaaBaseGrid;

/**
 * 
 * Class for wrapping Grid component into a Vaadin layout
 * 
 * @author jmlehtin
 *
 */
public class ResultGridWrapper<T, U> extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private ResultControlRow<U> controlRow;
	private AvaaBaseGrid<T, U> grid;
	
	public ResultGridWrapper(AvaaBaseGrid<T, U> grid) {
		super();
		this.grid = grid;
		addComponent(this.grid);
		setSizeFull();
		setResponsive(true);
		Responsive.makeResponsive(this);
	}
	
	public ResultGridWrapper(AvaaBaseGrid<T, U> grid, ResultControlRow<U> controlRow) {
		this(grid);
		this.controlRow = controlRow;
		addComponentAsFirst(controlRow);
	}

	public void populateGridWithItems(Collection<U> items) {
		grid.populateGrid(items);
	}
	
	public void setNewGrid(AvaaBaseGrid<T, U> newGrid) {
		super.replaceComponent(grid, newGrid);
		grid = newGrid;
	}

	public AvaaBaseGrid<T, U> getCurrentGrid() {
		return grid;
	}
	
	public void setNewControlRow(ResultControlRow<U> newControlRow) {
		super.replaceComponent(controlRow, newControlRow);
		controlRow = newControlRow;
	}

	public ResultControlRow<U> getCurrentControlRow() {
		return controlRow;
	}
}
