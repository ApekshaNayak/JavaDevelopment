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

import javax.swing.JDialog;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for New Standby Priority Description Cancel Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class CancelDescriptionListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(CancelDescriptionListener.class);
    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityDetailsController controller;
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
        CancelDescriptionListener.LOGGER.info("Enter CancelDescriptionListener:actionPerformed()");

        DcsMessagePanelHandler.clearAllMessages();
        priorityDialogBox.dispose();

        CancelDescriptionListener.LOGGER.info("Exit CancelDescriptionListener:actionPerformed()");
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
     * Get the 'priorityDialogBox' attribute value.
     * 
     * @return priorityDialogBox , null if not found.
     * </pre>
     */
    public JDialog getPriorityDialogBox() {
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
    public void setPriorityDialogBox(final JDialog priorityDialogBoxParam) {
        priorityDialogBox = priorityDialogBoxParam;
    }
}
