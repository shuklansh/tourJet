package com.shuklansh.tourJet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shuklansh.tourJet.ui.theme.JetpackPracticeNewTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackPracticeNewTheme {

                val viewModel = viewModel<MainViewModel>()
                val ptrState= rememberPullRefreshState(viewModel.isLoading.value, {viewModel.loadData()}) // 1

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //collapsingToolbar()
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(ptrState),
                    horizontalAlignment = Alignment.CenterHorizontally) { // 2

                        //Images(photlist = viewModel.listofPhotos)
                        collapsingToolbar(viewModel,ptrState)
                        //PullRefreshIndicator(viewModel.isLoading.value, ptrState) // 3
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackPracticeNewTheme {
        Greeting("Android")
    }
}