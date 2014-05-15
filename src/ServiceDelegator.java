/**
 * Created by flashconsult on 15.05.2014.
 */
public class ServiceDelegator {
    private ResourceForDelegation resource;
    public ServiceDelegator(ResourceForDelegation resource) {
        this.resource = resource;
    }

    public int foo(int value) {
        resource.bar();
        return resource.foo(value);
    }

    public void bar() {
        resource.bar();
    }
}
