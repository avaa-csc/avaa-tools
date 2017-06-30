/**
 *
 */
package fi.csc.avaa.tools.search.result;

import java.util.Collection;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickListener;

import fi.csc.avaa.tools.csv.ChooseCSVFieldsWindow;
import fi.csc.avaa.tools.search.SearchBean;
import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * @author jmlehtin
 *
 */
public abstract class ResultControlRow<T> extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	protected HorizontalLayout btnLayout;
	protected Button printBtn;
	protected Button csvBtn;
	protected String foundAmountStr;
	protected ChooseCSVFieldsWindow<T> csvWindow;
	protected Label label;

	/**
	 * Use null for printBtn or csvBtn or csvWindow when those functionalities not needed.
	 * If both printBtn and csvBtn are null, show only the amount of results.
	 *
	 * @param foundAmountStr
	 * @param printBtn the button to open a print dialog whose contents are implemented with getHtml
	 * @param csvBtn the button from where to download the csv file
	 * @param bottomMargin for the row
	 */
	public ResultControlRow(String foundAmountStr, Button printBtn, Button csvBtn, boolean bottomMargin) {
		this.foundAmountStr = foundAmountStr;
		this.printBtn = printBtn;
		this.csvBtn = csvBtn;
		if(bottomMargin) {
			setMargin(new MarginInfo(false, false, true, false));
		}
		setSizeFull();
		setSpacing(true);
		setResponsive(true);
	}

	public ResultControlRow(String foundAmountStr, Label label, Button csvBtn) {
		this.foundAmountStr = foundAmountStr;
		this.label = label;
		this.csvBtn = csvBtn;
		setSizeFull();
		setSpacing(true);
		setResponsive(true);
	}

	public void createNewContents(Collection<T> searchResults, SearchBean queryBean) {
		removeAllComponents();
		if(searchResults != null) {
			Label resultAmtText = new Label(foundAmountStr + searchResults.size());
			resultAmtText.setId("FoundAmount");

			if (searchResults.size() > 0) {
				if(printBtn != null || csvBtn != null) {
					btnLayout = new HorizontalLayout();
					btnLayout.setSizeUndefined();
					btnLayout.setSpacing(true);
					btnLayout.setResponsive(true);

					if(printBtn != null) {
						printBtn.getListeners(ClickEvent.class).stream().forEach(l -> printBtn.removeClickListener((ClickListener) l));
						printBtn.addClickListener(e -> {
							JavaScript.getCurrent().execute("top.consoleRef=window.open('','myconsole','width=1200,height=400');" +
									"top.consoleRef.document.write(\"" + getHtml(searchResults, queryBean) + "\");" +
									"top.consoleRef.print();" +
									"top.consoleRef.close();");
						});
						btnLayout.addComponent(printBtn);
					}
					if(label != null){
						btnLayout.addComponent(label);
					}
					if(csvBtn != null) {
						if(csvWindow != null) {
							csvBtn.addClickListener(e -> {
								UI.getCurrent().addWindow(csvWindow);
							});
						}
						btnLayout.addComponent(csvBtn);
					}

					addComponents(resultAmtText, btnLayout);
					setExpandRatio(resultAmtText, 2.0f);
					setExpandRatio(btnLayout, 1.0f);
					setComponentAlignment(btnLayout, Alignment.MIDDLE_RIGHT);
				} else {
					addComponent(resultAmtText);
				}
			} else {
				addComponent(resultAmtText);
			}
		}
	}

	/**
	 * Return the html that is to be printed when user click print button
	 *
	 * @param searchResults
	 * @param queryBean
	 * @return
	 */
	protected abstract String getHtml(Collection<T> searchResults, SearchBean queryBean);

	/**
	 * Set csv window if needed
	 *
	 * @param csvWindow if clicking the csv button should open a window where field for the csv can be chosen
	 */
	public void setCsvFieldsWindow(ChooseCSVFieldsWindow<T> csvWindow) {
		if(this.csvWindow != null) {
			UI.getCurrent().removeWindow(this.csvWindow);
		}
		this.csvWindow = csvWindow;
	}
}
