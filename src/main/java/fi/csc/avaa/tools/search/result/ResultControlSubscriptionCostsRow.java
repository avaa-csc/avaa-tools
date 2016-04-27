package fi.csc.avaa.tools.search.result;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import fi.csc.avaa.tools.search.SearchBean;
import fi.csc.avaa.tools.vaadin.language.Translator;

import java.util.Collection;

public abstract class ResultControlSubscriptionCostsRow<T> extends HorizontalLayout {

    private static final long serialVersionUID = 1L;
    private HorizontalLayout btnContainer;
    private Button createDownloadLink;
    protected Translator translator;

    public ResultControlSubscriptionCostsRow(Translator translator, Button createDownloadLink, boolean bottomMargin) {
        this.translator = translator;
        setSizeFull();
        setSpacing(true);
        if (bottomMargin) {
            setMargin(new MarginInfo(false, false, true, false));
        }
        setResponsive(true);
        this.createDownloadLink = createDownloadLink;
    }

    public void createNewContents(Collection<T> searchResults) {
        removeAllComponents();
        if (searchResults != null) {
            Label resultAmtText = new Label(translator.localize("Search.FoundAmount") + searchResults.size());
            resultAmtText.setId("FoundAmount");

            if (searchResults.size() > 0) {
                btnContainer = new HorizontalLayout();
                btnContainer.setSizeUndefined();
                btnContainer.setSpacing(true);
                btnContainer.setResponsive(true);
                btnContainer.addComponent(createDownloadLink);

                addComponents(resultAmtText, btnContainer);
                setExpandRatio(resultAmtText, 3.0f);
                setExpandRatio(btnContainer, 1.0f);
                setComponentAlignment(btnContainer, Alignment.MIDDLE_RIGHT);
            } else {
                addComponent(resultAmtText);
            }
        }
    }

    protected abstract String getHtml(Collection<T> searchResults, SearchBean queryBean);
}


