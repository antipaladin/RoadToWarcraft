package TestingDraw2D.t_10th_addBuildingListClassAndTestSetupClass;

public class BuildBuilding {



    public void BuildBuilding(BuildingData name, int xPos, int yPos) {
            BuildingList.addBuildingToList(new Building(name, xPos, yPos));
    }


}
