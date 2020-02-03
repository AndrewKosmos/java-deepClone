package cloners;

import java.lang.reflect.Field;
import java.util.HashSet;

public class HashSetCloner implements IDeepCloner {
    private IDeepCloner superCloner;

    public HashSetCloner(IDeepCloner superCloner) {
        this.superCloner = superCloner;
    }

    @Override
    public <T> T deepClone(T object) {
        HashSet hashSet = (HashSet) object;
        HashSet resultSet = new HashSet();
        Field[] fields = object.getClass().getFields();
        for (Field f : fields) {
            f.setAccessible(true);
        }
        for (final Object o : hashSet) {
            resultSet.add(superCloner.deepClone(o));
        }
        return (T) resultSet;
    }
}
