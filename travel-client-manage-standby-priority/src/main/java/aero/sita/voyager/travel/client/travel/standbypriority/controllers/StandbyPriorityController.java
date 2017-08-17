/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.filter.UpperCaseFilter;
import aero.sita.voyager.travel.client.travel.standbypriority.helpers.DefaultStandbyPriorityScreenHelper;
import aero.sita.voyager.travel.client.travel.standbypriority.helpers.StandbyPriorityScreenHelper;
import aero.sita.voyager.travel.client.travel.standbypriority.helpers.TabbedScreenHelper;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.AddDefaultStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.CopyDefaultStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.CopyStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.CreateDefaultStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.CreateStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.DefaultStandbyPriorityChangeListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.DefaultStandbyPriorityTableRowSelectionListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.DeleteDefaultStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.OpenStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.ResetStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.SearchStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.SearchStandbyPriorityTableRowSelectionListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.UpdateDefaultStandbyPriorityListener;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.ViewEditDescriptionListener;
import aero.sita.voyager.travel.client.travel.standbypriority.validators.DeleteStanbyPriorityValidator;
import aero.sita.voyager.travel.client.travel.standbypriority.validators.StandbyPriorityValidator;


/**
 * <pre>
 * <b>Description : </b>
 * StandbyPriorityController.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:06:13 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */

public class StandbyPriorityController extends UIBaseController {

    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(StandbyPriorityController.class);

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
     * Screen Helper for Default Standby Priority Screen.
     */
    private DefaultStandbyPriorityScreenHelper defaultSPScreenHelper;

    /**
     * CommonUtilitiesGateWay.
     */
    private CommonUtilitiesGateWay commonUtilitiesGateWay;

    /**
     * manageStandbyPriority.
     */
    private ManageStandbyPriority manageStandbyPriorityProxy;

    /**
     * NGAFFilterTablePanel for Default Standby Priority Filter Table.
     */
    private NGAFFilterTablePanel defaultSPFilterTablePanel;

    /**
     * TabbedScreenHelper to initiate the tab of the standby priority.
     */
    private TabbedScreenHelper tabbedScreenHelper;

    /**
     * This Panel contain all the Child Tabs.
     */
    private JPanel manageStandbyPriorityPanel;

    /**
     * Screen Helper for Search Standby Priority Screen.
     */
    private StandbyPriorityScreenHelper standbyPriorityScreenHelper;

    /**
     * Standby Priority Search Button.
     */
    private JButton searchSPSearchButton;

    /**
     * NGAFFilterTablePanel for Search Standby Priority Filter Table.
     */
    private NGAFFilterTablePanel searchSPFilterTablePanel;

    /**
     * NGAFFilterPanel for Search Standby Priority Filter.
     */
    private NGAFFilterPanel searchSPFilterRowPanel;

    /**
     * NGAFStandardTable for Search Standby Priority Standard Table.
     */
    private NGAFStandardTable searchSPStandardTable;

    /**
     * SubscriberLOV in Standby Priority Screen.
     */
    private SITAGUILOVComponent searchSPSubscriberLOV;

    /**
     * AirlineLOV in Standby Priority Screen.
     */

    private NGAFMultiSelectLOVComponent searchSPAirlinesLOV;

    /**
     * ResultsPaneHeader in Standby Priority Screen.
     */
    private JXTaskPane searchSPResultsPaneHeader;

    /**
     * Open Button in Standby Priority Screen.
     */
    private JButton searchSPOpenButton;

    /**
     * Copy Button in Standby Priority Screen.
     */
    private JButton searchSPCopyButton;

    /**
     * Create Button in Standby Priority Screen.
     */
    private JButton searchSPCreateButton;

    /**
     * Reset Button in Standby Priority Screen.
     */
    private JButton searchSPResetButton;

    /**
     * View and Edit Button in Default Standby Priority Screen.
     */
    private JButton defaultSPViewEditDescButton;

    /**
     * Delete Selected Rows Button in Default Standby Priority Screen.
     */
    private JButton defaultSPDeleteSelRowsButton;

    /**
     * Create Button in Default Standby Priority Screen.
     */
    private JButton defaultSPCreateButton;

    /**
     * Copy Button in Default Standby Priority Screen.
     */
    private JButton defaultSPCopyButton;

    /**
     * No Of Rows Combobox in Default Standby Priority Screen.
     */
    private JComboBox defaultSPNoOfRowsCombobox;

    /**
     * Add Rows Button in Default Standby Priority Screen.
     */
    private JButton defaultSPAddRowsButton;

