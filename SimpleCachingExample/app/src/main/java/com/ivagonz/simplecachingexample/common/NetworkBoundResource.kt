package com.ivagonz.simplecachingexample.common

import kotlinx.coroutines.flow.*


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    // Check if we need fetch new data from api
    val data = query().first()

    // Api data
    val flow = if (shouldFetch(data)) {
        // Loading state
        emit(Resource.Loading(data))

        try {
            // Cache data
            saveFetchResult(fetch())

            // Flow<ResultType> -> Flow<Resource<ResultType>>
            query().map { Resource.Success(it) }
            // Catching all possible errors
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        // Cache data
        query().map { Resource.Success(it) }
    }

    emitAll(flow)

}