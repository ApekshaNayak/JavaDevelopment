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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.horizon.schemas.metadata.v1.AdministrativeRecordType;
import aero.sita.horizon.schemas.metadata.v1.DocumentHeaderType;
import aero.sita.horizon.schemas.metadata.v1.DocumentIDType;
import aero.sita.horizon.schemas.metadata.v1.LanguageCodeType;
import aero.sita.horizon.schemas.metadata.v1.SchemaVersionAttrGroup;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.ConfirmStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.DeleteStandbyPriority;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsViewHandler;
import aero.sita.voyager.dcs.client.common.util.DcsSIAMInfoUtil;
import aero.sita.voyager.ngafoundation.client.common.util.NGAFAppContextUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.utils.StandbyPriorityUtil;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for New Standby Priority Delete Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class DeleteOfStandbyPriorityListener implements ActionListener {
    /**
     * AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;
    /**
     * FIFTEEN.
     */
    private static final int FIFTEEN = 15;
    /**
     * AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;

    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(DeleteOfStandbyPriorityListener.class);

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
        DeleteOfStandbyPriorityListener.LOGGER.debug("Enter DeleteOfStandbyPriorityListener:actionPerformed()");

        DcsMessagePanelHandler.clearAllMessages();
        DcsMessagePanelHandler.handleWarningMessages("CKI-10827");

        final StandbyPriorityUtil newStandbyPriorityUtil = new StandbyPriorityUtil();

        final int isDelete = newStandbyPriorityUtil.confirmDelete(StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
            StandbyPriorityConstants.CONFIRM_DELETE);

        if (isDelete == 0) {

            deleteDefaultStandByPriority();

            DcsMessagePanelHandler.clearAllMessages();
        }
        else {
            DcsMessagePanelHandler.clearAllMessages();
        }

        DeleteOfStandbyPriorityListener.LOGGER.debug("Exit DeleteOfStandbyPriorityListener:actionPerformed()");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * This method will be called to delete the selected standby priority rows.
     * 
     * </pre>
     */
    private void deleteDefaultStandByPriority() {
        DeleteOfStandbyPriorityListener.LOGGER
            .debug("Enter DeleteOfStandbyPriorityListener:deleteDefaultStandByPriority");

        try {
            int success = 0;
            int failed = 0;
            int unSavedRows = 0;
            final List errorCodesList = new ArrayList();

            if ((controller.getNewSPFilterTablePanel() != null)
                && (controller.getNewSPFilterTablePanel().getModel() != null)) {
                final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();
                final int rowCount = controller.getNewSPFilterTablePanel().getTable().getRowCount();
                if (rowCount >= 0) {
                    for (int rows = 0; rows < rowCount; rows++) {

                        if (dm.getValueAt(0, FIFTEEN) != null && dm.getValueAt(0, FIFTEEN).toString().trim().length() > 0) {
                            final DeleteStandbyPriority deleteStandbyPriorityDTO 
                                = populateDeleteSPRequestDTO(dm.getValueAt(0, FIFTEEN).toString());

                            if (controller.getManageStandbyPriorityProxy() != null) {
                                try {
                                    final ConfirmStandbyPriority confirmStandbyPriorityDTO = controller
                                        .getManageStandbyPriorityProxy()
                                        .deleteStandbyPriority(deleteStandbyPriorityDTO);

                                    if (confirmStandbyPriorityDTO != null) {
                                        DeleteOfStandbyPriorityListener.LOGGER
                                            .debug("Exit DeleteOfStandbyPriorityListener:confirmStandbyPriorityDTO"
                                                + confirmStandbyPriorityDTO);
                                    }
                                    dm.removeRow(0);
                                    success++;
                                }
                                catch (final CheckInException ex) {
                                    dm.removeRow(0);
                                    failed++;
                                    errorCodesList.add(ex);
                                    DeleteOfStandbyPriorityListener.LOGGER.debug("Error" + ex);

                                }
                            }
                        }
                        else {
                            dm.removeRow(0);
                            unSavedRows++;
                        }
                    }

                    String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();

                    NGAFAppContextUtil.getAppContext().put("DELETE_STATUS", "YES");
                    controller.getStandbyPriorityDetailsScreenHelper().updateSearchScreen();
                    
                    DcsTabDataContainer.getInstance().showTab(currentTabName);
                    failed++;
                    failed--;
                    if (failed == 0) {
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                            StandbyPriorityConstants.YES_VALUE);
                        DcsViewHandler.closeTab(currentTabName);
                        // displayMessage(success, failed, errorCodesList);

                    }
                    else {
                        controller.getStandbyPriorityDetailsScreenHelper().updateScreenUI();
                        displayMessage(success, failed, errorCodesList);

                    }

                }
            }
        }
        catch (final Exception ex) {
            DeleteOfStandbyPriorityListener.LOGGER.debug("Error : " + ex.getMessage());
            // ex.printStackTrace();
        }

        DeleteOfStandbyPriorityListener.LOGGER
            .debug("Exit DeleteOfStandbyPriorityListener:deleteDefaultStandByPriority");
    }

    /**
     * Method to display the message on Create.
     * 
     * @param success , not null
     * @param failed , not null
     * @param errorCodesList , may be null
     */
    private void displayMessage(final int success, final int failed, final List errorCodesList) {
        final String subscriberCode = controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase();
        final String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText().toUpperCase();
        if ((failed == 0) && (success > 0)) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, subscriberCode);
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, airlineCode);
            DcsMessagePanelHandler.handleSuccessMessage(success + " Success ");
            DcsMessagePanelHandler.handleSuccessMessage("CKI-10828");
        }
        else if (failed > 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");
        }

        if ((failed > 0) && (errorCodesList != null) && (errorCodesList.size() > 0)) {
            for (int i = 0; i < errorCodesList.size(); i++) {
                if (errorCodesList.get(i) != null) {
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg((CheckInException) errorCodesList.get(i));
                }
            }
        }
    }

    /**
     * 
     * This method will be called to populate the Request DTO for Delete Standby
     * Priority.
     * 
     * @param docId , not null.
     * @return deleteStandbyPriorityDTO , never null.
     */
    private DeleteStandbyPriority populateDeleteSPRequestDTO(final String docId) {
        DeleteOfStandbyPriorityListener.LOGGER
            .debug("Enter DeleteOfStandbyPriorityListener:populateDeletePSPRequestDTO()");

        final DeleteStandbyPriority deleteStandbyPriorityDTO = new DeleteStandbyPriority();
        final StandbyPriorityType standByPriorityDTO = new StandbyPriorityType();

        standByPriorityDTO.setIsDefault(false);

        final SchemaVersionAttrGroup schemaVerGroup = new SchemaVersionAttrGroup();
        schemaVerGroup.setSchemaVersion("1.0");
        schemaVerGroup.setLanguageCode(LanguageCodeType.EN);
        standByPriorityDTO.setSchemaVersionAttrGroup(schemaVerGroup);

        populateDeleteSPDocumentDTO(standByPriorityDTO, docId);

        deleteStandbyPriorityDTO.setStandbyPriority(standByPriorityDTO);
        DeleteOfStandbyPriorityListener.LOGGER
            .debug("Exit DeleteOfStandbyPriorityListener:populateDeletePSPRequestDTO()");

        return deleteStandbyPriorityDTO;
    }

    /**
     * 
     * This method will be called to populate the Document Header DTO for Delete
     * Standby Priority.
     * 
     * @param standByPriorityDTO , not null.
     * @param docId , not null.
     */
    private void populateDeleteSPDocumentDTO(final StandbyPriorityType standByPriorityDTO, final String docId) {
        DeleteOfStandbyPriorityListener.LOGGER.debug("Enter DeleteOfStandbyPriorityListener:populateDeleteInfoDTO()");
        String airlineNumber = "";
        String subscriber = "";
        final DocumentHeaderType docHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType adminRecodeDTO = new AdministrativeRecordType();
        if (controller.getNewSPAirlineLOV().getSearchField().getText() != null) {
            final AirlineInfoType airlineInfoDTO = new AirlineInfoType();
            final String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText();

            if (airlineCode.trim().length() == DeleteOfStandbyPriorityListener.AIRLINECODE_LENGTH_TWO) {
                airlineInfoDTO.setIataCode(airlineCode.toUpperCase());
                airlineNumber = populateAirlineNumber(airlineCode, true);
            }
            else if (airlineCode.trim().length() == DeleteOfStandbyPriorityListener.AIRLINECODE_LENGTH_THREE) {
                airlineInfoDTO.setIcaoCode(airlineCode.toUpperCase());
                airlineNumber = populateAirlineNumber(airlineCode, false);
            }
        }
        if (controller.getNewSPSubscriberLOV().getSearchField().getText() != null) {
            subscriber = controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase();
        }
        if ((airlineNumber != null) && (subscriber != null)) {
        	final DocumentIDType documentIDType=new DocumentIDType();
            documentIDType.setString(airlineNumber + "#" + docId + "#" + subscriber);
            adminRecodeDTO.setDocumentID(documentIDType);
        	
        }
        adminRecodeDTO.setDocumentVersion("1");
        adminRecodeDTO.setCreatedBy(DcsSIAMInfoUtil.getCurrentUserName());
        adminRecodeDTO.setLastModifiedDate(new Date());
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(DcsSIAMInfoUtil.getCurrentSubscriber());
        adminRecodeDTO.setSubscriberReference(subscriberIDType);
       

        docHeaderDTO.setAdministrativeRecord(adminRecodeDTO);
        standByPriorityDTO.setDocumentHeader(docHeaderDTO);

        DeleteOfStandbyPriorityListener.LOGGER.debug("Exit DeleteOfStandbyPriorityListener:populateDeleteInfoDTO()");
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
