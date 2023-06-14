package academy.bangkit.travelee.components

import academy.bangkit.travelee.R
import academy.bangkit.travelee.ui.theme.TraveleeGreen
import academy.bangkit.travelee.ui.theme.TraveleeGrey
import academy.bangkit.travelee.ui.theme.TraveleeTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// perlu dipindahin ke class main activity
val fontFamily = FontFamily(
    Font(R.font.urbanist, FontWeight.Normal),
    Font(R.font.urbanist_semibold, FontWeight.SemiBold),
    Font(R.font.urbanist_medium, FontWeight.Medium),
    Font(R.font.urbanist_bold, FontWeight.Bold),
)

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(63.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
                clip = true
            )
    ) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(63.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = TraveleeGreen),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(
                text = text,
                color = TraveleeGrey,
                fontSize = 21.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    TraveleeTheme() {
        PrimaryButton(text = "Sign In") {
        }
    }
}

