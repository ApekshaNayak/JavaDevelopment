/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.common.ScreenRegistry;
import aero.sita.csp.gui.thickclient.component.filtertable.NGAFFilterTablePanel;
import aero.sita.csp.gui.thickclient.component.lov.SITAGUILOVComponent;
import aero.sita.csp.gui.thickclient.component.pagination.SITAGUIPagination;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.csp.gui.thickclient.coreapi.base.ModuleDO;
import aero.sita.csp.gui.thickclient.util.SITAGUICoreUtil;
import aero.sita.csp.gui.thickclient.util.SITAGUIServiceUtil;
import aero.sita.voyager.checkin.standbyprioritymanager.interfaces.ManageStandbyPriority;
import aero.sita.voyager.dcs.client.common.base.constants.TabNamesConstants;
import aero.sita.voyager.dcs.client.common.base.controller.UIBaseController;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.dcs.client.common.listeners.ColumnHeaderToolTips;
import aero.sita.voyager.dcs.client.common.services.CommonUtilitiesGateWay;
import aero.sita.voyager.dcs.client.common.util.DCSFocusTraversalPolicy;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.filter.UpperCaseFilter;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.helpers.CreateAndUpdateHelper;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.helpers.StandByPriortyDetailsHelper;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.AddStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.CloseStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.CopyStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.CreateStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.DeleteOfStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.DeleteStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.ResetStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.StandbyPriorityTableRowSelectionListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.UpdateStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.ViewEditDescriptionListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.utils.StandbyPriorityUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.validators.CloseStandbyPriorityValidator;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.validators.StandbyPriorityDetailsValidator;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.valueobject.StandbyPriorityVO;

