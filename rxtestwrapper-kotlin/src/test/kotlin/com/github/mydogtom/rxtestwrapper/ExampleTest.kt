package com.github.mydogtom.rxtestwrapper

import org.junit.Test
import rx.Completable
import rx.Observable
import rx.Single

class ExampleTest{
    @Test fun testObservable() {
        Observable.just(1).test().hasValue(1).completed()
    }

    @Test
    fun testCompletable() {
        Completable.complete().test().completed()
    }

    @Test
    fun testSingle() {
        Single.just(1).test().hasValue(1).completed()
    }

    @Test
    fun testBlockingObservable() {
        Observable.just(1).toBlocking().test().hasValue(1).completed()
    }
}
