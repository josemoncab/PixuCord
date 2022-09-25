package dev.josemc.pixucord.managers;

import dev.josemc.pixucord.listeners.EventListener;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class EventLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLoader.class);

    public EventLoader() {
        LOGGER.info("Start event loading...");
        Reflections reflections = new Reflections("dev.josemc.pixucord.listeners");
        Set<Class<? extends EventListener>> eventListeners = reflections.getSubTypesOf(EventListener.class);
        for (Class<? extends EventListener> el : eventListeners) {
            try {
                EventListener eventListener = el.getDeclaredConstructor().newInstance();
                eventListener.load();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("{} events loaded!", eventListeners.size());
    }
}
