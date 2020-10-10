package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.util.Arrays;

public class CdkMultipleEnviromentsApp {
    public static void main(final String[] args) {
        App app = new App();

        StackProps stackProps_dev =
                new StackProps.Builder()
                        .env(
                                Environment.builder()
                                        .region("us-east-1")
                                        .account("12121212121212")
                                        .build())
                        .build();

        StackProps stackProps_prod =
                new StackProps.Builder()
                        .env(
                                Environment.builder()
                                        .region("eu-west-1")
                                        .account("13333333333")
                                        .build())
                        .build();
        new CdkMultipleEnviromentsStack(app, "myDevStack", stackProps_dev, false); //Dev
        new CdkMultipleEnviromentsStack(app, "myProdStack", stackProps_prod, true); // Prod

        app.synth();
    }
}
