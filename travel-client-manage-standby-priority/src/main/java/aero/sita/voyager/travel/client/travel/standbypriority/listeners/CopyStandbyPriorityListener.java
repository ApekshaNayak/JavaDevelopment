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
import aero.sita.voyager.dcs.client.common.base.controller.UIBaseController;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsViewHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.utils.StandbyPriorityUtil;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityRequest;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityQueryType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class CopyStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(CopyStandbyPriorityListener.class);

    /**
     * constant AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;

    /**
     * constant AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;

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
        CopyStandbyPriorityListener.LOGGER.info("Enter CopyStandbyPriorityListener:actionPerformed()");
        //String airlineCode = null;
        //String airlineNumber = null;
        DcsMessagePanelHandler.clearAllMessages();
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.OPEN_SEARCH, null);
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH,
            StandbyPriorityConstants.YES_VALUE);
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_CREATE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE, null);
        }
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH,
            StandbyPriorityConstants.YES_VALUE);

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_SAVE, null);

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE, null);

        boolean isTabOpened = DcsTabDataContainer.getInstance().isTabAlreadyOpen(
            StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

        try {
            String airlineCode;
            String airlineNumber = null;
            if (isTabOpened) {
                final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();
                final int isCopy = standbyPriorityUtil.confirmDelete("",
                    StandbyPriorityConstants.COPY_WARNING_DIALOG_MSG);

                if (isCopy == 0) {

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_SAVE,
                        StandbyPriorityConstants.YES_VALUE);
                    DcsTabDataContainer.getInstance().showTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
                    ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                        SystemConstants.SCREEN_REGISTRY);
                    ModuleDO currentModule = screenRegistry.getCurrentModule();
                    UIBaseController controller = (UIBaseController) currentModule.getController();
                    controller.refreshData();

                }
                else if (isCopy == 1) {
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE,
                        StandbyPriorityConstants.YES_VALUE);
                    String subscriberCode = NGAFContextUtil.getApplicationContext()
                        .get(StandbyPriorityConstants.MANAGE_SEARCHED_SUBSCRIBER).toString();
                    // controller.getSearchSPSubscriberLOV().getSelectedResult().toUpperCase();

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SEARCH_SUBSCRIBER,
                        subscriberCode);
                    int selectedRow = controller.getSearchSPFilterTablePanel().getStandardTable().getSelectedRow();
                    airlineCode = controller.getSearchSPFilterTablePanel().getStandardTable()
                        .getValueAt(selectedRow, 2).toString().toUpperCase();

                    if (subscriberCode != null && airlineCode != null) {
                        if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
                            airlineNumber = populateAirlineNumber(airlineCode, true);
                        }

                        if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
                            airlineNumber = populateAirlineNumber(airlineCode, false);
                        }
                    }
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SEARCH_AIRLINE_NUMBER,
                        airlineNumber);
                    DcsTabDataContainer.getInstance().showTab(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

                    ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                        SystemConstants.SCREEN_REGISTRY);
                    ModuleDO currentModule = screenRegistry.getCurrentModule();
                    UIBaseController controller = (UIBaseController) currentModule.getController();
                    controller.refreshData();
                }
            }
            else {
                String subscriberCode = NGAFContextUtil.getApplicationContext()
                    .get(StandbyPriorityConstants.MANAGE_SEARCHED_SUBSCRIBER).toString();
                // controller.getSearchSPSubscriberLOV().getSelectedResult().toUpperCase();

                int row = controller.getSearchSPStandardTable().getSelectedRow();
                airlineCode = controller.getSearchSPStandardTable().getStringAt(row, 2).toUpperCase();
                if (subscriberCode != null) {
                    if (airlineCode != null) {
                        if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
                            airlineNumber = populateAirlineNumber(airlineCode, true);
                        }

                        if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
                            airlineNumber = populateAirlineNumber(airlineCode, false);
                        }
                    }
                    if (airlineNumber != null) {
                        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getStandbyPriority(
                            subscriberCode, airlineNumber);
                        if (findStandbyPriorityResponseDTO != null) {
                            CopyStandbyPriorityListener.LOGGER.info("findStandbyPriorityResponseDTO");
                        }

                        createNewTabs(findStandbyPriorityResponseDTO, StandbyPriorityConstants.MODULE_ID,
                            StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);

                    }
                }
            }
        }
        catch (Exception ex) {
            CopyStandbyPriorityListener.LOGGER.error("Error in CopyStandbyPriorityListener:actionPerformed "
                + ex.getMessage());
        }

        CopyStandbyPriorityListener.LOGGER.info("Exit CopyStandbyPriorityListener:actionPerformed()");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to retrieve the Default Standby priority data.
     * @param subscriberCode , not null.
     * @param airlineNumber , not null.
     * @return FindStandbyPriorityResponseType , never null.
     * </pre>
     */
    private FindStandbyPriorityResponse getStandbyPriority(final String subscriberCode, final String airlineNumber) {
        LOGGER.debug("Enter CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

        try {
            final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = populateFindSPRequestDTO(
                subscriberCode, airlineNumber);

            if (controller.getManageStandbyPriorityProxy() != null) {
                try {
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = controller
                        .getManageStandbyPriorityProxy().findStandbyPriority(findStandbyPriorityRequestDTO);
                    return findStandbyPriorityResponseDTO;
                }
                catch (CheckInException ex) {
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                }
            }
        }
        catch (Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());
        }

        LOGGER.debug("Exit CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

        return null;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request for Find Standby Priority service to get default standby priority.
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

        if (isNotEmpty(airlineNumber)) {

            airLineInfoDTO.setSitaNumber(new Long(airlineNumber));
            standbyPriorityQueryDTO.setHandledAirline(airLineInfoDTO);
        }
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

        if (tabTitle != null) {
            DcsTabDataContainer.getInstance().addTabAndDTO(tabTitle, findStandbyPriorityResponseDTO);
            DcsViewHandler.invokeNewTab(moduleId, tabTitle);
        }
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
            LOGGER.info("airline number is :" + airlineNumber);
        }
        catch (Exception ex) {
            airlineNumber=null;
            
        }

        return airlineNumber;

    }

    /**
     * Stub method.
     * 
     * <pre>
     * <b>Description : </b>
     * Method for empty check.
     * 
     * @param text , may be null
     * @return boolean
     * </pre>
     */
    private static boolean isNotEmpty(final String text) {
        if ((text == null) || text.trim().equalsIgnoreCase("")) {
            return false;
        }

        return true;
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
