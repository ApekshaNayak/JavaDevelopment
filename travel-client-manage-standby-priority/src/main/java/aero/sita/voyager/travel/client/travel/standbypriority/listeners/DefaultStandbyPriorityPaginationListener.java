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

import java.util.ArrayList;
import java.util.List;

import aero.sita.csp.gui.pagination.bean.NGAFPaginationFilterBean;
import aero.sita.csp.gui.pagination.bean.NGAFPaginationResponse;
import aero.sita.csp.gui.pagination.bean.NGAFPaginationSearchBean;
import aero.sita.csp.gui.pagination.bean.NGAFPaginationSortBean;
import aero.sita.csp.gui.pagination.listener.client.NGAFPaginationClientListener;
import aero.sita.csp.gui.thickclient.context.util.NGAFContextUtil;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.controllers.StandbyPriorityController;
import aero.sita.voyager.travel.client.travel.standbypriority.valueobject.DefaultStandbyPriorityVO;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;

/**
 * <pre>
 * <b>Description : </b>
 * DefaultStandbyPriorityPaginationListener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-06 01:17:09 PM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class DefaultStandbyPriorityPaginationListener implements NGAFPaginationClientListener {

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
     * Reference for StandbyPriorityController.
     */
    private StandbyPriorityController controller;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This method will load the default standby priority data into the lov's. 
     * 
     * @param ngafPaginationSearchBean , not null.
     * @return NGAFPaginationResponse , never null.
     * </pre>
     */
    public final NGAFPaginationResponse loadData(final NGAFPaginationSearchBean ngafPaginationSearchBean) {
        //List<StandbyPriorityType> standbyPriorityRespList = new ArrayList<StandbyPriorityType>();
        List<Object> listOfVO = new ArrayList<Object>();
        NGAFPaginationResponse paginationResponse = new NGAFPaginationResponse();

        if (NGAFContextUtil.getApplicationContext().
            get(StandbyPriorityConstants.DEFAULT_PRIORITY_RESPONSE_LIST) != null) {
            List<StandbyPriorityType> standbyPriorityRespList = (List<StandbyPriorityType>) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.DEFAULT_PRIORITY_RESPONSE_LIST);
            if (standbyPriorityRespList != null && standbyPriorityRespList.size() > 0) {
                for (StandbyPriorityType standbyPriorityDTO : standbyPriorityRespList) {
                    listOfVO.add(setDTOtoVO(standbyPriorityDTO));

                }
                DefaultStandbyPriorityVO obj = new DefaultStandbyPriorityVO();
                obj.setCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setDateOfJoining(StandbyPriorityConstants.DOJ_OPTIONAL);
                obj.setBookedCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setNoRecCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setWaitlistCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setOpenTicketCanBeDisembarked(StandbyPriorityConstants.YES_VALUE);
                obj.setHoldsSpaceAvailableTicket(StandbyPriorityConstants.YES_VALUE);
                int index = listOfVO.size();
                if (index < StandbyPriorityConstants.NO_OF_ROWS) {
                    for (; index < StandbyPriorityConstants.NO_OF_ROWS; index++) {
                        listOfVO.add(obj);
                    }
                }

                paginationResponse.setListOfRecords(listOfVO);
                paginationResponse.setTotalRecordCount(listOfVO.size());

            }

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
        DefaultStandbyPriorityVO defaultStandbyPriorityVO = new DefaultStandbyPriorityVO();

        defaultStandbyPriorityVO.setPriorityStatusCode(standbyPriorityDTO.getInfo().getPriorityStatusCode());
        defaultStandbyPriorityVO.setPriorityStatusDesc(standbyPriorityDTO.getInfo().getPriorityStatusDescription());
        //String dateOfJoining = null;
        String dateOfJoiningTemp;
        // Date of Joining
        if (standbyPriorityDTO.getInfo() != null) {

            if (standbyPriorityDTO.getInfo().getDateOfJoiningRequired() != null) {
                String dateOfJoining = standbyPriorityDTO.getInfo().getDateOfJoiningRequired().toString();
                if (dateOfJoining.equalsIgnoreCase(StandbyPriorityConstants.DOJ_OPTIONAL)) {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_OPTIONAL;
                }
                else {
                    dateOfJoiningTemp = StandbyPriorityConstants.DOJ_NOT_REQUIRED;
                }
                defaultStandbyPriorityVO.setDateOfJoining(dateOfJoiningTemp);
            }

            defaultStandbyPriorityVO.setBookedPriorityValue(standbyPriorityDTO.getBookingStatusInfo()
                .getBookedPriorityInfo().getPriorityValue());
            defaultStandbyPriorityVO.setNoRecPriorityValue(standbyPriorityDTO.getBookingStatusInfo()
                .getNorecPriorityInfo().getPriorityValue());
            defaultStandbyPriorityVO.setWaitlistPriorityValue(standbyPriorityDTO.getBookingStatusInfo()
                .getWaitlistPriorityinfo().getPriorityValue());
            defaultStandbyPriorityVO.setOpenTicketPriorityValue(standbyPriorityDTO.getBookingStatusInfo()
                .getOpenPriorityInfo().getPriorityValue());
            defaultStandbyPriorityVO.setCanBeDisembarked(convertBooleanToString(standbyPriorityDTO.getInfo()
                .isCanBeDisembarked()));

            defaultStandbyPriorityVO.setBookedCanBeDisembarked((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getBookedPriorityInfo().isCanBeDisembarked())));
            defaultStandbyPriorityVO.setNoRecCanBeDisembarked((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getNorecPriorityInfo().isCanBeDisembarked())));

            defaultStandbyPriorityVO.setWaitlistCanBeDisembarked(((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getWaitlistPriorityinfo().isCanBeDisembarked()))));
            defaultStandbyPriorityVO.setOpenTicketCanBeDisembarked(((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getOpenPriorityInfo().isCanBeDisembarked()))));

            defaultStandbyPriorityVO.setHoldsSpaceAvailableTicket(((convertBooleanToString(standbyPriorityDTO
                .getBookingStatusInfo().getOpenPriorityInfo().isHoldsSpaceAvailableTicket()))));

            defaultStandbyPriorityVO.setDocumentId(((standbyPriorityDTO.getInfo().getPriorityStatusCode())));
        }
        return defaultStandbyPriorityVO;
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
     * @param object , not null.
     * @param filterBean , not null.
     * @return boolean , never null.
     * </pre>
     */
    public final boolean isFilterData(final Object object, final NGAFPaginationFilterBean filterBean) {
        final int column = filterBean.getFilterKey();
        String filterValue = filterBean.getFilterValue();
        if (object instanceof Object[]) {

            if (filterValue != null && filterValue.trim().length() > 0) {
                return false;
            }
            else {
                return true;
            }
        }
        final DefaultStandbyPriorityVO itemsExt = (DefaultStandbyPriorityVO) object;

        switch (column) {
            case COLUMN_ZERO:
                String statusCode = itemsExt.getPriorityStatusCode();
                if (isNullOrBlank(filterValue)
                    || (statusCode != null && filterValue != null && statusCode.toLowerCase().startsWith(
                        filterValue.toLowerCase()))) {
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
                    && (convertIntToString(itemsExt.getOpenTicketPriorityValue()))
                        .startsWith(filterValue.toLowerCase()) || filterValue == null || filterValue.length() == 0) {
                    return true;
                }

                break;

            default:
                return false;
        }

        return false;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Method to check if the given string null or blank.
     * 
     * @param filterValue , the string , may be null.
     * 
     * @return true if the string is null or blank , never null.
     * </pre>
     */
    private boolean isNullOrBlank(final String filterValue) {
        boolean isNull = false;
        if (filterValue == null || filterValue.length() == 0) {
            isNull = true;
        }
        return isNull;
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
     * @return result , never null.
     * </pre>
     */
    public final int compareTo(final Object objectOne, final Object objectTwo, final NGAFPaginationSortBean sortBean) {
        if (objectOne != null && objectTwo != null) {
            DefaultStandbyPriorityVO defaultStandbyPriorityVOOne = (DefaultStandbyPriorityVO) objectOne;
            DefaultStandbyPriorityVO defaultStandbyPriorityVOTwo = (DefaultStandbyPriorityVO) objectTwo;
            int column = sortBean.getColumnIndex();
            switch (column) {
                case COLUMN_FOUR:
                    return compareNumber(defaultStandbyPriorityVOOne.getBookedPriorityValue(),
                        defaultStandbyPriorityVOTwo.getBookedPriorityValue());
                case COLUMN_SIX:
                    return compareNumber(defaultStandbyPriorityVOOne.getNoRecPriorityValue(),
                        defaultStandbyPriorityVOTwo.getNoRecPriorityValue());
                case COLUMN_EIGHT:
                    return compareNumber(defaultStandbyPriorityVOOne.getWaitlistPriorityValue(),
                        defaultStandbyPriorityVOTwo.getWaitlistPriorityValue());
                case COLUMN_TEN:
                    return compareNumber(defaultStandbyPriorityVOOne.getOpenTicketPriorityValue(),
                        defaultStandbyPriorityVOTwo.getOpenTicketPriorityValue());
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
     * @return int , never null.
     * </pre>
     */
    private int compareNumber(final int valueOne, final int valueTwo) {
/*        if (valueOne == valueTwo && valueOne != 0 && valueTwo != 0) {
            return 0;
        }
        else*/ if (valueOne > valueTwo && valueOne != 0 && valueTwo != 0) {
            return 1;
        }
        else if (valueOne != 0 && valueTwo != 0) {
            return -1;
        }
        return 0;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'controller' attribute value.
     * 
     * @return controller , null if not found.
     * </pre>
     */

    public StandbyPriorityController getController() {
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
    public void setController(final StandbyPriorityController controllerParam) {
        controller = controllerParam;
    }
}
