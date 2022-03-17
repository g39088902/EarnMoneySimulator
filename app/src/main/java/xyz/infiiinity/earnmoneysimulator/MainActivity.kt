package xyz.infiiinity.earnmoneysimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.tencent.mmkv.MMKV
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.stringRes
import xyz.infiiinity.earnmoneysimulator.ui.theme.EarnMoneySimulatorTheme
import xyz.infiiinity.earnmoneysimulator.viewModel.PowerStation
import xyz.infiiinity.earnmoneysimulator.viewModel.Skill
import xyz.infiiinity.earnmoneysimulator.viewModel.Wallet

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMKV.initialize(this)
        setContent {
            EarnMoneySimulatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Main()
                }
            }
        }
        Wallet.load()
        Skill.load()
        PowerStation.load()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Main() {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.33f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            with(Wallet) {
                for (i in 0 until count) {
                    if (values[i].value > 0) Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringRes(R.array.wallet, i) + "${values[i].value}"
                    )
                }
            }
            if (PowerStation.powerStation.value > 0) Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.power_station) + "${PowerStation.powerStation.value}"
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
                .fillMaxSize()
        ) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                count = 3,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.9f)
                    .background(Color.LightGray),
            ) { index ->
                when (index) {
                    0 -> MainPageAction()
                    1 -> MainPageWaifu()
                    2 -> MainPageStudy()
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EarnMoneySimulatorTheme {
        Main()
    }
}