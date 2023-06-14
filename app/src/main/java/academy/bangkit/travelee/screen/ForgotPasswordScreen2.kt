package academy.bangkit.travelee.screen

import academy.bangkit.travelee.R
import academy.bangkit.travelee.components.CustomTextField
import academy.bangkit.travelee.components.PrimaryButton
import academy.bangkit.travelee.components.SecondaryButton
import academy.bangkit.travelee.ui.theme.TraveleeGrey
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// perlu dipindahin ke class main activity
val fontFamily2 = FontFamily(
    Font(R.font.urbanist, FontWeight.Normal),
    Font(R.font.urbanist_semibold, FontWeight.SemiBold),
    Font(R.font.urbanist_medium, FontWeight.Medium),
    Font(R.font.urbanist_bold, FontWeight.Bold),
)

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            TextButton(
                onClick = {},
            ) {
                Text(
                    text = stringResource(R.string.reset_password_tittle),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 25.sp,
                        color = TraveleeGrey,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily2
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(label = stringResource(R.string.email_label), keyboardType = KeyboardType.Email)
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(text = stringResource(R.string.reset_password)){}
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.atau))
        SecondaryButton(text = stringResource(R.string.masuk)){}
    }
}

