/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Repairs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.viewComputersAndRepairs.ComputersAndRepairsView;
import model.modelAldComputerService.AldComputerService;
import model.modelAldComputerService.Computer;
import model.modelAldComputerService.Repair;

/**
 *
 * @author barreirolistediego
 */
public final class RepairsController {

    private final ComputersAndRepairsView view;
    private final AldComputerService service;
    private Repair currentRepair;
    private Computer currentComputer;

    public RepairsController(ComputersAndRepairsView view, AldComputerService service) {
        this.view = view;
        this.service = service;
        this.view.addRpAddRepairsTableButtonListener(this.addRepairButtonListener());
        this.view.addRpEditRepairsTableButtonListener(this.editRepairButtonListener());
        this.view.addRpDeleteRepairsTableButtonListener(this.deleteRepairButtonListener());
        this.view.addRpGetInfoRepairsTableButtonListener(this.getInfoRepairButtonListener());
        this.view.addRpSaveButtonListener(this.saveRepairButtonListener());
        this.view.addRpCancelButtonListener(this.cancelRepairButtonListener());
        this.view.addRepairTypeComboBoxActionListener(this.repairTypeComboBoxActionListener());
        this.view.addRpRepairsTableSelectionListener(this.repairsTableSelectionListener());
        this.rpResetView();
    }

    private ListSelectionListener repairsTableSelectionListener() {
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = view.getSelectedRowInRepairsTable();
                    if (selectedRow != -1 && selectedRow < currentComputer.getRepairs().size()) {
                        currentRepair = currentComputer.getRepairs().get(selectedRow);
                    } else {
                        currentRepair = null;
                    }
                    view.setSelectedRowInRepairsTable(-1);
                    rpUpdateUI();
                }
            }
        };
        return lsl;
    }

    private ActionListener repairTypeComboBoxActionListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedType = view.getRepairTypeComboBox().getSelectedItem().toString();
                view.rpFormVisibilityAutoManage(selectedType);
            }
        };
        return al;
    }

    private ActionListener addRepairButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rpResetView();
                currentComputer = getSelectedComputer();
                setFormVisibilityOnRepairs(true);
            }
        };
        return al;
    }

    private ActionListener editRepairButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFormVisibilityOnRepairs(true);
                sendRepairInfoToForm(currentRepair);
            }
        };
        return al;
    }

    private ActionListener deleteRepairButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this repair?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION && currentRepair != null) {
                    currentComputer.getRepairs().remove(currentRepair);
                    rpResetView();
                }
            }
        };
        return al;
    }

    private ActionListener getInfoRepairButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentRepair != null) {
                    JOptionPane.showMessageDialog(view, currentRepair.toString(), "Repair Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
    }

    private ActionListener saveRepairButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentComputer = getSelectedComputer();
                if (validateRepairData()) {
                    Repair newRepair = createNewRepair();
                    if (currentRepair != null) {
                        currentRepair.setType(view.getRepairTypeComboBox().getSelectedItem().toString());
                        currentRepair.setDischargeDate(view.getRpDischargeDate());
                        currentRepair.setContactPerson(view.getRpContactPerson());
                        currentRepair.setPhoneContact(view.getRpPhoneContact());
                        currentRepair.setObservations(view.getRpObservationsOfRepairTextField());
                        currentRepair.setDescription(view.getRpDescription());
                        currentRepair.setPerformed(view.isRpIsPerformedCheckBoxSelected());
                    } else {
                        currentComputer.addRepair(newRepair);
                    }
                    view.updateRepairsTable(currentComputer.getRepairs());
                    rpResetView();
                }
            }
        };
        return al;
    }

    private ActionListener cancelRepairButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rpResetView();
            }
        };
        return al;
    }

    private void rpUpdateUI() {
        boolean repairIsSelected = (currentRepair != null);
        boolean rpFormIsVisible = view.isFormPanelRepairsVisible();

        view.rpAddButtonIsEnabled(!rpFormIsVisible && !repairIsSelected);
        view.rpEditButtonIsEnabled(!rpFormIsVisible && repairIsSelected);
        view.rpDeleteButtonIsEnabled(!rpFormIsVisible && repairIsSelected);
        view.rpGetInfoButtonIsEnabled(!rpFormIsVisible && repairIsSelected);

        if (rpFormIsVisible) {
            view.rpSaveButtonIsEnabled(true);
            view.rpCancelButtonIsEnabled(true);
            view.controlRepairsTableSelectionPossibility(false);
        } else {
            if (repairIsSelected) {
                sendRepairInfoToForm(currentRepair);
            }
        }
    }

    private void rpResetView() {
        view.clearRepairsTableSelection();
        currentRepair = null;
        view.clearRepairsForm();
        setFormVisibilityOnRepairs(false);
        view.controlRepairsTableSelectionPossibility(true);
    }

    private void setFormVisibilityOnRepairs(boolean isVisible) {
        view.formPanelRepairsIsVisible(isVisible);
        rpUpdateUI();
    }

    private boolean validateRepairData() {
        String repairType = view.getRepairTypeComboBox().getSelectedItem().toString();
        String description = view.getRpDescription();
        String contactPerson = view.getRpContactPerson();
        String phoneContact = view.getRpPhoneContact();
        String dischargeDate = view.getRpDischargeDate();

        if (repairType.equals("Please choose the type of maintenance here...")) {
            JOptionPane.showMessageDialog(view, "Please select a valid repair type.");
            return false;
        }
        if (description.isEmpty() || contactPerson.isEmpty()
                || phoneContact.isEmpty() || dischargeDate.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please complete all required fields.");
            return false;
        }
        return true;
    }

    public Repair createNewRepair() {
        Repair repair = new Repair();
        repair.setType(view.getRepairTypeComboBox().getSelectedItem().toString());
        repair.setDischargeDate(view.getRpDischargeDate());
        repair.setContactPerson(view.getRpContactPerson());
        repair.setPhoneContact(view.getRpPhoneContact());
        repair.setObservations(view.getRpObservationsOfRepairTextField());
        repair.setDescription(view.getRpDescription());
        repair.setPerformed(view.isRpIsPerformedCheckBoxSelected());
        return repair;
    }

    public void sendRepairInfoToForm(Repair repair) {
        view.getRepairTypeComboBox().setSelectedItem(repair.getType());
        view.setRpDischargeDate(repair.getDischargeDate());
        view.setRpContactPerson(repair.getContactPerson());
        view.setRpPhoneContact(repair.getPhoneContact());
        view.setRpObservationsOfRepairTextField(repair.getObservations());
        view.setRpDescription(repair.getDescription());
        view.setRpIsPerformedCheckBoxSelected(repair.isPerformed());
    }

    public Computer getSelectedComputer() {
        int selectedRow = view.getSelectedRowInComputersTable();
        return service.getComputer(view.getSerialNumberValueInComputersTable(selectedRow));
    }
}
