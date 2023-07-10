package com.example.leaguesfootballv2.presentation.view.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.leaguesfootballv2.R

@Composable
fun CommonGeneralError(
    errorMessage: String = stringResource(id = R.string.common_general_dialog_message),
    buttonLabel: String = stringResource(R.string.common_general_dialog_button),
    shouldShowDialogState: MutableState<Boolean> = remember { mutableStateOf(true) },
    onErrorDialogClosed: () -> Unit = {},
) {
    if (shouldShowDialogState.value) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        shouldShowDialogState.value = false
                        onErrorDialogClosed()
                    }
                )
                {
                    Text(text = buttonLabel)
                }
            },
            text = { Text(text = errorMessage) }
        )
    }
}

@Preview
@Composable
fun CommonGeneralErrorPReview() {
    CommonGeneralError(
        onErrorDialogClosed = {}
    )
}