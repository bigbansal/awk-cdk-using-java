package com.myorg;

import software.amazon.awscdk.core.App;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CdkMultipleEnviromentsTest {
    private final static ObjectMapper JSON =
        new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    @Test
    public void testStack() throws IOException {
        App app = new App();

        StackProps stackProps_prod =
                new StackProps.Builder()
                        .env(
                                Environment.builder()
                                        .region("eu-west-1")
                                        .account("13333333333")
                                        .build())
                        .build();

        CdkMultipleEnviromentsStack stack = new CdkMultipleEnviromentsStack(app, "test", stackProps_prod, false);

        // synthesize the stack to a CloudFormation template and compare against
        // a checked-in JSON file.
        JsonNode actual = JSON.valueToTree(app.synth().getStackArtifact(stack.getArtifactId()).getTemplate());
        assertEquals(new ObjectMapper().createObjectNode(), actual);
    }
}
