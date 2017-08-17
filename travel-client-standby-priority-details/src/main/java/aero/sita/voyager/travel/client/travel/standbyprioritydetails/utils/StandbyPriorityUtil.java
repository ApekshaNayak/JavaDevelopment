/*
 * This code contains copyright information which is the proprietary property
 * of SITA Information Network Computing Limited (SITA). No part of this
 * code may be reproduced, stored or transmitted in any form without the prior
 * written permission of SITA.
 *
 * Copyright (C) SITA Information Network Computing Limited 2010-2011.
 * All rights reserved.
 */
package aero.sita.voyager.travel.client.travel.standbyprioritydetails.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import aero.sita.csp.gui.interfaces.logger.SITAGUILogger;
import aero.sita.csp.gui.interfaces.logger.SITAGUILoggerFactory;
import aero.sita.voyager.ngafoundation.client.parentframe.util.NGAFParentControllerUtil;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.controllers.StandbyPriorityDetailsController;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.CancelDescriptionListener;
import aero.sita.voyager.travel.client.travel.standbyprioritydetails.listeners.SaveDescriptionListener;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * <pre>
 * <b>Description : </b> Util class for New Standby Priority.
 * 
 * @version $Revision: 1 $ $Date: 2011-12-08 05:26:05 PM $
 * @author $Author: nandinee.ramanathan $
 * </pre>
 */
public class StandbyPriorityUtil {

    /**
     * Logger for the class.
     */
    private static final SITAGUILogger LOGGER = SITAGUILoggerFactory.getLogger(StandbyPriorityUtil.class);

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constant for dialog box width.
     */
    private static final int JDIALOG_BOX_SIZE_WIDTH = 460;

    /**
     * Constant for dialog box height.
     */
    private static final int JDIALOG_BOX_SIZE_HEIGHT = 365;

    /**
     * Constant for text area row size.
     */
    private static final int TEXT_AREA_ROW = 40;

    /**
     * Constant for Text area column size.
     */
    private static final int TEXT_AREA_COL = 20;

    /**
     * Constant for Text area column size.
     */
    private static final int TEXT_AREA_ROW_SIZE = 20;

    /**
     * Constant for Text location x point.
     */
    private static final int DIALOG_LOCATION_X_AXIS = 400;

    /**
     * Constant for Text location y pint.
     */
    private static final int DIALOG_LOCATIO_Y_AXIS = 200;

    /**
     * Description.
     */
    private static final String DESCRIPTION = "Description";
    /**
     *  jDialog.
     */
    JDialog jDialog;
    /**
     * Dialog for showing priority.
     */
    private JDialog priorityDialogBox;

    /**
     * Panel for Label.
     */
    private JPanel labelNameHolder = new JPanel();

    /**
     * Panel for TextArea.
     */
    private JPanel textAreaHolder = new JPanel();

    /**
     * Panel for Buttons.
     */
    private JPanel buttonHolder = new JPanel();

    /**
     * Panel for main Panel.
     */
    private JPanel mainPanel = new JPanel();

    /**
     * Main Panel.
     */
    private JPanel mainPanelConstruct;

    /**
     * Save Button.
     */
    private JButton saveButton;

    /**
     * Scroll pane for the text area.
     */
    private JScrollPane scrollTextArea;

    /**
     * Text area for the priority.
     */
    private JTextArea priorityTextArea;

    /**
     * LABEL_VALUE.
     */
    private String labelValue = "Priority code ";

    /**
     * Instance of StandbyPriorityController.
     */
    private StandbyPriorityDetailsController controller;

    /**
     * Cancel button.
     */
    private JButton cancelButton;

    /**
     * Category.
     */
    private String category;

    /**
     * String view.
     */
    //private String view = "view";
    /**
     * String edit.
     */
    //private String edit = "edit";
    /**
     * Empty Constructor.
     */



    public StandbyPriorityUtil() {

    }

