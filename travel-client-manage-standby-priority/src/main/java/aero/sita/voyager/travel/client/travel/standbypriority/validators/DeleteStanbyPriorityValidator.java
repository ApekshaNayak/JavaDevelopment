package aero.sita.voyager.travel.client.travel.standbypriority.validators;

import javax.swing.table.DefaultTableModel;

import aero.sita.csp.gui.thickclient.component.lov.NGAFMultiSelectLOVComponent;
import aero.sita.csp.gui.thickclient.component.lov.SITAGUILOVComponent;
import aero.sita.csp.gui.thickclient.component.table.NGAFStandardTable;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;

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

public class DeleteStanbyPriorityValidator {

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
    private static final int SUBSCRIBER_MIN_LEN = 1;

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
        boolean isValid = true;

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW) != null) {
            NGAFContextUtil.getApplicationContext().put(StandbyPriorityConstants.DEFAULT_SP_MANDATORY_ROW, null);
        }

        for (int row = 0; row < dm.getRowCount(); row++) {
            if (dm.getValueAt(row, StandbyPriorityConstants.FIFTEEN) == null) {
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
        }

        if (isValid) {
            isValid = validateDuplicatePriorityCode(dm);
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
        final String SubscriberCode = controller.getSearchSPSubscriberLOV().getSelectedResult();
        final SITAGUILOVComponent subscriberLOV = controller.getSearchSPSubscriberLOV();
        String searchSPSubscriberLOV = subscriberLOV.getSelectedResult();

        if (!isNotEmpty(searchSPSubscriberLOV)) {
            isValid = false;
        }
        else if (!checkStringLengthRange(searchSPSubscriberLOV.trim(), SUBSCRIBER_MIN_LEN, SUBSCRIBER_MAX_LEN)) {
            isValid = false;
        }
        else if (controller.getCommonUtilitiesGateWay().getAllSubsciberList(SubscriberCode).isEmpty()
            || controller.getCommonUtilitiesGateWay().getAllSubsciberList(SubscriberCode).size() == 0) {
            isValid = false;
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
                        isValid = false;
                        return isValid;
                    }

                    for (int i = 0; i < airlineCodes.length; i++) {
                        if (airlineCodes[i] != null) {
                            airlineCode = airlineCodes[i].trim();

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
                                        isValid = false;
                                        break;
                                    }
                                }
                            }
                            else {
                                if (!isValidAirlineCode(airlineCode) || !checkStringLengthRange(airlineCode, 1, THREE)) {
                                    isValid = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                else {

                    isValid = false;
                    return isValid;

                }
            }
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
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validatePriorityStatusCode(final String priorityStatusCode,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
        boolean isValid = true;

        if (!isNotEmpty(priorityStatusCode)) {

            isValid = false;
        }
        else {
            if (!checkStringLengthRange(priorityStatusCode.trim(), PRIORITY_CODE_MIN_LEN, PRIORITY_CODE_MAX_LEN)) {

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
     * @param defaultSPStandardTable , not null.
     * @return isValid, never null.
     * </pre>
     */
    public boolean validatePriorityStatusDesc(final String priorityStatusDesc,
        final NGAFStandardTable defaultSPStandardTable) {
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
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateBookedPriorityValue(final String bookedPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
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
     * @param defaultSPStandardTable , not null.
     * @param row , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateNoRecPriorityValue(final String noRecPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
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
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateWaitlistPriorityValue(final String waitlistPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
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
     * @param defaultSPStandardTable , not null.
     * @return boolean, never null.
     * </pre>
     */
    private boolean validateOpenPriorityValue(final String openPriorityValue,
        final NGAFStandardTable defaultSPStandardTable, final int row) {
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
