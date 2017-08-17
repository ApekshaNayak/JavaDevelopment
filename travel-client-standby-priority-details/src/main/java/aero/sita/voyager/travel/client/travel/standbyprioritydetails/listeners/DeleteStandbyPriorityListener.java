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
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsViewHandler;
import aero.sita.voyager.dcs.client.common.util.DcsSIAMInfoUtil;
import aero.sita.voyager.ngafoundation.client.common.util.NGAFAppContextUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.utils.StandbyPriorityUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.valueobject.StandbyPriorityVO;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.horizon.schemas.metadata.v1.AdministrativeRecordType;
import aero.sita.horizon.schemas.metadata.v1.DocumentHeaderType;
import aero.sita.horizon.schemas.metadata.v1.DocumentIDType;
import aero.sita.horizon.schemas.metadata.v1.LanguageCodeType;
import aero.sita.horizon.schemas.metadata.v1.SchemaVersionAttrGroup;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.ConfirmStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.DeleteStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPrioritiesType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for New Standby Priority Add Button Action Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class DeleteStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(DeleteStandbyPriorityListener.class);
    /**
     * AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;
    /**
     * AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;
    /**
     * COLUMN_FIFTEEN.
     */
    private static final int COLUMN_FIFTEEN = 15;
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

        int[] selectedRows = {};

        DcsMessagePanelHandler.clearAllMessages();

        if (controller.getNewSPFilterTablePanel() != null) {
            selectedRows = controller.getNewSPFilterTablePanel().getTable().getSelectedRows();
        }
        if (selectedRows != null && selectedRows.length > 0) {
            if (isDeleteAll(selectedRows)) {
                deleteStandByPriority(selectedRows);
            }

        }
    }

    /**
     * 
     * This method will be called to validate the rows selected for deletion.
     * 
     * @param selectedRows , not null.
     * @return boolean , never null.
     */
    private boolean isDeleteAll(final int[] selectedRows) {
        try {
            int isDocIDCount = 0;
            Integer totalSize = 0;
            int isDelete                    ;
            boolean isValid = true;
            final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();
            if (controller.getNewSPFilterTablePanel() != null
                && controller.getNewSPFilterTablePanel().getModel() != null) {
                final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();

                if (NGAFContextUtil.getApplicationContext().
                        get(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT) != null) {
                    totalSize = (Integer) NGAFContextUtil.getApplicationContext().get(
                        StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT);
                }

                if (selectedRows.length > 0) {
                    for (int i = 0; i < selectedRows.length; i++) {

                        if (dm.getValueAt(selectedRows[i], COLUMN_FIFTEEN) != null && dm.getValueAt(selectedRows[i], COLUMN_FIFTEEN).toString().trim().length() > 0) {
                            isDocIDCount++;
                        }
                    }

                    if ((isDocIDCount == 0 && selectedRows.length > 0)) {
                        isDelete = standbyPriorityUtil.confirmDelete(
                            StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                            StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_MSG);
                        if (isDelete == 0) {
                            if (controller.getNewSPFilterTablePanel() != null
                                && controller.getNewSPFilterTablePanel().getModel() != null) {
                                return true;
                            }
                        }
                        else {
                            DcsMessagePanelHandler.clearAllMessages();
                            return false;
                        }
                    }
                    if (controller.getNewSPFilterTablePanel().getTable() != null) {
                        NGAFStandardTable SPStandardTable = (NGAFStandardTable) controller.getNewSPFilterTablePanel()
                            .getTable();
                        isValid = controller.getCloseStandbyPriorityValidator().validateStandbyPriority(dm,
                            SPStandardTable);
                    }
                    List<StandbyPriorityVO> spVOList;
                    boolean isSave = false;
                    spVOList = controller.getStandbyPriorityDetailsScreenHelper().fetchSPTableData();
                    spVOList = getModifiedRecords(spVOList);
                    if (spVOList != null) {
                        if (!isValid || spVOList.size() > 0) {
                            isSave = true;
                        }
                    }

                    if (isSave || (isDocIDCount > 0 && isDocIDCount != selectedRows.length)) {
                        isDelete = standbyPriorityUtil.confirmDelete(
                            StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                            StandbyPriorityConstants.CONFIRM_SAVE_AND_DELETE_DIALOG_MSG);
                        if (isDelete == 0) {
                            if (isDocIDCount == totalSize.intValue()) {
                                DcsMessagePanelHandler.handleWarningMessages("CKI-10827");
                                isDelete = standbyPriorityUtil.confirmDelete(
                                    StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                                    StandbyPriorityConstants.CONFIRM_DELETE_LAST_RECORD_DIALOG_MSG);
                                if (isDelete == 0) {
                                    if (controller.getNewSPFilterTablePanel() != null
                                        && controller.getNewSPFilterTablePanel().getModel() != null) {
                                        return true;
                                    }
                                }
                                else {
                                    DcsMessagePanelHandler.clearAllMessages();
                                    return false;
                                }
                            }
                            else if (controller.getNewSPFilterTablePanel() != null
                                && controller.getNewSPFilterTablePanel().getModel() != null) {
                                return true;
                            }
                        }
                        else {
                            DcsMessagePanelHandler.clearAllMessages();
                            return false;
                        }
                    }
                    else if (isDocIDCount == totalSize.intValue()) {
                        DcsMessagePanelHandler.handleWarningMessages("CKI-10827");
                        isDelete = standbyPriorityUtil.confirmDelete(
                            StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                            StandbyPriorityConstants.CONFIRM_DELETE_LAST_RECORD_DIALOG_MSG);
                    }
                    else {
                        isDelete = standbyPriorityUtil.confirmDelete(
                            StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_TITLE,
                            StandbyPriorityConstants.CONFIRM_DELETE_DIALOG_MSG);
                    }
                    if (isDelete == 0) {
                        if (controller.getNewSPFilterTablePanel() != null
                            && controller.getNewSPFilterTablePanel().getModel() != null) {
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
     * @param spVOList , not null.
     * @return list , never null.
     * </pre>
     */
    private List<StandbyPriorityVO> getModifiedRecords(final List<StandbyPriorityVO> spVOList) {
        String currentTabName = "";
        List<StandbyPriorityVO> spVOListTemp = new ArrayList();

        for (StandbyPriorityVO standbyPriorityVO : spVOList) {
            spVOListTemp.add(standbyPriorityVO);
        }
        if (DcsTabDataContainer.getInstance().getCurrentTabName() != null) {
            currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();

        }

        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(currentTabName);
        if (findStandbyPriorityResponseDTO != null) {
            if ((StandbyPrioritiesType) findStandbyPriorityResponseDTO.getStandbyPriorityList()
                .getStandbyPriorities() != null) {
                checkForSPModification((StandbyPrioritiesType) findStandbyPriorityResponseDTO.getStandbyPriorityList()
                    .getStandbyPriorities(), spVOList, spVOListTemp);
            }
        }

        return spVOListTemp;
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
        DeleteStandbyPriorityListener.LOGGER.debug("Enter DeleteStandbyPriorityListener.getTabDTO");

        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = 
                (FindStandbyPriorityResponse) DcsTabDataContainer.getInstance().getDTOForTab(tabName);

        DeleteStandbyPriorityListener.LOGGER.debug("Exit DeleteStandbyPriorityListener.getTabDTO");

        return findStandbyPriorityResponseDTO;
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
     * <pre>
     * <b>Description : </b>
     * This method is to check if any existing record is modified.
     * 
     * @param standbyPrioritiesDTO , not null.
     * @param spVOList , not null.
     * @param spVOListTemp , not null.
     * </pre>
     */
    private void checkForSPModification(final StandbyPrioritiesType standbyPrioritiesDTO,
        final List<StandbyPriorityVO> spVOList, final List<StandbyPriorityVO> spVOListTemp) {
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

                for (StandbyPriorityVO standbyPriorityVO : spVOList) {

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

                    if (standbyPriorityVO != null) {
                        if (standbyPriorityVO.getPriorityStatusCode() != null) {
                            priorityCodeFromVO = standbyPriorityVO.getPriorityStatusCode();
                        }

                        if (standbyPriorityVO.getPriorityStatusDesc() != null) {
                            priorityDescFromVO = standbyPriorityVO.getPriorityStatusDesc();
                        }

                        if (standbyPriorityVO.getDateOfJoining() != null) {
                            dateOfJoiningFromVO = standbyPriorityVO.getDateOfJoining();
                        }

                        if (standbyPriorityVO.getCanBeDisembarked() != null) {
                            checkPADFromVO = standbyPriorityVO.getCanBeDisembarked();
                        }

                        if (standbyPriorityVO.getBookedCanBeDisembarked() != null) {
                            bookPADFromVO = standbyPriorityVO.getBookedCanBeDisembarked();
                        }

                        if (standbyPriorityVO.getNoRecCanBeDisembarked() != null) {
                            norecPADFromVO = standbyPriorityVO.getNoRecCanBeDisembarked();
                        }

                        if (standbyPriorityVO.getWaitlistCanBeDisembarked() != null) {
                            waitlistPADFromVO = standbyPriorityVO.getWaitlistCanBeDisembarked();
                        }

                        if (standbyPriorityVO.getOpenTicketCanBeDisembarked() != null) {
                            openPADFromVO = standbyPriorityVO.getOpenTicketCanBeDisembarked();
                        }

                        if (standbyPriorityVO.getHoldsSpaceAvailableTicket() != null) {
                            openholdPADFromVO = standbyPriorityVO.getHoldsSpaceAvailableTicket();
                        }

                        bookPriorityValueFromVO = standbyPriorityVO.getBookedPriorityValue();
                        norecPriorityValueFromVO = standbyPriorityVO.getNoRecPriorityValue();
                        waitlistPriorityValueFromVO = standbyPriorityVO.getWaitlistPriorityValue();
                        openPriorityValueFromVO = standbyPriorityVO.getOpenTicketPriorityValue();
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
                            spVOListTemp.remove(standbyPriorityVO);
                            break;
                        }
                    }
                }
            }
        }
    }

    /* private void getSeletedRowsValue(final int[] selectedRows) {
         int success = 0;
         int failed = 0;
         int unSavedRows = 0;
         Integer totalSize = 0;
         int count = 0;
         List errorCodesList = new ArrayList();
         List newRows = new ArrayList();
         int[] newRows1 = selectedRows;
         List<String> docIds = new ArrayList();
         final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();
         totalSize = (Integer) NGAFContextUtil.getApplicationContext()
             .get(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT);
         for (int rows = 0; rows < selectedRows.length; rows++) {
             if (controller.getNewSPFilterTablePanel().getTable().getValueAt(selectedRows[rows], 15) != null) {
                 docIds.add(controller.getNewSPFilterTablePanel().getTable().getValueAt(selectedRows[rows], 15)
                     .toString());
             }
         }
         if (docIds != null && docIds.size() > 0) {
             for (int j = 0; j < docIds.size(); j++) {
                 final DeleteStandbyPriorityType deleteStandbyPriorityDTO = populateDeleteSPRequestDTO(docIds.get(j)
                     .toString());
                 if (controller.getManageStandbyPriorityProxy() != null) {
                     try {
                         final ConfirmStandbyPriorityType confirmStandbyPriorityDTO = controller
                             .getManageStandbyPriorityProxy().deleteStandbyPriority(deleteStandbyPriorityDTO);

                         if (confirmStandbyPriorityDTO != null) {
                             DeleteStandbyPriorityListener.LOGGER
                                 .debug("Exit DeleteStandbyPriorityListener:confirmStandbyPriorityDTO"
                                     + confirmStandbyPriorityDTO);
                         }
                         success++;
                     }
                     catch (final TravelException ex) {
                         failed++;
                         errorCodesList.add(ex);
                         LOGGER.debug("Error" + ex);
                     }
                 }
             }
             if (totalSize == success) {
                 String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
                 NGAFAppContextUtil.getAppContext().put(StandbyPriorityConstants.DELETE_STATUS,
                     StandbyPriorityConstants.YES_VALUE);
                 controller.getStandbyPriorityDetailsScreenHelper().updateSearchScreen();
                 NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                     StandbyPriorityConstants.YES_VALUE);
                 DcsViewHandler.closeTab(currentTabName);
             }
             else {
                 if (docIds.size() > 0) {
                     controller.getStandbyPriorityDetailsScreenHelper().updateScreenUI();
                     displayMessage(success, failed, errorCodesList);
                 }
                 else {
                     displayMessage(success, failed, errorCodesList);
                 }
             }

         }
         else {
             System.out.println("Delete empty rows");
             for (int i = 0; i < selectedRows.length; i++) {
                 dm.removeRow(selectedRows[i] - i);
             }
         }

     }*/

    /**
     * 
     * This method will be called to delete the selected standby priority rows.
     * 
     * @param selectedRows , not null.
     */
    private void deleteStandByPriority(final int[] selectedRows) {
        DeleteStandbyPriorityListener.LOGGER.debug("Enter DeleteStandbyPriorityListener:deleteDefaultStandByPriority");

        try {
            int success = 0;
            int failed = 0;
            List errorCodesList = new ArrayList();
            List<String> docIds = new ArrayList();
            final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();
            for (int rows = 0; rows < selectedRows.length; rows++) {
                if (controller.getNewSPFilterTablePanel().getTable().
                        getValueAt(selectedRows[rows], COLUMN_FIFTEEN) != null
                    && controller.getNewSPFilterTablePanel().getTable().getValueAt(selectedRows[rows], COLUMN_FIFTEEN)
                        .toString().trim().length() > 0) {
                    docIds.add(controller.getNewSPFilterTablePanel().getTable()
                        .getValueAt(selectedRows[rows], COLUMN_FIFTEEN).toString());
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
                                DeleteStandbyPriorityListener.LOGGER
                                    .debug("Exit DeleteStandbyPriorityListener:confirmStandbyPriorityDTO"
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
                if ((Integer) NGAFContextUtil.getApplicationContext().get(
                    StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT) == success) {
                    String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
                    NGAFAppContextUtil.getAppContext().put(StandbyPriorityConstants.DELETE_STATUS,
                        StandbyPriorityConstants.YES_VALUE);
                    controller.getStandbyPriorityDetailsScreenHelper().updateSearchScreen();
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                        StandbyPriorityConstants.YES_VALUE);
                    DcsViewHandler.closeTab(currentTabName);
                }
                else {
                    if (docIds.size() > 0) {
                        controller.getStandbyPriorityDetailsScreenHelper().updateScreenUI();
                        displayMessage(success, failed, errorCodesList);
                    }
                    else {
                        displayMessage(success, failed, errorCodesList);
                    }
                }

            }
            else {
                for (int i = 0; i < selectedRows.length; i++) {
                    dm.removeRow(selectedRows[i] - i);
                }
            }

        }
        catch (final Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());
            ex.printStackTrace();
        }

        DeleteStandbyPriorityListener.LOGGER.debug("Exit DeleteStandbyPriorityListener:deleteDefaultStandByPriority");
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
        controller.getNewSPFilterTablePanel().clearTextOfFilterRow();
        String subscriberCode = controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase();
        String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText().toUpperCase();

        if (failed == 0 && success > 0) {

            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, subscriberCode);
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, airlineCode);
            DcsMessagePanelHandler.handleSuccessMessage(success + " Success ");
            DcsMessagePanelHandler.handleSuccessMessage("CKI-10826");
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
        DeleteStandbyPriorityListener.LOGGER.debug("Enter DeleteStandbyPriorityListener:populateDeletePSPRequestDTO()");

        final DeleteStandbyPriority deleteStandbyPriorityDTO = new DeleteStandbyPriority();
        final StandbyPriorityType standByPriorityDTO = new StandbyPriorityType();

        standByPriorityDTO.setIsDefault(false);

        final SchemaVersionAttrGroup schemaVerGroup = new SchemaVersionAttrGroup();
        schemaVerGroup.setSchemaVersion("1.0");
        schemaVerGroup.setLanguageCode(LanguageCodeType.EN);
        standByPriorityDTO.setSchemaVersionAttrGroup(schemaVerGroup);

        populateDeleteSPDocumentDTO(standByPriorityDTO, docId);

        deleteStandbyPriorityDTO.setStandbyPriority(standByPriorityDTO);
        DeleteStandbyPriorityListener.LOGGER.debug("Exit DeleteStandbyPriorityListener:populateDeletePSPRequestDTO()");

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
        DeleteStandbyPriorityListener.LOGGER.debug("Enter DeleteStandbyPriorityListener:populateDeleteInfoDTO()");
        String airlineNumber = "";
        String subscriber = "";
        final DocumentHeaderType docHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType adminRecodeDTO = new AdministrativeRecordType();
        if (controller.getNewSPAirlineLOV().getSearchField().getText() != null) {
            final AirlineInfoType airlineInfoDTO = new AirlineInfoType();
            String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText();

            if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
                airlineInfoDTO.setIataCode(airlineCode.toUpperCase());
                airlineNumber = populateAirlineNumber(airlineCode, true);
            }
            else if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
                airlineInfoDTO.setIcaoCode(airlineCode.toUpperCase());
                airlineNumber = populateAirlineNumber(airlineCode, false);
            }
        }
        if (controller.getNewSPSubscriberLOV().getSearchField().getText() != null) {
            subscriber = controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase();
        }
        final DocumentIDType documentIDType=new DocumentIDType();
        documentIDType.setString(airlineNumber + "#" + docId + "#" + subscriber);
        adminRecodeDTO.setDocumentID(documentIDType);
        
        adminRecodeDTO.setDocumentVersion("1");
        adminRecodeDTO.setCreatedBy(DcsSIAMInfoUtil.getCurrentUserName());
        adminRecodeDTO.setLastModifiedDate(new Date());
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(DcsSIAMInfoUtil.getCurrentSubscriber());
        adminRecodeDTO.setSubscriberReference(subscriberIDType);
        

        docHeaderDTO.setAdministrativeRecord(adminRecodeDTO);
        standByPriorityDTO.setDocumentHeader(docHeaderDTO);

        DeleteStandbyPriorityListener.LOGGER.debug("Exit DeleteStandbyPriorityListener:populateDeleteInfoDTO()");
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
