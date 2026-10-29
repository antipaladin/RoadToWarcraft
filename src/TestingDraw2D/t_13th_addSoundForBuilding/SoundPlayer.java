package TestingDraw2D.t_13th_addSoundForBuilding;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/** Hangok betöltése. A fájlt egyszer olvassa be, lejátszót bárkinek ad. */
public class SoundPlayer {

    /** Egy beolvasott hang: a nyers bájtok és a formátumuk. */
    private record SoundData(AudioFormat format, byte[] bytes) {
    }

    private static final Map<String, SoundData> cache = new HashMap<>();

    /** Új, saját lejátszót ad a hanghoz. A fájlt csak az első kéréskor olvassa be. */
    public static Clip loadClip(String path) {
        SoundData data = cache.get(path);
        if (data == null) {
            data = readFile(path);            // először kérik: beolvasás a lemezről
            if (data == null) {
                return null;                  // nem sikerült (a hibát már kiírta)
            }
            cache.put(path, data);            // megjegyezzük a következő kérésekre
        }
        try {
            Clip clip = AudioSystem.getClip();                            // új lejátszó
            clip.open(data.format(), data.bytes(), 0, data.bytes().length); // a közös adatból
            return clip;
        } catch (Exception e) {
            System.err.println("Nem sikerült lejátszót készíteni: " + path + " – " + e.getMessage());
            return null;
        }
    }

    private static SoundData readFile(String path) {
        try (InputStream in = SoundPlayer.class.getResourceAsStream(path)) {
            if (in == null) {
                System.err.println("Hang nem található: " + path);
                return null;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(new BufferedInputStream(in));
            return new SoundData(audio.getFormat(), audio.readAllBytes());
        } catch (Exception e) {
            System.err.println("Nem sikerült beolvasni: " + path + " – " + e.getMessage());
            return null;
        }
    }
}