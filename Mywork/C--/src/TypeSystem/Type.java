package TypeSystem;

public interface Type {
    boolean isCompatible(Type other);

    Object defaultValue();
}
