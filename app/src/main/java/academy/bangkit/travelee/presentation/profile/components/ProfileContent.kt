package academy.bangkit.travelee.presentation.profile.components

import academy.bangkit.travelee.R
import academy.bangkit.travelee.components.PrimaryButton
import academy.bangkit.travelee.ui.theme.TraveleeGrey
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileContent(
    padding: PaddingValues
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize().padding(padding).padding(top = 48.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = stringResource(R.string.wellcome_illust)
            )
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material3.Text(
                text = stringResource(R.string.onboarding_text),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 25.sp,
                    color = TraveleeGrey,
                    fontWeight = FontWeight.SemiBold,
                    //fontFamily = fontFamily6
                ),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material3.Text(
                text = stringResource(R.string.onboarding_text2),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 20.sp,
                    color = TraveleeGrey,
                    fontWeight = FontWeight.Medium,
                    //fontFamily = fontFamily6
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(text = stringResource(R.string.letsgo)) {}
        }
    }
}