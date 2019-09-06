package edwylugo.woopsicredi.viewmodel

import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import edwylugo.woopsicredi.BR
import edwylugo.woopsicredi.core.WoopSicrediService
import edwylugo.woopsicredi.core.viewmodel.BaseViewModel
import edwylugo.woopsicredi.model.response.Event
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val root: View, private val service: WoopSicrediService) : BaseViewModel() {

    val eventsLiveData = MutableLiveData<List<Event>>()

    @get:Bindable
    var loading = false
        set(value) {
            field = value
            notifyPropertyChange(BR.loading)
        }

    fun getEvents(onRefresh: () -> Unit = {}) {
        registerDisposable(
            service.getEvents()
                .doOnSubscribe { loading = true }
                .doFinally { loading = false }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    eventsLiveData.value = it
                    onRefresh()
                }) {
                    Snackbar.make(root, "Erro ao recuperar eventos (${it.message}).", Snackbar.LENGTH_SHORT).show()
                    onRefresh()
                }
        )
    }

}