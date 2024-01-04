package guardrail.impl;

import guardrail.Guardrail;

public class GuardrailTwo implements Guardrail {
    @Override
    public Result check(String input) {
        System.out.println("Guardrail Two checking");
        return Result.R2;
    }
}
