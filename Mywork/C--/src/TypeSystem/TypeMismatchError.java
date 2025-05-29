package TypeSystem;

public class TypeMismatchError extends RuntimeException {
    public TypeMismatchError(String message) {
        super(message);
    }
}