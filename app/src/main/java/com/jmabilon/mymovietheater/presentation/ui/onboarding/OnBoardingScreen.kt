package com.jmabilon.mymovietheater.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.jmabilon.mymovietheater.R
import com.jmabilon.mymovietheater.navigation.ScreensRoute
import com.jmabilon.mymovietheater.presentation.theme.Grey
import com.jmabilon.mymovietheater.presentation.theme.White20

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<OnBoardingScreenViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val posters = listOf(
            R.drawable.img_interstellar_poster,
            R.drawable.img_the_batman_poster,
            R.drawable.img_dune_poster
        )
        val titleList = listOf(
            "If You Looking For The Latest Movies",
            "Hello Welcome to My Movie Theater",
            "Only With Us, Only For You"
        )
        val subtitleList = listOf(
            "Etiam fermentum est pretium neque elementum condimentum sed ac purus",
            "Pellentesque euismod ante non placerat hendrerit. Ut laoreet ullamcorper turpis, et accumsan justo fermentum in",
            "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos"
        )
        val pagerState = rememberPagerState(posters.size)
        val screenConstraints = ConstraintSet {
            val header = createRefFor("header")
            val pager = createRefFor("pager")
            val pagerIndicator = createRefFor("pagerIndicator")

            constrain(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(pager) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }

            constrain(pagerIndicator) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        }

        ConstraintLayout(
            constraintSet = screenConstraints,
            modifier = Modifier
                .fillMaxSize()
        ) {
            HorizontalPager(
                modifier = Modifier
                    .layoutId("pager"),
                state = pagerState
            ) { index ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = posters[index]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .offset(y = 220.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            text = titleList[index],
                            color = Color.White,
                            lineHeight = 35.sp,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            text = subtitleList[index],
                            color = Grey,
                            lineHeight = 16.sp,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        if (index == titleList.size - 1) {
                            Button(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally),
                                onClick = {
                                    viewModel.setShowOnBoardingVisibility()
                                    navHostController.navigate(ScreensRoute.HomeScreen.route)
                                }
                            ) {
                                Text(text = "Start")
                            }
                        }
                    }
                }
            }
            OnBoardingHeaderComponent(navHostController, viewModel)
            Column(
                modifier = Modifier
                    .layoutId("pagerIndicator")
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = Color.White,
                    inactiveColor = Color.Gray
                )
            }
        }
    }
}

@Composable
fun OnBoardingHeaderComponent(
    navHostController: NavHostController,
    viewModel: OnBoardingScreenViewModel
) {
    val constraints = ConstraintSet {
        val title = createRefFor("headerTitle")
        val skipButton = createRefFor("headerSkipButton")

        constrain(title) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(skipButton) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .layoutId("header")
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent)
            .padding(top = 15.dp)
    ) {
        Text(
            modifier = Modifier
                .layoutId("headerTitle")
                .wrapContentHeight(),
            text = "MyMovieTheater",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .layoutId("headerSkipButton")
                .wrapContentWidth(),
            onClick = {
                viewModel.setShowOnBoardingVisibility()
                navHostController.navigate(ScreensRoute.HomeScreen.route)
            },
            colors = ButtonDefaults.buttonColors(White20),
            shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
        ) {
            Text(
                text = "Skip",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview
private fun OnBoardingScreenComponentPreview() {
    OnBoardingScreen(
        navHostController = rememberNavController()
    )
}