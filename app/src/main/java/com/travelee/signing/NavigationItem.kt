package com.travelee.signing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AutoMode
import androidx.compose.material.icons.outlined.Diversity2
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var icon: ImageVector, var title: String) {
    object Home : NavigationItem(Icons.Outlined.Home, "Home" )
    object Events : NavigationItem(Icons.Outlined.Diversity2, "Events")
    object AI : NavigationItem(Icons.Outlined.AutoMode, "Whiz")
    object Favourites : NavigationItem(Icons.Outlined.FavoriteBorder, "Favorit")
    object Profile : NavigationItem(Icons.Outlined.AccountCircle, "Profil")
}