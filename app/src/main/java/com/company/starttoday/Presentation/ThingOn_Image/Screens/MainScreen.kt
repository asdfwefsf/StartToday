package com.company.starttoday.Presentation.ThingOn_Image.Screens

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.company.starttoday.Presentation.ThingOn_Image.ViewModel.ImageLinkViewModel
import com.company.starttoday.Presentation.ThingOn_Image.ViewModel.ThingOnViewModel
import com.company.starttoday.Theme.Sizes
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    count: Int,
) {

    val thingOnViewModel: ThingOnViewModel = hiltViewModel()
    val categories = thingOnViewModel.thingOn.collectAsState()

//    val categories = stringAllViewModel.categories.collectAsState()

    val imageLinkViewModel: ImageLinkViewModel = hiltViewModel()
    val imageLinklist = imageLinkViewModel.imageLinks.collectAsState()

    var test = imageLinklist.value


//    val test = stringAllViewModel.thionOnFlow.collectAsState(initial = listOf<ThingOn>()).value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val horizontalPagerState = rememberPagerState(
            pageCount = {
                test.count()
            },
            initialPage = count

        )
        HorizontalPager(
            state = horizontalPagerState,
            modifier = Modifier.size(250.dp),
        ) { page ->
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .applyCubic(horizontalPagerState, page)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(test[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { Log.d("haha", "haha") }
                )
                LaunchedEffect(key1 = horizontalPagerState.currentPage) {
                    imageLinkViewModel.save(horizontalPagerState.currentPage)
                }
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(Sizes.medium),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, {})
            }

        }
    }

}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(Sizes.medium))
            .border(1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, Sizes.extra),
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun Modifier.applyCubic(state: PagerState, page: Int, horizontal: Boolean = true): Modifier {
    return graphicsLayer {
        val pageOffset = state.offsetForPage(page)
        val offScreenRight = pageOffset < 0f
        val def = if (horizontal) {
            105f
        } else {
            -90f
        }
        val interpolated = FastOutLinearInEasing.transform(pageOffset.absoluteValue)
        val rotation = (interpolated * if (offScreenRight) def else -def).coerceAtMost(90f)
        if (horizontal) {
            rotationY = rotation
        } else {
            rotationX = rotation
        }

        transformOrigin = if (horizontal) {
            TransformOrigin(
                pivotFractionX = if (offScreenRight) 0f else 1f,
                pivotFractionY = .5f
            )
        } else {
            TransformOrigin(
                pivotFractionY = if (offScreenRight) 0f else 1f,
                pivotFractionX = .5f
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction
