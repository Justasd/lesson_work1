package TypeSystem;

public class PointerType<T> implements Type {
    private T pointedValue;

    public PointerType(T value) {
        this.pointedValue = value;
    }

    public T dereference() {
        return pointedValue;
    }

    public void assign(T value) {
        pointedValue = value;
    }

    @Override
    public boolean isCompatible(Type other) {
        return other instanceof PointerType;
    }

    @Override
    public Object defaultValue() {
        return new PointerType<>(null);
    }
}