    /**
     * <pre>
     * <b>Description : </b>
     * Create Dialog Box for the view and edit priority.
     * 
     * @param parentFram , not null.
     * @param description , not null.
     * @param priorityCode , not null.
     * @param controllerInstance , not null.
     * @param selectedRow , not null.
     * </pre>
     */
    public StandbyPriorityUtil(final Frame parentFram, final String description, final String priorityCode,
        final StandbyPriorityDetailsController controllerInstance, final int selectedRow) {
        jDialog = new JDialog(parentFram, DESCRIPTION, true);
        StandbyPriorityUtil.LOGGER.debug("Entered StandbyPriorityUtil.createDialog");

        jDialog.setLocationRelativeTo(jDialog.getOwner());
        jDialog.setLocation(StandbyPriorityUtil.DIALOG_LOCATION_X_AXIS, StandbyPriorityUtil.DIALOG_LOCATIO_Y_AXIS);
        jDialog.setResizable(false);
        jDialog.setModal(true);
        createPriorityPanel(description, priorityCode);
        jDialog.add(mainPanel);
        jDialog.pack();

        SaveDescriptionListener saveDescription = new SaveDescriptionListener();
        saveDescription.setController(controllerInstance);
        saveButton.addActionListener(saveDescription);
        saveDescription.setSelectedRow(selectedRow);
        saveDescription.setPriorityTextArea(priorityTextArea);
        saveDescription.setPriorityDialogBox(jDialog);

        CancelDescriptionListener cancelDescription = new CancelDescriptionListener();
        cancelDescription.setPriorityDialogBox(jDialog);
        cancelButton.addActionListener(cancelDescription);
        jDialog.setVisible(true);

        StandbyPriorityUtil.LOGGER.debug("Exiting StandbyPriorityUtil.createDialog");
    }

    /**
     * Create the Priority Panel.
     * 
     * <pre>
     * <b>Description : </b>
     * Create the Priority Panel.
     * 
     * @param description , not null.
     * @param priorityCode , not null.
     * </pre>
     */
    private void createPriorityPanel(final String description, final String priorityCode) {
        mainPanelConstruct = new JPanel();
        mainPanelConstruct.setLayout(new BorderLayout());
        labelNameHolder.setLayout(new BorderLayout());
        textAreaHolder.setLayout(new BorderLayout());
        buttonHolder.setLayout(new BorderLayout());

        mainPanelConstruct.add(labelNameHolder, BorderLayout.NORTH);
        mainPanelConstruct.add(textAreaHolder, BorderLayout.CENTER);
        mainPanelConstruct.add(buttonHolder, BorderLayout.SOUTH);
        mainPanelConstruct.setPreferredSize(new Dimension(StandbyPriorityUtil.JDIALOG_BOX_SIZE_WIDTH,
            StandbyPriorityUtil.JDIALOG_BOX_SIZE_HEIGHT));

        final JLabel priorityLabelName = new JLabel(labelValue + priorityCode);
        labelNameHolder.add(priorityLabelName, BorderLayout.WEST);

        priorityTextArea = new JTextArea(StandbyPriorityUtil.TEXT_AREA_COL, StandbyPriorityUtil.TEXT_AREA_ROW);
        priorityTextArea.setLineWrap(true);
        priorityTextArea.setRows(TEXT_AREA_ROW_SIZE);
        priorityTextArea.setText(description);

        scrollTextArea = new JScrollPane(priorityTextArea);
        scrollTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        priorityTextArea.setEditable(true);
        saveButton.setVisible(true);

        textAreaHolder.add(scrollTextArea, BorderLayout.CENTER);
        jDialog.setLayout(new BorderLayout());

        buttonHolder.add(saveButton, BorderLayout.WEST);
        buttonHolder.add(cancelButton, BorderLayout.EAST);

        // Main Panel Creation.
        mainPanel.setLayout(new FormLayout("6dlu,pref,6dlu", "6dlu,pref,6dlu"));
        final CellConstraints cellConstraintsMainPanel = new CellConstraints();
        mainPanel.add(mainPanelConstruct, cellConstraintsMainPanel.xy(2, 2));
    }

