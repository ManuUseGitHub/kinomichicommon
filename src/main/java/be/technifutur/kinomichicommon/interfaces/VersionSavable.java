package be.technifutur.kinomichicommon.interfaces;

import java.util.function.Consumer;

public interface VersionSavable<E> {
    void save(E tts, String filename, Consumer<Boolean> cbComplete);
}
