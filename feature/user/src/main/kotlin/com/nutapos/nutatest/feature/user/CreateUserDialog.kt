package com.nutapos.nutatest.feature.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nutapos.nutatest.core.ui.component.NutaTestCtaButton
import com.nutapos.nutatest.core.ui.component.NutaTestOutlinedCtaButton
import com.nutapos.nutatest.core.ui.component.NutaTestTextField
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.Typography
import com.nutapos.nutatest.feature.user.R

@Composable
fun CreateUserDialog(
    onDismissRequest: () -> Unit,
    onSaveUser: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(28.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.title_create_user),
                    style = Typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                NutaTestTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = stringResource(id = R.string.label_user_name),
                    placeholder = stringResource(id = R.string.hint_user_name)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NutaTestOutlinedCtaButton(
                        text = stringResource(id = R.string.action_cancel),
                        onClick = onDismissRequest,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    NutaTestCtaButton(
                        text = stringResource(id = R.string.action_save),
                        onClick = { onSaveUser(name) },
                        modifier = Modifier.weight(1f),
                        enabled = name.isNotBlank()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x80000000)
@Composable
private fun CreateUserDialogPreview() {
    NutaTestTheme {
        CreateUserDialog(onDismissRequest = {}, onSaveUser = {})
    }
}
