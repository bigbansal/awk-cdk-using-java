package com.myorg;

import software.amazon.awscdk.core.App;

import java.util.Arrays;

public class CdkProjectApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkProjectStack(app, "CdkProjectStack");

        app.synth();
    }
}
