public class Instruction {
    public enum Op {
        LOAD_INT, LOAD_VAR, STORE_VAR, ADD, RETURN_VAL,
        JUMP, CJUMP, LABEL, CALL, HALT
    }

    public Op op;
    public String dest, src1, src2;
    public int intValue;
    public String label;
    public String labelTrue;
    public String labelFalse;

    public static Instruction loadInt(String dest, int value) {
        Instruction i = new Instruction();
        i.op = Op.LOAD_INT;
        i.dest = dest;
        i.intValue = value;
        return i;
    }

    public static Instruction loadVar(String dest, String src) {
        Instruction i = new Instruction();
        i.op = Op.LOAD_VAR;
        i.dest = dest;
        i.src1 = src;
        return i;
    }

    public static Instruction storeVar(String dest, String src) {
        Instruction i = new Instruction();
        i.op = Op.STORE_VAR;
        i.dest = dest;
        i.src1 = src;
        return i;
    }

    public static Instruction add(String dest, String src1, String src2) {
        Instruction i = new Instruction();
        i.op = Op.ADD;
        i.dest = dest;
        i.src1 = src1;
        i.src2 = src2;
        return i;
    }

    public static Instruction cjump(String cond, String labelIfTrue, String labelIfFalse) {
        Instruction i = new Instruction();
        i.op = Op.CJUMP;
        i.src1 = cond;
        i.labelTrue = labelIfTrue;
        i.labelFalse = labelIfFalse;
        return i;
    }

    public static Instruction jump(String label) {
        Instruction i = new Instruction();
        i.op = Op.JUMP;
        i.label = label;
        return i;
    }

    public static Instruction label(String label) {
        Instruction i = new Instruction();
        i.op = Op.LABEL;
        i.label = label;
        return i;
    }

    public static Instruction returnVal(String src) {
        Instruction i = new Instruction();
        i.op = Op.RETURN_VAL;
        i.src1 = src;
        return i;
    }
}