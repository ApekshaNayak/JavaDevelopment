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

public class DefaultStandbyPriorityVO {
    
    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;
    /**
     * isDefault.
     */
    private boolean isDefaultSP;
    /**
     * priorityStatusCode.
     */
    private String priorityStatusCode;
    /**
     * priorityStatusDesc.
     */
    private String priorityStatusDesc;
    /**
     * canBeDisembarked.
     */
    private String canBeDisembarked;
    /**
     * dateOfJoining.
     */
    private String dateOfJoining;
    /**
     * bookedPriorityValue.
     */
    private int bookedPriorityValue;
    /**
     * bookedCanBeDisembarked.
     */
    private String bookedCanBeDisembarked;
    /**
     * noRecPriorityValue.
     */
    private int noRecPriorityValue;
    /**
     * noRecCanBeDisembarked.
     */
    private String noRecCanBeDisembarked;
    /**
     * waitlistPriorityValue.
     */
    private int waitlistPriorityValue;
    /**
     * waitlistCanBeDisembarked.
     */
    private String waitlistCanBeDisembarked;
    /**
     * openTicketPriorityValue.
     */
    private int openTicketPriorityValue;
    /**
     * openTicketCanBeDisembarked.
     */
    private String openTicketCanBeDisembarked;
    /**
     * holdsSpaceAvailableTicket.
     */
    private String holdsSpaceAvailableTicket;
    /**
     * documentId.
     */
    private String documentId;
    /**
     * rowNumber.
     */
    private int rowNumber;
    /**
     * isCreateUpdateEnabled.
     */
    private boolean isCreateUpdateEnabled;

    /**
     * Gets the isDefault.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the isDefault.
     * @return isDefault  , never null.
     * </pre>
     */
    public final boolean isDefault() {
        return isDefaultSP;
    }

    /**
     * Sets the isDefault.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the isDefault.
     * @param isDefaultSPParam , not null.
     * 
     * </pre>
     */
    public final void setDefault(final boolean isDefaultSPParam) {
        isDefaultSP = isDefaultSPParam;
    }

    /**
     * Gets the priorityStatusCode.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the priorityStatusCode.
     * @return priorityStatusCode , never null.
     * </pre>
     */
    public final String getPriorityStatusCode() {
        return priorityStatusCode;
    }

    /**
     * Sets the priorityStatusCode.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the priorityStatusCode.
     * @param priorityStatusCodeParam , not null.
     * 
     * </pre>
     */
    public final void setPriorityStatusCode(final String priorityStatusCodeParam) {
        priorityStatusCode = priorityStatusCodeParam;
    }

    /**
     * Gets the priorityStatusDesc.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the priorityStatusDesc.
     * @return priorityStatusDesc , never null.
     * </pre>
     */
    public final String getPriorityStatusDesc() {
        return priorityStatusDesc;
    }

    /**
     * Sets the priorityStatusDesc.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the priorityStatusDesc.
     * @param priorityStatusDescParam , not null.
     * 
     * </pre>
     */
    public final void setPriorityStatusDesc(final String priorityStatusDescParam) {
        priorityStatusDesc = priorityStatusDescParam;
    }

    /**
     * Gets the canBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the canBeDisembarked.
     * @return canBeDisembarked , never null.
     * </pre>
     */
    public final String getCanBeDisembarked() {
        return canBeDisembarked;
    }

    /**
     * Sets the canBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the canBeDisembarked.
     * @param canBeDisembarkedParam , not null.
     * 
     * </pre>
     */
    public final void setCanBeDisembarked(final String canBeDisembarkedParam) {
        canBeDisembarked = canBeDisembarkedParam;
    }

    /**
     * Gets the dateOfJoining.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the dateOfJoining.
     * @return dateOfJoining , never null.
     * </pre>
     */
    public final String getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * Sets the dateOfJoining.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the dateOfJoining.
     * @param dateOfJoiningParam , not null.
     * 
     * </pre>
     */
    public final void setDateOfJoining(final String dateOfJoiningParam) {
        dateOfJoining = dateOfJoiningParam;
    }

    /**
     * Gets the bookedPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the bookedPriorityValue.
     * @return bookedPriorityValue , never null.
     * </pre>
     */
    public final int getBookedPriorityValue() {
        return bookedPriorityValue;
    }

    /**
     * Sets the bookedPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the bookedPriorityValue.
     * @param bookedPriorityValueParam , not null.
     * 
     * </pre>
     */
    public final void setBookedPriorityValue(final int bookedPriorityValueParam) {
        bookedPriorityValue = bookedPriorityValueParam;
    }

    /**
     * Gets the bookedCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the bookedCanBeDisembarked.
     * @return bookedCanBeDisembarked , never null.
     * </pre>
     */
    public final String getBookedCanBeDisembarked() {
        return bookedCanBeDisembarked;
    }

    /**
     * Sets the bookedCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the bookedCanBeDisembarked.
     * @param bookedCanBeDisembarkedParam , not null.
     * 
     * </pre>
     */
    public final void setBookedCanBeDisembarked(final String bookedCanBeDisembarkedParam) {
        bookedCanBeDisembarked = bookedCanBeDisembarkedParam;
    }

    /**
     * Gets the noRecPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the noRecPriorityValue.
     * @return noRecPriorityValue , never null.
     * </pre>
     */
    public final int getNoRecPriorityValue() {
        return noRecPriorityValue;
    }

