package uy.klutter.aws.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.ObjectMetadata
import org.apache.http.HttpStatus
import uy.klutter.aws.defaultSafeCredentialsProviderChain

public fun AmazonS3Client.exists(bucket: String, key: String): Boolean {
    return ifExistsReturnMetadata(bucket, key) != null
}

public fun AmazonS3Client.ifExistsReturnMetadata(bucket: String, key: String): ObjectMetadata? {
    try {
        val metadata = getObjectMetadata(bucket, key)
        return metadata
    } catch (e: AmazonS3Exception) {
        if (e.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return null
        }
        throw e
    }
}


public fun AmazonS3Client.ifExistsReturnUserMetadata(bucket: String, key: String): Map<String, String>? {
    return ifExistsReturnMetadata(bucket, key)?.getUserMetadata()
}

