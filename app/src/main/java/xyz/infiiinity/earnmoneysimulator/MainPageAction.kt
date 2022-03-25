package xyz.infiiinity.earnmoneysimulator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.infiiinity.earnmoneysimulator.model.PowerStation
import xyz.infiiinity.earnmoneysimulator.model.Wallet

@Composable
fun MainPageAction(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = { Wallet.values[4].value -= 10 },
            content = { Text(stringResource(R.string.build_aircraft_carrier)) }
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = { Wallet.values[5].value -= 30 },
            content = { Text(stringResource(R.string.build_rocket)) }
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                Wallet.values[4].value -= 100
                Wallet.values[3].value -= 20
            },
            content = { Text(stringResource(R.string.build_dyson_sphere)) }
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                PowerStation.buildPowerStation()
            },
            content = { Text(stringResource(R.string.build_power_station)) }
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                PowerStation.destoryPowerStation()
            },
            content = { Text(stringResource(R.string.destory_power_station)) }
        )
    }
}