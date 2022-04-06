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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import xyz.infiiinity.earnmoneysimulator.model.RealEstate
import xyz.infiiinity.earnmoneysimulator.model.Skill
import xyz.infiiinity.earnmoneysimulator.model.Wallet
import xyz.infiiinity.earnmoneysimulator.model.Wallet.propertyList
import xyz.infiiinity.earnmoneysimulator.ui.theme.EarnMoneySimulatorTheme
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.utils.Time

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EarnMoneySimulatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Main()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Main() {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            with(Time) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "${gameYear.value}年${gameMouth.value}月${gameDay.value}日${gameHour.value}时${gameMinute.value}分${gameSecond.value}秒"
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringRes(R.array.time,1) + values[1].value
                )
            }
            for ( property in propertyList ){
                with(property) {
                    for (i in 0 .. lastIndex) if (values[i].value > 0) Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringRes(resId, i) + "${values[i].value}"
                    )
                }
            }
            with(RealEstate) {
                for (i in 0 .. lastIndex) {
                    if (values[i].value > 0) Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringRes(resId, i) + "${values[i].value}"
                    )
                    for(j in 0..factoryList[i].lastIndex){
                        if (factoryList[i].values[j].value > 0) Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringRes(factoryList[i].resId, j) + "${factoryList[i].values[j].value}"
                        )
                    }
                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                count = 4,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.96f)
                    .background(Color.LightGray),
            ) { index ->
                when (index) {
                    0 -> MainPageAction()
                    1 -> MainPageWaifu()
                    2 -> MainPageStudy()
                    3 -> MainHire()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalPagerIndicator( pagerState = pagerState )
            }

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