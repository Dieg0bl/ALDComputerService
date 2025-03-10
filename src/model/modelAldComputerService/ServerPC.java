/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelAldComputerService;

/**
 *
 * @author barreirolistediego
 */
public class ServerPC extends Computer {

    String raidConfiguration;

    public ServerPC() {
        super();
        this.raidConfiguration = "";
    }

    public String getRaidConfiguration() {
        return raidConfiguration;
    }

    public void setRaidConfiguration(String raidConfiguration) {
        this.raidConfiguration = raidConfiguration;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "raidConfiguration= " + raidConfiguration;
    }
    

}
