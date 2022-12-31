package messagingframework.service.registry;

public interface ServiceRegistry {
    void collectServices();
    Class<?> getService(String command);
}
