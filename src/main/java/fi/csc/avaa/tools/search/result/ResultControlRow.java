/**
 * 
 */
package fi.csc.avaa.tools.search.result;

import java.util.Collection;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import fi.csc.avaa.tools.csv.ChooseCSVFieldsWindow;
import fi.csc.avaa.tools.search.SearchBean;
import fi.csc.avaa.tools.vaadin.language.Translator;
import fi.csc.avaa.vaadin.tools.VaadinTools;

/**
 * @author jmlehtin
 *
 */
public abstract class ResultControlRow<T> extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	private HorizontalLayout btnContainer;
	protected Button printBtn;
	protected Button csvBtn;
	protected Translator translator;
	protected ChooseCSVFieldsWindow<T> csvWindow;
	private boolean doPrint, doCsv;

	public ResultControlRow(Translator translator, boolean doPrint, boolean doCsv, boolean bottomMargin) {
		this.translator = translator;
		setSizeFull();
		setSpacing(true);
		if(bottomMargin) {
			setMargin(new MarginInfo(false, false, true, false));
		}
		setResponsive(true);
		this.doCsv = doCsv;
		this.doPrint = doPrint;
	}

	public void createNewContents(Collection<T> searchResults, SearchBean queryBean) {
		removeAllComponents();
		if(searchResults != null) {
			Label resultAmtText = new Label(translator.localize("Search.FoundAmount") + searchResults.size());
			resultAmtText.setId("FoundAmount");
			
			if (searchResults.size() > 0) {
				if(doPrint || doCsv) {
					btnContainer = new HorizontalLayout();
					btnContainer.setSizeUndefined();
					btnContainer.setSpacing(true);
					btnContainer.setResponsive(true);
				
					if(doPrint) {
						printBtn = VaadinTools.createButton(translator.localize("Application.Print"), FontAwesome.PRINT, null, "print-all-btn");
						printBtn.addClickListener(e -> {
							JavaScript.getCurrent().execute("top.consoleRef=window.open('','myconsole','width=1200,height=400');" +
															"top.consoleRef.document.write(\"" + getHtml(searchResults, queryBean) + "\");" + 
															"top.consoleRef.print();" +
															"top.consoleRef.close();");
						});
						btnContainer.addComponent(printBtn);
					}
					if(doCsv) {
						csvBtn = VaadinTools.createButton(translator.localize("Application.Csv"), FontAwesome.DOWNLOAD, null, "download-all-btn");
						csvBtn.addClickListener(e -> {
							if(csvWindow != null) {
								UI.getCurrent().addWindow(csvWindow);
							}
						});
						btnContainer.addComponent(csvBtn);
					}
					
					addComponents(resultAmtText, btnContainer);
					setExpandRatio(resultAmtText, 3.0f);
					setExpandRatio(btnContainer, 1.0f);
					setComponentAlignment(btnContainer, Alignment.MIDDLE_RIGHT);
				} else {
					addComponent(resultAmtText);
				}
			} else {
				addComponent(resultAmtText);
			}
		}
	}
	
	protected abstract String getHtml(Collection<T> searchResults, SearchBean queryBean);
	
	public void setCsvFieldsWindow(ChooseCSVFieldsWindow<T> csvWindow) {
		this.csvWindow = csvWindow;
	}
}
