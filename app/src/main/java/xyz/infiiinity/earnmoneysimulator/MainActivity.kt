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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.infiiinity.earnmoneysimulator.ui.theme.EarnMoneySimulatorTheme
import xyz.infiiinity.earnmoneysimulator.Wellet.getCrypto
import xyz.infiiinity.earnmoneysimulator.Wellet.startMine

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EarnMoneySimulatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Header()
                }
            }
        }
        getCrypto()
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
            if(Wellet.crypto.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = "加密货币 ${Wellet.crypto.value}"
            )
            if(Wellet.fossil.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = "化石燃料 ${Wellet.fossil.value}"
            )
            if(Wellet.preciousMetal.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = "贵金属 ${Wellet.preciousMetal.value}"
            )
            if(Wellet.basicMetal.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = "基本金属 ${Wellet.basicMetal.value}"
            )
            if(Wellet.lightMetal.value>0) Text(
                modifier = Modifier.padding(8.dp),
                text = "轻金属 ${Wellet.lightMetal.value}"
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
                onClick = { Wellet.basicMetal.value -= 10 },
                content = { Text("造航母") }
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { Wellet.lightMetal.value -= 30 },
                content = { Text("造火箭") }
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    Wellet.basicMetal.value -= 100
                    Wellet.preciousMetal.value -= 20
                },
                content = { Text("造戴森球") }
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