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

//import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
//import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
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
public class SearchStandbyPriorityTableRowSelectionListener implements ListSelectionListener {

    /**
     * Logger instance for the class.
     */
    //private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
    //    .getLogger(SearchStandbyPriorityTableRowSelectionListener.class);

    /**
     * reference for StandbyPriorityController.
     */
    private StandbyPriorityController controller;

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

    /**
     * <pre>
     * <b>Description : </b>
     * This method is listener of value changes of the result panel list items.
     * 
     * @param selectionEventParam , not null.
     * </pre>
     */
    public final void valueChanged(final ListSelectionEvent selectionEventParam) {
        if (selectionEventParam.getValueIsAdjusting()) {
            /*
            if (controller.getSearchSPFilterTablePanel().getTable().getSelectedRowCount() > 0) {
                controller.getSearchSPOpenButton().setEnabled(true);
            }
            else {
                controller.getSearchSPOpenButton().setEnabled(false);
            }
            */
            
            /*if (controller.getSearchSPFilterTablePanel().getTable().getSelectedRowCount() == 1) {
                controller.getSearchSPCopyButton().setEnabled(true);
            }
            else {
                controller.getSearchSPCopyButton().setEnabled(false);
            }*/
            if (controller.getSearchSPFilterTablePanel().getTable().getSelectedRowCount() == 1) {
                controller.getSearchSPCopyButton().setEnabled(true);
                controller.getSearchSPOpenButton().setEnabled(true);
                
            }
            else {
                controller.getSearchSPCopyButton().setEnabled(false);
                controller.getSearchSPOpenButton().setEnabled(false);
            }
            if (controller.getSearchSPFilterTablePanel().getTable().getSelectedRowCount() > 1) {
                controller.getSearchSPOpenButton().setEnabled(true);
            }
        }
    }
}

