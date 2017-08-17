/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants;

/**
 * defines constants to used in the Passenger Standby Priority module.
 * 
 * <pre>
 * <b>Description : </b>
 * defines constants to used in the Passenger Standby Priority module.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-05 10:00:13 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public interface StandbyPriorityConstants {
   /* /**
     * PAD_OPTIONS.
     *//*
    String[] PAD_OPTIONS = { "Yes", "No" };

    *//**
     * DATE_OF_JOINING_OPTIONS.
     *//*
    String[] DATE_OF_JOINING_OPTIONS = { "Optional", "Not required" };*/

    /**
     * NO_OF_ROWS.
     */
    int NO_OF_ROWS = 20;

    /**
     * Add NO_OF_ROWS.
     */
    int ADD_NO_OF_ROWS = 9;

    /**
     * CONFIRM_DELETE_DIALOG_TITLE.
     */
    String CONFIRM_DELETE_DIALOG_TITLE = "Confirm Delete";

    /**
     * CONFIRM_DELETE_DIALOG_MSG.
     */
    String CONFIRM_DELETE_DIALOG_MSG = "Do you want to delete the selected rows? ";

    /**
     * CONFIRM_DELETE_DIALOG_MSG.
     */
    String CONFIRM_DELETE_LAST_RECORD_DIALOG_MSG = "Do you want to delete standby priority?";

    /**
     * CONFIRM_DELETE_DIALOG_MSG.
     */
    String CONFIRM_DELETE = "Are you sure, you want to delete standby priority?  ";

    /**
     * COPY_WARNING_DIALOG_MSG.
     */
    String COPY_WARNING_DIALOG_MSG = 
        "New standby priority tab has some data.\n Do you wish to save data before proceeding?";

    /**
     * CLOSE_WARNING_DIALOG_MSG.
     */
    String CLOSE_WARNING_DIALOG_MSG = "Do you want to save the data before proceeding?";

    /**
     * ERROR_MESSAGE_1.
     */
    String ERROR_MESSAGE_1 = "Please select only one row.";

    /**
     * Delete Error Message.
     */
    String DELETE_ERROR_MSG = "Cannot be deleted as it is the last record in the Default PSPI.";

    /**
     * Delete All Error Message.
     */
    String DELETE_All_ERROR_MSG = "If all the Priority Status Codes are deleted," 
        + "the SITA Default Passenger Standby Priority Information would be used in the future";

    /**
     * Delete All Error Message.
     */
    String DELETE_BUTTON_All_ERROR_MSG = 
        "If standby priority is deleted, then the SITA DEFAULT PSPI would be used in the future”.";

    /**
     * Delete Success message.
     */
    String DELETE_SUCCESS_MSG = "Selected standby priority codes deleted successfully.";

    /**
     * Delete Success message.
     */
    String DELETE_BUTTON_SUCCESS_MSG = "Standby priority codes deleted successfully.";

    /**
     * NEW_STANDBY_PRIORITY_TAB.
     */
    String NEW_STANDBY_PRIORITY_TAB = "New standby priority";

    /**
     * MODULE_ID.
     */
    String MODULE_ID = "standby-priority-details";

    /**
     * DELETE_WARNING_MSG.
     */
    String DELETE_WARNING_MSG = "If all the Priority Status Codes are selected for delete, "
        + "system will delete the existing Passenger Standby Priority Information for the "
        + "Handled Airline and update the system to use the SITA Default "
        + "Passenger Standby Priority Information in the future";

    /**
     * DEFAULT_STANDBY_PRIORITY_RESPONSE.
     */
    String DEFAULT_STANDBY_PRIORITY_RESPONSE = "DEFAULT_STANDBY_PRIORITY_RESPONSE";

    /**
     * SP_RESPONSE_ROWCOUNT.
     */
    String SP_RESPONSE_ROWCOUNT = "SP_RESPONSE_ROWCOUNT";

    /**
     * YES_VALUE.
     */
    String YES_VALUE = "Yes";

    /**
     * NO_VALUE.
     */
    String NO_VALUE = "No";

    /**
     * DOJ_OPTIONAL.
     */
    String DOJ_OPTIONAL = "Optional";

    /**
     * DOJ_NOT_REQUIRED.
     */
    String DOJ_NOT_REQUIRED = "Not required";

    /**
     * SP_MANDATORY_ROW.
     */
    String SP_MANDATORY_ROW = "SP_MANDATORY_ROW";

    /**
     * CREATE_SP_FAILED_ROWS.
     */
    String CREATE_SP_FAILED_ROWS = "CREATE_SP_FAILED_ROWS";

    /**
     * SP_SUBSCRIBER_CODE.
     */
    String SP_SUBSCRIBER_CODE = "SP_SUBSCRIBER_CODE";

    /**
     * SP_AIRLINE_CODE.
     */
    String SP_AIRLINE_CODE = "SP_AIRLINE_CODE";

    /**
     * COPY_DEFAULT.
     */
    String COPY_DEFAULT = "COPY_DEFAULT";

    /**
     * RESET_DEFAULT.
     */
    String RESET_DEFAULT = "RESET_DEFAULT";

    /**
     * RESET_STANBY.
     */
    String RESET_STANBY = "RESET_STANBY";
    /**
     * RESET_SEARCH.
     */
    String RESET_SEARCH = "RESET_SEARCH";

    /**
     * CLOSE.
     */
    String CLOSE = "CLOSE";

    /**
     * ZERO_VALUE.
     */
    String ZERO_VALUE = "0";

    /**
     * ZERO_TWO_DIGIT_VALUE.
     */
    String ZERO_TWO_DIGIT_VALUE = "00";

    /**
     * FIFTEEN.
     */
    int FIFTEEN = 15;

    /**
     * Max number of row in a Standby priority table.
     */
    int MAX_ROW_COUNT = 500;

    /**
     * COPY_DEFAULT_SAVE.
     */
    String COPY_DEFAULT_SAVE = "COPY_DEFAULT_SAVE";

    /**
     * COPY_DEFAULT_DO_NOT_SAVE.
     */
    String COPY_DEFAULT_DO_NOT_SAVE = "COPY_DEFAULT_DO_NOT_SAVE";

    /**
     * STANDBY_PRIORITIES.
     */
    String STANDBY_PRIORITIES = "STANDBY_PRIORITIES";

    /**
     * NOT_PERMITTED.
     */
    String DOJ_NOT_PERMITTED = "NOT_PERMITTED";

    /**
     * COPY_NEW_SAVE.
     */
    String COPY_NEW_SAVE = "COPY_NEW_SAVE";

    /**
     * COPY_NEW_DO_NOT_SAVE.
     */
    String COPY_NEW_DO_NOT_SAVE = "COPY_NEW_DO_NOT_SAVE";

    /**
     * COPY_STANDBY.
     */
    String COPY_STANDBY = "COPY_STANDBY";

    /**
     * Open Search.
     */
    String OPEN_SEARCH = "OPEN_SEARCH";

    /**
     * COPY_SEARCH.
     */
    String COPY_SEARCH = "COPY_SEARCH";

    /**
     * SUBSCRIBER_AIRLINE_NAME.
     */
    String SUBSCRIBER = "SUBSCRIBER_NAME";

    /**
     * COPY_NEW_SAVE_TAB.
     */
    String COPY_NEW_SAVE_TAB = "COPY_NEW_SAVE_TAB";

    /**
     * SEARCH_COPY_NEW_SAVE_TAB.
     */
    String SEARCH_COPY_NEW_SAVE_TAB = "SEARCH_COPY_NEW_SAVE_TAB";

    /**
     * CREATE_STANDY.
     */
    String CREATE_STANDBY = "CREATE_STANDBY";

    /**
     * COPY_DEFAULT_CONFIRM_NEW_TAB.
     */
    String COPY_DEFAULT_CONFIRM_NEW_TAB = "COPY_DEFAULT_CONFIRM_NEW_TAB";

    /**
     * COPY_SEARCH_SAVE.
     */
    String COPY_SEARCH_SAVE = "COPY_SEARCH_SAVE";

    /**
     * COPY_SEARCH_DO_NOT_SAVE.
     */
    String COPY_SEARCH_DO_NOT_SAVE = "COPY_SEARCH_DO_NOT_SAVE";

    /**
     * SEARCH_SUBSCRIBER.
     */
    String SEARCH_SUBSCRIBER = "SEARCH_SUBSCRIBER";

    /**
     * SEARCH_AIRLINE_NUMBER.
     */
    String SEARCH_AIRLINE_NUMBER = "SEARCH_AIRLINE_NUMBER";

    /**
     * CREATE_STANDBY_NEW_SAVE.
     */
    String CREATE_STANDBY_NEW_SAVE = "CREATE_STANDBY_NEW_SAVE";

    /**
     * CURRENT_SUBSCRIBER.
     */

    String CURRENT_SUBSCRIBER = "CURRENT_SUBSCRIBER";
    /**
     * CURRENT_AIRLINE.
     */
    String CURRENT_AIRLINE = "CURRENT_AIRLINE";
    /**
     * COPY_STANDBY_CONFIRM_NEW_TAB.
     */
    String COPY_STANDBY_CONFIRM_NEW_TAB = "COPY_STANDBY_CONFIRM_NEW_TAB";
    /**
     * COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB.
     */
    String COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB = "COPY_DEFAULTSTANDBY_CONFIRM_NEW_TAB";
    /**
     * RESET_CREATE.
     */
    String RESET_CREATE = "RESET_CREATE";
    /**
     * DELETE_STATUS.
     */
    String DELETE_STATUS = "DELETE_STATUS";
    /**
     * Standby Priority.
     */
    String MANAGE_MODULE_NAME = "Standby Priority";
    /**
     * CONFIRM_DELETE_DIALOG_MSG.
     */
    String CONFIRM_SAVE_AND_DELETE_DIALOG_MSG = 
        "You have unsaved data in the screen. If you continue to delete, \n the unsaved data will be removed."
        + "Do you want to continue to delete?";
    /**
     * New Standby Priority.
     */
    String NEW_STANDBY_PRIORITY_LIST = "NEW_STANDBY_PRIORITY_LIST";
    /**
     * SELECTED_LIST_TO_OPEN.
     */
    String SELECTED_LIST_TO_OPEN = "SELECTED_LIST_TO_OPEN";
    /**
     * ACTION_KEY.
     */
    String ACTION_KEY = "Button Pressed";

}
