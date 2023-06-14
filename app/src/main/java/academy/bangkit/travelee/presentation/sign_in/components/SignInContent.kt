package academy.bangkit.travelee.presentation.sign_in.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.travelee.signing.R
import com.travelee.signing.components.EmailField
import com.travelee.signing.components.PasswordField
import com.travelee.signing.components.SmallSpacer
import com.travelee.signing.components.fontFamily
import com.travelee.signing.ui.theme.TraveleeGreen
import com.travelee.signing.ui.theme.TraveleeWhite
import com.travelee.signing.utilities.Constants.EMPTY_STRING
import com.travelee.signing.utilities.Constants.FORGOT_PASSWORD
import com.travelee.signing.utilities.Constants.SIGN_IN_BUTTON
import com.travelee.signing.utilities.Constants.SIGN_UP_BUTTON

@Composable
@ExperimentalComposeUiApi
fun SignInContent(
    padding: PaddingValues,
    signIn: (email: String, password: String) -> Unit,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit
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
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(R.drawable.travelee_logo), contentDescription = stringResource(R.string.travelee_logo_for_signing))
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        SmallSpacer()
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            }
        )
        SmallSpacer()
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                modifier = Modifier.clickable {
                    navigateToForgotPasswordScreen()
                },
                text = FORGOT_PASSWORD,
                fontSize = 15.sp
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(63.dp),
            onClick = {
                keyboard?.hide()
                signIn(email.text, password.text)},
            colors = ButtonDefaults.buttonColors(containerColor = TraveleeGreen),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(
                text = SIGN_IN_BUTTON,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily
            )
        }
        Text(text = stringResource(R.string.atau))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(63.dp),
            onClick = {
                keyboard?.hide()
                navigateToSignUpScreen()},
            colors = ButtonDefaults.buttonColors(containerColor = TraveleeWhite),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(
                text = SIGN_UP_BUTTON,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily
            )
        }
    }
}