package academy.bangkit.travelee.components

import academy.bangkit.travelee.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val fontFamily4 = FontFamily(
    Font(R.font.urbanist, FontWeight.Normal),
    Font(R.font.urbanist_semibold, FontWeight.SemiBold),
    Font(R.font.urbanist_medium, FontWeight.Medium),
    Font(R.font.urbanist_bold, FontWeight.Bold),
)

@Composable
fun Greeting(
    userName: String,
    userProfilePhoto: Painter
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = userProfilePhoto,
            contentDescription = "User Profile Photo",
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Hello, $userName!",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily4
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val userProfilePhoto: Painter = painterResource(id = R.drawable.dummy_profile)
    val userName = "Pristia"

    Greeting(userName = userName, userProfilePhoto = userProfilePhoto)
}