/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.validators.StandbyPriorityValidator;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class SaveDescriptionListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(SaveDescriptionListener.class);
    
    /**
     * constant 3.
     */
    private static final int THREE = 3;
    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;
    /**
     * Seleted row.
     */
    private int selectedRow;
    /**
     * Description.
     */
    private String descriptionText;
    /**
     * TextArea.
     */
    private JTextArea priorityTextArea;
    /**
     * Dialog.
     */
    private JDialog priorityDialogBox;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        SaveDescriptionListener.LOGGER.info("Enter SaveDescriptionListener:actionPerformed()");
        DcsMessagePanelHandler.clearAllMessages();

       // boolean isValid = true;
        final StandbyPriorityValidator standbyPriorityValidator = new StandbyPriorityValidator();

        final DefaultTableModel defaultTableModel = (DefaultTableModel) controller.getDefaultSPFilterTablePanel()
            .getModel();
        final NGAFStandardTable defaultSPStandardTable = (NGAFStandardTable) controller
            .getDefaultSPFilterTablePanel().getTable();

        boolean isValid = standbyPriorityValidator.validatePriorityStatusDesc(priorityTextArea.getText().toString(),
            defaultSPStandardTable);

        if (isValid) {
            defaultTableModel.setValueAt(priorityTextArea.getText(), selectedRow, THREE);
            priorityDialogBox.dispose();
        }

        SaveDescriptionListener.LOGGER.info("Exit SaveDescriptionListener:actionPerformed()");
    }

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
     * <pre>
     * <b>Description : </b>
     * Get the 'priorityDialogBox' attribute value.
     * 
     * @return priorityDialogBox , null if not found.
     * </pre>
     */
    public final JDialog getPriorityDialogBox() {
        return priorityDialogBox;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'priorityDialogBox' attribute value.
     * 
     * @param priorityDialogBoxParam , may be null.
     * </pre>
     */
    public final void setPriorityDialogBox(final JDialog priorityDialogBoxParam) {
        priorityDialogBox = priorityDialogBoxParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'priorityTextArea' attribute value.
     * 
     * @return priorityTextArea , null if not found.
     * </pre>
     */

    public final JTextArea getPriorityTextArea() {
        return priorityTextArea;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'priorityTextArea' attribute value.
     * 
     * @param priorityTextAreaParam , may be null.
     * </pre>
     */
    public final void setPriorityTextArea(final JTextArea priorityTextAreaParam) {
        priorityTextArea = priorityTextAreaParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'descriptionText' attribute value.
     * 
     * @return descriptionText , null if not found.
     * </pre>
     */

    public final String getDescriptionText() {
        return descriptionText;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'descriptionText' attribute value.
     * 
     * @param descriptionTextParam , may be null.
     * </pre>
     */
    public final void setDescriptionText(final String descriptionTextParam) {
        descriptionText = descriptionTextParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'selectedRow' attribute value.
     * 
     * @return selectedRow , null if not found.
     * </pre>
     */

    public final int getSelectedRow() {
        return selectedRow;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'selectedRow' attribute value.
     * 
     * @param selectedRowParam , may be null.
     * </pre>
     */
    public final void setSelectedRow(final int selectedRowParam) {
        selectedRow = selectedRowParam;
    }
}

