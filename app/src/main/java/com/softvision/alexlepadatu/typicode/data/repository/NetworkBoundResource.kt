package com.softvision.alexlepadatu.typicode.data.repository

import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<DTO, Entity, Domain> {

    @ExperimentalCoroutinesApi
    fun asFlow(): Flow<ResultState<Domain>> =
        channelFlow {
            launch(Dispatchers.IO) {
                send(ResultState.Loading())

                try {
                    val fetchResult = fetchFromRemote()
                    saveFetchResult(dtoToEntity(fetchResult))
                } catch (throwable: Throwable) {
                    onFetchFailed(throwable)
                    send(ResultState.Error<Domain>(throwable))
                }
            }

            query()
                .map {
                    ResultState.Success(entityToDomain(it))
                }.collect {
                    send(it)
                }
        }

    abstract fun query(): Flow<Entity>

    abstract suspend fun fetchFromRemote(): DTO

    abstract suspend fun saveFetchResult(data: Entity)

    abstract suspend fun dtoToEntity(data: DTO): Entity

    abstract suspend fun entityToDomain(data: Entity): Domain

    open fun onFetchFailed(throwable: Throwable) = Unit

    open fun shouldFetch(data: Entity) = true
}
