package assignment1;

public class MainProgram {

	public static void main(String[] args) {
		BuildingManagementSystem buildingManagementSystem = new BuildingManagementSystem();
        Menu menu = new Menu(buildingManagementSystem);
        menu.runMenu();
    }
	

}
