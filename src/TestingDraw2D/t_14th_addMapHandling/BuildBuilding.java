package TestingDraw2D.t_14th_addMapHandling;

public class BuildBuilding {



    public void BuildBuilding(BuildingData name, int column, int row) {
            BuildingList.addBuildingToList(new Building(name, column, row));
    }


}
