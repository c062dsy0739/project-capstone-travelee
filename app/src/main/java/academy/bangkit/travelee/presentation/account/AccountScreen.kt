package academy.bangkit.travelee.presentation.account

import academy.bangkit.travelee.R
import academy.bangkit.travelee.components.SmallSpacer
import academy.bangkit.travelee.components.TextHeader
import academy.bangkit.travelee.sources.Constants.IN_DEV
import academy.bangkit.travelee.sources.ProfileItem
import academy.bangkit.travelee.ui.theme.Ruby
import academy.bangkit.travelee.ui.theme.TraveleeSoftYellow
import academy.bangkit.travelee.ui.theme.White
import academy.bangkit.travelee.ui.theme.White2
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen() { //navigation parameter needed
    Scaffold { paddingValues ->
        val padding = paddingValues
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White2)
        ) {
            item {
                SmallSpacer()
                TextHeader(text = "Your Profile")
                SmallSpacer()
                Header()
                Body()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = { /*TODO*/ }) {  // logout viewmodel needed
                        Text(
                            text = "Log Out",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Ruby
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ImageProfile() {
    Image(
        /*model = ImageRequest.Builder(LocalContext.current)
            .data("https://avatars.githubusercontent.com/u/69514214?v=4").crossfade(true).build(),*/
        painter = painterResource(id = R.drawable.avatar),
        contentDescription = "Image Profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .border(width = 2.dp, color = TraveleeSoftYellow, CircleShape)
            .width(60.dp)
            .height(60.dp)
    )
}


@Composable
fun ItemMenuProfile(icon: Int, label: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, IN_DEV, Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(12.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Icon(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            painter = painterResource(id = icon),
            contentDescription = "Icon"
        )
    }
}


@Composable
fun Header() {
    Row(modifier = Modifier.padding(start = 30.dp),
        verticalAlignment = Alignment.CenterVertically)
        {
            ImageProfile()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = White)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Pristia",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "pristia@gmail.com",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
            }
    }
}
@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen()
}

@Preview(showBackground = true)
@Composable
fun Body() {
    Column(modifier = Modifier.padding(20.dp)) {
        Card(colors = CardDefaults.cardColors(containerColor = White)) {
            ProfileItem.data.forEach { item ->
                ItemMenuProfile(icon = item.Icon, label = item.Label)
            }
        }
    }
}
