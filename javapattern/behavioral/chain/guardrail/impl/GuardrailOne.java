package guardrail.impl;

import guardrail.Guardrail;

public class GuardrailOne implements Guardrail {
    @Override
    public Result check(String input) {
        System.out.println("Guardrail One checking");
        return Result.R1;
    }
}
