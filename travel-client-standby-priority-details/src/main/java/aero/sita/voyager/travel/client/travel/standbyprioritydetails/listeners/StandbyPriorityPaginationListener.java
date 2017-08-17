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

import java.util.ArrayList;
import java.util.List;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.csp.gui.pagination.bean.NGAFPaginationFilterBean;
import aero.sita.csp.gui.pagination.bean.NGAFPaginationResponse;
import aero.sita.csp.gui.pagination.bean.NGAFPaginationSearchBean;
import aero.sita.csp.gui.pagination.bean.NGAFPaginationSortBean;
import aero.sita.csp.gui.pagination.listener.client.NGAFPaginationClientListener;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.valueobject.StandbyPriorityVO;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;

/**
 * <pre>
 * <b>Description : </b>
 * StandbyPriorityPaginationListener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-06 01:17:09 PM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class StandbyPriorityPaginationListener implements NGAFPaginationClientListener {

    /**
     * LOGGER.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory
    .getLogger(StandbyPriorityPaginationListener.class);

    /**
     * COLUMN_ZERO.
     */
    private static final int COLUMN_ZERO = 0;

    /**
     * COLUMN_FOUR.
     */
    private static final int COLUMN_FOUR = 4;
    /**
     * COLUMN_SIX.
     */
    private static final int COLUMN_SIX = 6;
    /**
     * COLUMN_EIGHT.
     */
    private static final int COLUMN_EIGHT = 8;
    /**
     * COLUMN_TEN.
     */
    private static final int COLUMN_TEN = 10;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method will load the default standby priority data into the lov's. 
     * 
     * @param ngafPaginationSearchBean , not null.
     * @return NGAFPaginationResponse , never null
     * </pre>
     */
    public final NGAFPaginationResponse loadData(final NGAFPaginationSearchBean ngafPaginationSearchBean) {
        //List<StandbyPriorityType> standbyPriorityRespList = new ArrayList<StandbyPriorityType>();
        List<Object> listOfVO = new ArrayList<Object>();
        NGAFPaginationResponse paginationResponse = new NGAFPaginationResponse();

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.NEW_STANDBY_PRIORITY_LIST) != null) {
            List<StandbyPriorityType> standbyPriorityRespList = (List<StandbyPriorityType>) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.NEW_STANDBY_PRIORITY_LIST);
            if (standbyPriorityRespList != null && standbyPriorityRespList.size() > 0) {
                for (StandbyPriorityType standbyPriorityDTO : standbyPriorityRespList) {
                    listOfVO.add(setDTOtoVO(standbyPriorityDTO));

                }

                StandbyPriorityVO obj = new StandbyPriorityVO();
                obj.setCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setDateOfJoining(StandbyPriorityConstants.DOJ_OPTIONAL);
                obj.setBookedCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setNoRecCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setWaitlistCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setOpenTicketCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setHoldsSpaceAvailableTicket(StandbyPriorityConstants.YES_VALUE);
                obj.setCreateUpdateEnabled(false);
                int index = listOfVO.size();
                if (index < StandbyPriorityConstants.NO_OF_ROWS) {
                    for (; index < StandbyPriorityConstants.NO_OF_ROWS; index++) {
                        listOfVO.add(obj);
                    }
                }
            }

            paginationResponse.setListOfRecords(listOfVO);
            paginationResponse.setTotalRecordCount(listOfVO.size());

        }
        return paginationResponse;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This is to set the DTO to VO.
     * 
     * @param standbyPriorityDTO , not null.
     * @return Object , never null.
     * </pre>
     */
    private Object setDTOtoVO(final StandbyPriorityType standbyPriorityDTO) {

        StandbyPriorityVO standbyPriorityVO = new StandbyPriorityVO();

        standbyPriorityVO.setPriorityStatusCode(standbyPriorityDTO.getInfo().getPriorityStatusCode());
        standbyPriorityVO.setPriorityStatusDesc(standbyPriorityDTO.getInfo().getPriorityStatusDescription());
        //String dateOfJoining = null;
        //String dateOfJoiningTemp = null;
        // Date of Joining
        if (standbyPriorityDTO.getInfo() != null) {
            String dateOfJoiningTemp;
            if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired() != null) {
               String dateOfJoining = standbyPriorityDTO.getInfo().getDateOfJoiningRequired().toString();
                if (dateOfJoining.equalsIgnoreCase(StandbyPriorityConstants.DOJ_OPTIONAL)) {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_OPTIONAL;
                }
                else {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_NOT_REQUIRED;
                }
                standbyPriorityVO.setDateOfJoining(dateOfJoiningTemp);
            }

            standbyPriorityVO.setBookedPriorityValue(standbyPriorityDTO.getBookingStatusInfo().getBookedPriorityInfo()
                .getPriorityValue());
            standbyPriorityVO.setNoRecPriorityValue(standbyPriorityDTO.getBookingStatusInfo().getNorecPriorityInfo()
                .getPriorityValue());
            standbyPriorityVO.setWaitlistPriorityValue(standbyPriorityDTO.getBookingStatusInfo()
                .getWaitlistPriorityinfo().getPriorityValue());
            standbyPriorityVO.setOpenTicketPriorityValue(standbyPriorityDTO.getBookingStatusInfo()
                .getOpenPriorityInfo().getPriorityValue());
            standbyPriorityVO.setCanBeDisembarked(convertBooleanToString(standbyPriorityDTO.getInfo()
                .isCanBeDisembarked()));

            standbyPriorityVO.setBookedCanBeDisembarked((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getBookedPriorityInfo().isCanBeDisembarked())));
            standbyPriorityVO.setNoRecCanBeDisembarked((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getNorecPriorityInfo().isCanBeDisembarked())));

            standbyPriorityVO.setWaitlistCanBeDisembarked(((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getWaitlistPriorityinfo().isCanBeDisembarked()))));
            standbyPriorityVO.setOpenTicketCanBeDisembarked(((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getOpenPriorityInfo().isCanBeDisembarked()))));

            standbyPriorityVO.setHoldsSpaceAvailableTicket(((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getOpenPriorityInfo().isHoldsSpaceAvailableTicket()))));

            standbyPriorityVO.setDocumentId(((standbyPriorityDTO.getInfo().getPriorityStatusCode())));
           
        }

        return standbyPriorityVO;

    }




    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method is to convert the boolean value to String.
     * 
     * @param value , not null.
     * @return String , never null.
     * </pre>
     */
    public String convertBooleanToString(final Boolean value) {
        if (value) {
            return StandbyPriorityConstants.YES_VALUE;
        }
        else {
            return StandbyPriorityConstants.NO_VALUE;
        }
    }
    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This is use for filterData.
     * 
     * @param object , not null
     * @param filterBean , not null
     * @return
     * </pre>
     */
    public final boolean isFilterData(final Object object, final NGAFPaginationFilterBean filterBean) {

        final int column = filterBean.getFilterKey();

        final String filterValue = filterBean.getFilterValue();
        if (object instanceof Object[]) {

            if (filterValue != null && filterValue.trim().length() > 0) {
                return false;
            } 
            else {
                return true;
            }
        }
        final StandbyPriorityVO itemsExt = (StandbyPriorityVO) object;
        switch (column) {
            case COLUMN_ZERO:
                if (itemsExt.getPriorityStatusCode() != null
                    && (itemsExt.getPriorityStatusCode().toLowerCase()).startsWith(filterValue.toLowerCase())
                    || filterValue == null || filterValue.length() == 0) {

                    return true;
                }

                break;

            case COLUMN_FOUR:
                if (itemsExt.getBookedPriorityValue() != 0
                    && (convertIntToString(itemsExt.getBookedPriorityValue())).startsWith(filterValue.toLowerCase())
                    || filterValue == null || filterValue.length() == 0) {
                    return true;
                }

                break;
            case COLUMN_SIX:
                if (itemsExt.getNoRecPriorityValue() != 0
                    && (convertIntToString(itemsExt.getNoRecPriorityValue())).startsWith(filterValue.toLowerCase())
                    || filterValue == null || filterValue.length() == 0) {
                    return true;
                }

                break;
            case COLUMN_EIGHT:
                if (itemsExt.getWaitlistPriorityValue() != 0
                    && (convertIntToString(itemsExt.getWaitlistPriorityValue())).startsWith(filterValue.toLowerCase())
                    || filterValue == null || filterValue.length() == 0) {
                    return true;
                }

                break;
            case COLUMN_TEN:
                if (itemsExt.getOpenTicketPriorityValue() != 0
                    && (convertIntToString(itemsExt.getOpenTicketPriorityValue())).startsWith(filterValue.toLowerCase())
                    || filterValue == null || filterValue.length() == 0) {
                    return true;
                }

                break;

            default:
                return false;
        }
        return false;
    }

    /**
     * compareTo implementation.
     * 
     * <pre>
     * <b>Description : </b>
     * compareTo method.
     * 
     * @param objectOne , not null.
     * @param objectTwo , not null.
     * @param sortBean , not null.
     * @return
     * </pre>
     */
    public final int compareTo(final Object objectOne, final Object objectTwo, final NGAFPaginationSortBean sortBean) {

        if (objectOne != null && objectTwo != null) {
            StandbyPriorityVO StandbyPriorityVOOne = (StandbyPriorityVO) objectOne;
            StandbyPriorityVO StandbyPriorityVOTwo = (StandbyPriorityVO) objectTwo;
            int column = sortBean.getColumnIndex();
            switch (column) {
                case COLUMN_FOUR:
                    return compareNumber(StandbyPriorityVOOne.getBookedPriorityValue(),
                        StandbyPriorityVOTwo.getBookedPriorityValue());
                case COLUMN_SIX:
                    return compareNumber(StandbyPriorityVOOne.getNoRecPriorityValue(),
                        StandbyPriorityVOTwo.getNoRecPriorityValue());
                case COLUMN_EIGHT:
                    return compareNumber(StandbyPriorityVOOne.getWaitlistPriorityValue(),
                        StandbyPriorityVOTwo.getWaitlistPriorityValue());
                case COLUMN_TEN:
                    return compareNumber(StandbyPriorityVOOne.getOpenTicketPriorityValue(),
                        StandbyPriorityVOTwo.getOpenTicketPriorityValue());
                default:
                    break;
            }

        }

        return 0;
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method is to converted the int value to String.
     * 
     * @param value , not null.
     * @return String , never null.
     * </pre>
     */
    private String convertIntToString(final int value) {
        String valueConverted = "" + value;
        return valueConverted.trim();
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to compare two integer value and return integer.
     * 
     * @param valueOne , not null.
     * @param valueTwo , not null.
     * @return
     * </pre>
     */
    private int compareNumber(final int valueOne, final int valueTwo) {

        /*if (valueOne == valueTwo && valueOne != 0 && valueTwo != 0) {
            return 0;
        }*/
        /*else*/ if (valueOne > valueTwo && valueOne != 0 && valueTwo != 0) {
            return 1;
        }
        else if (valueOne != 0 && valueTwo != 0) {
            return -1;
        }
        return 0;
    }
}

