package PlanQ.PlanQ.notification;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SseEmitterRepository {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    public SseEmitter save(String emitterId, SseEmitter emitter) {
        emitters.put(emitterId, emitter);
        return emitter;
    }

    public void saveEventCache(String eventId, Object event) {
        eventCache.put(eventId, event);
    }

    public Map<String, SseEmitter> findAllEmittersByIdPrefix(String prefix) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .collect(ConcurrentHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), ConcurrentHashMap::putAll);
    }

    public Map<String, Object> findAllEventCacheStartsWith(String prefix) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .collect(ConcurrentHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), ConcurrentHashMap::putAll);
    }

    public void deleteEmitterById(String emitterId) {
        emitters.remove(emitterId);
    }

    public void deleteAllEmittersStartsWith(String prefix) {
        emitters.keySet().removeIf(key -> key.startsWith(prefix));
    }

    public void deleteAllEventCacheStartsWith(String prefix) {
        eventCache.keySet().removeIf(key -> key.startsWith(prefix));
    }
}
