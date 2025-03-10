/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelAldComputerService;

/**
 *
 * @author barreirolistediego
 */
public class LaptopPC extends Computer {

    String batteryCapacity;

    public LaptopPC() {
        super();
        batteryCapacity = "";
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "batteryCapacity=" + batteryCapacity;
    }

}
