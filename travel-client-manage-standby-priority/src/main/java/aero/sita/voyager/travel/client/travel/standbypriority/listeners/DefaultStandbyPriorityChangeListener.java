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

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.common.ScreenRegistry;
import aero.sita.csp.gui.thickclient.common.SystemConstants;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.csp.gui.thickclient.coreapi.base.ModuleDO;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.ngafoundation.client.parentframe.util.NGAFParentControllerUtil;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Default Standby Priority Chnage Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:15:51 AM $
 * @author $Author: ravi.powli $ 
 * </pre>
 */
public class DefaultStandbyPriorityChangeListener implements ChangeListener {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(CancelDescriptionListener.class);
    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * <pre>
     * <b>Description : </b>
     * Change Lister Action Performed .
     * 
     * @param actionEvent , not null.
     * </pre>
     */
    public void stateChanged(final ChangeEvent actionEvent) {
        LOGGER.debug("DefaultStandbyPriorityChangeListener :stateChanged ");
        if (controller.getManageStandbyPriorityTabbedPane().getSelectedIndex() == 0) {
            DcsMessagePanelHandler.clearAllMessages();
            ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                SystemConstants.SCREEN_REGISTRY);
            ModuleDO module = screenRegistry.getCurrentModule();
            DcsMessagePanelHandler.clearModuleMessgaes(module.getModuleId());
            NGAFParentControllerUtil.getParentController().setScreenPath(StandbyPriorityConstants.DEFAULT_SCREEN_PATH);
        }

        if (controller.getManageStandbyPriorityTabbedPane().getSelectedIndex() == 1) {
            DcsMessagePanelHandler.clearAllMessages();
            ScreenRegistry screenRegistry = (ScreenRegistry) NGAFContextUtil.getApplicationContext().get(
                SystemConstants.SCREEN_REGISTRY);
            ModuleDO module = screenRegistry.getCurrentModule();
            DcsMessagePanelHandler.clearModuleMessgaes(module.getModuleId());
            NGAFParentControllerUtil.getParentController().setScreenPath(StandbyPriorityConstants.SEARCH_TAB_SCREEN_PATH);
        }
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
