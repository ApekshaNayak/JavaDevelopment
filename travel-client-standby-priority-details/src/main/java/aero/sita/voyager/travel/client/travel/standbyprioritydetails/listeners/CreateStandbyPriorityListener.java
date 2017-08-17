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
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.csp.gui.thickclient.screencontainer.tabhelper.NGAFTabbedPaneHelper;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.valueobject.StandbyPriorityVO;

import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.horizon.schemas.metadata.v1.ActionCodeType;
import aero.sita.horizon.schemas.metadata.v1.AdministrativeRecordType;
import aero.sita.horizon.schemas.metadata.v1.DocumentHeaderType;
import aero.sita.horizon.schemas.metadata.v1.LanguageCodeType;
import aero.sita.horizon.schemas.metadata.v1.SchemaVersionAttrGroup;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusPriorityInfoType;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;

import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.ConfirmStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.CreateStandbyPriority;
import aero.sita.voyager.checkin.transferobjects.v9.DateOfJoiningRequiredType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityRequest;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.OpenPriorityInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityQueryType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Standby Priority Create Button Action Listener.
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
     * AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;

    /**
     * AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;

    /**
     * COLUMN_VALUE_TWO.
     */
    private static final int COLUMN_VALUE_TWO = 2;

    /**
     * COLUMN_VALUE_FIFTEEN.
     */
    private static final int COLUMN_VALUE_FIFTEEN = 15;

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
        CreateStandbyPriorityListener.LOGGER.info("Enter CreateStandbyPriorityListener:actionPerformed()");
        DcsMessagePanelHandler.clearAllMessages();

        boolean isValid = true;

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_SUBSCRIBER_CODE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_AIRLINE_CODE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, null);
        }

        if (controller.getNewSPFilterTablePanel() != null && controller.getNewSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();

            if (controller.getNewSPFilterTablePanel().getTable() != null) {
                NGAFStandardTable spStandardTable = (NGAFStandardTable) controller.getNewSPFilterTablePanel()
                    .getTable();

                isValid = controller.getStandbyPriorityDetailsValidator().validateStandbyPriority(dm, spStandardTable);
            }
        }

        if (isValid) {
            if (!controller.getStandbyPriorityDetailsScreenHelper().getFindResult(
                controller.getNewSPSubscriberLOV().getSelectedResult().toUpperCase(),
                controller.getNewSPAirlineLOV().getSelectedResult().toUpperCase())) {
                createNewStandByPriority(controller.getStandbyPriorityDetailsScreenHelper().fetchSPTableData());
            }
            else {
                DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10888");
            }
        }

        CreateStandbyPriorityListener.LOGGER.info("Exit CreateStandbyPriorityListener:actionPerformed()");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method for service call of create stand by priority.
     * 
     * @param spVOList , not null.
     * </pre>
     */
    private void createNewStandByPriority(final List<StandbyPriorityVO> spVOList) {
        CreateStandbyPriorityListener.LOGGER.debug("Enter CreateStandbyPriorityListener.createStandByPriority");

        int success = 0;
        int failed = 0;
        List failedRowsList = new ArrayList();
        List errorCodesList = new ArrayList();

        DefaultTableModel defaultTableModel = null;

        if (controller.getNewSPFilterTablePanel().getModel() != null
            && controller.getNewSPFilterTablePanel().getModel() != null) {
            defaultTableModel = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();
        }

        try {
            if (controller.getManageStandbyPriorityProxy() != null) {
                for (StandbyPriorityVO standbyPriorityVO : spVOList) {

                    try {
                        final ConfirmStandbyPriority confirmStandbyPriorityDTO = controller
                        .getManageStandbyPriorityProxy().createStandbyPriority(populateCreateSPRequestDTO(standbyPriorityVO));

                        if (confirmStandbyPriorityDTO != null) {
                            CreateStandbyPriorityListener.LOGGER
                            .debug("Exit CreateStandbyPriorityListener.confirmStandbyPriorityDTO"
                                + confirmStandbyPriorityDTO);
                        }

                        if (standbyPriorityVO.getPriorityStatusCode() != null && defaultTableModel != null) {
                            defaultTableModel.setValueAt(standbyPriorityVO.getPriorityStatusCode(),
                                standbyPriorityVO.getRowNumber(), COLUMN_VALUE_FIFTEEN);
                        }

                        success++;
                    }
                    catch (final CheckInException ex) {
                        failed++;
                        failedRowsList.add(standbyPriorityVO.getRowNumber());
                        errorCodesList.add(ex);
                        LOGGER.error("Error occured" + ex);
                    }
                }
            }

            displayMessage(success, failed, spVOList, errorCodesList, failedRowsList);
        }
        catch (final Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());
            // ex.printStackTrace();
        }

        CreateStandbyPriorityListener.LOGGER.debug("Exit CreateStandbyPriorityListener.createStandByPriority");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to display the message on Create.
     * 
     * @param success , not null.
     * @param failed , not null.
     * @param spVOList , not null.
     * @param errorCodesList , not null.
     * @param failedRowsList , not null.
     * </pre>
     */
    private void displayMessage(final int success, final int failed, final List spVOList, final List errorCodesList,
        final List failedRowsList) {
        String subscriberCode = controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase();

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.
                DEFAULT_STANDBY_PRIORITY_RESPONSE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITY_RESPONSE,
                null);
        }

        if (failed == 0 && success > 0) {
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
            }

            String airlineCodeLOV = controller.getNewSPAirlineLOV().getSearchField().getText();

            JTabbedPane pane = ((JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent()));
            final int index = ((JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent())).getSelectedIndex();
            String airlineName = controller.getCommonUtilitiesGateWay().getAirlineName(airlineCodeLOV);

            pane.setTitleAt(index, subscriberCode.trim() + "-" + airlineName.trim());
            // pane.setTitleAt(index, airlineCode.trim());

            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, subscriberCode);
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, airlineCodeLOV);

            controller.getStandbyPriorityDetailsScreenHelper().updateScreenUI();
            controller.getNewSPUpdateButton().setVisible(true);
            controller.getNewSPUpdateButton().setEnabled(true);
            controller.getNewSPCopyButton().setEnabled(true);
            controller.getNewSPDeleteButton().setEnabled(true);
            controller.getNewSPAirlineLOV().getSearchField().setEnabled(false);
            controller.getNewSPAirlineLOV().getSearchButton().setEnabled(false);
            controller.getNewSPSubscriberLOV().getSearchButton().setEnabled(false);
            controller.getNewSPSubscriberLOV().getSearchField().setEnabled(false);
            controller.getNewSPCreateButton().setEnabled(false);
            controller.getNewSPCreateButton().setVisible(false);

            DcsMessagePanelHandler.handleSuccessMessage(success + " Success ");
            DcsMessagePanelHandler.handleSuccessMessage("CKI-10831");
        }
        else if (failed > 0 && success > 0) {
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
            }
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");
            String airlineCodeLOV = controller.getNewSPAirlineLOV().getSearchField().getText();
            JTabbedPane pane = (JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent());
            final int index = ((JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent())).getSelectedIndex();
            String airlineName = controller.getCommonUtilitiesGateWay().getAirlineName(airlineCodeLOV);

            pane.setTitleAt(index, subscriberCode.trim() + "-" + airlineName.trim());
            // pane.setTitleAt(index, airlineCode.trim());

            controller.getNewSPUpdateButton().setVisible(true);
            controller.getNewSPUpdateButton().setEnabled(true);
            controller.getNewSPCopyButton().setEnabled(true);
            controller.getNewSPDeleteButton().setEnabled(true);
            controller.getNewSPAirlineLOV().getSearchField().setEnabled(false);
            controller.getNewSPAirlineLOV().getSearchButton().setEnabled(false);
            controller.getNewSPSubscriberLOV().getSearchButton().setEnabled(false);
            controller.getNewSPSubscriberLOV().getSearchField().setEnabled(false);
        }
        else if (failed > 0 && success == 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(failed + " Failed");

            controller.getNewSPUpdateButton().setVisible(false);
            controller.getNewSPCopyButton().setEnabled(false);
            controller.getNewSPCreateButton().setVisible(true);
            controller.getNewSPCreateButton().setEnabled(true);
        }

        if (failed > 0 && errorCodesList != null && errorCodesList.size() > 0) {
            for (int i = 0; i < errorCodesList.size(); i++) {
                if (errorCodesList.get(i) != null) {
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg((CheckInException) errorCodesList.get(i));
                }
            }
        }

        if (failedRowsList != null && failedRowsList.size() > 0) {
            highlightFailedRows(failedRowsList, spVOList);
        }
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request.
     * 
     * @param standbyPriorityVO , not null.
     * @return createStandbyPriorityDTO , never null.
     * </pre>
     */

    private CreateStandbyPriority populateCreateSPRequestDTO(final StandbyPriorityVO standbyPriorityVO) {
        CreateStandbyPriorityListener.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPRequestDTO");

        final CreateStandbyPriority createStandbyPriorityDTO = new CreateStandbyPriority();
        final StandbyPriorityType standbyPriorityDTO = new StandbyPriorityType();

        populateCreateSPDocumentDTO(standbyPriorityDTO);
        populateCreateSPInfoDTO(standbyPriorityDTO, standbyPriorityVO);
        populateCreateSPAirlineInfo(standbyPriorityDTO, standbyPriorityVO);
        populateCreateSPBookingStatusDTO(standbyPriorityDTO, standbyPriorityVO);

        createStandbyPriorityDTO.setStandbyPriority(standbyPriorityDTO);

        CreateStandbyPriorityListener.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPRequestDTO");

        return createStandbyPriorityDTO;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Standbypriority Documentheader DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * </pre>
     */
    private void populateCreateSPDocumentDTO(final StandbyPriorityType standbyPriorityDTO) {
        CreateStandbyPriorityListener.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPDocumentDTO");

        final DocumentHeaderType documentHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType administrativeRecordDTO = new AdministrativeRecordType();
        String subscriber = controller.getNewSPSubscriberLOV().getSearchField().getText();
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(subscriber.trim().toUpperCase());
        administrativeRecordDTO.setSubscriberReference(subscriberIDType);
       
        documentHeaderDTO.setAdministrativeRecord(administrativeRecordDTO);

        final SchemaVersionAttrGroup schemaVersionAttrGroupParam = new SchemaVersionAttrGroup();
        schemaVersionAttrGroupParam.setLanguageCode(LanguageCodeType.EN);
        schemaVersionAttrGroupParam.setSchemaVersion("1.0");
        schemaVersionAttrGroupParam.setActionCode(ActionCodeType.NEW);

        standbyPriorityDTO.setDocumentHeader(documentHeaderDTO);
        standbyPriorityDTO.setIsDefault(false);
        standbyPriorityDTO.setSchemaVersionAttrGroup(schemaVersionAttrGroupParam);

        CreateStandbyPriorityListener.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPDocumentDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Standbypriority Info DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param standbyPriorityVO , not null.
     * </pre>
     */
    private void populateCreateSPInfoDTO(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CreateStandbyPriorityListener.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPInfoDTO");

        if (standbyPriorityVO != null) {
            final StandbyPriorityInfoType standbyPriorityInfoDTO = new StandbyPriorityInfoType();

            if (standbyPriorityVO.getPriorityStatusCode() != null) {
                standbyPriorityInfoDTO.setPriorityStatusCode(standbyPriorityVO.getPriorityStatusCode());
            }

            if (standbyPriorityVO.getPriorityStatusDesc() != null) {
                standbyPriorityInfoDTO.setPriorityStatusDescription(standbyPriorityVO.getPriorityStatusDesc());
            }

            if (standbyPriorityVO.getCanBeDisembarked() != null) {

                if (standbyPriorityVO.getCanBeDisembarked().equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    standbyPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    standbyPriorityInfoDTO.setCanBeDisembarked(false);
                }
            }

            if (standbyPriorityVO.getDateOfJoining() != null) {

                if (standbyPriorityVO.getDateOfJoining().equalsIgnoreCase(StandbyPriorityConstants.DOJ_OPTIONAL)) {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.OPTIONAL);
                }
                else {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.NOT_PERMITTED);
                }
            }

            standbyPriorityDTO.setInfo(standbyPriorityInfoDTO);
        }

        CreateStandbyPriorityListener.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPInfoDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Airline Info DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param standbyPriorityVO , not null.
     * </pre>
     */
    private void populateCreateSPAirlineInfo(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CreateStandbyPriorityListener.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPAirlineInfo");

        String airlineNumber = null;

        if (standbyPriorityVO != null) {
            if (controller.getNewSPAirlineLOV().getSearchField().getText() != null) {
                final AirlineInfoType airlineInfoDTO = new AirlineInfoType();
                String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText();

                if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
                    airlineInfoDTO.setIataCode(airlineCode.toUpperCase());
                    airlineNumber = populateAirlineNumber(airlineCode, true);
                }

                if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
                    airlineInfoDTO.setIcaoCode(airlineCode.toUpperCase());
                    airlineNumber = populateAirlineNumber(airlineCode, false);
                }

                if (isNotEmpty(airlineNumber)) {
                    airlineInfoDTO.setSitaNumber(new Long(airlineNumber));
                    standbyPriorityDTO.setHandledAirline(airlineInfoDTO);
                }
            }
        }

        CreateStandbyPriorityListener.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPAirlineInfo");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Standbypriority BookingStatus Info DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param standbyPriorityVO , not null.
     * </pre>
     */
    private void populateCreateSPBookingStatusDTO(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CreateStandbyPriorityListener.LOGGER
        .debug("Enter CreateStandbyPriorityListener.populateCreateSPBookingStatusDTO");

        final BookingStatusInfoType bookingStatusInfoDTO = new BookingStatusInfoType();
        final BookingStatusPriorityInfoType bookedPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType noRecPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType waitlistPriorityinfoDTO = new BookingStatusPriorityInfoType();
        final OpenPriorityInfoType openPriorityInfoDTO = new OpenPriorityInfoType();

        if (standbyPriorityVO != null) {
            if (standbyPriorityVO.getBookedPriorityValue() != 0 && standbyPriorityVO.getBookedPriorityValue() != 0
                && standbyPriorityVO.getBookedCanBeDisembarked() != null) {

                bookedPriorityInfoDTO.setPriorityValue(standbyPriorityVO.getBookedPriorityValue());

                if (standbyPriorityVO.getBookedCanBeDisembarked().equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    bookedPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    bookedPriorityInfoDTO.setCanBeDisembarked(false);
                }

                bookingStatusInfoDTO.setBookedPriorityInfo(bookedPriorityInfoDTO);
            }

            if (standbyPriorityVO.getNoRecPriorityValue() != 0 && standbyPriorityVO.getNoRecPriorityValue() != 0
                && standbyPriorityVO.getNoRecCanBeDisembarked() != null) {

                noRecPriorityInfoDTO.setPriorityValue(standbyPriorityVO.getNoRecPriorityValue());

                if (standbyPriorityVO.getNoRecCanBeDisembarked().equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    noRecPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    noRecPriorityInfoDTO.setCanBeDisembarked(false);
                } 

                bookingStatusInfoDTO.setNorecPriorityInfo(noRecPriorityInfoDTO);
            }

            if (standbyPriorityVO.getWaitlistCanBeDisembarked() != null
                && standbyPriorityVO.getWaitlistPriorityValue() != 0
                && standbyPriorityVO.getWaitlistPriorityValue() != 0) {

                waitlistPriorityinfoDTO.setPriorityValue(standbyPriorityVO.getWaitlistPriorityValue());

                if (standbyPriorityVO.getWaitlistCanBeDisembarked().equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    waitlistPriorityinfoDTO.setCanBeDisembarked(true);
                }
                else {
                    waitlistPriorityinfoDTO.setCanBeDisembarked(false);
                }

                bookingStatusInfoDTO.setWaitlistPriorityinfo(waitlistPriorityinfoDTO);
                standbyPriorityDTO.setBookingStatusInfo(bookingStatusInfoDTO);
            }

            if (standbyPriorityVO.getOpenTicketPriorityValue() != 0
                && standbyPriorityVO.getOpenTicketPriorityValue() != 0
                && standbyPriorityVO.getOpenTicketCanBeDisembarked() != null
                && standbyPriorityVO.getHoldsSpaceAvailableTicket() != null) {

                if (standbyPriorityVO.getOpenTicketCanBeDisembarked().equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    openPriorityInfoDTO.setCanBeDisembarked(false);
                }

                if (standbyPriorityVO.getHoldsSpaceAvailableTicket().equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(true);
                }
                else {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(false);
                }

                openPriorityInfoDTO.setPriorityValue(standbyPriorityVO.getOpenTicketPriorityValue());
                bookingStatusInfoDTO.setOpenPriorityInfo(openPriorityInfoDTO);
            }
        }

        CreateStandbyPriorityListener.LOGGER
        .debug("Exit CreateStandbyPriorityListener.populateCreateSPBookingStatusDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to highlight failedrows.
     * 
     * @param failedRowsList , not null.
     * @param spVOList , not null.
     * </pre>
     */
    private void highlightFailedRows(final List failedRowsList, final List<StandbyPriorityVO> spVOList) {
        CreateStandbyPriorityListener.LOGGER.debug("Enter CreatetStandbyPriorityListener.highlightFailedRows");

        try {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.CREATE_SP_FAILED_ROWS, failedRowsList);

            for (int i = 0; i < failedRowsList.size(); i++) {
                if (controller.getNewSPFilterTablePanel() != null
                    && controller.getNewSPFilterTablePanel().getModel() != null) {

                    final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();

                    if (controller.getNewSPFilterTablePanel().getTable() != null) {
                        NGAFStandardTable spStandardTable = (NGAFStandardTable) controller.
                            getNewSPFilterTablePanel().getTable();

                        for (StandbyPriorityVO StandbyPriorityVO : spVOList) {
                            if (failedRowsList.get(i) != null && !("").equals(failedRowsList.get(i))) {
                                int rowno = Integer.parseInt(failedRowsList.get(i).toString());

                                String docId = null;
                                String priorityStatusCode = null;

                                if (dm.getValueAt(rowno, COLUMN_VALUE_FIFTEEN) != null
                                    && !("").equals(dm.getValueAt(rowno, COLUMN_VALUE_FIFTEEN))) {
                                    docId = dm.getValueAt(rowno, COLUMN_VALUE_FIFTEEN).toString();
                                }

                                if (dm.getValueAt(rowno, COLUMN_VALUE_TWO) != null
                                    && !("").equals(dm.getValueAt(rowno, COLUMN_VALUE_TWO))) {
                                    priorityStatusCode = dm.getValueAt(rowno, COLUMN_VALUE_TWO).toString();
                                }

                                if (StandbyPriorityVO.getPriorityStatusCode() != null
                                    && StandbyPriorityVO.getDocumentId() == null && docId == null
                                    && priorityStatusCode != null) {

                                    // renderTableColumns(spStandardTable);
                                    spStandardTable.repaint();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            LOGGER.error("Error : " + ex.getMessage());
            // ex.printStackTrace();
        }

        CreateStandbyPriorityListener.LOGGER.debug("Exit CreatetStandbyPriorityListener.highlightFailedRows");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to render the table cells.
     * 
     * @param spStandardTable , not null.
     * </pre>
     */
    // private void renderTableColumns(final NGAFStandardTable spStandardTable)
    // {
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_TWO).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_THREE).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_FOUR).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_FIVE).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_SIX).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_SEVEN).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_EIGHT).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_NINE).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_TEN).setCellRenderer(new
    // ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_ELEVEN)
    // .setCellRenderer(new ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_TWELVE)
    // .setCellRenderer(new ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_THIRTEEN)
    // .setCellRenderer(new ColorRendererCreateSP(true));
    //
    // spStandardTable.getColumnModel().getColumn(COLUMN_VALUE_FOURTEEN)
    // .setCellRenderer(new ColorRendererCreateSP(true));
    // }

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
