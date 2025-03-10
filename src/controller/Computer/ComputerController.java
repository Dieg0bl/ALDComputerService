/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Computer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.viewComputersAndRepairs.ComputersAndRepairsView;
import model.modelAldComputerService.AldComputerService;
import model.modelAldComputerService.Computer;
import model.modelAldComputerService.DesktopPC;
import model.modelAldComputerService.LaptopPC;
import model.modelAldComputerService.ServerPC;

/**
 *
 * @author barreirolistediego
 */
public final class ComputerController {

    private final ComputersAndRepairsView view;
    private final AldComputerService service;
    private Computer currentComputer;

    public ComputerController(ComputersAndRepairsView view, AldComputerService service) {
        this.view = view;
        this.service = service;
        this.view.addCtAddComputersTableButtonListener(this.addComputerButtonListener());
        this.view.addCtEditComputersTableButtonListener(this.editComputerButtonListener());
        this.view.addCtDeleteComputersTableButtonListener(this.deleteComputerButtonListener());
        this.view.addCtGetInfoComputersTableButtonListener(this.getInfoComputerButtonListener());
        this.view.addCtSaveButtonListener(this.saveComputerButtonListener());
        this.view.addCtCancelButtonListener(this.cancelComputerButtonListener());
        this.view.addComputerTypeComboBoxActionListener(this.computerTypeComboBoxActionListener());
        this.view.addCtComputersTableSelectionListener(this.computersTableSelectionListener());
        this.ctResetView();

    }

