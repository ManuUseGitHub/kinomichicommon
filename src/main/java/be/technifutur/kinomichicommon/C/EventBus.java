package be.technifutur.kinomichicommon.C;

import be.technifutur.kinomichicommon.interfaces.IEventListener;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EventBus {
    private static final EventBus INSTANCE = new EventBus();

    private final ReentrantReadWriteLock readWriteLock;
    private final Map<String, Set<IEventListener>> listeners;
    private final Map<String, Set<Event>> events;
    private final ScheduledExecutorService executor;

    private EventBus() {
        readWriteLock = new ReentrantReadWriteLock();
        listeners = new ConcurrentHashMap<>();  // Better for concurrent access
        events = new ConcurrentHashMap<>();     // Better for concurrent access
        executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "event-bus");
            thread.setDaemon(true);  // Prevent hanging on shutdown
            return thread;
        });
    }

    public static void registerListener(String topic, IEventListener listener) {
        Objects.requireNonNull(topic, "topic cannot be null");
        Objects.requireNonNull(listener, "listener cannot be null");

        try {
            INSTANCE.readWriteLock.writeLock().lock();
            Set<IEventListener> listeners = INSTANCE.listeners.computeIfAbsent(
                    topic, k -> ConcurrentHashMap.newKeySet()
            );
            listeners.add(listener);
        } finally {
            INSTANCE.readWriteLock.writeLock().unlock();
        }
    }

    public static void removeListener(String topic, IEventListener listener) {
        Objects.requireNonNull(topic, "topic cannot be null");
        Objects.requireNonNull(listener, "listener cannot be null");

        try {
            INSTANCE.readWriteLock.writeLock().lock();
            var listeners = INSTANCE.listeners.get(topic);
            if (listeners != null) {
                listeners.remove(listener);
                if (listeners.isEmpty()) {
                    INSTANCE.listeners.remove(topic);  // Clean up empty topics
                }
            }
        } finally {
            INSTANCE.readWriteLock.writeLock().unlock();
        }
    }

    public static void publishEvent(String topic, Event event) {
        Objects.requireNonNull(topic, "topic cannot be null");
        Objects.requireNonNull(event, "event cannot be null");

        try {
            INSTANCE.readWriteLock.writeLock().lock();
            INSTANCE.events.computeIfAbsent(topic, k -> new LinkedHashSet<>()).add(event);

            Runnable runnable = () -> {
                Set<Event> eventsToProcess = new LinkedHashSet<>();
                Set<IEventListener> listenersToNotify = new HashSet<>();

                try {
                    INSTANCE.readWriteLock.writeLock().lock();
                    var eventsForTopic = INSTANCE.events.get(topic);
                    if (eventsForTopic != null && !eventsForTopic.isEmpty()) {
                        eventsToProcess.addAll(eventsForTopic);
                        eventsForTopic.clear();
                    }
                    var topicListeners = INSTANCE.listeners.get(topic);
                    if (topicListeners != null) {
                        listenersToNotify.addAll(topicListeners);
                    }
                } finally {
                    INSTANCE.readWriteLock.writeLock().unlock();
                }

                // Dispatch events
                for (Event e : eventsToProcess) {
                    for (IEventListener listener : listenersToNotify) {
                        try {
                            listener.processEvent(e);
                        } catch (Exception ex) {
                            // Log but don't let one listener failure affect others
                            System.err.println("Error processing event: " + ex.getMessage());
                        }
                    }
                }
            };

            INSTANCE.executor.schedule(runnable, 250, TimeUnit.MILLISECONDS);
        } finally {
            INSTANCE.readWriteLock.writeLock().unlock();
        }
    }

    // Graceful shutdown
    public static void shutdown() {
        INSTANCE.executor.shutdown();
        try {
            if (!INSTANCE.executor.awaitTermination(5, TimeUnit.SECONDS)) {
                INSTANCE.executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            INSTANCE.executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}