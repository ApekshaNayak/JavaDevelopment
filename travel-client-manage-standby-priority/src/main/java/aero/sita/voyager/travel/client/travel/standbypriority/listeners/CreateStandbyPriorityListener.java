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

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.common.ScreenRegistry;
import aero.sita.csp.gui.thickclient.common.SystemConstants;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.csp.gui.thickclient.coreapi.base.ModuleDO;
import aero.sita.csp.gui.thickclient.util.NGAFModuleInvocationUtil;
import aero.sita.voyager.dcs.client.common.base.controller.UIBaseController;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.utils.StandbyPriorityUtil;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class CreateStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(CreateStandbyPriorityListener.class);

    /**
     * Controller instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * <pre>
	 * <b>Description : </b>
	 * Action Performed by Assign button.
	 * 
	 * @param actionEvent , not null.
	 * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
    	DcsMessagePanelHandler.clearAllMessages();

        if (controller.getDefaultSPCreateButton().isEnabled()) {
            //controller.getManageStandbyPriorityTabbedPane().getTabComponentAt(0);
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10889");
        }
        else {

            CreateStandbyPriorityListener.LOGGER.info("Enter CreateStandbyPriorityListener:actionPerformed()");
            DcsMessagePanelHandler.clearAllMessages();
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.OPEN_SEARCH) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.OPEN_SEARCH, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_CREATE) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
            }
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY,
                StandbyPriorityConstants.YES_VALUE);
            boolean isTabOpened = DcsTabDataContainer.getInstance().isTabAlreadyOpen(
                StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

            try {
                if (isTabOpened) {
                    final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();
                    // TabDataContainer.getInstance().showTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                    final int isCreate = standbyPriorityUtil.confirmDelete("",
                        StandbyPriorityConstants.COPY_WARNING_DIALOG_MSG);

                    if (isCreate == 0) {

                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE,
                            StandbyPriorityConstants.YES_VALUE);
                        DcsTabDataContainer.getInstance().showTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                        ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                            SystemConstants.SCREEN_REGISTRY);
                        ModuleDO currentModule = screenRegistry.getCurrentModule();
                        UIBaseController controller = (UIBaseController) currentModule.getController();
                        controller.refreshData();
                    }
                    else if (isCreate == 1) {
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                            StandbyPriorityConstants.YES_VALUE);
                        DcsTabDataContainer.getInstance().closeTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE,
                            StandbyPriorityConstants.YES_VALUE);
                        NGAFModuleInvocationUtil.invokeModuleInNextTab(StandbyPriorityConstants.MODULE_ID,
                            StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

                    }
                }
                else {
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE,
                        StandbyPriorityConstants.YES_VALUE);
                    NGAFModuleInvocationUtil.invokeModuleInNextTab(StandbyPriorityConstants.MODULE_ID,
                        StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

                }

            }
            catch (Exception ex) {
                // e.printStackTrace();
                CreateStandbyPriorityListener.LOGGER.error("Error in CreateStandbyPriorityListener:actionPerformed "
                    + ex.getMessage());
            }
        }

        CreateStandbyPriorityListener.LOGGER.info("Exit CreateStandbyPriorityListener:actionPerformed()");
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
}
