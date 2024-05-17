package com.wesleyerick.gitcoffe.ui.screen.pr.data.model

data class PullRequestItem(
    val active_lock_reason: Any = Any(),
    val assignee: Any = Any(),
    val assignees: List<Any> = emptyList(),
    val author_association: String = "",
    val auto_merge: Any = Any(),
    val body: String = "",
    val closed_at: Any = Any(),
    val comments_url: String = "",
    val commits_url: String = "",
    val created_at: String = "",
    val diff_url: String = "",
    val draft: Boolean = false,
    val html_url: String = "",
    val id: Int = 0,
    val issue_url: String = "",
    val labels: List<Any> = emptyList(),
    val locked: Boolean = false,
    val merge_commit_sha: String = "",
    val merged_at: Any = Any(),
    val milestone: Any = Any(),
    val node_id: String = "",
    val number: Int = 0,
    val patch_url: String = "",
    val requested_reviewers: List<Any> = emptyList(),
    val requested_teams: List<Any> = emptyList(),
    val review_comment_url: String = "",
    val review_comments_url: String = "",
    val state: String = "",
    val statuses_url: String = "",
    val title: String = "",
    val updated_at: String = "",
    val url: String = "",
    val user: UserPullRequest = UserPullRequest()
)