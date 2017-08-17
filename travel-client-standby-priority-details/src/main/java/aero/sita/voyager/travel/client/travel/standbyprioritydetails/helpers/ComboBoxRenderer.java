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

/**
 * combobox renderer.
 * 
 * <pre>
 * <b>Description : </b>
 * combobox renderer.
 * 
 * @version $Revision: 1 $ $Date: 2011-11-03 02:24:13 PM $
 * @author $Author: user.name $
 * </pre>
 */
public class ComboBoxRenderer extends JComboBox implements TableCellRenderer {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor for ComboBoxRenderer.
     * 
     * <pre>
     * <b>Description : </b>
     * <<WRITE DESCRIPTION HERE>>
     * 
     * @param items , not null.
     * </pre>
     */
    public ComboBoxRenderer(final String[] items) {
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
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
        final boolean hasFocus, final int row, final int column) {
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
