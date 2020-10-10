package com.myorg;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketEncryption;

public class CdkMultipleEnviromentsStack extends Stack {
    public CdkMultipleEnviromentsStack(final Construct scope, final String id, final boolean isprod) {
        this(scope, id, null, isprod);
    }

    public CdkMultipleEnviromentsStack(final Construct scope, final String id ,final StackProps props, final  boolean isprod) {
        super(scope, id, props);

        if(isprod) {
            Bucket bucket = Bucket.Builder.create(this, "myProdArtifactBucketId")
                    .versioned(true)
                    .encryption(BucketEncryption.S3_MANAGED)
                    .removalPolicy(RemovalPolicy.RETAIN).build();
        }
        else {
            Bucket bucket = Bucket.Builder.create(this, "myDevArtifactBucketId")
                    .removalPolicy(RemovalPolicy.DESTROY).build();
        }
        // The code that defines your stack goes here
    }

    public CdkMultipleEnviromentsStack(App app, String cdkMultipleEnviromentsStack, boolean b, Environment environment_prod) {
    }
}
