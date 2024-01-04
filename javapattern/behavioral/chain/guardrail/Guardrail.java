package guardrail;

@FunctionalInterface
public interface Guardrail {
    Result check(String input);

    default Guardrail chain(Guardrail after) {
        return (dataConfigurationState) -> {
            Result currentResult = this.check(dataConfigurationState);

            return !currentResult.equals(Result.R1) ? currentResult : after.check(dataConfigurationState);
        };
    }
}
