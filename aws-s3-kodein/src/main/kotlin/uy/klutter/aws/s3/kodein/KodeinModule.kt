package uy.klutter.aws.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.singleton
import uy.klutter.aws.defaultSafeCredentialsProviderChain

object KodeinAmazonS3Client {
    /**
     * Add an AmazonS3Client singleton factory for Kodein that uses the default safe credentials provider chain
     */
    val module = Kodein.Module() {
        bind<AmazonS3Client>() with singleton { AmazonS3Client(defaultSafeCredentialsProviderChain()) }
    }
}

