/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.valueobject.DefaultStandbyPriorityVO;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityRequest;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityQueryType;

/**
 * 
 * 
 * <pre>
 * <b>Description : </b>
 * This class is a Screen Helper.
 * Helper to render the Default Standby Priority screen.
 * @version $Revision: 1 $ $Date: 2011-12-05 10:06:12 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class DefaultStandbyPriorityScreenHelper extends NGAFStandardTable {

    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
        .getLogger(DefaultStandbyPriorityScreenHelper.class);

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * FIVE.
     */
    private static final int FIVE = 5;

    /**
     * FOUR.
     */
    private static final int FOUR = 4;

    /**
     * SEVEN.
     */
    private static final int SEVEN = 7;

    /**
     * NINE.
     */
    private static final int NINE = 9;

    /**
     * ELEVEN.
     */
    private static final int ELEVEN = 11;

    /**
     * TWENTY.
     */
    private static final int TWENTY = 20;

    /**
     * EIGHTY.
     */
    private static final int EIGHTY = 80;

    /**
     * ONE_HUNDRED_FOURTY_THREE.
     */
    private static final int ONE_HUNDRED_FOURTY_THREE = 143;

    /**
     * EIGHTY_FIVE.
     */
    private static final int EIGHTY_FIVE = 85;

    /**
     * THIRTEEN.
     */
    private static final int THIRTEEN = 13;

    /**
     * THIRTY_FIVE.
     */
    private static final int THIRTY_FIVE = 35;

    /**
     * FOURTEEN.
     */
    private static final int FOURTEEN = 14;

    /**
     * COLUMN_VALUE_ONE.
     */
    private static final int COLUMN_VALUE_ONE = 1;

    /**
     * COLUMN_VALUE_TWO.
     */
    private static final int COLUMN_VALUE_TWO = 2;

    /**
     * COLUMN_VALUE_THREE.
     */
    private static final int COLUMN_VALUE_THREE = 3;

    /**
     * COLUMN_VALUE_FOUR.
     */
    private static final int COLUMN_VALUE_FOUR = 4;

    /**
     * COLUMN_VALUE_FIVE.
     */
    private static final int COLUMN_VALUE_FIVE = 5;

    /**
     * COLUMN_VALUE_SIX.
     */
    private static final int COLUMN_VALUE_SIX = 6;

    /**
     * COLUMN_VALUE_SEVEN.
     */
    private static final int COLUMN_VALUE_SEVEN = 7;

    /**
     * COLUMN_VALUE_EIGHT.
     */
    private static final int COLUMN_VALUE_EIGHT = 8;

    /**
     * COLUMN_VALUE_NINE.
     */
    private static final int COLUMN_VALUE_NINE = 9;

    /**
     * COLUMN_VALUE_TEN.
     */
    private static final int COLUMN_VALUE_TEN = 10;

    /**
     * COLUMN_VALUE_ELEVEN.
     */
    private static final int COLUMN_VALUE_ELEVEN = 11;

    /**
     * COLUMN_VALUE_TWELVE.
     */
    private static final int COLUMN_VALUE_TWELVE = 12;

    /**
     * COLUMN_VALUE_THIRTEEN.
     */
    private static final int COLUMN_VALUE_THIRTEEN = 13;

    /**
     * COLUMN_VALUE_FOURTEEN.
     */
    private static final int COLUMN_VALUE_FOURTEEN = 14;

    /**
     * COLUMN_VALUE_FIFTEEN.
     */
    private static final int COLUMN_VALUE_FIFTEEN = 15;
    /**
     * PAD_OPTIONS.
     */
    private static final String[] PAD_OPTIONS = { "Yes", "No" };

    /**
     * DATE_OF_JOINING_OPTIONS.
     */
    private static final String[] DATE_OF_JOINING_OPTIONS = { "Optional", "Not required" };
    /**
     * Reference for StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * Method to load the Default Standby Priority Screen.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to load the Default Standby Priority Screen.
     * 
     * </pre>
     */
    public void updateScreenUI() {
        DefaultStandbyPriorityScreenHelper.LOGGER.debug("Entered DefaultStandbyPriorityScreenHelper.updateScreenUI");

        clearAppContext();
        setColumnWidth();
        alignColumnHeader();
        setKeyStroke();
        setButtonOnScreenLoad();
        fillDefaultSPNoOfRowsCombobox();
        refreshDefaultSPTable();
        initalizeDefaultSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
        getDefaultStandbyPriority();
        controller.getSearchSPResetButton().setEnabled(true);
        DefaultStandbyPriorityScreenHelper.LOGGER.debug("Exited DefaultStandbyPriorityScreenHelper.updateScreenUI");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to retrieve the Default Standby priority data on page load.
     * 
     * </pre>
     */
    private void getDefaultStandbyPriority() {
        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Enter DefaultStandbyPriorityScreenHelper.getDefaultStandbyPriority");

        try {
            if (NGAFContextUtil.getApplicationContext().
                get(StandbyPriorityConstants.DEFAULT_SP_RESPONSE_ROWCOUNT) != null) {
                NGAFContextUtil.getApplicationContext()
                    .put(StandbyPriorityConstants.DEFAULT_SP_RESPONSE_ROWCOUNT, null);
            }

            final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = populateFindSPRequestDTO();

            if (controller.getManageStandbyPriorityProxy() != null) {
                try {
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = controller
                        .getManageStandbyPriorityProxy().findStandbyPriority(findStandbyPriorityRequestDTO);

                    if (findStandbyPriorityResponseDTO != null) {
                        controller.getDefaultSPCreateButton().setVisible(false);
                        controller.getDefaultSPCreateButton().setEnabled(false);
                        controller.getDefaultSPUpdateButton().setVisible(true);
                        controller.getDefaultSPUpdateButton().setEnabled(true);
                        controller.getDefaultSPCopyButton().setEnabled(true);

                        renderDefaultSPTable(findStandbyPriorityResponseDTO);
                    }
                }
                catch (CheckInException ex) {
                    DefaultStandbyPriorityScreenHelper.LOGGER
                        .error("Error in DefaultStandbyPriorityScreenHelper.getDefaultStandbyPriority : " + ex);
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                }
            }
        }
        catch (Exception ex) {
            DefaultStandbyPriorityScreenHelper.LOGGER
                .error("Error in DefaultStandbyPriorityScreenHelper.getDefaultStandbyPriority : " + ex.getMessage());
        }

        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Exit DefaultStandbyPriorityScreenHelper.getDefaultStandbyPriority");
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to populate request for Find Standby Priority.
     * 
     * @return findStandbyPriorityRequestDTO , never null.
     * </pre>
     */
    private FindStandbyPriorityRequest populateFindSPRequestDTO() {
        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Enter DefaultStandbyPriorityScreenHelper.populateFindSPRequestDTO");

        final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = new FindStandbyPriorityRequest();
        final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();

        findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);

        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Exit DefaultStandbyPriorityScreenHelper.populateFindSPRequestDTO");

        return findStandbyPriorityRequestDTO;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method is to populate the Find StandbyPriority DTO to Screen.
     * 
     * @param findStandbyPriorityResponseDTO , not null.
     * </pre>
     */
    private void renderDefaultSPTable(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {
        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Enter DefaultStandbyPriorityScreenHelper.renderDefaultSPTable");
        int totalRecords;

        if (findStandbyPriorityResponseDTO != null) {
            if (findStandbyPriorityResponseDTO.getStandbyPriorityList() != null
                && findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities() != null) {
                if (findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                    .getStandbyPriorityList() != null) {
                    totalRecords = findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                        .getStandbyPriorityList().size();

                    List<StandbyPriorityType> standbyPriorityDTOList = findStandbyPriorityResponseDTO
                        .getStandbyPriorityList().getStandbyPriorities().getStandbyPriorityList();
                    // int noofRows
                    // =StandbyPriorityConstants.NO_OF_ROWS-standbyPriorityDTOList.size();
                    NGAFContextUtil.getApplicationContext().put(
                        StandbyPriorityConstants.DEFAULT_PRIORITY_RESPONSE_LIST, standbyPriorityDTOList);
                    if (standbyPriorityDTOList.size() < StandbyPriorityConstants.NO_OF_ROWS) {
                        initalizeDefaultSPTableCells(standbyPriorityDTOList.size()
                            - StandbyPriorityConstants.NO_OF_ROWS);
                    }
                    else {
                        initalizeDefaultSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
                    }

                    controller.getDefaultSPStandardTable().getFilterRow();
                    controller.getDefaultSPFilterTablePanel().populateData();
                    if (controller.getDefaultSPStandardTable().getSelectedRowCount() > 0) {
                        controller.getDefaultSPStandardTable().removeRowSelectionInterval(0, 0);
                    }
                    /*
                     * Object object = SITAGUIServiceUtil.getServiceObject (
                     * "aero.sita.csp.gui.table.NGAFTableResponseListener",
                     * "ngaftable.listener",
                     * controller.getDefaultSPStandardTable
                     * ().getTableResponseListenerFilter ( ) );
                     */

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.DEFAULT_SP_RESPONSE_ROWCOUNT,
                        totalRecords);

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITIES,
                        findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities());
                }
            }
        }

        DefaultStandbyPriorityScreenHelper.LOGGER.debug("Exit DefaultStandbyPriorityScreenHelper.renderDefaultSPTable");
    }

    /**
     * Method to fetch Default Standby Priority Table Data to VO.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to fetch Default Standby Priority Table Data to VO.
     * 
     * @return defaultSPVOList, never null.
     * </pre>
     */
    public List<DefaultStandbyPriorityVO> fetchDefaultSPTableData() {
        DefaultStandbyPriorityScreenHelper.LOGGER.debug("Entered DefaultStandbyPriorityScreenHelper." + "");

        List<DefaultStandbyPriorityVO> defaultSPVOList = new ArrayList<DefaultStandbyPriorityVO>();

        if (controller.getDefaultSPFilterTablePanel() != null
            && controller.getDefaultSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel dm = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();

            for (int i = 0; i < dm.getRowCount(); i++) {
                DefaultStandbyPriorityVO defaultSPVO = new DefaultStandbyPriorityVO();

                if (dm.getValueAt(i, COLUMN_VALUE_TWO) != null
                    && !("").equals(dm.getValueAt(i, COLUMN_VALUE_TWO).toString().trim())
                    && dm.getValueAt(i, COLUMN_VALUE_THREE) != null
                    && !("").equals(dm.getValueAt(i, COLUMN_VALUE_THREE).toString().trim())) {

                    defaultSPVO.setDefault(true);
                    defaultSPVO.setPriorityStatusCode(dm.getValueAt(i, COLUMN_VALUE_TWO).toString().trim());
                    defaultSPVO.setPriorityStatusDesc(dm.getValueAt(i, COLUMN_VALUE_THREE).toString().trim());
                    defaultSPVO.setCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_FOUR).toString());
                    defaultSPVO.setDateOfJoining(dm.getValueAt(i, COLUMN_VALUE_FIVE).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_SIX) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_SIX).toString().trim())) {
                        defaultSPVO.setBookedPriorityValue(Integer.parseInt(dm.getValueAt(i, COLUMN_VALUE_SIX)
                            .toString().trim()));
                    }

                    defaultSPVO.setBookedCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_SEVEN).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_EIGHT) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_EIGHT).toString().trim())) {
                        defaultSPVO.setNoRecPriorityValue(Integer.parseInt(dm.getValueAt(i, COLUMN_VALUE_EIGHT)
                            .toString().trim()));
                    }

                    defaultSPVO.setNoRecCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_NINE).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_TEN) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_TEN).toString().trim())) {
                        defaultSPVO.setWaitlistPriorityValue(Integer.parseInt(dm.getValueAt(i, COLUMN_VALUE_TEN)
                            .toString().trim()));
                    }

                    defaultSPVO.setWaitlistCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_ELEVEN).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_TWELVE) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_TWELVE).toString().trim())) {
                        defaultSPVO.setOpenTicketPriorityValue(Integer.parseInt(dm.getValueAt(i, COLUMN_VALUE_TWELVE)
                            .toString().trim()));
                    }

                    defaultSPVO.setOpenTicketCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_THIRTEEN).toString());
                    defaultSPVO.setHoldsSpaceAvailableTicket(dm.getValueAt(i, COLUMN_VALUE_FOURTEEN).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_FIFTEEN) != null) {
                        defaultSPVO.setDocumentId(dm.getValueAt(i, COLUMN_VALUE_FIFTEEN).toString());
                    }

                    defaultSPVO.setRowNumber(i);

                    defaultSPVOList.add(defaultSPVO);
                    // defaultSPVO = null;
                }
            }
        }

        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Exited DefaultStandbyPriorityScreenHelper.fetchDefaultSPTableData");

        return defaultSPVOList;
    }

    /**
     * Method to refresh the Default Standby Priority Table.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to refresh the Default Standby Priority Table.
     * 
     * </pre>
     */
    private void refreshDefaultSPTable() {
        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Entered DefaultStandbyPriorityScreenHelper.refreshDefaultSPTable");

        if (controller.getDefaultSPFilterTablePanel() != null
            && controller.getDefaultSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel defaultTableModel = (DefaultTableModel) controller.getDefaultSPFilterTablePanel()
                .getModel();

            if (controller.getDefaultSPFilterTablePanel() != null
                && controller.getDefaultSPFilterTablePanel().getTable() != null) {
                int rowCount = controller.getDefaultSPFilterTablePanel().getTable().getRowCount();

                for (int rows = 0; rows < rowCount; rows++) {
                    defaultTableModel.removeRow(0);
                }
            }
        }

        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Exited DefaultStandbyPriorityScreenHelper.refreshDefaultSPTable");
    }

    /**
     * Method to initialize Default Standby Priority Table.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to initialize Default Standby Priority Table.
     * @param rowCount , not null.
     * </pre>
     */
    private void initalizeDefaultSPTableCells(final int rowCount) {
        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Entered DefaultStandbyPriorityScreenHelper.initalizeDefaultSPTableCells");

        if (controller.getDefaultSPFilterTablePanel() != null
            && controller.getDefaultSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel dm = (DefaultTableModel) controller.getDefaultSPFilterTablePanel().getModel();
            final Object[][] dataArray = new Object[][] { { "", "", "Yes", "Optional", "", "Yes", "", "Yes", "", "Yes",
                    "", "Yes", "Yes", }, };

            for (int i = 0; i < rowCount; i++) {
                dm.addRow(dataArray[0]);
            }

            if (controller.getDefaultSPFilterTablePanel().getTable() != null) {
                NGAFStandardTable defaultSPStandardTable = (NGAFStandardTable) controller
                    .getDefaultSPFilterTablePanel().getTable();
                final JComboBox comboBoxPadOptions = new JComboBox(PAD_OPTIONS);
                final JComboBox comboBoxDOJ = new JComboBox(DATE_OF_JOINING_OPTIONS);

                defaultSPStandardTable.getColumnModel().getColumn(FOUR)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                defaultSPStandardTable.getColumnModel().getColumn(SEVEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                defaultSPStandardTable.getColumnModel().getColumn(NINE)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                defaultSPStandardTable.getColumnModel().getColumn(ELEVEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                defaultSPStandardTable.getColumnModel().getColumn(THIRTEEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                defaultSPStandardTable.getColumnModel().getColumn(FOURTEEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));

                defaultSPStandardTable.getColumnModel().getColumn(FIVE)
                    .setCellEditor(new ComboBoxCellEditorDOJ(comboBoxDOJ));

                final ComboBoxRenderer rendererPadOptions1 = new ComboBoxRenderer(PAD_OPTIONS);
                defaultSPStandardTable.getColumnModel().getColumn(FOUR).setCellRenderer(rendererPadOptions1);

                final ComboBoxRenderer rendererPadOptions2 = new ComboBoxRenderer(PAD_OPTIONS);
                defaultSPStandardTable.getColumnModel().getColumn(SEVEN).setCellRenderer(rendererPadOptions2);

                final ComboBoxRenderer rendererPadOptions3 = new ComboBoxRenderer(PAD_OPTIONS);
                defaultSPStandardTable.getColumnModel().getColumn(NINE).setCellRenderer(rendererPadOptions3);

                final ComboBoxRenderer rendererPadOptions4 = new ComboBoxRenderer(PAD_OPTIONS);
                defaultSPStandardTable.getColumnModel().getColumn(ELEVEN).setCellRenderer(rendererPadOptions4);

                final ComboBoxRenderer rendererPadOptions5 = new ComboBoxRenderer(PAD_OPTIONS);
                defaultSPStandardTable.getColumnModel().getColumn(THIRTEEN).setCellRenderer(rendererPadOptions5);

                final ComboBoxRenderer rendererPadOptions6 = new ComboBoxRenderer(PAD_OPTIONS);
                defaultSPStandardTable.getColumnModel().getColumn(FOURTEEN).setCellRenderer(rendererPadOptions6);

                final ComboBoxRendererDOJ rendererDOJ1 = new ComboBoxRendererDOJ(DATE_OF_JOINING_OPTIONS);
                defaultSPStandardTable.getColumnModel().getColumn(FIVE).setCellRenderer(rendererDOJ1);

                setVisible(true);
            }
        }

        DefaultStandbyPriorityScreenHelper.LOGGER
            .debug("Exited DefaultStandbyPriorityScreenHelper.initalizeDefaultSPTableCells");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method is to render default rows in the table.
     * 
     * @param noOfRows , not null.
     * </pre>
     */
    public void callInitialised(final int noOfRows) {
        initalizeDefaultSPTableCells(noOfRows);
    }

    /**
     * Method to set the button status.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to set the button status.
     * 
     * </pre>
     */
    private void setButtonOnScreenLoad() {
        controller.getDefaultSPAddRowsButton().setEnabled(true);
        controller.getDefaultSPCreateButton().setEnabled(true);
        controller.getDefaultSPCopyButton().setEnabled(false);
        controller.getDefaultSPViewEditDescButton().setEnabled(false);
        controller.getDefaultSPDeleteSelRowsButton().setEnabled(false);
    }

    /**
     * Method to fill the No of Rows Combobox.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to fill the No of Rows Combobox.
     * 
     * </pre>
     */
    private void fillDefaultSPNoOfRowsCombobox() {
        controller.getDefaultSPNoOfRowsCombobox().removeAllItems();

        for (int item = 1; item <= StandbyPriorityConstants.ADD_NO_OF_ROWS; item++) {
            controller.getDefaultSPNoOfRowsCombobox().addItem(item);
        }
    }

    /**
     * This method will set the key stroke.
     * 
     * <pre>
     * <b>Description : </b>
     * This method will set the key stroke
     * 
     * </pre>
     */
    private void setKeyStroke() {
        controller.getDefaultSPStandardTable().getInputMap(JComponent.WHEN_FOCUSED)
            .put(KeyStroke.getKeyStroke("DELETE"), null);
        controller.getDefaultSPStandardTable().getTableHeader().setReorderingAllowed(false);

        final Border gridBorder = BorderFactory.createLineBorder(controller.getDefaultSPStandardTable().getGridColor());

        controller.getDefaultSPStandardTable().setBorder(gridBorder);
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method will set the column width.
     * 
     * </pre>
     */
    private void setColumnWidth() {

        final TableColumn columnNumber = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_ONE);
        columnNumber.setPreferredWidth(TWENTY);

        final JTableHeader header = new JTableHeader();
        header.setName(StandbyPriorityConstants.NO_VALUE);
        columnNumber.setHeaderValue(header.getName());

        final TableColumn columnPriorityCode = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_TWO);
        columnPriorityCode.setPreferredWidth(EIGHTY);

        final TableColumn columnDescription = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_THREE);
        columnDescription.setPreferredWidth(ONE_HUNDRED_FOURTY_THREE);

        final TableColumn columnCheckPAD = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_FOUR);
        columnCheckPAD.setPreferredWidth(columnCheckPAD.getPreferredWidth());

        final TableColumn columnDateOfJoining = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_FIVE);
        columnDateOfJoining.setPreferredWidth(EIGHTY_FIVE);

        final TableColumn columnBookedPriority = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_SIX);
        columnBookedPriority.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnBookedPAD = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_SEVEN);
        columnBookedPAD.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnNorecPriority = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_EIGHT);
        columnNorecPriority.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnNorecPAD = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_NINE);
        columnNorecPAD.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnWaitlistPriority = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_TEN);
        columnWaitlistPriority.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnWaitlistPAD = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_ELEVEN);
        columnWaitlistPAD.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnOpenTicketPriority = controller.getDefaultSPStandardTable().getColumn(
            COLUMN_VALUE_TWELVE);
        columnOpenTicketPriority.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnOpenTicketPAD = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_THIRTEEN);
        columnOpenTicketPAD.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnOpenTicketSATicket = controller.getDefaultSPStandardTable().getColumn(
            COLUMN_VALUE_FOURTEEN);
        columnOpenTicketSATicket.setPreferredWidth(THIRTY_FIVE);

        final TableColumn columnDocumentID = controller.getDefaultSPStandardTable().getColumn(COLUMN_VALUE_FIFTEEN);
        columnDocumentID.setWidth(0);
        columnDocumentID.setMinWidth(0);
        columnDocumentID.setMaxWidth(0);
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to align the column Header.
     * 
     * </pre>
     */
    private void alignColumnHeader() {
        controller.getDefaultSPBookedLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getDefaultSPBookedLabel().setOpaque(true);

        controller.getDefaultSPNoRecLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getDefaultSPNoRecLabel().setOpaque(true);

        controller.getDefaultSPWaitlistLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getDefaultSPWaitlistLabel().setOpaque(true);

        controller.getDefaultSPOpenTicketLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getDefaultSPOpenTicketLabel().setOpaque(true);
    }

    /**
     * Method to clear the application context variables.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to clear the application context variables.
     * 
     * </pre>
     */
    private void clearAppContext() {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITY_RESPONSE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITY_RESPONSE,
                null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_SUBSCRIBER_CODE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_AIRLINE_CODE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST, null);
        }
    }

    /* *//**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to Convert boolean value to 'Yes' and 'No'.
     * 
     * @param booleanValue , not null.
     * @return String , never null.
     * </pre>
     */
    /*
     * private String booleanToString(final boolean booleanValue) { if
     * (booleanValue) { return StandbyPriorityConstants.YES_VALUE; }
     * 
     * return StandbyPriorityConstants.NO_VALUE; }
     */

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
