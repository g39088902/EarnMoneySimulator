package xyz.infiiinity.earnmoneysimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tencent.mmkv.MMKV
import xyz.infiiinity.earnmoneysimulator.Wallet.loadMMKV
import xyz.infiiinity.earnmoneysimulator.Wallet.powerGenerate
import xyz.infiiinity.earnmoneysimulator.Wallet.saveMMKV
import xyz.infiiinity.earnmoneysimulator.ui.theme.EarnMoneySimulatorTheme
import xyz.infiiinity.earnmoneysimulator.Wallet.startMine

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
        loadMMKV()
        startMine()
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
            if(Wallet.crypto.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.crypto)+"${Wallet.crypto.value}"
            )
            if(Wallet.electricity.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.electricity)+"${Wallet.electricity.value}"
            )
            if(Wallet.fossil.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.fossil)+"${Wallet.fossil.value}"
            )
            if(Wallet.preciousMetal.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.precious_metal)+"${Wallet.preciousMetal.value}"
            )
            if(Wallet.basicMetal.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.basic_metal)+"${Wallet.basicMetal.value}"
            )
            if(Wallet.lightMetal.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.light_metal)+"${Wallet.lightMetal.value}"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { Wallet.basicMetal.value -= 10 },
                content = { Text(stringResource(R.string.build_aircraft_carrier)) }
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { Wallet.lightMetal.value -= 30 },
                content = { Text(stringResource(R.string.build_rocket)) }
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    Wallet.basicMetal.value -= 100
                    Wallet.preciousMetal.value -= 20
                },
                content = { Text(stringResource(R.string.build_dyson_sphere)) }
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    powerGenerate()
                },
                content = { Text(stringResource(R.string.power_generate)) }
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