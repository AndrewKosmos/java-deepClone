import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapCloner implements IDeepCloner {
    private IDeepCloner superCloner;

    public LinkedHashMapCloner(IDeepCloner superCloner) {
        this.superCloner = superCloner;
    }

    @Override
    public <T> T deepClone(T object) {
        LinkedHashMap<Object, Object> linkedHashMap = (LinkedHashMap) object;
        LinkedHashMap resultMap = new LinkedHashMap();
        for (final Map.Entry entry : linkedHashMap.entrySet()) {
            resultMap.put(superCloner.deepClone(entry.getKey()), superCloner.deepClone(entry.getValue()));
        }
        return (T) resultMap;
    }
}