    private ListSelectionListener computersTableSelectionListener() {
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = view.getSelectedRowInComputersTable();
                    if (selectedRow != -1 && selectedRow < service.getComputers().size()) {
                        currentComputer = service.getComputer(view.getSerialNumberValueInComputersTable(selectedRow));
                        view.updateRepairsTable(currentComputer.getRepairs());
                    } else {
                        currentComputer = null;
                    }
                    ctUpdateUI();
                    TabRepairsRenameAndActive(currentComputer);
                }
            }
        };
        return lsl;
    }

    private ActionListener computerTypeComboBoxActionListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedType = view.getComputerTypeComboBox().getSelectedItem().toString();
                view.ctFormVisibilityAutoManage(selectedType);
            }
        };
        return al;
    }

    private ActionListener addComputerButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFormVisibilityOnComputers(true);
            }
        };
        return al;
    }

    private ActionListener editComputerButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFormVisibilityOnComputers(true);
                sendComputerInfoToForm(currentComputer);
            }
        };
        return al;
    }

    private ActionListener deleteComputerButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this computer?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION && currentComputer != null) {
                    service.removeComputer(currentComputer.getSerialNumber());
                    ctResetView();
                }
            }
        };
        return al;
    }

    private ActionListener getInfoComputerButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentComputer != null) {
                    JOptionPane.showMessageDialog(view, currentComputer.toString(), "Computer Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
    }

    private ActionListener saveComputerButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateComputerData()) {
                    Computer computer = createNewComputer();
                    if (currentComputer != null) {
                        computer.setRepairs(currentComputer.getRepairs());
                        service.removeComputer(currentComputer.getSerialNumber());
                    }
                    service.addComputer(computer);
                    ctResetView();
                }
            }
        };
        return al;
    }

    private ActionListener cancelComputerButtonListener() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctResetView();
            }
        };
        return al;
    }

    private void ctUpdateUI() {
        boolean computerIsSelected = (currentComputer != null);
        boolean ctFormIsVisible = view.isFormPanelComputersVisible();

        view.ctAddButtonIsEnabled(!ctFormIsVisible && !computerIsSelected);
        view.ctEditButtonIsEnabled(!ctFormIsVisible && computerIsSelected);
        view.ctDeleteButtonIsEnabled(!ctFormIsVisible && computerIsSelected);
        view.ctGetInfoButtonIsEnabled(!ctFormIsVisible && computerIsSelected);

        if (ctFormIsVisible) {
            view.controlComputersTableSelectionPossibility(false);
            view.ctSaveButtonIsEnabled(true);
            view.ctCancelButtonIsEnabled(true);
        } else {
            if (computerIsSelected) {
                sendComputerInfoToForm(currentComputer);
            }
        }
    }

    private void ctResetView() {
        view.clearComputerTableSelection();
        currentComputer = null;
        TabRepairsRenameAndActive(currentComputer);
        view.updateComputersTable(service.getComputers());
        view.clearComputersForm();
        setFormVisibilityOnComputers(false);
        view.controlComputersTableSelectionPossibility(true);
    }

    private void setFormVisibilityOnComputers(boolean isVisible) {
        view.formPanelComputersIsVisible(isVisible);
        ctUpdateUI();
    }

    private boolean validateComputerData() {
        String serialNumber = view.getCtSerialNumberText();
        Computer existingComputer = service.getComputer(serialNumber);
        boolean commonFieldsFilled = !serialNumber.isEmpty() && !view.getCtBrandText().isEmpty() && !view.getCtModelText().isEmpty();
        boolean specificFieldsFilled = true;

        String selectedType = view.getComputerTypeComboBox().getSelectedItem().toString();
        switch (selectedType) {
            case "DESKTOP":
                specificFieldsFilled = !view.getCtCaseType().isEmpty();
                break;
            case "LAPTOP":
                specificFieldsFilled = !view.getCtBatteryCapacityText().isEmpty();
                break;
            case "SERVER":
                specificFieldsFilled = !view.getCtRaidConfiguration().isEmpty();
                break;
        }

        if (!serialNumber.isEmpty() && existingComputer != null && (currentComputer == null || !serialNumber.equals(currentComputer.getSerialNumber()))) {
            JOptionPane.showMessageDialog(view, "The serial number already exists.", "Duplicate Serial Number", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!commonFieldsFilled || !specificFieldsFilled) {
            JOptionPane.showMessageDialog(view, "Please complete all required fields.", "Incomplete Fields", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private Computer createNewComputer() {
        String type = view.getComputerTypeComboBox().getSelectedItem().toString();
        String serialNumber = view.getCtSerialNumberText();
        String brand = view.getCtBrandText();
        String model = view.getCtModelText();
        Computer computer = null;

        switch (type) {
            case "DESKTOP":
                DesktopPC desktop = new DesktopPC();
                desktop.setCaseType(view.getCtCaseType());
                computer = desktop;
                break;
            case "LAPTOP":
                LaptopPC laptop = new LaptopPC();
                String batteryCapacity = view.getCtBatteryCapacityText();
                laptop.setBatteryCapacity(batteryCapacity);
                computer = laptop;
                break;
            case "SERVER":
                ServerPC server = new ServerPC();
                server.setRaidConfiguration(view.getCtRaidConfiguration());
                computer = server;
                break;
        }

        if (computer != null) {
            computer.setSerialNumber(serialNumber);
            computer.setBrand(brand);
            computer.setModel(model);
        }

        return computer;
    }

    private void sendComputerInfoToForm(Computer computer) {
        if (computer == null) {
            return;
        }
        view.setCtSerialNumberText(computer.getSerialNumber());
        view.setCtBrandText(computer.getBrand());
        view.setCtModelText(computer.getModel());

        if (computer instanceof DesktopPC) {
            DesktopPC desktop = (DesktopPC) computer;
            view.getComputerTypeComboBox().setSelectedItem("DESKTOP");
            view.setCtCaseType(desktop.getCaseType());
        } else if (computer instanceof LaptopPC) {
            LaptopPC laptop = (LaptopPC) computer;
            view.getComputerTypeComboBox().setSelectedItem("LAPTOP");
            view.setCtBatteryCapacityText(laptop.getBatteryCapacity());
        } else if (computer instanceof ServerPC) {
            ServerPC server = (ServerPC) computer;
            view.getComputerTypeComboBox().setSelectedItem("SERVER");
            view.setCtRaidConfiguration(server.getRaidConfiguration());
        }
    }

    private void TabRepairsRenameAndActive(Computer computer) {
        if (computer != null) {
            currentComputer = computer;
            view.TABrepsActiveAndRename(computer.getSerialNumber(), true);
            ctUpdateUI();
        } else {
            view.TABrepsActiveAndRename("Repairs", false);
        }
    }
}
