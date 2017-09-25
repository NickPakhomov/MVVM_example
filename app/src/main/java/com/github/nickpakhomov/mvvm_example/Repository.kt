package com.github.nickpakhomov.mvvm_example

/**
 * Created by Nikolay Pakhomov on 25/09/17.
 */
data class Repository(var repositoryName: String?,
                 var repositoryOwner: String?,
                 var numberOfStars: Int? ,
                 var hasIssues: Boolean = false)