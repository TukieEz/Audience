package com.example.audience

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
                Search()
                }
            }
        }
    }
@Composable
private fun AudienceCard(Aud: Any){
    var isOpen by remember {
        mutableStateOf(false)
    }
    var ImgId = 0
    if(Aud is SearchCardAudiences)
        ImgId = Aud.audienceId
    else if(Aud is Persons)
        ImgId = Aud.personId
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .clickable {
                isOpen = !isOpen
            }
    ) {
        if (isOpen) {
            ShowImage(Aud = ImgId)
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
                    text =
                    if(Aud is SearchCardAudiences) "${Aud.audienceTitle}"
                    else if(Aud is Persons) "${Aud.surname} ${Aud.name} ${Aud.middlename}" else "0"
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
private fun AudienceCar(Au: Persons){
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
            ShowImage(Aud = Au.personId)
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
                        text = "${Au.surname} ${Au.name} ${Au.middlename}"
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
private fun ShowImage(Aud: Int){
    Image(painter = painterResource(id = Aud),
        contentDescription = null,
        modifier = Modifier
            .zIndex(1f)
            .fillMaxSize()
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(viewModel: SearchViewModel = viewModel()) {
    var isOpenCard by remember{
        mutableStateOf(false)
    }
    val searchText by viewModel.searchText.collectAsState()
    val searching by viewModel.searching.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth(0.9f)
            .background(Color.White)) {
            TextField(
                value = searchText,
                onValueChange = { viewModel.onSearchTextChange(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Поиск...")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
            )
        }
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 10.dp)
        ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(BackGray)
                        .clickable {
                            isOpenCard = !isOpenCard
                        }
                ) {
                        itemsIndexed(
                            if(isOpenCard) Cards else searching
                        ) { _, item ->
                            AudienceCard(item)
                        }
                    }
               }
            }
        }
