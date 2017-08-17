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
import java.util.ArrayList;
import java.util.List;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsViewHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
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
public class OpenStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(OpenStandbyPriorityListener.class);

    /**
     * constant AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;
    /**
     * constant AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;
    /**
     * constant AIRLINE_COLUMN_THREE.
     */
    private static final int AIRLINE_COLUMN_THREE = 3;

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
        OpenStandbyPriorityListener.LOGGER.info("Enter OpenStandbyPriorityListener:actionPerformed()");

        DcsMessagePanelHandler.clearAllMessages();

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.OPEN_SEARCH,
            StandbyPriorityConstants.YES_VALUE);
        // }
        // String airlineCode = null;
        String airlineNumber = null;
        final String subscriberCode = NGAFContextUtil.getApplicationContext()
            .get(StandbyPriorityConstants.MANAGE_SEARCHED_SUBSCRIBER).toString();
        final List<String> contentList = new ArrayList<String>();
        final int selectedRowCount = controller.getSearchSPFilterTablePanel().getStandardTable().getSelectedRows().length;
        // For the List Of App-Context for opening single or multiple Tab.
        for (int index = 0; index < selectedRowCount; index++) {
            final int[] selectedAirlineRow = controller.getSearchSPFilterTablePanel().getStandardTable()
                .getSelectedRows();
            final String airlineCodeListItem = controller.getSearchSPFilterTablePanel().getStandardTable()
                .getValueAt(selectedAirlineRow[index], 2).toString();
            final String airlineNameListItem = controller.getSearchSPFilterTablePanel().getStandardTable()
                .getValueAt(selectedAirlineRow[index], OpenStandbyPriorityListener.AIRLINE_COLUMN_THREE).toString()
                .toUpperCase();
            // value is appended with "-".
            if (!DcsTabDataContainer.getInstance().isTabAlreadyOpen(subscriberCode + "-" + airlineNameListItem)) {
                contentList.add(airlineCodeListItem + "-" + airlineNameListItem);
            }
        }

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SELECTED_LIST_TO_OPEN, contentList);

        for (int index = 0; index < selectedRowCount; index++) {

            final int[] selectedAirlineRow = controller.getSearchSPFilterTablePanel().getStandardTable()
                .getSelectedRows();

            try {
                String airlineCode;

                // String subscriberCode =
                // controller.getSearchSPSubscriberLOV().getSelectedResult().toUpperCase();

                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SUBSCRIBER, null);

                airlineCode = controller.getSearchSPFilterTablePanel().getStandardTable()
                    .getValueAt(selectedAirlineRow[index], 2).toString();
                final String airlineName = controller.getSearchSPFilterTablePanel().getStandardTable()
                    .getValueAt(selectedAirlineRow[index], OpenStandbyPriorityListener.AIRLINE_COLUMN_THREE).toString()
                    .toUpperCase();
                if (subscriberCode != null) {
                    if (airlineCode != null) {
                        if (airlineCode.trim().length() == OpenStandbyPriorityListener.AIRLINECODE_LENGTH_TWO) {
                            airlineNumber = populateAirlineNumber(airlineCode, true);
                        }

                        if (airlineCode.trim().length() == OpenStandbyPriorityListener.AIRLINECODE_LENGTH_THREE) {
                            airlineNumber = populateAirlineNumber(airlineCode, false);
                        }
                    }
                    final String tabName = subscriberCode + "-" + airlineName;

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SUBSCRIBER, subscriberCode);
                    if (airlineNumber != null) {
                        synchronized (this) {
                            final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getStandbyPriority(
                                subscriberCode, airlineNumber);
                            wait(1000);

                            NGAFContextUtil.getApplicationContext().put("CURRENT_AIRLINE_CODE", airlineCode);
                            NGAFContextUtil.getApplicationContext().put("CURRENTTAB", tabName);

                            createNewTabs(findStandbyPriorityResponseDTO, StandbyPriorityConstants.MODULE_ID, tabName);
                            wait(1000);

                        }
                    }
                }

            }
            catch (final Exception ex) {

                OpenStandbyPriorityListener.LOGGER.error("Error in OpenStandbyPriorityListener.invokeTab "
                    + ex.getMessage());
            }
        }

        OpenStandbyPriorityListener.LOGGER.info("Exit OpenStandbyPriorityListener:actionPerformed()");
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
        catch (final Exception ex) {
            airlineNumber = null;

        }

        return airlineNumber;

    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to retrieve the Default Standby priority data.
     * 
     * @param subscriberCode , not null.
     * @param airlineNumber , not null.
     * @return FindStandbyPriorityResponseType , never null.
     * </pre>
     */
    private FindStandbyPriorityResponse getStandbyPriority(final String subscriberCode, final String airlineNumber) {
        OpenStandbyPriorityListener.LOGGER.debug("Enter CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

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
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                }
            }
        }
        catch (final Exception ex) {
            OpenStandbyPriorityListener.LOGGER.debug("Error : " + ex.getMessage());
        }

        OpenStandbyPriorityListener.LOGGER.debug("Exit CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

        return null;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     *  Method to populate request for Find Standby Priority service to get default standby priority.
     * 
     * @param subscriberCode , not null.
     * @param airlineNumber , not null.
     * @return FindStandbyPriorityRequestType , never null.
     * </pre>
     */
    private FindStandbyPriorityRequest populateFindSPRequestDTO(final String subscriberCode,
        final String airlineNumber) {
        final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = new FindStandbyPriorityRequest();
        final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();
        final AirlineInfoType airLineInfoDTO = new AirlineInfoType();

        if (OpenStandbyPriorityListener.isNotEmpty(airlineNumber)) {

            airLineInfoDTO.setSitaNumber(new Long(airlineNumber));
            standbyPriorityQueryDTO.setHandledAirline(airLineInfoDTO);

        }
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(subscriberCode);
       
        standbyPriorityQueryDTO.setSubscriberReference(subscriberIDType);
        findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);

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
