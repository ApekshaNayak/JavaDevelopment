/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.valueobject;

/**
 * 
 * <pre>
 * <b>Description : </b>
 * Value Object for Standby Priority.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-08 05:18:39 PM $
 * @author $Author: abirami.rajaram $
 * </pre>
 */
public class SearchStandbyPriorityVO {
    /**
     * Logger instance for the class.
     */
    // private static final SITAGUILogger LOGGER =
    // SITAGUILoggerFactory.getLogger(SearchStandbyPriorityVO.class);
    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;
    /**
     * airlineCode.
     */
    private String airlineCode;
    /**
     * airlineName.
     */
    private String airlineName;

    /**
     * Gets the airlineCode.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the airlineCode.
     * @return airlineCode , never null.
     * </pre>
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * Sets the airlineCode.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the airlineCode.
     * @param airlineCodeParam , not null.
     * 
     * </pre>
     */
    public void setAirlineCode(final String airlineCodeParam) {
        airlineCode = airlineCodeParam;
    }

    /**
     * Gets the airlineName.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the airlineName.
     * @return airlineName , never null.
     * </pre>
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     * Sets the airlineName.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the airlineName.
     * @param airlineNameParam , not null.
     * 
     * </pre>
     */
    public void setAirlineName(final String airlineNameParam) {
        airlineName = airlineNameParam;
    }
}
