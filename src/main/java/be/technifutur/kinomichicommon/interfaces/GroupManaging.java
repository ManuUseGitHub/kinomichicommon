package be.technifutur.kinomichicommon.interfaces;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

public interface GroupManaging<T, B> extends Serializable {
    @Transient
    void addItem(B building);

    @Transient
    T getItemById(int id);

    @Transient
    void removeItem(int id);

    @Transient
    List<T> getItems();

    @Transient
    void replaceItems(Object load);

    @Transient
    void changeItem(B builder, T selected);
}
