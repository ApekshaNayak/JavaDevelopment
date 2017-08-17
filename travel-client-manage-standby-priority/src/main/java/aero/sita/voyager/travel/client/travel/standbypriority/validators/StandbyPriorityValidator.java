/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.validators;

import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.thickclient.component.lov.NGAFMultiSelectLOVComponent;
import aero.sita.csp.gui.thickclient.component.lov.SITAGUILOVComponent;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.listeners.AddDefaultStandbyPriorityListener;
import aero.sita.voyager.travel.common.interfaces.exception.TravelException;

/**
 * 
 * <pre>
 * <b>Description : </b>
 * StandbyPriorityValidator Class which does validation for Standby Priority.
 * 
 * @version $Revision: 1 $ $Date: 2011-01-07 10:18:39 AM $
 * @author $Author: rajesh.choudhary $
 * </pre>
 */
public class StandbyPriorityValidator {
    /**
     * Logger instance for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
         .getLogger(StandbyPriorityValidator.class);

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * constant for PRIORITY_CODE_MIN_LEN.
     */
    private static final int PRIORITY_CODE_MIN_LEN = 2;

    /**
     * constant for PRIORITY_CODE_MAX_LEN.
     */
    private static final int PRIORITY_CODE_MAX_LEN = 20;

    /**
     * constant for PRIORITY_DESC_MAX_LEN.
     */
    private static final int PRIORITY_DESC_MAX_LEN = 100;

    /**
     * Constant TWO.
     */
    private static final int TWO = 2;

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
     * COLUMN_VALUE_EIGHT.
     */
    private static final int COLUMN_VALUE_EIGHT = 8;

    /**
     * COLUMN_VALUE_TEN.
     */
    private static final int COLUMN_VALUE_TEN = 10;

    /**
     * COLUMN_VALUE_TWELVE.
     */
    private static final int COLUMN_VALUE_TWELVE = 12;

    /**
     * SUBSCRIBER_MIN_LEN.
     */
    private static final int SUBSCRIBER_MIN_LEN = 4;

    /**
     * SUBSCRIBER_MAX_LEN.
     */
    private static final int SUBSCRIBER_MAX_LEN = 5;

    /**
     * AIRLINE_MAX.
     */
    private static final int AIRLINE_MAX = 5;

    /**
     * Three.
     */
    private static final int THREE = 3;

