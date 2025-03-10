/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelAldComputerService;

/**
 *
 * @author barreirolistediego
 */
public class DesktopPC extends Computer{

   
    String caseType;
    
    
     public DesktopPC() {
        super();
        caseType= "";    
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "caseType= " + caseType;
    }
    
    

}
