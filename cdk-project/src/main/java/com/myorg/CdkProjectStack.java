package com.myorg;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.iam.Group;
import software.amazon.awscdk.services.s3.BlockPublicAccess;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketEncryption;

public class CdkProjectStack extends Stack {
    public CdkProjectStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkProjectStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        Bucket bucket = Bucket.Builder.create(this, "SiteBucket")
                .bucketName("gouravbansal11.com")
                .versioned(false)
                .encryption(BucketEncryption.S3_MANAGED)
                .blockPublicAccess(BlockPublicAccess.BLOCK_ALL)
                .build();

        String snstopicname = "snasds";
        if (!Token.isUnresolved(snstopicname) && snstopicname.length() > 10)
            throw new RuntimeException("topic name is incorrect");

        System.out.println(bucket.getBucketName());

            Bucket mybucket = Bucket.Builder.create(this, "SiteBucket1").build();

        Group group = Group.Builder.create(this, "gid").build();

            CfnOutput cfnOutput = CfnOutput.Builder.create(this, "myBucketOutput1")
                    .value(mybucket.getBucketName())
                    .description("My First CDK Bucket")
                    .exportName("myBuckoutput1")
                    .build();
    }
}
