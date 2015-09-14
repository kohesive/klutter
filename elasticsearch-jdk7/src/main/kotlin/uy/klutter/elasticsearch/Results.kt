package uy.klutter.elasticsearch

import com.fasterxml.jackson.core.type.TypeReference
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.search.SearchHits


public inline fun <reified T: Any> SearchResponse.getHitsAsObjects(): Sequence<T> {
   return getHits().getHits().asSequence().map { EsConfig.objectMapper.readValue<T>(it.sourceAsString(), object : TypeReference<T>(){}) }
}

public inline fun <reified T: Any> SearchHits.getHitsAsObjects():  Sequence<T> {
   return getHits().asSequence().map { EsConfig.objectMapper.readValue<T>(it.sourceAsString(), object : TypeReference<T>(){}) }
}
