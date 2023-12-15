package com.example.audience

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader
import com.example.audience.ui.theme.AudienceTheme

class MyGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val model = ObjLoader().loadModel(Gdx.files.internal("app/sampledata/333 real.obj"))

        }
    }
}