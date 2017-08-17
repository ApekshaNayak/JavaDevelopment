/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.filtertable.NGAFFilterTablePanel;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for New Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-09 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class AddStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(AddStandbyPriorityListener.class);
    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityDetailsController controller;
    /**
     * Add Rows Button.
     */
    private JButton newSPAddRowsButton;
    /**
     * NGAFFilterTablePanel for New Standby Priority Filter Table.
     */
    private NGAFFilterTablePanel newSPFilterTablePanel;
    /**
     * No Of Rows Combobox.
     */
    private JComboBox newSPNoOfRowsCombobox;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        AddStandbyPriorityListener.LOGGER.debug("Enter AddStandbyPriorityListener:actionPerformed()");
        DcsMessagePanelHandler.clearAllMessages();
        controller.getNewSPFilterTablePanel().clearTextOfFilterRow();
        if (controller.getNewSPFilterTablePanel() != null && controller.getNewSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();
            final int addRows = controller.getNewSPNoOfRowsCombobox().getSelectedIndex();

            if (dm != null) {
                if (dm.getRowCount() + addRows < StandbyPriorityConstants.MAX_ROW_COUNT) {
                    final int rowCount = dm.getRowCount();
                    final int appendRow = rowCount + 1;
                    final Object[][] dataArray = new Object[][] { { "", "", "Yes", "Optional", "", "Yes", "", "Yes",
                            "", "Yes", "", "Yes", "Yes", }, };

                    for (int i = appendRow; i <= appendRow + addRows; i++) {
                        dm.addRow(dataArray[0]);
                    }
                }
                else {
                    DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10837");
                }
            }
        }

        AddStandbyPriorityListener.LOGGER.debug("Exit AddStandbyPriorityListener:actionPerformed()");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'controller' attribute value.
     * 
     * @return controller , null if not found.
     * </pre>
     */
    public final StandbyPriorityDetailsController getController() {
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
    public final void setController(final StandbyPriorityDetailsController controllerParam) {
        controller = controllerParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPNoOfRowsCombobox' attribute value.
     * 
     * @return newSPNoOfRowsCombobox , null if not found.
     * </pre>
     */

    public final JComboBox getnewSPNoOfRowsCombobox() {
        return newSPNoOfRowsCombobox;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPNoOfRowsCombobox' attribute value.
     * 
     * @param newSPNoOfRowsComboboxParam , may be null.
     * </pre>
     */
    public final void setNewSPNoOfRowsCombobox(final JComboBox newSPNoOfRowsComboboxParam) {
        this.newSPNoOfRowsCombobox = newSPNoOfRowsComboboxParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPAddRowsButton' attribute value.
     * 
     * @return newSPAddRowsButton , null if not found.
     * </pre>
     */

    public final JButton getNewSPAddRowsButton() {
        return newSPAddRowsButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPFilterTablePanel' attribute value.
     * 
     * @return newSPFilterTablePanel , null if not found.
     * </pre>
     */

    public final NGAFFilterTablePanel getNewSPFilterTablePanel() {
        return newSPFilterTablePanel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPAddRowsButton' attribute value.
     * 
     * @param newSPAddRowsButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPAddRowsButton(final JButton newSPAddRowsButtonParam) {
        newSPAddRowsButton = newSPAddRowsButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPFilterTablePanel' attribute value.
     * 
     * @param newSPFilterTablePanelParam , may be null.
     * </pre>
     */
    public final void setNewSPFilterTablePanel(final NGAFFilterTablePanel newSPFilterTablePanelParam) {
        newSPFilterTablePanel = newSPFilterTablePanelParam;
    }
}
