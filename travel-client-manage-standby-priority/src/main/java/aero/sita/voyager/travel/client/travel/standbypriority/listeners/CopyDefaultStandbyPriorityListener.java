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

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.common.ScreenRegistry;
import aero.sita.csp.gui.thickclient.common.SystemConstants;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.csp.gui.thickclient.coreapi.base.ModuleDO;
import aero.sita.voyager.dcs.client.common.base.controller.UIBaseController;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsViewHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.utils.StandbyPriorityUtil;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityRequest;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityQueryType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Copy Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class CopyDefaultStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
        .getLogger(CopyDefaultStandbyPriorityListener.class);

    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * Copy Button.
     */
    private JButton defaultSPCopyButton;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        CopyDefaultStandbyPriorityListener.LOGGER.debug("Enter CopyDefaultStandbyPriorityListener:actionPerformed()");

        DcsMessagePanelHandler.clearAllMessages();

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT,
            StandbyPriorityConstants.YES_VALUE);

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_CREATE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE, null);
        }
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT,
            StandbyPriorityConstants.YES_VALUE);
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.OPEN_SEARCH, null);

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE, null);

        boolean isTabOpened = DcsTabDataContainer.getInstance().isTabAlreadyOpen(
            StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

        try {
            if (isTabOpened) {
                final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();
                final int isCopy = standbyPriorityUtil.confirmDelete("",
                    StandbyPriorityConstants.COPY_WARNING_DIALOG_MSG);

                if (isCopy == 0) {
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE,
                        StandbyPriorityConstants.YES_VALUE);

                    DcsTabDataContainer.getInstance().showTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                    ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                        SystemConstants.SCREEN_REGISTRY);
                    ModuleDO currentModule = screenRegistry.getCurrentModule();
                    UIBaseController controller = (UIBaseController) currentModule.getController();
                    controller.refreshData();
                }
                else if(isCopy == 1) {
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE,
                        StandbyPriorityConstants.YES_VALUE);
                    DcsTabDataContainer.getInstance().showTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                    ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                        SystemConstants.SCREEN_REGISTRY);
                    ModuleDO currentModule = screenRegistry.getCurrentModule();
                    UIBaseController controller = (UIBaseController) currentModule.getController();
                    controller.refreshData();
                }
            }
            else {
                final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getDefaultStandbyPriority();

                createNewTabs(findStandbyPriorityResponseDTO, StandbyPriorityConstants.MODULE_ID,
                    StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
            }
        }
        catch (Exception ex) {
            CopyDefaultStandbyPriorityListener.LOGGER
                .error("Error in CopyDefaultStandbyPriorityListener:actionPerformed " + ex.getMessage());
        }
        CopyDefaultStandbyPriorityListener.LOGGER.debug("Exit CopyDefaultStandbyPriorityListener:actionPerformed()");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to retrieve the Default Standby priority data.
     * 
     * @return FindStandbyPriorityResponseType , never null.
     * </pre>
     */
    private FindStandbyPriorityResponse getDefaultStandbyPriority() {
        CopyDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

        try {
            final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = populateFindSPRequestDTO();

            if (controller.getManageStandbyPriorityProxy() != null) {
                try {
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO 
                        = controller.getManageStandbyPriorityProxy().findStandbyPriority(findStandbyPriorityRequestDTO);
                    return findStandbyPriorityResponseDTO;
                }
                catch (CheckInException ex) {
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                }
            }
        }
        catch (Exception ex) {
            CopyDefaultStandbyPriorityListener.LOGGER.debug("Error : " + ex.getMessage());
        }

        CopyDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

        return null;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request for Find Standby Priority service to get default standby priority.
     * 
     * @return findStandbyPriorityRequestDTO , never null.
     * </pre>
     */
    private FindStandbyPriorityRequest populateFindSPRequestDTO() {
        CopyDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CopyDefaultStandbyPriorityListener.populateFindSPRequestDTO");

        final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = new FindStandbyPriorityRequest();
        final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();

        findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);
        CopyDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CopyDefaultStandbyPriorityListener.populateFindSPRequestDTO");

        return findStandbyPriorityRequestDTO;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to open new tabs for the Standby Priority.
     * 
     * @param findStandbyPriorityResponseDTO is, not null
     * @param moduleId is, not null
     * @param tabTitle is, not null
     * </pre>
     */
    private void createNewTabs(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO,
        final String moduleId, final String tabTitle) {
        CopyDefaultStandbyPriorityListener.LOGGER.debug("Enter CopyDefaultStandbyPriorityListener.createNewTabs");

        if (tabTitle != null) {

            DcsTabDataContainer.getInstance().addTabAndDTO(tabTitle, findStandbyPriorityResponseDTO);
            DcsViewHandler.invokeNewTab(moduleId, tabTitle);
        }

        CopyDefaultStandbyPriorityListener.LOGGER.debug("Exit CopyDefaultStandbyPriorityListener.createNewTabs");
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
     * Get the 'defaultSPCopyButton' attribute value.
     * 
     * @return defaultSPCopyButton , null if not found.
     * </pre>
     */
    public JButton getDefaultSPCopyButton() {
        return defaultSPCopyButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPCopyButton' attribute value.
     * 
     * @param defaultSPCopyButtonParam , may be null.
     * </pre>
     */
    public void setDefaultSPCopyButton(final JButton defaultSPCopyButtonParam) {
        defaultSPCopyButton = defaultSPCopyButtonParam;
    }
}
