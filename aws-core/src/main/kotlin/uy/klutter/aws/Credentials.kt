package uy.klutter.aws

import com.amazonaws.auth.AWSCredentialsProviderChain
import com.amazonaws.auth.InstanceProfileCredentialsProvider
import com.amazonaws.auth.SystemPropertiesCredentialsProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider


fun defaultSafeCredentialsProviderChain(optionalProfileName: String? = null, asyncRefreshInstanceCredentials: Boolean = false): AWSCredentialsProviderChain {
    return AWSCredentialsProviderChain(
            SystemPropertiesCredentialsProvider(), // Allow override from system properties
            InstanceProfileCredentialsProvider(asyncRefreshInstanceCredentials),  // Good for production, from IAM roles
            ProfileCredentialsProvider(optionalProfileName) // Good for development to use credentials from ~/.aws/credentials (see http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html)
            // DO NOT USE:
            // EnvironmentVariableCredentialsProvider(), favour system properties over environment variables
            // ClasspathPropertiesFileCredentialsProvider() unsafe to put credentials in code or JARs
    )
}
