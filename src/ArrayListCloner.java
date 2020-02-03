import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListCloner implements IDeepCloner {

    private IDeepCloner cloner;

    public ArrayListCloner(IDeepCloner cloner) {
        this.cloner = cloner;
    }

    @Override
    public <T> T deepClone(T object) {
        ArrayList list = (ArrayList) object;
        int length = list.size();
        Field[] fields = object.getClass().getFields();
        for (Field f : fields) {
            f.setAccessible(true);
        }
        ArrayList listClone = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            listClone.add(cloner.deepClone(list.get(i)));
        }
        return (T) listClone;
    }
}
