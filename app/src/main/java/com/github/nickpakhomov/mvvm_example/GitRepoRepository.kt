package com.github.nickpakhomov.mvvm_example


/**
 * Created by Nikolay Pakhomov on 24/09/17.
 */
class GitRepoRepository (val netManager: NetManager) {

    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()

    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {

        netManager.isConnectedToInternet?.let {
            if (it) {
                remoteDataSource.getRepositories(object : OnRepoRemoteReadyCallback {

                    override fun onRemoteDataReady(data: ArrayList<Repository>) {
                        localDataSource.saveRepositories(data)
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            } else {
                localDataSource.getRepositories(object : OnRepoLocalReadyCallback {

                    override fun onLocalDataReady(data: ArrayList<Repository>) {
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }
        }


    }

    interface OnRepositoryReadyCallback {
        fun onDataReady(data : ArrayList<Repository>)
    }
}