/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbypriority.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * <pre>
 * <b>Description : </b>
 * Listener to convert to upper case.
 * 
 * @version $Revision: 1 $ $Date: 2012-01-24 06:46:57 PM $
 * @author $Author: timmesh.kurmayya $ 
 * </pre>
 */
public class UpperCaseFilter extends DocumentFilter {
    /**
     * <pre>
     * <b>Description : </b>
     * Override insertString method of DocumentFilter to make the text format to uppercase.
     * 
     * @param fb , may be null
     * @param offset , may be null
     * @param text , may be null
     * @param attr , may be null
     * @throws BadLocationException , null if not found.
     * </pre>
     */
    public void insertString(final DocumentFilter.FilterBypass fb, final int offset, final String text,
        final AttributeSet attr) throws BadLocationException {
        // fb.insertString(offset, text.toUpperCase(), attr);
        fb.insertString(offset, text, attr);
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Override replace method of DocumentFilter to make the text format to uppercase.
     * @param fb , may be null
     * @param offset , may be null
     * @param length , may be null
     * @param text , may be null
     * @param attr , may be null
     * @throws BadLocationException , null if not found.
     * </pre>
     */
    public void replace(final DocumentFilter.FilterBypass fb, final int offset, final int length, final String text,
        final AttributeSet attr) throws BadLocationException {
        fb.replace(offset, length, text.toUpperCase(), attr);
    }
}