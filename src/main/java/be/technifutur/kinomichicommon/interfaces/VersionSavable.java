package be.technifutur.kinomichicommon.interfaces;

public interface VersionSavable<E> {
    void save(E tts, String filename);
}
