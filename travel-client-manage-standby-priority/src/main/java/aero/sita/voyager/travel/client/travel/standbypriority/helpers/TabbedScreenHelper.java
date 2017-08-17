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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.swixml.adaptor.NGAFSwingEngine;
import aero.sita.csp.gui.thickclient.util.SITAGUIServiceUtil;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is to render the Tab.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-06 09:53:42 AM $
 * @author $Author: bikoo.changmai $ 
 * </pre>
 */
public class TabbedScreenHelper {
    
    /**
     * Logger Instance.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(TabbedScreenHelper.class);
    /**
     * constant 990.
     */
    private static final int NINE_HUNDRED_NINTY = 990;
    /**
     * constant 520.
     */
    private static final int FIVE_HUNDRED_TWENTY = 520;
    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;
   
    /**
     * NGAFSwingEngine.
     */
    private NGAFSwingEngine swingEngine = new NGAFSwingEngine();
    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to render the Tab.
     * 
     * @param tabsPanel , not null.
     * </pre>
     */
    public void renderManageStandbyPriorityTab(final JPanel tabsPanel) {
        TabbedScreenHelper.LOGGER.debug("Entered TabbedScreenHelper.renderManageStandbyPriorityTab() ");

        final JTabbedPane tabbedPaneStandbyPriority = new JTabbedPane();
        tabbedPaneStandbyPriority.add("Default standby priority", createDefaultStandbyPriorityTab());
        tabbedPaneStandbyPriority.add("Search", createSearchStandbyPriorityTab());
        tabbedPaneStandbyPriority.setPreferredSize(new Dimension(NINE_HUNDRED_NINTY , FIVE_HUNDRED_TWENTY));

        tabsPanel.setLayout(new FlowLayout());
        tabsPanel.add(tabbedPaneStandbyPriority, FlowLayout.LEFT);
        tabbedPaneStandbyPriority.revalidate();
        tabbedPaneStandbyPriority.repaint();

        TabbedScreenHelper.LOGGER.debug("Exit TabbedScreenHelper.renderManageStandbyPriorityTab() ");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to render the create default priority tab.
     * 
     * @return
     * </pre>
     */
    private JPanel createDefaultStandbyPriorityTab() {
        return (JPanel) renderLayoutXML("/default-standby-priority-layout.xml");
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to render the search standby priority tab.
     * 
     * @return
     * </pre>
     */
    private JPanel createSearchStandbyPriorityTab() {
        return (JPanel) renderLayoutXML("/search-standby-priority-layout.xml");
    }

    /**
     * Method to render an XML using SwiXML framework.
     * 
     * <pre>
     * <b>Description : </b>
     * 
     * 
     * @param layoutXML , not null.
     * @return JComponent , never null.
     * </pre>
     */
    private JComponent renderLayoutXML(final String layoutXML) {
        JComponent comp = null;

        try {
            final URL layoutURL = SITAGUIServiceUtil.getResource(layoutXML, TabbedScreenHelper.class, null);
            comp = (JComponent) swingEngine.render(layoutURL);
            comp.setVisible(true);
        }
        catch (final Exception ex) {
            LOGGER.error("Error in TabbedScreenHelper.renderLayoutXML " + ex.getMessage());
        }

        return comp;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'controller' attribute value.
     * 
     * @return controller , null if not found.
     * </pre>
     */

    public StandbyPriorityController getController() {
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
    public void setController(final StandbyPriorityController controllerParam) {
        this.controller = controllerParam;
    }
}
