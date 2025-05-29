package TypeSystem;

public class ArrayType<T> implements Type {
    private T[] array;

    public ArrayType(T[] array) {
        this.array = array;
    }

    public T get(int index) {
        return array[index];
    }

    public void set(int index, T value) {
        array[index] = value;
    }

    @Override
    public boolean isCompatible(Type other) {
        return other instanceof ArrayType &&
                ((ArrayType<?>) other).array.getClass().isAssignableFrom(array.getClass());
    }

    @Override
    public Object defaultValue() {
        return new ArrayType<>(new Object[0]);
    }
}
