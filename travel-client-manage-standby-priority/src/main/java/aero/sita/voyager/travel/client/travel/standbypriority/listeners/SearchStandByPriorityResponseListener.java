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

import aero.sita.csp.gui.table.NGAFTableResponseListener;
import aero.sita.voyager.travel.client.travel.standbypriority.valueobject.SearchStandbyPriorityVO;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Standby Priority Response Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-08 10:15:51 AM $
 * @author $Author: abirami.rajaram $ 
 * </pre>
 */
public class SearchStandByPriorityResponseListener implements NGAFTableResponseListener {

    /**
     * constant.
     */
    private static final int TWO = 2;

    /**
     * Loads data to the Standby Priority Filter Table.
     * 
     * <pre>
     * <b>Description : </b>
     * Loads data to the Standby Priority Filter Table.
     * @return objects , never null.
     * @param item , not null.
     * </pre>
     */
    public Object[] transformResponse(final Object item) {
        final Object[] columnValues = new Object[TWO];

        if (item instanceof SearchStandbyPriorityVO) {
            final SearchStandbyPriorityVO searchStandbyPriorityVO = (SearchStandbyPriorityVO) item;

            for (int colIndex = 0; colIndex < TWO; colIndex++) {
                columnValues[colIndex] = getColumnValue(searchStandbyPriorityVO, colIndex);
            }
        }

        return columnValues;
    }

    /**
     * Method to get the String value corresponding to a Column.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to get the String value corresponding to a Column.
     * 
     * @param searchStandbyPriorityVO , not null.
     * @param colIndex , not null.
     * @return columnValue , never null.
     * </pre>
     */
    private String getColumnValue(final SearchStandbyPriorityVO searchStandbyPriorityVO, final int colIndex) {
        String columnValue = "";

        switch (colIndex) {
            case 0:
                columnValue = getAirlineCode(searchStandbyPriorityVO);
                break;
            case 1:
                columnValue = getAirlineName(searchStandbyPriorityVO);
                break;
            /*default:
                columnValue = " ";
                break;*/
        }

        return columnValue;
    }

    /**
     * To get the Airline Code.
     * 
     * <pre>
     * <b>Description : </b>
     * 
     * @param searchStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getAirlineCode(final SearchStandbyPriorityVO searchStandbyPriorityVO) {
        if (isNotEmpty(searchStandbyPriorityVO.getAirlineCode())) {
            return searchStandbyPriorityVO.getAirlineCode();
        }
        else {
            return " ";
        }
    }

    /**
     * To get the Airline Name.
     * 
     * <pre>
     * <b>Description : </b>
     * 
     * @param searchStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getAirlineName(final SearchStandbyPriorityVO searchStandbyPriorityVO) {
        if (isNotEmpty(searchStandbyPriorityVO.getAirlineName())) {
            return searchStandbyPriorityVO.getAirlineName();
        }
        else {
            return " ";
        }
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
}
