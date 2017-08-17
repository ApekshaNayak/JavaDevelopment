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

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.table.NGAFTableResponseListener;
import aero.sita.voyager.travel.client.travel.standbypriority.valueobject.DefaultStandbyPriorityVO;

/**
 * <pre>
 * <b>Description : </b>
 * DefaultStandbyPriorityResponseListener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-06 01:17:09 PM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */

public class DefaultStandbyPriorityResponseListener implements NGAFTableResponseListener {
    /**
     * LOGGER.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
    .getLogger(DefaultStandbyPriorityResponseListener.class);
    /**
     * COLUMN_VALUE_ONE.
     */
    private static final int COLUMN_VALUE_ONE = 1;

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
     * COLUMN_VALUE_SEVEN.
     */
    private static final int COLUMN_VALUE_SEVEN = 7;

    /**
     * COLUMN_VALUE_EIGHT.
     */
    private static final int COLUMN_VALUE_EIGHT = 8;

    /**
     * COLUMN_VALUE_NINE.
     */
    private static final int COLUMN_VALUE_NINE = 9;

    /**
     * COLUMN_VALUE_TEN.
     */
    private static final int COLUMN_VALUE_TEN = 10;

    /**
     * COLUMN_VALUE_ELEVEN.
     */
    private static final int COLUMN_VALUE_ELEVEN = 11;

    /**
     * COLUMN_VALUE_TWELVE.
     */
    private static final int COLUMN_VALUE_TWELVE = 12;

    /**
     * COLUMN_VALUE_THIRTEEN.
     */
    private static final int COLUMN_VALUE_THIRTEEN = 13;

    /**
     * stringConverstion.
     */
    private String stringConverstion = "";

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * this is transformResponse to table.
     * 
     * @param item , not null
     * @return
     * </pre>
     */
    public final Object[] transformResponse(final Object item) {
        final Object[] columnValues = new Object[14];

        if (item instanceof DefaultStandbyPriorityVO) {
            final DefaultStandbyPriorityVO defaultStandbyPriorityVO = (DefaultStandbyPriorityVO) item;

            for (int colIndex = 0; colIndex < 14; colIndex++) {

                columnValues[colIndex] = getColumnValue(defaultStandbyPriorityVO, colIndex);
            }
        }
        else {
            return (Object[]) item;
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
     * @param defaultStandbyPriorityVO , not null.
     * @param colIndex , not null.
     * @return columnValue , never null.
     * </pre>
     */
    private String getColumnValue(final DefaultStandbyPriorityVO defaultStandbyPriorityVO, final int colIndex) {
        String columnValue = "";

        switch (colIndex) {
            case 0:
                columnValue = getPriorityCode(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_ONE:
                columnValue = getPriorityDesc(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_TWO:
                columnValue = getCheckPAD(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_THREE:
                columnValue = getDateOfJoining(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_FOUR:
                columnValue = getBookedPriority(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_FIVE:
                columnValue = getBookedPAD(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_SIX:
                columnValue = getNORECPriority(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_SEVEN:
                columnValue = getNORECPAD(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_EIGHT:
                columnValue = getWaitingPriority(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_NINE:
                columnValue = getWaitingPAD(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_TEN:
                columnValue = getOpenPriority(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_ELEVEN:
                columnValue = getOpenPAD(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_TWELVE:
                columnValue = getOpenSATicket(defaultStandbyPriorityVO);
                break;
            case COLUMN_VALUE_THIRTEEN:
                columnValue = getDocumentID(defaultStandbyPriorityVO);
                break;
            /*
             * default: columnValue = ""; break;
             */
        }
        return columnValue;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * To get the Document ID.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getDocumentID(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getDocumentId())) {
            return defaultStandbyPriorityVO.getDocumentId();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getOpenSATicket.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getOpenSATicket(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(
            defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket())) {
            return defaultStandbyPriorityVO.getHoldsSpaceAvailableTicket();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getOpenPAD.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getOpenPAD(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(
            defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked())) {
            return defaultStandbyPriorityVO.getOpenTicketCanBeDisembarked();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getOpenPriority.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getOpenPriority(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        return conversionNumber(defaultStandbyPriorityVO.getOpenTicketPriorityValue());

    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getWaitingPAD.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getWaitingPAD(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getWaitlistCanBeDisembarked())) {
            return defaultStandbyPriorityVO.getWaitlistCanBeDisembarked();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getWaitingPriority.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getWaitingPriority(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        return conversionNumber(defaultStandbyPriorityVO.getWaitlistPriorityValue());

    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getNORECPAD.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getNORECPAD(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getNoRecCanBeDisembarked())) {
            return defaultStandbyPriorityVO.getNoRecCanBeDisembarked();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getNORECPriority.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getNORECPriority(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        return conversionNumber(defaultStandbyPriorityVO.getNoRecPriorityValue());

    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getBookedPAD.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getBookedPAD(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getBookedCanBeDisembarked())) {
            return defaultStandbyPriorityVO.getBookedCanBeDisembarked();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getBookedPriority.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getBookedPriority(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        return conversionNumber(defaultStandbyPriorityVO.getBookedPriorityValue());
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getDateOfJoining.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getDateOfJoining(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getDateOfJoining())) {
            return defaultStandbyPriorityVO.getDateOfJoining();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getCheckPAD.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getCheckPAD(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getCanBeDisembarked())) {
            return defaultStandbyPriorityVO.getCanBeDisembarked();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getPriorityDesc.
     * 
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getPriorityDesc(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getPriorityStatusDesc())) {
            return defaultStandbyPriorityVO.getPriorityStatusDesc();
        }
        else {
            return "";
        }
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getPriorityCode.
     * to get the Priority code.
     * @param defaultStandbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getPriorityCode(final DefaultStandbyPriorityVO defaultStandbyPriorityVO) {
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(defaultStandbyPriorityVO.getPriorityStatusCode())) {
            return defaultStandbyPriorityVO.getPriorityStatusCode();
        }
        else {
            return "";
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

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Integer to String Conversion.
     * 
     * @param value
     * @return
     * </pre>
     */
    private String conversionNumber(final int value) {
        stringConverstion = "" + value;
        if (DefaultStandbyPriorityResponseListener.isNotEmpty(stringConverstion)
            && !stringConverstion.trim().equalsIgnoreCase("0")) {
            return stringConverstion;
        }
        else {
            return "";
        }

    }

}
