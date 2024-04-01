package com.company.starttoday.Presentation.Screen

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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.company.starttoday.Presentation.ImageLink.ImageLinkViewModel
import com.company.starttoday.Presentation.ViewModel.StringAllViewModel
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    count : Int,
//    repository: StringRepositoryImpl,
//    imateLinkRepository : ImageLinkImpl

    ) {


    val pictures = listOf(
//        R.drawable.ic_launcher_foreground,
//        R.drawable.ic_launcher_background,
//        R.drawable.ic_launcher_foreground,
//        R.drawable.ic_launcher_background,
//        R.drawable.ic_launcher_foreground
        "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/09/18/1e586277-48ba-4e8a-9b98-d8cdbe075d86.jpg",
        "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/09/18/1e586277-48ba-4e8a-9b98-d8cdbe075d86.jpg",
        "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/09/18/1e586277-48ba-4e8a-9b98-d8cdbe075d86.jpg",
        "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/09/18/1e586277-48ba-4e8a-9b98-d8cdbe075d86.jpg",
        "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/09/18/1e586277-48ba-4e8a-9b98-d8cdbe075d86.jpg"


    )


    val stringAllViewModel: StringAllViewModel = hiltViewModel()
    val categories = stringAllViewModel.categories.collectAsState()

    val imageLinkViewModel : ImageLinkViewModel = hiltViewModel()
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
//                Image(
//                    painter = painterResource(id = pictures[page]),
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .clickable { Log.d("haha", "haha") },
//                    contentDescription = null
//                )
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
                stringAllViewModel.save(horizontalPagerState.currentPage)
            }


            }
        }
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, {} )
            }

        }
    }

}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
//            .clickable {
//                onClick(category)
//            }
//            .size(160.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
//            .paint(
//                painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                contentScale = ContentScale.Crop
//            )
            .border(1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 20.dp),
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

