package io.github.takusan23.irsendroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.takusan23.irsendroid.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = viewModel()) {

    val pattern = viewModel.patternLiveData.observeAsState(initial = "")

    val isErrorLiveData = viewModel.isErrorLiveData.observeAsState(initial = false)

    Column {
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(300.dp),
            isError = isErrorLiveData.value,
            value = pattern.value,
            onValueChange = { viewModel.setPattern(it) },
            label = { Text(text = "赤外線のパターンを入力") }
        )
        Button(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(10.dp),
            onClick = { viewModel.sendIr() }
        ) {
            Text(text = "赤外線送信")
        }
    }

}