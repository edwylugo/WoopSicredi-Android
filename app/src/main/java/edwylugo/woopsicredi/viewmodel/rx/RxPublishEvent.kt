package edwylugo.woopsicredi.viewmodel.rx

import edwylugo.woopsicredi.model.response.Event
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

object RxPublishEvent {

    private val subject = PublishSubject.create<Event>()

    fun publish(event: Event) = subject.onNext(event)

    fun subscribe(onComplete: (Event) -> Unit): Disposable = subject.subscribe(onComplete)
}