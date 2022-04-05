package xyz.infiiinity.earnmoneysimulator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket

@Composable
fun MainPageWaifu(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Button(
            onClick = { WaifuSocket.nextWaifu() },
            content = { Text("获得新Waifu") }
        )
        for(waifu in WaifuSocket.waifuList){
            Row(
                modifier = Modifier.height(80.dp)
            ) {
                Image(
                    bitmap = WaifuSocket.base64ToBitmap(waifu).asImageBitmap(),
                    contentDescription = "Waifu",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Button(
                    onClick = {  },
                    content = { Text("锁定") }
                )
                Button(
                    onClick = { WaifuSocket.waifuList.remove(waifu) },
                    content = { Text("删除") }
                )
            }

        }

    }
}