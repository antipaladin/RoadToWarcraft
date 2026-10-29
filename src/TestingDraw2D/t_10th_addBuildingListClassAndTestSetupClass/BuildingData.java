package TestingDraw2D.t_10th_addBuildingListClassAndTestSetupClass;

public enum BuildingData {
    BARRACK("Barrack", 96, 96, 64, "/graphics/tilesets/summer/human/buildings/barracks_for_testing.png"),
    CHURCH("Church", 96, 96, 64, "/graphics/tilesets/summer/human/buildings/church_for_testing.png");

    private final String displayName;
    private final int width;
    private final int height;
    private final int viewRange;
    private final String path;


    BuildingData(String displayName, int width, int height, int viewRange, String path) {
        this.displayName = displayName;
        this.width = width;
        this.height = height;
        this.viewRange = viewRange;
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getViewRange() {
        return viewRange;
    }

    public String getPath() {
        return path;
    }
}
