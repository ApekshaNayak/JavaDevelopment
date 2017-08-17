/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.helpers;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jdesktop.jxlayer.JXLayer;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.common.ScreenRegistry;
import aero.sita.csp.gui.thickclient.component.lov.SITAGUILOVComponent;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.componentextension.mandatory.ui.SlantLineUI;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.csp.gui.thickclient.coreapi.base.ModuleDO;
import aero.sita.csp.gui.thickclient.screencontainer.tabhelper.NGAFTabbedPaneHelper;
import aero.sita.voyager.dcs.client.common.base.controller.UIBaseController;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsViewHandler;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.valueobject.StandbyPriorityVO;
import aero.sita.voyager.checkin.common.interfaces.exception.v9.CheckInException;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.horizon.schemas.metadata.v1.ActionCodeType;
import aero.sita.horizon.schemas.metadata.v1.AdministrativeRecordType;
import	aero.sita.horizon.schemas.metadata.v1.DocumentHeaderType;
import aero.sita.horizon.schemas.metadata.v1.LanguageCodeType;
import aero.sita.horizon.schemas.metadata.v1.SchemaVersionAttrGroup;
import aero.sita.horizon.schemas.metadata.v1.SubscriberIDType;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.BookingStatusPriorityInfoType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.ConfirmStandbyPriority;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.CreateStandbyPriority;
import aero.sita.voyager.checkin.transferobjects.v9.DateOfJoiningRequiredType;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityRequest;
import aero.sita.voyager.checkin.standbypriority.transferobjects.v1.FindStandbyPriorityResponse;
import aero.sita.voyager.checkin.transferobjects.v9.OpenPriorityInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPrioritiesType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityQueryType;

/**
 * <pre>
     * <b>Description : </b>
     * StandByPriortyDetailsHelper.
     * 
     * @version $Revision: 1 $ $Date: 2011-12-05 10:06:13 AM $
     * @author $Author: nandinee.ramanathan $
     * </pre>
 */
public class StandByPriortyDetailsHelper extends NGAFStandardTable {

    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(StandByPriortyDetailsHelper.class);

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * callTime, to see number of times open is called.
     */
    private  int callTime = 0;

    /**
     * Constant Value 1.
     */
    private static final int COLUMN_VALUE_ONE = 1;

    /**
     * Constant Value 2.
     */
    private static final int COLUMN_VALUE_TWO = 2;

    /**
     * Constant Value 3.
     */
    private static final int COLUMN_VALUE_THREE = 3;

    /**
     * Constant Value 4.
     */
    private static final int COLUMN_VALUE_FOUR = 4;

    /**
     * Constant Value 5.
     */
    private static final int COLUMN_VALUE_FIVE = 5;

    /**
     * Constant Value 6.
     */
    private static final int COLUMN_VALUE_SIX = 6;

    /**
     * Constant Value 7.
     */
    private static final int COLUMN_VALUE_SEVEN = 7;

    /**
     * Constant Value 8.
     */
    private static final int COLUMN_VALUE_EIGHT = 8;

    /**
     * Constant Value 9.
     */
    private static final int COLUMN_VALUE_NINE = 9;

    /**
     * Constant Value 10.
     */
    private static final int COLUMN_VALUE_TEN = 10;

    /**
     * Constant Value 11.
     */
    private static final int COLUMN_VALUE_ELEVEN = 11;

    /**
     * Constant Value 12.
     */
    private static final int COLUMN_VALUE_TWELVE = 12;

    /**
     * Constant Value 13.
     */
    private static final int COLUMN_VALUE_THIRTEEN = 13;

    /**
     * Constant Value 14.
     */
    private static final int COLUMN_VALUE_FOURTEEN = 14;

    /**
     * Constant Value 35.
     */
    private static final int COLUMN_VALUE_THIRTYFIVE = 35;

    /**
     * Constant Value 85.
     */
    private static final int COLUMN_VALUE_EIGHTYFIVE = 85;

    /**
     * Constant Value 140.
     */
    private static final int COLUMN_VALUE_ONEFOURTY = 140;

    /**
     * Constant Value 80.
     */
    private static final int COLUMN_VALUE_EIGHTY = 80;

    /**
     * Constant Value 20.
     */
    private static final int COLUMN_VALUE_TWENTY = 20;

    /**
     * COLUMN_VALUE_FIFTEEN.
     */
    private static final int COLUMN_VALUE_FIFTEEN = 15;

    /**
     * FOUR.
     */
    private static final int FOUR = 4;

    /**
     * FOUR.
     */
    private static final int FIVE = 5;

    /**
     * FOUR.
     */
    private static final int FOURTEEN = 14;

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
     * THIRTEEN.
     */
    private static final int THIRTEEN = 13;

    /**
     * AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;

    /**
     * AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;

    /**
     * Reference for StandbyPriorityDetailsController.
     */
    private StandbyPriorityDetailsController controller;
    /**
     * PAD_OPTIONS.
     */
     private static final String[] PAD_OPTIONS = { "Yes", "No" };

    /**
     * DATE_OF_JOINING_OPTIONS.
     */
     private static final String[] DATE_OF_JOINING_OPTIONS = { "Optional", "Not required" };
    /**
     * <pre>
         * <b>Description : </b>
         * Get the 'controller' attribute value.
         * 
         * @return controller , null if not found.
         * </pre>
     */
    public StandbyPriorityDetailsController getController() {
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
    public void setController(final StandbyPriorityDetailsController controllerParam) {
        controller = controllerParam;
    }

    /**
     * Method to load the New Standby Priority Screen.
     * 
     * <pre>
         * <b>Description : </b>
         * Method to load the New Standby Priority Screen.
         * 
         * </pre>
     */
    public final void updateScreenUI() {

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {

            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }
        // refreshSPTable();
        // initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
        refreshSPScreen();
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Default Standby priority data.
         * 
         * </pre>
     */
    public void getStandByPriorityInDontSave() {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {

            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }
        // refreshSPTable();
        // initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
        refreshSPScreenOnCopyStandBy();
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to create the  Standby priority data.
         * 
         * </pre>
     */
    public final void createStandBy() {
        if (NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB, null);
        }
        if (NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB,
                null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.OPEN_SEARCH) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.OPEN_SEARCH, null);
        }
        setComponentBorder();
        setColumnWidth();
        alignColumnHeader();
        setKeyStroke();
        setButtonOnScreenLoad();
        fillSPNoOfRowsCombobox();
        // //refreshSPTable();
        initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
        // refreshSPScreen();
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method will set border for the lov.
         * 
         * </pre>
     */
    private void setComponentBorder() {
        controller.getNewSPSubscriberLOV().setBorder(null);
        controller.getNewSPAirlineLOV().setBorder(null);
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
        // setting the Height of the table.
        // controller.getNewSPStandardTable().setPreferredSize(new
        // Dimension(900,480));

        // final TableColumn columnNumber =
        // controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_ONE);
        // columnNumber.setPreferredWidth(20);

        final TableColumn columnNumber = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_ONE);
        columnNumber.setPreferredWidth(COLUMN_VALUE_TWENTY);

        final JTableHeader header = new JTableHeader();
        header.setName("No");
        columnNumber.setHeaderValue(header.getName());

        final TableColumn columnPriorityCode = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_TWO);
        columnPriorityCode.setPreferredWidth(COLUMN_VALUE_EIGHTY);