    /**
     * Sets the noRecPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the noRecPriorityValue.
     * @param noRecPriorityValueParam , not null.
     * 
     * </pre>
     */
    public final void setNoRecPriorityValue(final int noRecPriorityValueParam) {
        noRecPriorityValue = noRecPriorityValueParam;
    }

    /**
     * Gets the noRecCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the noRecCanBeDisembarked.
     * @return noRecCanBeDisembarked  , never null.
     * </pre>
     */
    public final String getNoRecCanBeDisembarked() {
        return noRecCanBeDisembarked;
    }

    /**
     * Sets the noRecCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the noRecCanBeDisembarked.
     * @param noRecCanBeDisembarkedParam , not null.
     * 
     * </pre>
     */
    public final void setNoRecCanBeDisembarked(final String noRecCanBeDisembarkedParam) {
        noRecCanBeDisembarked = noRecCanBeDisembarkedParam;
    }

    /**
     * Gets the waitlistPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the waitlistPriorityValue.
     * @return waitlistPriorityValue , never null.
     * </pre>
     */
    public final int getWaitlistPriorityValue() {
        return waitlistPriorityValue;
    }

    /**
     * Sets the waitlistPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the waitlistPriorityValue.
     * @param waitlistPriorityValueParam , not null.
     * 
     * </pre>
     */
    public final void setWaitlistPriorityValue(final int waitlistPriorityValueParam) {
        waitlistPriorityValue = waitlistPriorityValueParam;
    }

    /**
     * Gets the waitlistCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the waitlistCanBeDisembarked.
     * @return waitlistCanBeDisembarked  , never null.
     * </pre>
     */
    public final String getWaitlistCanBeDisembarked() {
        return waitlistCanBeDisembarked;
    }

    /**
     * Sets the waitlistCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the waitlistCanBeDisembarked.
     * @param waitlistCanBeDisembarkedParam , not null.
     * 
     * </pre>
     */
    public final void setWaitlistCanBeDisembarked(final String waitlistCanBeDisembarkedParam) {
        waitlistCanBeDisembarked = waitlistCanBeDisembarkedParam;
    }

    /**
     * Gets the openTicketPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the openTicketPriorityValue.
     * @return openTicketPriorityValue , never null.
     * </pre>
     */
    public final int getOpenTicketPriorityValue() {
        return openTicketPriorityValue;
    }

    /**
     * Sets the openTicketPriorityValue.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the openTicketPriorityValue.
     * @param openTicketPriorityValueParam , not null.
     * 
     * </pre>
     */
    public final void setOpenTicketPriorityValue(final int openTicketPriorityValueParam) {
        openTicketPriorityValue = openTicketPriorityValueParam;
    }

    /**
     * Gets the openTicketCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the openTicketCanBeDisembarked.
     * @return openTicketCanBeDisembarked  , never null.
     * </pre>
     */
    public final String getOpenTicketCanBeDisembarked() {
        return openTicketCanBeDisembarked;
    }

    /**
     * Sets the openTicketCanBeDisembarked.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the openTicketCanBeDisembarked.
     * @param openTicketCanBeDisembarkedParam , not null.
     * 
     * </pre>
     */
    public final void setOpenTicketCanBeDisembarked(final String openTicketCanBeDisembarkedParam) {
        openTicketCanBeDisembarked = openTicketCanBeDisembarkedParam;
    }

    /**
     * Gets the holdsSpaceAvailableTicket.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the holdsSpaceAvailableTicket.
     * @return holdsSpaceAvailableTicket , never null.
     * </pre>
     */
    public final String getHoldsSpaceAvailableTicket() {
        return holdsSpaceAvailableTicket;
    }

    /**
     * Sets the holdsSpaceAvailableTicket.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the holdsSpaceAvailableTicket.
     * @param spaceAvailableTktParam , not null.
     * 
     * </pre>
     */
    public final void setHoldsSpaceAvailableTicket(final String spaceAvailableTktParam) {
        holdsSpaceAvailableTicket = spaceAvailableTktParam;
    }

    /**
     * Gets the documentId.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the documentId.
     * @return documentId , never null.
     * </pre>
     */
    public final String getDocumentId() {
        return documentId;
    }

    /**
     * Sets the documentId.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the documentId.
     * @param documentIdParam , not null.
     * 
     * </pre>
     */
    public final void setDocumentId(final String documentIdParam) {
        documentId = documentIdParam;
    }

    /**
     * Gets the rowNumber.
     * 
     * <pre>
     * <b>Description : </b>
     * Gets the rowNumber.
     * @return rowNumber , never null.
     * </pre>
     */
    public final int getRowNumber() {
        return rowNumber;
    }

    /**
     * Sets the rowNumber.
     * 
     * <pre>
     * <b>Description : </b>
     * Sets the rowNumber.
     * @param rowNumberParam , not null.
     * 
     * </pre>
     */
    public final void setRowNumber(final int rowNumberParam) {
        rowNumber = rowNumberParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * CreateUpdateEnabled status.
     * 
     * @param isCreateUpdateEnabledparam , not null.
     * </pre>
     */
    public void setCreateUpdateEnabled(final boolean isCreateUpdateEnabledparam) {
        isCreateUpdateEnabled = isCreateUpdateEnabledparam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * CreateUpdateEnabled status.
     * 
     * @return isCreateUpdateEnabled , never null.
     * </pre>
     */
    public boolean isCreateUpdateEnabled() {
        return isCreateUpdateEnabled;
    }
}
