import java.lang.reflect.Field;
import java.util.LinkedList;

public class LinkedListCloner implements IDeepCloner {
    private IDeepCloner cloner;

    public LinkedListCloner(IDeepCloner cloner) {
        this.cloner = cloner;
    }

    @Override
    public <T> T deepClone(T object) {
        LinkedList list = (LinkedList) object;
        LinkedList resultList = new LinkedList();
        Field[] fields = object.getClass().getFields();
        for (Field f : fields) {
            f.setAccessible(true);
        }
        for ( int i = 0; i < list.size(); i++ ) {
            resultList.add(cloner.deepClone(list.get(i)));
        }
        return (T) resultList;
    }
}
