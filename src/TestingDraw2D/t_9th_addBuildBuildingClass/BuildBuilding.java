package TestingDraw2D.t_9th_addBuildBuildingClass;

import java.util.ArrayList;
import java.util.List;

public class BuildBuilding {

    private final List<Building> buildingList = new ArrayList<>();

    public BuildBuilding() {
        Building barrack1 = new Building(BuildingData.BARRACK,  96, 64);
        Building barrack2 = new Building(BuildingData.BARRACK, 256, 256);
        buildingList.add(barrack1);
        buildingList.add(barrack2);
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

}
