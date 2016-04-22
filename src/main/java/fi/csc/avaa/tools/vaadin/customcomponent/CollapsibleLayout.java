package fi.csc.avaa.tools.vaadin.customcomponent;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fi.csc.avaa.vaadin.tools.VaadinTools;

public class CollapsibleLayout extends CustomComponent {
	private static final long serialVersionUID = 1L;

	private String showInfoText;
	private String hideInfoText;

	private Button button;
	
	public CollapsibleLayout(String htmlContent, String showContentText, String hideContentText) {
		VerticalLayout collapseLayout = new VerticalLayout();
		collapseLayout.setMargin(true);
		Label contentText = new Label(htmlContent);
		contentText.setContentMode(ContentMode.HTML);
		contentText.addStyleName("medium-font-size");
		collapseLayout.addComponent(contentText);
		collapseLayout.setComponentAlignment(contentText, Alignment.MIDDLE_LEFT);
		// Create a link button to enable expanding/collapsing the table help
    	button = VaadinTools.createLinkNativeButton(null, null, null, "collapse-button-container");
    	// Add click listener to collapse/expand button
    	button.addClickListener(e -> {
    		Button btn = e.getButton();
    		collapseLayout.setVisible(!collapseLayout.isVisible());
    		if(collapseLayout.isVisible()) {
    			btn.setCaption("<b>" + hideContentText + "</b>");
    			btn.setIcon(FontAwesome.ANGLE_DOUBLE_DOWN);
    		} else {
    			btn.setCaption("<b>" + showContentText + "</b>");
    			btn.setIcon(FontAwesome.ANGLE_DOUBLE_LEFT);
    		}
    	});
    	setCompositionRoot(collapseLayout);
	}
	
	/**
	 * 20.10.2015
	 * 
	 * @param content
	 * @param showInfoText
	 * @param hideInfoText
	 */
	public CollapsibleLayout(VerticalLayout content, String showInfoText, String hideInfoText, boolean collapsed) {
		this.showInfoText = showInfoText;
		this.hideInfoText = hideInfoText;
		// Layout for page info
		VerticalLayout collapseLayout = new VerticalLayout();
		collapseLayout.setMargin(true);
		collapseLayout.addComponent(content);
		// Create a link button to enable expanding/collapsing the table help
    	button = VaadinTools.createLinkButton(null, null, null, "collapse-button-container");
    	if (collapsed) {
			setButtonAsCollapsed();
    		collapseLayout.setVisible(false);
    	}
    	else {
    		setButtonAsExpanded();
    		collapseLayout.setVisible(true);
    	}
    	// Add click listener to collapse/expand button
    	button.addClickListener(e -> {
    		collapseLayout.setVisible(!collapseLayout.isVisible());
    		if(collapseLayout.isVisible()) {
    			setButtonAsExpanded();
    		} else {
    			setButtonAsCollapsed();
    		}
    	});
    	setCompositionRoot(collapseLayout);
	}

	/**
	 * 26.10.2015
	 * 
	 * @param content
	 * @param button
	 * @param showInfoText
	 * @param hideInfoText
	 * @param collapsed
	 */
	public CollapsibleLayout(VerticalLayout content, Button button, String showInfoText, String hideInfoText, boolean collapsed) {
		this.button = button;
		this.showInfoText = showInfoText;
		this.hideInfoText = hideInfoText;
		content.setMargin(true);
    	if (collapsed) {
			setButtonAsCollapsed();
    		content.setVisible(false);
    	}
    	else {
    		setButtonAsExpanded();
    		content.setVisible(true);
    	}
    	button.addClickListener(e -> {
    		content.setVisible(!content.isVisible());
    		if(content.isVisible()) {
    			setButtonAsExpanded();
    		} else {
    			setButtonAsCollapsed();
    		}
    	});
    	setCompositionRoot(content);
	}

	private void setButtonAsCollapsed() {
		button.setCaption(this.showInfoText);
		button.setIcon(FontAwesome.ANGLE_DOUBLE_RIGHT);
	}

	private void setButtonAsExpanded() {
		button.setCaption(this.hideInfoText);
		button.setIcon(FontAwesome.ANGLE_DOUBLE_DOWN);
	}

	public Button getCollapseLink() {
    	return button;
	}
}
