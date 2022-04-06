package xyz.infiiinity.earnmoneysimulator

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket
import xyz.infiiinity.earnmoneysimulator.model.Wallet
import xyz.infiiinity.earnmoneysimulator.model.Wallet.CRYPTO
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast

@Composable
fun MainPageWaifu(){
    val scrollState = rememberScrollState(0)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
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
                    content = { Text(stringRes(R.string.lock)) }
                )
                Button(
                    onClick = { WaifuSocket.waifuList.remove(waifu) },
                    content = { Text(stringRes(R.string.delete)) }
                )
            }
        }
        Button(
            onClick = {
                if (Wallet.values[CRYPTO].value>10000){
                    Wallet.values[CRYPTO].value-=10000
                    WaifuSocket.nextWaifu()
                }else toast(stringRes(R.string.no_item))
            },
            content = { Text("获得新Waifu(一万块)") }
        )
    }
}