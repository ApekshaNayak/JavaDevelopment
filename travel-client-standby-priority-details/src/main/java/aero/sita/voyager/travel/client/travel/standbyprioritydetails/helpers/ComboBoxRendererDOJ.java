/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.helpers;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;

/**
 * <pre>
 * <b>Description : </b>
 * This Class is to render the Combobox.
 * 
 * @version $Revision: 1 $ $Date: 2012-01-06 11:16:02 PM $
 * @author $Author: bikoo.changmai $ 
 * </pre>
 */
public class ComboBoxRendererDOJ extends JComboBox implements TableCellRenderer {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor for ComboBoxRenderer.
     * 
     * <pre>
     * <b>Description : </b>
     *
     * 
     * @param items , not null.
     * </pre>
     */
    public ComboBoxRendererDOJ(final String[] items) {
        super(items);
    }

    /**
     * returns a tablecellrenderer component.
     * 
     * <pre>
     * <b>Description : </b>
     * <<WRITE DESCRIPTION HERE>>
     * 
     * @param table , not null.
     * @param value , not null.
     * @param isSelected , not null.
     * @param hasFocus , not null.
     * @param row , not null.
     * @param column , not null.
     * @return
     * </pre>
     */
    public final Component getTableCellRendererComponent(final JTable table, final Object value,
        final boolean isSelected, final boolean hasFocus, final int row, final int column) {

        if (value instanceof String) {

            if (value.toString().equalsIgnoreCase(StandbyPriorityConstants.DOJ_OPTIONAL)) {
                this.setSelectedIndex(0);
            }
            else if (value.toString().equalsIgnoreCase(StandbyPriorityConstants.DOJ_NOT_REQUIRED)) {
                this.setSelectedIndex(1);
            }
        }

        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }
        else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        // Select the current value
        setSelectedItem(value);
        return this;
    }
}
