package com.example.filmgyujtemeny

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filmgyujtemeny.ui.theme.FilmgyujtemenyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen()
{
    val context = LocalContext.current
    val db = remember {
        FilmDatabaseHelper(context.applicationContext)
    }

    var films by remember {
        mutableStateOf(db.getAllFilms())
    }

    var title by remember {
        mutableStateOf("")
    }

    var genre by remember {
        mutableStateOf("")
    }

    var rating by remember {
        mutableStateOf("")
    }

    var editingId by remember {
        mutableStateOf<Int?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ){
        Text(
            text = "Filmnyilvántartó",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text("Film neve")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = genre,
            onValueChange = {genre = it},
            label = {Text("Film kategóriája")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = rating,
            onValueChange = {rating = it},
            label = {Text("Értékelés")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(Modifier.height(12.dp))

        Row{

            Button(
                enabled = title.isNotBlank() &&
                        genre.isNotBlank() &&
                        (rating.toIntOrNull() ?: 0) in 1..5,
                onClick = 
            ) { }
        }


    }

}