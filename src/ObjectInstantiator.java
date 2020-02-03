public class ObjectInstantiator <T> {

    private Class<T> aClass;

    public ObjectInstantiator(Class<T> aClass) {
        this.aClass = aClass;
    }

    public T createInstance() throws Exception {
        return aClass.getDeclaredConstructor().newInstance();
    }

}
