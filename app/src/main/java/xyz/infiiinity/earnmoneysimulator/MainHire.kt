package xyz.infiiinity.earnmoneysimulator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import xyz.infiiinity.earnmoneysimulator.model.Employee
import xyz.infiiinity.earnmoneysimulator.model.Employee.EMPLOYEE
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.model.Plan
import xyz.infiiinity.earnmoneysimulator.model.Wallet
import xyz.infiiinity.earnmoneysimulator.model.Wallet.CRYPTO
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast

@Composable
fun MainHire(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                      if(Wallet.values[CRYPTO].value>100){
                          Wallet.values[CRYPTO].value-=100
                          Employee.values[EMPLOYEE].value++
                      }else toast(stringRes(R.string.no_item))
            },
            content = { Text("招聘") },
        )
    }
}