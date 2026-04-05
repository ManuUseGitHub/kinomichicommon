package be.technifutur.kinomichicommon;

import java.io.Serializable;

public enum ParticipantType implements Serializable {
    VISITOR("Visiteur", "Personne venant visiter l'événement"),
    TEACHER("Instructeur", "Personne pouvant assister le maître"),
    MEMBER("Membre", "Personne régulière et faisant partie du club local"),
    KID_MEMBER("Membre enfant", "Personne membre étant âgée de maximum 12 ans"),
    KID("Enfant","Fils ou fille d'un visiteur"),
    KID_VIP("Enfant de membre honoraire","Fils ou fille d'un VIP"),
    VIP("Invité honoraire","Personne ayant une invitation particulière");

    private final String name;
    private final String description;

    ParticipantType(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
