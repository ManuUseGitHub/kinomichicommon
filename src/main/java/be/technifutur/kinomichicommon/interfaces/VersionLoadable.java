package be.technifutur.kinomichicommon.interfaces;

import java.util.function.Consumer;

public interface VersionLoadable<E> {

    E load(String filename, Consumer<Boolean> cbComplete);

    E loadByTextSource(String textContent,Consumer<Boolean> cbComplete);
}
