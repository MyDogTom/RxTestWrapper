package com.github.mydogtom.rxtestwrapper

import rx.Completable
import rx.Observable
import rx.Single
import rx.observables.BlockingObservable

fun <T> Observable<T>.test() : RxTestWrapper<T>{
    return RxTestWrapper.assertThat(this)
}

fun Completable.test() : RxTestWrapper<Any>{
    return RxTestWrapper.assertThat(this)
}

fun <T> Single<T>.test() : RxTestWrapper<T>{
    return RxTestWrapper.assertThat(this)
}

fun <T> BlockingObservable<T>.test() : RxTestWrapper<T>{
    return RxTestWrapper.assertThat(this)
}
