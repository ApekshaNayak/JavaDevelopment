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
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.utils.StandbyPriorityUtil;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityRequest;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityQueryType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for New Standby Priority Copy Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class CopyStandbyPriorityListener implements ActionListener {
    /**
     * AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;
    /**
     * AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(CopyStandbyPriorityListener.class);
    /**
     * Controller instance of StandbyPriorityController.
     */
    private StandbyPriorityDetailsController controller;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        CopyStandbyPriorityListener.LOGGER.debug("Enter CopyStandbyPriorityListener:actionPerformed()");
        DcsMessagePanelHandler.clearAllMessages();
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.OPEN_SEARCH, null);
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY,
            StandbyPriorityConstants.YES_VALUE);
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_CREATE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE, null);
        }
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY,
            StandbyPriorityConstants.YES_VALUE);
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_SAVE, null);

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE, null);

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
        }

        final boolean isTabOpened = DcsTabDataContainer.getInstance().isTabAlreadyOpen(
            StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

        String airlineNumber = null;

        try {
            if (isTabOpened) {
                final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();

                final int isCopy = standbyPriorityUtil.confirmDelete("",
                    StandbyPriorityConstants.COPY_WARNING_DIALOG_MSG);

                if (isCopy == 0) {
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_SAVE,
                        StandbyPriorityConstants.YES_VALUE);
                    DcsTabDataContainer.getInstance().showTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                    ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                        SystemConstants.SCREEN_REGISTRY);
                    ModuleDO currentModule = screenRegistry.getCurrentModule();
                    UIBaseController controller = (UIBaseController) currentModule.getController();
                    controller.refreshData();

                }
                else if (isCopy == 1) {
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE,
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
                String currentSubscriberCode = controller.getNewSPSubscriberLOV().getSearchField().getText()
                    .toUpperCase();
                String currentAirlineName = controller.getNewSPAirlineLOV().getSearchField().getText().toUpperCase();

                if (currentSubscriberCode != null && currentAirlineName != null) {

                    if (currentAirlineName.trim().length() == CopyStandbyPriorityListener.AIRLINECODE_LENGTH_TWO) {
                        airlineNumber = populateAirlineNumber(currentAirlineName, true);

                    }

                    if (currentAirlineName.trim().length() == CopyStandbyPriorityListener.AIRLINECODE_LENGTH_THREE) {
                        airlineNumber = populateAirlineNumber(currentAirlineName, false);
                    }
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CURRENT_SUBSCRIBER,
                        currentSubscriberCode);
                    NGAFContextUtil.getApplicationContext()
                        .put(StandbyPriorityConstants.CURRENT_AIRLINE, airlineNumber);
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getStandByPriority(
                        currentSubscriberCode, airlineNumber);
                    createNewTabs(findStandbyPriorityResponseDTO, StandbyPriorityConstants.MODULE_ID,
                        StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SEARCH_COPY_NEW_SAVE_TAB,
                        StandbyPriorityConstants.YES_VALUE);
                }
            }
        }
        catch (final Exception ex) {
            CopyStandbyPriorityListener.LOGGER.error("Error in CopyStandbyPriorityListener:actionPerformed "
                + ex.getMessage());
        }
        CopyStandbyPriorityListener.LOGGER.debug("Exit CopyStandbyPriorityListener:actionPerformed()");
    }

   
    /**
     * This Method is to retrieve the Default Standby priority data.
     * @param subscriberCode , may be null
     * @param airlineNumber , may be null
     * @return findStandbyPriorityResponseDTO , never null
     */
    private FindStandbyPriorityResponse getStandByPriority(final String subscriberCode, final String airlineNumber) {
        CopyStandbyPriorityListener.LOGGER.debug("Enter StandbyPriorityDetailsScreenHelper.getDefaultStandbyPriority");
        try {
            final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = populateFindSPRequestDTO(
                subscriberCode, airlineNumber);
            if (controller.getManageStandbyPriorityProxy() != null) {
                try {
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = controller
                        .getManageStandbyPriorityProxy().findStandbyPriority(findStandbyPriorityRequestDTO);
                    return findStandbyPriorityResponseDTO;
                }
                catch (final CheckInException ex) {
                    CopyStandbyPriorityListener.LOGGER.debug("Error occured" + ex);
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                }
            }
        }
        catch (final Exception ex) {
            CopyStandbyPriorityListener.LOGGER.debug("Error : " + ex.getMessage());
        }
        CopyStandbyPriorityListener.LOGGER.debug("Exit StandbyPriorityDetailsScreenHelper.getDefaultStandbyPriority");
        return null;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request for Find Standby Priority.
     * @param subscriberCode , not null.
     * @param airlineNumber , not null.
     * @return findStandbyPriorityRequestDTO , never null.
     * </pre>
     */
    private FindStandbyPriorityRequest populateFindSPRequestDTO(final String subscriberCode, 
        final String airlineNumber) {
        CopyStandbyPriorityListener.LOGGER.debug("Enter DefaultStandbyPriorityScreenHelper.populateFindSPRequestDTO");

        final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = new FindStandbyPriorityRequest();
        final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();
        final AirlineInfoType airLineInfoDTO = new AirlineInfoType();
        airLineInfoDTO.setSitaNumber(new Long(airlineNumber));
        standbyPriorityQueryDTO.setHandledAirline(airLineInfoDTO);
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(subscriberCode);
        
        standbyPriorityQueryDTO.setSubscriberReference(subscriberIDType);
        findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);
        CopyStandbyPriorityListener.LOGGER.debug("Exit DefaultStandbyPriorityScreenHelper.populateFindSPRequestDTO");

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
        CopyStandbyPriorityListener.LOGGER.debug("Enter CopyDefaultStandbyPriorityListener.createNewTabs");
        if (tabTitle != null) {
            DcsTabDataContainer.getInstance().addTabAndDTO(tabTitle, findStandbyPriorityResponseDTO);
            DcsViewHandler.invokeNewTab(moduleId, tabTitle);
        }
        CopyStandbyPriorityListener.LOGGER.debug("Exit CopyDefaultStandbyPriorityListener.createNewTabs");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to populate airline Number.
     *  Checks for IATA code.
     * @param airlineCode , not null.
     * @param isIATA , not null.
     * @return String , never null.
     * </pre>
     */
    private String populateAirlineNumber(final String airlineCode, final boolean isIATA) {
        String airlineNumber;

        try {
            airlineNumber = controller.getCommonUtilitiesGateWay().fetchAirlineNumber(airlineCode, isIATA);
        }
        catch (Exception ex) {
            airlineNumber = null;
        }

        return airlineNumber;
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
}
