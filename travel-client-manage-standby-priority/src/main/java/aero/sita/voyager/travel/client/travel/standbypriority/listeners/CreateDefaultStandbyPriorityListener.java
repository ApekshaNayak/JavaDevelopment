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

import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.filtertable.NGAFFilterTablePanel;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.util.DcsSIAMInfoUtil;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.valueobject.DefaultStandbyPriorityVO;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.horizon.schemas.metadata.v1.ActionCodeType;
import aero.sita.horizon.schemas.metadata.v1.AdministrativeRecordType;
import aero.sita.horizon.schemas.metadata.v1.DocumentHeaderType;
import aero.sita.horizon.schemas.metadata.v1.LanguageCodeType;
import aero.sita.horizon.schemas.metadata.v1.SchemaVersionAttrGroup;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusPriorityInfoType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.ConfirmStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.CreateStandbyPriority;
import aero.sita.voyager.checkin.transferobjects.v9.DateOfJoiningRequiredType;
import aero.sita.voyager.checkin.transferobjects.v9.OpenPriorityInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityInfoType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class CreateDefaultStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
        .getLogger(CreateDefaultStandbyPriorityListener.class);

    /**
     * COLUMN_VALUE_TWO.
     */
    private static final int COLUMN_VALUE_TWO = 2;

    /**
     * COLUMN_VALUE_FIFTEEN.
     */
    private static final int COLUMN_VALUE_FIFTEEN = 15;

    /**
     * Controller instance of View Flights Controller.
     */
    private StandbyPriorityController controller;

    /**
     * NGAFFilterTablePanel in Default Standby Priority Screen.
     */
    private NGAFFilterTablePanel defaultSPFilterTablePanel;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Action Performed by Assign button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CreateDefaultStandbyPriorityListener:actionPerformed()");

        boolean isValid = true;

        DcsMessagePanelHandler.clearAllMessages();

        if (controller.getDefaultSPFilterTablePanel() != null
            && controller.getDefaultSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel dm = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();

            if (controller.getDefaultSPFilterTablePanel().getTable() != null) {
                NGAFStandardTable defaultSPStandardTable = (NGAFStandardTable) controller
                    .getDefaultSPFilterTablePanel().getTable();

                isValid = controller.getStandbyPriorityValidator().validateStandbyPriority(dm, defaultSPStandardTable);
            }
        }

        if (isValid) {
            // List<DefaultStandbyPriorityVO> defaultSPVOList = null;
            List<DefaultStandbyPriorityVO> defaultSPVOList = controller.getDefaultSPScreenHelper()
                .fetchDefaultSPTableData();

            if (defaultSPVOList != null && defaultSPVOList.size() > 0) {
                createDefaultStandByPriority(defaultSPVOList);
            }
        }

        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CreateDefaultStandbyPriorityListener:actionPerformed()");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method for service call of create stand by priority.
     * 
     * @param defaultSPVOList , not null.
     * </pre>
     */
    private void createDefaultStandByPriority(final List<DefaultStandbyPriorityVO> defaultSPVOList) {
        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CreateDefaultStandbyPriorityListener.createStandByPriority");

        int success = 0;
        int failed = 0;
        List failedRowsList = new ArrayList();
        List errorCodesList = new ArrayList();
        DefaultTableModel defaultTableModel = null;

        if (controller.getDefaultSPFilterTablePanel().getModel() != null
            && controller.getDefaultSPFilterTablePanel().getModel() != null) {
            defaultTableModel = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();
        }

        try {
            if (controller.getManageStandbyPriorityProxy() != null) {
                for (DefaultStandbyPriorityVO defaultStandbyPriorityVO : defaultSPVOList) {

                    try {
                        controller.getManageStandbyPriorityProxy().createStandbyPriority(
                            populateCreateSPRequestDTO(defaultStandbyPriorityVO));
                        if (defaultStandbyPriorityVO.getPriorityStatusCode() != null && defaultTableModel != null) {
                            defaultTableModel.setValueAt(defaultStandbyPriorityVO.getPriorityStatusCode(),
                                defaultStandbyPriorityVO.getRowNumber(), COLUMN_VALUE_FIFTEEN);
                        }

                        success++;
                    }
                    catch (final CheckInException ex) {
                        failed++;
                        failedRowsList.add(defaultStandbyPriorityVO.getRowNumber());
                        errorCodesList.add(ex);

                        CreateDefaultStandbyPriorityListener.LOGGER
                            .error("Error in CreateDefaultStandbyPriorityListener.createStandByPriority : " + ex);
                    }
                }

                displayMessage(success, failed, defaultSPVOList, errorCodesList, failedRowsList);
            }
        }
        catch (final Exception ex) {
            CreateDefaultStandbyPriorityListener.LOGGER
                .error("Error in CreateDefaultStandbyPriorityListener.createStandByPriority : " + ex.getMessage());
            // ex.printStackTrace();
        }

        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CreateDefaultStandbyPriorityListener.createStandByPriority");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to display the message on Create.
     * 
     * @param success , not null.
     * @param failed , not null.
     * @param defaultSPVOList , not null.
     * @param errorCodesList , not null.
     * @param failedRowsList , not null.
     * </pre>
     */
    private void displayMessage(final int success, final int failed, final List defaultSPVOList,
        final List errorCodesList, final List failedRowsList) {
        CreateDefaultStandbyPriorityListener.LOGGER.debug("Enter CreateDefaultStandbyPriorityListener.displayMessage");

        if (failed == 0 && success > 0) {
            DcsMessagePanelHandler.handleSuccessMessage(success + " Success ");
            DcsMessagePanelHandler.handleSuccessMessage("CKI-10829");

            controller.getDefaultSPScreenHelper().updateScreenUI();

            controller.getDefaultSPUpdateButton().setVisible(true);
            controller.getDefaultSPUpdateButton().setEnabled(true);
            controller.getDefaultSPCopyButton().setEnabled(true);
            controller.getDefaultSPCreateButton().setVisible(false);
        }
        else if (failed > 0 && success > 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");

            controller.getDefaultSPUpdateButton().setVisible(true);
            controller.getDefaultSPUpdateButton().setEnabled(true);
            controller.getDefaultSPCopyButton().setEnabled(true);
            controller.getDefaultSPCreateButton().setVisible(false);
        }
        else if (failed > 0 && success == 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(failed + " Failed");

            controller.getDefaultSPUpdateButton().setVisible(false);
            controller.getDefaultSPCopyButton().setEnabled(false);
            controller.getDefaultSPCreateButton().setVisible(true);
            controller.getDefaultSPCreateButton().setEnabled(true);
        }

        if (failed > 0 && errorCodesList != null && errorCodesList.size() > 0) {
            for (int i = 0; i < errorCodesList.size(); i++) {
                if (errorCodesList.get(i) != null) {
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg((CheckInException) errorCodesList.get(i));
                }
            }
        }

        if (failedRowsList != null && failedRowsList.size() > 0) {
            highlightFailedRows(failedRowsList, defaultSPVOList);
        }

        CreateDefaultStandbyPriorityListener.LOGGER.debug("Exit CreateDefaultStandbyPriorityListener.displayMessage");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to highlight failedrows.
     * 
     * @param failedRowsList , not null.
     * @param defaultSPVOList , not null.
     * </pre>
     */
    private void highlightFailedRows(final List failedRowsList, final List<DefaultStandbyPriorityVO> defaultSPVOList) {
        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CreateDefaultStandbyPriorityListener.highlightFailedRows");

        try {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.CREATE_DEFAULT_SP_FAILED_ROWS, failedRowsList);

            for (int i = 0; i < failedRowsList.size(); i++) {
                if (controller.getDefaultSPFilterTablePanel() != null
                    && controller.getDefaultSPFilterTablePanel().getModel() != null) {

                    final DefaultTableModel dm = (DefaultTableModel) controller.getDefaultSPFilterTablePanel()
                        .getModel();

                    if (controller.getDefaultSPFilterTablePanel().getTable() != null) {
                        NGAFStandardTable defaultSPStandardTable = (NGAFStandardTable) controller
                            .getDefaultSPFilterTablePanel().getTable();

                        for (DefaultStandbyPriorityVO defaultStandbyPriorityVO : defaultSPVOList) {
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

                                if (defaultStandbyPriorityVO.getPriorityStatusCode() != null
                                    && defaultStandbyPriorityVO.getDocumentId() == null && docId == null
                                    && priorityStatusCode != null) {

                                    // renderTableColumns(defaultSPStandardTable);
                                    defaultSPStandardTable.repaint();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            CreateDefaultStandbyPriorityListener.LOGGER
                .error("Error in CreateDefaultStandbyPriorityListener.highlightFailedRows : " + ex.getMessage());
        }

        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CreateDefaultStandbyPriorityListener.highlightFailedRows");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request for Create Standby Priority.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return createStandbyPriorityDTO , never null.
     * </pre>
     */
    private CreateStandbyPriority populateCreateSPRequestDTO(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CreateDefaultStandbyPriorityListener.populateCreateSPRequestDTO");

        final CreateStandbyPriority createStandbyPriorityDTO = new CreateStandbyPriority();
        final StandbyPriorityType standbyPriorityDTO = new StandbyPriorityType();

        populateCreateSPDocumentDTO(standbyPriorityDTO);
        populateCreateSPInfoDTO(standbyPriorityDTO, defaultStandbyPriorityVO);
        populateCreateSPBookingStatusDTO(standbyPriorityDTO, defaultStandbyPriorityVO);

        createStandbyPriorityDTO.setStandbyPriority(standbyPriorityDTO);

        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CreateDefaultStandbyPriorityListener.populateCreateSPRequestDTO");

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
        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CreateDefaultStandbyPriorityListener.populateCreateSPDocumentDTO");

        final DocumentHeaderType documentHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType administrativeRecordDTO = new AdministrativeRecordType();

        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(DcsSIAMInfoUtil.getCurrentSubscriber());
        administrativeRecordDTO.setSubscriberReference(subscriberIDType);
        documentHeaderDTO.setAdministrativeRecord(administrativeRecordDTO);

        final SchemaVersionAttrGroup schemaVersionAttrGroupParam = new SchemaVersionAttrGroup();
        schemaVersionAttrGroupParam.setLanguageCode(LanguageCodeType.EN);
        schemaVersionAttrGroupParam.setSchemaVersion("1.0");
        schemaVersionAttrGroupParam.setActionCode(ActionCodeType.NEW);

        standbyPriorityDTO.setDocumentHeader(documentHeaderDTO);
        standbyPriorityDTO.setIsDefault(true);
        standbyPriorityDTO.setSchemaVersionAttrGroup(schemaVersionAttrGroupParam);

        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CreateDefaultStandbyPriorityListener.populateCreateSPDocumentDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Standbypriority Info DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param defaultStandbyPriorityVO , not null.
     * </pre>
     */
    private void populateCreateSPInfoDTO(final StandbyPriorityType standbyPriorityDTO,
        final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CreateDefaultStandbyPriorityListener.populateCreateSPInfoDTO");

        if (defaultStandbyPriorityVO != null) {
            final StandbyPriorityInfoType standbyPriorityInfoDTO = new StandbyPriorityInfoType();

            if (defaultStandbyPriorityVO.getPriorityStatusCode() != null) {
                standbyPriorityInfoDTO.setPriorityStatusCode(defaultStandbyPriorityVO.getPriorityStatusCode());
            }

            if (defaultStandbyPriorityVO.getPriorityStatusDesc() != null) {
                standbyPriorityInfoDTO.setPriorityStatusDescription(defaultStandbyPriorityVO.getPriorityStatusDesc());
            }

            if (defaultStandbyPriorityVO.getCanBeDisembarked() != null) {

                if (defaultStandbyPriorityVO.getCanBeDisembarked().equalsIgnoreCase("Yes")) {
                    standbyPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    standbyPriorityInfoDTO.setCanBeDisembarked(false);
                }
            }

            if (defaultStandbyPriorityVO.getDateOfJoining() != null) {

                if (defaultStandbyPriorityVO.getDateOfJoining().equalsIgnoreCase("Optional")) {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.OPTIONAL);
                }
                else {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.NOT_PERMITTED);
                }
            }

            standbyPriorityDTO.setInfo(standbyPriorityInfoDTO);
        }

        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CreateDefaultStandbyPriorityListener.populateCreateSPInfoDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Standbypriority BookingStatus Info DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param defaultStandbyPriorityVO , not null.
     * </pre>
     */
    private void populateCreateSPBookingStatusDTO(final StandbyPriorityType standbyPriorityDTO,
        final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Enter CreateDefaultStandbyPriorityListener.populateCreateSPBookingStatusDTO");

        // String holdsSpaceAvailableTicket = "";
        final BookingStatusInfoType bookingStatusInfoDTO = new BookingStatusInfoType();
        final BookingStatusPriorityInfoType bookedPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType noRecPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType waitlistPriorityinfoDTO = new BookingStatusPriorityInfoType();
        final OpenPriorityInfoType openPriorityInfoDTO = new OpenPriorityInfoType();

        if (defaultStandbyPriorityVO != null) {
            if (defaultStandbyPriorityVO.getBookedPriorityValue() != 0
                && defaultStandbyPriorityVO.getBookedPriorityValue() != 0
                && defaultStandbyPriorityVO.getBookedCanBeDisembarked() != null) {

                bookedPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getBookedPriorityValue());

                if (defaultStandbyPriorityVO.getBookedCanBeDisembarked().equalsIgnoreCase("Yes")) {
                    bookedPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    bookedPriorityInfoDTO.setCanBeDisembarked(false);
                }

                bookingStatusInfoDTO.setBookedPriorityInfo(bookedPriorityInfoDTO);
            }

            if (defaultStandbyPriorityVO.getNoRecPriorityValue() != 0
                && defaultStandbyPriorityVO.getNoRecPriorityValue() != 0
                && defaultStandbyPriorityVO.getNoRecCanBeDisembarked() != null) {

                noRecPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getNoRecPriorityValue());

                if (defaultStandbyPriorityVO.getNoRecCanBeDisembarked().equalsIgnoreCase("Yes")) {
                    noRecPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    noRecPriorityInfoDTO.setCanBeDisembarked(false);
                }

                bookingStatusInfoDTO.setNorecPriorityInfo(noRecPriorityInfoDTO);
            }

            if (defaultStandbyPriorityVO.getWaitlistCanBeDisembarked() != null
                && defaultStandbyPriorityVO.getWaitlistPriorityValue() != 0
                && defaultStandbyPriorityVO.getWaitlistPriorityValue() != 0) {

                waitlistPriorityinfoDTO.setPriorityValue(defaultStandbyPriorityVO.getWaitlistPriorityValue());

                if (defaultStandbyPriorityVO.getWaitlistCanBeDisembarked().equalsIgnoreCase("Yes")) {
                    waitlistPriorityinfoDTO.setCanBeDisembarked(true);
                }
                else {
                    waitlistPriorityinfoDTO.setCanBeDisembarked(false);
                }

                bookingStatusInfoDTO.setWaitlistPriorityinfo(waitlistPriorityinfoDTO);
                standbyPriorityDTO.setBookingStatusInfo(bookingStatusInfoDTO);
            }

            if (defaultStandbyPriorityVO.getOpenTicketPriorityValue() != 0
                && defaultStandbyPriorityVO.getOpenTicketPriorityValue() != 0
                && defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked() != null
                && defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket() != null) {

                if (defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked().equalsIgnoreCase("Yes")) {
                    openPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    openPriorityInfoDTO.setCanBeDisembarked(false);
                }

                if (defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket().equalsIgnoreCase("Yes")) {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(true);
                }
                else {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(false);
                }

                openPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getOpenTicketPriorityValue());
                bookingStatusInfoDTO.setOpenPriorityInfo(openPriorityInfoDTO);
            }
        }

        CreateDefaultStandbyPriorityListener.LOGGER
            .debug("Exit CreateDefaultStandbyPriorityListener.populateCreateSPBookingStatusDTO");
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
