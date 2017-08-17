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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.filtertable.NGAFFilterTablePanel;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Add Button Action Listener.
 *
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class AddDefaultStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
         .getLogger(AddDefaultStandbyPriorityListener.class);
    /**
     * Controller instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;
    /**
     * Add Rows Button.
     */
    private JButton defaultSPAddRowsButton;
    /**
     * NGAFFilterTablePanel for Default Standby Priority Filter Table.
     */
    private NGAFFilterTablePanel defaultSPFilterTablePanel;
    /**
     * No Of Rows Combobox.
     */
    private JComboBox defaultSPNoOfRowsCombobox;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     *
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        AddDefaultStandbyPriorityListener.LOGGER.debug("Enter "
            + "AddDefaultStandbyPriorityListener:actionPerformed()");

        DcsMessagePanelHandler.clearAllMessages();
        controller.getDefaultSPFilterTablePanel().clearTextOfFilterRow();

        if (controller.getDefaultSPFilterTablePanel() != null
            && controller.getDefaultSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel dm = (DefaultTableModel) controller
                .getDefaultSPFilterTablePanel().getModel();
            final int addRows = controller.
            getDefaultSPNoOfRowsCombobox().getSelectedIndex();

            if (dm != null) {
                if (dm.getRowCount() + addRows < StandbyPriorityConstants.MAX_ROW_COUNT) {
                    final int rowCount = dm.getRowCount();
                    final int appendRow = rowCount + 1;
                    final Object[][] dataArray = new Object[][] {{"", "", "Yes",
                            "Optional", "", "Yes", "", "Yes", "", "Yes", "", "Yes", "Yes", }, };

                    for (int i = appendRow; i <= appendRow + addRows; i++) {
                        dm.addRow(dataArray[0]);
                    }
                }
                else {
                    DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10837");
                }
            }
        }

        AddDefaultStandbyPriorityListener.LOGGER.debug("Exit "
            + "AddDefaultStandbyPriorityListener:actionPerformed()");
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
    public final void setController(
        final StandbyPriorityController controllerParam) {
        controller = controllerParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPNoOfRowsCombobox' attribute value.
     *
     * @return defaultSPNoOfRowsCombobox , null if not found.
     * </pre>
     */

    public final JComboBox getDefaultSPNoOfRowsCombobox() {
        return defaultSPNoOfRowsCombobox;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPNoOfRowsCombobox' attribute value.
     * @param defaultSPNoOfRowsComboboxParam , not null.
     * </pre>
     */
    public final void setDefaultSPNoOfRowsCombobox(
        final JComboBox defaultSPNoOfRowsComboboxParam) {
        defaultSPNoOfRowsCombobox = defaultSPNoOfRowsComboboxParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPAddRowsButton' attribute value.
     *
     * @return defaultSPAddRowsButton , null if not found.
     * </pre>
     */

    public final JButton getDefaultSPAddRowsButton() {
        return defaultSPAddRowsButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPFilterTablePanel' attribute value.
     *
     * @return defaultSPFilterTablePanel , null if not found.
     * </pre>
     */

    public final NGAFFilterTablePanel getDefaultSPFilterTablePanel() {
        return defaultSPFilterTablePanel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPAddRowsButton' attribute value.
     *
     * @param defaultSPAddRowsButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPAddRowsButton(
        final JButton defaultSPAddRowsButtonParam) {
        defaultSPAddRowsButton = defaultSPAddRowsButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPFilterTablePanel' attribute value.
     *
     * @param defaultSPFilterTablePanelParam , may be null.
     * </pre>
     */
    public final void setDefaultSPFilterTablePanel(
        final NGAFFilterTablePanel defaultSPFilterTablePanelParam) {
        defaultSPFilterTablePanel = defaultSPFilterTablePanelParam;
    }
}