        final TableColumn columnDescription = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_THREE);
        columnDescription.setPreferredWidth(COLUMN_VALUE_ONEFOURTY);

        final TableColumn columnCheckPAD = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_FOUR);
        columnCheckPAD.setPreferredWidth(columnCheckPAD.getPreferredWidth());

        final TableColumn columnDateOfJoining = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_FIVE);
        columnDateOfJoining.setPreferredWidth(COLUMN_VALUE_EIGHTYFIVE);

        final TableColumn columnBookedPriority = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_SIX);
        columnBookedPriority.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnBookedPAD = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_SEVEN);
        columnBookedPAD.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnNorecPriority = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_EIGHT);
        columnNorecPriority.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnNorecPAD = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_NINE);
        columnNorecPAD.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnWaitlistPriority = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_TEN);
        columnWaitlistPriority.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnWaitlistPAD = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_ELEVEN);
        columnWaitlistPAD.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnOpenTicketPriority = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_TWELVE);
        columnOpenTicketPriority.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnOpenTicketPAD = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_THIRTEEN);
        columnOpenTicketPAD.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnOpenTicketSATicket = controller.getNewSPStandardTable()
            .getColumn(COLUMN_VALUE_FOURTEEN);
        columnOpenTicketSATicket.setPreferredWidth(COLUMN_VALUE_THIRTYFIVE);

        final TableColumn columnHiddenColumn = controller.getNewSPStandardTable().getColumn(COLUMN_VALUE_FIFTEEN);
        columnHiddenColumn.setMinWidth(0);
        columnHiddenColumn.setMaxWidth(0);
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
        controller.getNewSPBookedLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getNewSPBookedLabel().setOpaque(true);

        controller.getNewSPNoRecLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getNewSPNoRecLabel().setOpaque(true);

        controller.getNewSPWaitlistLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getNewSPWaitlistLabel().setOpaque(true);

        controller.getNewSPOpenTicketLabel().setAlignmentX(JLabel.CENTER_ALIGNMENT);
        controller.getNewSPOpenTicketLabel().setOpaque(true);
    }

    /**
     * <pre>
         * <b>Description : </b> 
         * Adds mandatory mark to the text field of LOV.
         * @param searchSPSubscriberLOV , not null.
         * </pre>
     */

    public static void decorateLOVToMandatory(final SITAGUILOVComponent searchSPSubscriberLOV) {

        JTextField textField = searchSPSubscriberLOV.getSearchField();
        JButton button = searchSPSubscriberLOV.getSearchButton();
        Container parent1 = textField.getParent();
        SlantLineUI decoratorUI = new SlantLineUI();
        decoratorUI.setColor(Color.RED);
        JXLayer<JComponent> componentJXLayer = new JXLayer<JComponent>(textField, decoratorUI);
        parent1.add(componentJXLayer);
        parent1.add(button);

    }

    /**
     * <pre>
         * <b>Description : </b> 
         * Adds mandatory mark to the text field of LOV.
         * @param newSPAirlineLOV , not null.
         * </pre>
     */

    public static void decorateAirlineLOVToMandatory(final SITAGUILOVComponent newSPAirlineLOV) {

        JTextField textField = newSPAirlineLOV.getSearchField();
        JButton button = newSPAirlineLOV.getSearchButton();
        Container parent2 = textField.getParent();
        SlantLineUI decoratorUI = new SlantLineUI();
        decoratorUI.setColor(Color.RED);
        JXLayer<JComponent> componentJXLayer = new JXLayer<JComponent>(textField, decoratorUI);
        parent2.add(componentJXLayer);
        parent2.add(button);

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
        controller.getNewSPStandardTable().getInputMap(JComponent.WHEN_FOCUSED)
            .put(KeyStroke.getKeyStroke("DELETE"), null);
        controller.getNewSPStandardTable().getTableHeader().setReorderingAllowed(false);

        final Border gridBorder = BorderFactory.createLineBorder(controller.getNewSPStandardTable().getGridColor());
        controller.getNewSPStandardTable().setBorder(gridBorder);
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
        controller.getNewSPAddRowsButton().setEnabled(true);
        controller.getNewSPCreateButton().setEnabled(true);
        controller.getNewSPCopyButton().setEnabled(false);
        controller.getNewSPCloseButton().setEnabled(true);
        controller.getNewSPViewEditDescButton().setEnabled(false);
        controller.getNewSPDeleteSelRowsButton().setEnabled(false);
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
    private void fillSPNoOfRowsCombobox() {
        controller.getNewSPNoOfRowsCombobox().removeAllItems();

        for (int item = 1; item <= StandbyPriorityConstants.ADD_NO_OF_ROWS; item++) {
            controller.getNewSPNoOfRowsCombobox().addItem(item);
        }
    }

    /**
     * Method to refresh the Standby Priority Table.
     * 
     * <pre>
         * <b>Description : </b>
         * Method to refresh the Standby Priority Table.
         * 
         * </pre>
     */
    private void refreshSPTable() {
        if (controller.getNewSPFilterTablePanel() != null && controller.getNewSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel defaultTableModel = (DefaultTableModel) controller.getNewSPFilterTablePanel()
                .getModel();

            if (controller.getNewSPFilterTablePanel() != null
                && controller.getNewSPFilterTablePanel().getTable() != null) {
                int rowCount = controller.getNewSPFilterTablePanel().getTable().getRowCount();

                for (int rows = 0; rows < rowCount; rows++) {
                    defaultTableModel.removeRow(0);
                }
            }
        }

    }

    /**
     * Method to initialize Standby Priority Table.
     * 
     * <pre>
         * <b>Description : </b>
         * Method to initialize Standby Priority Table.
         * @param rowCount , not null.
         * </pre>
     */
    private void initalizeSPTableCells(final int rowCount) {

        if (controller.getNewSPFilterTablePanel() != null && controller.getNewSPFilterTablePanel().getModel() != null) {
            final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();
            final Object[][] dataArray = new Object[][] { { "", "", "Yes", "Optional", "", "Yes", "", "Yes", "", "Yes",
                    "", "Yes", "Yes", }, };

            for (int i = 0; i < rowCount; i++) {
                dm.addRow(dataArray[0]);
            }

            if (controller.getNewSPFilterTablePanel().getTable() != null) {
                NGAFStandardTable newSPStandardTable = (NGAFStandardTable) controller.getNewSPFilterTablePanel()
                    .getTable();

                final JComboBox comboBoxPadOptions = new JComboBox(PAD_OPTIONS);
                final JComboBox comboBoxDOJ = new JComboBox(DATE_OF_JOINING_OPTIONS);

                newSPStandardTable.getColumnModel().getColumn(FOUR)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                newSPStandardTable.getColumnModel().getColumn(SEVEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                newSPStandardTable.getColumnModel().getColumn(NINE)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                newSPStandardTable.getColumnModel().getColumn(ELEVEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                newSPStandardTable.getColumnModel().getColumn(THIRTEEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));
                newSPStandardTable.getColumnModel().getColumn(FOURTEEN)
                    .setCellEditor(new ComboBoxCellEditor(comboBoxPadOptions));

                newSPStandardTable.getColumnModel().getColumn(FIVE)
                    .setCellEditor(new ComboBoxCellEditorDOJ(comboBoxDOJ));

                final ComboBoxRenderer rendererPadOptions1 = new ComboBoxRenderer(PAD_OPTIONS);
                newSPStandardTable.getColumnModel().getColumn(FOUR).setCellRenderer(rendererPadOptions1);

                final ComboBoxRenderer rendererPadOptions2 = new ComboBoxRenderer(PAD_OPTIONS);
                newSPStandardTable.getColumnModel().getColumn(SEVEN).setCellRenderer(rendererPadOptions2);

                final ComboBoxRenderer rendererPadOptions3 = new ComboBoxRenderer(PAD_OPTIONS);
                newSPStandardTable.getColumnModel().getColumn(NINE).setCellRenderer(rendererPadOptions3);

                final ComboBoxRenderer rendererPadOptions4 = new ComboBoxRenderer(PAD_OPTIONS);
                newSPStandardTable.getColumnModel().getColumn(ELEVEN).setCellRenderer(rendererPadOptions4);

                final ComboBoxRenderer rendererPadOptions5 = new ComboBoxRenderer(PAD_OPTIONS);
                newSPStandardTable.getColumnModel().getColumn(THIRTEEN).setCellRenderer(rendererPadOptions5);

                final ComboBoxRenderer rendererPadOptions6 = new ComboBoxRenderer(PAD_OPTIONS);
                newSPStandardTable.getColumnModel().getColumn(FOURTEEN).setCellRenderer(rendererPadOptions6);

                final ComboBoxRendererDOJ rendererDOJ1 = new ComboBoxRendererDOJ(
                    DATE_OF_JOINING_OPTIONS);
                newSPStandardTable.getColumnModel().getColumn(FIVE).setCellRenderer(rendererDOJ1);

                setVisible(true);
            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper.initalizeSPTableCells");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to refresh the Standby Priority tab.
         * 
         * </pre>
     */
    private void refreshSPScreenOnCopyStandBy() {
        String subscriberCode = null;
        String airlineNumber = null;

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CURRENT_SUBSCRIBER) != null) {
            subscriberCode = (String) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.CURRENT_SUBSCRIBER);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CURRENT_AIRLINE) != null) {
            airlineNumber = (String) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.CURRENT_AIRLINE);
        }
        controller.getNewSPAirlineLOV().getSearchField().setText("");
        controller.getNewSPSubscriberLOV().getSearchField().setText("");
        if (subscriberCode != null && airlineNumber != null) {
            FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getStandbyPriority(subscriberCode,
                airlineNumber);
            if (findStandbyPriorityResponseDTO != null) {
                if (DcsTabDataContainer.getInstance().getCurrentTabName() != null) {
                    String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
                    DcsTabDataContainer.getInstance().addTabAndDTO(currentTabName, findStandbyPriorityResponseDTO);
                }
                // renderSPTable(findStandbyPriorityResponseDTO);
                renderSPTableOnCopyStandBy(findStandbyPriorityResponseDTO);
            }
        }

    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to refresh the Standby Priority tab.
         * 
         * </pre>
     */
    private void refreshSPScreen() {
        String subscriberCode = null;
        String airlineCode;
        String airlineNumber = null;

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_SUBSCRIBER_CODE) != null) {
            subscriberCode = (String) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.SP_SUBSCRIBER_CODE);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_AIRLINE_CODE) != null) {
            airlineCode = (String) NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.SP_AIRLINE_CODE);
            if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
                airlineNumber = populateAirlineNumber(airlineCode, true);
            }

            if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
                airlineNumber = populateAirlineNumber(airlineCode, false);
            }
        }
        if (subscriberCode != null && airlineNumber != null) {
            FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getStandbyPriority(subscriberCode,
                airlineNumber);
            if (findStandbyPriorityResponseDTO != null) {
                if (DcsTabDataContainer.getInstance().getCurrentTabName() != null) {
                    String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
                    DcsTabDataContainer.getInstance().addTabAndDTO(currentTabName, findStandbyPriorityResponseDTO);
                }
                renderSPTable(findStandbyPriorityResponseDTO);

            }
        }

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
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Standby priority data on refresh.
         * @param subscriberCode , not null.
         * @param airlineNumber , not null.
         * 
         * @return findStandbyPriorityResponseDTO , never null.
         * </pre>
     */
    private FindStandbyPriorityResponse getStandbyPriority(final String subscriberCode, final String airlineNumber) {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper:getStandbyPriority");
        FindStandbyPriorityResponse findStandbyPriorityResponseDTO = null;
        try {

            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT) != null) {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT, 0);
            }

            if (subscriberCode != null && airlineNumber != null) {
                final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = populateFindSPRequestDTO(
                    subscriberCode, airlineNumber);

                if (controller.getManageStandbyPriorityProxy() != null) {
                    try {
                        findStandbyPriorityResponseDTO = controller.getManageStandbyPriorityProxy()
                            .findStandbyPriority(findStandbyPriorityRequestDTO);

                        if (findStandbyPriorityResponseDTO != null) {
                            return findStandbyPriorityResponseDTO;
                        }
                    }
                    catch (CheckInException ex) {
                        StandByPriortyDetailsHelper.LOGGER.debug("Error occured" + ex);
                        DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                    }
                }
            }

        }
        catch (Exception ex) {
            StandByPriortyDetailsHelper.LOGGER.debug("Error : " + ex.getMessage());
        }
        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper:getStandbyPriority");
        return findStandbyPriorityResponseDTO;

    }

    /**
     * <pre>
         * <b>Description : </b>
         * Method to populate request for Find Standby Priority.
         * @param subscriberCode , not null.
         * @param airlineNumber , not null.
         * @return findStandbyPriorityRequestDTO , never null.
         * </pre>
     */
    private FindStandbyPriorityRequest populateFindSPRequestDTO(final String subscriberCode,
        final String airlineNumber) {
        StandByPriortyDetailsHelper.LOGGER.debug("Enter DefaultStandbyPriorityScreenHelper.populateFindSPRequestDTO");
        final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = new FindStandbyPriorityRequest();
        final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();
        final AirlineInfoType airLineInfoDTO = new AirlineInfoType();

        if (isNotEmpty(airlineNumber)) {
            airLineInfoDTO.setSitaNumber(new Long(airlineNumber));
            standbyPriorityQueryDTO.setHandledAirline(airLineInfoDTO);
        }
        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(subscriberCode);
        
        standbyPriorityQueryDTO.setSubscriberReference(subscriberIDType);
        findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);
        StandByPriortyDetailsHelper.LOGGER.debug("Exit DefaultStandbyPriorityScreenHelper.populateFindSPRequestDTO");

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
    private void renderSPTable(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper::renderSPTable");
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT, 0);
        }
        StandbyPrioritiesType standbyPrioritiesDTO = null;
        int totalRecords = 0;
        // int rowCount = 0; // variable to act as a counter for a row.

        if (findStandbyPriorityResponseDTO != null) {
            if (findStandbyPriorityResponseDTO.getStandbyPriorityList() != null
                && findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities() != null) {

                standbyPrioritiesDTO = findStandbyPriorityResponseDTO.getStandbyPriorityList()
                    .getStandbyPriorities();

                if (findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                    .getStandbyPriorityList() != null) {
                    totalRecords = findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                        .getStandbyPriorityList().size();

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT,
                        totalRecords);

                }
            }

            if (totalRecords > StandbyPriorityConstants.NO_OF_ROWS) {
                refreshSPTable();
                initalizeSPTableCells(totalRecords);
            }
            else {
                refreshSPTable();
                initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
            }

            if (standbyPrioritiesDTO != null && controller.getNewSPFilterTablePanel() != null
                && controller.getNewSPFilterTablePanel().getModel() != null
                && standbyPrioritiesDTO.getStandbyPriorityList() != null
                && standbyPrioritiesDTO.getStandbyPriorityList().size() > 0) {
                renderData(findStandbyPriorityResponseDTO);
            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper::renderSPTable");
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
    private void renderSPTableOnCopyStandBy(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT, 0);
        }

        StandbyPrioritiesType standbyPrioritiesDTO = null;
        int totalRecords = 0;
        // int rowCount = 0; // variable to act as a counter for a row.

        if (findStandbyPriorityResponseDTO != null) {
            if (findStandbyPriorityResponseDTO.getStandbyPriorityList() != null
                && findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities() != null) {

                standbyPrioritiesDTO = findStandbyPriorityResponseDTO.getStandbyPriorityList()
                    .getStandbyPriorities();

                if (findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                    .getStandbyPriorityList() != null) {
                    totalRecords = findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                        .getStandbyPriorityList().size();

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT,
                        totalRecords);

                }
            }

            if (totalRecords > StandbyPriorityConstants.NO_OF_ROWS) {
                refreshSPTable();
                initalizeSPTableCells(totalRecords);
            }
            else {
                refreshSPTable();
                initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
            }

            if (standbyPrioritiesDTO != null && controller.getNewSPFilterTablePanel() != null
                && controller.getNewSPFilterTablePanel().getModel() != null
                && standbyPrioritiesDTO.getStandbyPriorityList() != null
                && standbyPrioritiesDTO.getStandbyPriorityList().size() > 0) {
                renderData(findStandbyPriorityResponseDTO);

            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper::renderSPTable");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method will render the Standby Priority Information.
         * 
         * @param standbyPriorityDTO , not null.
         * @param newTableModel , not null.
         * @param rowCount , not null.
         * </pre>
     */
    /*private void renderSPInfoOnCopyStandBy(final StandbyPriorityType standbyPriorityDTO,
        final DefaultTableModel newTableModel, final int rowCount) {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper.renderSPInfo");
        String priorityDesc;
        String dateOfJoiningTemp;

        if (standbyPriorityDTO != null && standbyPriorityDTO.getInfo() != null) {
            if (standbyPriorityDTO.getInfo().getPriorityStatusCode() != null) {
                newTableModel.setValueAt(standbyPriorityDTO.getInfo().getPriorityStatusCode(), rowCount,
                    COLUMN_VALUE_TWO);

            }

            if (standbyPriorityDTO.getInfo().getPriorityStatusDescription() != null) {
                priorityDesc = standbyPriorityDTO.getInfo().getPriorityStatusDescription();
                newTableModel.setValueAt(priorityDesc, rowCount, COLUMN_VALUE_THREE);
            }

            if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired() != null) {
                if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired().toString()
                    .equalsIgnoreCase(StandbyPriorityConstants.DOJ_OPTIONAL)) {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_OPTIONAL;
                }
                else {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_NOT_REQUIRED;
                }

                if (dateOfJoiningTemp != null) {
                    newTableModel.setValueAt(dateOfJoiningTemp, rowCount, COLUMN_VALUE_FIVE);
                }
            }
            newTableModel.setValueAt(booleanToString(standbyPriorityDTO.getInfo().isCanBeDisembarked()), rowCount,
                COLUMN_VALUE_FOUR);

        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper.renderSPInfo");
    }
*/
    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to Convert boolean value to 'Yes' and 'No'.
         * 
         * @param booleanValue , not null.
         * @return String , never null.
         * </pre>
     */
    /*private String booleanToString(final boolean booleanValue) {
        if (booleanValue) {
            return StandbyPriorityConstants.YES_VALUE;
        }

        return StandbyPriorityConstants.NO_VALUE;
    }*/

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
     * 
     * <pre>
         * <b>Description : </b>
         * update Search Screen.
         * 
         * </pre>
     */
    public void updateSearchScreen() {
        DcsTabDataContainer.getInstance().showTab(StandbyPriorityConstants.MANAGE_MODULE_NAME);
        ModuleDO moduleDO = ScreenRegistry.getInstance().getCurrentModule();
        UIBaseController controller = (UIBaseController) moduleDO.getController();
        controller.refreshData();
    }

    /**
     * Method to fetch Standby Priority Table Data to VO.
     * 
     * <pre>
         * <b>Description : </b>
         * Method to fetch Standby Priority Table Data to VO.
         * 
         * @return spVOList, never null.
         * </pre>
     */
    public List<StandbyPriorityVO> fetchSPTableData() {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper.fetchSPTableData");

        List<StandbyPriorityVO> spVOList = new ArrayList<StandbyPriorityVO>();

        if (controller.getNewSPFilterTablePanel() != null && controller.getNewSPFilterTablePanel().getModel() != null) {
            if (controller.getNewSPFilterTablePanel().getTable().getCellEditor() != null) {
                controller.getNewSPFilterTablePanel().getTable().getCellEditor().stopCellEditing();
            }
            final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();

            for (int i = 0; i < dm.getRowCount(); i++) {
                StandbyPriorityVO standbyPriorityVO = new StandbyPriorityVO();

                if (dm.getValueAt(i, COLUMN_VALUE_TWO) != null
                    && !("").equals(dm.getValueAt(i, COLUMN_VALUE_TWO).toString().trim())
                    && dm.getValueAt(i, COLUMN_VALUE_THREE) != null
                    && !("").equals(dm.getValueAt(i, COLUMN_VALUE_THREE).toString().trim())) {

                    standbyPriorityVO.setDefault(false);
                    standbyPriorityVO.setSubscriberCode(controller.getNewSPSubscriberLOV().getSearchField().getText());
                    standbyPriorityVO.setAirlineCode(controller.getNewSPAirlineLOV().getSearchField().getText());

                    standbyPriorityVO.setPriorityStatusCode(dm.getValueAt(i, COLUMN_VALUE_TWO).toString().trim());
                    standbyPriorityVO.setPriorityStatusDesc(dm.getValueAt(i, COLUMN_VALUE_THREE).toString().trim());
                    standbyPriorityVO.setCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_FOUR).toString());
                    standbyPriorityVO.setDateOfJoining(dm.getValueAt(i, COLUMN_VALUE_FIVE).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_SIX) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_SIX).toString().trim())) {
                        standbyPriorityVO.setBookedPriorityValue(Integer.parseInt(dm.getValueAt(i, COLUMN_VALUE_SIX)
                            .toString().trim()));
                    }

                    standbyPriorityVO.setBookedCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_SEVEN).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_EIGHT) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_EIGHT).toString().trim())) {
                        standbyPriorityVO.setNoRecPriorityValue(Integer.parseInt(dm.getValueAt(i, COLUMN_VALUE_EIGHT)
                            .toString().trim()));
                    }

                    standbyPriorityVO.setNoRecCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_NINE).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_TEN) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_TEN).toString().trim())) {
                        standbyPriorityVO.setWaitlistPriorityValue(Integer.parseInt(dm.getValueAt(i, COLUMN_VALUE_TEN)
                            .toString().trim()));
                    }

                    standbyPriorityVO.setWaitlistCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_ELEVEN).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_TWELVE) != null
                        && !("").equals(dm.getValueAt(i, COLUMN_VALUE_TWELVE).toString().trim())) {
                        standbyPriorityVO.setOpenTicketPriorityValue(Integer.parseInt(dm
                            .getValueAt(i, COLUMN_VALUE_TWELVE).toString().trim()));
                    }

                    standbyPriorityVO.setOpenTicketCanBeDisembarked(dm.getValueAt(i, COLUMN_VALUE_THIRTEEN).toString());
                    standbyPriorityVO.setHoldsSpaceAvailableTicket(dm.getValueAt(i, COLUMN_VALUE_FOURTEEN).toString());

                    if (dm.getValueAt(i, COLUMN_VALUE_FIFTEEN) != null) {
                        standbyPriorityVO.setDocumentId(dm.getValueAt(i, COLUMN_VALUE_FIFTEEN).toString());
                    }

                    standbyPriorityVO.setRowNumber(i);

                    spVOList.add(standbyPriorityVO);
                }
            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper.fetchSPTableData");

        return spVOList;
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to load the screen.
         * 
         * </pre>
     */
    private void onScreenLoad() {
        setComponentBorder();
        fillSPNoOfRowsCombobox();
        setColumnWidth();
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to copy the default standby priority data to the New Standby Priority tab.
         * 
         * </pre>
     */
    public void copyDefault() {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper:copyDefault");

        onScreenLoad();
        if (NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_DEFAULT) != null) {

            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT).toString()
                .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {

                // final String tabName =
                // TabDataContainer.getInstance().getCurrentTabName();
                final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                if (findStandbyPriorityResponseDTO != null) {
                    renderDefaultStandbyPriority(findStandbyPriorityResponseDTO);

                }
            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper:copyDefault");
    }

    /**
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Default Standby priority data on page load.
         *
         * @param findStandbyPriorityResponseDTO , not null.
         * </pre>
     */
    public void renderDefaultStandbyPriority(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {
        StandByPriortyDetailsHelper.LOGGER
            .debug("Entered StandbyPriorityDetailsScreenHelper:renderDefaultStandbyPriority");

        try {

            if (findStandbyPriorityResponseDTO != null) {
                controller.getNewSPCreateButton().setVisible(true);
                controller.getNewSPCreateButton().setEnabled(true);
                controller.getNewSPUpdateButton().setVisible(false);
                controller.getNewSPUpdateButton().setEnabled(true);
                controller.getNewSPCopyButton().setEnabled(false);

                controller.getNewSPAirlineLOV().getSearchField().setText("");
                controller.getNewSPSubscriberLOV().getSearchField().setText("");

                renderSPTableOnCopyDefault(findStandbyPriorityResponseDTO);
            }
            // }
        }
        catch (Exception ex) {
            StandByPriortyDetailsHelper.LOGGER.debug("Error : " + ex.getMessage());
        }

        StandByPriortyDetailsHelper.LOGGER
            .debug("Exited StandbyPriorityDetailsScreenHelper:renderDefaultStandbyPriority");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This method is to populate the Find StandbyPriority DTO to Screen while copying from Default.
         * 
         * @param findStandbyPriorityResponseDTO , not null.
         * </pre>
     */
    private void renderSPTableOnCopyDefault(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {
        StandByPriortyDetailsHelper.LOGGER
            .debug("Entered StandbyPriorityDetailsScreenHelper::renderSPTableOnCopyDefault");

        StandbyPrioritiesType standbyPrioritiesDTO = null;
        int totalRecords = 0;
        // int rowCount = 0; // variable to act as a counter for a row.

        if (findStandbyPriorityResponseDTO != null) {
            if (findStandbyPriorityResponseDTO.getStandbyPriorityList() != null
                && findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities() != null) {

                standbyPrioritiesDTO = findStandbyPriorityResponseDTO.getStandbyPriorityList()
                    .getStandbyPriorities();

                if (findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                    .getStandbyPriorityList() != null) {
                    totalRecords = findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                        .getStandbyPriorityList().size();

                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT,
                        totalRecords);
                }
            }

            if (totalRecords > StandbyPriorityConstants.NO_OF_ROWS) {
                refreshSPTable();
                initalizeSPTableCells(totalRecords);
            }
            else {
                refreshSPTable();
                initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
            }

            if (standbyPrioritiesDTO != null && controller.getNewSPFilterTablePanel() != null
                && controller.getNewSPFilterTablePanel().getModel() != null
                && standbyPrioritiesDTO.getStandbyPriorityList() != null
                && standbyPrioritiesDTO.getStandbyPriorityList().size() > 0) {
                renderData(findStandbyPriorityResponseDTO);
            }
        }

        StandByPriortyDetailsHelper.LOGGER
            .debug("Exited StandbyPriorityDetailsScreenHelper::renderSPTableOnCopyDefault");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method will render the Standby Priority Information while copying from Default.
         * 
         * @param standbyPriorityDTO , not null.
         * @param newTableModel , not null.
         * @param rowCount , not null.
         * </pre>
     */
    /*private void renderSPInfoOnCopyDefault(final StandbyPriorityType standbyPriorityDTO,
        final DefaultTableModel newTableModel, final int rowCount) {
        StandByPriortyDetailsHelper.LOGGER
            .debug("Entered StandbyPriorityDetailsScreenHelper.renderSPInfoOnCopyDefault");
        String priorityDesc;
        String dateOfJoiningTemp;

        if (standbyPriorityDTO != null && standbyPriorityDTO.getInfo() != null) {
            if (standbyPriorityDTO.getInfo().getPriorityStatusCode() != null) {
                newTableModel.setValueAt(standbyPriorityDTO.getInfo().getPriorityStatusCode(), rowCount,
                    COLUMN_VALUE_TWO);
            }

            if (standbyPriorityDTO.getInfo().getPriorityStatusDescription() != null) {
                priorityDesc = standbyPriorityDTO.getInfo().getPriorityStatusDescription();
                newTableModel.setValueAt(priorityDesc, rowCount, COLUMN_VALUE_THREE);
            }

            if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired() != null) {

                if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired().toString()
                    .equalsIgnoreCase(StandbyPriorityConstants.DOJ_OPTIONAL)) {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_OPTIONAL;
                }
                else {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_NOT_REQUIRED;
                }

                if (dateOfJoiningTemp != null) {
                    newTableModel.setValueAt(dateOfJoiningTemp, rowCount, COLUMN_VALUE_FIVE);
                }
            }

            newTableModel.setValueAt(booleanToString(standbyPriorityDTO.getInfo().isCanBeDisembarked()), rowCount,
                COLUMN_VALUE_FOUR);

        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper.renderSPInfoOnCopyDefault");
    }
*/
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
        StandByPriortyDetailsHelper.LOGGER.debug("Enter StandbyPriorityDetailsScreenHelper.getTabDTO");

        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO 
            = (FindStandbyPriorityResponse) DcsTabDataContainer
            .getInstance().getDTOForTab(tabName);

        StandByPriortyDetailsHelper.LOGGER.debug("Exit StandbyPriorityDetailsScreenHelper.getTabDTO");

        return findStandbyPriorityResponseDTO;
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to copy the default standby priority data to the New Standby Priority tab.
         * 
         * </pre>
     */
    public void copyStandBy() {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper:copyDefault");

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY, null);
        }
        onScreenLoad();
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY).toString()
                .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {

                // final String tabName =
                // TabDataContainer.getInstance().getCurrentTabName();
                final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                if (findStandbyPriorityResponseDTO != null) {
                    renderStandbyPriority(findStandbyPriorityResponseDTO);

                }
            }
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }
        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper:copyDefault");
    }

    /**
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Default Standby priority data on page load.
         *
         * @param findStandbyPriorityResponseDTO , not null.
         * </pre>
     */
    public void renderStandbyPriority(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {
        StandByPriortyDetailsHelper.LOGGER
            .debug("Entered StandbyPriorityDetailsScreenHelper:renderDefaultStandbyPriority");

        try {

            if (findStandbyPriorityResponseDTO != null) {
                controller.getNewSPCreateButton().setVisible(true);
                controller.getNewSPCreateButton().setEnabled(true);
                controller.getNewSPUpdateButton().setVisible(false);
                controller.getNewSPUpdateButton().setEnabled(false);
                controller.getNewSPCopyButton().setEnabled(false);

                renderSPTableOnCopyStandBy(findStandbyPriorityResponseDTO);
            }
            // }
        }
        catch (Exception ex) {
            StandByPriortyDetailsHelper.LOGGER.debug("Error : " + ex.getMessage());
        }

        StandByPriortyDetailsHelper.LOGGER
            .debug("Exited StandbyPriorityDetailsScreenHelper:renderDefaultStandbyPriority");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to copy the default standby priority data to the New Standby Priority tab.
         * 
         * </pre>
     */
    public void copySearch() {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper:copyDefault");

        // String copySearch = null;
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY, null);
        }

        onScreenLoad();
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {
            // copySearch =
            // NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH).toString();

            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH).toString()
                .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {
                    NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH, null);
                }
                // final String tabName =
                // TabDataContainer.getInstance().getCurrentTabName();
                final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                if (findStandbyPriorityResponseDTO != null) {
                    renderDefaultStandbyPriority(findStandbyPriorityResponseDTO);
                }
            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper:copyDefault");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This is to open the Standby.
         * 
         * </pre>
     */
    public void openStandBy() {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper:Open Standby");

        onScreenLoad();
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH, null);
        }

        if (NGAFContextUtil.getApplicationContext()
            .get(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB, null);
        }
        if (NGAFContextUtil.getApplicationContext()
            .get(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB,
                null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.OPEN_SEARCH) != null) {
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.OPEN_SEARCH).toString()
                .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {

                final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(NGAFContextUtil
                    .getApplicationContext().get("CURRENTTAB").toString());

                String airlineCode = NGAFContextUtil.getApplicationContext().get("CURRENT_AIRLINE_CODE").toString();

                if (findStandbyPriorityResponseDTO != null) {
                    if (NGAFContextUtil.getApplicationContext().get("CURRENTTAB").toString()
                        .equalsIgnoreCase(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB)) {

                        renderLovs(false, airlineCode, callTime);
                        openSearchButtonEnabler(false);
                    }
                    else {
                        renderLovs(true, airlineCode, callTime++);
                        renderOpenSearchedAirline(findStandbyPriorityResponseDTO);
                        openSearchButtonEnabler(true);

                    }

                }

            }

        }
        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper:Open Standby");

    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This method is to render the LOV's of the Searched Result Opened..
         * 
         * @param airlineCodeItem , not null.
         * @param callTimes , not null.
         * @param enabler , not null.
         * </pre>
     */
    private void renderLovs(final Boolean enabler, final String airlineCodeItem, final int callTimes) {
        if (enabler) {
            if (airlineCodeItem != null) {
                if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SUBSCRIBER) != null) {
                    String subscriberName = NGAFContextUtil.getApplicationContext()
                        .get(StandbyPriorityConstants.SUBSCRIBER).toString();

                    controller.getNewSPSubscriberLOV().getSearchField().setText(subscriberName);
                    controller.getNewSPSubscriberLOV().getSearchField().setEnabled(false);
                    controller.getNewSPSubscriberLOV().getSearchButton().setEnabled(false);
                    controller.getNewSPAirlineLOV().getSearchField().setText(getAirlineCode(callTimes));
                    controller.getNewSPAirlineLOV().getSearchField().setEnabled(false);
                    controller.getNewSPAirlineLOV().getSearchButton().setEnabled(false);
                }
            }
        }
        else {
            controller.getNewSPSubscriberLOV().getSearchField().setEnabled(true);
            controller.getNewSPSubscriberLOV().getSearchButton().setEnabled(true);
            controller.getNewSPAirlineLOV().getSearchField().setEnabled(true);
            controller.getNewSPAirlineLOV().getSearchButton().setEnabled(true);
        }

    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This method is used to get the Airline Code  from the App-context.
         * 
         * @param callTimes , not null.
         * @return String , never null.
         * </pre>
     */
    private String getAirlineCode(final int callTimes) {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SELECTED_LIST_TO_OPEN) != null) {
            // List<String> contentList = new ArrayList<String>();
            List<String> contentList = (ArrayList) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.SELECTED_LIST_TO_OPEN);
            if (contentList.size() > 0) {
                String item = contentList.get(callTimes);
                String[] splitedValue = item.split("-");

                if (callTime == contentList.size()) {
                    callTime = 0;
                }
                return splitedValue[0];

            }
        }
        return null;
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This method is to enable the Button of the Searched Result.
         * 
         * @param enabler , not null.
         * </pre>
     */
    private void openSearchButtonEnabler(final Boolean enabler) {
        if (enabler) {
            controller.getNewSPDeleteButton().setEnabled(true);
            controller.getNewSPUpdateButton().setEnabled(true);
            controller.getNewSPUpdateButton().setVisible(true);
            controller.getNewSPCreateButton().setVisible(false);
            controller.getNewSPCreateButton().setEnabled(false);
            controller.getNewSPCopyButton().setEnabled(true);
        }
        else {
            controller.getNewSPDeleteButton().setEnabled(false);
            controller.getNewSPCopyButton().setEnabled(false);
            controller.getNewSPUpdateButton().setEnabled(false);
            controller.getNewSPUpdateButton().setVisible(false);
            controller.getNewSPCreateButton().setVisible(true);
            controller.getNewSPCreateButton().setEnabled(true);

        }

    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * Render Open Searched Response DTO.
         * 
         * @param findStandbyPriorityResponseDTO , not null.
         * </pre>
     */
    private void renderOpenSearchedAirline(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {

        StandByPriortyDetailsHelper.LOGGER
            .debug("Entered StandbyPriorityDetailsScreenHelper::renderOpenSearchedAirline");

        // StandbyPrioritiesType standbyPrioritiesDTO = null;
        // int rowCount = 0;
        // standbyPrioritiesDTO =
        // findStandbyPriorityResponseDTO.getStandbyPriorityListDTO().getStandbyPriorities();
        final int totalRecords = findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
            .getStandbyPriorityList().size();
        if (totalRecords > StandbyPriorityConstants.NO_OF_ROWS) {
            refreshSPTable();
            initalizeSPTableCells(totalRecords);
        }
        else {
            refreshSPTable();
            initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
        }
        if (findStandbyPriorityResponseDTO.getStandbyPriorityList()
                .getStandbyPriorities().getStandbyPriorityList() != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_RESPONSE_ROWCOUNT, totalRecords);

        }
        renderData(findStandbyPriorityResponseDTO);

        StandByPriortyDetailsHelper.LOGGER
            .debug("Exited StandbyPriorityDetailsScreenHelper::renderOpenSearchedAirline");

    }

    /**
     * <pre>
         * <b>Description : </b>
         * This Method is to save the Standby priority data on page load.
         *
         * </pre>
     */
    public void copyDefaultSaveConfirm() {
        DcsMessagePanelHandler.clearAllMessages();
        boolean isValid = true;
        // if(NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT))
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
            if (!getFindResult(controller.getNewSPSubscriberLOV().getSearchField().getText().toUpperCase(), controller
                .getNewSPAirlineLOV().getSearchField().getText().toUpperCase())) {
                createNewStandByPriority(fetchSPTableData());
            }
            else {
                DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10888");
            }

        }
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
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CreateStandbyPriorityListener.createStandByPriority");
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
                            StandByPriortyDetailsHelper.LOGGER
                                .debug("CreateStandbyPriorityListener.confirmStandbyPriorityDTO");
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
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exit CreateStandbyPriorityListener.createStandByPriority");
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
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPRequestDTO");
        final CreateStandbyPriority createStandbyPriorityDTO = new CreateStandbyPriority();
        final StandbyPriorityType standbyPriorityDTO = new StandbyPriorityType();

        populateCreateSPDocumentDTO(standbyPriorityDTO);
        populateCreateSPInfoDTO(standbyPriorityDTO, standbyPriorityVO);
        populateCreateSPAirlineInfo(standbyPriorityDTO, standbyPriorityVO);
        populateCreateSPBookingStatusDTO(standbyPriorityDTO, standbyPriorityVO);

        createStandbyPriorityDTO.setStandbyPriority(standbyPriorityDTO);
        StandByPriortyDetailsHelper.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPRequestDTO");

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
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPDocumentDTO");

        final DocumentHeaderType documentHeaderDTO = new DocumentHeaderType();
        final AdministrativeRecordType administrativeRecordDTO = new AdministrativeRecordType();
        String subscriber = controller.getNewSPSubscriberLOV().getSearchField().getText();

        SubscriberIDType subscriberIDType=new SubscriberIDType();
        subscriberIDType.setString(subscriber);
        administrativeRecordDTO.setSubscriberReference(subscriberIDType);
        documentHeaderDTO.setAdministrativeRecord(administrativeRecordDTO);

        final SchemaVersionAttrGroup schemaVersionAttrGroupParam = new SchemaVersionAttrGroup();
        schemaVersionAttrGroupParam.setLanguageCode(LanguageCodeType.EN);
        schemaVersionAttrGroupParam.setSchemaVersion("1.0");
        schemaVersionAttrGroupParam.setActionCode(ActionCodeType.NEW);

        standbyPriorityDTO.setDocumentHeader(documentHeaderDTO);
        standbyPriorityDTO.setIsDefault(false);
        standbyPriorityDTO.setSchemaVersionAttrGroup(schemaVersionAttrGroupParam);

        StandByPriortyDetailsHelper.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPDocumentDTO");
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
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPInfoDTO");

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

        StandByPriortyDetailsHelper.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPInfoDTO");
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
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CreateStandbyPriorityListener.populateCreateSPAirlineInfo");
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

        StandByPriortyDetailsHelper.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPAirlineInfo");
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
        StandByPriortyDetailsHelper.LOGGER
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

        StandByPriortyDetailsHelper.LOGGER.debug("Exit CreateStandbyPriorityListener.populateCreateSPBookingStatusDTO");
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
        String subscriberCode = controller.getNewSPSubscriberLOV().getSearchField().getText();
        String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText();

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT, null);
        }

        // if
        // (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE)
        // != null) {
        // NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE,
        // null);
        // }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITY_RESPONSE) 
                != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.DEFAULT_STANDBY_PRIORITY_RESPONSE,
                null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_SAVE, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_SAVE, null);
        }

        if (failed == 0 && success > 0) {

            String airlineCodeLOV = controller.getNewSPAirlineLOV().getSearchField().getText();

            JTabbedPane pane = ((JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent()));
            final int index = ((JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent())).getSelectedIndex();
            String airlineName = controller.getCommonUtilitiesGateWay().getAirlineName(airlineCodeLOV);

            pane.setTitleAt(index, subscriberCode.trim() + "-" + airlineName.trim());
            // pane.setTitleAt(index, airlineCode.trim());

            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE, subscriberCode);

            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, airlineCode);
            // controller.getStandbyPriorityDetailsScreenHelper().updateScreenUI();
            updateScreenUI();
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
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
                copyDefaultStandByPriorityInNewTab();
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
            }
            else {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
                copyStandByPriorityInNewTab(subscriberCode, airlineCode);
            }
            DcsMessagePanelHandler.handleSuccessMessage(success + " Success ");
            DcsMessagePanelHandler.handleSuccessMessage("CKI-10831");
        }
        else if (failed > 0 && success > 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(success + " Success " + failed + " Failed");
            String airlineCodeLOV = controller.getNewSPAirlineLOV().getSearchField().getText();

            JTabbedPane pane = (JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent());
            final int index = ((JTabbedPane) (NGAFTabbedPaneHelper.getCardTopComponent())).getSelectedIndex();
            String airlineName = controller.getCommonUtilitiesGateWay().getAirlineName(airlineCodeLOV);

            pane.setTitleAt(index, subscriberCode.trim() + "-" + airlineName.trim());

            // pane.setTitleAt(index, airlineCode.trim());

            /*
             * controller.getNewSPUpdateButton().setVisible(true);
             * controller.getNewSPUpdateButton().setEnabled(false);
             * controller.getNewSPCreateButton().setVisible(false);
             */
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

        }
        else if (failed > 0 && success == 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg(failed + " Failed");

            controller.getNewSPUpdateButton().setVisible(false);
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
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CreatetStandbyPriorityListener.highlightFailedRows");

        try {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.CREATE_SP_FAILED_ROWS, failedRowsList);

            for (int i = 0; i < failedRowsList.size(); i++) {
                if (controller.getNewSPFilterTablePanel() != null
                    && controller.getNewSPFilterTablePanel().getModel() != null) {

                    final DefaultTableModel dm = (DefaultTableModel) controller.getNewSPFilterTablePanel().getModel();

                    if (controller.getNewSPFilterTablePanel().getTable() != null) {
                        NGAFStandardTable spStandardTable = (NGAFStandardTable) controller.getNewSPFilterTablePanel()
                            .getTable();

                        for (StandbyPriorityVO standbyPriorityVO : spVOList) {
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

                                if (standbyPriorityVO.getPriorityStatusCode() != null
                                    && standbyPriorityVO.getDocumentId() == null && docId == null
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

        StandByPriortyDetailsHelper.LOGGER.debug("Exit CreatetStandbyPriorityListener.highlightFailedRows");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * Method to open new tabs with the Default Standby Priority.
         * 
         * @param subscriberCode , not null.
         * @param airlineCode , not null.
         * </pre>
     */
    public void copyStandByPriorityInNewTab(final String subscriberCode, final String airlineCode) {
        String airlineNumber = null;
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE, null);
        }
        if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
            airlineNumber = populateAirlineNumber(airlineCode, true);
        }

        if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
            airlineNumber = populateAirlineNumber(airlineCode, false);
        }
        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getStandbyPriorityWithAirline(
            subscriberCode, airlineNumber);
        if (findStandbyPriorityResponseDTO != null) {
            createNewTabs(findStandbyPriorityResponseDTO, StandbyPriorityConstants.MODULE_ID,
                StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
        }
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB,
            StandbyPriorityConstants.YES_VALUE);
    }

    /**
     * <pre>
         * <b>Description : </b>
         * Method to open new tabs with the Default Standby Priority.
         * 
         * </pre>
     */
    public void copyDefaultStandByPriorityInNewTab() {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE, null);
        }

        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getDefaultStandbyPriority();
        if (findStandbyPriorityResponseDTO != null) {
            createNewTabs(findStandbyPriorityResponseDTO, StandbyPriorityConstants.MODULE_ID,
                StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB);
        }
        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB,
            StandbyPriorityConstants.YES_VALUE);
    }

    /**
     * <pre>
         * <b>Description : </b>
         * Method to open new tabs for the Standby Priority.
         * 
         * @param findStandbyPriorityResponseDTO is, not null
         * @param moduleId is, not null
         * @param tabTitle is, not null
         * </pre>
     */
    private void createNewTabs(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO,
        final String moduleId, final String tabTitle) {
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CopyDefaultStandbyPriorityListener.createNewTabs");
        if (tabTitle != null) {
            DcsTabDataContainer.getInstance().addTabAndDTO(tabTitle, findStandbyPriorityResponseDTO);
            DcsViewHandler.invokeNewTab(moduleId, tabTitle);
        }
        StandByPriortyDetailsHelper.LOGGER.debug("Exit CopyDefaultStandbyPriorityListener.createNewTabs");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Default Standby priority data.
         * @param subscriberCode , not null.
         * @param airlineNumber , not null.
         * @return FindStandbyPriorityResponseType , never null.
         * </pre>
     */
    private FindStandbyPriorityResponse getStandbyPriorityWithAirline(final String subscriberCode,
        final String airlineNumber) {
        StandByPriortyDetailsHelper.LOGGER.debug("Enter StandbyPriorityDetailsScreenHelper.getStandbyPriority");
        try {
            final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = populateFindSPRequestDTO(
                subscriberCode, airlineNumber);

            if (controller.getManageStandbyPriorityProxy() != null) {
                try {
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = controller
                        .getManageStandbyPriorityProxy().findStandbyPriority(findStandbyPriorityRequestDTO);
                    return findStandbyPriorityResponseDTO;
                }
                catch (CheckInException ex) {
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                }
            }
        }
        catch (Exception ex) {
            StandByPriortyDetailsHelper.LOGGER.debug("Error : " + ex.getMessage());
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exit StandbyPriorityDetailsScreenHelper.getDefaultStandbyPriority");

        return null;
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to copy the default standby priority data to the New Standby Priority tab.
         * 
         * </pre>
     */
    public void copyDefaultConfirmNewTab() {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper:copyDefault");
        onScreenLoad();
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB) 
                != null) {

            if (NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB).toString()
                .equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {

                // final String tabName =
                // TabDataContainer.getInstance().getCurrentTabName();
                final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                if (findStandbyPriorityResponseDTO != null) {
                    renderDefaultStandbyPriority(findStandbyPriorityResponseDTO);

                }
            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper:copyDefault");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to copy the default standby priority data to the New Standby Priority tab.
         * 
         * </pre>
     */
    public void copyStandByConfirmNewTab() {
        StandByPriortyDetailsHelper.LOGGER.debug("Entered StandbyPriorityDetailsScreenHelper:copyDefault");
        onScreenLoad();
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB) 
                != null) {

            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB)
                .toString().equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {

                // final String tabName =
                // TabDataContainer.getInstance().getCurrentTabName();
                final String tabName = StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB;
                final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getTabDTO(tabName);

                if (findStandbyPriorityResponseDTO != null) {
                    renderStandbyPriority(findStandbyPriorityResponseDTO);

                }
            }
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exited StandbyPriorityDetailsScreenHelper:copyDefault");
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Default Standby priority data.
         * 
         * </pre>
     */
    public void getDefaultStandByPriorityInDontSave() {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE, null);
        }
        final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getDefaultStandbyPriority();
        if (findStandbyPriorityResponseDTO != null) {
            renderDefaultStandbyPriority(findStandbyPriorityResponseDTO);
        }
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Default Standby priority data.
         * 
         * @return FindStandbyPriorityResponseType , never null.
         * </pre>
     */
    private FindStandbyPriorityResponse getDefaultStandbyPriority() {
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

        try {
            final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = populateFindSPRequestDTO();

            if (controller.getManageStandbyPriorityProxy() != null) {
                try {
                    final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = controller
                        .getManageStandbyPriorityProxy().findStandbyPriority(findStandbyPriorityRequestDTO);
                    return findStandbyPriorityResponseDTO;
                }
                catch (CheckInException ex) {
                    DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                }
            }
        }
        catch (Exception ex) {
            StandByPriortyDetailsHelper.LOGGER.debug("Error : " + ex.getMessage());
        }

        StandByPriortyDetailsHelper.LOGGER.debug("Exit CopyDefaultStandbyPriorityListener.getDefaultStandbyPriority");

        return null;
    }

    /**
     * <pre>
         * <b>Description : </b>
         * Method to populate request for Find Standby Priority service to get default standby priority.
         * 
         * @return findStandbyPriorityRequestDTO , never null.
         * </pre>
     */
    private FindStandbyPriorityRequest populateFindSPRequestDTO() {
        StandByPriortyDetailsHelper.LOGGER.debug("Enter CopyDefaultStandbyPriorityListener.populateFindSPRequestDTO");

        final FindStandbyPriorityRequest findStandbyPriorityRequestDTO = new FindStandbyPriorityRequest();
        final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();

        findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);
        StandByPriortyDetailsHelper.LOGGER.debug("Exit CopyDefaultStandbyPriorityListener.populateFindSPRequestDTO");

        return findStandbyPriorityRequestDTO;
    }

    /**
     * Method to load the New Standby Priority Screen.
     * 
     * <pre>
         * <b>Description : </b>
         * Method to load the New Standby Priority Screen.
         * 
         * </pre>
     */
    public final void searchCopyDoNotSave() {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_SEARCH, null);
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY, null);
        }
        // refreshSPTable();
        // initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
        refreshSearchCopyDoNotSaveScreen();
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to refresh the Standby Priority tab.
         * 
         * </pre>
     */
    private void refreshSearchCopyDoNotSaveScreen() {
        String subscriberCode = null;
        String airlineNumber = null;

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SEARCH_SUBSCRIBER) != null) {
            subscriberCode = (String) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.SEARCH_SUBSCRIBER);
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SEARCH_AIRLINE_NUMBER) != null) {
            airlineNumber = (String) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.SEARCH_AIRLINE_NUMBER);
        }
        controller.getNewSPAirlineLOV().getSearchField().setText("");
        controller.getNewSPSubscriberLOV().getSearchField().setText("");
        if (subscriberCode != null && airlineNumber != null) {
            FindStandbyPriorityResponse findStandbyPriorityResponseDTO = getStandbyPriority(subscriberCode,
                airlineNumber);
            if (findStandbyPriorityResponseDTO != null) {
                renderSPTableOnCopyStandBy(findStandbyPriorityResponseDTO);
            }
        }

    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * <<WRITE DESCRIPTION HERE>>
         * This method is to set the default blank twenty rows. 
         * </pre>
     */
    public void resetBlankRows() {
        refreshSPTable();
        initalizeSPTableCells(StandbyPriorityConstants.NO_OF_ROWS);
    }

    /**
     * 
     * <pre>
         * <b>Description : </b>
         * This Method is to retrieve the Airlines having the Standby Priority data.
         * 
         * @param subscriberName , not null.
         * @param airlineName , not null.
         * @return boolean , never null.
         * </pre>
     */
    public boolean getFindResult(final String subscriberName, final String airlineName) {
        List<Object> standbyPriorityResponseList = new ArrayList<Object>();
        try {
            if (airlineName != null) {

                if (controller.getManageStandbyPriorityProxy() != null) {
                    String airlineNumber = null;
                    try {
                        final FindStandbyPriorityRequest findStandbyPriorityRequestDTO 
                            = new FindStandbyPriorityRequest();
                        final StandbyPriorityQueryType standbyPriorityQueryDTO = new StandbyPriorityQueryType();
                        final AirlineInfoType airLineInfoDTO = new AirlineInfoType();

                        if (airlineName.trim().length() == AIRLINECODE_LENGTH_TWO) {
                            airLineInfoDTO.setIataCode(airlineName.toUpperCase());
                            airlineNumber = populateAirlineNumber(airlineName, true);
                        }

                        if (airlineName.trim().length() == AIRLINECODE_LENGTH_THREE) {
                            airLineInfoDTO.setIcaoCode(airlineName.toUpperCase());
                            airlineNumber = populateAirlineNumber(airlineName, false);
                        }

                        if (isNotEmpty(airlineNumber)) {
                            airLineInfoDTO.setSitaNumber(new Long(airlineNumber));
                            standbyPriorityQueryDTO.setHandledAirline(airLineInfoDTO);
                            SubscriberIDType subscriberIDType=new SubscriberIDType();
                            subscriberIDType.setString(subscriberName.trim().toUpperCase());
                           
                            standbyPriorityQueryDTO.setSubscriberReference(subscriberIDType);
                            findStandbyPriorityRequestDTO.setStandbyPriorityQuery(standbyPriorityQueryDTO);

                            final FindStandbyPriorityResponse findStandbyPriorityResponseDTO = controller
                                .getManageStandbyPriorityProxy().findStandbyPriority(findStandbyPriorityRequestDTO);

                            if (findStandbyPriorityResponseDTO != null
                                && findStandbyPriorityResponseDTO.getStandbyPriorityList() != null
                                && findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities() 
                                    != null
                                && findStandbyPriorityResponseDTO.getStandbyPriorityList().getStandbyPriorities()
                                    .getStandbyPriorityList() != null) {

                                List<StandbyPriorityType> standbyPriorityCustomList = findStandbyPriorityResponseDTO
                                    .getStandbyPriorityList().getStandbyPriorities().getStandbyPriorityList();

                                final StandbyPriorityType standbyPriorityDTO 
                                = (StandbyPriorityType) standbyPriorityCustomList.get(0);
                                boolean isDefault = standbyPriorityDTO.isIsDefault();

                                if (!isDefault) {

                                    standbyPriorityResponseList.add(standbyPriorityDTO);
                                }
                            }
                        }
                    }
                    catch (CheckInException ex) {
                        DcsMessagePanelHandler.handleCheckInServiceErrorMsg(ex);
                    }

                }
            }

            if (standbyPriorityResponseList.size() > 0) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());
            return false;
        }
    }

   /**
    * 
    * <pre>
    * <b>Description : </b>
    * This Method is to populate the data.
    * 
    * @param findStandbyPriorityResponseDTO , not null.
    * </pre>
    */
    private final void renderData(final FindStandbyPriorityResponse findStandbyPriorityResponseDTO) {
        List<StandbyPriorityType> standbyPriorityDTOList = findStandbyPriorityResponseDTO.getStandbyPriorityList()
            .getStandbyPriorities().getStandbyPriorityList();

        NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_LIST,
            standbyPriorityDTOList);

        controller.getNewSPStandardTable().getFilterRow();
        controller.getNewSPFilterTablePanel().populateData();
        /*
         * Object object = SITAGUIServiceUtil.getServiceObject(
         * "aero.sita.csp.gui.table.NGAFTableResponseListener",
         * "ngaftable.listener",
         * controller.getNewSPStandardTable().getTableResponseListenerFilter());
         */
        if (controller.getNewSPStandardTable().getSelectedRowCount() > 0) {
            controller.getNewSPStandardTable().removeRowSelectionInterval(0, 0);
        }
    }

}
