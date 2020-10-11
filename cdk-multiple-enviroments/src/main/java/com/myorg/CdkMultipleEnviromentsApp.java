package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.util.*;

public class CdkMultipleEnviromentsApp {
    public static void main(final String[] args) {
        App app = new App();
        Map prodMap = (LinkedHashMap) app.getNode().tryGetContext("prod");
        Map devMap = (LinkedHashMap) app.getNode().tryGetContext("dev");

        StackProps stackProps_dev =
                new StackProps.Builder()
                        .env(
                                Environment.builder()
                                        .region((String) devMap.get("region"))
                                        .account((String)devMap.get("account"))
                                        .build())
                        .build();

        StackProps stackProps_prod =
                new StackProps.Builder()
                        .env(
                                Environment.builder()
                                        .region((String) prodMap.get("region"))
                                        .account((String) prodMap.get("account"))
                                        .build())
                        .build();
        new CdkMultipleEnviromentsStack(app, (String) devMap.get("stackId") , stackProps_dev, (Boolean) devMap.get("isProd")); //Dev
        new CdkMultipleEnviromentsStack(app, (String) prodMap.get("stackId"), stackProps_prod, (Boolean) prodMap.get("isProd")); // Prod

        app.synth();
    }
}
