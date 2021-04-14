package com.softvision.alexlepadatu.typicode.domain.common

sealed class ResultState<out T> {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    class Loading<T> : ResultState<T>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultState<T>()

    /**
     * A state to show a [throwable] is thrown.
     */
    data class Error<T>(val throwable: Throwable) : ResultState<T>()
}
