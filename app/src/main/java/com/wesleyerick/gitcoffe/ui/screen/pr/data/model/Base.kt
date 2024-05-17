package com.wesleyerick.gitcoffe.ui.screen.pr.data.model

data class Base(
    val label: String,
    val ref: String,
    val repo: Repo,
    val sha: String,
    val user: UserPullRequest
)