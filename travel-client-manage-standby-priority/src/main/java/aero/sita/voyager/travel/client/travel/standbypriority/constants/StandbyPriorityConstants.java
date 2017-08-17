/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.constants;

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
    /**
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
     * fifteen.
     */
    int FIFTEEN = 15;

    /**
     * Add NO_OF_ROWS.
     */
    int ADD_NO_OF_ROWS = 9;

    /**
     * CONFIRM_DELETE_DIALOG_TITLE.
     */
    String CONFIRM_DELETE_DIALOG_TITLE = "Confirm Delete";
    /**
     * CLOSE.
     */
    String CLOSE = "CLOSE";

    /**
     * CONFIRM_DELETE_DIALOG_MSG.
     */
    String CONFIRM_DELETE_DIALOG_MSG = "Are you sure, you want to delete the selected \n "
        + "default standby priority codes?";

    /**
     * COPY_WARNING_DIALOG_MSG.
     */
    String COPY_WARNING_DIALOG_MSG = "New standby priority tab has some data." 
        + "\n Do you wish to save data before proceeding?";

    /**
     * ERROR_MESSAGE_1.
     */
    String ERROR_MESSAGE_1 = "Please select only one row.";

    /**
     * Delete Success message.
     */
    String DELETE_SUCCESS_MSG = "Selected standby priority codes deleted successfully.";

    /**
     * Add Success message.
     */
    String ADD_SUCCESS_MSG = "Record saved successfully";

    /**
     * NEW_STANDBY_PRIORITY_TAB.
     */
    String NEW_STANDBY_PRIORITY_TAB = "New standby priority";

    /**
     * MODULE_ID.
     */
    String MODULE_ID = "standby-priority-details";

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
     * DEFAULT_SP_RESPONSE_ROWCOUNT.
     */
    String DEFAULT_SP_RESPONSE_ROWCOUNT = "DEFAULT_SP_RESPONSE_ROWCOUNT";

    /**
     * Max number of row in a Standby priority table.
     */
    int MAX_ROW_COUNT = 500;

    /**
     * ZERO_VALUE.
     */
    String ZERO_VALUE = "0";

    /**
     * ZERO_TWO_DIGIT_VALUE.
     */
    String ZERO_TWO_DIGIT_VALUE = "00";

    /**
     * CREATE_DEFAULT_SP_FAILED_ROWS.
     */
    String CREATE_DEFAULT_SP_FAILED_ROWS = "CREATE_DEFAULT_SP_FAILED_ROWS";

    /**
     * DEFAULT_SP_MANDATORY_ROW.
     */
    String DEFAULT_SP_MANDATORY_ROW = "DEFAULT_SP_MANDATORY_ROW";

    /**
     * NOT_PERMITTED.
     */
    String DOJ_NOT_PERMITTED = "NOT_PERMITTED";

    /**
     * DEFAULT_STANDBY_PRIORITIES.
     */
    String DEFAULT_STANDBY_PRIORITIES = "DEFAULT_STANDBY_PRIORITIES";

    /**
     * DEFAULT_STANDBY_PRIORITY_RESPONSE.
     */
    String DEFAULT_STANDBY_PRIORITY_RESPONSE = "DEFAULT_STANDBY_PRIORITY_RESPONSE";

    /**
     * COPY_DEFAULT.
     */
    String COPY_DEFAULT = "COPY_DEFAULT";

    /**
     * RESET_DEFAULT.
     */
    String RESET_DEFAULT = "RESET_DEFAULT";

    /**
     * RESET_SEARCH.
     */
    String RESET_SEARCH = "RESET_SEARCH";

    /**
     * COPY_DEFAULT_SAVE.
     */
    String COPY_DEFAULT_SAVE = "COPY_DEFAULT_SAVE";

    /**
     * COPY_DEFAULT_DO_NOT_SAVE.
     */
    String COPY_DEFAULT_DO_NOT_SAVE = "COPY_DEFAULT_DO_NOT_SAVE";

    /**
     * SP_SUBSCRIBER_CODE.
     */
    String SP_SUBSCRIBER_CODE = "SP_SUBSCRIBER_CODE";

    /**
     * SP_AIRLINE_CODE.
     */
    String SP_AIRLINE_CODE = "SP_AIRLINE_CODE";

    /**
     * STANDBY_PRIORITY_RESPONSE_LIST.
     */
    String STANDBY_PRIORITY_RESPONSE_LIST = "STANDBY_PRIORITY_RESPONSE_LIST";

    /**
     * COPY_SEARCH_SAVE.
     */
    String COPY_SEARCH_SAVE = "COPY_SEARCH_SAVE";

    /**
     * COPY_SEARCH_DO_NOT_SAVE.
     */
    String COPY_SEARCH_DO_NOT_SAVE = "COPY_SEARCH_DO_NOT_SAVE";

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
     * COPY_STANDBY.
     */
    String COPY_STANDBY = "COPY_STANDBY";

    /**
     * CREATE_STANDBY.
     */
    String CREATE_STANDBY = "CREATE_STANDBY";

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
     * RESET_STANBY.
     */
    String RESET_STANBY = "RESET_STANBY";
    /**
     * RESET_CREATE.
     */
    String RESET_CREATE = "RESET_CREATE";

    /**
     * MANAGE_SEARCHED_SUBSCRIBER.
     */
    String MANAGE_SEARCHED_SUBSCRIBER = "MANAGE_SEARCHED_SUBSCRIBER";

    /**
     * CONFIRM_DELETE_DIALOG_MSG.
     */
    String CONFIRM_SAVE_AND_DELETE_DIALOG_MSG = "You have unsaved data in the screen." 
        +"If you continue to delete, \n the unsaved data will be removed. Do you want to continue to delete?";
    /**
     * DEFAULT_PRIORITY_RESPONSE_LIST.
     */
    String DEFAULT_PRIORITY_RESPONSE_LIST = "DEFAULT_PRIORITY_RESPONSE_LIST";

    /**
     * SELECTED_LIST_TO_OPEN.
     */
    String SELECTED_LIST_TO_OPEN = "SELECTED_LIST_TO_OPEN";

    /**
     * DEFAULT_SCREEN_PATH.
     */
    String DEFAULT_SCREEN_PATH = " Administration | Standby priority | Default standby priority";
    /**
     * SEARCH_TAB_SCREEN_PATH.
     */
    String SEARCH_TAB_SCREEN_PATH = " Administration | Standby priority";
    /**
     * ACTION_KEY.
     */
    String ACTION_KEY = "Button Pressed";

}
