package TypeSystem;

public class TypeChecker {
    public static Type checkBinaryOp(Type left, Type right, String op) throws TypeMismatchError {
        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
            if (left.isCompatible(right)) {
                return (left instanceof FloatType || right instanceof FloatType)
                        ? new FloatType(0)
                        : new IntType(0);
            } else {
                throw new TypeMismatchError("Type mismatch: " + left + " and " + right);
            }
        }
        return new IntType(0); // 默认返回布尔类型
    }

    public static Type checkCondition(Type condition) throws TypeMismatchError {
        if (!(condition instanceof IntType)) {
            throw new TypeMismatchError("Condition must be an integer");
        }
        return condition;
    }
}
