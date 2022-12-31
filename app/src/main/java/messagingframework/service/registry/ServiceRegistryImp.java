package messagingframework.service.registry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import messagingframework.service.Servicable;
import messagingframework.service.Service;

public class ServiceRegistryImp implements ServiceRegistry{

    private Map<String, Class<?>> registry = new HashMap<>();
    private Reflections reflection = new Reflections("messagingframework");

    private Predicate<Class<?>> isImplementServicable 
            = (classWithAnnotation) -> {
                return Arrays.asList(classWithAnnotation.getInterfaces())
                        .contains(Servicable.class);
        };

    private Set<Class<?>> collectServiceInterface()
    {
        var classWithServiceAnnotation = reflection.getTypesAnnotatedWith(Service.class);
        
        
        return classWithServiceAnnotation.stream()
            .filter(isImplementServicable)
            .collect(Collectors.toSet());
    }

    private String getCommandOfService(Class<?> service)
    {
        return service.getAnnotation(Service.class).command();
    }

    private void addServiceToRegistry(Class<?> service)
    {
        registry.put(
            getCommandOfService(service),
            service);
    }

    @Override
    public void collectServices() {
        Set<Class<?>> collectedServices = collectServiceInterface();
        for (var service : collectedServices)
            addServiceToRegistry(service);
    }

	@Override
	public Class<?> getService(String command) {
		return registry.get(command);
	}
}
