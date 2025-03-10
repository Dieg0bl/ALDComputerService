/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelAldComputerService;

/**
 *
 * @author barreirolistediego
 */
public class Repair {

    private String type;
    private String description;
    private String contactPerson;
    private String phoneContact;
    private String dischargeDate;
    private boolean isPerformed;
    private String observations;

    public Repair() {
    }

    public Repair(String type, String description, String contactPerson, String phoneContact, String dischargeDate, boolean isPerformed, String observations
    ) {
        this.type = type;
        this.description = description;
        this.contactPerson = contactPerson;
        this.phoneContact = phoneContact;
        this.dischargeDate = dischargeDate;
        this.isPerformed = isPerformed;
        this.observations = observations;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

   
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Type: " + this.getType() + "\n" + "Description: '" + this.getDescription() + "'\n" + "Contact Person: '" + this.getContactPerson() + "'\n" + "Phone Contact: '" + this.getPhoneContact() + "'\n" + "Discharge Date: '" + this.getDischargeDate() + "'\n" + "Is Performed: " + this.isPerformed() + "\n" + "Observations: '" + this.getObservations() + "'";
    }

    public boolean isPerformed() {
        return isPerformed;
    }

    public void setPerformed(boolean isPerformed) {
        this.isPerformed = isPerformed;
    }

}
