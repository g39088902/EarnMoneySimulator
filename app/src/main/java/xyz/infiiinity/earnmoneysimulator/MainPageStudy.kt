package xyz.infiiinity.earnmoneysimulator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.viewModel.Plan

@Composable
fun MainPageStudy(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        for(i in 0 .. Plan.lastIndex){
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = if(i == Plan.select.value) ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                else ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                content = { Text(stringRes(R.array.plan,i)) },
                onClick = { Plan.select.value = i }
            )
        }

    }
}