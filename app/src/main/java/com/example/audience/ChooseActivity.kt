package com.example.audience

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ChooseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .background(Color(0xFFCFCFCF))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                back_arrow(context)
                CenText()
                choose("Артем", R.drawable.artyom,0.5f)
                choose("Ильнур", R.drawable.ilnur,1f)
            }
        }
    }
}
@Composable
private fun choose(name: String, model: Int, weight: Float){
        Column(
            modifier = Modifier
                .fillMaxHeight(weight)
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontSize = 50.sp
            )
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .size(300.dp),
                painter = painterResource(id = model),
                contentDescription = null,
            )
        }
}
@Composable
private fun back_arrow(context: Context){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ){
        Image(
            painter = painterResource(id = R.drawable.left_arrow__1_),
            contentDescription = null,
            alignment = Alignment.TopStart,
            modifier = Modifier
                .size(50.dp)
                .padding(top = 5.dp, start = 5.dp)
                .clickable {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
        )
    }
}
@Composable
private fun CenText(){
        Text(
            text = "Выберите персонажа",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(top=10.dp)
                .fillMaxWidth()
        )
    }