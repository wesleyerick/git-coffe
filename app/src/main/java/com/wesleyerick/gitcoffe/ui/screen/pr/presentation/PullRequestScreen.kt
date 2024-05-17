package com.wesleyerick.gitcoffe.ui.screen.pr.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wesleyerick.gitcoffe.R
import com.wesleyerick.gitcoffe.ui.component.GitCoffeTitle
import com.wesleyerick.gitcoffe.ui.component.TitleItem
import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItem
import com.wesleyerick.gitcoffe.ui.theme.CreatedAt
import com.wesleyerick.gitcoffe.ui.theme.ListDivider
import com.wesleyerick.gitcoffe.utils.ResourceView
import com.wesleyerick.gitcoffe.utils.toDateFormatter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun PullRequestScreen(
    navController: NavController,
    viewModel: PullRequestViewModel = koinViewModel(),
    creator: String,
    repo: String
) {

    val listState by viewModel.state.observeAsState()
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            viewModel.getList(
                creator = creator,
                repo = repo
            )
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BackButton(onClick = {
                navController.popBackStack()
            })
            GitCoffeTitle(
                text = "Pull Requests",
                modifier = Modifier
                    .padding(16.dp)
//                    .align(Alignment.CenterHorizontally)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (listState) {
                is ResourceView.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.semantics {
                            contentDescription = "Loading"
                        }
                    )
                }

                is ResourceView.Success -> {
                    val listSuccess =
                        (listState as ResourceView.Success<List<PullRequestItem>>).data
                    if (listSuccess.isNotEmpty()) {
                        PullRequestList(listSuccess)
                    } else {
                        Text(text = context.getString(R.string.no_data))
                    }
                }

                is ResourceView.Error -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.default_error_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }
    }
}

@Composable
fun PullRequestList(list: List<PullRequestItem>) {
    LazyColumn {
        items(list.size) { index ->
            PullRequestListItem(list[index])
        }
    }
}

@Composable
fun PullRequestListItem(item: PullRequestItem) {
    Column {
        TitleItem(text = item.title, maxLines = 2)
        Row {
            Text(text = "by: ")
            Text(text = item.user.login, fontWeight = FontWeight.SemiBold)
        }
        Row {
            Text(
                text = "created at: ",
                color = CreatedAt,
                fontSize = 14.sp,
            )
            Text(
                text = item.created_at.toDateFormatter(),
                color = CreatedAt,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

        }
        Text(
            text = item.body,
            maxLines = 4,
            modifier = Modifier.padding(top = 8.dp),
            overflow = TextOverflow.Ellipsis,
        )
        HorizontalDivider(
            color = ListDivider,
            thickness = 1.dp,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
        )
    }
}
