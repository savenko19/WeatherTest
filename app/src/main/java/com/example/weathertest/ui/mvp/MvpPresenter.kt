package com.example.weathertest.ui.mvp

interface MvpPresenter<V: MvpView> {

    fun onAttachView(view: V)

    fun viewIsReady()

    fun onDetachView()
}