package com.github.mydogtom.rxtestwrapper;

import org.junit.Test;
import rx.Observable;
import rx.subjects.BehaviorSubject;

import java.util.Arrays;
import java.util.Collections;

import static com.github.mydogtom.rxtestwrapper.RxTestWrapper.assertThat;

public class RxTestWrapperTest {
    @Test
    public void shouldSucceedReceivedOnNext() {
        assertThat(Observable.just("test"))
                .receivedOnNext(Collections.singletonList("test"));
    }

    @Test(expected = AssertionError.class)
    public void shouldFailReceivedOnNext() {
        assertThat(Observable.empty())
                .receivedOnNext(Collections.singletonList("test"));
    }

    @Test
    public void shouldSucceedHasTerminalEvent() {
        assertThat(Observable.empty())
                .hasTerminalEvent();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasTerminalEvent() {
        assertThat(Observable.never())
                .hasTerminalEvent();
    }

    @Test
    public void shouldSucceedUnsubscribed() {
        assertThat(Observable.empty())
                .unsubscribed();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailUnsubscribed() {
        assertThat(Observable.never())
                .unsubscribed();
    }

    @Test
    public void shouldSucceedHasNoErrors() {
        assertThat(Observable.empty())
                .hasNoErrors();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasNoErrors() {
        assertThat(Observable.error(new Throwable()))
                .hasNoErrors();
    }

    @Test
    public void shouldSucceedCompleted() {
        assertThat(Observable.just("test"))
                .completed();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailCompleted() {
        assertThat(Observable.never())
                .completed();
    }

    @Test
    public void shouldSucceedNotCompleted() throws Exception {
        assertThat(Observable.never())
                .notCompleted();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailNotCompleted() throws Exception {
        assertThat(Observable.empty())
                .notCompleted();
    }

    @Test
    public void shouldSucceedHasErrorByType() throws Exception {
        assertThat(Observable.error(new IllegalArgumentException()))
                .hasError(IllegalArgumentException.class);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasErrorByType() throws Exception {
        assertThat(Observable.just("test"))
                .hasError(Throwable.class);
    }

    @Test
    public void shouldSucceedHasError() throws Exception {
        IllegalArgumentException exception = new IllegalArgumentException();
        assertThat(Observable.error(exception))
                .hasError(exception);
    }
    @Test(expected = AssertionError.class)
    public void shouldFailHasError() throws Exception {
        assertThat(Observable.error(new IllegalArgumentException()))
                .hasError(new IllegalArgumentException());
    }

    @Test
    public void shouldSucceedHasNoTerminalEvent() throws Exception {
        assertThat(Observable.never())
                .hasNoTerminalEvent();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasNoTerminalEvent() throws Exception {
        assertThat(Observable.empty())
                .hasNoTerminalEvent();
    }

    @Test
    public void shouldSucceedHasNoValues() throws Exception {
        assertThat(Observable.empty())
                .hasNoValues();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasNoValues() throws Exception {
        assertThat(Observable.just("test"))
                .hasNoValues();
    }

    @Test
    public void shouldSucceedHasValueCount() throws Exception {
        assertThat(Observable.from(Arrays.asList(1, 2, 3)))
                .hasValueCount(3);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasValueCount() throws Exception {
        assertThat(Observable.from(Arrays.asList(1, 2, 3)))
                .hasValueCount(0);
    }

    @Test
    public void shouldSucceedHasValues() throws Exception {
        assertThat(Observable.from(Arrays.asList(1, 2, 3)))
                .hasValues(1, 2, 3);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasValues() throws Exception {
        assertThat(Observable.from(Arrays.asList(1, 2, 3)))
                .hasValues(3, 2, 1);
    }

    @Test
    public void shouldSucceedHasValue() throws Exception {
        assertThat(Observable.just("test"))
                .hasValue("test");
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasValue() throws Exception {
        assertThat(Observable.just("test"))
                .hasValue("another string");
    }

    @Test
    public void shouldSucceedHasValuesAndClear() throws Exception {
        BehaviorSubject<String> subject = BehaviorSubject.create("first item");
        RxTestWrapper<String> assertion = assertThat(subject)
                .hasValuesAndClear("first item");
        subject.onNext("second value");
        assertion.hasValueCount(1);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailHasValuesAndClear() throws Exception {
        assertThat(Observable.just("test"))
                .hasValuesAndClear("anther string");
    }

    @Test
    public void shouldSucceedValueIsExactlyInstanceOf() throws Exception {
        assertThat(Observable.<Throwable>just(new IllegalArgumentException()))
                .valueIsExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailValueIsExactlyInstanceOf() throws Exception {
        assertThat(Observable.<Throwable>just(new IllegalArgumentException()))
                .valueIsExactlyInstanceOf(Throwable.class);

    }
}