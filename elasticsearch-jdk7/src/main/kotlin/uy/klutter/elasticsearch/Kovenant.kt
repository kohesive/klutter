package uy.klutter.elasticsearch

import nl.komponents.kovenant.Deferred
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred
import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.ActionRequest
import org.elasticsearch.action.ActionRequestBuilder
import org.elasticsearch.action.ActionResponse
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse
import org.elasticsearch.client.ElasticsearchClient

public fun <T: Any> promiseResult(deferred: Deferred<T, Exception>): ActionListener<T> {
   return object: ActionListener<T> {
       override fun onResponse(response: T) {
           deferred.resolve(response)
       }

       override fun onFailure(e: Throwable) {
           deferred.reject(wrapThrowable(e))
       }
   }
}

public fun <T: Any, O: Any> promiseResult(deferred: Deferred<T, Exception>, map: (O)->T): ActionListener<O> {
    return object: ActionListener<O> {
        override fun onResponse(response: O) {
            deferred.resolve(map(response))
        }

        override fun onFailure(e: Throwable) {
            deferred.reject(wrapThrowable(e))
        }
    }
}

public fun <Request: ActionRequest<*>, Response: ActionResponse, RequestBuilder: ActionRequestBuilder<*, *, *, *>, Client: ElasticsearchClient<*>>
        ActionRequestBuilder<Request, Response, RequestBuilder, Client>.promise(deferred: Deferred<Response, Exception>): Promise<Response, Exception> {
    this.execute(promiseResult(deferred))
    return deferred.promise
}


public fun <Request: ActionRequest<*>, Response: ActionResponse, RequestBuilder: ActionRequestBuilder<*, *, *, *>, Client: ElasticsearchClient<*>>
        ActionRequestBuilder<Request, Response, RequestBuilder, Client>.promise(): Promise<Response, Exception> {
    val deferred = deferred<Response, Exception>()
    this.execute(promiseResult(deferred))
    return deferred.promise
}

public fun <Request: ActionRequest<*>, Response: ActionResponse, RequestBuilder: ActionRequestBuilder<*, *, *, *>, Client: ElasticsearchClient<*>>
        ActionRequestBuilder<Request, Response, RequestBuilder, Client>.promiseNothing(): Promise<Unit, Exception> {
    val deferred = deferred<Unit, Exception>()
    this.execute(promiseResult(deferred, {}))
    return deferred.promise
}


public fun <Request: ActionRequest<*>, Response: ActionResponse, RequestBuilder: ActionRequestBuilder<*, *, *, *>, Client: ElasticsearchClient<*>, O: Any>
        ActionRequestBuilder<Request, Response, RequestBuilder, Client>.promise(map: (Response)->O): Promise<O, Exception> {
    val deferred = deferred<O, Exception>()
    this.execute(promiseResult(deferred, map))
    return deferred.promise
}

public fun <Request: ActionRequest<*>, Response: ActionResponse, RequestBuilder: ActionRequestBuilder<*, *, *, *>, Client: ElasticsearchClient<*>, O: Any>
        ActionRequestBuilder<Request, Response, RequestBuilder, Client>.promise(deferred: Deferred<O, Exception>, map: (Response)->O): Promise<O, Exception> {
    this.execute(promiseResult(deferred, map))
    return deferred.promise
}