#DEPRECATED
[RxJava 1.2.3](https://github.com/ReactiveX/RxJava/releases/tag/v1.2.3) introduced `AssertableSubscriber`. It provides identical functionality as `TestSubscriber` but allows chaining of assertions. Also `Observable`, `Completable` and `Single` have `test()` method that returns `AssertableSubscriber`.
# RxTestWrapper
Wrapper around `TestSubscriber`. Allows chaining of assertion methods, making code more concise and more readable.

#Example
**pure rxJava syntax**
```java
    @Test
    public void pureRxJavaSyntax() {
        TestSubscriber<String> testSubscriber = TestSubscriber.create();
        Observable.just("test").subscribe(testSubscriber);
        testSubscriber.assertValue("test");
        testSubscriber.assertCompleted();
    }
```
**with RxTestWrapper**
```java
    @Test
    public void syntaxWithRxTestWrapper() {
        RxTestWrapper.assertThat(Observable.just("test"))
                .hasValue("test")
                .completed();
    }
```
**with RxTestWrapper and Kotlin**
```kotlin
    @Test
    fun syntaxWithRxTestWrapper() {
        Observable.just("test").test()
                .hasValue("test")
                .completed()
    }
```


#Idea and advantages
Initial idea is taken from [RxAssertions](https://github.com/ubiratansoares/rxassertions) and [PR#3304](https://github.com/ReactiveX/RxJava/pull/3304)  from rxJava (which is rejected due to binary incompatibility).

Advantages (comparing RxAssertions v0.3.2):
* API as much close to RxJava as possible. For example: `assertCompleted` -> `completed`, `assertValue` -> `hasValue`. It makes significantly easy to switch between projects that use `rxJava 1.x`, `rxJava 2.x` and `RxTestWrapper`. No need to keep in memory different method names.
* Library do not enforce you to use `BlockingObservable`. That means that you can use `TestScheduler` during you test. See advanced examples.
* No external dependencies except `rxJava`

#Download
```
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    testCompile 'com.github.MyDogTom:RxTestWrapper:v0.1.1'
}
```

#Advanced examples
###with `TestScheduler`
```
    @Test
    public void testWithScheduler() throws Exception {
        TestScheduler scheduler = new TestScheduler();

        RxTestWrapper<Long> assertion = RxTestWrapper.assertThat(Observable.interval(1, TimeUnit.MILLISECONDS, scheduler).take(3));
        assertion
                .notCompleted()
                .hasNoValues()
                .hasNoErrors();

        scheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS);
        assertion.hasValueCount(1);

        scheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS);
        assertion.hasValueCount(2);

        scheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS);
        assertion.hasValueCount(3)
                .completed();
    }
```
#Further development and Contributing
PRs, ideas and suggestions are welcome!
