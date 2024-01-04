package guardrail;

public enum Result {
    R1("valid"),
    R2("r2"),
    R3("r3"),
    R4("r4");

    private final String result;

    Result(String result) {
        this.result = result;
    }
}
