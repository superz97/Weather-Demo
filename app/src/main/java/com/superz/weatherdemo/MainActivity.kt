package com.superz.weatherdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.superz.weatherdemo.ui.daily.DailyScreen
import com.superz.weatherdemo.ui.home.HomeScreen
import com.superz.weatherdemo.ui.theme.WeatherDemoTheme
import com.superz.weatherdemo.util.Tabs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherDemoTheme {
                WeatherDemoApp()
            }
        }
    }

    @Composable
    private fun WeatherDemoApp() {
        var selectedTabIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                WeatherDemoBottomNavigationBar(
                    tabs = Tabs.entries,
                    selectedIndex = selectedTabIndex
                ) {
                    selectedTabIndex = it
                }
            }
        ) { innerPadding ->
            when (selectedTabIndex) {
                0 -> {
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
                1 -> {
                    DailyScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun WeatherDemoBottomNavigationBar(
        modifier: Modifier = Modifier,
        tabs: List<Tabs>,
        selectedIndex: Int,
        onSelectedChange: (Int) -> Unit
    ) {
        NavigationBar(modifier = modifier) {
            tabs.forEachIndexed { index, tabs ->
                NavigationBarItem(
                    selected = index == selectedIndex,
                    onClick = { onSelectedChange(index) },
                    icon = { Icon(imageVector = tabs.icon, contentDescription = tabs.title) },
                    label = { Text(text = tabs.title) }
                )
            }
        }
    }
}
