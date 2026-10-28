package TestingDraw2D.t_9th_addBuildBuildingClass;

public enum Resolution {

    FIRST_RES(800, 800);

    private final int width;
    private final int height;

    Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
