package TestingDraw2D.t_10th_addBuildingListClassAndTestSetupClass;

/**
 * IDEIGLENES: a kezdőállapot összeállítása, az éles hívásokat szimulálva.
 */
public  class TestSetup {

     TestSetup() {
    }

    public static void createStartingWorld() {
        BuildBuilding builder = new BuildBuilding();
        builder.BuildBuilding(BuildingData.BARRACK,  64, 64);
        builder.BuildBuilding(BuildingData.CHURCH,  256, 256);
    }
}