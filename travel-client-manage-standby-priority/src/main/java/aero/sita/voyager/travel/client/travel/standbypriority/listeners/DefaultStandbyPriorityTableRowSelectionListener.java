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

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is the action listener for the result panel.
 * Action Listener for a checked row.
 * @version $Revision: 1 $ $Date: 2011-10-28 01:58:56 PM $
 * @author $Author: bikoo.changmai $ 
 * </pre>
 */
public class DefaultStandbyPriorityTableRowSelectionListener implements ListSelectionListener {

    /**
     * Logger instance for the class.
     */
    //private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
    //    .getLogger(DefaultStandbyPriorityTableRowSelectionListener.class);

    /**
     * reference for StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * <pre>
     * <b>Description : </b>
     * This method will be called when the result panel list items are selected.
     * 
     * @param selectionEventParam , not null.
     * </pre>
     */
    public final void valueChanged(final ListSelectionEvent selectionEventParam) {
        if (selectionEventParam.getValueIsAdjusting()) {
            if (controller.getDefaultSPFilterTablePanel().getTable().getSelectedRowCount() > 0) {
                controller.getDefaultSPDeleteSelRowsButton().setEnabled(true);
            }
            else {
                controller.getDefaultSPDeleteSelRowsButton().setEnabled(false);
            }

            if (controller.getDefaultSPFilterTablePanel().getTable().getSelectedRowCount() == 1) {
                controller.getDefaultSPViewEditDescButton().setEnabled(true);
            }
            else {
                controller.getDefaultSPViewEditDescButton().setEnabled(false);
            }
        }
    }
    
    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'controller' attribute value.
     * 
     * @return controller , null if not found.
     * </pre>
     */

    public StandbyPriorityController getController() {
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
    public void setController(final StandbyPriorityController controllerParam) {
        controller = controllerParam;
    }
}

