package academy.bangkit.travelee.presentation.forgot_password.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.travelee.signing.components.EmailField
import com.travelee.signing.components.SmallSpacer
import com.travelee.signing.components.fontFamily
import com.travelee.signing.screen.fontFamily2
import com.travelee.signing.ui.theme.TraveleeGreen
import com.travelee.signing.ui.theme.TraveleeGrey
import com.travelee.signing.utilities.Constants.EMPTY_STRING
import com.travelee.signing.utilities.Constants.RESET_PASSWORD
import com.travelee.signing.utilities.Constants.RESET_PASSWORD_BUTTON

@Composable
fun ForgotPasswordContent(
    padding: PaddingValues,
    sendPasswordResetEmail: (email: String) -> Unit,
) {
    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Text(
                text = RESET_PASSWORD,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 25.sp,
                    color = TraveleeGrey,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily2
                    )
                )
            }
        }
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        SmallSpacer()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(63.dp),
            onClick = {
                sendPasswordResetEmail(email.text)},
            colors = ButtonDefaults.buttonColors(containerColor = TraveleeGreen),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(
                text = RESET_PASSWORD_BUTTON,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily
            )
        }
    }
