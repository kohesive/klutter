package uy.klutter.elasticsearch

import org.elasticsearch.action.index.IndexRequestBuilder

fun IndexRequestBuilder.setSourceFromObject(pojo: Any) {
    setSource(EsConfig.objectMapper.writeValueAsString(pojo))
}
