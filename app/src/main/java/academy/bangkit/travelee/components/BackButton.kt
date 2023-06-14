package academy.bangkit.travelee.components

import academy.bangkit.travelee.R
import academy.bangkit.travelee.ui.theme.TraveleeBlack
import academy.bangkit.travelee.ui.theme.TraveleeWhite
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
    ) {
        Button(
            onClick = onClick,  //needs changing
            colors = ButtonDefaults.buttonColors(
                containerColor = TraveleeWhite,
                contentColor = TraveleeBlack,
            ),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.backbutton_description)
            )
        }
    }
}

@Preview
@Composable
fun BackButtonPreview() {
    BackButton(onClick = {})
}