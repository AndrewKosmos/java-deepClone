import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class HashMapCloner implements IDeepCloner {
    private IDeepCloner superCloner;

    public HashMapCloner(IDeepCloner superCloner) {
        this.superCloner = superCloner;
    }

    @Override
    public <T> T deepClone(T object) {
        HashMap<Object,Object> hashMap = (HashMap) object;
        HashMap<Object, Object> resultMap = new HashMap<>();
        Field[] fields = object.getClass().getFields();
        for (Field f : fields) {
            f.setAccessible(true);
        }
        for (final Map.Entry entry : hashMap.entrySet()) {
            resultMap.put(superCloner.deepClone(entry.getKey()), superCloner.deepClone(entry.getValue()));
        }
        return (T) resultMap;
    }
}
