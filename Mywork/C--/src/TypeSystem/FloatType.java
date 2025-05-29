package TypeSystem;

public class FloatType implements Type {
    private float value;

    public FloatType(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean isCompatible(Type other) {
        return other instanceof FloatType || other instanceof IntType;
    }

    @Override
    public Object defaultValue() {
        return 0.0f;
    }
}
