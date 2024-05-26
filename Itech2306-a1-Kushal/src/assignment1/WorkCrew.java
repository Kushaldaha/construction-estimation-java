package assignment1;
public class WorkCrew { //this class is workcrew class which means it is the class responsible for storing data of the working crews
    private String crewName;//name of the company or crew working and their information
    private int phoneNumber;
    private String primaryContact;
    private int stageNumber;//stage which crew will work on
//constructor 
    public WorkCrew(String crewName, int phoneNumber, String primaryContact, int stageNumber) {
        this.crewName = crewName;
        this.phoneNumber = phoneNumber;
        this.primaryContact = primaryContact;
        this.stageNumber = stageNumber;
    }
//gettermethod and setter method 
    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }
   //to string method used so that it is in user readable form
    public String toString() {
        return "WorkCrew{" +
                "crewName='" + crewName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", primaryContact='" + primaryContact + '\'' +
                ", stageNumber=" + stageNumber +
                '}';
    }
}

