/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.validators;

import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.dcs.client.common.base.msgpanelhandler.DcsMessagePanelHandler;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;

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
public class CloseStandbyPriorityValidator {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * SUBSCRIBER_MIN_LEN.
     */
    private static final int SUBSCRIBER_MIN_LEN = 1;

    /**
     * SUBSCRIBER_MAX_LEN.
     */
    private static final int SUBSCRIBER_MAX_LEN = 5;

    /**
     * AIRLINECODE_LENGTH_TWO.
     */
    private static final int AIRLINECODE_LENGTH_TWO = 2;

    /**
     * AIRLINECODE_LENGTH_THREE.
     */
    private static final int AIRLINECODE_LENGTH_THREE = 3;

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
     * THREE.
     */
    private static final int THREE = 3;

    /**
     * Reference for StandbyPriorityDetailsController.
     */
    private StandbyPriorityDetailsController controller;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate standbyPriority.
     * 
     * @param dm , not null.
     * @param spStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    public final boolean validateStandbyPriority(final DefaultTableModel dm, final NGAFStandardTable spStandardTable) {
        boolean isValid;

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.SP_MANDATORY_ROW) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.SP_MANDATORY_ROW, null);
        }

        isValid = validateSubscriber();

        if (isValid) {
            isValid = validateAirline();
        }

        if (isValid) {
            isValid = validateSPTable(dm);
        }

        if (isValid) {
            for (int row = 0; row < dm.getRowCount(); row++) {
                String prorityStatusCode = dm.getValueAt(row, COLUMN_VALUE_TWO).toString();
                if (!(prorityStatusCode.trim().length() > 0)) {
                    continue;
                }
                if (isValid) {
                    isValid = validateSPMandatoryFields(dm.getValueAt(row, COLUMN_VALUE_TWO).toString(),
                        dm.getValueAt(row, COLUMN_VALUE_THREE).toString(), dm.getValueAt(row, COLUMN_VALUE_FOUR)
                            .toString(), dm.getValueAt(row, COLUMN_VALUE_FIVE).toString(), dm, spStandardTable, row);
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
     * Method to validate Standby Priority table to check if atleast one row is entered.
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

        // if (updateCount == 0 && (count == 0 || count == dm.getRowCount())) {
        if (updateCount == 0 && count == dm.getRowCount()) {
            isValid = false;
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
        final String subscriberCode = controller.getNewSPSubscriberLOV().getSearchField().getText();

        if (!isNotEmpty(subscriberCode)) {
            isValid = false;
        }
        else {
            if (!checkStringLengthRange(subscriberCode.trim(), SUBSCRIBER_MIN_LEN, SUBSCRIBER_MAX_LEN)) {
                DcsMessagePanelHandler.handleClientSideErrorMsg("Invalid Subscriber");
                isValid = false;
            }
        }

        return isValid;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Airline.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateAirline() {
        boolean isValid = true;
        String airlineNumber = null;
        final String airlineCode = controller.getNewSPAirlineLOV().getSearchField().getText();

        if (!isNotEmpty(airlineCode)) {
            DcsMessagePanelHandler.handleClientSideErrorMsg("CKI-10019");
            isValid = false;
        }
        else {
            if (!isValidAirlineCode(airlineCode) || !checkStringLengthRange(airlineCode, 1, THREE)) {
                isValid = false;
            }

            if (airlineCode.trim().length() == AIRLINECODE_LENGTH_TWO) {
                airlineNumber = populateAirlineNumber(airlineCode, true);
            }

            if (airlineCode.trim().length() == AIRLINECODE_LENGTH_THREE) {
                airlineNumber = populateAirlineNumber(airlineCode, false);
            }

            if (!isNotEmpty(airlineNumber)) {
                isValid = false;
            }
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
     * @param spStandardTable , not null.
     * @param row , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateSPMandatoryFields(final String priorityStatusCode, 
        final String priorityStatusDesc,
        final String checkPAD, final String dateOfJoining, final DefaultTableModel dm,
        final NGAFStandardTable spStandardTable, final int row) {
        boolean isValid = true;

        if (checkPAD.equalsIgnoreCase(StandbyPriorityConstants.NO_VALUE) || checkPAD.equalsIgnoreCase("false")
            || dateOfJoining.equalsIgnoreCase(StandbyPriorityConstants.DOJ_NOT_REQUIRED)
            || dm.getValueAt(row, StandbyPriorityConstants.FIFTEEN) != null) {
            isValid = validatePriorityStatusCode(priorityStatusCode, spStandardTable, row);

            if (isValid) {
                isValid = validatePriorityStatusDesc(priorityStatusDesc, spStandardTable, row);
            }
        }

        if (isValid) {
            if (isNotEmpty(priorityStatusDesc)) {
                isValid = validatePriorityStatusCode(priorityStatusCode, spStandardTable, row);
            }
        }

        if (isValid) {
            if (isNotEmpty(priorityStatusCode)) {
                isValid = validatePriorityStatusDesc(priorityStatusDesc, spStandardTable, row);

                if (isValid) {
                    isValid = validateBookedPriorityValue(dm.getValueAt(row, COLUMN_VALUE_SIX).toString(),
                        spStandardTable, row);
                }

                if (isValid) {
                    isValid = validateNoRecPriorityValue(dm.getValueAt(row, COLUMN_VALUE_EIGHT).toString(),
                        spStandardTable, row);
                }

                if (isValid) {
                    isValid = validateWaitlistPriorityValue(dm.getValueAt(row, COLUMN_VALUE_TEN).toString(),
                        spStandardTable, row);
                }

                if (isValid) {
                    isValid = validateOpenPriorityValue(dm.getValueAt(row, COLUMN_VALUE_TWELVE).toString(),
                        spStandardTable, row);
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

            for (int j = row + 1; j < dm.getRowCount(); j++) {
                String ps = dm.getValueAt(j, COLUMN_VALUE_TWO).toString();

                if (priorityStatusCode != null && ps != null && !("").equals(priorityStatusCode.trim())
                    && !("").equals(ps.trim()) && priorityStatusCode.equalsIgnoreCase(ps)) {
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

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Method to validate Priority Status Code.
     * @param priorityStatusCode , not null.
     * @param row , not null.
     * @param spStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validatePriorityStatusCode(final String priorityStatusCode,
        final NGAFStandardTable spStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(priorityStatusCode)) {
            isValid = false;
        }
        else {
            if (!checkStringLengthRange(priorityStatusCode.trim(), 
                PRIORITY_CODE_MIN_LEN, PRIORITY_CODE_MAX_LEN)) {
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
     * @param spStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validatePriorityStatusDesc(final String priorityStatusDesc,
        final NGAFStandardTable spStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(priorityStatusDesc)) {

            isValid = false;
        }
        else {
            if (!checkStringLength(priorityStatusDesc.trim(), PRIORITY_DESC_MAX_LEN)) {
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
     * @return isValid, never null.
     * </pre>
     */
    public boolean validatePriorityStatusDesc(final String priorityStatusDesc,
            final NGAFStandardTable spStandardTable) {
        boolean isValid = true;

        if (!isNotEmpty(priorityStatusDesc)) {
            isValid = false;
        }
        else {
            if (!checkStringLength(priorityStatusDesc.trim(), PRIORITY_DESC_MAX_LEN)) {
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
     * @param spStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateBookedPriorityValue(final String bookedPriorityValue,
        final NGAFStandardTable spStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(bookedPriorityValue)) {
            isValid = false;
        }
        else if (!isDT4(bookedPriorityValue) || (!checkStringLength(bookedPriorityValue.trim(), TWO))) {
            isValid = false;

        }
        else if (bookedPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || bookedPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
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
     * @param spStandardTable , not null.
     * @param row , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateNoRecPriorityValue(final String noRecPriorityValue,
        final NGAFStandardTable spStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(noRecPriorityValue)) {
            isValid = false;

        }
        else if (!isDT4(noRecPriorityValue) || (!checkStringLength(noRecPriorityValue.trim(), TWO))) {
            isValid = false;

        }
        else if (noRecPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || noRecPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
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
     * @param spStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateWaitlistPriorityValue(final String waitlistPriorityValue,
        final NGAFStandardTable spStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(waitlistPriorityValue)) {

            isValid = false;
        }
        else if (!isDT4(waitlistPriorityValue) || (!checkStringLength(waitlistPriorityValue.trim(), TWO))) {
            isValid = false;
        }
        else if (waitlistPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || waitlistPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
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
     * @param spStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateOpenPriorityValue(final String openPriorityValue, final NGAFStandardTable spStandardTable,
        final int row) {
        boolean isValid = true;

        if (!isNotEmpty(openPriorityValue)) {
            isValid = false;
        }

        else if (!isDT4(openPriorityValue) || (!checkStringLength(openPriorityValue.trim(), TWO))) {
            isValid = false;

        }
        else if (openPriorityValue.equals(StandbyPriorityConstants.ZERO_VALUE)
            || openPriorityValue.equals(StandbyPriorityConstants.ZERO_TWO_DIGIT_VALUE)) {
            isValid = false;
        }

        return isValid;
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
            airlineNumber=null;
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
     * @param text , may be null.
     * @return boolean , never null.
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
     * @return boolean , never null.
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
