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
import aero.sita.horizon.schemas.metadata.v1.DocumentIDType;
import aero.sita.horizon.schemas.metadata.v1.LanguageCodeType;
import aero.sita.horizon.schemas.metadata.v1.SchemaVersionAttrGroup;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusPriorityInfoType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.ConfirmStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.CreateStandbyPriority;
import aero.sita.voyager.checkin.transferobjects.v9.DateOfJoiningRequiredType;
import aero.sita.voyager.checkin.transferobjects.v9.OpenPriorityInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPrioritiesType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityInfoType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.UpdateStandbyPriority;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class UpdateDefaultStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
    .getLogger(UpdateDefaultStandbyPriorityListener.class);
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
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener:actionPerformed()");

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
            defaultSPVOList = getModifiedRecords(defaultSPVOList);

            if (defaultSPVOList != null && defaultSPVOList.size() > 0) {
                updateDefaultStandByPriority(defaultSPVOList);
            }
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener:actionPerformed()");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method is to filter the modified record from the list.
     * 
     * @param defaultSPVOList , not null.
     * @return list , never null.
     * </pre>
     */
    private List<DefaultStandbyPriorityVO> getModifiedRecords(final List<DefaultStandbyPriorityVO> defaultSPVOList) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener:getModifiedRecords");

        // StandbyPrioritiesType standbyPrioritiesDTO = null;
        List<DefaultStandbyPriorityVO> defaultSPVOListTemp = new ArrayList();

        for (DefaultStandbyPriorityVO defaultStandbyPriorityVO : defaultSPVOList) {
            defaultSPVOListTemp.add(defaultStandbyPriorityVO);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITIES) != null) {

            StandbyPrioritiesType standbyPrioritiesDTO = (StandbyPrioritiesType) NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITIES);

            checkForSPModification(standbyPrioritiesDTO, defaultSPVOList, defaultSPVOListTemp);
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener:getModifiedRecords");

        return defaultSPVOListTemp;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method is to check if any existing record is modified.
     * 
     * @param standbyPrioritiesDTO , not null.
     * @param defaultSPVOList , not null.
     * @param defaultSPVOListTemp , not null.
     * </pre>
     */
    private void checkForSPModification(final StandbyPrioritiesType standbyPrioritiesDTO,
        final List<DefaultStandbyPriorityVO> defaultSPVOList, 
        final List<DefaultStandbyPriorityVO> defaultSPVOListTemp) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener:checkForSPModification");

        String priorityCode = null;
        String priorityDesc = null;
        // String dateOfJoining = null;
        String dateOfJoin = null;
        String checkPAD = null;
        String bookPAD = null;
        String norecPAD = null;
        String waitlistPAD = null;
        String openPAD = null;
        String openholdPAD = null;
        String priorityCodeFromVO = null;
        String priorityDescFromVO = null;
        String dateOfJoiningFromVO = null;
        String checkPADFromVO = null;
        String bookPADFromVO = null;
        String norecPADFromVO = null;
        String waitlistPADFromVO = null;
        String openPADFromVO = null;
        String openholdPADFromVO = null;
        // int bookPriorityValue = 0;
        // int norecPriorityValue = 0;
        // int waitlistPriorityValue = 0;
        // int openPriorityValue = 0;
        int bookPriorityValueFromVO = 0;
        int norecPriorityValueFromVO = 0;
        int waitlistPriorityValueFromVO = 0;
        int openPriorityValueFromVO = 0;

        if (standbyPrioritiesDTO.getStandbyPriorityList() != null) {
            for (StandbyPriorityType standbyPriorityDTO : standbyPrioritiesDTO.getStandbyPriorityList()) {

                for (DefaultStandbyPriorityVO defaultStandbyPriorityVO : defaultSPVOList) {

                    if (standbyPriorityDTO != null) {
                        if (standbyPriorityDTO.getInfo() != null) {
                            if (standbyPriorityDTO.getInfo().getPriorityStatusCode() != null) {
                                priorityCode = standbyPriorityDTO.getInfo().getPriorityStatusCode();
                            }

                            if (standbyPriorityDTO.getInfo().getPriorityStatusDescription() != null) {
                                priorityDesc = standbyPriorityDTO.getInfo().getPriorityStatusDescription();
                            }

                            if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired() != null) {
                                String dateOfJoining = standbyPriorityDTO.getInfo().getDateOfJoiningRequired()
                                .toString();

                                if (dateOfJoining != null
                                    && dateOfJoining.equalsIgnoreCase(StandbyPriorityConstants.DOJ_NOT_PERMITTED)) {
                                    dateOfJoin = StandbyPriorityConstants.DOJ_NOT_REQUIRED;
                                }
                                else {
                                    dateOfJoin = StandbyPriorityConstants.DOJ_OPTIONAL;
                                }
                            }

                            checkPAD = booleanToString(standbyPriorityDTO.getInfo().isCanBeDisembarked());
                        }

                        if (standbyPriorityDTO.getBookingStatusInfo() != null) {
                            if (standbyPriorityDTO.getBookingStatusInfo().getBookedPriorityInfo() != null) {
                                bookPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getBookedPriorityInfo().isCanBeDisembarked());

                                /*
                                 * bookPriorityValue =
                                 * standbyPriorityDTO.getBookingStatusInfo
                                 * ().getBookedPriorityInfo()
                                 * .getPriorityValue();
                                 */
                            }

                            if (standbyPriorityDTO.getBookingStatusInfo().getNorecPriorityInfo() != null) {
                                norecPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getNorecPriorityInfo().isCanBeDisembarked());

                                /*
                                 * norecPriorityValue =
                                 * standbyPriorityDTO.getBookingStatusInfo
                                 * ().getNorecPriorityInfo()
                                 * .getPriorityValue();
                                 */
                            }

                            if (standbyPriorityDTO.getBookingStatusInfo().getWaitlistPriorityinfo() != null) {
                                waitlistPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getWaitlistPriorityinfo().isCanBeDisembarked());

                                /*
                                 * waitlistPriorityValue =
                                 * standbyPriorityDTO.getBookingStatusInfo()
                                 * .getWaitlistPriorityinfo
                                 * ().getPriorityValue();
                                 */
                            }

                            if (standbyPriorityDTO.getBookingStatusInfo().getOpenPriorityInfo() != null) {
                                openPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getOpenPriorityInfo().isCanBeDisembarked());

                                openholdPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getOpenPriorityInfo().isHoldsSpaceAvailableTicket());

                                /*
                                 * openPriorityValue =
                                 * standbyPriorityDTO.getBookingStatusInfo
                                 * ().getOpenPriorityInfo() .getPriorityValue();
                                 */
                            }
                        }

                        if (defaultStandbyPriorityVO != null) {
                            if (defaultStandbyPriorityVO.getPriorityStatusCode() != null) {
                                priorityCodeFromVO = defaultStandbyPriorityVO.getPriorityStatusCode();
                            }

                            if (defaultStandbyPriorityVO.getPriorityStatusDesc() != null) {
                                priorityDescFromVO = defaultStandbyPriorityVO.getPriorityStatusDesc();
                            }

                            if (defaultStandbyPriorityVO.getDateOfJoining() != null) {
                                dateOfJoiningFromVO = defaultStandbyPriorityVO.getDateOfJoining();
                            }

                            if (defaultStandbyPriorityVO.getCanBeDisembarked() != null) {
                                checkPADFromVO = defaultStandbyPriorityVO.getCanBeDisembarked();
                            }

                            if (defaultStandbyPriorityVO.getBookedCanBeDisembarked() != null) {
                                bookPADFromVO = defaultStandbyPriorityVO.getBookedCanBeDisembarked();
                            }

                            if (defaultStandbyPriorityVO.getNoRecCanBeDisembarked() != null) {
                                norecPADFromVO = defaultStandbyPriorityVO.getNoRecCanBeDisembarked();
                            }

                            if (defaultStandbyPriorityVO.getWaitlistCanBeDisembarked() != null) {
                                waitlistPADFromVO = defaultStandbyPriorityVO.getWaitlistCanBeDisembarked();
                            }

                            if (defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked() != null) {
                                openPADFromVO = defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked();
                            }

                            if (defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket() != null) {
                                openholdPADFromVO = defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket();
                            }

                            bookPriorityValueFromVO = defaultStandbyPriorityVO.getBookedPriorityValue();
                            norecPriorityValueFromVO = defaultStandbyPriorityVO.getNoRecPriorityValue();
                            waitlistPriorityValueFromVO = defaultStandbyPriorityVO.getWaitlistPriorityValue();
                            openPriorityValueFromVO = defaultStandbyPriorityVO.getOpenTicketPriorityValue();
                        }

                        if (priorityCode != null && priorityCodeFromVO != null && priorityDesc != null
                            && priorityDescFromVO != null && checkPAD != null && checkPADFromVO != null
                            && dateOfJoin != null && dateOfJoiningFromVO != null && bookPAD != null
                            && bookPADFromVO != null && norecPAD != null && norecPADFromVO != null
                            && waitlistPAD != null && waitlistPADFromVO != null && openPAD != null
                            && openPADFromVO != null && openholdPAD != null && openholdPADFromVO != null) {

                            if (priorityCode.equalsIgnoreCase(priorityCodeFromVO)
                                && priorityDesc.equalsIgnoreCase(priorityDescFromVO)
                                && checkPAD.equalsIgnoreCase(checkPADFromVO)
                                && dateOfJoin.equalsIgnoreCase(dateOfJoiningFromVO)
                                && bookPAD.equalsIgnoreCase(bookPADFromVO)
                                && norecPAD.equalsIgnoreCase(norecPADFromVO)
                                && waitlistPAD.equalsIgnoreCase(waitlistPADFromVO)
                                && openPAD.equalsIgnoreCase(openPADFromVO)
                                && openholdPAD.equalsIgnoreCase(openholdPADFromVO)
                                && (standbyPriorityDTO.getBookingStatusInfo().getBookedPriorityInfo()
                                    .getPriorityValue()) == bookPriorityValueFromVO
                                    && (standbyPriorityDTO.getBookingStatusInfo().getNorecPriorityInfo()
                                        .getPriorityValue()) == norecPriorityValueFromVO
                                    && (standbyPriorityDTO.getBookingStatusInfo().getWaitlistPriorityinfo()
                                        .getPriorityValue()) == waitlistPriorityValueFromVO
                                        && (standbyPriorityDTO.getBookingStatusInfo().getOpenPriorityInfo()
                                            .getPriorityValue()) == openPriorityValueFromVO) {

                                defaultSPVOListTemp.remove(defaultStandbyPriorityVO);
                                break;
                            }
                        }
                    }
                }
            }
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener:checkForSPModification");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method for service call of create stand by priority.
     * 
     * @param defaultSPVOList , not null.
     * </pre>
     */
    private void updateDefaultStandByPriority(final List<DefaultStandbyPriorityVO> defaultSPVOList) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.updateDefaultStandByPriority");

        int success = 0;
        int failed = 0;
        // int row = 0;
        List failedRowsList = new ArrayList();
        List errorCodesList = new ArrayList();
        DefaultTableModel defaultTableModel = null;
        // CreateStandbyPriorityType createStandbyPriorityDTO = null;
        // UpdateStandbyPriorityType updateStandbyPriorityRequestDTO =
        // null;

        if (controller.getDefaultSPFilterTablePanel().getModel() != null
            && controller.getDefaultSPFilterTablePanel().getModel() != null) {
            defaultTableModel = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();
        }

        try {

            if (controller.getManageStandbyPriorityProxy() != null) {
                for (DefaultStandbyPriorityVO defaultStandbyPriorityVO : defaultSPVOList) {
                    int row = defaultStandbyPriorityVO.getRowNumber();

                    try {
                        if (defaultStandbyPriorityVO.getDocumentId() != null
                            && defaultStandbyPriorityVO.getDocumentId().trim().length() > 0) {
                            UpdateStandbyPriority updateStandbyPriorityRequestDTO = 
                                populateUpdateSPRequestDTO(defaultStandbyPriorityVO);

                            controller.getManageStandbyPriorityProxy().updateStandbyPriority(
                                updateStandbyPriorityRequestDTO);
                        }
                        else {
                            CreateStandbyPriority createStandbyPriorityDTO = 
                                populateCreateSPRequestDTO(defaultStandbyPriorityVO);

                            controller.getManageStandbyPriorityProxy().createStandbyPriority(createStandbyPriorityDTO);

                        }

                        if (defaultStandbyPriorityVO.getPriorityStatusCode() != null && defaultTableModel != null) {
                            defaultTableModel.setValueAt(defaultStandbyPriorityVO.getPriorityStatusCode(),
                                defaultStandbyPriorityVO.getRowNumber(), COLUMN_VALUE_FIFTEEN);
                        }

                        success++;
                    }
                    catch (final CheckInException ex) {
                        failed++;
                        failedRowsList.add(row);
                        errorCodesList.add(ex);

                        UpdateDefaultStandbyPriorityListener.LOGGER
                        .error("Error in UpdateDefaultStandbyPriorityListener" + ".updateDefaultStandByPriority : "
                            + ex);
                    }
                }

                displayMessage(success, failed, defaultSPVOList, errorCodesList, failedRowsList);
            }
        }
        catch (final Exception ex) {
            UpdateDefaultStandbyPriorityListener.LOGGER
            .error("Error in UpdateDefaultStandbyPriorityListener.updateDefaultStandByPriority : "
                + ex.getMessage());
            // ex.printStackTrace();
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.updateDefaultStandByPriority");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to display the message on Update.
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
        UpdateDefaultStandbyPriorityListener.LOGGER.debug("Enter UpdateDefaultStandbyPriorityListener.displayMessage");

        if (failed == 0 && success > 0) {
            DcsMessagePanelHandler.handleSuccessMessage(success + " Success ");
            DcsMessagePanelHandler.handleSuccessMessage("CKI-10830");

            controller.getDefaultSPScreenHelper().updateScreenUI();
        }
        else if (failed > 0 && success > 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");

            controller.getDefaultSPScreenHelper().updateScreenUI();
        }
        else if (failed > 0 && success == 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(failed + " Failed");

            controller.getDefaultSPScreenHelper().updateScreenUI();
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

        UpdateDefaultStandbyPriorityListener.LOGGER.debug("Exit UpdateDefaultStandbyPriorityListener.displayMessage");
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
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.highlightFailedRows");

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
            UpdateDefaultStandbyPriorityListener.LOGGER
            .error("Error in UpdateDefaultStandbyPriorityListener.highlightFailedRows : " + ex.getMessage());
            // ex.printStackTrace();
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.highlightFailedRows");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return updateStandbyPriorityRequestDTO , never null.
     * </pre>
     */
    private UpdateStandbyPriority populateUpdateSPRequestDTO(
        final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateUpdateSPRequestDTO");

        final UpdateStandbyPriority updateStandbyPriorityRequestDTO = new UpdateStandbyPriority();
        final StandbyPriorityType standbyPriorityDTO = new StandbyPriorityType();

        populateUpdateSPDocumentDTO(standbyPriorityDTO, defaultStandbyPriorityVO);
        populateUpdateSPInfoDTO(standbyPriorityDTO, defaultStandbyPriorityVO);
        populateUpdateSPBookingStatusDTO(standbyPriorityDTO, defaultStandbyPriorityVO);

        updateStandbyPriorityRequestDTO.setStandbyPriority(standbyPriorityDTO);
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateUpdateSPRequestDTO");

        return updateStandbyPriorityRequestDTO;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Standbypriority Documentheader DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param defaultStandbyPriorityVO , not null.
     * </pre>
     */
    private void populateUpdateSPDocumentDTO(final StandbyPriorityType standbyPriorityDTO,
        final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateUpdateSPDocumentDTO");

        final DocumentHeaderType documentHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType administrativeRecordDTO = new AdministrativeRecordType();
        final DocumentIDType documentIDType=new DocumentIDType();
        documentIDType.setString(defaultStandbyPriorityVO.getDocumentId());
        administrativeRecordDTO.setDocumentID(documentIDType);

        
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(DcsSIAMInfoUtil.getCurrentSubscriber());
        administrativeRecordDTO.setSubscriberReference(subscriberIDType);
        documentHeaderDTO.setAdministrativeRecord(administrativeRecordDTO);

        final SchemaVersionAttrGroup schemaVersionAttrGroupParam = new SchemaVersionAttrGroup();
        schemaVersionAttrGroupParam.setLanguageCode(LanguageCodeType.EN);
        schemaVersionAttrGroupParam.setSchemaVersion("1.0");

        standbyPriorityDTO.setDocumentHeader(documentHeaderDTO);
        standbyPriorityDTO.setIsDefault(true);
        standbyPriorityDTO.setSchemaVersionAttrGroup(schemaVersionAttrGroupParam);

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateUpdateSPDocumentDTO");
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
    private void populateUpdateSPInfoDTO(final StandbyPriorityType standbyPriorityDTO,
        final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateUpdateSPInfoDTO");
        // String priorityStatusCode = "";
        // String priorityStatusDescription = "";
        // String canBeDisembarked = "";
        // String dateOfJoiningRequired = "";

        if (defaultStandbyPriorityVO != null) {
            final StandbyPriorityInfoType standbyPriorityInfoDTO = new StandbyPriorityInfoType();

            if (defaultStandbyPriorityVO.getPriorityStatusCode() != null) {
                // priorityStatusCode =
                // defaultStandbyPriorityVO.getPriorityStatusCode();
                standbyPriorityInfoDTO.setPriorityStatusCode(defaultStandbyPriorityVO.getPriorityStatusCode());
            }

            if (defaultStandbyPriorityVO.getPriorityStatusDesc() != null) {
                // priorityStatusDescription =
                // defaultStandbyPriorityVO.getPriorityStatusDesc();
                standbyPriorityInfoDTO.setPriorityStatusDescription(defaultStandbyPriorityVO.getPriorityStatusDesc());
            }

            if (defaultStandbyPriorityVO.getCanBeDisembarked() != null) {
                // canBeDisembarked =
                // defaultStandbyPriorityVO.getCanBeDisembarked();

                if ((defaultStandbyPriorityVO.getCanBeDisembarked()).equalsIgnoreCase("Yes")) {
                    standbyPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    standbyPriorityInfoDTO.setCanBeDisembarked(false);
                }
            }

            if (defaultStandbyPriorityVO.getDateOfJoining() != null) {
                // dateOfJoiningRequired =
                // defaultStandbyPriorityVO.getDateOfJoining();

                if ((defaultStandbyPriorityVO.getDateOfJoining()).equalsIgnoreCase("Optional")) {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.OPTIONAL);
                }
                else {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.NOT_PERMITTED);
                }
            }

            standbyPriorityDTO.setInfo(standbyPriorityInfoDTO);
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateUpdateSPInfoDTO");
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
    private void populateUpdateSPBookingStatusDTO(final StandbyPriorityType standbyPriorityDTO,
        final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateUpdateSPBookingStatusDTO");
        // int bookedPriorityValue = 0;
        // int noRecPriorityValue = 0;
        // int openPriorityValue = 0;
        // int waitPriorityValue = 0;
        // String bookedCanbeDisembarked = "";
        // String noRecCanbeDisembarked = "";
        // String openCanBeDisembarked = "";
        // String holdsSpaceAvailableTicket = "";
        // String waitCanBeDisembarked = "";
        final BookingStatusInfoType bookingStatusInfoDTO = new BookingStatusInfoType();
        final BookingStatusPriorityInfoType bookedPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType noRecPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType waitlistPriorityinfoDTO = new BookingStatusPriorityInfoType();
        final OpenPriorityInfoType openPriorityInfoDTO = new OpenPriorityInfoType();

        if (defaultStandbyPriorityVO != null) {
            if (defaultStandbyPriorityVO.getBookedPriorityValue() != 0
                && defaultStandbyPriorityVO.getBookedPriorityValue() != 0
                && defaultStandbyPriorityVO.getBookedCanBeDisembarked() != null) {
                // bookedPriorityValue =
                // defaultStandbyPriorityVO.getBookedPriorityValue();
                // bookedCanbeDisembarked =
                // defaultStandbyPriorityVO.getBookedCanBeDisembarked();

                bookedPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getBookedPriorityValue());

                if ((defaultStandbyPriorityVO.getBookedCanBeDisembarked()).equalsIgnoreCase("Yes")) {
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
                // noRecPriorityValue =
                // defaultStandbyPriorityVO.getNoRecPriorityValue();

                // noRecCanbeDisembarked =
                // defaultStandbyPriorityVO.getNoRecCanBeDisembarked();

                noRecPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getNoRecPriorityValue());

                if ((defaultStandbyPriorityVO.getNoRecCanBeDisembarked()).equalsIgnoreCase("Yes")) {
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

                // waitPriorityValue =
                // defaultStandbyPriorityVO.getWaitlistPriorityValue();

                // waitCanBeDisembarked =
                // defaultStandbyPriorityVO.getWaitlistCanBeDisembarked();

                waitlistPriorityinfoDTO.setPriorityValue(defaultStandbyPriorityVO.getWaitlistPriorityValue());

                if ((defaultStandbyPriorityVO.getWaitlistCanBeDisembarked())
                    .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
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

                // openPriorityValue =
                // defaultStandbyPriorityVO.getOpenTicketPriorityValue();

                // openCanBeDisembarked =
                // defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked();

                // holdsSpaceAvailableTicket =
                // defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket();

                if ((defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked())
                    .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    openPriorityInfoDTO.setCanBeDisembarked(false);
                }

                if ((defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket())
                    .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(true);
                }
                else {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(false);
                }
                openPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getOpenTicketPriorityValue());
                bookingStatusInfoDTO.setOpenPriorityInfo(openPriorityInfoDTO);
            }
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateUpdateSPBookingStatusDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return createStandbyPriorityDTO , never null.
     * </pre>
     */
    private CreateStandbyPriority populateCreateSPRequestDTO(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateCreateSPRequestDTO");

        final CreateStandbyPriority createStandbyPriorityDTO = new CreateStandbyPriority();
        final StandbyPriorityType standbyPriorityDTO = new StandbyPriorityType();

        populateCreateSPDocumentDTO(standbyPriorityDTO);
        populateCreateSPInfoDTO(standbyPriorityDTO, defaultStandbyPriorityVO);
        populateCreateSPBookingStatusDTO(standbyPriorityDTO, defaultStandbyPriorityVO);

        createStandbyPriorityDTO.setStandbyPriority(standbyPriorityDTO);

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateCreateSPRequestDTO");

        return createStandbyPriorityDTO;
    }

    /**
     * <pre>
     * populateCreateSPDocumentDTO.
     * 
     * @param standbyPriorityDTO , not null.
     * </pre>
     */
    private void populateCreateSPDocumentDTO(final StandbyPriorityType standbyPriorityDTO) {
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateCreateSPDocumentDTO");

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

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateCreateSPDocumentDTO");
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
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateCreateSPInfoDTO");

        // String priorityStatusCode = "";
        // String priorityStatusDescription = "";
        // String canBeDisembarked = "";
        // String dateOfJoiningRequired = "";

        if (defaultStandbyPriorityVO != null) {
            final StandbyPriorityInfoType standbyPriorityInfoDTO = new StandbyPriorityInfoType();

            if (defaultStandbyPriorityVO.getPriorityStatusCode() != null) {
                // priorityStatusCode =
                // defaultStandbyPriorityVO.getPriorityStatusCode();
                standbyPriorityInfoDTO.setPriorityStatusCode(defaultStandbyPriorityVO.getPriorityStatusCode());
            }

            if (defaultStandbyPriorityVO.getPriorityStatusDesc() != null) {
                // priorityStatusDescription =
                // defaultStandbyPriorityVO.getPriorityStatusDesc();
                standbyPriorityInfoDTO.setPriorityStatusDescription(defaultStandbyPriorityVO.getPriorityStatusDesc());
            }

            if (defaultStandbyPriorityVO.getCanBeDisembarked() != null) {
                // canBeDisembarked =
                // defaultStandbyPriorityVO.getCanBeDisembarked();

                if ((defaultStandbyPriorityVO.getCanBeDisembarked()).equalsIgnoreCase("Yes")) {
                    standbyPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    standbyPriorityInfoDTO.setCanBeDisembarked(false);
                }
            }

            if (defaultStandbyPriorityVO.getDateOfJoining() != null) {
                // dateOfJoiningRequired =
                // defaultStandbyPriorityVO.getDateOfJoining();

                if ((defaultStandbyPriorityVO.getDateOfJoining()).equalsIgnoreCase("Optional")) {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.OPTIONAL);
                }
                else {
                    standbyPriorityInfoDTO.setDateOfJoiningRequired(DateOfJoiningRequiredType.NOT_PERMITTED);
                }
            }

            standbyPriorityDTO.setInfo(standbyPriorityInfoDTO);
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateCreateSPInfoDTO");
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
        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateCreateSPBookingStatusDTO");

        // int bookedPriorityValue = 0;
        // int noRecPriorityValue = 0;
        // int openPriorityValue = 0;
        // int waitPriorityValue = 0;
        // String bookedCanbeDisembarked = "";
        // String noRecCanbeDisembarked = "";
        // String openCanBeDisembarked = "";
        // String holdsSpaceAvailableTicket = "";
        // String waitCanBeDisembarked = "";
        final BookingStatusInfoType bookingStatusInfoDTO = new BookingStatusInfoType();
        final BookingStatusPriorityInfoType bookedPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType noRecPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType waitlistPriorityinfoDTO = new BookingStatusPriorityInfoType();
        final OpenPriorityInfoType openPriorityInfoDTO = new OpenPriorityInfoType();

        if (defaultStandbyPriorityVO != null) {
            if (defaultStandbyPriorityVO.getBookedPriorityValue() != 0
                && defaultStandbyPriorityVO.getBookedPriorityValue() != 0
                && defaultStandbyPriorityVO.getBookedCanBeDisembarked() != null) {
                // bookedPriorityValue =
                // defaultStandbyPriorityVO.getBookedPriorityValue();
                // bookedCanbeDisembarked =
                // defaultStandbyPriorityVO.getBookedCanBeDisembarked();

                bookedPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getBookedPriorityValue());

                if ((defaultStandbyPriorityVO.getBookedCanBeDisembarked()).equalsIgnoreCase("Yes")) {
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
                // noRecPriorityValue =
                // defaultStandbyPriorityVO.getNoRecPriorityValue();

                // noRecCanbeDisembarked =
                // defaultStandbyPriorityVO.getNoRecCanBeDisembarked();

                noRecPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getNoRecPriorityValue());

                if ((defaultStandbyPriorityVO.getNoRecCanBeDisembarked()).equalsIgnoreCase("Yes")) {
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

                // waitPriorityValue =
                // defaultStandbyPriorityVO.getWaitlistPriorityValue();

                // waitCanBeDisembarked =
                // defaultStandbyPriorityVO.getWaitlistCanBeDisembarked();

                waitlistPriorityinfoDTO.setPriorityValue(defaultStandbyPriorityVO.getWaitlistPriorityValue());

                if ((defaultStandbyPriorityVO.getWaitlistCanBeDisembarked()).equalsIgnoreCase("Yes")) {
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

                // openPriorityValue =
                // defaultStandbyPriorityVO.getOpenTicketPriorityValue();

                // openCanBeDisembarked =
                // defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked();

                // holdsSpaceAvailableTicket =
                // defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket();

                if ((defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked()).equalsIgnoreCase("Yes")) {
                    openPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    openPriorityInfoDTO.setCanBeDisembarked(false);
                }

                if ((defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket()).equalsIgnoreCase("Yes")) {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(true);
                }
                else {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(false);
                }

                openPriorityInfoDTO.setPriorityValue(defaultStandbyPriorityVO.getOpenTicketPriorityValue());
                bookingStatusInfoDTO.setOpenPriorityInfo(openPriorityInfoDTO);
            }
        }

        UpdateDefaultStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateCreateSPBookingStatusDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * This method is to convert boolean to string.
     * @param booleanValue , not null.
     * @return String , never null.
     * </pre>
     */
    private String booleanToString(final boolean booleanValue) {
        if (booleanValue) {
            return StandbyPriorityConstants.YES_VALUE;
        }

        return StandbyPriorityConstants.NO_VALUE;
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
