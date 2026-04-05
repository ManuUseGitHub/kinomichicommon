package be.technifutur.kinomichicommon.interfaces;

import java.io.Serializable;

public interface CopyCatAble<T, B> extends Serializable {
    void copyCat(T built);

    B pastyCat(B built);
}
