package com.company.constructs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.hashicorp.cdktf.TerraformStack;
import com.hashicorp.cdktf.Testing;
import com.hashicorp.cdktf.providers.kubernetes.deployment.Deployment;
import com.mycompany.constructs.KubernetesWebAppDeployment;
import com.mycompany.constructs.KubernetesWebAppDeploymentConfig;

public class KubernetesWebAppDeploymentTest {

    @Test
    void containsDeploymentResource() {
        TerraformStack stack = new TerraformStack(Testing.app(), "stack");

        new KubernetesWebAppDeployment(stack, "myapp-test",
                new KubernetesWebAppDeploymentConfig()
                        .setImage("nginx:latest")
                        .setReplicas(4)
                        .setApp("myapp")
                        .setComponent("frontend")
                        .setEnvironment("dev"));
        String synthesized = Testing.synth(stack);

        assertTrue(Testing.toHaveResource(synthesized, Deployment.TF_RESOURCE_TYPE));
    }
}
