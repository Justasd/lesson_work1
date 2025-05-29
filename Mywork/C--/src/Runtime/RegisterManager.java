package Runtime;

import java.util.HashMap;

import TypeSystem.Type;
import TypeSystem.IntType;

public class RegisterManager {
    private HashMap<String, Type> registers = new HashMap<>();

    public void setRegister(String regName, Type value) {
        registers.put(regName, value);
    }

    public Type getRegister(String regName) {
        return registers.getOrDefault(regName, new IntType(0));
    }

    public void clear() {
        registers.clear();
    }
}
