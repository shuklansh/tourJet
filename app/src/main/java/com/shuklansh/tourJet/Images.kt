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
                .height(200.dp), contentScale = ContentScale.Crop,model = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/67bd6e8d-605f-4803-92a3-624298b4b26e/dfqj9ag-917cf1bf-1399-4a93-8675-0d465cbb263c.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzY3YmQ2ZThkLTYwNWYtNDgwMy05MmEzLTYyNDI5OGI0YjI2ZVwvZGZxajlhZy05MTdjZjFiZi0xMzk5LTRhOTMtODY3NS0wZDQ2NWNiYjI2M2MucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.8EecUTv7RtQDEA21EGGymdWxYmz6HrTz53bIYzrkg6I" , contentDescription = "charger" )

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