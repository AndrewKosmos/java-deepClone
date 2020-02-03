import java.lang.reflect.Field;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

public class TreeSetCloner implements IDeepCloner {
    private IDeepCloner superCloner;
    private static Field m;
    private static Field comparator;

    public TreeSetCloner(IDeepCloner superCloner) {
        this.superCloner = superCloner;
    }

    @Override
    public <T> T deepClone(T object) {
        TreeSet<Object> treeSet = (TreeSet) object;
        TreeSet resultSet = null;
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        try {
            m = TreeSet.class.getDeclaredField("m");
            comparator= TreeMap.class.getDeclaredField("comparator");
            resultSet = new TreeSet((Comparator) comparator.get(m.get(object)));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (resultSet != null) {
            for (Object o : treeSet) {
                resultSet.add(superCloner.deepClone(o));
            }
        }

        return (T) resultSet;
    }
}
