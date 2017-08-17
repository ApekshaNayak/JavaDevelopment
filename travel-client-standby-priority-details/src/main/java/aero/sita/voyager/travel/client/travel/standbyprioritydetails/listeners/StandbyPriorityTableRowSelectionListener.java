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

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is the action listener for the result panel.
 * Action Listener for a checked row.
 * @version $Revision: 1 $ $Date: 2011-10-28 01:58:56 PM $
 * @author $Author: bikoo.changmai $ 
 * </pre>
 */
public class StandbyPriorityTableRowSelectionListener implements ListSelectionListener {

    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
        .getLogger(StandbyPriorityTableRowSelectionListener.class);

    /**
     * reference for StandbyPriorityController.
     */
    private StandbyPriorityDetailsController controller;

    /**
     * <pre>
     * <b>Description : </b>
     * This method will be called when the result panel list items are selected.
     * 
     * @param selectionEventParam , not null.
     * </pre>
     */
    public final void valueChanged(final ListSelectionEvent selectionEventParam) {
        StandbyPriorityTableRowSelectionListener.LOGGER
            .debug("Enter StandbyPriorityTableRowSelectionListener.valueChanged");
        DcsMessagePanelHandler.clearAllMessages();

        if (selectionEventParam.getValueIsAdjusting()) {
            if (controller.getNewSPFilterTablePanel().getTable().getSelectedRowCount() > 0) {
                controller.getNewSPDeleteSelRowsButton().setEnabled(true);
            }
            else {
                controller.getNewSPDeleteSelRowsButton().setEnabled(false);
            }

            if (controller.getNewSPFilterTablePanel().getTable().getSelectedRowCount() == 1) {
                controller.getNewSPViewEditDescButton().setEnabled(true);
            }
            else {
                controller.getNewSPViewEditDescButton().setEnabled(false);
            }
        }

        StandbyPriorityTableRowSelectionListener.LOGGER
            .debug("Exit StandbyPriorityTableRowSelectionListener.valueChanged");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'controller' attribute value.
     * 
     * @return controller , null if not found.
     * </pre>
     */

    public StandbyPriorityDetailsController getController() {
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
    public void setController(final StandbyPriorityDetailsController controllerParam) {
        this.controller = controllerParam;
    }
}
