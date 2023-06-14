package academy.bangkit.travelee.presentation.sign_up.components

import academy.bangkit.travelee.R
import academy.bangkit.travelee.components.CustomField
import academy.bangkit.travelee.components.EmailField
import academy.bangkit.travelee.components.PasswordField
import academy.bangkit.travelee.components.SmallSpacer
import academy.bangkit.travelee.components.fontFamily
import academy.bangkit.travelee.ui.theme.TraveleeGreen
import academy.bangkit.travelee.ui.theme.TraveleeWhite
import academy.bangkit.travelee.utilities.Constants.EMPTY_STRING
import academy.bangkit.travelee.utilities.Constants.SIGN_IN_BUTTON
import academy.bangkit.travelee.utilities.Constants.SIGN_UP_BUTTON
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@ExperimentalComposeUiApi
fun SignUpContent(
    padding: PaddingValues,
    signUp: (name : String, email: String, password: String) -> Unit,
    navigateBack: () -> Unit
) {
    var name by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
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
        CustomField(
            name = name,
            onEmailValueChange = { newValue ->
            name = newValue
        }
        )
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp),
            onClick = {
                keyboard?.hide()
                signUp(name.text, email.text, password.text)},
            colors = ButtonDefaults.buttonColors(containerColor = TraveleeGreen),
            shape = RoundedCornerShape(15.dp),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text(
                text = SIGN_UP_BUTTON,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily,
                color = Color.Black
            )
        }
        SmallSpacer()
        Text(text = stringResource(R.string.atau))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp),
            onClick = {
                keyboard?.hide()
                navigateBack()},
            colors = ButtonDefaults.buttonColors(containerColor = TraveleeWhite),
            shape = RoundedCornerShape(15.dp),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text(
                text = SIGN_IN_BUTTON,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily,
                color = Color.Black
            )
        }
    }
}