package guardrail.impl;

import guardrail.Guardrail;

public class GuardrailThree implements Guardrail {
    @Override
    public Result check(String input) {
        System.out.println("Guardrail Three checking");
        return Result.R4;
    }
}
