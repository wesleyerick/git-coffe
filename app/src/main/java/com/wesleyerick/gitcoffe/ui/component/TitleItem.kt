package com.wesleyerick.gitcoffe.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.wesleyerick.gitcoffe.ui.theme.Title

@Composable
fun TitleItem(text: String, maxLines: Int = 1, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}