package com.example.audience

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.audience.ui.theme.AudienceTheme
import com.example.audience.ui.theme.BackGray
import com.example.audience.ui.theme.LikeBlack
import com.google.ar.core.Config
import com.google.ar.core.Pose
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.PlacementMode

class ArMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudienceTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize(),
                    ) {
                    Box(modifier = Modifier.fillMaxSize()){
                        val intent = intent
                        if(intent.extras == null){
                            val currentModel = remember {
                                mutableStateOf("4floor")
                            }
                            ARScreen(currentModel.value)
                            Menu(modifier = Modifier.align(Alignment.BottomCenter)){
                                currentModel.value = it
                            }
                        } else {
                            val model = intent.getStringExtra("AudModel")
                            ARScreen(model = model.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Menu(modifier: Modifier, onClick:(String)->Unit){
    var currentIndex by remember {
        mutableStateOf(0)
    }
    val itemList = Cards.filterIsInstance<SearchCardAudiences>()
    val newItem = SearchCardAudiences(0,"4 этаж", "4floor")
    val itemsList = listOf(newItem) + itemList

    fun updateIndex(offset: Int){
        currentIndex = (currentIndex+offset + itemsList.size) % itemsList.size
        onClick(itemsList[currentIndex].audienceModel)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        IconButton(onClick = {
            updateIndex(-1)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "previous"
            )
        }
        //val audText = itemsList[currentIndex].audienceTitle
        Text(
            text = itemsList[currentIndex].audienceTitle,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        IconButton(onClick = {
            updateIndex(1)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "next"
            )
        }
    }
}

@Composable
fun ARScreen(model: String) {
    val nodes = remember {
        mutableListOf<ArNode>()
    }
    val modelNode = remember {
        mutableStateOf<ArModelNode?>(null)
    }
    val placeModelButton = remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        ARScene(
            modifier = Modifier
                .fillMaxSize(),
            nodes = nodes,
            planeRenderer = true,
            onCreate = { arSceneView ->
                arSceneView.lightEstimationMode = Config.LightEstimationMode.DISABLED
                arSceneView.planeRenderer.isShadowReceiver = false
                modelNode.value = ArModelNode(arSceneView.engine, PlacementMode.INSTANT).apply {
                    loadModelGlbAsync(
                        glbFileLocation = "models/${model}.glb",
                        scaleToUnits = 0.8f
                    ) {

                    }
                    onAnchorChanged = {
                        placeModelButton.value = !isAnchored
                    }
                    onHitResult = { node, hitResult ->
                        placeModelButton.value = node.isTracking
                    }

                }
                nodes.add(modelNode.value!!)
            },
            onSessionCreate = {
                planeRenderer.isVisible = false
            }
        )
        if (placeModelButton.value) {
            Button(onClick = {
                modelNode.value?.anchor()
            }, modifier = Modifier.align(Alignment.Center)) {
                Text(text = "Зафиксировать")
            }
        }
    }

    LaunchedEffect(key1 = model){
        modelNode.value?.loadModelGlbAsync(
            glbFileLocation = "models/${model}.glb",
            scaleToUnits = 0.8f
        )
        Log.e("errorloading","ERROR LOADING MODEL")
    }
}