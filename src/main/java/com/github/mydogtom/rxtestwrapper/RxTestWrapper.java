package com.github.mydogtom.rxtestwrapper;

import java.util.List;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.observables.BlockingObservable;
import rx.observers.TestSubscriber;

/**
 * This is a temporary simplified version. Wrapper will be extracted in a separate library later.
 *
 * IMPORTANT If you need a method that is not currently present, please add it or ask @svyatoslav.chatchenko
 */
public class RxTestWrapper<T> {
    private final TestSubscriber<T> testSubscriber;

    public RxTestWrapper(Observable<T> observable) {
        testSubscriber = TestSubscriber.create();
        observable.subscribe(testSubscriber);
    }

    public RxTestWrapper(BlockingObservable<T> blockingObservable) {
        testSubscriber = TestSubscriber.create();
        blockingObservable.subscribe(testSubscriber);
    }

    public static <T> RxTestWrapper<T> assertThat(Observable<T> observable) {
        return new RxTestWrapper<>(observable);
    }

    public static <T> RxTestWrapper<T> assertThat(Single<T> single) {
        return new RxTestWrapper<>(single.toObservable());
    }

    public static <T> RxTestWrapper<T> assertThat(Completable completable) {
        return new RxTestWrapper<>(completable.toObservable());
    }

    public static <T> RxTestWrapper<T> assertThat(BlockingObservable<T> blockingObservable) {
        return new RxTestWrapper<>(blockingObservable);
    }

    /** Wrapper arround {@link TestSubscriber#assertReceivedOnNext(List)} */
    public final RxTestWrapper<T> receivedOnNext(List<T> items) {
        testSubscriber.assertReceivedOnNext(items);
        return this;
    }

    /** Wrapper around {@link TestSubscriber#assertTerminalEvent()} */
    public final RxTestWrapper<T> terminalEvent() {
        testSubscriber.assertTerminalEvent();
        return this;
    }

    /** Wrapper around {@link TestSubscriber#assertUnsubscribed()} */
    public final RxTestWrapper<T> unsubscribed() {
        testSubscriber.assertUnsubscribed();
        return this;
    }

    /** Wraps {@link TestSubscriber#assertNoErrors()} */
    public final RxTestWrapper<T> noErrors() {
        testSubscriber.assertNoErrors();
        return this;
    }

    /** Wraps {@link TestSubscriber#assertCompleted()} */
    public final RxTestWrapper<T> completed() {
        testSubscriber.assertCompleted();
        return this;
    }

    /** Wraps {@link TestSubscriber#assertNotCompleted()} */
    public final RxTestWrapper<T> notCompleted() {
        testSubscriber.assertNotCompleted();
        return this;
    }

    /** Wraps {@link TestSubscriber#assertError(Class)} */
    public final RxTestWrapper<T> error(Class<? extends Throwable> clazz) {
        testSubscriber.assertError(clazz);
        return this;
    }

    /**
     * Wraps {@link TestSubscriber#assertError(Throwable)}
     */
    public final RxTestWrapper<T> error(Throwable throwable) {
        testSubscriber.assertError(throwable);
        return this;
    }

    /** Wraps {@link TestSubscriber#assertNoTerminalEvent()} */
    public final RxTestWrapper<T> noTerminalEvent() {
        testSubscriber.assertNoTerminalEvent();
        return this;
    }

    /** Wraps {@link TestSubscriber#assertNoValues()} */
    public final RxTestWrapper<T> noValues() {
        testSubscriber.assertNoValues();
        return this;
    }

    /** Wraps {@link TestSubscriber#assertValueCount(int)} */
    public final RxTestWrapper<T> valueCount(int i) {
        testSubscriber.assertValueCount(i);
        return this;
    }

    /** Wraps {@link TestSubscriber#assertValues(Object[])} */
    public final RxTestWrapper<T> values(T... values) {
        testSubscriber.assertValues(values);
        return this;
    }

    /** Wraps {@link TestSubscriber#assertValue(Object)} */
    public final RxTestWrapper<T> value(T value) {
        testSubscriber.assertValue(value);
        return this;
    }

    /** Wraps {@link TestSubscriber#assertValuesAndClear(Object, Object[])} */
    public final RxTestWrapper<T> valuesAndClear(T expectedFirstValue, T... expectedRestValues) {
        testSubscriber.assertValuesAndClear(expectedFirstValue, expectedRestValues);
        return this;
    }

    public final RxTestWrapper<T> valueIsExactlyInstanceOf(Class<? extends T> type) {
        testSubscriber.assertValueCount(1);
        T value = testSubscriber.getOnNextEvents().get(0);
        if (!type.equals(value.getClass())) {
            throw new AssertionError(String.format("Expected value to be instance of %s, \nbut was: %s",
                    type.getName(), value.getClass().getName()));
        }
        return this;
    }
}

