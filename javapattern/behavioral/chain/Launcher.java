import guardrail.Guardrail;

public class Launcher {
    public static void main(String args[]) {
        System.out.println("Hello World!");
        Guardrail guardrailChain =
                new GuardrailOne()
                        .chain(input -> {
                            System.out.println("this is lambda");
                            return Result.R1;
                        })
                        .chain(new GuardrailTwo())
                        .chain(new GuardrailThree());

        guardrailChain.check("dummy input");
    }
}
