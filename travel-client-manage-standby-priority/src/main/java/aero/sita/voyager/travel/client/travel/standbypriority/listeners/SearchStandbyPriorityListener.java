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
import aero.sita.csp.gui.thickclient.component.lov.NGAFMultiSelectLOVComponent;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityRequest;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;
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
public class SearchStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(SearchStandbyPriorityListener.class);

    /**
     * AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;

    /**
     * AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;

    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Search button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        SearchStandbyPriorityListener.LOGGER.info("Enter SearchStandbyPriorityListener:actionPerformed()");

        DcsMessagePanelHandler.clearAllMessages();
        final List<Object> standbyPriorityResponseList = new ArrayList<Object>();
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST, null);
        }

        final boolean isValid = controller.getStandbyPriorityValidator().validateSearchSP();
        // setting the Searched subscriber in application context.

        if (isValid) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.MANAGE_SEARCHED_SUBSCRIBER,
                controller.getSearchSPSubscriberLOV().getSelectedResult().toUpperCase());
            getSearchResult();
            populateValues();
            if (controller.getSearchSPFilterTablePanel().getTable().getRowCount() == 1) {
                controller.getSearchSPFilterTablePanel().getTable().selectAll();
                controller.getSearchSPOpenButton().doClick();
            }

        }
        else {
            if (controller.getSearchSPStandardTable().getModel().getRowCount() > 0) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST,
                    standbyPriorityResponseList);
                controller.getSearchSPFilterTablePanel().populateData();
            }
        }

        SearchStandbyPriorityListener.LOGGER.info("Exit SearchStandbyPriorityListener:actionPerformed()");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * method to populate values.
     * 
     * </pre>
     */
    private void populateValues() {
        controller.getSearchSPResultsPaneHeader().setVisible(true);
        controller.getSearchSPResultsPaneHeader().setCollapsed(false);
        controller.getSearchSPStandardTable().getFilterRow();
        controller.getSearchSPFilterTablePanel().populateData();
        controller.getSearchSPStandardTable().getSelectionModel().clearSelection();
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to retrieve the Airlines having the Standby Priority data.
     * 
     * </pre>
     */
    private void getSearchResult() {
        final List<String> airlineList = uniqueAirlines();
        final List<Object> standbyPriorityResponseList = new ArrayList<Object>();
        final String subscriber = controller.getSearchSPSubscriberLOV().getSelectedResult().toUpperCase();
        try {
            if ((airlineList != null) && (airlineList.size() > 0)) {

                if (controller.getManageStandbyPriorityProxy() != null) {
                    for (int i = 0; i < airlineList.size(); i++) {
                        String airlineNumber = null;

                        if (airlineList.get(i) != null) {
                            // airlineList.get(i).trim();

                            try {
                                final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = new FindStandbyPriorityRequest();
                                final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();
                                final AirlineInfoType airLineInfoDTO = new AirlineInfoType();

                                if (airlineList.get(i).trim().length() == SearchStandbyPriorityListener.AIRLINECODE_LENGTH_TWO) {
                                    airLineInfoDTO.setIataCode(airlineList.get(i).toUpperCase());
                                    airlineNumber = populateAirlineNumber(airlineList.get(i).trim(), true);
                                }

                                if (airlineList.get(i).trim().length() == SearchStandbyPriorityListener.AIRLINECODE_LENGTH_THREE) {
                                    airLineInfoDTO.setIcaoCode(airlineList.get(i).trim().toUpperCase());
                                    airlineNumber = populateAirlineNumber(airlineList.get(i).trim(), false);
                                }

                                if (SearchStandbyPriorityListener.isNotEmpty(airlineNumber)) {
                                    airLineInfoDTO.setSitaNumber(new Long(airlineNumber));
                                    standbyPriorityQueryDTO.setHandledAirline(airLineInfoDTO);
                                    SubscriberIDType subscriberIDType=new SubscriberIDType();
                                    subscriberIDType.setString(subscriber.trim().toUpperCase());
                                    
                                    standbyPriorityQueryDTO.setSubscriberReference(subscriberIDType);
                                    findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);

                                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = controller
                                        .getManageStandbyPriorityProxy().findStandbyPriority(
                                            findStandbyPriorityRequestDTO);

                                    if ((findStandbyPriorityResponseDTO != null)
                                        && (findStandbyPriorityResponseDTO.getStandbyPriorityList() != null)
                                        && (findStandbyPriorityResponseDTO.getStandbyPriorityList()
                                            .getStandbyPriorities() != null)
                                        && (findStandbyPriorityResponseDTO.getStandbyPriorityList()
                                            .getStandbyPriorities().getStandbyPriorityList() != null)) {

                                        final List<StandbyPriorityType> standbyPriorityCustomList = findStandbyPriorityResponseDTO
                                            .getStandbyPriorityList().getStandbyPriorities().
                                            getStandbyPriorityList();

                                        final StandbyPriorityType standbyPriorityDTO = standbyPriorityCustomList.get(0);
                                        final boolean isDefault = standbyPriorityDTO.isIsDefault();

                                        if (!isDefault) {
                                            standbyPriorityResponseList.add(standbyPriorityDTO);
                                        }
                                    }
                                }
                            }
                            catch (final CheckInException ex) {
                                DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                            }
                        }
                    }
                }
            }

            if (standbyPriorityResponseList.size() > 0) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST,
                    standbyPriorityResponseList);
            }
            else {
                DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10856");
            }
        }
        catch (final Exception ex) {
            SearchStandbyPriorityListener.LOGGER.debug("Error : " + ex.getMessage());
            // ex.printStackTrace();
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method will give the unique airlines from the list of airlines.
     * @return airlineList , never null.
     * </pre>
     */
    private List<String> uniqueAirlines() {
        final List<String> airlineList = new ArrayList<String>();

        if (controller.getSearchSPAirlinesLOV() != null) {
            final NGAFMultiSelectLOVComponent airlineLOV = controller.getSearchSPAirlinesLOV();

            if (airlineLOV.getSearchResult() != null) {
                final String[] airlineCodes = airlineLOV.getSearchResult();

                if ((airlineCodes != null) && (airlineCodes.length > 0)) {
                    if (airlineCodes.length == 1) {
                        final String airlineCode = airlineCodes[0];
                        airlineList.add(airlineCode);
                    }

                    if (airlineCodes.length > 1) {
                        for (int i = 0; i < airlineCodes.length; i++) {
                            final String airlineCode = airlineCodes[i];
                            boolean isDuplicate = false;

                            for (int j = i + 1; j < airlineCodes.length; j++) {
                                final String airlineCodeTemp = airlineCodes[j];

                                if (airlineCode.equals(airlineCodeTemp)) {
                                    isDuplicate = true;
                                    break;
                                }
                            }

                            if (!isDuplicate) {
                                airlineList.add(airlineCode);
                            }
                        }
                    }
                }
            }
        }

        return airlineList;
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
