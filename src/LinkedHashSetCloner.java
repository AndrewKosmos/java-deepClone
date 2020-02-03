import java.lang.reflect.Field;
import java.util.LinkedHashSet;

public class LinkedHashSetCloner implements IDeepCloner {
    private IDeepCloner superCloner;

    public LinkedHashSetCloner(IDeepCloner superCloner) {
        this.superCloner = superCloner;
    }

    @Override
    public <T> T deepClone(T object) {
        LinkedHashSet<Object> linkedHashSet = (LinkedHashSet) object;
        LinkedHashSet resultSet = new LinkedHashSet();
        Field[] fields = object.getClass().getFields();
        for (Field f : fields) {
            f.setAccessible(true);
        }
        for (final Object o : linkedHashSet) {
            resultSet.add(superCloner.deepClone(o));
        }
        return (T) resultSet;
    }
}
