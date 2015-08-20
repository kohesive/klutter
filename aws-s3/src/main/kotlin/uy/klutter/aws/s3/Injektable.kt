package uy.klutter.aws.s3

import com.amazonaws.services.s3.AmazonS3Client
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.klutter.aws.defaultSafeCredentialsProviderChain

/**
 * Add an AmazonS3Client singleton factory to Injekt registry that uses the default safe credentials provider chain
 */
public object AmazonS3Injektables : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory { AmazonS3Client(defaultSafeCredentialsProviderChain()) }
    }
}


