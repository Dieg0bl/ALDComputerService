/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelAldComputerService;

import java.util.HashMap;

/**
 *
 * @author barreirolistediego
 */
public class AldComputerService {

    private String address;
    private String establishmentName;
    private String Phone;
    private int NumberOfEmployees;

    private final HashMap<String, Computer> computers;

    public AldComputerService() {
        this.address = "";
        this.establishmentName = "";
        this.Phone = "";
        this.NumberOfEmployees = 0;
        this.computers = new HashMap<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public int getNumberOfEmployees() {
        return NumberOfEmployees;
    }

    public void setNumberOfEmployees(int NumberOfEmployees) {
        this.NumberOfEmployees = NumberOfEmployees;
    }

    public void addComputer(Computer computer) {
        computers.put(computer.getSerialNumber(), computer);
    }

    public void removeComputer(String serialNumber) {
        computers.remove(serialNumber);
    }

    public Computer getComputer(String serialNumber) {
        return computers.get(serialNumber);
    }

    public HashMap<String, Computer> getComputers() {
        return computers;
    }

}
