package xyz.infiiinity.earnmoneysimulator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.infiiinity.earnmoneysimulator.model.*
import xyz.infiiinity.earnmoneysimulator.model.RealEstate.AGRICULTURAL_LAND
import xyz.infiiinity.earnmoneysimulator.model.RealEstate.factoryList
import xyz.infiiinity.earnmoneysimulator.model.Wallet.CRYPTO
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.utils.Tips
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast

@Composable
fun MainPageAction() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        for((i,factory) in factoryList.withIndex()){
            Row{
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { factory.buy() },
                    content = { Text( stringRes(R.string.buy)+ stringRes(R.array.real_estate,i)) }
                )
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { factory.sell() },
                    content = { Text( stringRes(R.string.sell)+ stringRes(R.array.real_estate,i)) }
                )
            }
        }
    }
}