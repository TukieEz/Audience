package com.example.audience

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.audience.ui.theme.AudienceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            home()
        }
    }
}
@Preview
@Composable
private fun home(){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCFCFCF))
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable {
                        //rememberNavController().navigate()
                    },
                painter = painterResource(id = R.drawable.uspu),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Text(
                text = "Audience",
                modifier = Modifier
                    .padding(start = 5.dp),
                fontSize = 25.sp
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .size(300.dp)
                        .clickable {
                            val intent = Intent(context, ChooseActivity::class.java)
                            context.startActivity(intent)
                        },
                    painter = painterResource(id = R.drawable.free_icon_play_button_109197),
                    contentDescription = null
                )
                Text(
                    text = "Просмотр",
                    modifier = Modifier
                        .padding(top=3.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.free_icon_search_bar_8053592),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val intent = Intent(context, SearchActivity::class.java)
                            context.startActivity(intent)
                        }
                )
                Text(
                    text = "Поиск",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.free_icon_exit_402718),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                )
                Text(
                    text = "Выход",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
            ) {
            Text(
                text = "@Designed by students BPOi-21-03",
                fontSize = 15.sp
            )
        }
    }
}

