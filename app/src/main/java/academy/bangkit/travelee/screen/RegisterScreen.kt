package academy.bangkit.travelee.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.travelee.signing.R
import com.travelee.signing.components.CustomTextField
import com.travelee.signing.components.PasswordTextField
import com.travelee.signing.components.PrimaryButton
import com.travelee.signing.components.SecondaryButton

@Composable
fun RegisterScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(R.drawable.travelee_logo), contentDescription = stringResource(R.string.travelee_logo_for_signing))
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(label = stringResource(R.string.nama), keyboardType = KeyboardType.Text)
        CustomTextField(label = stringResource(R.string.email_label), keyboardType = KeyboardType.Email)
        PasswordTextField(label = stringResource(R.string.password_label))
        PasswordTextField(label = stringResource(R.string.konfirmasi_password))
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(text = stringResource(R.string.signup)){}
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.atau))
        SecondaryButton(text = stringResource(R.string.masuk)){}
    }
}

/*@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}*/