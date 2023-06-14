package com.travelee.signing.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.travelee.signing.R
import com.travelee.signing.ui.theme.TraveleeYellow
import com.travelee.signing.ui.theme.TraveleeYellow2



// stateless textfield untuk password
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier : Modifier = Modifier
) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var passwordVisible by remember { mutableStateOf(false) }
    val image = if (passwordVisible) {
        painterResource(R.drawable.ic_visibility)
    } else {
        painterResource(R.drawable.ic_visibilityoff)
    }


    OutlinedTextField(
        value = text,
        onValueChange = { value ->
            text = value
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledBorderColor = TraveleeYellow,
            focusedBorderColor = TraveleeYellow,
            unfocusedBorderColor = TraveleeYellow,
            errorBorderColor = Color.Red,
            cursorColor = TraveleeYellow
        ),
        maxLines = 1,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(63.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = { Text(
            text = label,
            color = TraveleeYellow2 )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = image,
                    contentDescription = "")
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
        PasswordTextField(label = stringResource(id = R.string.password_label))
    }

