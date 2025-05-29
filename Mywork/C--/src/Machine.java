import java.util.List;

import Runtime.RegisterManager;
import java.util.HashMap;
import TypeSystem.*;

public class Machine {
    private RegisterManager registers = new RegisterManager();
    private HashMap<String, Integer> labels = new HashMap<>();
    private int pc = 0;

    public int execute(List<Instruction> code) {
        for (int i = 0; i < code.size(); i++) {
            Instruction instr = code.get(i);
            if (instr.op == Instruction.Op.LABEL) {
                labels.put(instr.label, i);
            }
        }

        while (pc < code.size()) {
            Instruction instr = code.get(pc++);
            switch (instr.op) {
                case LOAD_INT:
                    registers.setRegister(instr.dest, new IntType(instr.intValue));
                    break;
                case ADD:
                    try {
                        Type left = registers.getRegister(instr.src1);
                        Type right = registers.getRegister(instr.src2);
                        Type result = TypeChecker.checkBinaryOp(left, right, "+");
                        registers.setRegister(instr.dest, result);
                    } catch (TypeMismatchError e) {
                        throw new RuntimeException("Type error in ADD: " + e.getMessage());
                    }
                    break;
                case CJUMP:
                    Type cond = registers.getRegister(instr.src1);
                    TypeChecker.checkCondition(cond);
                    pc = (cond instanceof IntType && ((IntType) cond).getValue() != 0)
                            ? labels.get(instr.labelTrue)
                            : labels.get(instr.labelFalse);
                    break;
                case RETURN_VAL:
                    Type returnValue = registers.getRegister(instr.src1);
                    return (returnValue instanceof IntType)
                            ? ((IntType) returnValue).getValue()
                            : 0;
                default:
                    throw new RuntimeException("Unsupported instruction: " + instr.op);

            }
        }
        return 0;
    }
}