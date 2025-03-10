/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelAldComputerService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barreirolistediego
 */
public abstract class Computer {

    String serialNumber;
    String brand;
    String model;

    private final List<Repair> repairs;

    public Computer() {
        this.serialNumber = "";
        this.brand = "";
        this.model = "";
        this.repairs = new ArrayList();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void addRepair(Repair repair) {
        repairs.add(repair);
    }

    @Override
    public String toString() {
        return "serialNumber= " + serialNumber + "\n" + "brand= " + brand + "\n" + "model= " + model;
    }

    public void addRepairs(List<Repair> repairsToAdd) {
        if (repairsToAdd != null) {
            for (Repair repair : repairsToAdd) {
                this.repairs.add(repair);
            }
        }
    }


    public void setRepairs(List<Repair> repairs) {
        this.repairs.addAll(repairs);
    }

    public void deleteRepair(Repair repair) {
        repairs.remove(repair);
    }

}
