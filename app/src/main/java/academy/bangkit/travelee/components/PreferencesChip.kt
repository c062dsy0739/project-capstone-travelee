package academy.bangkit.travelee.components

import academy.bangkit.travelee.R
import academy.bangkit.travelee.ui.theme.SoftGray
import academy.bangkit.travelee.ui.theme.TraveleeGreen2
import academy.bangkit.travelee.ui.theme.TraveleeSoftYellow
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val fontFamily3 = FontFamily(
    Font(R.font.urbanist, FontWeight.Normal),
    Font(R.font.urbanist_semibold, FontWeight.SemiBold),
    Font(R.font.urbanist_medium, FontWeight.Medium),
    Font(R.font.urbanist_bold, FontWeight.Bold),
)

@Composable
fun CategoryChip(
    icon: Painter,
    text: String,
    checked: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (checked) TraveleeSoftYellow else SoftGray)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = fontFamily3,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 4.dp),
                color = Color.Black
            )
        }
        if (checked) {
            Icon(
                imageVector = (Icons.Default.CheckCircle),
                contentDescription = null,
                tint = TraveleeGreen2,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun CategoryChipValue(){
    CategoryChip(icon = painterResource(id = R.drawable.baseline_beach_access_24), text = "Berenang", checked = true) {
    }
}

@Preview
@Composable
fun CategoryChipValue2(){
    CategoryChip(icon = painterResource(id = R.drawable.baseline_beach_access_24), text = "Berenang", checked = false) {
    }
}