/**
 * <pre>
 * <b>Description : </b>
 * StandbyPriorityController.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:06:13 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class StandbyPriorityDetailsController extends UIBaseController {

    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(StandbyPriorityDetailsController.class);

    /**
     * FIFTEEN.
     */
    private static final int FIFTEEN = 15;

    /**
     * Static string layout.
     */
    private String layout;

    /**
     * Static string model.
     */
    private String model;

    /**
     * Static string dataBinderType.
     */
    private String dataBinderType;

    /**
     * Standard Table.
     */
    private NGAFStandardTable newSPStandardTable;

    /**
     * Screen Helper for New Standby Priority Screen.
     */
    private StandByPriortyDetailsHelper standbyPriorityDetailsScreenHelper;

    /**
     * Filter Table Panel for New Standby Priority Screen.
     */
    private NGAFFilterTablePanel newSPFilterTablePanel;

    /**
     * Single Select LOV.
     */

    private SITAGUILOVComponent newSPSubscriberLOV;

    /**
     * Single Select LOV.
     */
    private SITAGUILOVComponent newSPAirlineLOV;

    /**
     * Delete Button.
     */
    private JButton newSPDeleteButton;

    /**
     * Close Button.
     */
    private JButton newSPCloseButton;

    /**
     * Reset Button.
     */
    private JButton newSPResetButton;

    /**
     * View/Edit Button.
     */
    private JButton newSPViewEditDescButton;

    /**
     * Add rows Button.
     */
    private JButton newSPAddRowsButton;

    /**
     * Combox Box.
     */
    private JComboBox newSPNoOfRowsCombobox;

    /**
     * Copy Button.
     */
    private JButton newSPCopyButton;

    /**
     * Create Button.
     */
    private JButton newSPCreateButton;

    /**
     * Delete Selected Rows Button in New Standby Priority Screen.
     */
    private JButton newSPDeleteSelRowsButton;

    /**
     * Label to replace the Multi-column header in New Standby Priority Screen.
     */
    private JLabel newSPBookedLabel;

    /**
     * Label to replace the Multi-column header in New Standby Priority Screen.
     */
    private JLabel newSPNoRecLabel;

    /**
     * Label to replace the Multi-column header in New Standby Priority Screen.
     */
    private JLabel newSPWaitlistLabel;

    /**
     * Label to replace the Multi-column header in New Standby Priority Screen.
     */
    private JLabel newSPOpenTicketLabel;

    /**
     * Update Button.
     */
    private JButton newSPUpdateButton;

    /**
     * Validator for Standby Priority.
     */
    private StandbyPriorityDetailsValidator standbyPriorityDetailsValidator;

    /**
     * Reference to common utilities gateway..
     */
    private CommonUtilitiesGateWay commonUtilitiesGateWay;

    /**
     * UpdateModified validator.
     */
    private CloseStandbyPriorityValidator closeStandbyPriorityValidator;

    /**
     * createAndUpdateHelper.
     */
    private CreateAndUpdateHelper createAndUpdateHelper;

    /**
     * manageStandbyPriority.
     */
    private ManageStandbyPriority manageStandbyPriorityProxy;

    /**
     * ResetStandbyPriorityListener.
     */
    private ResetStandbyPriorityListener resetStandbyPriorityListener;

    /**
     * ViewEditDescriptionListener.
     */
    private ViewEditDescriptionListener viewEditListener;

    /**
     * DeleteStandbyPriorityListener.
     */
    private DeleteStandbyPriorityListener deleteStandbyPriorityListener;

    /**
     * DeleteOfStandbyPriorityListener.
     */
    private DeleteOfStandbyPriorityListener deleteOfStandbyPriorityListener;

    /**
     * AddStandbyPriorityListener.
     */
    private AddStandbyPriorityListener addRowListener;

    /**
     * CopyStandbyPriorityListener.
     */
    private CopyStandbyPriorityListener copyListener;

    /**
     * CreateStandbyPriorityListener.
     */
    private CreateStandbyPriorityListener createStandbyPriorityListener;

    /**
     * CloseStandbyPriorityListener.
     */
    private CloseStandbyPriorityListener closeStandbyPriorityListener;

    /**
     * StandbyPriorityTableRowSelectionListener.
     */
    private StandbyPriorityTableRowSelectionListener resultTableRowSelHandler;

    /**
     * UpdateStandbyPriorityListener.
     */
    private UpdateStandbyPriorityListener updateStandbyPriorityListener;

    /**
     * defaultSPPagination.
     */
    private SITAGUIPagination newSPPagination;

    /**
     * updateFocusFields having the comma separated value entered in
     * focusTraversalFields.properties updatePanelModelFileds.
     */
    protected String updateFocusFields;
    /**
     * flightViewParentPanel.
     */
    private JPanel newSPParentPanel;

    /**
     * ColumnHeaderToolTips.
     */
    private ColumnHeaderToolTips columnHeaderListener;

    /**
     * columnNames.
     */

    private String[] columnNames = { "", "No.", "Priority code", "Description", 
        "Check PAD", "Date of joining", "Priority",
        "PAD", "Priority", "PAD", "Priority", "PAD", "Priority", "PAD", "SA ticket" };

    /**
     * getCreateAndUpdateHelper.
     * 
     * @return the createAndUpdateHelper , never null.
     */
    public CreateAndUpdateHelper getCreateAndUpdateHelper() {
        return createAndUpdateHelper;
    }

    /**
     * createAndUpdateHelper.
     * 
     * @param createAndUpdateHelperParam , not null.
     */
    public void setCreateAndUpdateHelper(final CreateAndUpdateHelper createAndUpdateHelperParam) {
        createAndUpdateHelper = createAndUpdateHelperParam;
    }

    /**
     * Method to initialize screen components.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to initialize screen components.
     * 
     * </pre>
     */
    public final void initialize() {
        LOGGER.debug("initialize entry");
        addKeyboardShortcutsToButtons();
        initiateFocusTraversalFields();
        setFocusTravesalPolicy();
        setLOVRendering();
        conversionToUpperCase();
        newSPPagination.setVisible(false);
        standbyPriorityDetailsScreenHelper.setController(this);
        standbyPriorityDetailsValidator.setController(this);
        closeStandbyPriorityValidator.setController(this);
        createAndUpdateHelper.setController(this);
        checkScreenStatus();
        registenerListeners();
        LOGGER.debug("initialize exit");

        columnHeaderListener = new ColumnHeaderToolTips();
        JTableHeader columnHeader = newSPFilterTablePanel.getTable().getTableHeader();

        if (columnHeader != null) {
            columnHeader.addMouseMotionListener(columnHeaderListener);
        }
        addHeaderToolTips();
    }

    /**
     * Add Header Tool Tips. <br>
     * <br>
     * Method to add Header tool tip.<br>
     */
    private void addHeaderToolTips() {

        if (columnHeaderListener != null) {
            for (int colCount = 0; colCount < columnNames.length; colCount++) {
                columnHeaderListener.setToolTip(colCount, columnNames[colCount]);
            }
        }
    }

    /**
     * <pre>
     * <b>Description : </b> <br>
     * This method requires a property file reference to read the
     * screen specific fields for focus traversal. 
     * Extending class must provide the focusTraversalFields.properties file.
     * @return Property file , never null.
     * </pre>
     */
    public Properties getFocusFieldsProperties() {
        URL resourceURL = SITAGUIServiceUtil.getResource(
            TabNamesConstants.FOCUS_TRAVERSAL_STANBY_FIELDS_PROPERTIES.toString(), this.getClass(), null);
        if (resourceURL != null) {
            return SITAGUICoreUtil.loadConfig(resourceURL);
        }
        return null;
    }

    /**
     * <pre>
     * <b>Description : </b> <br>
     * This method initiates the way focus traversal policy requires.
     * The focus traversal policy is split into multiple screen (panel)
     * level and hence, the controller invokes the focus traversal for each of the panel.
     * Hence the panel fields are mapped in the focusTraversalFields.properties file
     * and then are populated into the separate string value for each panel.
     * </pre>
     */
    protected void initiateFocusTraversalFields() {

        // fields required as per abstract focus fields controller class.
        Properties focusFieldsProperties = getFocusFieldsProperties();

        if (focusFieldsProperties != null) {

            updateFocusFields = focusFieldsProperties.getProperty(TabNamesConstants.PANEL_FIELDS.toString());
        }
    }

    /**
     * <pre>
     * <b>Description : </b>
     * This method set the focus travesal policy for each component.
     * 
     * </pre>
     */

    private void setFocusTravesalPolicy() {
        ScreenRegistry screenRegistry = ScreenRegistry.getInstance();
        ModuleDO moduleDO = screenRegistry.getCurrentModule();
        if (moduleDO != null) {
            Map<String, JComponent> compMap = moduleDO.getController().getComponentMap();
            DCSFocusTraversalPolicy d = new DCSFocusTraversalPolicy();
            d.focusTraversalPolicy(compMap, updateFocusFields, newSPParentPanel);
        }
    }

    /**
     * <pre>
     * <b>Description : </b>
     * This method short cut keys for PSPI Layout.
     * 
     * </pre>
     */

    private void addKeyboardShortcutsToButtons() {
        // newSPCreateButton.doClick(KeyEvent.VK_C);
        @SuppressWarnings("serial")
        Action createAction = new AbstractAction() {
            public void actionPerformed(final ActionEvent ex) {
                // createStandbyPriorityListener.actionPerformed(ex);
                newSPCreateButton.doClick();
            }
        };
        newSPCreateButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), StandbyPriorityConstants.ACTION_KEY);
        newSPCreateButton.getActionMap().put(StandbyPriorityConstants.ACTION_KEY, createAction);

        newSPUpdateButton.setMnemonic(KeyEvent.VK_U);
        newSPDeleteButton.setMnemonic(KeyEvent.VK_D);
        newSPResetButton.setMnemonic(KeyEvent.VK_R);
        newSPCloseButton.setMnemonic(KeyEvent.VK_L);
    }

    /**
     * 
     * <pre>
     * 
     * convertToUpperCase.
     * 
     * </pre>
     */
    public void conversionToUpperCase() {
        DocumentFilter filter = new UpperCaseFilter();
        ((AbstractDocument) getNewSPAirlineLOV().getSearchField().getDocument()).setDocumentFilter(filter);

        ((AbstractDocument) getNewSPSubscriberLOV().getSearchField().getDocument()).setDocumentFilter(filter);
    }

    /**
     * Method to check screen status.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to check screen status.
     * 
     * </pre>
     */
    public void checkScreenStatus() {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT) != null) {
            String copyDefault = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT)
                .toString();
            if (copyDefault.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.copyDefault();
                deleteDocID();
            }
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY) != null) {
            String copyStandBy = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_STANDBY)
                .toString();
            if (copyStandBy.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.copyStandBy();
                deleteDocID();
            }
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH) != null) {
            String copySearch = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH)
                .toString();
            if (copySearch.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.copySearch();
                deleteDocID();
            }
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.OPEN_SEARCH) != null) {
            String openSearch = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.OPEN_SEARCH)
                .toString();
            if (openSearch.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.openStandBy();
            }
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY) != null) {
            String createStandBy = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY)
                .toString();
            if (createStandBy.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.createStandBy();
            }
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CREATE_STANDBY, null);
        }
        if (NGAFContextUtil.getApplicationContext()
            .get(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB) != null) {
            String copyDefaultConfirmNewTab = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB).toString();
            if (copyDefaultConfirmNewTab.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.copyDefaultConfirmNewTab();
                deleteDocID();
            }
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB,
                null);
        }

        if (NGAFContextUtil.getApplicationContext()
            .get(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB) != null) {
            String copyStandByConfirmNewTab = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB).toString();
            if (copyStandByConfirmNewTab.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.copyStandByConfirmNewTab();
                deleteDocID();
            }
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.COPY_STANDBY_CONFIRM_NEW_TAB, null);
        }
    }

    /**
     * fill doc with null.
     * 
     * <pre>
     * <b>Description : </b>
     * 
     * 
     * </pre>
     */
    private void deleteDocID() {
        for (int i = 0; i < newSPFilterTablePanel.getTable().getRowCount(); i++) {
            newSPFilterTablePanel.getTable().setValueAt("", i, FIFTEEN);
        }

    }

    /**
     * Implementation of refresh Data
     * 
     * <pre>
     * <b>Description : </b>
     * Refreshing Screen based on changed Data.
     * 
     * </pre>
     */
    public void refreshData() {
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_SAVE) != null) {
            String copyDefaultSave = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_DEFAULT_SAVE).toString();

            if (copyDefaultSave.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                deleteDocID();
                standbyPriorityDetailsScreenHelper.copyDefaultSaveConfirm();

            }
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE) != null) {
            DcsMessagePanelHandler.clearAllMessages();
            String copyDefaultDoNotSave = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_DEFAULT_DO_NOT_SAVE).toString();

            if (copyDefaultDoNotSave.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.getDefaultStandByPriorityInDontSave();
                deleteDocID();

            }
        }
        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_SAVE) != null) {
            String copyNewSave = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_SAVE)
                .toString();
            if (copyNewSave.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                deleteDocID();
                standbyPriorityDetailsScreenHelper.copyDefaultSaveConfirm();

            }
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_SAVE) != null) {
            String copySearchSave = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_SEARCH_SAVE).toString();
            if (copySearchSave.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                deleteDocID();
                standbyPriorityDetailsScreenHelper.copyDefaultSaveConfirm();

            }
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE) != null) {
            String createStandByNewSave = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.CREATE_STANDBY_NEW_SAVE).toString();
            if (createStandByNewSave.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                deleteDocID();
                standbyPriorityDetailsScreenHelper.copyDefaultSaveConfirm();

            }
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE) != null) {
            DcsMessagePanelHandler.clearAllMessages();
            String copyNewDoNotSave = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_NEW_DO_NOT_SAVE).toString();

            if (copyNewDoNotSave.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.getStandByPriorityInDontSave();
                deleteDocID();
            }
        }

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE) != null) {
            DcsMessagePanelHandler.clearAllMessages();
            String copySearchDoNotSave = NGAFContextUtil.getApplicationContext()
                .get(StandbyPriorityConstants.COPY_SEARCH_DO_NOT_SAVE).toString();

            if (copySearchDoNotSave.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                standbyPriorityDetailsScreenHelper.searchCopyDoNotSave();
                deleteDocID();
            }
        }

    }

    /**
     * preDisplayInit method.
     * 
     * <pre>
     * <b>Description : </b>
     * <<WRITE DESCRIPTION HERE>>
     * 
     * </pre>
     */
    @Override
    public final void preDisplayInit() {
        super.preDisplayInit();
        LOGGER.debug("In Pre display init...");
        DcsMessagePanelHandler.clearAllMessages();
    }

    /**
     * postDisplayInit method.
     * 
     * <pre>
     * <b>Description : </b>
     * <<WRITE DESCRIPTION HERE>>
     * 
     * </pre>
     */
    @Override
    public final void postDisplayInit() {
        super.postDisplayInit();
        LOGGER.debug("In Post display init...");
        DcsMessagePanelHandler.clearAllMessages();
    }

    /**
     * Registers listeners for the components.
     * 
     * <pre>
     * <b>Description : </b>
     * Registers the listeners for the components.
     * 
     * </pre>
     */
    private void registenerListeners() {
        resetStandbyPriorityListener = new ResetStandbyPriorityListener();
        resetStandbyPriorityListener.setController(this);
        newSPResetButton.addActionListener(resetStandbyPriorityListener);

        viewEditListener = new ViewEditDescriptionListener();
        viewEditListener.setController(this);
        viewEditListener.setNewSPViewEditDescButton(newSPViewEditDescButton);
        viewEditListener.setNewSPFilterTablePanel(newSPFilterTablePanel);
        newSPViewEditDescButton.addActionListener(viewEditListener);

        deleteStandbyPriorityListener = new DeleteStandbyPriorityListener();
        deleteStandbyPriorityListener.setController(this);
        newSPDeleteSelRowsButton.addActionListener(deleteStandbyPriorityListener);

        deleteOfStandbyPriorityListener = new DeleteOfStandbyPriorityListener();
        deleteOfStandbyPriorityListener.setController(this);
        newSPDeleteButton.addActionListener(deleteOfStandbyPriorityListener);

        addRowListener = new AddStandbyPriorityListener();
        addRowListener.setController(this);
        addRowListener.setNewSPAddRowsButton(newSPAddRowsButton);
        addRowListener.setNewSPFilterTablePanel(newSPFilterTablePanel);
        addRowListener.setNewSPNoOfRowsCombobox(newSPNoOfRowsCombobox);
        newSPAddRowsButton.addActionListener(addRowListener);

        copyListener = new CopyStandbyPriorityListener();
        copyListener.setController(this);
        newSPCopyButton.addActionListener(copyListener);

        createStandbyPriorityListener = new CreateStandbyPriorityListener();
        createStandbyPriorityListener.setController(this);
        newSPCreateButton.addActionListener(createStandbyPriorityListener);

        closeStandbyPriorityListener = new CloseStandbyPriorityListener();
        closeStandbyPriorityListener.setController(this);
        newSPCloseButton.addActionListener(closeStandbyPriorityListener);

        resultTableRowSelHandler = new StandbyPriorityTableRowSelectionListener();
        resultTableRowSelHandler.setController(this);
        newSPFilterTablePanel.getTable().getSelectionModel().addListSelectionListener(resultTableRowSelHandler);

        updateStandbyPriorityListener = new UpdateStandbyPriorityListener();
        updateStandbyPriorityListener.setController(this);
        newSPUpdateButton.addActionListener(updateStandbyPriorityListener);
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     *   method to set LOV Rendering.
     * 
     * </pre>
     */
    private void setLOVRendering() {
        newSPSubscriberLOV.setBorder(null);
        StandByPriortyDetailsHelper.decorateLOVToMandatory(newSPSubscriberLOV);
        newSPAirlineLOV.setBorder(null);
        StandByPriortyDetailsHelper.decorateLOVToMandatory(newSPAirlineLOV);

    }

    /**
     * Pre Close. .
     * 
     * <pre>
     * <b>Description : </b>
     * Save before cloae.
     * 
     * </pre>
     */
    public boolean preClose() {
        try {
            String closeTabContext = "";
            DcsMessagePanelHandler.clearAllMessages();
            final StandbyPriorityUtil standbyPriorityUtil = new StandbyPriorityUtil();
            String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
            if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CLOSE) != null) {
                closeTabContext = NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.CLOSE)
                    .toString();
            }
            if (!closeTabContext.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
                if (currentTabName.equals(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB)) {
                    final int isClose = standbyPriorityUtil.confirmDelete("",
                        StandbyPriorityConstants.CLOSE_WARNING_DIALOG_MSG);

                    if (isClose == 0) {
                        boolean isValid = true;
                        if (NGAFContextUtil.getApplicationContext()
                            .get(StandbyPriorityConstants.SP_SUBSCRIBER_CODE) != null) {
                            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_SUBSCRIBER_CODE,
                                null);
                        }

                        if (NGAFContextUtil.getApplicationContext()
                            .get(StandbyPriorityConstants.SP_AIRLINE_CODE) != null) {
                            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_AIRLINE_CODE, null);
                        }

                        if (getNewSPFilterTablePanel() != null && getNewSPFilterTablePanel().getModel() != null) {
                            final DefaultTableModel dm = (DefaultTableModel) getNewSPFilterTablePanel().getModel();

                            if (getNewSPFilterTablePanel().getTable() != null) {
                                NGAFStandardTable spStandardTable = (NGAFStandardTable) getNewSPFilterTablePanel()
                                    .getTable();

                                isValid = getStandbyPriorityDetailsValidator().validateStandbyPriority(dm,
                                    spStandardTable);
                            }
                        }

                        if (isValid) {
                            List<StandbyPriorityVO> spVOList = getStandbyPriorityDetailsScreenHelper()
                                .fetchSPTableData();

                            return getCreateAndUpdateHelper().createNewStandByPriority(spVOList);
                        }

                    }
                    else if (isClose == 1) {
                        if (NGAFContextUtil.getApplicationContext()
                            .get(StandbyPriorityConstants.RESET_DEFAULT) != null) {
                            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_DEFAULT, null);
                        }
                        if (NGAFContextUtil.getApplicationContext()
                            .get(StandbyPriorityConstants.RESET_STANBY) != null) {
                            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_STANBY, null);
                        }
                        if (NGAFContextUtil.getApplicationContext()
                            .get(StandbyPriorityConstants.RESET_SEARCH) != null) {
                            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.RESET_SEARCH, null);
                        }

                        return true;

                    }
                }
                if (!currentTabName.equals(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB)) {
                    boolean isValid;

                    DcsMessagePanelHandler.clearAllMessages();

                    if (getNewSPFilterTablePanel() != null && getNewSPFilterTablePanel().getModel() != null) {
                        final DefaultTableModel dm = (DefaultTableModel) getNewSPFilterTablePanel().getModel();

                        if (getNewSPFilterTablePanel().getTable() != null) {
                            NGAFStandardTable spStandardTable = (NGAFStandardTable) getNewSPFilterTablePanel()
                                .getTable();

                            isValid = getCloseStandbyPriorityValidator().validateStandbyPriority(dm, spStandardTable);
                            List<StandbyPriorityVO> spVOList = getStandbyPriorityDetailsScreenHelper()
                                .fetchSPTableData();
                            spVOList = getCreateAndUpdateHelper().getModifiedRecords(spVOList);
                            if (spVOList != null) {
                                if (spVOList.size() != 0) {
                                    final int isCopyUpdate = standbyPriorityUtil.confirmDelete("",
                                        StandbyPriorityConstants.CLOSE_WARNING_DIALOG_MSG);
                                    if (isCopyUpdate == 0) {
                                        if (getNewSPFilterTablePanel() != null
                                            && getNewSPFilterTablePanel().getModel() != null) {
                                            final DefaultTableModel dm1 = (DefaultTableModel) getNewSPFilterTablePanel()
                                                .getModel();

                                            if (getNewSPFilterTablePanel().getTable() != null) {
                                                NGAFStandardTable spStandardTable1 = 
                                                    (NGAFStandardTable) getNewSPFilterTablePanel()
                                                        .getTable();

                                                isValid = getStandbyPriorityDetailsValidator().validateStandbyPriority(
                                                    dm1, spStandardTable1);
                                            }
                                        }

                                        if (isValid) {

                                            spVOList = getStandbyPriorityDetailsScreenHelper().fetchSPTableData();
                                            List<StandbyPriorityVO> spVOList1 = getCreateAndUpdateHelper()
                                                .getModifiedRecords(spVOList);

                                            if (spVOList != null && spVOList.size() > 0) {
                                                return getCreateAndUpdateHelper().updateStandByPriority(spVOList1);
                                            }
                                        }

                                    }
                                    else if (isCopyUpdate == 1) {
                                        return true;
                                    }
                                }
                                else {
                                    return true;
                                }
                            }

                        }
                    }
                }
            }
            else {
                NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.CLOSE, null);
                return true;
            }
        }
        catch (Exception ex) {
            LOGGER.debug("Error : " + ex.getMessage());

        }

        return false;
    }

    /**
     * Method to cleanup the resources.
     * 
     * <pre>
     * <b>Description : </b>
     * Registers the listeners for the components.
     * 
     * </pre>
     */
    @Override
    public final void cleanup() {
        LOGGER.debug("Cleanup entry");
        super.cleanup();
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'model' attribute value.
     * 
     * @return model , null if not found.
     * </pre>
     */
    public final String getModel() {
        return model;
    }

    /**
     * <pre>
     * <b>Description : </b>
     *  Get the 'layout' attribute value.
     *
     *  @return layout , null if not found.
     * </pre>
     */
    public final String getLayout() {
        return layout;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'dataBinderType' attribute value.
     * 
     * @return dataBinderType , null if not found.
     * </pre>
     */
    public final String getDataBinderType() {
        return dataBinderType;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'layout' attribute value.
     * 
     * @param layoutParam , may be null.
     * </pre>
     */
    public final void setLayout(final String layoutParam) {
        layout = layoutParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'model' attribute value.
     * 
     * @param modelParam , may be null.
     * </pre>
     */
    public final void setModel(final String modelParam) {
        model = modelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'dataBinderType' attribute value.
     * 
     * @param dataBinderTypeParam , may be null.
     * </pre>
     */
    public final void setDataBinderType(final String dataBinderTypeParam) {
        dataBinderType = dataBinderTypeParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPStandardTable' attribute value.
     * 
     * @return newSPStandardTable , never null.
     * </pre>
     */
    public final NGAFStandardTable getNewSPStandardTable() {
        return newSPStandardTable;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPStandardTable' attribute value.
     * 
     * @param newSPStandardTableParam , not null.
     * </pre>
     */
    public final void setNewSPStandardTable(final NGAFStandardTable newSPStandardTableParam) {
        newSPStandardTable = newSPStandardTableParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPFilterTablePanel' attribute value.
     * 
     * @return newSPFilterTablePanel , null if not found.
     * </pre>
     */
    public final NGAFFilterTablePanel getNewSPFilterTablePanel() {
        return newSPFilterTablePanel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPFilterTablePanel' attribute value.
     * 
     * @param newSPFilterTablePanelParam , may be null.
     * </pre>
     */
    public final void setNewSPFilterTablePanel(final NGAFFilterTablePanel newSPFilterTablePanelParam) {
        newSPFilterTablePanel = newSPFilterTablePanelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'standbyPriorityDetailsScreenHelper' attribute value.
     * 
     * @return standbyPriorityDetailsScreenHelper , null if not found.
     * </pre>
     */
    public StandByPriortyDetailsHelper getStandbyPriorityDetailsScreenHelper() {
        return standbyPriorityDetailsScreenHelper;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'standbyPriorityDetailsScreenHelper' attribute value.
     * 
     * @param standbyPriorityDetailsScreenHelperParam , may be null.
     * </pre>
     */
    public void setStandbyPriorityDetailsScreenHelper(
        final StandByPriortyDetailsHelper standbyPriorityDetailsScreenHelperParam) {
        standbyPriorityDetailsScreenHelper = standbyPriorityDetailsScreenHelperParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPSubscriberLOV' attribute value.
     * 
     * @return newSPSubscriberLOV , null if not found.
     * </pre>
     */
    public final SITAGUILOVComponent getNewSPSubscriberLOV() {
        return newSPSubscriberLOV;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPSubscriberLOV' attribute value.
     * 
     * @param newSPSubscriberLOVParam , may be null.
     * </pre>
     */
    public final void setNewSPSubscriberLOV(final SITAGUILOVComponent newSPSubscriberLOVParam) {
        newSPSubscriberLOV = newSPSubscriberLOVParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPAirlineLOV' attribute value.
     * 
     * @return newSPAirlineLOV , null if not found.
     * </pre>
     */
    public final SITAGUILOVComponent getNewSPAirlineLOV() {
        return newSPAirlineLOV;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPAirlineLOV' attribute value.
     * 
     * @param newSPAirlineLOVParam , may be null.
     * </pre>
     */
    public final void setNewSPAirlineLOV(final SITAGUILOVComponent newSPAirlineLOVParam) {
        newSPAirlineLOV = newSPAirlineLOVParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPDeleteButton' attribute value.
     * 
     * @return newSPDeleteButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPDeleteButton() {
        return newSPDeleteButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPDeleteButton' attribute value.
     * 
     * @param newSPDeleteButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPDeleteButton(final JButton newSPDeleteButtonParam) {
        newSPDeleteButton = newSPDeleteButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPCloseButton' attribute value.
     * 
     * @return newSPCloseButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPCloseButton() {
        return newSPCloseButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPCloseButton' attribute value.
     * 
     * @param newSPCloseButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPCloseButton(final JButton newSPCloseButtonParam) {
        newSPCloseButton = newSPCloseButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPResetButton' attribute value.
     * 
     * @return newSPResetButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPResetButton() {
        return newSPResetButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPResetButton' attribute value.
     * 
     * @param newSPResetButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPResetButton(final JButton newSPResetButtonParam) {
        newSPResetButton = newSPResetButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPViewEditDescButton' attribute value.
     * 
     * @return newSPViewEditDescButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPViewEditDescButton() {
        return newSPViewEditDescButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPViewEditDescButton' attribute value.
     * 
     * @param newSPViewEditDescButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPViewEditDescButton(final JButton newSPViewEditDescButtonParam) {
        newSPViewEditDescButton = newSPViewEditDescButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPAddRowsButton' attribute value.
     * 
     * @return newSPAddRowsButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPAddRowsButton() {
        return newSPAddRowsButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPAddRowsButton' attribute value.
     * 
     * @param newSPAddRowsButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPAddRowsButton(final JButton newSPAddRowsButtonParam) {
        newSPAddRowsButton = newSPAddRowsButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPNoOfRowsCombobox' attribute value.
     * 
     * @return newSPNoOfRowsCombobox , null if not found.
     * </pre>
     */
    public final JComboBox getNewSPNoOfRowsCombobox() {
        return newSPNoOfRowsCombobox;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPNoOfRowsCombobox' attribute value.
     * 
     * @param newSPNoOfRowsComboboxParam , may be null.
     * </pre>
     */
    public final void setNewSPNoOfRowsCombobox(final JComboBox newSPNoOfRowsComboboxParam) {
        newSPNoOfRowsCombobox = newSPNoOfRowsComboboxParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPCopyButton' attribute value.
     * 
     * @return newSPCopyButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPCopyButton() {
        return newSPCopyButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPCopyButton' attribute value.
     * 
     * @param newSPCopyButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPCopyButton(final JButton newSPCopyButtonParam) {
        newSPCopyButton = newSPCopyButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPCreateButton' attribute value.
     * 
     * @return newSPCreateButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPCreateButton() {
        return newSPCreateButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPCreateButton' attribute value.
     * 
     * @param newSPCreateButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPCreateButton(final JButton newSPCreateButtonParam) {
        newSPCreateButton = newSPCreateButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPDeleteSelRowsButton' attribute value.
     * 
     * @return newSPDeleteSelRowsButton , null if not found.
     * </pre>
     */
    public final JButton getNewSPDeleteSelRowsButton() {
        return newSPDeleteSelRowsButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPDeleteSelRowsButton' attribute value.
     * 
     * @param newSPDeleteSelRowsButtonParam , may be null.
     * </pre>
     */
    public final void setNewSPDeleteSelRowsButton(final JButton newSPDeleteSelRowsButtonParam) {
        newSPDeleteSelRowsButton = newSPDeleteSelRowsButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPBookedLabel' attribute value.
     * 
     * @return newSPBookedLabel , null if not found.
     * </pre>
     */
    public final JLabel getNewSPBookedLabel() {
        return newSPBookedLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPNoRecLabel' attribute value.
     * 
     * @return newSPNoRecLabel , null if not found.
     * </pre>
     */
    public final JLabel getNewSPNoRecLabel() {
        return newSPNoRecLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPWaitlistLabel' attribute value.
     * 
     * @return newSPWaitlistLabel , null if not found.
     * </pre>
     */
    public final JLabel getNewSPWaitlistLabel() {
        return newSPWaitlistLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPOpenTicketLabel' attribute value.
     * 
     * @return newSPOpenTicketLabel , null if not found.
     * </pre>
     */
    public final JLabel getNewSPOpenTicketLabel() {
        return newSPOpenTicketLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPBookedLabel' attribute value.
     * 
     * @param newSPBookedLabelParam , may be null.
     * </pre>
     */
    public final void setNewSPBookedLabel(final JLabel newSPBookedLabelParam) {
        newSPBookedLabel = newSPBookedLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPNoRecLabel' attribute value.
     * 
     * @param newSPNoRecLabelParam , may be null.
     * </pre>
     */
    public final void setNewSPNoRecLabel(final JLabel newSPNoRecLabelParam) {
        newSPNoRecLabel = newSPNoRecLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPWaitlistLabel' attribute value.
     * 
     * @param newSPWaitlistLabelParam , may be null.
     * </pre>
     */
    public final void setNewSPWaitlistLabel(final JLabel newSPWaitlistLabelParam) {
        newSPWaitlistLabel = newSPWaitlistLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPOpenTicketLabel' attribute value.
     * 
     * @param newSPOpenTicketLabelParam , may be null.
     * </pre>
     */
    public final void setNewSPOpenTicketLabel(final JLabel newSPOpenTicketLabelParam) {
        newSPOpenTicketLabel = newSPOpenTicketLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPUpdateButton' attribute value.
     * 
     * @return newSPUpdateButton , null if not found.
     * </pre>
     */
    public JButton getNewSPUpdateButton() {
        return newSPUpdateButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPUpdateButton' attribute value.
     * 
     * @param newSPUpdateButtonParam , may be null.
     * </pre>
     */
    public void setNewSPUpdateButton(final JButton newSPUpdateButtonParam) {
        newSPUpdateButton = newSPUpdateButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'commonUtilitiesGateWay' attribute value.
     * 
     * @param commonUtilitiesGateWayParam , may be null.
     * </pre>
     */
    public void setCommonUtilitiesGateWay(final CommonUtilitiesGateWay commonUtilitiesGateWayParam) {
        commonUtilitiesGateWay = commonUtilitiesGateWayParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'commonUtilitiesGateWay' attribute value.
     * 
     * @return commonUtilitiesGateWay , null if not found.
     * </pre>
     */
    public CommonUtilitiesGateWay getCommonUtilitiesGateWay() {
        return commonUtilitiesGateWay;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'standbyPriorityDetailsValidator' attribute value.
     * 
     * @return standbyPriorityDetailsValidator , null if not found.
     * </pre>
     */
    public StandbyPriorityDetailsValidator getStandbyPriorityDetailsValidator() {
        return standbyPriorityDetailsValidator;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'standbyPriorityDetailsValidator' attribute value.
     * 
     * @param standbyPriorityDetailsValidatorParam , may be null.
     * </pre>
     */
    public void setStandbyPriorityDetailsValidator(
        final StandbyPriorityDetailsValidator standbyPriorityDetailsValidatorParam) {
        standbyPriorityDetailsValidator = standbyPriorityDetailsValidatorParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'closeStandbyPriorityValidator' attribute value.
     * 
     * @return closeStandbyPriorityValidator , null if not found.
     * </pre>
     */
    public CloseStandbyPriorityValidator getCloseStandbyPriorityValidator() {
        return closeStandbyPriorityValidator;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'closeStandbyPriorityValidator' attribute value.
     * 
     * @param closeStandbyPriorityValidatorParam , may be null.
     * </pre>
     */
    public void setCloseStandbyPriorityValidator(final CloseStandbyPriorityValidator 
        closeStandbyPriorityValidatorParam) {
        closeStandbyPriorityValidator = closeStandbyPriorityValidatorParam;
    }

    /**
     * getManageStandbyPriorityProxy.
     * 
     * @return the manageStandbyPriorityProxy , never null.
     */
    public ManageStandbyPriority getManageStandbyPriorityProxy() {
        return manageStandbyPriorityProxy;
    }

    /**
     * setManageStandbyPriorityProxy.
     * 
     * @param manageStandbyPriorityProxyParam , not null.
     */
    public void setManageStandbyPriorityProxy(final ManageStandbyPriority manageStandbyPriorityProxyParam) {
        manageStandbyPriorityProxy = manageStandbyPriorityProxyParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'newSPPagination' attribute value.
     * 
     * @return newSPPagination , null if not found.
     * </pre>
     */

    public SITAGUIPagination getNewSPPagination() {
        return newSPPagination;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'newSPPagination' attribute value.
     * 
     * @param newSPPaginationParam , may be null.
     * </pre>
     */
    public void setNewSPPagination(final SITAGUIPagination newSPPaginationParam) {
        newSPPagination = newSPPaginationParam;
    }

}
