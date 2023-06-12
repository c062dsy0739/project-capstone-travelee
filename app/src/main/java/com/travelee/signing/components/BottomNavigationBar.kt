package com.travelee.signing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.travelee.signing.NavigationItem
import com.travelee.signing.ui.theme.TraveleeYellow
import com.travelee.signing.ui.theme.White

val items = listOf(
    NavigationItem.Home,
    NavigationItem.Events,
    NavigationItem.AI,
    NavigationItem.Favourites,
    NavigationItem.Profile,
)

@Composable
fun BottomNavigationBar(
    currentScreen: NavigationItem,
    onNavItemClicked: (NavigationItem) -> Unit
) {
    NavigationBar(containerColor = White, contentColor = Color.Black, modifier = Modifier.height(80.dp)) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentScreen == item,
                onClick = { onNavItemClicked(item) },
                icon = {
                    Box(
                        modifier = Modifier
                            .padding(0.dp)
                            .size(44.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (item == NavigationItem.AI) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(26.dp)
                            )
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }
                },
                label = {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 4.dp)
                            .fillMaxWidth()
                            .height(24.dp), // Adjust the height as needed
                        contentAlignment = Alignment.Center
                    ) {
                        if (item == NavigationItem.AI) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelSmall,
                                color = White,
                            )
                        } else {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1
                            )
                        }
                    }
                },
                modifier = Modifier
                    .padding(4.dp)
                    .background(
                        if (item == NavigationItem.AI) TraveleeYellow else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    ),
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    val currentScreen = remember { NavigationItem.Home }

    BottomNavigationBar(
        currentScreen = currentScreen,
        onNavItemClicked = { item ->
            // Handle navigation item click
        }
    )
}
