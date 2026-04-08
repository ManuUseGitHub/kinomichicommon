package be.technifutur.kinomichicommon;

import be.technifutur.kinomichicommon.interfaces.HasName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum ParticipantType implements HasName, Serializable {
    VISITOR("Visiteur", "Personne venant visiter l'événement","VIST"),
    TEACHER("Instructeur", "Personne pouvant assister le maître","TEAC"),
    MEMBER("Membre", "Personne régulière et faisant partie du club local","MEMP"),
    KID_MEMBER("Membre enfant", "Personne membre étant âgée de maximum 12 ans","KMEM"),
    KID("Enfant","Fils ou fille d'un visiteur","VISK"),
    VIP_KID("Enfant de membre honoraire","Fils ou fille d'un VIP","VIPK"),
    VIP("Invité honoraire","Personne ayant une invitation particulière","VIPP");

    private final String name;
    private final String description;
    private final String code;

    ParticipantType(String name, String description, String code){
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static ParticipantType get(String value){
        AtomicReference<ParticipantType> state = new AtomicReference<>(ParticipantType.VISITOR);
        Arrays.stream(values()).filter(v -> v.name.equals(value)).findFirst().ifPresent(state::set);
        return state.get();
    }
}
