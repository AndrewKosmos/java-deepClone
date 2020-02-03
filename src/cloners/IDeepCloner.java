package cloners;

public interface IDeepCloner {

    <T> T deepClone(final T object);

}
