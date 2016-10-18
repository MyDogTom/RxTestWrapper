package com.github.mydogtom.rxtestwrapper;

import org.junit.Test;
import rx.Observable;

import static org.junit.Assert.*;

/**
 * Created by svyatoslav.chatchenk on 18/10/16.
 */
public class RxTestWrapperTest {
    @Test
    public void name() throws Exception {
        RxTestWrapper.assertThat(Observable.just("asdf"))
                .completed()
                .value("asdf");

    }
}