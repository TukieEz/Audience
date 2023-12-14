package com.example.audience

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.audience.ui.theme.AudienceTheme
import com.example.audience.ui.theme.BackGray

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(BackGray)
            ) {
                back_arrow()
                Row(Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center){
                    Text(
                        text = "Поиск",
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp
                    )

                }
                Search()
                    LazyColumn(modifier = Modifier
                        .fillMaxHeight()
                        .background(BackGray)){
                        itemsIndexed(
                            Cards
                        ){_, item ->
                            AudienceCard(Aud = item)
                        }
                    }
                }
            }
        }
    }
@Composable
private fun AudienceCard(Aud: SearchCardAudiences){
    var isOpen by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .clickable {
                isOpen = !isOpen
            }
    ) {
        if (isOpen) {
            ShowImage(Aud = Aud)
        } else{
        Box(
            Modifier
                .clip(CircleShape)
                .fillMaxSize()
                .background(Color.White)
                .zIndex(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 15.dp)
                        .fillMaxWidth(0.9f),
                    text = Aud.audienceTitle
                )
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.free_icon_play_2550046),
                    contentDescription = null
                )
            }
        }
        }
    }
}
@Composable
private fun ShowImage(Aud: SearchCardAudiences){
    Image(painter = painterResource(id = Aud.audienceId),
        contentDescription = null,
        modifier = Modifier
            .zIndex(1f)
            .fillMaxSize()
    )
}
@Composable
private fun Search(){
    val viewModel = viewModel<SearchBar>()
    val searchText by viewModel.searchtext.collectAsState()
    val searching by viewModel.searching.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
    ){
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text(text = "Поиск")
            }
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ){
            items(searching){searching ->
                Text(
                    text = "${searching.audienceTitle} ${searching.teacherSurname} ${searching.teacherName} ${searching.teacherMiddlename}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}