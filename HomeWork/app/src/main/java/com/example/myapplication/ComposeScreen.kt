package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class ComposeScreen {

    @Composable
    fun ComposeMainScreen(onBack: () -> Unit) {
        var screen by remember { mutableStateOf("ColumnRowBox") }

        when (screen) {
            "ColumnRowBox" -> ColumnRowBoxScreen(onNavigate = { screen = it }, onBack = onBack)
            "LazyList" -> LazyListScreen(onNavigate = { screen = it }, onBack = onBack)
            "Counter" -> CounterScreen(onBack = onBack)
        }
    }

    @Composable
    fun ColumnRowBoxScreen(onNavigate: (String) -> Unit, onBack: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Column, Row, Box Example", style = MaterialTheme.typography.h6)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /* Do something */ }) {
                    Text(text = "Button 1")
                }
                Button(onClick = { /* Do something */ }) {
                    Text(text = "Button 2")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray)
            ) {
                Text(
                    text = "This is a Box",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onNavigate("LazyList") }) {
                Text("Go to LazyList Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }

    @Composable
    fun LazyListScreen(onNavigate: (String) -> Unit, onBack: () -> Unit) {
        val items = List(20) { "Item #$it" }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items) { item ->
                    Text(
                        text = item,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(16.dp)
                    )
                }
            }

            Button(onClick = { onNavigate("Counter") }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Go to Counter Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }

    class CounterViewModel : ViewModel() {
        private val _counter = mutableStateOf(0)
        val counter: State<Int> = _counter

        fun incrementCounter() {
            _counter.value += 1
        }
    }

    @Composable
    fun CounterScreen(onBack: () -> Unit, viewModel: CounterViewModel = viewModel()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Counter: ${viewModel.counter.value}",
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.incrementCounter() }) {
                Text(text = "Increment")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}
