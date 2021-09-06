package com.example.devblock.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.devblock.base.BaseViewModel
import com.example.devblock.data.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> = _userInfo

    fun login(userName: String, password: String) {
        addToDisposable(
            Completable.timer(3, TimeUnit.SECONDS).doOnSubscribe { _loading.postValue(true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _loading.postValue(false) }
                .subscribe({
                    if (userName == "devblock" && password == "2021") {
                        _userInfo.postValue(UserInfo("devblock", "2021"))
                    } else {
                        _userInfo.postValue(UserInfo("", ""))
                    }
                }, { throwable ->
                    print(throwable)
                })
        )

    }
}