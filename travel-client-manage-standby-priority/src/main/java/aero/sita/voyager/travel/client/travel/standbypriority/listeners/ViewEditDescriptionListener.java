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
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.filtertable.NGAFFilterTablePanel;
import aero.sita.voyager.ngafoundation.client.parentframe.util.NGAFParentFrameUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.utils.StandbyPriorityUtil;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority View/Edit Description Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class ViewEditDescriptionListener extends JFrame implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(ViewEditDescriptionListener.class);
    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constant 3.
     */
    private static final int THREE = 3;
    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;
    /**
     * View and edit button.
     */
    private JButton defaultSPViewEditDescButton;
    /**
     * NGAFFilterTablePanel in Default Standby Priority Screen.
     */
    private NGAFFilterTablePanel defaultSPFilterTablePanel;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        ViewEditDescriptionListener.LOGGER.info("Enter ViewEditDescriptionListener:actionPerformed()");
        String priorityCode = null;

        DcsMessagePanelHandler.clearAllMessages();

        if (controller.getDefaultSPFilterTablePanel() != null
            && controller.getDefaultSPFilterTablePanel().getTable() != null) {
            final int selectedRowCount = controller.getDefaultSPFilterTablePanel().getTable().getSelectedRowCount();

            if (selectedRowCount == 1) {
                final int selectedRow = controller.getDefaultSPFilterTablePanel().getTable().getSelectedRow();

                if (controller.getDefaultSPFilterTablePanel().getTable().getValueAt(selectedRow, 2) != null) {
                    priorityCode = controller.getDefaultSPFilterTablePanel().getTable().getValueAt(selectedRow, 2)
                        .toString();
                }

                final DefaultTableModel dm = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();

                JFrame parentFrame = NGAFParentFrameUtil.getParentFrame().getNGAFParentFrame();
                final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil(parentFrame, dm.getValueAt(selectedRow, THREE).toString(),
                    priorityCode, controller, selectedRow);
                
                //if (standbyPriorityUtil != null) {
                    ViewEditDescriptionListener.LOGGER.info("ViewEditDescriptionListener:actionPerformed()" 
                        + standbyPriorityUtil);
                //}
            }
        }

        ViewEditDescriptionListener.LOGGER.info("Exit ViewEditDescriptionListener:actionPerformed()");
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
     * Set the 'defaultSPFilterTablePanel' attribute value.
     * 
     * @param defaultSPFilterTablePanelParam , may be null.
     * </pre>
     */
    public final void setDefaultSPFilterTablePanel(final NGAFFilterTablePanel defaultSPFilterTablePanelParam) {
        defaultSPFilterTablePanel = defaultSPFilterTablePanelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPViewEditDescButton' attribute value.
     * 
     * @return defaultSPViewEditDescButton , null if not found.
     * </pre>
     */
    public final JButton getDefaultSPViewEditDescButton() {
        return defaultSPViewEditDescButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPViewEditDescButton' attribute value.
     * 
     * @param defaultSPViewEditDescButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPViewEditDescButton(final JButton defaultSPViewEditDescButtonParam) {
        defaultSPViewEditDescButton = defaultSPViewEditDescButtonParam;
    }
}
