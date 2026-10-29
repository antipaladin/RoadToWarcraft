package TestingDraw2D.t_11th_addTileSystem;

public class BuildBuilding {



    public void BuildBuilding(BuildingData name, int column, int row) {
            BuildingList.addBuildingToList(new Building(name, column, row));
    }


}
