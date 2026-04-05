package be.technifutur.kinomichicommon.C;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum States {
    PRE_MAIN_MENU("-a", "Retour au menu principal"),
    MAIN_MENU("a", "Menu principal"),

    PLAGE_MANAGEMENT("b","Gestion des plages"),
    PEOPLE_MANAGEMENT("c","Gestion des participants"),
    ADMIN_MANAGEMENT("d", "Administration"),

    PLAGE_ADDING("b1", "Ajout de plages"),
    PLAGE_DELETING("b2", "Suppression de plages"),
    PLAGE_EDIT("b3", "Edition de plages"),
    PLAGE_LOADING("b4", "Chargement de plages"),
    PLAGE_LOADING_A("b41", "Chargement d'une sauvegarde"),
    PLAGE_LOADING_B("b42", "Chargement par log text"),
    PLAGE_SAVING("b5", "Sauvegarde des plages"),
    PLAGE_LISTING("b6", "Listing des plages"),

    PEOPLE_ADDING("c1", "Ajout de participants"),
    PEOPLE_DELETING("c2", "Suppression de participants"),
    PEOPLE_EDIT("c3", "Edition de participants"),
    PEOPLE_LOADING("c4","Chargement de participants"),
    PEOPLE_LOADING_A("c41","Chargement d'une sauvegarde"),
    PEOPLE_LOADING_B("c42","Chargement par log text"),
    PEOPLE_SAVING("c5", "Sauvegarde des participants"),
    PEOPLE_LISTING("c6", "Listing des participants");

    private final String value;
    private final String label;

    States(String value,String label){
        this.value = value;
        this.label = label;
    }

    public static States get(String value){
        AtomicReference<States> state = new AtomicReference<>();
        Arrays.stream(values()).filter(v -> v.value.equals(value)).findFirst().ifPresent(state::set);
        return state.get();
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
