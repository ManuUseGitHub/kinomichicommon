package be.technifutur.kinomichicommon.C;

public record Event(Object sender, String eventType) {

    // Compact constructor with validation (Java 16+)
    public Event {
        java.util.Objects.requireNonNull(sender, "sender cannot be null");
        java.util.Objects.requireNonNull(eventType, "eventType cannot be null");
        if (eventType.isBlank()) {
            throw new IllegalArgumentException("eventType cannot be blank");
        }
    }

    // Static factory methods for common event types
    public static Event createSaveEvent(Object sender) {
        return new Event(sender, "save");
    }

    public static Event createAddEvent(Object sender) {
        return new Event(sender, "add");
    }

    public static Event createNavEvent(Object sender) {
        return new Event(sender, "nav");
    }

    public static Event createRemoveEvent(Object sender) {
        return new Event(sender, "remove");
    }

    public static Event createModifyEvent(Object sender) {
        return new Event(sender, "modify");
    }
}
