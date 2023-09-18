package com.dtu.uemad.birthdaycardtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dtu.uemad.birthdaycardtest.ui.theme.BirthdayCardTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val selectedIndex = remember { mutableStateOf(0) }
                    Column {
                        when (selectedIndex.value) {
                            0 -> Greeting("Favourite")
                            1 -> Greeting("Search")
                            2 -> Greeting("Spellbook")
                            3 -> Greeting("Create")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        BottomNavigation(
                            modifier = Modifier.height(70.dp).background(Color.Black)
                        ) {
                            BottomNavigationItem(
                                icon = {
                                    val iconId = if (selectedIndex.value == 0) {
                                        R.drawable.heart_marked
                                    } else {
                                        R.drawable.heart_unmarked
                                    }
                                    val painter = painterResource(id = iconId)
                                    Icon(painter = painter, contentDescription = "kek lol")
                                },
                                label = { Text("Favourites") },
                                selected = selectedIndex.value == 0,
                                onClick = { selectedIndex.value = 0 }
                            )
                            BottomNavigationItem(
                                icon = {
                                    val iconId = if (selectedIndex.value == 1) {
                                        R.drawable.search_marked
                                    } else {
                                        R.drawable.search_unmarked
                                    }
                                    val painter = painterResource(id = iconId)
                                    Icon(painter = painter, contentDescription = null)
                                },
                                label = { Text("Search") },
                                selected = selectedIndex.value == 1,
                                onClick = { selectedIndex.value = 1 }
                            )
                            BottomNavigationItem(
                                icon = {
                                    val iconId = if (selectedIndex.value == 2) {
                                        R.drawable.spellbook_marked
                                    } else {
                                        R.drawable.spellbook_unmarked
                                    }
                                    val painter = painterResource(id = iconId)
                                    Icon(painter = painter, contentDescription = null)
                                },
                                label = { Text("Spellbook") },
                                selected = selectedIndex.value == 2,
                                onClick = { selectedIndex.value = 2 }
                            )
                            BottomNavigationItem(
                                icon = {
                                    val iconId = if (selectedIndex.value == 3) {
                                        R.drawable.create_marked
                                    } else {
                                        R.drawable.create_unmarked
                                    }
                                    val painter = painterResource(id = iconId)
                                    Icon(painter = painter, contentDescription = null)
                                },
                                label = { Text("Create") },
                                selected = selectedIndex.value == 3,
                                onClick = { selectedIndex.value = 3 }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BirthdayCardTestTheme {
        Greeting("Android")
    }
}
