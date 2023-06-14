package com.travelee.signing.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.travelee.signing.R
import com.travelee.signing.components.CustomTextField
import com.travelee.signing.components.PasswordTextField
import com.travelee.signing.components.PrimaryButton
import com.travelee.signing.components.SecondaryButton
import com.travelee.signing.ui.theme.TraveleeGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(R.drawable.travelee_logo), contentDescription = stringResource(R.string.travelee_logo_for_signing))
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(label = stringResource(R.string.email_label), keyboardType = KeyboardType.Email)
        PasswordTextField(label = stringResource(R.string.password_label))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(
                onClick = {},
            ) {
                Text(
                    text = stringResource(id = R.string.lupa_password),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 15.sp,
                        color = TraveleeGrey
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(text = stringResource(R.string.masuk)){}
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.atau))
        SecondaryButton(text = stringResource(R.string.signup)){}
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}


