/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.controllerAldComputerService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.modelAldComputerService.AldComputerService;
import view.viewAldComputerService.AldComputerServiceView;

/**
 *
 * @author barreirolistediego
 */
public class AldComputerServiceController {

    private final AldComputerServiceView view;
    private final AldComputerService service;

    public AldComputerServiceController(AldComputerServiceView aldcsView, AldComputerService model) {
        this.view = aldcsView;
        this.service = model;
        this.view.setSaveButtonItemActionListener(this.saveButtonListener());
        this.view.setEditButtonItemActionListener(this.editButtonListener());
        this.view.setCancelButtonItemActionListener(this.cancelButtonListener());
        this.serviceUpdateUI();
    }

    private ActionListener saveButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateServiceData()) {
                    createNewService();
                    serviceUpdateUI();
                }
            }
        };
    }

    private ActionListener editButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageStatusOfAllTextFields(true);
                view.setEnabledEditButton(false);
                view.setEnabledSaveButton(true);
            }
        };
    }

    private ActionListener cancelButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
    }

    private boolean validateServiceData() {
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(view, "Please complete required fields.");
            return false;
        }

        String numberOfEmployeesText = view.getNumberOfEmployeesTextField().trim();
        try {
            int numberOfEmployees = Integer.parseInt(numberOfEmployeesText);
            if (numberOfEmployees <= 0) {
                JOptionPane.showMessageDialog(view, "The number of employees cannot be negative.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid number of employees.");
            return false;
        }

        return true;
    }

    private void createNewService() {
        if (validateServiceData()) {
            service.setAddress(view.getTextAddressTextField());
            service.setEstablishmentName(view.getTextEstablishmentNameTextField());
            service.setPhone(view.getPhoneTextField());
            service.setNumberOfEmployees(Integer.parseInt(view.getNumberOfEmployeesTextField()));
        }
    }

    private void sendRepairInfoToForm(AldComputerService service) {
        view.setTextAddressTextField(service.getAddress());
        view.setTextEstablishmentNameTextField(service.getEstablishmentName());
        view.setPhoneTextField(service.getPhone());
        view.setNumberOfEmployeesTextField(String.valueOf(service.getNumberOfEmployees()));
    }

    private void ManageStatusOfAllTextFields(boolean status) {
        view.setEditableAddressTextField(status);
        view.setEditableEstablishmentNameTextField(status);
        view.setEditableNumberOfEmployeesTextField(status);
        view.setEditablePhoneTextField(status);
    }

    private void serviceUpdateUI() {
        sendRepairInfoToForm(service);
        if (areFieldsEmpty()) {
            view.setEnabledEditButton(false);
            view.setEnabledSaveButton(true);
            ManageStatusOfAllTextFields(true);
        } else {
            view.setEnabledEditButton(true);
            view.setEnabledSaveButton(false);
            ManageStatusOfAllTextFields(false);
        }
        view.setEnabledCancelButton(true);
    }

    private boolean areFieldsEmpty() {
        return view.getTextAddressTextField().isEmpty()
                || view.getTextEstablishmentNameTextField().isEmpty()
                || view.getPhoneTextField().isEmpty()
                || view.getNumberOfEmployeesTextField().isEmpty();
    }

}
