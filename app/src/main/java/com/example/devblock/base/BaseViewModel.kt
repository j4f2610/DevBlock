package com.example.devblock.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Viktor on 31,May,2020
 */
open class BaseViewModel : ViewModel(), HasDisposableManager {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable.isDisposed) compositeDisposable = CompositeDisposable()
        return compositeDisposable
    }

    override fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        getCompositeDisposable().clear()
    }
}

interface HasDisposableManager {
    fun getCompositeDisposable(): CompositeDisposable
    fun addToDisposable(disposable: Disposable)
    fun dispose()
}