package xyz.infiiinity.earnmoneysimulator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket

@Composable
fun MainPageWaifu(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            bitmap = WaifuSocket.waifuBitmap.asImageBitmap(), contentDescription = "Waifu"
        )
    }
}