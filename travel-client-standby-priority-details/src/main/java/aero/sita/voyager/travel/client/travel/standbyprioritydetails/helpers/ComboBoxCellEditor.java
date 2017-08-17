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

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import aero.sita.csp.gui.thickclient.util.StringUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.constants.StandbyPriorityConstants;


/**
 * <pre>
 * <b>Description : </b>
 * This Class is to edit the cell of  Combobox.
 * 
 * @version $Revision: 1 $ $Date: 2012-01-06 11:16:02 PM $
 * @author $Author: bikoo.changmai $ 
 * </pre>
 */
public class ComboBoxCellEditor extends DefaultCellEditor implements TableCellEditor {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * <b>Description : </b>
     * Constructs an instance of 'ComboBoxCellEditor'.
     * 
     * @param combobox , not null.
     * </pre>
     */
    public ComboBoxCellEditor(final JComboBox combobox) {
        super(combobox);
    }

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * This Method is to get the table cell Editor Component.
     * 
     * @param table , not null.
     * @param value , not null.
     * @param isSelected , not null.
     * @param row , not null.
     * @param column , not null.
     * @return Component , never null.
     * </pre>
     */
    @Override
    public final Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
        final int row, final int column) {

        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public final Object getCellEditorValue() {
        Object cellValue = super.getCellEditorValue();
        JComboBox combobox = (JComboBox) getComponent();
        String selectedItem = (String) combobox.getSelectedItem();

        if (StringUtil.isNotEmpty(selectedItem)) {
            String defaultValue = selectedItem;

            if (StandbyPriorityConstants.YES_VALUE.equalsIgnoreCase(defaultValue)) {
                cellValue = StandbyPriorityConstants.YES_VALUE;
            }
            else if (StandbyPriorityConstants.NO_VALUE.equalsIgnoreCase(defaultValue)) {
                cellValue = StandbyPriorityConstants.NO_VALUE;
            }
        }

        return cellValue;
    }
}
