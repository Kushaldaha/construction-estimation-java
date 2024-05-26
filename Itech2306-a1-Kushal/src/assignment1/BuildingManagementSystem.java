package assignment1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*this class is responsible for maintaining the functional requirements of the program i.e it manages the building site and work crews */
public class BuildingManagementSystem {
	   
	    private List<BuildingJob> buildingJobs;/*creating array to store value passed by user */
	    private List<WorkCrew> workCrews;
	    Scanner scanner = new Scanner(System.in);
	    
/*constructor*/
	    public BuildingManagementSystem() {
	    	this. workCrews = new ArrayList<>();
	         this.buildingJobs = new ArrayList<>();
	     
	    }
	    //method to add new work crew
	    public void addNewWorkCrew() {
	    	System.out.print("Enter the crew name: ");
	        String crewName = scanner.nextLine();
	   
	        System.out.print("Enter the phone number: ");
	        int phoneNumber = scanner.nextInt();
	        System.out.print("Enter the stage number (1, 2, or 3): ");
	        int stageNumber = scanner.nextInt();
	         
	        
	        scanner.nextLine(); 
	        System.out.print("Enter the primary contact person: ");
	        String primaryContact = scanner.nextLine();
	        WorkCrew newWorkCrew = new WorkCrew(crewName, phoneNumber, primaryContact, stageNumber);
	        workCrews.add(newWorkCrew);
	        System.out.println("Work crew added successfully.");
	        System.out.println(newWorkCrew);
	    }
	    //method to add new construction or building site
	    public void addNewBuildingJob() {
	        
	        System.out.print("Enter the street number: ");
	        int streetNumber = scanner.nextInt();
	        scanner.nextLine();
	        System.out.print("Enter the street name: ");
	        String streetName = scanner.nextLine();
	        System.out.print("Enter the suburb: ");
	        String suburb = scanner.nextLine();
	        System.out.print("Enter the currentstage (1, 2, or 3): ");
	        int stage = scanner.nextInt();
	        System.out.print("Enter the total cost of the build: ");
	        double totalCost = scanner.nextDouble();
	        scanner.nextLine(); 
	        BuildingJob newBuildingJob = new BuildingJob(streetNumber,streetName,suburb,stage,totalCost);
	        buildingJobs.add(newBuildingJob);
	        System.out.println("Building job added successfully.");
	        System.out.println(newBuildingJob);
	    }
	    
//method which displays all the building sites
	    public void displayBuildingJobSummary() {
	    	if (buildingJobs == null) {
	            System.out.println("No building jobs available.");
	            return;
	        }
	        System.out.println("Summary list of job sites where houses are still being built:");
	        for (BuildingJob job : buildingJobs) {
	            if (!job.isFullyCompletedAndPaid()) {
	                String stageStatus;
	                switch (job.getStage()) {
	                    case 1:
	                        stageStatus = "Preparing for Stage 1";
	                        break;
	                    case 2:
	                        stageStatus = "Working on Stage 2";
	                        break;
	                    case 3:
	                        stageStatus = "Working on Stage 3";
	                        break;
	                    default:
	                        stageStatus = "Unknown stage";
	                }

	                WorkCrew assignedCrew = job.getWorkCrew();

	                System.out.println(job.getStreetNumber() + " " + job.getStreetName() + ", " + job.getSuburb() + " [" + stageStatus + "]");
	                if (assignedCrew != null) {
	                    System.out.println("Active Crew: " + assignedCrew.getCrewName());
	                } else {
	                    System.out.println("Active Crew: None");
	                }
	                System.out.println();
	            }
	        }
	    }
	    
//Method to view crew who are working on different stages
	    public void viewWorkCrewsForStage() {
	    	if (workCrews == null) {
	            System.out.println("No work crews available.");
	            return;
	        }
	        System.out.print("Enter stage number to view work crews for: ");
	        int stage = scanner.nextInt();
	        System.out.println("Work crews for Stage " + stage + ":");
	        for (WorkCrew crew : workCrews) {
	            if (crew.getStageNumber() == stage) {
	                System.out.println(crew.getCrewName() + " [contact: " + crew.getPhoneNumber() + " (" + crew.getPrimaryContact() + ")]");
	            }
	        }
	    }
	    //method to add the crew to the building site 
	    public void addCrewToSite() {
	    	
	            System.out.println("List of job sites:");
	            for (int i = 0; i < buildingJobs.size(); i++) {
	                System.out.println((i + 1) + ". " + buildingJobs.get(i));
	            }

	            System.out.print("Enter the number of the job site to add crew to: ");
	            int siteNumber = scanner.nextInt();
	            scanner.nextLine(); // Consume newline character

	            if (siteNumber < 1 || siteNumber > buildingJobs.size()) {
	                System.out.println("Invalid job site number.");
	                return;
	            }

	            BuildingJob selectedJob = buildingJobs.get(siteNumber - 1);

	            System.out.println("Available work crews for Stage " + selectedJob.getStage() + ":");
	            int count = 1;
	            for (WorkCrew crew : workCrews) {
	                if (crew.getStageNumber() == selectedJob.getStage()) {
	                    System.out.println(count + ". " + crew);
	                    count++;
	                }
	            }

	            if (count == 1) {
	                System.out.println("No work crews available for Stage " + selectedJob.getStage());
	                return;
	            }

	            System.out.print("Enter the number of the work crew to assign to the job site: ");
	            int crewNumber = scanner.nextInt();
	            scanner.nextLine();

	            if (crewNumber < 1 || crewNumber >= count) {
	                System.out.println("Invalid work crew number.");
	                return;
	            }

	            WorkCrew selectedCrew = null;
	            count = 1;
	            for (WorkCrew crew : workCrews) {
	                if (crew.getStageNumber() == selectedJob.getStage()) {
	                    if (count == crewNumber) {
	                        selectedCrew = crew;
	                        break;
	                    }
	                    count++;
	                }
	            }

	            selectedJob.assignCrew(selectedCrew);

	            System.out.println("Work crew successfully assigned to the job site.");
	        }
	    
//method that track payment percentage in different stages 
	    public void makePayment() {
			if (buildingJobs == null) {
		        System.out.println("No building jobs available.");
		        return;
		    }
	        System.out.println(" select a building job:");
	        
	        for (int i = 0; i < buildingJobs.size(); i++) {
	            BuildingJob job = buildingJobs.get(i);
	            if (job.getAmountOwingForCurrentStage() > 0) {
	                System.out.println((i + 1) + ". " + job.getStreetNumber() + " " + job.getStreetName() + ", " + job.getSuburb());
	            }
	        }
	        System.out.print("Enter your choice: ");
	        int jobIndex = scanner.nextInt();
	        BuildingJob selectedJob = buildingJobs.get(jobIndex - 1);
	        double amountOwing = selectedJob.getAmountOwingForCurrentStage();
	        System.out.println("The total cost of stage " + selectedJob.getStage() + " for the building job at " + selectedJob.getStreetNumber() + " " + selectedJob.getStreetName() + ", " + selectedJob.getSuburb() + " is $" + selectedJob.getTotalCostForCurrentStage());
	        System.out.println("The amount owed is $" + amountOwing);
	        System.out.print("payment of $ ");
	        double paymentAmount = scanner.nextDouble();
	        scanner.nextLine(); 
	        if (paymentAmount <= 0) {
	            System.out.println("Invalid  amount.");
	            return;
	        }
	        selectedJob.acceptPayment(paymentAmount);
	        
	    }
	    //method which updates the building process
	   public void updateBuildingJobState() {
	    	if (buildingJobs == null) {
	            System.out.println("No building jobs available.");
	            return;
	        }
	        
	        
	        System.out.println("Please select a building job:");

	       
	        for (int i = 0; i < buildingJobs.size(); i++) {
	            BuildingJob job = buildingJobs.get(i);
	            if (!job.isFullyCompletedAndPaid()) {
	                System.out.println((i + 1) + ". " + job.getStreetNumber() + " " + job.getStreetName() + ", " + job.getSuburb());
	            }
	        }

	        System.out.print("Enter your choice: ");
	        int jobIndex = scanner.nextInt();
	        BuildingJob selectedJob = buildingJobs.get(jobIndex - 1);

	        
	        selectedJob.updateStage();
	    }
	   //method which display the summary of the job done
	    public void displayBuildingJobDetails() {
	    	if (buildingJobs == null) {
	            System.out.println("No building jobs available.");
	            return;
	        }
	        System.out.println("Please select a building job:");

	        
	        for (int i = 0; i < buildingJobs.size(); i++) {
	            BuildingJob job = buildingJobs.get(i);
	            String stageStatus;
	            if(job.getStage() == 1) {
	                stageStatus = "Working on Stage " + job.getStage();}
	            else if (job.getStage() == 2) {
	                stageStatus = "Working on Stage " + job.getStage();
	            
	            } else if(job. getAmountOwingForCurrentStage()==0&&job.getStage()==3) {
	                stageStatus = "Completed Stage " + job.getStage();
	            
	            
	            }
	            
	        }

	        System.out.print("Which building job do you want to see the details for? ");
	        int jobIndex = scanner.nextInt();
	        BuildingJob selectedJob = buildingJobs.get(jobIndex - 1);

	        System.out.println("Report about building job at " + selectedJob.getStreetNumber() + " " + selectedJob.getStreetName() + ", " + selectedJob.getSuburb() + ":");
	        System.out.println("Current stage: " + selectedJob.getStage());
	        System.out.println("Work crews assigned to this job:");
	        
	        WorkCrew assignedCrew = selectedJob.getWorkCrew();
	        if (assignedCrew != null) {
	            System.out.println("  " + assignedCrew.getCrewName());
	        } else {
	            System.out.println("  None");
	        }

	        System.out.println("Total cost of job: $" + selectedJob.getTotalCost());
	        System.out.println("Total paid: $" + selectedJob.getTotalPaymentsReceived());
	        System.out.println("Amount owing: $" + selectedJob.getAmountOwingForCurrentStage());
	    }
	    

}

