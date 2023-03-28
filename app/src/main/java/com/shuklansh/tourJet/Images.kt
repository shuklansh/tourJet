package com.shuklansh.tourJet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.shuklansh.tourJet.Model.Photo
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState



@Composable
fun Images(photlist: List<Photo>) {


    LazyColumn {
        items(photlist) {
            AsyncImage(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f), contentScale = ContentScale.Crop,model = it.src.medium, contentDescription = "imgloaded")

        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun collapsingToolbar(vm : MainViewModel , ptrState : PullRefreshState){
    val state = rememberCollapsingToolbarScaffoldState()
    val viewModel = viewModel<MainViewModel>()

    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {

            val textSize = (18 + (30 - 12) * state.toolbarState.progress).sp


            AsyncImage(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentScale = ContentScale.Crop,model = "https://images.wallpaperscraft.com/image/single/silhouette_travel_hill_187424_300x188.jpg" , contentDescription = "charger" )

            Text(
                "Where to?",
                style = TextStyle(
                    color = Color.Magenta,
                    fontSize = textSize,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .road(whenCollapsed = Alignment.TopStart, whenExpanded = Alignment.BottomStart)
            )


        }){

        Images(photlist = viewModel.listofPhotos )
        Column( modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            PullRefreshIndicator(
                viewModel.isLoading.value,
                ptrState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }
}