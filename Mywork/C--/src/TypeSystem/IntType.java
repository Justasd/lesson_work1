package TypeSystem;

public class IntType implements Type {
    private int value;

    public IntType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean isCompatible(Type other) {
        return other instanceof IntType;
    }

    @Override
    public Object defaultValue() {
        return 0;
    }
}
