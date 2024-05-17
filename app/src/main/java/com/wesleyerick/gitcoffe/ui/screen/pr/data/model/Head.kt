package com.wesleyerick.gitcoffe.ui.screen.pr.data.model

data class Head(
    val label: String,
    val ref: String,
    val repo: Repo,
    val sha: String,
    val user: UserPullRequest
)