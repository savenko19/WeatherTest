package com.example.weathertest.ui.mvp

abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {

    var view: V? = null

    override fun onAttachView(view: V) {
        this.view = view
    }

    override fun onDetachView() {
        view = null
    }
}