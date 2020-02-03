package cloners;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Cloner {

    private final Set<Class<?>> immutables = new HashSet<>();
    private Map<Class<?>, IDeepCloner> clonersMap = new HashMap<>();

    private IDeepCloner deepCloner  = Cloner.this::deepClone;

    public Cloner(){
        init();
    }

    private void init() {
        registerClonerImmutables();
        registerCloners();
    }

    public <T> T deepClone(final T obj) {
        if (obj == null) { return null; }
        if (obj == this) { return null; }

        Class<?> objClass = obj.getClass();
        IDeepCloner cloner = getDeepCloner(objClass);
        return cloner.deepClone(obj);
    }

    private void registerClonerImmutables() {
        registerImmutable(String.class);
        registerImmutable(Integer.class);
        registerImmutable(Long.class);
        registerImmutable(Boolean.class);
        registerImmutable(Float.class);
        registerImmutable(Double.class);
        registerImmutable(Byte.class);
        registerImmutable(Short.class);
        registerImmutable(Character.class);
        registerImmutable(BigDecimal.class);
        registerImmutable(BigInteger.class);
        registerImmutable(UUID.class);
    }

    private void registerCloners() {
        clonersMap.put(ArrayList.class, new ArrayListCloner(deepCloner));
        clonersMap.put(LinkedList.class, new LinkedListCloner(deepCloner));
        clonersMap.put(HashSet.class, new HashSetCloner(deepCloner));
        clonersMap.put(HashMap.class, new HashMapCloner(deepCloner));
        clonersMap.put(TreeMap.class, new TreeMapCloner(deepCloner));
        clonersMap.put(TreeSet.class, new TreeSetCloner(deepCloner));
        clonersMap.put(LinkedHashMap.class, new LinkedHashMapCloner(deepCloner));
        clonersMap.put(LinkedHashSet.class, new LinkedHashSetCloner(deepCloner));
    }

    private void registerImmutable(Class<?> cls) {
        immutables.add(cls);
    }

    private IDeepCloner getDeepCloner(Class<?> cl) {
        if (cl.isArray()) {
            return new ArrayCloner(cl);
        } else if (clonersMap.containsKey(cl)) {
            return clonersMap.get(cl);
        }
        return new ObjectCloner();
    }

    private class ArrayCloner implements IDeepCloner {

        private boolean isPrimitive;
        private Class<?> componentType;

        public ArrayCloner(Class<?> cls) {
            isPrimitive = cls.getComponentType().isPrimitive();
            componentType = cls.getComponentType();
        }

        @Override
        public <T> T deepClone(T object) {
            int lenght = Array.getLength(object);
            T newInstance = (T) Array.newInstance(componentType, lenght);
            if ( isPrimitive ) {
                System.arraycopy(object, 0, newInstance, 0, lenght);
            }
            else {
                for (int i = 0; i < lenght; i++) {
                    Array.set(newInstance, i, Cloner.this.deepClone(Array.get(object, i)));
                }
            }
            return newInstance;
        }
    }

    private class ObjectCloner implements IDeepCloner {

        ObjectCloner() { }

        @Override
        public <T> T deepClone(T object) {

            T instance = null;
            try {
                instance = new ObjectInstantiator<>((Class<T>) object.getClass()).createInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Field field : object.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    int modifier = field.getModifiers();
                    if (field.getType().isPrimitive() || immutables.contains(field.getType())) {
                        if (!Modifier.isStatic(modifier)) {
                            field.set(instance, field.get(object));
                        }
                    } else {
                        if (!Modifier.isStatic(modifier)) {
                            field.set(instance, Cloner.this.deepClone(field.get(object)));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return instance;
        }
    }

}
