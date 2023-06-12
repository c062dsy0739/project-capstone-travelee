package com.travelee.signing.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.travelee.signing.R
import com.travelee.signing.ui.theme.SoftGray
import com.travelee.signing.ui.theme.TraveleeSoftYellow
import com.travelee.signing.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    placeholder: String,
    value: String,
    enable: Boolean = true,
    onClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onClear: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(63.dp)
            .clickable {
                onClick()
            },
        value = value,
        enabled = enable,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = White,
            unfocusedBorderColor = White,
            focusedBorderColor = SoftGray,
            cursorColor = TraveleeSoftYellow
        ),
        shape = RoundedCornerShape(12.dp),
        maxLines = 1,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Medium,
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp),
                painter = painterResource(R.drawable.baseline_search_24),
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (value.length > 3) {
                Icon(
                    modifier = Modifier.clickable {
                        onClear()
                    },
                    painter = painterResource(R.drawable.outline_cancel_24),
                    contentDescription = "cancel typing",
                    tint = SoftGray
                )
            }
        },
        onValueChange = onValueChange,
        placeholder = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
            }
        },
    )
}

@Preview
@Composable
fun SearchFieldPreview() {
    val searchText = remember { mutableStateOf("") }

    SearchField(
        placeholder = "Search",
        value = searchText.value,
        onClick = { /* Handle click action */ },
        onValueChange = { newText -> searchText.value = newText },
        onClear = { searchText.value = "" }
    )
}