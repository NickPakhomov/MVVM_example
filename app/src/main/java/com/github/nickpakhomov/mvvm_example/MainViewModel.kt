package com.github.nickpakhomov.mvvm_example

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField

/**
 * Created by Nikolay Pakhomov on 24/09/17.
 */
class MainViewModel : AndroidViewModel{

    constructor(application: Application) : super(application)
    var gitRepoRepository: GitRepoRepository = GitRepoRepository(NetManager(getApplication()))

    val text = ObservableField("old data")
    val isLoading = ObservableField(false)

    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun loadRepositories() {
        isLoading.set(true)

        gitRepoRepository.getRepositories(object : GitRepoRepository.OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }
}