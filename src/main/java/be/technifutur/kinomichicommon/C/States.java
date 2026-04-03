package be.technifutur.kinomichicommon.C;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum States {
    PRE_MAIN_MENU("-a", "Retour au menu principal"),
    MAIN_MENU("a", "Menu principal"),

    PLAGE_MANAGEMENT("b","Gestion des plages"),
    PEOPLE_MANAGEMENT("c","Gestion des participants"),
    ADMIN_MANAGEMENT("d", "Administration"),

    PLAGE_ADDING("b1", "Ajout de plage(s)"),
    PLAGE_ADDING_ACTIVITY("b11", "Ajout de plage(s)"),
    PLAGE_LISTING_ACTIVITY("b13", "Liste des plages"),
    PLAGE_DELETING("b2", "Suppression de plage(s)"),
    PLAGE_EDIT("b3", "Edition d'une plage"),
    PLAGE_EDIT_ACTIVITY("b31", "Edition d'une plage"),
    PLAGE_LOADING("b4", "chargement des plages"),
    PLAGE_LOADING_ACTIVITY_A("b411", "loading par objets"),
    PLAGE_LOADING_ACTIVITY_B("b412", "loading par log text"),
    PLAGE_SAVING_ACTIVITY("b5", "sauvegarde des plages"),
    PLAGE_LISTING("b6", "Liste des plages"),

    PEOPLE_ADDING("c1", "Ajout d'un participant"),
    PEOPLE_DELETING("c2", "Suppression"),
    PEOPLE_EDIT("c3", "Edition d'un participant"),
    PEOPLE_LISTING("c4", "Liste des participants");

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
