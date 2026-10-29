package TestingDraw2D.t_12th_addMouseHandling;

import java.util.ArrayList;
import java.util.List;

public class BuildingList {

    private static final List<Building> buildingList = new ArrayList<>();


    public static void addBuildingToList(Building building){
        buildingList.add(building);
    }

    public static List<Building> getBuildingFromList(){
        return buildingList;
    }


}
