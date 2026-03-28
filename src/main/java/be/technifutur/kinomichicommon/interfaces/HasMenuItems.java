package be.technifutur.kinomichicommon.interfaces;

import java.util.stream.Stream;

public interface HasMenuItems {
    String getName();
    Stream<String> getItems();
}
