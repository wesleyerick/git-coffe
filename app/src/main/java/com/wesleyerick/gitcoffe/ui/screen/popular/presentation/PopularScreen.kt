package com.wesleyerick.gitcoffe.ui.screen.popular.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.wesleyerick.gitcoffe.R
import com.wesleyerick.gitcoffe.ui.component.InfiniteScrollList
import com.wesleyerick.gitcoffe.ui.component.TitleItem
import com.wesleyerick.gitcoffe.ui.navigation.Screen
import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesItem
import com.wesleyerick.gitcoffe.ui.theme.TitleTopScreen
import com.wesleyerick.gitcoffe.ui.theme.ListDivider
import com.wesleyerick.gitcoffe.ui.theme.StarIcon
import com.wesleyerick.gitcoffe.ui.theme.Title
import com.wesleyerick.gitcoffe.utils.ResourceView
import org.koin.androidx.compose.koinViewModel

@Composable
fun PopularScreen(
    navController: NavController,
    viewModel: PopularViewModel = koinViewModel()
) {
    val listState by viewModel.state.observeAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = context.getString(R.string.app_name),
            color = TitleTopScreen,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

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
                        (listState as ResourceView.Success<List<PopularRepositoriesItem>>).data
                    if (listSuccess.isNotEmpty()) {
                        PopularList(navController, viewModel, listSuccess)
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
fun PopularList(
    navController: NavController,
    viewModel: PopularViewModel,
    list: List<PopularRepositoriesItem>
) {
    InfiniteScrollList(
        itemCount = list.size,
        loadMoreItems = {
            viewModel.getList()
        }
    ) { position ->
        PopularListItem(navController, list[position])
    }
}

@Composable
fun PopularListItem(navController: NavController, item: PopularRepositoriesItem) {
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Screen.PR.withArgs
                        .replace("{creator}", item.owner.login)
                        .replace("{repo}", item.name)
                )
            }
        ) {
            UserInformation(item, modifier = Modifier.fillMaxSize(0.25f))
            RepositoryInformation(
                item, modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
            )
        }
        HorizontalDivider(
            color = ListDivider,
            thickness = 1.dp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun RepositoryInformation(item: PopularRepositoriesItem, modifier: Modifier) {
    Column(modifier = modifier) {
        TitleItem(text = item.name)
        Text(
            text = item.description,
            maxLines = 2,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            color = Color.DarkGray,
        )

        RepositoryCount(item, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun RepositoryCount(item: PopularRepositoriesItem, modifier: Modifier) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.fork),
            contentDescription = "App icon",
            modifier = Modifier.size(24.dp)
        )
        RepositoryCountText(text = item.forks_count.toString())

        Spacer(modifier = Modifier.padding(8.dp))

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Star icon",
            tint = StarIcon,
            modifier = Modifier.size(24.dp)
        )
        RepositoryCountText(text = item.stargazers_count.toString())

    }
}

@Composable
fun RepositoryCountText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp
    )
}

@Composable
fun UserInformation(item: PopularRepositoriesItem, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = item.owner.avatar_url,
            contentDescription = "Avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp)
        )
        Text(
            text = item.owner.login,
            color = Title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


