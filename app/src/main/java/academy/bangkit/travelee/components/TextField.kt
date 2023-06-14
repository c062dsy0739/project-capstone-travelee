package academy.bangkit.travelee.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.travelee.signing.ui.theme.TraveleeYellow
import com.travelee.signing.ui.theme.TraveleeYellow2

// stateless function of customtextfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier : Modifier = Modifier
) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
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
            errorBorderColor = Red,
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
            color = TraveleeYellow2
        ) }
    )
}



@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    CustomTextField(
        label = "Enter text",
    )
}