package assignment1;

public class BuildingJob {//this class is the construction that is going on 
	private int streetNumber;
	private String streetName;//street number street name and suburb represent the adress
    private String suburb;
    private int stage;//this is the stage currently being constructed
    private double totalCost;//cost required to complete construction
    private double totalPaymentsReceived;//paymment recived for construction
    private double amountOwing;//amount that is not paid in total amount
    private WorkCrew workCrew;//it is the crew which works on this project
//constructor
    public BuildingJob( int streetNumber,String streetName, String suburb, int stage, double totalCost) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.suburb = suburb;
        this.stage = stage;
        this.totalCost = totalCost;
        this.amountOwing = totalCost;//this is as total cost because initially you owe the total amount
        this.totalPaymentsReceived = 0;//initially you wouldnt recive any payment so 0
    }
//getter methods and setter methods
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalPaymentsReceived() {
        return totalPaymentsReceived;
    }

    public void setTotalPaymentsReceived(double totalPaymentsReceived) {
        this.totalPaymentsReceived = totalPaymentsReceived;
    }

    public double getAmountOwing() {
        return amountOwing;
    }

    public void setAmountOwing(double amountOwing) {
        this.amountOwing = amountOwing;
    }

    public WorkCrew getWorkCrew() {
        return workCrew;
    }
    
  
  //calculates the cost for current stage of building
    public double getTotalCostForCurrentStage() {
        double cost;
        if (stage == 1) {
            cost = totalCost * 0.15; //15%payment in 1st stage
        } else if (stage == 2) {
            cost = totalCost * 0.35; //35%payment in 2nd stage
        } else if (stage == 3) {
            cost = totalCost * 0.30; //30%payment in 3rd stage
        } else {
            System.out.println("Invalid stage.");
            return 0; 
        }
        return cost;
    }
//Calculate owed amount for current stage
    public double getAmountOwingForCurrentStage() {
        double totalCostForCurrentStage = getTotalCostForCurrentStage();
        return totalCostForCurrentStage - totalPaymentsReceived;
    }
//accepts the payment checks it and updates the amount owed while also updates the stage of building
    public void acceptPayment(double amount) {
        double amountOwingForCurrentStage = getAmountOwingForCurrentStage();
        if (amount > amountOwingForCurrentStage + 1 || amount < 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        totalPaymentsReceived += amount;
        amountOwing = getTotalCostForCurrentStage() - totalPaymentsReceived;
        updateStage();
        System.out.println("New amount owing is $" + amountOwing);
    }
//updates the stage of building after checking the payment 
    public int updateStage() {
        if (stage < 3) {
            double requiredPayment =getTotalCostForCurrentStage();
            if (totalPaymentsReceived >= requiredPayment && workCrew != null) {
                stage++; // Move to the next stage
                System.out.println("preparing for Stage " + (stage + 1)); 
            }
        } else if (stage == 3) {
            if (totalPaymentsReceived >= totalCost) {
                System.out.println("Building job is completed and paid."); 
            } 
        }
        return stage;
    }
    

    public boolean isFullyCompletedAndPaid() {
        return (updateStage() == 3 && totalPaymentsReceived >= totalCost);
    }
//assign crew to building it
    public void assignCrew(WorkCrew crew) {
        this.workCrew = crew;
    }
    //checks if crew can work or not if crew is not same stage as building place crew cannot work
    public boolean canWorkOnAssignedStage() {
        if (workCrew == null) {
            System.out.println("No work crew assigned.");
            return false;
        }
        if (workCrew.getStageNumber() != stage) {
            System.out.println("Work crew is not eligible for the current stage.");
            return false;
        }
        return true;
    }

//to string method for easy redability
    @Override
    public String toString() {
        return "BuildingJob{" +
        "streetNumber='" + streetNumber + '\'' +
        ", streetName=" + streetName +
        ", suburb='" + suburb + '\'' +
        ", stage=" + stage +
        ", totalCost=" + totalCost +
        ", totalPaymentsReceived=" + totalPaymentsReceived +
        '}';
    }
}