    /**
     * NGAFStandardTable for the result population in Default Standby Priority
     * Screen.
     */
    private NGAFStandardTable defaultSPStandardTable;

    /**
     * Label to replace the Multi-column header in Default Standby Priority
     * Screen.
     */
    private JLabel defaultSPBookedLabel;

    /**
     * Label to replace the Multi-column header in Default Standby Priority
     * Screen.
     */
    private JLabel defaultSPNoRecLabel;

    /**
     * Label to replace the Multi-column header in Default Standby Priority
     * Screen.
     */
    private JLabel defaultSPWaitlistLabel;

    /**
     * Label to replace the Multi-column header in Default Standby Priority
     * Screen.
     */
    private JLabel defaultSPOpenTicketLabel;

    /**
     * JTabbed pane.
     */
    private JTabbedPane manageStandbyPriorityTabbedPane;

    /**
     * JPanel for 'parentPanelDSearchTab'.
     */
    private JPanel parentPanelDSearchTab;

    /**
     * ConfirmStandByPriorityDTO.
     */
    private ConfirmStandbyPriority confirmStandbyPriorityDTO;

    /**
     * CreateStandByPriorityDTO.
     */
    private CreateStandbyPriority createStandbyPriorityDTO;

    /**
     * Update Button in Default Standby Priority Screen.
     */
    private JButton defaultSPUpdateButton;

    /**
     * Validator for Standby Priority.
     */
    private StandbyPriorityValidator standbyPriorityValidator;

    /**
     * defaultStandbyPriorityChangeListener.
     */
    private DefaultStandbyPriorityChangeListener defaultStandbyPriorityChangeListener;

    /**
     * resetStandbyPriorityListener.
     */
    private ResetStandbyPriorityListener resetStandbyPriorityListener;

    /**
     * searchStandbyPriorityListener.
     */
    private SearchStandbyPriorityListener searchStandbyPriorityListener;

    /**
     * openStandbyPriorityListener.
     */
    private OpenStandbyPriorityListener openStandbyPriorityListener;

    /**
     * openStandbyPriorityListener.
     */
    private ViewEditDescriptionListener viewEditListener;

    /**
     * deleteDefaultStandByListener.
     */
    private DeleteDefaultStandbyPriorityListener deleteDefaultStandByListener;

    /**
     * addRowListener.
     */
    private AddDefaultStandbyPriorityListener addRowListener;

    /**
     * resultTableRowSelHandler.
     */
    private DefaultStandbyPriorityTableRowSelectionListener resultTableRowSelHandler;

    /**
     * tableRowSelectionListener.
     */
    private SearchStandbyPriorityTableRowSelectionListener tableRowSelectionListener;

    /**
     * copyStandbyPriorityListener.
     */
    private CopyStandbyPriorityListener copyStandbyPriorityListener;

    /**
     * copyDefaultListener.
     */
    private CopyDefaultStandbyPriorityListener copyDefaultListener;

    /**
     * createStandbyPriorityListener.
     */
    private CreateStandbyPriorityListener createStandbyPriorityListener;

    /**
     * createDefaultStandbyPriorityListener.
     */
    private CreateDefaultStandbyPriorityListener createDefaultStandbyPriorityListener;

    /**
     * updateDefaultStandbyPriorityListener.
     */
    private UpdateDefaultStandbyPriorityListener updateDefaultStandbyPriorityListener;
    
    /**
     * DeleteStanbyPriorityValidator.
     */
    private DeleteStanbyPriorityValidator deleteStanbyPriorityValidator;
    
    /**
     * defaultSPPagination.
     */
    private SITAGUIPagination defaultSPPagination;
    
    /**
     * ColumnHeaderToolTips.
     */
    private ColumnHeaderToolTips columnHeaderListener;
    
    /**
     * ColumnHeaderToolTips.
     */
    private ColumnHeaderToolTips searchColumnHeaderListener;

    /**
     * columnNames.
     */
    private String[] columnNames = { "", "No.", "Priority code", "Description", 
        "Check PAD", "Date of joining", "Priority","PAD", "Priority","PAD", "Priority","PAD","Priority","PAD","SA ticket" };
    /**
     * columnNames.
     */
    private String[] searchColumnNames = { "", "No.", "Airline code","Airline name" };



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
        setLOVRendering();
        convertToUpperCase();
        defaultSPScreenHelper.setController(this);
        defaultSPScreenHelper.updateScreenUI();
        
