package academy.bangkit.travelee.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

@Composable
fun Gallery(
    images: List<ImageBitmap>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(images) { image ->
            Image(
                bitmap = image,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}
