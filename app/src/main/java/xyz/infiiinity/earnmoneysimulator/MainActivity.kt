package xyz.infiiinity.earnmoneysimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tencent.mmkv.MMKV
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.stringRes
import xyz.infiiinity.earnmoneysimulator.OkHttp.waifuBitmap
import xyz.infiiinity.earnmoneysimulator.PowerStation.buildPowerStation
import xyz.infiiinity.earnmoneysimulator.PowerStation.destoryPowerStation
import xyz.infiiinity.earnmoneysimulator.ui.theme.EarnMoneySimulatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMKV.initialize(this)
        setContent {
            EarnMoneySimulatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Header()
                }
            }
        }
        Wallet.load()
        Skill.load()
        PowerStation.load()
    }
}

@Composable
fun Header() {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.33f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            with(Wallet){
                for (i in 0 until count){
                    if(values[i].value>0) Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringRes(R.array.wallet, i) +"${values[i].value}"
                    )
                }
            }
            if(PowerStation.powerStation.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.power_station)+"${PowerStation.powerStation.value}"
            )
            with(Skill) {
                for (i in 0 until count) {
                    if (values[i].value > 0) Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringRes(R.array.skill, i) + "${values[i].value}"
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                bitmap = waifuBitmap.asImageBitmap()
                ,contentDescription = "Waifu"
            )
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
                    buildPowerStation()
                },
                content = { Text(stringResource(R.string.build_power_station)) }
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    destoryPowerStation()
                },
                content = { Text(stringResource(R.string.destory_power_station)) }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EarnMoneySimulatorTheme {
        Header()
    }
}