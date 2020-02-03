package cloners;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapCloner implements IDeepCloner {
    private IDeepCloner superCloner;

    public TreeMapCloner(IDeepCloner superCloner) {
        this.superCloner = superCloner;
    }

    @Override
    public <T> T deepClone(T object) {
        TreeMap<Object, Object> treeMap = (TreeMap) object;
        TreeMap<Object, Object> resultMap = new TreeMap<>();
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        for (final Map.Entry entry : treeMap.entrySet()) {
            resultMap.put(superCloner.deepClone(entry.getKey()), superCloner.deepClone(entry.getValue()));
        }
        return (T) resultMap;
    }
}
