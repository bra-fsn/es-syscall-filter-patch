package hu.fsn;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class ESPatchAgent {
    public static void premain(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
            .type(ElementMatchers.named("org.elasticsearch.bootstrap.BootstrapChecks$SystemCallFilterCheck"))
            .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                builder.method(ElementMatchers.named("isSystemCallFilterInstalled"))
                    .intercept(FixedValue.value(true)))
            .installOn(instrumentation);
        
        System.out.println("ES Patch Agent: Installed SystemCallFilterCheck patch");
    }
}