        columnHeaderListener = new ColumnHeaderToolTips();
        searchColumnHeaderListener = new ColumnHeaderToolTips();
        
        JTableHeader columnHeader = defaultSPFilterTablePanel.getTable().getTableHeader();
        JTableHeader searchColumnHeader = searchSPFilterTablePanel.getTable().getTableHeader();
       
        if(columnHeader != null){
            columnHeader.addMouseMotionListener(columnHeaderListener);
        }
        addHeaderToolTips();
        
        if(searchColumnHeader != null){
            searchColumnHeader.addMouseMotionListener(searchColumnHeaderListener);
        }
        addSearchHeaderToolTips();       


        standbyPriorityScreenHelper.setController(this);
        standbyPriorityScreenHelper.updateScreenUI();
        deleteStanbyPriorityValidator.setController(this);

        standbyPriorityValidator.setController(this);
        defaultStandbyPriorityChangeListener = new DefaultStandbyPriorityChangeListener();
        defaultStandbyPriorityChangeListener.setController(this);
        manageStandbyPriorityTabbedPane.addChangeListener(defaultStandbyPriorityChangeListener);

        registenerListeners();
        LOGGER.debug("initialize exit");
    }
    /**
     * <pre>
     * <b>Description : </b>
     * This method short cut keys for PSPI Layout.
     * 
     * </pre>
     */
    private void addKeyboardShortcutsToButtons() {

        //Default Layout.
        defaultSPCreateButton.setMnemonic(KeyEvent.VK_D);
        defaultSPUpdateButton.setMnemonic(KeyEvent.VK_U);
        
        //Search Layout.
        searchSPSearchButton.setMnemonic(KeyEvent.VK_S);
        searchSPOpenButton.setMnemonic(KeyEvent.VK_O);
        searchSPResetButton.setMnemonic(KeyEvent.VK_R);
        
        //Search Layout- Create Standby Priority.
        @SuppressWarnings("serial")
        Action createAction = new AbstractAction() {
            public void actionPerformed(final ActionEvent ex) {
                //createStandbyPriorityListener.actionPerformed(ex);
                searchSPCreateButton.doClick();
            }
        };
        searchSPCreateButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK), StandbyPriorityConstants.ACTION_KEY);
        searchSPCreateButton.getActionMap().put(StandbyPriorityConstants.ACTION_KEY, createAction);
        
    }
    
    /**
     * Add Header Tool Tips. <br>
     * <br>
     * Method to add Header tool tip.<br>
     */
    private void addSearchHeaderToolTips() {
        
        if (searchColumnHeaderListener != null) {
            for (int colCount = 0; colCount < searchColumnNames.length; colCount++) {
                searchColumnHeaderListener
                        .setToolTip(colCount, searchColumnNames[colCount]);
            }
        }
    }
    
    /**
     * Add Header Tool Tips. <br>
     * <br>
     * Method to add Header tool tip.<br>
     */
    private void addHeaderToolTips() {
        
        if (columnHeaderListener != null) {
            for (int colCount = 0; colCount < columnNames.length; colCount++) {
                columnHeaderListener
                        .setToolTip(colCount, columnNames[colCount]);
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
    public void preDisplayInit() {
        super.preDisplayInit();
        LOGGER.debug("In Pre display init...");
        //DcsMessagePanelHandler.clearAllMessages();
    }

    /**
     * Refresh Data.
     * <pre>
     * <b>Description : </b>
     * <<WRITE DESCRIPTION HERE>>
     * 
     * </pre>
     */
    public void refreshData() {
        String deleteStatus = (String) NGAFAppContextUtil.getAppContext().get("DELETE_STATUS");
        if (deleteStatus.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE)) {
            searchSPSearchButton.doClick();
        }
        NGAFAppContextUtil.getAppContext().remove("DELETE_STATUS");
        NGAFAppContextUtil.getAppContext().put("DELETE_STATUS", "NO");
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
    public void postDisplayInit() {
        super.postDisplayInit();
        LOGGER.debug("In Post display init...");
        DcsMessagePanelHandler.clearAllMessages();
    }
    /**
     * 
     * <pre>
     * 
     * ConvertToUpperCase.
     * </pre>
     */
    public void convertToUpperCase(){
        DocumentFilter filter = new UpperCaseFilter();
        ((AbstractDocument)getSearchSPAirlinesLOV().getSearchField().getDocument()).setDocumentFilter(filter);

        ((AbstractDocument)getSearchSPSubscriberLOV().getSearchField().getDocument()).setDocumentFilter(filter);
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
        
        LOGGER.debug("registenerListeners");

        resetStandbyPriorityListener = new ResetStandbyPriorityListener();
        resetStandbyPriorityListener.setController(this);
        searchSPResetButton.addActionListener(resetStandbyPriorityListener);

        searchStandbyPriorityListener = new SearchStandbyPriorityListener();
        searchStandbyPriorityListener.setController(this);
        searchSPSearchButton.addActionListener(searchStandbyPriorityListener);

        openStandbyPriorityListener = new OpenStandbyPriorityListener();
        openStandbyPriorityListener.setController(this);
        searchSPOpenButton.addActionListener(openStandbyPriorityListener);

        viewEditListener = new ViewEditDescriptionListener();
        viewEditListener.setController(this);
        viewEditListener.setDefaultSPViewEditDescButton(defaultSPViewEditDescButton);
        viewEditListener.setDefaultSPFilterTablePanel(defaultSPFilterTablePanel);
        defaultSPViewEditDescButton.addActionListener(viewEditListener);

        deleteDefaultStandByListener = new DeleteDefaultStandbyPriorityListener();
        deleteDefaultStandByListener.setController(this);
        deleteDefaultStandByListener.setDefaultSPDeleteSelRowsButton(defaultSPDeleteSelRowsButton);
        deleteDefaultStandByListener.setDefaultSPFilterTablePanel(defaultSPFilterTablePanel);
        defaultSPDeleteSelRowsButton.addActionListener(deleteDefaultStandByListener);

        copyDefaultListener = new CopyDefaultStandbyPriorityListener();
        copyDefaultListener.setController(this);
        defaultSPCopyButton.addActionListener(copyDefaultListener);

        addRowListener = new AddDefaultStandbyPriorityListener();
        addRowListener.setController(this);
        addRowListener.setDefaultSPAddRowsButton(defaultSPAddRowsButton);
        addRowListener.setDefaultSPFilterTablePanel(defaultSPFilterTablePanel);
        addRowListener.setDefaultSPNoOfRowsCombobox(defaultSPNoOfRowsCombobox);
        defaultSPAddRowsButton.addActionListener(addRowListener);

        resultTableRowSelHandler = new DefaultStandbyPriorityTableRowSelectionListener();
        resultTableRowSelHandler.setController(this);
        defaultSPFilterTablePanel.getTable().getSelectionModel().addListSelectionListener(resultTableRowSelHandler);

        tableRowSelectionListener = new SearchStandbyPriorityTableRowSelectionListener();
        tableRowSelectionListener.setController(this);
        searchSPFilterTablePanel.getTable().getSelectionModel().addListSelectionListener(tableRowSelectionListener);

        copyStandbyPriorityListener = new CopyStandbyPriorityListener();
        copyStandbyPriorityListener.setController(this);
        searchSPCopyButton.addActionListener(copyStandbyPriorityListener);

        createStandbyPriorityListener = new CreateStandbyPriorityListener();
        createStandbyPriorityListener.setController(this);
        searchSPCreateButton.addActionListener(createStandbyPriorityListener);

        createDefaultStandbyPriorityListener = new CreateDefaultStandbyPriorityListener();
        createDefaultStandbyPriorityListener.setController(this);
        defaultSPCreateButton.addActionListener(createDefaultStandbyPriorityListener);

        updateDefaultStandbyPriorityListener = new UpdateDefaultStandbyPriorityListener();
        updateDefaultStandbyPriorityListener.setController(this);
        defaultSPUpdateButton.addActionListener(updateDefaultStandbyPriorityListener);

        LOGGER.debug("end::registenerListeners");
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
        searchSPSubscriberLOV.setBorder(null);
        StandbyPriorityScreenHelper.decorateLOVToMandatory(searchSPSubscriberLOV);
//        searchSPAirlinesLOV.setBorder(null);
//        StandbyPriorityScreenHelper.decorateAirlineLOVToMandatory(searchSPAirlinesLOV);
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
     * Get the 'defaultSPScreenHelper' attribute value.
     * 
     * @return defaultSPScreenHelper , null if not found.
     * </pre>
     */
    public final DefaultStandbyPriorityScreenHelper getDefaultSPScreenHelper() {
        return defaultSPScreenHelper;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPScreenHelper' attribute value.
     * 
     * @param defaultSPScreenHelperParam , may be null.
     * </pre>
     */
    public final void setDefaultSPScreenHelper(final DefaultStandbyPriorityScreenHelper defaultSPScreenHelperParam) {
        defaultSPScreenHelper = defaultSPScreenHelperParam;
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
     * Get the 'tabbedScreenHelper' attribute value.
     * 
     * @return tabbedScreenHelper , null if not found.
     * </pre>
     */
    public final TabbedScreenHelper getTabbedScreenHelper() {
        return tabbedScreenHelper;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'tabbedScreenHelper' attribute value.
     * 
     * @param tabbedScreenHelperParam , may be null.
     * </pre>
     */
    public final void setTabbedScreenHelper(final TabbedScreenHelper tabbedScreenHelperParam) {
        tabbedScreenHelper = tabbedScreenHelperParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'manageStandbyPriorityPanel' attribute value.
     * 
     * @return manageStandbyPriorityPanel , null if not found.
     * </pre>
     */
    public final JPanel getManageStandbyPriorityPanel() {
        return manageStandbyPriorityPanel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'manageStandbyPriorityPanel' attribute value.
     * 
     * @param manageStandbyPriorityPanelParam , may be null.
     * </pre>
     */
    public final void setManageStandbyPriorityPanel(final JPanel manageStandbyPriorityPanelParam) {
        manageStandbyPriorityPanel = manageStandbyPriorityPanelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPResultsPaneHeader' attribute value.
     * 
     * @return searchSPResultsPaneHeader , null if not found.
     * </pre>
     */
    public final JXTaskPane getSearchSPResultsPaneHeader() {
        return searchSPResultsPaneHeader;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPResultsPaneHeader' attribute value.
     * 
     * @param searchSPResultsPaneHeaderParam , may be null.
     * </pre>
     */
    public final void setSearchSPResultsPaneHeader(final JXTaskPane searchSPResultsPaneHeaderParam) {
        searchSPResultsPaneHeader = searchSPResultsPaneHeaderParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'standbyPriorityScreenHelper' attribute value.
     * 
     * @return standbyPriorityScreenHelper , null if not found.
     * </pre>
     */
    public final StandbyPriorityScreenHelper getStandbyPriorityScreenHelper() {
        return standbyPriorityScreenHelper;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'standbyPriorityScreenHelper' attribute value.
     * 
     * @param standbyPriorityScreenHelperParam , may be null.
     * </pre>
     */
    public final void setStandbyPriorityScreenHelper(
        final StandbyPriorityScreenHelper standbyPriorityScreenHelperParam) {
        standbyPriorityScreenHelper = standbyPriorityScreenHelperParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPSearchButton' attribute value.
     * 
     * @return searchSPSearchButton , null if not found.
     * </pre>
     */
    public final JButton getSearchSPSearchButton() {
        return searchSPSearchButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPSearchButton' attribute value.
     * 
     * @param searchSPSearchButtonParam , may be null.
     * </pre>
     */
    public final void setSearchSPSearchButton(final JButton searchSPSearchButtonParam) {
        searchSPSearchButton = searchSPSearchButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPFilterTablePanel' attribute value.
     * 
     * @return searchSPFilterTablePanel , null if not found.
     * </pre>
     */
    public final NGAFFilterTablePanel getSearchSPFilterTablePanel() {
        return searchSPFilterTablePanel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPFilterTablePanel' attribute value.
     * 
     * @param searchSPFilterTablePanelParam , may be null.
     * </pre>
     */
    public final void setSearchSPFilterTablePanel(final NGAFFilterTablePanel searchSPFilterTablePanelParam) {
        searchSPFilterTablePanel = searchSPFilterTablePanelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPFilterRowPanel' attribute value.
     * 
     * @return searchSPFilterRowPanel , null if not found.
     * </pre>
     */
    public final NGAFFilterPanel getSearchSPFilterRowPanel() {
        return searchSPFilterRowPanel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPFilterRowPanel' attribute value.
     * 
     * @param searchSPFilterRowPanelParam , may be null.
     * </pre>
     */
    public final void setSearchSPFilterRowPanel(final NGAFFilterPanel searchSPFilterRowPanelParam) {
        searchSPFilterRowPanel = searchSPFilterRowPanelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPStandardTable' attribute value.
     * 
     * @return searchSPStandardTable , null if not found.
     * </pre>
     */
    public final NGAFStandardTable getSearchSPStandardTable() {
        return searchSPStandardTable;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPStandardTable' attribute value.
     * 
     * @param searchSPStandardTableParam , may be null.
     * </pre>
     */
    public final void setSearchSPStandardTable(final NGAFStandardTable searchSPStandardTableParam) {
        searchSPStandardTable = searchSPStandardTableParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPOpenButton' attribute value.
     * 
     * @return searchSPOpenButton , null if not found.
     * </pre>
     */
    public final JButton getSearchSPOpenButton() {
        return searchSPOpenButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPOpenButton' attribute value.
     * 
     * @param searchSPOpenButtonParam , may be null.
     * </pre>
     */
    public final void setSearchSPOpenButton(final JButton searchSPOpenButtonParam) {
        searchSPOpenButton = searchSPOpenButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPCopyButton' attribute value.
     * 
     * @return searchSPCopyButton , null if not found.
     * </pre>
     */
    public final JButton getSearchSPCopyButton() {
        return searchSPCopyButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPCopyButton' attribute value.
     * 
     * @param searchSPCopyButtonParam , may be null.
     * </pre>
     */
    public final void setSearchSPCopyButton(final JButton searchSPCopyButtonParam) {
        searchSPCopyButton = searchSPCopyButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPCreateButton' attribute value.
     * 
     * @return searchSPCreateButton , null if not found.
     * </pre>
     */
    public final JButton getSearchSPCreateButton() {
        return searchSPCreateButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPCreateButton' attribute value.
     * 
     * @param searchSPCreateButtonParam , may be null.
     * </pre>
     */
    public final void setSearchSPCreateButton(final JButton searchSPCreateButtonParam) {
        searchSPCreateButton = searchSPCreateButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPResetButton' attribute value.
     * 
     * @return searchSPResetButton , null if not found.
     * </pre>
     */
    public final JButton getSearchSPResetButton() {
        return searchSPResetButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPResetButton' attribute value.
     * 
     * @param searchSPResetButtonParam , may be null.
     * </pre>
     */
    public final void setSearchSPResetButton(final JButton searchSPResetButtonParam) {
        searchSPResetButton = searchSPResetButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPStandardTable' attribute value.
     * 
     * @return defaultSPStandardTable , null if not found.
     * </pre>
     */

    public final NGAFStandardTable getDefaultSPStandardTable() {
        return defaultSPStandardTable;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPStandardTable' attribute value.
     * 
     * @param defaultSPStandardTableParam , may be null.
     * </pre>
     */
    public final void setDefaultSPStandardTable(final NGAFStandardTable defaultSPStandardTableParam) {
        defaultSPStandardTable = defaultSPStandardTableParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPBookedLabel' attribute value.
     * 
     * @return defaultSPBookedLabel , null if not found.
     * </pre>
     */
    public final JLabel getDefaultSPBookedLabel() {
        return defaultSPBookedLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPNoRecLabel' attribute value.
     * 
     * @return defaultSPNoRecLabel , null if not found.
     * </pre>
     */
    public final JLabel getDefaultSPNoRecLabel() {
        return defaultSPNoRecLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPWaitlistLabel' attribute value.
     * 
     * @return defaultSPWaitlistLabel , null if not found.
     * </pre>
     */
    public final JLabel getDefaultSPWaitlistLabel() {
        return defaultSPWaitlistLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPOpenTicketLabel' attribute value.
     * 
     * @return defaultSPOpenTicketLabel , null if not found.
     * </pre>
     */
    public final JLabel getDefaultSPOpenTicketLabel() {
        return defaultSPOpenTicketLabel;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPBookedLabel' attribute value.
     * 
     * @param defaultSPBookedLabelParam , may be null.
     * </pre>
     */
    public final void setDefaultSPBookedLabel(final JLabel defaultSPBookedLabelParam) {
        defaultSPBookedLabel = defaultSPBookedLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPNoRecLabel' attribute value.
     * 
     * @param defaultSPNoRecLabelParam , may be null.
     * </pre>
     */
    public final void setDefaultSPNoRecLabel(final JLabel defaultSPNoRecLabelParam) {
        defaultSPNoRecLabel = defaultSPNoRecLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPWaitlistLabel' attribute value.
     * 
     * @param defaultSPWaitlistLabelParam , may be null.
     * </pre>
     */
    public final void setDefaultSPWaitlistLabel(final JLabel defaultSPWaitlistLabelParam) {
        defaultSPWaitlistLabel = defaultSPWaitlistLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPOpenTicketLabel' attribute value.
     * 
     * @param defaultSPOpenTicketLabelParam , may be null.
     * </pre>
     */
    public final void setDefaultSPOpenTicketLabel(final JLabel defaultSPOpenTicketLabelParam) {
        defaultSPOpenTicketLabel = defaultSPOpenTicketLabelParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPSubscriberLOV' attribute value.
     * 
     * @return searchSPSubscriberLOV , null if not found.
     * </pre>
     */

    public final SITAGUILOVComponent getSearchSPSubscriberLOV() {
        return searchSPSubscriberLOV;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPSubscriberLOV' attribute value.
     * 
     * @param searchSPSubscriberLOVParam , may be null.
     * </pre>
     */
    public final void setSearchSPSubscriberLOV(final SITAGUILOVComponent searchSPSubscriberLOVParam) {
        searchSPSubscriberLOV = searchSPSubscriberLOVParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPCreateButton' attribute value.
     * 
     * @return defaultSPCreateButton , null if not found.
     * </pre>
     */
    public final JButton getDefaultSPCreateButton() {
        return defaultSPCreateButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPCreateButton' attribute value.
     * 
     * @param defaultSPCreateButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPCreateButton(final JButton defaultSPCreateButtonParam) {
        defaultSPCreateButton = defaultSPCreateButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPAddRowsButton' attribute value.
     * 
     * @return defaultSPAddRowsButton , null if not found.
     * </pre>
     */
    public final JButton getDefaultSPAddRowsButton() {
        return defaultSPAddRowsButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPAddRowsButton' attribute value.
     * 
     * @param defaultSPAddRowsButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPAddRowsButton(final JButton defaultSPAddRowsButtonParam) {
        defaultSPAddRowsButton = defaultSPAddRowsButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPNoOfRowsCombobox' attribute value.
     * 
     * @return defaultSPNoOfRowsCombobox , null if not found.
     * </pre>
     */

    public final JComboBox getDefaultSPNoOfRowsCombobox() {
        return defaultSPNoOfRowsCombobox;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPNoOfRowsCombobox' attribute value.
     * 
     * @param defaultSPNoOfRowsComboboxParam , may be null.
     * </pre>
     */
    public final void setDefaultSPNoOfRowsCombobox(final JComboBox defaultSPNoOfRowsComboboxParam) {
        defaultSPNoOfRowsCombobox = defaultSPNoOfRowsComboboxParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPCopyButton' attribute value.
     * 
     * @return defaultSPCopyButton , null if not found.
     * </pre>
     */
    public final JButton getDefaultSPCopyButton() {
        return defaultSPCopyButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'confirmStandbyPriorityDTO' attribute value.
     * 
     * @return confirmStandbyPriorityDTO , null if not found.
     * </pre>
     */
    public final ConfirmStandbyPriority getConfirmStandbyPriorityDTO() {
        return confirmStandbyPriorityDTO;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'createStandbyPriorityDTO' attribute value.
     * 
     * @return createStandbyPriorityDTO , null if not found.
     * </pre>
     */
    public final CreateStandbyPriority getCreateStandbyPriorityDTO() {
        return createStandbyPriorityDTO;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPCopyButton' attribute value.
     * 
     * @param defaultSPCopyButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPCopyButton(final JButton defaultSPCopyButtonParam) {
        defaultSPCopyButton = defaultSPCopyButtonParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPViewEditDescButton' attribute value.
     * 
     * @return defaultSPViewEditDescButton , null if not found.
     * </pre>
     */
    public final JButton getDefaultSPViewEditDescButton() {
        return defaultSPViewEditDescButton;
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
     * Set the 'defaultSPViewEditDescButton' attribute value.
     * 
     * @param defaultSPViewEditDescButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPViewEditDescButton(final JButton defaultSPViewEditDescButtonParam) {
        defaultSPViewEditDescButton = defaultSPViewEditDescButtonParam;
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

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'searchSPAirlinesLOV' attribute value.
     * 
     * @return searchSPAirlinesLOV , null if not found.
     * </pre>
     */
    public final NGAFMultiSelectLOVComponent getSearchSPAirlinesLOV() {
        return searchSPAirlinesLOV;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'searchSPAirlinesLOV' attribute value.
     * 
     * @param searchSPAirlinesLOVParam , may be null.
     * </pre>
     */
    public final void setSearchSPAirlinesLOV(final NGAFMultiSelectLOVComponent searchSPAirlinesLOVParam) {
        searchSPAirlinesLOV = searchSPAirlinesLOVParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'parentPanelDSearchTab' attribute value.
     * 
     * @return parentPanelDSearchTab , null if not found.
     * </pre>
     */

    public final JPanel getParentPanelDSearchTab() {
        return parentPanelDSearchTab;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'parentPanelDSearchTab' attribute value.
     * 
     * @param parentPanelDSearchTabParam , may be null.
     * </pre>
     */
    public final void setParentPanelDSearchTab(final JPanel parentPanelDSearchTabParam) {
        parentPanelDSearchTab = parentPanelDSearchTabParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'confirmStandbyPriorityDTO' attribute value.
     * 
     * @param confirmStandbyPriorityDTOParam , may be null.
     * </pre>
     */
    public final void setConfirmStandbyPriorityDTO(final ConfirmStandbyPriority confirmStandbyPriorityDTOParam) {
        confirmStandbyPriorityDTO = confirmStandbyPriorityDTOParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'createStandbyPriorityDTO' attribute value.
     * 
     * @param createStandbyPriorityDTOParam , may be null.
     * </pre>
     */
    public final void setCreateStandbyPriorityDTO(final CreateStandbyPriority createStandbyPriorityDTOParam) {
        createStandbyPriorityDTO = createStandbyPriorityDTOParam;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getter for StandbyPriorityValidator.
     * 
     * @return
     * </pre>
     */
    public final StandbyPriorityValidator getStandbyPriorityValidator() {
        return standbyPriorityValidator;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * setter for StandbyPriorityValidator.
     * 
     * @param standbyPriorityValidatorParam , not null.
     * </pre>
     */
    public final void setStandbyPriorityValidator(final StandbyPriorityValidator standbyPriorityValidatorParam) {
        standbyPriorityValidator = standbyPriorityValidatorParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPUpdateButton' attribute value.
     * 
     * @return defaultSPUpdateButton , null if not found.
     * </pre>
     */
    public final JButton getDefaultSPUpdateButton() {
        return defaultSPUpdateButton;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPUpdateButton' attribute value.
     * 
     * @param defaultSPUpdateButtonParam , may be null.
     * </pre>
     */
    public final void setDefaultSPUpdateButton(final JButton defaultSPUpdateButtonParam) {
        defaultSPUpdateButton = defaultSPUpdateButtonParam;
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
     * 
     * <pre>
     * <b>Description : </b>
     * getManageStandbyPriorityProxy.
     * 
     * @return manageStandbyPriorityProxy , never null.
     * </pre>
     */
    public ManageStandbyPriority getManageStandbyPriorityProxy() {
        return manageStandbyPriorityProxy;
    }
    
    /**
     * 
     * <pre>
     * <b>Description : </b>
     * setManageStandbyPriorityProxy.
     * 
     * @param manageStandbyPriorityProxyParam , not null.
     * </pre>
     */
    public void setManageStandbyPriorityProxy(final ManageStandbyPriority manageStandbyPriorityProxyParam) {
        manageStandbyPriorityProxy = manageStandbyPriorityProxyParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'manageStandbyPriorityTabbedPane' attribute value.
     * 
     * @return manageStandbyPriorityTabbedPane , null if not found.
     * </pre>
     */
    public JTabbedPane getManageStandbyPriorityTabbedPane() {
        return manageStandbyPriorityTabbedPane;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'manageStandbyPriorityTabbedPane' attribute value.
     * 
     * @param manageStandbyPriorityTabbedPaneParam , may be null.
     * </pre>
     */
    public void setManageStandbyPriorityTabbedPane(final JTabbedPane manageStandbyPriorityTabbedPaneParam) {
        manageStandbyPriorityTabbedPane = manageStandbyPriorityTabbedPaneParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'defaultSPPagination' attribute value.
     * 
     * @return defaultSPPagination , null if not found.
     * </pre>
     */
    
    public SITAGUIPagination getDefaultSPPagination() {
        return defaultSPPagination;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'defaultSPPagination' attribute value.
     * 
     * @param defaultSPPaginationParam , may be null.
     * </pre>
     */
    public void setDefaultSPPagination(final SITAGUIPagination defaultSPPaginationParam) {
        defaultSPPagination = defaultSPPaginationParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'deleteStanbyPriorityValidator' attribute value.
     * 
     * @return deleteStanbyPriorityValidator , null if not found.
     * </pre>
     */
    
    public DeleteStanbyPriorityValidator getDeleteStanbyPriorityValidator() {
        return deleteStanbyPriorityValidator;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'deleteStanbyPriorityValidator' attribute value.
     * 
     * @param deleteStanbyPriorityValidatorParam , may be null.
     * </pre>
     */
    public void setDeleteStanbyPriorityValidator(final DeleteStanbyPriorityValidator deleteStanbyPriorityValidatorParam) {
        deleteStanbyPriorityValidator = deleteStanbyPriorityValidatorParam;
    }

}
