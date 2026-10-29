package TestingDraw2D.t_12th_addMouseHandling;

/**
 * IDEIGLENES: a kezdőállapot összeállítása, az éles hívásokat szimulálva.
 */
public  class TestSetup {

     TestSetup() {
    }

    public static void createStartingWorld() {
        BuildBuilding builder = new BuildBuilding();
        builder.BuildBuilding(BuildingData.BARRACK,  2, 2);
        builder.BuildBuilding(BuildingData.CHURCH,  6, 6);
    }



}