    /**
     * controller.
     */
    private StandbyPriorityController controller;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate standbyPriority.
     * 
     * @param dm , not null.
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    public final boolean validateStandbyPriority(final DefaultTableModel dm,
        final NGAFStandardTable defaultSPStandardTable) {
        boolean isValid;

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, null);
        }

        isValid = validateSPTable(dm);

        if (isValid) {
            for (int row = 0; row < dm.getRowCount(); row++) {
                String prorityStatusCode = dm.getValueAt(row, COLUMN_VALUE_TWO).toString();
                String priorityStatusDesc = dm.getValueAt(row, COLUMN_VALUE_THREE).toString();
                if (!(prorityStatusCode.trim().length() > 0) && !(priorityStatusDesc.trim().length() > 0)) {
                    continue;
                }
                if (isValid) {
                    isValid = validateSPMandatoryFields(dm.getValueAt(row, COLUMN_VALUE_TWO).toString(),
                        dm.getValueAt(row, COLUMN_VALUE_THREE).toString(), dm.getValueAt(row, COLUMN_VALUE_FOUR)
                            .toString(), dm.getValueAt(row, COLUMN_VALUE_FIVE).toString(), dm, defaultSPStandardTable,
                        row);
                }

                if (!isValid) {
                    break;
                }
            }

            if (isValid) {
                isValid = validateDuplicatePriorityCode(dm);
            }
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Default Standby Priority table to check if atleast one row is entered.
     * @param dm , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateSPTable(final DefaultTableModel dm) {
        boolean isValid = true;
        int count = 0;
        int updateCount = 0;

        for (int row = 0; row < dm.getRowCount(); row++) {
            String priorityStatusCode = dm.getValueAt(row, COLUMN_VALUE_TWO).toString();
            String priorityStatusDesc = dm.getValueAt(row, COLUMN_VALUE_THREE).toString();
            String checkPAD = dm.getValueAt(row, COLUMN_VALUE_FOUR).toString();
            String dateOfJoining = dm.getValueAt(row, COLUMN_VALUE_FIVE).toString();

            if ((checkPAD.equalsIgnoreCase(StandbyPriorityConstants.YES_VALUE) || checkPAD.equalsIgnoreCase("true"))
                && dateOfJoining.equalsIgnoreCase(StandbyPriorityConstants.DOJ_OPTIONAL)
                && !isNotEmpty(priorityStatusCode) && !isNotEmpty(priorityStatusDesc)) {
                count++;
            }

            if (dm.getValueAt(row, StandbyPriorityConstants.FIFTEEN) != null) {
                updateCount++;
            }
        }

        if (updateCount == 0 && (count == 0 || count == dm.getRowCount())) {
            /*
             * for (int index = 2; index <= 14; index++) {
             * 
             * DcsMessagePanelHandler.handleClientErrorMessage("CKI-10833",
             * "standardTable", controller.getDefaultSPStandardTable(), true, 0,
             * index); }
             */
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10833");
            isValid = false;
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Subcriber and Airline.
     * @return isValid, never null.
     * </pre>
     */
    public boolean validateSearchSP() {
        boolean isValid;

        isValid = validateSubscriber();

        if (isValid) {
            isValid = validateAirlineCode();
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Subcriber.
     * @return isValid, never null.
     * </pre>
     */
    private boolean validateSubscriber() {
        boolean isValid = true;
        final String subscriberCode = controller.getSearchSPSubscriberLOV().getSelectedResult().toUpperCase();
        final SITAGUILOVComponent subscriberLOV = controller.getSearchSPSubscriberLOV();
        String searchSPSubscriberLOV = subscriberLOV.getSelectedResult();

        if (!isNotEmpty(searchSPSubscriberLOV)) {
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10006", controller.getSearchSPSubscriberLOV());
            isValid = false;
        }
        else if (!checkStringLengthRange(searchSPSubscriberLOV.trim(), SUBSCRIBER_MIN_LEN, SUBSCRIBER_MAX_LEN)) {
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10269", controller.getSearchSPSubscriberLOV());
            isValid = false;
        }
        else if (controller.getCommonUtilitiesGateWay().getAllSubsciberList(subscriberCode).isEmpty()
            || controller.getCommonUtilitiesGateWay().getAllSubsciberList(subscriberCode).size() == 0) {
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10269", controller.getSearchSPSubscriberLOV());
            isValid = false;
        }
        try {

            if (isValid) {
                isValid = controller.getCommonUtilitiesGateWay().validateSubscriber(subscriberCode);
                if (!isValid) {
                    DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10269", controller.getSearchSPSubscriberLOV());
                }
            }
        }
        catch (TravelException ex) {
            LOGGER.info("validateSubscriber Exception");
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Airline.
     * @return isValid, never null.
     * </pre>
     */
    private boolean validateAirlineCode() {
        boolean isValid = true;

        if (controller.getSearchSPAirlinesLOV() != null) {
            final NGAFMultiSelectLOVComponent airlineLOV = controller.getSearchSPAirlinesLOV();

            if (airlineLOV.getSearchResult() != null) {
                final String[] airlineCodes = airlineLOV.getSearchResult();
                String airlineCode;
                int flag = 0;

                if ((airlineCodes != null) && (airlineCodes.length > 0)) {
                    if (airlineCodes.length > AIRLINE_MAX) {
                        DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10887",
                            controller.getSearchSPAirlinesLOV());
                        isValid = false;
                        return isValid;
                    }

                    for (int i = 0; i < airlineCodes.length; i++) {
                        if (airlineCodes[i] != null) {
                            airlineCode = airlineCodes[i].trim();

                            for (int index = i + 1; index < airlineCodes.length; index++) {
                                if (airlineCode.contentEquals(airlineCodes[index])) {
                                    DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10885",
                                        controller.getSearchSPAirlinesLOV());
                                    isValid = false;
                                    return isValid;
                                }

                            }

                            if (i > 0) {
                                for (int j = 0; j < i; j++) {
                                    if (airlineCodes[j] != null) {
                                        if (airlineCode.equalsIgnoreCase(airlineCodes[j])) {
                                            flag = 1;
                                            break;
                                        }
                                    }
                                }

                                if (flag == 0) {
                                    if (!isValidAirlineCode(airlineCode)
                                        || !checkStringLengthRange(airlineCode, 1, THREE)) {
                                        DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10002",
                                            controller.getSearchSPAirlinesLOV());
                                        isValid = false;
                                        break;
                                    }
                                }
                            }
                            else {
                                if (!isValidAirlineCode(airlineCode) 
                                    || !checkStringLengthRange(airlineCode, 1, THREE)) {
                                    DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10002",
                                        controller.getSearchSPAirlinesLOV());
                                    isValid = false;
                                    break;
                                }
                            }
                         /*   for (i=0;i < airlineCodes.length; i++){
                                for (int j=0; j<airlineCodes.length; j++){
                                    if(airlineCodes[i].equals(airlineCodes[j])&& i!=j){
                                        return true;
                                    }
                                }
                            }*/
                        }

                    }
                }
                else {
                    DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10886", controller.getSearchSPAirlinesLOV());
                    isValid = false;
                    return isValid;

                }
            }
        }

        else {
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10886", controller.getSearchSPAirlinesLOV());
            isValid = false;
            return isValid;

        }
        return isValid;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to validate airline code.
     * @param airlineCode , not null
     * @return boolean , not null
     * </pre>
     */
    private boolean isValidAirlineCode(final String airlineCode) {
        boolean isValid = true;

        if (!airlineCode.matches("[0-9a-zA-Z]*") || airlineCode.matches("[0-9]*")) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate for Mandatory fields.
     * @param priorityStatusCode , not null.
     * @param priorityStatusDesc , not null.
     * @param checkPAD , not null.
     * @param dateOfJoining , not null.
     * @param dm , not null.
     * @param defaultSPStandardTable , not null.
     * @param row , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateSPMandatoryFields(final String priorityStatusCode, final String priorityStatusDesc,
        final String checkPAD, final String dateOfJoining, final DefaultTableModel dm,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (checkPAD.equalsIgnoreCase(StandbyPriorityConstants.NO_VALUE) || checkPAD.equalsIgnoreCase("false")
            || dateOfJoining.equalsIgnoreCase(StandbyPriorityConstants.DOJ_NOT_REQUIRED)
            || dm.getValueAt(row, StandbyPriorityConstants.FIFTEEN) != null) {
            isValid = validatePriorityStatusCode(priorityStatusCode, defaultSPStandardTable, row);

            if (isValid) {
                isValid = validatePriorityStatusDesc(priorityStatusDesc, defaultSPStandardTable, row);
            }
        }

        if (isValid) {
            if (isNotEmpty(priorityStatusDesc)) {
                isValid = validatePriorityStatusCode(priorityStatusCode, defaultSPStandardTable, row);
            }
        }

        if (isValid) {
            if (isNotEmpty(priorityStatusCode)) {
                isValid = validatePriorityStatusDesc(priorityStatusDesc, defaultSPStandardTable, row);

                if (isValid) {
                    isValid = validateBookedPriorityValue(dm.getValueAt(row, COLUMN_VALUE_SIX).toString(),
                        defaultSPStandardTable, row);
                }

                if (isValid) {
                    isValid = validateNoRecPriorityValue(dm.getValueAt(row, COLUMN_VALUE_EIGHT).toString(),
                        defaultSPStandardTable, row);
                }

                if (isValid) {
                    isValid = validateWaitlistPriorityValue(dm.getValueAt(row, COLUMN_VALUE_TEN).toString(),
                        defaultSPStandardTable, row);
                }

                if (isValid) {
                    isValid = validateOpenPriorityValue(dm.getValueAt(row, COLUMN_VALUE_TWELVE).toString(),
                        defaultSPStandardTable, row);
                }
            }
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate for duplicate Priority Status Code.
     * @param dm , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateDuplicatePriorityCode(final DefaultTableModel dm) {
        boolean isValid = true;

        for (int row = 0; row < dm.getRowCount(); row++) {
            String priorityStatusCode = dm.getValueAt(row, COLUMN_VALUE_TWO).toString();
          
            for (int duplicateRow = row + 1; duplicateRow < dm.getRowCount(); duplicateRow++) {
                String ps = dm.getValueAt(duplicateRow, COLUMN_VALUE_TWO).toString();

                if (priorityStatusCode != null && ps != null && !("").equals(priorityStatusCode.trim())
                    && !("").equals(ps.trim()) && priorityStatusCode.equalsIgnoreCase(ps)) {
                    DcsMessagePanelHandler.handleClientErrorMessage("CKI-10803", "standardTable",
                        controller.getDefaultSPStandardTable(), true, duplicateRow, COLUMN_VALUE_TWO);
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                break;
            }
        }

        return isValid;
    }

    /*
     * private boolean validateDuplicatePriorityCode(final DefaultTableModel dm)
     * { boolean isValid = true;
     * 
     * for (int row = 0; row < dm.getRowCount(); row++) { String
     * priorityStatusCode = dm.getValueAt(row, COLUMN_VALUE_TWO).toString();
     * 
     * for (int duplicateRow = row + 1; duplicateRow < dm.getRowCount();
     * duplicateRow++) { String ps = dm.getValueAt(duplicateRow,
     * COLUMN_VALUE_TWO).toString();
     * 
     * if (priorityStatusCode != null && ps != null &&
     * !("").equals(priorityStatusCode.trim()) && !("").equals(ps.trim()) &&
     * priorityStatusCode.equalsIgnoreCase(ps)) {
     * DcsMessagePanelHandler.handleClientErrorMessage("CKI-10803",
     * "standardTable", controller.getDefaultSPStandardTable(), true,
     * duplicateRow, COLUMN_VALUE_TWO); isValid = false; break; } }
     * 
     * if (!isValid) { break; } }
     * 
     * return isValid; }
     *//**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Priority Status Code.
     * @param priorityStatusCode , not null.
     * @param row , not null.
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validatePriorityStatusCode(final String priorityStatusCode,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(priorityStatusCode)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10802", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_TWO);
         
            isValid = false;
        }
        else {
            if (!checkStringLengthRange(priorityStatusCode.trim(), PRIORITY_CODE_MIN_LEN, PRIORITY_CODE_MAX_LEN)) {
                NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
                DcsMessagePanelHandler.handleClientErrorMessage("CKI-10804", "standardTable", defaultSPStandardTable,
                    true, row, COLUMN_VALUE_TWO);

                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Priority Status Description.
     * @param priorityStatusDesc , not null.
     * @param spStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    public boolean validatePriorityStatusDesc(final String priorityStatusDesc,
            final NGAFStandardTable spStandardTable) {
        boolean isValid = true;
        if (!isNotEmpty(priorityStatusDesc)) {
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10834");
            isValid = false;
        }
        else {
            if (!checkStringLength(priorityStatusDesc.trim(), PRIORITY_DESC_MAX_LEN)) {
                DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10836");
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Priority Status Description.
     * @param priorityStatusDesc , not null.
     * @param row , not null.
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validatePriorityStatusDesc(final String priorityStatusDesc,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(priorityStatusDesc)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10834", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_THREE);

            isValid = false;
        }
        else {
            if (!checkStringLength(priorityStatusDesc.trim(), PRIORITY_DESC_MAX_LEN)) {
                NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);

                DcsMessagePanelHandler.handleClientErrorMessage("CKI-10836", "standardTable", defaultSPStandardTable,
                    true, row, COLUMN_VALUE_THREE);

                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Booked Priority Value.
     * @param bookedPriorityValue , not null.
     * @param row , not null.
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateBookedPriorityValue(final String bookedPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(bookedPriorityValue)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10805", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_SIX);

            isValid = false;
        }
        else if (!isDT4(bookedPriorityValue) || (!checkStringLength(bookedPriorityValue.trim(), TWO))) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10806", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_SIX);

            isValid = false;

        }
        else if (bookedPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || bookedPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10806", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_SIX);

            isValid = false;
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate NoRec Priority Value.
     * @param noRecPriorityValue , not null.
     * @param defaultSPStandardTable , not null.
     * @param row , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateNoRecPriorityValue(final String noRecPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(noRecPriorityValue)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10811", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_EIGHT);

            isValid = false;
        }
        else if (!isDT4(noRecPriorityValue) || (!checkStringLength(noRecPriorityValue.trim(), TWO))) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10812", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_EIGHT);

            isValid = false;

        }
        else if (noRecPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || noRecPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10812", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_EIGHT);

            isValid = false;
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Waitlist Priority Value.
     * @param waitlistPriorityValue , not null.
     * @param row , not null.
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateWaitlistPriorityValue(final String waitlistPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(waitlistPriorityValue)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10820", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_TEN);

            isValid = false;
        }
        else if (!isDT4(waitlistPriorityValue) || (!checkStringLength(waitlistPriorityValue.trim(), TWO))) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10821", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_TEN);

            isValid = false;
        }
        else if (waitlistPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || waitlistPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10821", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_TEN);

            isValid = false;
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Open Priority Value.
     * @param openPriorityValue , not null.
     * @param row , not null.
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateOpenPriorityValue(final String openPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(openPriorityValue)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10822", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_TWELVE);

            isValid = false;
        }

        else if (!isDT4(openPriorityValue) || (!checkStringLength(openPriorityValue.trim(), TWO))) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10823", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_TWELVE);

            isValid = false;

        }
        else if (openPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || openPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
            NGAFContextUtil.addToScreenContext(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, row);
            DcsMessagePanelHandler.handleClientErrorMessage("CKI-10823", "standardTable", defaultSPStandardTable, true,
                row, COLUMN_VALUE_TWELVE);

            isValid = false;
        }

        return isValid;
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
     * Method to validate a number as per DT4 data type specification.
     * 
     * @param value , not null.
     * @return boolean
     */
    private static boolean isDT4(final String value) {
        boolean isValid = true;

        if (!value.matches("[0-9]*")) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate a string as per DT3 data type specification.
     * 
     * @param value , not null
     * @param min , not null
     * @param max , not null
     * @return boolean
     * </pre>
     */
    private static boolean checkStringLengthRange(final String value, final int min, final int max) {
        boolean isValid = true;

        if ((value.length() < min) || (value.length() > max)) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to check the length for the field.
     * 
     * @param obj , not null
     * @param length , not null
     * @return boolean , never null.
     * </pre>
     */
    private boolean checkStringLength(final String obj, final int length) {
        boolean isValid = true;

        if (obj != null) {
            if (obj.length() > length) {
                isValid = false;
            }
        }
        else {
            isValid = false;
        }

        return isValid;
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
