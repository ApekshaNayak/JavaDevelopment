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
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class ResetStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(ResetStandbyPriorityListener.class);

    /**
     * Controller instance of StandbyPriorityController.
     */
    private StandbyPriorityDetailsController controller;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Reset button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        ResetStandbyPriorityListener.LOGGER.info("Enter ResetStandbyPriorityListener:actionPerformed()");
        DcsMessagePanelHandler.clearAllMessages();
        String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
        if (!currentTabName.equals(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB)) {
            String subscriberCode = controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase();
            String airlineCodeLOV = controller.getNewSPAirlineLOV().getSearchField().getText().toUpperCase();
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, subscriberCode);
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, airlineCodeLOV);
            controller.getStandbyPriorityDetailsScreenHelper().updateScreenUI();
        }
        else {
            if (controller.getNewSPSubscriberLOV().getSearchField().getText() != null) {
                controller.getNewSPSubscriberLOV().getSearchField().setText("");
            }
            if (controller.getNewSPAirlineLOV().getSearchField().getText() != null) {
                controller.getNewSPAirlineLOV().getSearchField().setText("");
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
                //System.out.println("Copy Default Reset");
                //System.out.println("Reset Default:"
                   // + NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT));

                String copyDefault = NGAFContextUtil.getApplicationContext()
                    .get(StandbyPriorityConstants.RESET_DEFAULT).toString();
                if (copyDefault.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                    FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                    if (findStandbyPriorityResponseDTO != null) {
                        if (NGAFContextUtil.getApplicationContext().get(
                                StandbyPriorityConstants.RESET_CREATE) != null) {
                            controller.getStandbyPriorityDetailsScreenHelper().resetBlankRows();
                        }
                        else {
                            controller.getStandbyPriorityDetailsScreenHelper().renderDefaultStandbyPriority(
                                findStandbyPriorityResponseDTO);
                            
                        }
                      
                        
                        
                    }
                }
            }
            else if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE, null);
                String copyStandBy = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY)
                    .toString();
                if (copyStandBy.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {

                    // final String tabName =
                    // TabDataContainer.getInstance().getCurrentTabName();
                    final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                    if (findStandbyPriorityResponseDTO != null) {
                        controller.getStandbyPriorityDetailsScreenHelper().renderStandbyPriority(
                            findStandbyPriorityResponseDTO);

                    }
                }
            }
            else if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_CREATE, null);
                /*String resetSearch = (String) NGAFContextUtil.getApplicationContext().get(
                    StandbyPriorityConstants.RESET_SEARCH);*/
                final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                if (findStandbyPriorityResponseDTO != null) {
                    if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_CREATE) != null) {
                        controller.getStandbyPriorityDetailsScreenHelper().resetBlankRows();
                        //findStandbyPriorityResponseDTO = null;
                    }
                    else {
                        controller.getStandbyPriorityDetailsScreenHelper().renderStandbyPriority(
                            findStandbyPriorityResponseDTO);
                    }

                }
            }
            else if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_CREATE) != null) {
                //final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                //FindStandbyPriorityResponseType findStandbyPriorityResponseDTO = getTabDTO(tabName);
                //findStandbyPriorityResponseDTO = null;
                controller.getStandbyPriorityDetailsScreenHelper().resetBlankRows();
            }
        }
        ResetStandbyPriorityListener.LOGGER.info("Exit ResetStandbyPriorityListener:actionPerformed()");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to get the DTO associated with the tab.
     *
     * @param tabName , not null.
     * @return findStandbyPriorityResponseDTO , never null.
     * </pre>
     */
    private FindStandbyPriorityResponse getTabDTO(final String tabName) {
        ResetStandbyPriorityListener.LOGGER.debug("Enter StandbyPriorityDetailsScreenHelper.getTabDTO");

        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO =
                (FindStandbyPriorityResponse) DcsTabDataContainer.getInstance().getDTOForTab(tabName);

        ResetStandbyPriorityListener.LOGGER.debug("Exit StandbyPriorityDetailsScreenHelper.getTabDTO");

        return findStandbyPriorityResponseDTO;
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
