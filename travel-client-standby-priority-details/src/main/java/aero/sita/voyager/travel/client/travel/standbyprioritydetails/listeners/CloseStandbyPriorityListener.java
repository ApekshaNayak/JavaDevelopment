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
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsViewHandler;
import aero.sita.voyager.dcs.client.common.util.DcsSIAMInfoUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.utils.StandbyPriorityUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.valueobject.StandbyPriorityVO;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
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
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
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
public class CloseStandbyPriorityListener implements ActionListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(CloseStandbyPriorityListener.class);

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
        CloseStandbyPriorityListener.LOGGER.info("Enter CloseStandbyPriorityListener:actionPerformed()");
        try {
            DcsMessagePanelHandler.clearAllMessages();
            final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();
            String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
            if (currentTabName.equals(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB)) {
                final int isCopy = standbyPriorityUtil.confirmDelete("",
                    StandbyPriorityConstants.CLOSE_WARNING_DIALOG_MSG);

                if (isCopy == 0) {
                    boolean isValid = true;
                    if (NGAFContextUtil.getApplicationContext().
                        get(StandbyPriorityConstants.SP_SUBSCRIBER_CODE) != null) {
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, null);
                    }

                    if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_AIRLINE_CODE) != null) {
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, null);
                    }

                    if (controller.getNewSPFilterTablePanel() != null
                        && controller.getNewSPFilterTablePanel().getModel() != null) {
                        final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel()
                            .getModel();

                        if (controller.getNewSPFilterTablePanel().getTable() != null) {
                            NGAFStandardTable spStandardTable = (NGAFStandardTable) controller
                            .getNewSPFilterTablePanel().getTable();
                            if (dm != null) {
                                isValid = controller.getStandbyPriorityDetailsValidator().validateStandbyPriority(dm,
                                    spStandardTable);
                            }
                        }
                    }

                    if (isValid) {
                        createNewStandByPriority(controller.getStandbyPriorityDetailsScreenHelper().fetchSPTableData());
                    }

                }
                else if (isCopy == 1) {
                    if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
                    }
                    if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
                    }
                    if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
                        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH, null);
                    }

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                        StandbyPriorityConstants.YES_VALUE);

                    DcsViewHandler.closeTab(currentTabName);

                }
            }
            if (!currentTabName.equals(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB)) {

                DcsMessagePanelHandler.clearAllMessages();

                if (controller.getNewSPFilterTablePanel() != null
                    && controller.getNewSPFilterTablePanel().getModel() != null) {
                    final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();

                    if (controller.getNewSPFilterTablePanel().getTable() != null) {
                        boolean isValid = true;
                        NGAFStandardTable spStandardTable = (NGAFStandardTable) controller.getNewSPFilterTablePanel()
                            .getTable();
                        if (dm != null) {
                            isValid = controller.getCloseStandbyPriorityValidator().validateStandbyPriority(dm,
                                spStandardTable);
                        }
                        /*
                         * List<StandbyPriorityVO> spVOList = null;
                         * 
                         * spVOList =
                         * controller.getStandbyPriorityDetailsScreenHelper
                         * ().fetchSPTableData(); spVOList =
                         * getModifiedRecords(controller
                         * .getStandbyPriorityDetailsScreenHelper
                         * ().fetchSPTableData());
                         */
                        if (getModifiedRecords(controller.getStandbyPriorityDetailsScreenHelper()
                            .fetchSPTableData()) != null) {
                            if (!isValid
                                || getModifiedRecords(
                                    controller.getStandbyPriorityDetailsScreenHelper().fetchSPTableData()).size() > 0) {
                                final int isCopyUpdate = standbyPriorityUtil.confirmDelete("",
                                    StandbyPriorityConstants.CLOSE_WARNING_DIALOG_MSG);
                                if (isCopyUpdate == 0) {
                                    if (controller.getNewSPFilterTablePanel() != null
                                        && controller.getNewSPFilterTablePanel().getModel() != null) {
                                        final DefaultTableModel dm1 = (DefaultTableModel) controller
                                        .getNewSPFilterTablePanel().getModel();

                                        if (controller.getNewSPFilterTablePanel().getTable() != null) {
                                            NGAFStandardTable spStandardTable1 = (NGAFStandardTable) controller
                                            .getNewSPFilterTablePanel().getTable();
                                            if (dm1 != null) {
                                                isValid = controller.getStandbyPriorityDetailsValidator()
                                                    .validateStandbyPriority(dm1, spStandardTable1);
                                            }
                                        }
                                    }

                                    if (isValid) {
                                        /*
                                         * spVOList = controller.
                                         * getStandbyPriorityDetailsScreenHelper
                                         * () .fetchSPTableData();
                                         */

                                        if (controller.getStandbyPriorityDetailsScreenHelper()
                                            .fetchSPTableData() != null
                                            && controller.getStandbyPriorityDetailsScreenHelper().fetchSPTableData()
                                            .size() > 0) {
                                            updateStandByPriority(getModifiedRecords(controller
                                                .getStandbyPriorityDetailsScreenHelper().fetchSPTableData()));
                                        }
                                    }

                                }
                                else if (isCopyUpdate == 1) {
                                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                                        StandbyPriorityConstants.YES_VALUE);
                                    DcsViewHandler.closeTab(currentTabName);
                                }
                            }
                            else {
                                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                                    StandbyPriorityConstants.YES_VALUE);
                                DcsViewHandler.closeTab(currentTabName);
                            }
                        }

                    }
                }
            }
        }
        catch (Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());

        }

        CloseStandbyPriorityListener.LOGGER.info("Exit CloseStandbyPriorityListener:actionPerformed()");

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
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.createStandByPriority");

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
                        .getManageStandbyPriorityProxy().createStandbyPriority(
                            populateCreateSPRequestDTO(standbyPriorityVO));

                        if (confirmStandbyPriorityDTO != null) {
                            CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener."
                                + "confirmStandbyPriorityDTO" + confirmStandbyPriorityDTO);
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

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.createStandByPriority");
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

        if (NGAFContextUtil.getApplicationContext()
            .get(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITY_RESPONSE) != null) {
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
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH, null);
            }
            String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                StandbyPriorityConstants.YES_VALUE);
            DcsViewHandler.closeTab(currentTabName);
        }
        else if (failed > 0 && success > 0) {
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_STANBY) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
            }
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.RESET_SEARCH) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH, null);
            }
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");
            String airlineCodeLOV = controller.getNewSPAirlineLOV().getSearchField().getText();
            JTabbedPane pane = (JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent());
            final int index = ((JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent())).getSelectedIndex();
            String airlineName = controller.getCommonUtilitiesGateWay().getAirlineName(airlineCodeLOV);

            pane.setTitleAt(index, subscriberCode.trim() + "-" + airlineName.trim());

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
     * Method to highlight failedrows.
     * 
     * @param failedRowsList , not null.
     * @param spVOList , not null.
     * </pre>
     */
    private void highlightFailedRows(final List failedRowsList, final List<StandbyPriorityVO> spVOList) {
        CloseStandbyPriorityListener.LOGGER.debug("Enter CreatetStandbyPriorityListener.highlightFailedRows");

        try {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.CREATE_SP_FAILED_ROWS, failedRowsList);

            for (int i = 0; i < failedRowsList.size(); i++) {
                if (controller.getNewSPFilterTablePanel() != null
                    && controller.getNewSPFilterTablePanel().getModel() != null) {

                    final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();

                    if (controller.getNewSPFilterTablePanel().getTable() != null) {
                        NGAFStandardTable spStandardTable = (NGAFStandardTable) controller.getNewSPFilterTablePanel()
                            .getTable();

                        for (StandbyPriorityVO StandbyPriorityVO : spVOList) {
                            if (failedRowsList.get(i) != null && !("").equals(failedRowsList.get(i))) {
                                int rowno = Integer.parseInt(failedRowsList.get(i).toString());

                                String docId = null;
                                String priorityStatusCode = null;
                                if (dm != null) {
                                    if (dm.getValueAt(rowno, COLUMN_VALUE_FIFTEEN) != null
                                        && !("").equals(dm.getValueAt(rowno, COLUMN_VALUE_FIFTEEN))) {
                                        docId = dm.getValueAt(rowno, COLUMN_VALUE_FIFTEEN).toString();
                                    }

                                    if (dm.getValueAt(rowno, COLUMN_VALUE_TWO) != null
                                        && !("").equals(dm.getValueAt(rowno, COLUMN_VALUE_TWO))) {
                                        priorityStatusCode = dm.getValueAt(rowno, COLUMN_VALUE_TWO).toString();
                                    }
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

        CloseStandbyPriorityListener.LOGGER.debug("Exit CreatetStandbyPriorityListener.highlightFailedRows");
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
     * This method is to filter the modified record from the list.
     * 
     * @param spVOList , not null.
     * @return list , never null.
     * </pre>
     */
    private List<StandbyPriorityVO> getModifiedRecords(final List<StandbyPriorityVO> spVOList) {
        StandbyPrioritiesType standbyPrioritiesDTO;
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
            standbyPrioritiesDTO = (StandbyPrioritiesType) findStandbyPriorityResponseDTO.getStandbyPriorityList()
                .getStandbyPriorities();
            if (standbyPrioritiesDTO != null) {
                checkForSPModification(standbyPrioritiesDTO, spVOList, spVOListTemp);
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
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.getTabDTO");

        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = 
            (FindStandbyPriorityResponse) DcsTabDataContainer
            .getInstance().getDTOForTab(tabName);

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.getTabDTO");

        return findStandbyPriorityResponseDTO;
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
        String dateOfJoining;
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
                                dateOfJoining = standbyPriorityDTO.getInfo().getDateOfJoiningRequired().toString();

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
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.populateCreateSPRequestDTO");

        final CreateStandbyPriority createStandbyPriorityDTO = new CreateStandbyPriority();
        final StandbyPriorityType standbyPriorityDTO = new StandbyPriorityType();

        populateCreateSPDocumentDTO(standbyPriorityDTO);
        populateCreateSPInfoDTO(standbyPriorityDTO, standbyPriorityVO);
        populateCreateSPAirlineInfo(standbyPriorityDTO, standbyPriorityVO);
        populateCreateSPBookingStatusDTO(standbyPriorityDTO, standbyPriorityVO);

        createStandbyPriorityDTO.setStandbyPriority(standbyPriorityDTO);

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateCreateSPRequestDTO");

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
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.populateCreateSPDocumentDTO");

        final DocumentHeaderType documentHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType administrativeRecordDTO = new AdministrativeRecordType();
        String subscriber = controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase();
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

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateCreateSPDocumentDTO");
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
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.populateCreateSPInfoDTO");

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

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateCreateSPInfoDTO");
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
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.populateCreateSPAirlineInfo");

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

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateCreateSPAirlineInfo");
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
     * Method to populate Standbypriority BookingStatus Info DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param standbyPriorityVO , not null.
     * </pre>
     */
    private void populateCreateSPBookingStatusDTO(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CloseStandbyPriorityListener.LOGGER
        .debug("Enter CloseStandbyPriorityListener.populateCreateSPBookingStatusDTO");

        final BookingStatusInfoType bookingStatusInfoDTO = new BookingStatusInfoType();
        final BookingStatusPriorityInfoType bookedPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType noRecPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType waitlistPriorityinfoDTO = new BookingStatusPriorityInfoType();
        final OpenPriorityInfoType openPriorityInfoDTO = new OpenPriorityInfoType();

        if (standbyPriorityVO != null) {
            if (standbyPriorityVO.getBookedPriorityValue() != 0 && standbyPriorityVO.getBookedPriorityValue() != 0
                && standbyPriorityVO.getBookedCanBeDisembarked() != null) {

                bookedPriorityInfoDTO.setPriorityValue(standbyPriorityVO.getBookedPriorityValue());

                if (standbyPriorityVO.getBookedCanBeDisembarked()
                    .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
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

                if (standbyPriorityVO.getWaitlistCanBeDisembarked()
                    .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
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

                if (standbyPriorityVO.getOpenTicketCanBeDisembarked().equalsIgnoreCase(
                    StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    openPriorityInfoDTO.setCanBeDisembarked(false);
                }

                if (standbyPriorityVO.getHoldsSpaceAvailableTicket().equalsIgnoreCase(
                    StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(true);
                }
                else {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(false);
                }

                openPriorityInfoDTO.setPriorityValue(standbyPriorityVO.getOpenTicketPriorityValue());
                bookingStatusInfoDTO.setOpenPriorityInfo(openPriorityInfoDTO);
            }
        }

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateCreateSPBookingStatusDTO");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method for service call of create stand by priority.
     * 
     * @param spVOList , not null.
     * </pre>
     */
    private void updateStandByPriority(final List<StandbyPriorityVO> spVOList) {
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.UpdateStandByPriority");

        int success = 0;
        int failed = 0;
        List failedRowsList = new ArrayList();
        List errorCodesList = new ArrayList();
        DefaultTableModel defaultDefaultTableModel = null;

        if (controller.getNewSPFilterTablePanel().getModel() != null
            && controller.getNewSPFilterTablePanel().getModel() != null) {
            defaultDefaultTableModel = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();
        }

        try {
            if (controller.getManageStandbyPriorityProxy() != null) {
                for (StandbyPriorityVO standbyPriorityVO : spVOList) {

                    try {
                        if (standbyPriorityVO.getDocumentId() != null
                            && standbyPriorityVO.getDocumentId().trim().length() > 0) {

                            final ConfirmStandbyPriority updateConfirmStandbyPriorityDTO = controller
                            .getManageStandbyPriorityProxy().updateStandbyPriority(
                                populateUpdateSPRequestDTO(standbyPriorityVO));
                            if (updateConfirmStandbyPriorityDTO != null) {
                                LOGGER.debug("updateConfirmStandbyPriorityDTO");
                            }
                        }
                        else {
                            final ConfirmStandbyPriority createConfirmStandbyPriorityDTO = controller
                            .getManageStandbyPriorityProxy().createStandbyPriority(
                                populateCreateSPRequestDTO(standbyPriorityVO));
                            if (createConfirmStandbyPriorityDTO != null) {
                                LOGGER.debug("createConfirmStandbyPriorityDTO");
                            }
                        }

                        if (standbyPriorityVO.getPriorityStatusCode() != null && defaultDefaultTableModel != null) {
                            defaultDefaultTableModel.setValueAt(standbyPriorityVO.getPriorityStatusCode(),
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

                displayMessageForUpdate(success, failed, spVOList, errorCodesList, failedRowsList);
            }
        }
        catch (final Exception ex) {
            LOGGER.error("Error : " + ex.getMessage());
            // ex.printStackTrace();
        }

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.updateStandByPriority");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to display the message on Update.
     * 
     * @param success , not null.
     * @param failed , not null.
     * @param spVOList , not null.
     * @param errorCodesList , not null.
     * @param failedRowsList , not null.
     * </pre>
     */
    private void displayMessageForUpdate(final int success, final int failed, final List spVOList,
        final List errorCodesList, final List failedRowsList) {

        /*
         * if
         * (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants
         * .COPY_DEFAULT) != null) {
         * NGAFContextUtil.getApplicationContext().put(
         * StandbyPriorityConstants.COPY_DEFAULT, null); } if
         * (NGAFContextUtil.getApplicationContext
         * ().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
         * NGAFContextUtil
         * .getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY,
         * null); } if
         * (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants
         * .COPY_SEARCH) != null) {
         * NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants
         * .COPY_SEARCH, null); } if
         * (NGAFContextUtil.getApplicationContext().get
         * (StandbyPriorityConstants.OPEN_SEARCH) != null) {
         * NGAFContextUtil.getApplicationContext
         * ().put(StandbyPriorityConstants.OPEN_SEARCH, null); }
         */

        if (failed == 0 && success > 0) {

            String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE,
                StandbyPriorityConstants.YES_VALUE);
            DcsViewHandler.closeTab(currentTabName);

        }
        else if (failed > 0 && success > 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");
            controller.getStandbyPriorityDetailsScreenHelper().updateScreenUI();
        }
        else if (failed > 0 && success == 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(failed + " Failed");
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
     * @return updateStandbyPriorityRequestDTO , never null.
     * </pre>
     */
    private UpdateStandbyPriority populateUpdateSPRequestDTO(final StandbyPriorityVO standbyPriorityVO) {
        CloseStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateUpdateSPRequestDTO");

        final UpdateStandbyPriority updateStandbyPriorityRequestDTO = new UpdateStandbyPriority();
        final StandbyPriorityType standbyPriorityDTO = new StandbyPriorityType();

        populateUpdateSPDocumentDTO(standbyPriorityDTO, standbyPriorityVO);
        populateUpdateSPInfoDTO(standbyPriorityDTO, standbyPriorityVO);
        populateUpdateSPAirlineInfo(standbyPriorityDTO, standbyPriorityVO);
        populateUpdateSPBookingStatusDTO(standbyPriorityDTO, standbyPriorityVO);

        updateStandbyPriorityRequestDTO.setStandbyPriority(standbyPriorityDTO);

        CloseStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateUpdateSPRequestDTO");

        return updateStandbyPriorityRequestDTO;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate Standbypriority Documentheader DTO.
     * 
     * @param standbyPriorityDTO , not null.
     * @param standbyPriorityVO , not null.
     * </pre>
     */
    private void populateUpdateSPDocumentDTO(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CloseStandbyPriorityListener.LOGGER
        .debug("Enter UpdateDefaultStandbyPriorityListener.populateUpdateSPDocumentDTO");
        String airlineNumber = "";
        String subscriber = "";
        final DocumentHeaderType documentHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType administrativeRecordDTO = new AdministrativeRecordType();
        final DocumentIDType documentIDType = new DocumentIDType();
        documentIDType.setString(standbyPriorityVO.getDocumentId());
        administrativeRecordDTO.setDocumentID(documentIDType);
       

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
        DocumentIDType documentIDType1=new DocumentIDType();
        documentIDType1.setString(airlineNumber + "#" + standbyPriorityVO.getDocumentId() + "#"
                + subscriber);
        administrativeRecordDTO.setDocumentID(documentIDType1);
       
        SubscriberIDType subscriberIDType = new SubscriberIDType();
        subscriberIDType.setString(DcsSIAMInfoUtil.getCurrentSubscriber());
        administrativeRecordDTO.setSubscriberReference(subscriberIDType);
        documentHeaderDTO.setAdministrativeRecord(administrativeRecordDTO);

        final SchemaVersionAttrGroup schemaVersionAttrGroupParam = new SchemaVersionAttrGroup();
        schemaVersionAttrGroupParam.setLanguageCode(LanguageCodeType.EN);
        schemaVersionAttrGroupParam.setSchemaVersion("1.0");
        // schemaVersionAttrGroupParam.setActionCode(ActionCodeDTO.NEW);

        standbyPriorityDTO.setDocumentHeader(documentHeaderDTO);
        standbyPriorityDTO.setIsDefault(false);
        standbyPriorityDTO.setSchemaVersionAttrGroup(schemaVersionAttrGroupParam);

        CloseStandbyPriorityListener.LOGGER
        .debug("Exit UpdateDefaultStandbyPriorityListener.populateUpdateSPDocumentDTO");
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
    private void populateUpdateSPAirlineInfo(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.populateCreateSPAirlineInfo");

        String airlineNumber = null;

        if (standbyPriorityVO != null) {
            if (controller.getNewSPAirlineLOV().getSearchField().getText() != null) {
                final AirlineInfoType airlineInfoDTO = new AirlineInfoType();
                String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText();

                if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
                    // airlineInfoDTO.setIataCode(airlineCode.toUpperCase());
                    airlineNumber = populateAirlineNumber(airlineCode, true);
                }

                if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
                    // airlineInfoDTO.setIcaoCode(airlineCode.toUpperCase());
                    airlineNumber = populateAirlineNumber(airlineCode, false);
                }

                if (isNotEmpty(airlineNumber)) {
                    airlineInfoDTO.setSitaNumber(new Long(airlineNumber));
                    standbyPriorityDTO.setHandledAirline(airlineInfoDTO);
                }
            }
        }

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateCreateSPAirlineInfo");
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
    private void populateUpdateSPInfoDTO(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CloseStandbyPriorityListener.LOGGER.debug("Enter CloseStandbyPriorityListener.populateUpdateSPInfoDTO");

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

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateUpdateSPInfoDTO");
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
    private void populateUpdateSPBookingStatusDTO(final StandbyPriorityType standbyPriorityDTO,
        final StandbyPriorityVO standbyPriorityVO) {
        CloseStandbyPriorityListener.LOGGER
        .debug("Enter CloseStandbyPriorityListener.populateUpdateSPBookingStatusDTO");

        final BookingStatusInfoType bookingStatusInfoDTO = new BookingStatusInfoType();
        final BookingStatusPriorityInfoType bookedPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType noRecPriorityInfoDTO = new BookingStatusPriorityInfoType();
        final BookingStatusPriorityInfoType waitlistPriorityinfoDTO = new BookingStatusPriorityInfoType();
        final OpenPriorityInfoType openPriorityInfoDTO = new OpenPriorityInfoType();

        if (standbyPriorityVO != null) {
            if (standbyPriorityVO.getBookedPriorityValue() != 0 && standbyPriorityVO.getBookedPriorityValue() != 0
                && standbyPriorityVO.getBookedCanBeDisembarked() != null) {

                bookedPriorityInfoDTO.setPriorityValue(standbyPriorityVO.getBookedPriorityValue());

                if (standbyPriorityVO.getBookedCanBeDisembarked()
                    .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
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

                if (standbyPriorityVO.getWaitlistCanBeDisembarked()
                    .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
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

                if (standbyPriorityVO.getOpenTicketCanBeDisembarked().equalsIgnoreCase(
                    StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setCanBeDisembarked(true);
                }
                else {
                    openPriorityInfoDTO.setCanBeDisembarked(false);
                }

                if (standbyPriorityVO.getHoldsSpaceAvailableTicket().equalsIgnoreCase(
                    StandbyPriorityConstants.YES_VALUE)) {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(true);
                }
                else {
                    openPriorityInfoDTO.setHoldsSpaceAvailableTicket(false);
                }

                openPriorityInfoDTO.setPriorityValue(standbyPriorityVO.getOpenTicketPriorityValue());
                bookingStatusInfoDTO.setOpenPriorityInfo(openPriorityInfoDTO);
            }
        }

        CloseStandbyPriorityListener.LOGGER.debug("Exit CloseStandbyPriorityListener.populateUpdateSPBookingStatusDTO");
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
