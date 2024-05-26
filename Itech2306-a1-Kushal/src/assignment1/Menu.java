package assignment1;
import java.util.Scanner;
/** The purpose of this class is to be text console prompting user which function to use*/  

public class Menu {
	private Scanner scanner;
    private BuildingManagementSystem buildingManagementSystem;
/** Constructs a Menu object with a reference to the BuildingManagementSystem.*/
    public Menu(BuildingManagementSystem buildingManagementSystem) {
        scanner = new Scanner(System.in);
        this.buildingManagementSystem = buildingManagementSystem;
        
    }
    /*construction of menu options that will be displayed*/
    private void displayMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Add a new crew");
        System.out.println("2. Add a new building job");
        System.out.println("3. Show summary list of job sites ");
        System.out.println("4. View work crews for a specific stage");
        System.out.println("5. Assign work crew to job site");
        System.out.println("6. Make a payment");
        System.out.println("7. Update building job state");
        System.out.println("8. Display details of building site");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
/*runs the menu with case of what to run when the button is pressed*/

    public void runMenu() {
        boolean stillRunning = true;
        while (stillRunning) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    buildingManagementSystem.addNewWorkCrew();
                    break;
                case 2:
                    buildingManagementSystem.addNewBuildingJob();
                    break;
                case 3:
                    buildingManagementSystem.displayBuildingJobSummary();
                    break;
                case 4:
                    buildingManagementSystem.viewWorkCrewsForStage();
                    break;
                case 5:
                    buildingManagementSystem.addCrewToSite();
                    break;
                case 6:
                    buildingManagementSystem.makePayment();
                    break;
                case 7:
                    buildingManagementSystem.updateBuildingJobState();
                    break;
                case 8:
                    buildingManagementSystem.displayBuildingJobDetails();
                    break;
                case 0:
                    stillRunning = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    
}
