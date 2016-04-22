package fi.csc.avaa.tools.vaadin.customcomponent;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import fi.csc.avaa.tools.vaadin.language.Translator;
import fi.csc.avaa.vaadin.tools.VaadinTools;

public class DetailsConfirmationDialog extends Window {
	private static final long serialVersionUID = 1L;
	private String message;
	private String details;
	private Translator translator;

	public DetailsConfirmationDialog(Translator translator, String caption, String message, String details) {
		super(caption);
		this.translator = translator;
		this.details = details;
		this.message = message;
		setWidth(350, Unit.PIXELS);
		setResponsive(true);
		setResizable(false);
		init();
	}

	protected void init() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.addStyleName("modal-layout");
		HorizontalLayout labelLayout = new HorizontalLayout(new Label(message));
		TextArea textArea = new TextArea();
		textArea.setWordwrap(false);
		textArea.setValue(details);
		textArea.setReadOnly(true);
		textArea.setSizeFull();
		textArea.setVisible(false);
		textArea.setRows(20);
		Button okButton = VaadinTools.createButton(translator.localize("Application.Dialog.Close"), null, null, null);
		okButton.addClickListener(event -> {
			close();
		});
		Button detailsButton = VaadinTools.createButton(translator.localize("Application.Dialog.More.Button"), FontAwesome.ANGLE_DOUBLE_DOWN, null, null);
		detailsButton.addClickListener(event -> {
			if (! textArea.isVisible()) {
				textArea.setVisible(true);
				detailsButton.setCaption(translator.localize("Application.Dialog.Less.Button"));
				detailsButton.setIcon(FontAwesome.ANGLE_DOUBLE_UP);
			}
			else {
				textArea.setVisible(false);
				detailsButton.setCaption(translator.localize("Application.Dialog.More.Button"));
				detailsButton.setIcon(FontAwesome.ANGLE_DOUBLE_DOWN);
			}
		});
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.setMargin(true);
		buttonLayout.addComponents(okButton, detailsButton);

		layout.addComponents(labelLayout, textArea, buttonLayout);
		layout.setComponentAlignment(labelLayout, Alignment.BOTTOM_CENTER);
		layout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_CENTER);
		setContent(layout);
		center();
	}
}
