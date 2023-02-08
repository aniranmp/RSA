package ir.aniranmp.web.util;

import com.fasterxml.jackson.annotation.JsonValue;

public class Enums {
    public enum RTURequestType {
        NONE,
        ALL,
        COILS,
        DISCRETE_INPUT,
        INPUT_REGISTERS,
        HOLDING_REGISTERS;

        @JsonValue
        public int value() {
            return ordinal();
        }
    }

}
