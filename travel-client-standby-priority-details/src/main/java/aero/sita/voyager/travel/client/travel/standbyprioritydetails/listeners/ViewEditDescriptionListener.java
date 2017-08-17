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
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.filtertable.NGAFFilterTablePanel;
import aero.sita.voyager.ngafoundation.client.parentframe.util.NGAFParentFrameUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.utils.StandbyPriorityUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for New Standby Priority View/Edit Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class ViewEditDescriptionListener extends JFrame implements ActionListener {

    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
        .getLogger(ViewEditDescriptionListener.class);
    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;
    /**
     * THREE.
     */
    private static final int THREE = 3;
    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityDetailsController controller;
    /**
     * View and edit button.
     */
    private JButton newSPViewEditDescButton;
    /**
     * NGAFFilterTablePanel in New Standby Priority Screen.
     */
    private NGAFFilterTablePanel newSPFilterTablePanel;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        ViewEditDescriptionListener.LOGGER
            .info("Enter ViewEditDescriptionListener:actionPerformed()");
        String priorityCode = null;

        DcsMessagePanelHandler.clearAllMessages();

        if (controller.getNewSPFilterTablePanel() != null
            && controller.getNewSPFilterTablePanel().getTable() != null) {
            final int selectedRowCount = controller.getNewSPFilterTablePanel().getTable()
                .getSelectedRowCount();

            if (selectedRowCount == 1) {
                final int selectedRow = controller.getNewSPFilterTablePanel().getTable()
                    .getSelectedRow();

                if (controller.getNewSPFilterTablePanel().getTable().getValueAt(selectedRow, 2) != null) {
                    priorityCode = controller.getNewSPFilterTablePanel().getTable().getValueAt(
                        selectedRow, 2).toString();
                }

                final DefaultTableModel dm = (DefaultTableModel) controller
                    .getNewSPFilterTablePanel().getModel();

                JFrame parentFrame = NGAFParentFrameUtil.getParentFrame().getNGAFParentFrame();
                final StandbyPriorityUtil newStandbyPriorityUtil = new StandbyPriorityUtil(
                    parentFrame, dm.getValueAt(selectedRow, THREE).toString(), priorityCode, controller, selectedRow);

                //if (newStandbyPriorityUtil != null) {
                    ViewEditDescriptionListener.LOGGER
                        .info("ViewEditDescriptionListener:actionPerformed()"
                            + newStandbyPriorityUtil);
               // }
            } 
            else {
                DcsMessagePanelHandler.clearAllMessages();
                DcsMessagePanelHandler
                    .handleClientSideErrorMsg(StandbyPriorityConstants.ERROR_MESSAGE_1);
            }
        }

        ViewEditDescriptionListener.LOGGER
            .info("Exit ViewEditDescriptionListener:actionPerformed()");
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
     * Get the 'newSPFilterTablePanel' attribute value.
     * 
     * @return newSPFilterTablePanel , null if not found.
     * </pre>
     */
    public NGAFFilterTablePanel getNewSPFilterTablePanel() {
        return newSPFilterTablePanel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPFilterTablePanel' attribute value.
     * 
     * @param newSPFilterTablePanelParam , may be null.
     * </pre>
     */
    public void setNewSPFilterTablePanel(final NGAFFilterTablePanel newSPFilterTablePanelParam) {
        newSPFilterTablePanel = newSPFilterTablePanelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPViewEditDescButton' attribute value.
     * 
     * @return newSPViewEditDescButton , null if not found.
     * </pre>
     */
    public JButton getNewSPViewEditDescButton() {
        return newSPViewEditDescButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPViewEditDescButton' attribute value.
     * 
     * @param newSPViewEditDescButtonParam , may be null.
     * </pre>
     */
    public void setNewSPViewEditDescButton(final JButton newSPViewEditDescButtonParam) {
        newSPViewEditDescButton = newSPViewEditDescButtonParam;
    }
}
