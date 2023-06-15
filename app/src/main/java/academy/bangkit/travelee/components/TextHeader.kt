package academy.bangkit.travelee.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextHeader(text: String) {
    Text(
        modifier = Modifier.padding(start = 36.dp),
        text = text,
        fontSize = 26.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
}
