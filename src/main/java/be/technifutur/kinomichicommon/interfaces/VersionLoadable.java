package be.technifutur.kinomichicommon.interfaces;

public interface VersionLoadable<E> {

    E load(String filename);

    E loadByTextSource(String textContent);
}
