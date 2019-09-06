package edwylugo.woopsicredi.viewmodel

import android.view.View
import androidx.databinding.Bindable
import edwylugo.woopsicredi.BR
import edwylugo.woopsicredi.core.WoopSicrediService
import edwylugo.woopsicredi.core.viewmodel.BaseViewModel
import edwylugo.woopsicredi.model.request.CheckIn
import edwylugo.woopsicredi.viewmodel.rx.RxPublishEvent
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventViewModel(
    private val root: View,
    private val sicrediService: WoopSicrediService
) : BaseViewModel() {

    @Bindable
    var description = ""
        set(value) {
            field = value
            notifyPropertyChange(BR.description)
        }

    @Bindable
    var hasShows = true
        set(value) {
            field = value
            notifyPropertyChange(BR.hasShows)
        }

    @Bindable
    var loading = true
        set(value) {
            field = value
            notifyPropertyChange(BR.loading)
        }

    fun getEventById(id: Int, onRefresh: () -> Unit = {}) {
        registerDisposable(
            sicrediService.getEventById(id)
                .doOnSubscribe { loading = true }
                .doFinally { loading = false }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    RxPublishEvent.publish(it)
                    onRefresh()
                }) {
                    Snackbar.make(root, "Erro ao recuperar evento de ID $id (${it.message}).", Snackbar.LENGTH_SHORT).show()
                    onRefresh()
                }
        )
    }

    fun performCheckIn(form: CheckIn, onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        registerDisposable(
            sicrediService.checkIn(form)
                .doOnSubscribe { loading = true }
                .doFinally { loading = false }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete, onError)
        )
    }

}