package com.jmabilon.mymovietheater.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.jmabilon.mymovietheater.domain.model.Trending
import com.jmabilon.mymovietheater.presentation.theme.GreyLight
import com.jmabilon.mymovietheater.utils.Constants.Companion.BASE_IMAGE_URL

@Composable
fun ContentItem(
    content: Trending
) {
    Image(
        modifier = Modifier
            .width(110.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(4))
            .border(width = 1.dp, color = GreyLight, RoundedCornerShape(4)),
        painter = rememberAsyncImagePainter(
            model = BASE_IMAGE_URL + content.posterPath
        ),
        contentDescription = null
    )
}

@Composable
@Preview
fun ContentItemPreview() {
    ContentItem(Trending())
}