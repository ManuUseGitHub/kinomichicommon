package be.technifutur.kinomichicommon.C;

public record Event(Object sender, EventType eventType) {

    // Compact constructor with validation (Java 16+)
    public Event {
        java.util.Objects.requireNonNull(sender, "sender cannot be null");
        java.util.Objects.requireNonNull(eventType, "eventType cannot be null");

    }

    // Static factory methods for common event types
    public static Event createSaveEvent(Object sender) {
        return new Event(sender, EventType.SAVE);
    }


    public static Event createLockEvent(Object sender) {
        return new Event(sender, EventType.LOCK);
    }

    public static Event createUnlockEvent(Object sender) {
        return new Event(sender, EventType.UNLOCK);
    }

    public static Event createNavEvent(Object sender) {
        return new Event(sender, EventType.NAVIGATE);
    }

    public enum Topic{
        LOCK,
        NAVIGATION,
        PERSISTANCE
    }

    public enum EventType{
        NAVIGATE("navigate"),
        LOCK("lock"),
        UNLOCK("unlock"),
        SAVE("save"),
        SAVING("saving"),
        CHANGED("changed"),
        SAVED("saved"),
        LOADING("loading"),
        PENDING("pending"),
        LOADED("loaded");

        private final String name;

        EventType(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}
