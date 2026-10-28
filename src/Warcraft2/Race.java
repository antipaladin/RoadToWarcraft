package Warcraft2;

/**
 * A HUD "skinje". A név egyben a resources alatti mappanév is:
 * /graphics/ui/human/... illetve /graphics/ui/orc/...
 */
public enum Race {
    HUMAN("human"),
    ORC("orc");

    private final String folder;

    Race(String folder) {
        this.folder = folder;
    }

    public String folder() {
        return folder;
    }
}