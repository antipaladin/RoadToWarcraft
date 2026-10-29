package TestingDraw2D.t_13th_addSoundForBuilding;

public enum BuildingData {
    BARRACK("Barrack", 3, 3, 2, "/graphics/tilesets/summer/human/buildings/barracks_for_testing.png", "/sounds/stables.wav"),
    CHURCH("Church", 3, 3, 2, "/graphics/tilesets/summer/human/buildings/church_for_testing.png", "/sounds/church.wav");

    private final String displayName;
    private final int width;
    private final int height;
    private final int viewRange;
    private final String path;
    private final String soundPath;


    BuildingData(String displayName, int width, int height, int viewRange, String path, String soundPath) {
        this.displayName = displayName;
        this.width = width;
        this.height = height;
        this.viewRange = viewRange;
        this.path = path;
        this.soundPath = soundPath;
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

    public String getDisplayName() {
        return displayName;
    }

    public String getSoundPath() {
        return soundPath;
    }
}
