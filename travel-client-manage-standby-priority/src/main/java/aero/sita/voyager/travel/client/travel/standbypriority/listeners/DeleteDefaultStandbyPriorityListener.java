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
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
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
import aero.sita.voyager.travel.client.travel.standbypriority.utils.StandbyPriorityUtil;
import aero.sita.voyager.travel.client.travel.standbypriority.valueobject.DefaultStandbyPriorityVO;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.horizon.schemas.metadata.v1.AdministrativeRecordType;
import aero.sita.horizon.schemas.metadata.v1.DocumentHeaderType;
import aero.sita.horizon.schemas.metadata.v1.DocumentIDType;
import aero.sita.horizon.schemas.metadata.v1.LanguageCodeType;
import aero.sita.horizon.schemas.metadata.v1.SchemaVersionAttrGroup;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.ConfirmStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.DeleteStandbyPriority;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPrioritiesType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Delete Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class DeleteDefaultStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
        .getLogger(DeleteDefaultStandbyPriorityListener.class);

    /**
     * Number Fifteen.
     */
    private static final int FIFTEEN = 15;

    /**
     * Instance of StandbyPriorityController.
     */
    StandbyPriorityController controller;

    /**
     * Delete Selected Rows Button.
     */
    private JButton defaultSPDeleteSelRowsButton;

    /**
     * NGAFFilterTablePanel for Default Standby Priority Filter Table.
     */
    private NGAFFilterTablePanel defaultSPFilterTablePanel;

    /**
     * <pre>
     * <b>Description : </b>
     * Action Performed by Delete Selected Rows button.
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public final void actionPerformed(final ActionEvent actionEvent) {
        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Enter DeleteDefaultStandbyPriorityListener:actionPerformed()");

        int[] selectedRows = {};
        boolean isValid = false;

        DcsMessagePanelHandler.clearAllMessages();

        if (controller.getDefaultSPFilterTablePanel() != null) {
            selectedRows = controller.getDefaultSPFilterTablePanel().getTable().getSelectedRows();
            isValid = isValid(selectedRows);
        }

        if (isValid) {
            deleteDefaultStandByPriority(selectedRows);
        }

        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Exit DeleteDefaultStandbyPriorityListener:actionPerformed()");
    }

    /**
     * 
     * This method will be called to validate the rows selected for deletion.
     * 
     * @param selectedRows , not null.
     * @return boolean , never null.
     */
    private boolean isValid(final int[] selectedRows) {
        try {
            int isDocIDCount = 0;
            Integer totalSize = 0;
            boolean isValid = true;
            final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();
            if (controller.getDefaultSPFilterTablePanel() != null
                && controller.getDefaultSPFilterTablePanel().getModel() != null) {
                final DefaultTableModel dm = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();

                if (NGAFContextUtil.getApplicationContext().
                    get(StandbyPriorityConstants.DEFAULT_SP_RESPONSE_ROWCOUNT) != null) {
                    totalSize = (Integer) NGAFContextUtil.getApplicationContext().get(
                        StandbyPriorityConstants.DEFAULT_SP_RESPONSE_ROWCOUNT);
                }

                if (totalSize.intValue() == 0) {
                    return true;
                }
                else {
                    if (selectedRows.length > 0) {
                        for (int i = 0; i < selectedRows.length; i++) {

                            if (dm.getValueAt(selectedRows[i], FIFTEEN) != null && dm.getValueAt(selectedRows[i], FIFTEEN).toString().trim().length() > 0) {
                                isDocIDCount++;
                            }
                        }

                        if ((isDocIDCount == 0 && selectedRows.length > 0)) {
                            final int isDelete = standbyPriorityUtil.confirmDelete(
                                StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                                StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_MSG);
                            if (isDelete == 0) {
                                if (controller.getDefaultSPFilterTablePanel() != null
                                    && controller.getDefaultSPFilterTablePanel().getModel() != null) {
                                    return true;
                                }
                            }
                            else {
                                DcsMessagePanelHandler.clearAllMessages();
                                return false;
                            }
                        }

                        if (controller.getDefaultSPFilterTablePanel().getTable() != null) {
                            NGAFStandardTable defaultSPStandardTable = (NGAFStandardTable) controller
                                .getDefaultSPFilterTablePanel().getTable();
                            isValid = controller.getDeleteStanbyPriorityValidator().validateStandbyPriority(dm,
                                defaultSPStandardTable);
                        }
                        //List<DefaultStandbyPriorityVO> defaultSPVOList = null;
                        boolean isSave = false;
                        List<DefaultStandbyPriorityVO> defaultSPVOList = controller.getDefaultSPScreenHelper().fetchDefaultSPTableData();
                        defaultSPVOList = getModifiedRecords(defaultSPVOList);
                        if (defaultSPVOList != null) {
                            if (!isValid || defaultSPVOList.size() > 0) {
                                isSave = true;
                            }
                        }
                        if (isSave || (isDocIDCount > 0 && isDocIDCount != selectedRows.length)) {
                            final int isDelete = standbyPriorityUtil.confirmDelete(
                                StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                                StandbyPriorityConstants.CONFIRM_SAVE_AND_DELETE_DIALOG_MSG);
                            if (isDelete == 0) {
                                if (isDocIDCount == totalSize.intValue()) {
                                    DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10824");
                                    return false;
                                }
                                else if (controller.getDefaultSPFilterTablePanel() != null
                                    && controller.getDefaultSPFilterTablePanel().getModel() != null) {
                                    return true;
                                }
                            }
                            else {
                                DcsMessagePanelHandler.clearAllMessages();
                                return false;
                            }
                        }
                        else {
                            if (isDocIDCount == totalSize.intValue()) {
                                DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10824");
                                return false;
                            }

                            final int isDelete = standbyPriorityUtil.confirmDelete(
                                StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                                StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_MSG);
                            if (isDelete == 0) {
                                if (controller.getDefaultSPFilterTablePanel() != null
                                    && controller.getDefaultSPFilterTablePanel().getModel() != null) {
                                    return true;
                                }
                            }
                            else {
                                DcsMessagePanelHandler.clearAllMessages();
                                return false;
                            }

                        }

                    }
                }
            }
        }
        catch (Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());

        }

        return false;
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
        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Enter DeleteDefaultStandbyPriorityListener:getModifiedRecords");

        
        List<DefaultStandbyPriorityVO> defaultSPVOListTemp = new ArrayList();

        for (DefaultStandbyPriorityVO defaultStandbyPriorityVO : defaultSPVOList) {
            defaultSPVOListTemp.add(defaultStandbyPriorityVO);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITIES) != null) {


            checkForSPModification((StandbyPrioritiesType) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITIES), defaultSPVOList, defaultSPVOListTemp);
        }

        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Exit DeleteDefaultStandbyPriorityListener:getModifiedRecords");

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
        final List<DefaultStandbyPriorityVO> defaultSPVOList, final List<DefaultStandbyPriorityVO> 
    defaultSPVOListTemp) {
        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Enter DeleteDefaultStandbyPriorityListener:checkForSPModification");

        String priorityCode = null;
        String priorityDesc = null;
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
        int bookPriorityValue = 0;
        int norecPriorityValue = 0;
        int waitlistPriorityValue = 0;
        int openPriorityValue = 0;
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

                                if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired().toString() != null
                                    && standbyPriorityDTO.getInfo().getDateOfJoiningRequired().toString().equalsIgnoreCase(StandbyPriorityConstants.DOJ_NOT_PERMITTED)) {
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

                                bookPriorityValue = standbyPriorityDTO.getBookingStatusInfo().getBookedPriorityInfo()
                                    .getPriorityValue();
                            }

                            if (standbyPriorityDTO.getBookingStatusInfo().getNorecPriorityInfo() != null) {
                                norecPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getNorecPriorityInfo().isCanBeDisembarked());

                                norecPriorityValue = standbyPriorityDTO.getBookingStatusInfo().getNorecPriorityInfo()
                                    .getPriorityValue();
                            }

                            if (standbyPriorityDTO.getBookingStatusInfo().getWaitlistPriorityinfo() != null) {
                                waitlistPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getWaitlistPriorityinfo().isCanBeDisembarked());

                                waitlistPriorityValue = standbyPriorityDTO.getBookingStatusInfo()
                                    .getWaitlistPriorityinfo().getPriorityValue();
                            }

                            if (standbyPriorityDTO.getBookingStatusInfo().getOpenPriorityInfo() != null) {
                                openPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getOpenPriorityInfo().isCanBeDisembarked());

                                openholdPAD = booleanToString(standbyPriorityDTO.getBookingStatusInfo()
                                    .getOpenPriorityInfo().isHoldsSpaceAvailableTicket());

                                openPriorityValue = standbyPriorityDTO.getBookingStatusInfo().getOpenPriorityInfo()
                                    .getPriorityValue();
                            }
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
                        && bookPADFromVO != null && norecPAD != null && norecPADFromVO != null && waitlistPAD != null
                        && waitlistPADFromVO != null && openPAD != null && openPADFromVO != null && openholdPAD != null
                        && openholdPADFromVO != null) {

                        if (priorityCode.equalsIgnoreCase(priorityCodeFromVO)
                            && priorityDesc.equalsIgnoreCase(priorityDescFromVO)
                            && checkPAD.equalsIgnoreCase(checkPADFromVO)
                            && dateOfJoin.equalsIgnoreCase(dateOfJoiningFromVO)
                            && bookPAD.equalsIgnoreCase(bookPADFromVO) && norecPAD.equalsIgnoreCase(norecPADFromVO)
                            && waitlistPAD.equalsIgnoreCase(waitlistPADFromVO)
                            && openPAD.equalsIgnoreCase(openPADFromVO)
                            && openholdPAD.equalsIgnoreCase(openholdPADFromVO)
                            && bookPriorityValue == bookPriorityValueFromVO
                            && norecPriorityValue == norecPriorityValueFromVO
                            && waitlistPriorityValue == waitlistPriorityValueFromVO
                            && openPriorityValue == openPriorityValueFromVO) {

                            defaultSPVOListTemp.remove(defaultStandbyPriorityVO);
                            break;
                        }
                    }
                }
            }
        }

        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Exit DeleteDefaultStandbyPriorityListener:checkForSPModification");
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
     * 
     * This method will be called to get the confirmation for delete.
     * 
     * @param selectedRows , not null.
     *//*
    private void confirmDelete(final int[] selectedRows) {
        final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();

        final int isDelete = standbyPriorityUtil.confirmDelete(StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
            StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_MSG);

        if (isDelete == 0) {
            if (controller.getDefaultSPFilterTablePanel() != null
                && controller.getDefaultSPFilterTablePanel().getModel() != null) {
                deleteDefaultStandByPriority(selectedRows);
            }
        }
        else {
            DcsMessagePanelHandler.clearAllMessages();
        }
    }*/

    /**
     * 
     * This method will be called to delete the selected standby priority rows.
     * 
     * @param selectedRows , not null.
     */
    private void deleteDefaultStandByPriority(final int[] selectedRows) {
        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Enter DeleteDefaultStandbyPriorityListener:deleteDefaultStandByPriority");
        try {
            int success = 0;
            int failed = 0;
            List errorCodesList = new ArrayList();
            List<String> docIds = new ArrayList();
            final DefaultTableModel dm = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();
            for (int rows = 0; rows < selectedRows.length; rows++) {
                if (controller.getDefaultSPFilterTablePanel().getTable().getValueAt(selectedRows[rows], FIFTEEN) != null
                    && controller.getDefaultSPFilterTablePanel().getTable().getValueAt(selectedRows[rows], FIFTEEN)
                        .toString().trim().length() > 0) {
                    docIds.add(controller.getDefaultSPFilterTablePanel().getTable()
                        .getValueAt(selectedRows[rows], FIFTEEN).toString());
                }
            }

            if (docIds.size() > 0) {
                for (int j = 0; j < docIds.size(); j++) {
                    final DeleteStandbyPriority deleteStandbyPriorityDTO = populateDeleteSPRequestDTO(docIds.get(j)
                        .toString());
                    if (controller.getManageStandbyPriorityProxy() != null) {
                        try {
                            final ConfirmStandbyPriority confirmStandbyPriorityDTO = controller
                                .getManageStandbyPriorityProxy().deleteStandbyPriority(deleteStandbyPriorityDTO);

                            if (confirmStandbyPriorityDTO != null) {
                                DeleteDefaultStandbyPriorityListener.LOGGER
                                    .debug("Exit DeleteDefaultStandbyPriorityListener:confirmStandbyPriorityDTO"
                                        + confirmStandbyPriorityDTO);
                            }
                            success++;
                        }
                        catch (final CheckInException ex) {
                            failed++;
                            errorCodesList.add(ex);
                            LOGGER.debug("Error" + ex);
                        }
                    }
                }
                if (docIds.size() > 0) {
                    controller.getDefaultSPScreenHelper().updateScreenUI();
                }

                displayMessage(success, failed, errorCodesList);

            }
            else {
                for (int i = 0; i < selectedRows.length; i++) {
                    dm.removeRow(selectedRows[i] - i);
                }
            }
        }
        catch (final Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());
            // ex.printStackTrace();
        }

        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Exit DeleteDefaultStandbyPriorityListener:deleteDefaultStandByPriority");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to display the message on Create.
     * 
     * @param success , not null.
     * @param failed , not null.
     * @param errorCodesList , not null.
     * </pre>
     */
    private void displayMessage(final int success, final int failed, final List errorCodesList) {
        controller.getDefaultSPFilterTablePanel().clearTextOfFilterRow();
        if (failed == 0 && success > 0) {
            DcsMessagePanelHandler.handleSuccessMessage(success + " Success ");
            DcsMessagePanelHandler.handleSuccessMessage("CKI-10825");
        }
        else if (failed > 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");
        }

        if (failed > 0 && errorCodesList != null && errorCodesList.size() > 0) {
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
        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Enter DeleteDefaultStandbyPriorityListener:populateDeletePSPRequestDTO()");

        final DeleteStandbyPriority deleteStandbyPriorityDTO = new DeleteStandbyPriority();
        final StandbyPriorityType standByPriorityDTO = new StandbyPriorityType();

        standByPriorityDTO.setIsDefault(true);

        final SchemaVersionAttrGroup schemaVerGroup = new SchemaVersionAttrGroup();
        schemaVerGroup.setSchemaVersion("1.0");
        schemaVerGroup.setLanguageCode(LanguageCodeType.EN);
        standByPriorityDTO.setSchemaVersionAttrGroup(schemaVerGroup);

        populateDeleteSPDocumentDTO(standByPriorityDTO, docId);

        deleteStandbyPriorityDTO.setStandbyPriority(standByPriorityDTO);

        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Exit DeleteDefaultStandbyPriorityListener:populateDeletePSPRequestDTO()");

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
        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Enter DeleteDefaultStandbyPriorityListener:populateDeleteInfoDTO()");

        final DocumentHeaderType docHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType adminRecodeDTO = new AdministrativeRecordType();
        final DocumentIDType documentIDType=new DocumentIDType();
        documentIDType.setString(docId);
        adminRecodeDTO.setDocumentID(documentIDType);
        
        adminRecodeDTO.setDocumentVersion("1");
        adminRecodeDTO.setCreatedBy(DcsSIAMInfoUtil.getCurrentUserName());
        adminRecodeDTO.setLastModifiedDate(new Date());
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(DcsSIAMInfoUtil.getCurrentSubscriber());
        adminRecodeDTO.setSubscriberReference(subscriberIDType);
        docHeaderDTO.setAdministrativeRecord(adminRecodeDTO);
        standByPriorityDTO.setDocumentHeader(docHeaderDTO);

        DeleteDefaultStandbyPriorityListener.LOGGER
            .debug("Exit DeleteDefaultStandbyPriorityListener:populateDeleteInfoDTO()");
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
     * Get the 'defaultSPDeleteSelRowsButton' attribute value.
     * 
     * @return defaultSPDeleteSelRowsButton , null if not found.
     * </pre>
     */
    public final JButton getDefaultSPDeleteSelRowsButton() {
        return defaultSPDeleteSelRowsButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPDeleteSelRowsButton' attribute value.
     * 
     * @param defaultSPDeleteSelRowsButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPDeleteSelRowsButton(final JButton defaultSPDeleteSelRowsButtonParam) {
        defaultSPDeleteSelRowsButton = defaultSPDeleteSelRowsButtonParam;
    }
}