    /**
    * 
    * <pre>
    * <b>Description : </b>
    * This Method is to ask for confirmation on delete.
    * 
    * @param title , not null.
    * @param customisedMsg , not null.
    * @return option , never null.
    * </pre>
    */
    public int confirmDelete(final String title, final String customisedMsg) {
        StandbyPriorityUtil.LOGGER.debug("Enter StandbyPriorityUtil.confirmDelete");

        final JFrame parentFrame = (JFrame) NGAFParentControllerUtil.getParentController().getNGAFParentFrame();
        final int option = createOptionForConfirmation(parentFrame, title, customisedMsg);

        parentFrame.repaint();
        StandbyPriorityUtil.LOGGER.debug("Exit StandbyPriorityUtil.confirmDelete");

        return option;
    }

    /**
    * 
    * <pre>
    * <b>Description : </b>
    * This Method is to create option for confirmation.
    * 
    * @param parentFrame , not null.
    * @param title , not null.
    * @param customisedMsg , not null.
    * @return confirmationValue , never null.
    * </pre>
    */
    private static int createOptionForConfirmation(final JFrame parentFrame, final String title,
        final String customisedMsg) {
        final String[] deleteButtons = { "Delete", "Do Not Delete" };
        final String[] saveButtons = { "Save", "Do Not Save" };
        int confirmationValue;

        if (!title.isEmpty()) {
            confirmationValue = JOptionPane.showOptionDialog(parentFrame, customisedMsg, title, 0,
                JOptionPane.PLAIN_MESSAGE, null, deleteButtons, deleteButtons[1]);
        }
        else {
            confirmationValue = JOptionPane.showOptionDialog(parentFrame, customisedMsg, title, 0,
                JOptionPane.WARNING_MESSAGE, null, saveButtons, saveButtons[0]);
        }

        return confirmationValue;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'category' attribute value.
     * 
     * @return category , null if not found.
     * </pre>
     */
    public final String getCategory() {
        return category;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'category' attribute value.
     * 
     * @param categoryParam , may be null.
     * </pre>
     */
    public final void setCategory(final String categoryParam) {
        category = categoryParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'controller' attribute value.
     * 
     * @return controller , null if not found.
     * </pre>
     */
    public final StandbyPriorityDetailsController getController() {
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
    public void setController(final StandbyPriorityDetailsController controllerParam) {
        controller = controllerParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'priorityTextArea' attribute value.
     * 
     * @return priorityTextArea , null if not found.
     * </pre>
     */
    public JTextArea getpriorityTextArea() {
        return priorityTextArea;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'priorityTextArea' attribute value.
     * 
     * @param priorityTextAreaParam , may be null.
     * </pre>
     */
    public final void setpriorityTextArea(final JTextArea priorityTextAreaParam) {
        priorityTextArea = priorityTextAreaParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Get the 'priorityDialogBox' attribute value.
     * 
     * @return priorityDialogBox , null if not found.
     * </pre>
     */
    public final JDialog getpriorityDialogBox() {
        return priorityDialogBox;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * Set the 'priorityDialogBox' attribute value.
     * 
     * @param priorityDialogBoxParam , may be null.
     * </pre>
     */
    public final void setpriorityDialogBox(final JDialog priorityDialogBoxParam) {
        priorityDialogBox = priorityDialogBoxParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * This method marshalls the DTO to XML.
     * 
     * @param beanToMarshall , may be null.
     * @param marshallType , may be null.
     * </pre>
     */
    //    public void marshallDTOToXML(final Object beanToMarshall, final Class marshallType) {
    //
    //       LOGGER.info("marshallDTOToXML entry");
    //
    //        PrintWriter resWriter = null;
    //
    //        try {
    //            IBindingFactory bindingFactory = null;
    //            String outputFileName = null;
    //
    //            final String typeName = marshallType.getSimpleName();
    //            outputFileName = "D:/" + typeName + ".xml";
    //
    //            bindingFactory = BindingDirectory.getFactory(marshallType);
    //            IMarshallingContext marshalCtx = bindingFactory.createMarshallingContext();
    //
    //            final File resFile = new File(outputFileName);
    //            resWriter = new PrintWriter(new BufferedWriter(new FileWriter(resFile)));
    //
    //            LOGGER.info(resWriter);
    //            marshalCtx.marshalDocument(beanToMarshall, null, null, resWriter);
    //        } catch (BeansException ex) {
    //            //ex.printStackTrace();
    //            LOGGER.error(ex);
    //        } catch (JiBXException ex) {
    //            //ex.printStackTrace();
    //            LOGGER.error(ex);
    //        } catch (IOException ex) {
    //            //ex.printStackTrace();
    //            LOGGER.error(ex);
    //        } catch (Exception ex) {
    //            //ex.printStackTrace();
    //            LOGGER.error(ex);
    //        }
    //        if (resWriter != null) {
    //            try {
    //                resWriter.close();
    //            } catch (Exception ex) {
    //                LOGGER.error(ex);
    //            }
    //        }
    //
    //        LOGGER.info("marshallDTOToXML exit");
    //    }

    /**
     * Unmarshals an xml file at specified inputFileName to a DTO of class type
     * marshallType.
     * 
     * <pre>
     * <b>Description : </b>
     * Unmarshals an xml file at specified  inputFileName to a DTO of class type marshallType.
     * 
     * @param marshallType : Type of the DTO , not null.
     * @param inputFileNameParam : inputFlie path , not null.
     * @return : DTO object that has been unmarshalled , null if not found.
     * </pre>
     */
    //    public Object unMarshallXMLToDTO(final Class marshallType, String inputFileNameParam) {
    //        LOGGER.debug("unMarshallXMLToDTO entry");
    //
    //        Object object = null;
    //        FileReader fileReader = null;
    //
    //        try {
    //            IBindingFactory bindingFactory = null;
    //            String defaultDirectory = "D:/";
    //
    //            if (StringUtil.isEmpty(inputFileNameParam)) {
    //                inputFileNameParam = defaultDirectory + "response.xml";
    //            } 
    //            else {
    //                inputFileNameParam = defaultDirectory + inputFileNameParam;
    //            }
    //
    //            LOGGER.debug("inputFileName " + inputFileNameParam);
    //
    //            bindingFactory = BindingDirectory.getFactory(marshallType);
    //            IUnmarshallingContext unMarshalCtx = bindingFactory.createUnmarshallingContext();
    //
    //            final File resFile = new File(inputFileNameParam);
    //            fileReader = new FileReader(resFile);
    //            object = unMarshalCtx.unmarshalDocument(fileReader);
    //        } 
    //        catch (BeansException ex) {
    //            // e.printStackTrace();
    //            LOGGER.error(ex);
    //        } 
    //        catch (JiBXException ex) {
    //            // e.printStackTrace();
    //            LOGGER.error(ex);
    //        } 
    //        catch (IOException ex) {
    //            // e.printStackTrace();
    //            LOGGER.error(ex);
    //        } 
    //        catch (Exception ex) {
    //            // e.printStackTrace();
    //            LOGGER.error(ex);
    //        } 
    //        finally {
    //            if (fileReader != null) {
    //                try {
    //                    fileReader.close();
    //                } 
    //                catch (Exception ex) {
    //                    LOGGER.error(ex);
    //                }
    //            }
    //        }
    //
    //        LOGGER.debug("unMarshallXMLToDTO exit");
    //
    //        return object;
    //    }
}
