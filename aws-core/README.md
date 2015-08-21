## klutter/aws-core

Core helper functions for AWS in general.  This is a tiny module, currently has these methods:

### Safe Credentials Provider

Provides a safe credentials chain that is secure for production use, avoiding practices that tend to end up with secrets checked into source
repositories.  This loads credentials from System Properties, if not the IAM roles, if not then the home directory credentials profile.

```
public fun defaultSafeCredentialsProviderChain(): AWSCredentialsProviderChain
```

The best bet in Development is to use the [credentials file in your home directory](http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html).  And
in Production and IAM role is preferred.  Do not put AWS Credentials in your runtime configurations for your IDE, you might accidentally commit the project files containing 
the secrets into your source control.
