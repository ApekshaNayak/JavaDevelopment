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
import aero.sita.voyager.dcs.client.common.services.CommonUtilitiesGateWay;
import aero.sita.voyager.travel.client.travel.standbypriority.constants.StandbyPriorityConstants;
import aero.sita.voyager.travel.client.travel.standbypriority.valueobject.SearchStandbyPriorityVO;
import aero.sita.voyager.checkin.common.masterdata.airline.v1.AirlineInfoType;
import aero.sita.voyager.checkin.transferobjects.v9.StandbyPriorityType;

/**
 * <pre>
 * <b>Description : </b>
 * This class is for Standby Priority Pagination Listener.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-08 10:15:51 AM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class SearchStandByPriorityPaginationListener implements NGAFPaginationClientListener {

    /**
     * CommonUtilitiesGateWay.
     */
    private CommonUtilitiesGateWay commonUtilitiesGateWay;

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'commonUtilitiesGateWay' attribute value.
     * 
     * @param commonUtilitiesGateWayParam , may be null.
     * </pre>
     */
    public void setCommonUtilitiesGateWay(final CommonUtilitiesGateWay commonUtilitiesGateWayParam) {
        commonUtilitiesGateWay = commonUtilitiesGateWayParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'commonUtilitiesGateWay' attribute value.
     * 
     * @return commonUtilitiesGateWay , null if not found.
     * </pre>
     */
    public CommonUtilitiesGateWay getCommonUtilitiesGateWay() {
        return commonUtilitiesGateWay;
    }

    /**
     * Setting data to the Standby Priority Pagination Response.
     * 
     * <pre>
     * Setting data to the Standby Priority Pagination Response.
     * @return paginationResponse , never null.
     * @param arg0 , not null.
     * </pre>
     */
    public NGAFPaginationResponse loadData(final NGAFPaginationSearchBean arg0) {
        List airlinesList = new ArrayList();
        NGAFPaginationResponse paginationResponse = new NGAFPaginationResponse();

        if (NGAFContextUtil.getApplicationContext().get(StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST) != null) {

           List<StandbyPriorityType>  standbyPriorityRespList = (List<StandbyPriorityType>) NGAFContextUtil.getApplicationContext().get(
                StandbyPriorityConstants.STANDBY_PRIORITY_RESPONSE_LIST);
           
            if (standbyPriorityRespList != null && standbyPriorityRespList.size() > 0) {
                for (StandbyPriorityType standbyPriorityDTO : standbyPriorityRespList) {
                    if (standbyPriorityDTO.getHandledAirline() != null) {
                        final AirlineInfoType airlineInfoDTO = standbyPriorityDTO.getHandledAirline();      
                       
                        if (airlineInfoDTO.getIataCode() != null) {
                            String  iataCode = airlineInfoDTO.getIataCode();
                            String airlineName = populateAirlineName(iataCode, true, commonUtilitiesGateWay);
                            airlinesList.add(addAirlines(iataCode, airlineName));
                        }
                    }
                }
            }

            paginationResponse.setListOfRecords(airlinesList);

        }
        return paginationResponse;
    }

    /**
     * Method to return the filter status.
     * 
     * <pre>
     * <b>Description : </b>
     * Method to return the filter status.
     * @param filterBean , not null.
     * @param object , not null.
     * @return boolean , may be null.
     * </pre>
     */
    public boolean isFilterData(final Object object, final NGAFPaginationFilterBean filterBean) {
        final int column = filterBean.getFilterKey();
        final String filterValue = filterBean.getFilterValue();
        final SearchStandbyPriorityVO itemsExt = (SearchStandbyPriorityVO) object;
        switch (column) {
            case 0:
                if (itemsExt.getAirlineCode() != null
                    && (itemsExt.getAirlineCode().toLowerCase()).startsWith(filterValue.toLowerCase())
                    || filterValue == null || filterValue.length() == 0) {
                    return true;
                }

                break;

            case 1:
                if (itemsExt.getAirlineName() != null
                    && (itemsExt.getAirlineName().toLowerCase()).startsWith(filterValue.toLowerCase())
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
     * 
     * <pre>
     * This Method is to populate airline Number.
     *  Checks for IATA code.
     * @param airlineCode , not null.
     * @param isIATA , not null.
     * @param commonUtilitiesGateWayParam , not null.
     * @return String , never null.
     * </pre>
     */
    private String populateAirlineName(final String airlineCode, final boolean isIATA,
        final CommonUtilitiesGateWay commonUtilitiesGateWayParam) {
        String airlineName;

        try {
            airlineName = commonUtilitiesGateWayParam.fetchAirlineName(airlineCode, isIATA);
        }
        catch (Exception ex) {
            airlineName = null;
            ex.printStackTrace();
        }

        return airlineName;
    }

    /**
     * Method to return the comparison status.
     * 
     * <pre>
     * Method to return the comparison status.
     * @param objectOne , may be null.
     * @param objectTwo , may be null.
     * @param sortBean , may be null.
     * @return 0 , never null.
     * </pre>
     */
    public final int compareTo(final Object objectOne, final Object objectTwo, final NGAFPaginationSortBean sortBean) {
        if (objectOne != null && objectTwo != null) {
            SearchStandbyPriorityVO searchStandbyPriorityVOOne = (SearchStandbyPriorityVO) objectOne;
            SearchStandbyPriorityVO searchStandbyPriorityVOTwo = (SearchStandbyPriorityVO) objectTwo;
            int column = sortBean.getColumnIndex();
            switch (column) {
                case 0:
                    return ((searchStandbyPriorityVOOne.getAirlineCode()).compareTo(searchStandbyPriorityVOTwo
                        .getAirlineCode()));

                case 1:
                    return ((searchStandbyPriorityVOOne.getAirlineName()).compareTo(searchStandbyPriorityVOTwo
                        .getAirlineName()));

                default:
                    break;
            }

        }
        return 0;
    }

    /**
     * Loads data to the Standby Priority Filter Table.
     * 
     * <pre>
     * Loads data to the Standby Priority Filter Table.
     * 
     * @param airlineCode , not null.
     * @param airlineName , not null.
     * @return searchStandbyPriorityVO , never null.
     * </pre>
     */
    private Object addAirlines(final String airlineCode, final String airlineName) {
        final SearchStandbyPriorityVO searchStandbyPriorityVO = new SearchStandbyPriorityVO();

        searchStandbyPriorityVO.setAirlineCode(airlineCode);
        searchStandbyPriorityVO.setAirlineName(airlineName);
        return searchStandbyPriorityVO;
    }

}
