/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.helpers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

import org.jdesktop.jxlayer.JXLayer;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.lov.NGAFMultiSelectLOVComponent;
import aero.sita.csp.gui.thickclient.component.lov.SITAGUILOVComponent;
import aero.sita.csp.gui.thickclient.componentextension.mandatory.ui.SlantLineUI;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;

/**
 * 
 * <pre>
 * <b>Description : </b>
 * This class is a Screen Helper.
 * Helper to render the Standby Priority screen.
 * @version $Revision: 1 $ $Date: 2011-12-05 10:06:12 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class StandbyPriorityScreenHelper {
    /**
     * color for panel.
     */
    public static final Color PANEL_BLUE = new Color(198, 216, 234);
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(StandbyPriorityScreenHelper.class);
    
    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constant Value 100.
     */
    private static final int HUNDRED = 100;
    /**
     * Constant Value 50.
     */
    private static final int FIFTY = 50;
    /**
     * Constant Value 20.
     */
    private static final int TWENTY = 20;
    /**
     * Constant Value 1.
     */
    private static final int COLUMN_VALUE_ONE = 1;
    /**
     * Constant Value 2.
     */
    private static final int COLUMN_VALUE_TWO = 2;
    /**
     * Constant Value 3.
     */
    private static final int COLUMN_VALUE_THREE = 3;
    
    /**
     * reference for StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method will initialize the Search Flight User Interface. 
     * 
     * </pre>
     */
    public final void updateScreenUI() {
        StandbyPriorityScreenHelper.LOGGER.debug("Entered StandbyPriorityScreenHelper.updateScreenUI");
        controller.getDefaultSPPagination().setVisible(false);
        setButtonOnScreenLoad();
        setComponentBorder();
        setColumnWidth();
        setKeyStroke();
        
        StandbyPriorityScreenHelper.LOGGER.debug("Entered StandbyPriorityScreenHelper.updateScreenUI");
    }

    /**
     * Method to set the button status.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to set the button status.
     * 
     * </pre>
     */
    private void setButtonOnScreenLoad() {
        controller.getSearchSPCreateButton().setEnabled(true);
        controller.getSearchSPCopyButton().setEnabled(false);
        controller.getSearchSPOpenButton().setEnabled(false);
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method will set border for the lov. 
     * 
     * </pre>
     */
    private void setComponentBorder() {
        controller.getSearchSPSubscriberLOV().setBorder(null);
        controller.getParentPanelDSearchTab().setBorder(new LineBorder(PANEL_BLUE, 2));
    }
    /**
     * 
     * <pre>
     * decorateLOVToMandatory.
     * 
     * @param searchSPSubscriberLOV , not null.
     * </pre>
     */
    public static void decorateLOVToMandatory(final SITAGUILOVComponent searchSPSubscriberLOV) {
        
        JTextField textField = searchSPSubscriberLOV.getSearchField();
        JButton button = searchSPSubscriberLOV.getSearchButton();
        Container parent1 = textField.getParent();
        SlantLineUI decoratorUI = new SlantLineUI();
        decoratorUI.setColor(Color.RED);
        JXLayer<JComponent> componentJXLayer = new JXLayer<JComponent>(textField, decoratorUI);
        parent1.add(componentJXLayer);
        parent1.add(button);

    }
    
  
    /**
     * 
     * <pre>
     * decorateLOVToMandatory.
     * 
     * @param searchSPSubscriberLOV , not null.
     * </pre>
     */
 /*  public static void decorateAirlineLOVToMandatory(final  NGAFMultiSelectLOVComponent searchSPAirlinesLOV) {

       JTextPane textField = searchSPAirlinesLOV.getSearchField();
       textField.setPreferredSize(new Dimension(150,40));
       JButton button = searchSPAirlinesLOV.getSearchButton();
       button.setPreferredSize(new Dimension(100,10));
       Container parent2 = textField.getParent();
       SlantLineUI decoratorUI = new SlantLineUI();
       decoratorUI.setColor(Color.RED);
       JXLayer<JComponent> componentJXLayer = new JXLayer<JComponent>(textField, decoratorUI);
       parent2.add(componentJXLayer);
       parent2.add(button);

   }*/
    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'controller' attribute value.
     * 
     * @return controller , null if not found.
     * </pre>
     */
    public final StandbyPriorityController getController() {
        return controller;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'controller' attribute value.
     * 
     * @param controllerParam , may be null.
     * </pre>
     */
    public final void setController(final StandbyPriorityController controllerParam) {
        controller = controllerParam;

    }

    /**
     * This method will set the key stroke.
     * 
     * <pre>
     * <b>Description : </b>
     * This method will set the key stroke
     * 
     * </pre>
     */
    private void setKeyStroke() {
        controller.getSearchSPStandardTable().getInputMap(JComponent.WHEN_FOCUSED)
            .put(KeyStroke.getKeyStroke("DELETE"), null);
        controller.getSearchSPStandardTable().getTableHeader().setReorderingAllowed(false);
        controller.getSearchSPStandardTable().getTableHeader().setResizingAllowed(false);

        final Border gridBorder = BorderFactory.createLineBorder(controller.getSearchSPStandardTable().getGridColor());
        controller.getSearchSPStandardTable().setBorder(gridBorder);
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method will set the column width.
     * 
     * </pre>
     */
    private void setColumnWidth() {
        final TableColumn columnNumber = controller.getSearchSPStandardTable().getColumn(COLUMN_VALUE_ONE);
        columnNumber.setPreferredWidth(TWENTY);

        final TableColumn columnAirlineCode = controller.getSearchSPStandardTable().getColumn(COLUMN_VALUE_TWO);
        columnAirlineCode.setPreferredWidth(columnAirlineCode.getPreferredWidth() + FIFTY);

        final TableColumn columnAirlineDescription = controller.getSearchSPStandardTable()
            .getColumn(COLUMN_VALUE_THREE);
        columnAirlineDescription.setPreferredWidth(columnAirlineDescription.getPreferredWidth() + HUNDRED);
    }
}

