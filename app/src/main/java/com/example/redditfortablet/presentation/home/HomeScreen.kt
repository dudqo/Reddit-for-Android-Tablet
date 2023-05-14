package com.example.redditfortablet.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@ExperimentalMaterial3Api
@Composable
@Preview(name = "NEXUS_10", device = Devices.NEXUS_10)
fun HomeScreen() {
    var state by remember { mutableStateOf(0) }
    var tabRowHeight by remember { mutableStateOf(0) }
    var titles = listOf("Best", "Hot", "New", "Top", "Rising")
    Row() {
        Surface(
            modifier = Modifier.weight(1f)
        ) {
            TabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { tabRowHeight = it.height },
                selectedTabIndex = state) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }

            }
            LazyColumn(
                modifier = Modifier
                    .padding(top = 50.dp)
            ) {
                items(100) {
                    Box() {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = "image", contentDescription = "thumbnail",
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .size(100.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topEnd = 8.dp,
                                            topStart = 8.dp,
                                            bottomStart = 8.dp,
                                            bottomEnd = 8.dp
                                        )
                                    )
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "title",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(10.dp, 2.dp)
                                )
                                Text(
                                    text = "publisher",
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(10.dp, 2.dp)
                                )
                                Text(
                                    text = "subreddit",
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(10.dp, 2.dp)
                                )
                                Text(
                                    text = "",
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Red,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(10.dp, 2.dp),
                                )
                            }
                        }
                    }
                }
            }

        }

        Surface(
            modifier = Modifier.weight(2f)
        ) {
            var text by rememberSaveable { mutableStateOf("") }
            var active by rememberSaveable { mutableStateOf(false) }

            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text("Hinted search text") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            ) {

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = "image", contentDescription = "thumbnail",
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .size(500.dp)
                        .clip(
                            RoundedCornerShape(
                                topEnd = 8.dp,
                                topStart = 8.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 8.dp
                            )
                        )
                )
            }
        }
    }

}