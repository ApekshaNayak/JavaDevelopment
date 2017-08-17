/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.table.NGAFTableResponseListener;
import aero.sita.voyager.dcs.client.common.base.viewhandler.DcsTabDataContainer;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.valueobject.StandbyPriorityVO;

/**
 * <pre>
 * <b>Description : </b>
 * StandbyPriorityResponseListener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-06 01:17:09 PM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */

public class StandbyPriorityResponseListener implements NGAFTableResponseListener {
    /**
     * LOGGER.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
        .getLogger(StandbyPriorityResponseListener.class);
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
     * COLUMN_VALUE_FOURTEEN.
     */
    private static final int COLUMN_VALUE_FOURTEEN = 14;
    
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
        final Object[] columnValues = new Object[COLUMN_VALUE_FOURTEEN];

        if (item instanceof StandbyPriorityVO) {
            final StandbyPriorityVO standbyPriorityVO = (StandbyPriorityVO) item;

            for (int colIndex = 0; colIndex < COLUMN_VALUE_FOURTEEN; colIndex++) {
                
                columnValues[colIndex] = getColumnValue(standbyPriorityVO, colIndex);
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
     * @param standbyPriorityVO , not null.
     * @param colIndex , not null.
     * @return columnValue , never null.
     * </pre>
     */
    private String getColumnValue(final StandbyPriorityVO standbyPriorityVO, final int colIndex) {
        String columnValue = "";
        String currentTabName = DcsTabDataContainer.getInstance().getCurrentTabName();
      
        switch (colIndex) {
            case 0:
                columnValue = getPriorityCode(standbyPriorityVO);
                break;
            case COLUMN_VALUE_ONE:
                columnValue = getPriorityDesc(standbyPriorityVO);
                break;
            case COLUMN_VALUE_TWO:
                columnValue = getCheckPAD(standbyPriorityVO);
                break;
            case COLUMN_VALUE_THREE:
                columnValue = getDateOfJoining(standbyPriorityVO);
                break;
            case COLUMN_VALUE_FOUR:
                columnValue = getBookedPriority(standbyPriorityVO);
                break;
            case COLUMN_VALUE_FIVE:
                columnValue = getBookedPAD(standbyPriorityVO);
                break;
            case COLUMN_VALUE_SIX:
                columnValue = getNORECPriority(standbyPriorityVO);
                break;
            case COLUMN_VALUE_SEVEN:
                columnValue = getNORECPAD(standbyPriorityVO);
                break;
            case COLUMN_VALUE_EIGHT:
                columnValue = getWaitingPriority(standbyPriorityVO);
                break;
            case COLUMN_VALUE_NINE:
                columnValue = getWaitingPAD(standbyPriorityVO);
                break;
            case COLUMN_VALUE_TEN:
                columnValue = getOpenPriority(standbyPriorityVO);
                break;
            case COLUMN_VALUE_ELEVEN:
                columnValue = getOpenPAD(standbyPriorityVO);
                break;
            case COLUMN_VALUE_TWELVE:
                columnValue = getOpenSATicket(standbyPriorityVO);
                break;
            case COLUMN_VALUE_THIRTEEN:
                if (!currentTabName.equals(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_TAB)) {
                    columnValue = getDocumentID(standbyPriorityVO);
                }
                break;
            default:
                columnValue = "";
                break;
        }

        return columnValue;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * To get the Document ID.
     * 
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getDocumentID(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getDocumentId())) {
            return standbyPriorityVO.getDocumentId();
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
    * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getOpenSATicket(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getHoldsSpaceAvailableTicket())) {
            return standbyPriorityVO.getHoldsSpaceAvailableTicket();
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
    * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getOpenPAD(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getOpenTicketCanBeDisembarked())) {
            return standbyPriorityVO.getOpenTicketCanBeDisembarked();
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
    * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getOpenPriority(final StandbyPriorityVO standbyPriorityVO) {
        return conversionNumber(standbyPriorityVO.getOpenTicketPriorityValue());
        
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getWaitingPAD.
     * 
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getWaitingPAD(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getWaitlistCanBeDisembarked())) {
            return standbyPriorityVO.getWaitlistCanBeDisembarked();
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
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getWaitingPriority(final StandbyPriorityVO standbyPriorityVO) {
        return conversionNumber(standbyPriorityVO.getWaitlistPriorityValue());
        
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getNORECPAD.
     * 
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getNORECPAD(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getNoRecCanBeDisembarked())) {
            return standbyPriorityVO.getNoRecCanBeDisembarked();
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
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getNORECPriority(final StandbyPriorityVO standbyPriorityVO) {
        return conversionNumber(standbyPriorityVO.getNoRecPriorityValue());
        
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getBookedPAD.
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getBookedPAD(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getBookedCanBeDisembarked())) {
            return standbyPriorityVO.getBookedCanBeDisembarked();
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
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getBookedPriority(final StandbyPriorityVO standbyPriorityVO) {
        return conversionNumber(standbyPriorityVO.getBookedPriorityValue());
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * getDateOfJoining.
     * 
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getDateOfJoining(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getDateOfJoining())) {
            return standbyPriorityVO.getDateOfJoining();
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
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getCheckPAD(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getCanBeDisembarked())) {
            return standbyPriorityVO.getCanBeDisembarked();
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
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getPriorityDesc(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getPriorityStatusDesc())) {
            return standbyPriorityVO.getPriorityStatusDesc();
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
     * @param standbyPriorityVO , not null.
     * @return String , never null.
     * </pre>
     */
    private String getPriorityCode(final StandbyPriorityVO standbyPriorityVO) {
        if (isNotEmpty(standbyPriorityVO.getPriorityStatusCode())) {
            return standbyPriorityVO.getPriorityStatusCode();
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
     * @param value , not null.
     * @return
     * </pre>
     */
    private String conversionNumber(final int value) {
        stringConverstion = "" + value;
        if (isNotEmpty(stringConverstion) && !stringConverstion.trim().equalsIgnoreCase("0")) {
            return stringConverstion;
        }
        else {
            return "";
        }
        
    }
    
}
