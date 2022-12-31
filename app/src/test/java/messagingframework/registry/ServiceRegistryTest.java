package messagingframework.registry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import messagingframework.service.Servicable;
import messagingframework.service.Service;
import messagingframework.service.registry.ServiceRegistry;
import messagingframework.service.registry.ServiceRegistryImp;

public class ServiceRegistryTest {
    @Test
    public void collectServicesTest()
    {
        @Service(command = "TestServiceA")
        class TestServiceA implements Servicable{}

        @Service(command = "TestServiceB")
        class TestServiceB implements Servicable{}

        @Service(command = "TestServiceC")
        class TestServiceC implements Servicable{}

        ServiceRegistry serviceRegistry = new ServiceRegistryImp();
        serviceRegistry.collectServices();
        
        assertEquals(TestServiceA.class, serviceRegistry.getService("TestServiceA"));
        assertEquals(TestServiceB.class, serviceRegistry.getService("TestServiceB"));
        assertEquals(TestServiceC.class, serviceRegistry.getService("TestServiceC"));
    }
}
