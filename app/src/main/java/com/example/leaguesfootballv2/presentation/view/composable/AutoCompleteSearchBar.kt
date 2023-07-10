package com.example.leaguesfootballv2.presentation.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leaguesfootballv2.R

private const val EMPTY_STRING = ""

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AutoCompleteSearchBar(
    modifier: Modifier,
    items: List<String>,
    isUserTyping: MutableState<Boolean> = remember { mutableStateOf(false) },
    onSearchClicked: (String) -> Unit,
) {
    var leagues by remember { mutableStateOf(items) }
    val textFieldInteractionSource = remember { MutableInteractionSource() }
    var searchText by remember { mutableStateOf(EMPTY_STRING) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isSearchBarFocus by textFieldInteractionSource.collectIsFocusedAsState()
    if (isSearchBarFocus) {
        isUserTyping.value = true
    }

    val trailingIconView = @Composable {
        IconButton(
            onClick = { searchText = EMPTY_STRING },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {

        TextField(
            modifier = modifier.background(Color.White),
            interactionSource = textFieldInteractionSource,
            value = searchText,
            onValueChange = { typedText ->
                leagues = if (typedText.isBlank())
                    emptyList()
                else
                    items.filter { it.lowercase().contains(typedText.lowercase()) }
                searchText = typedText
            },
            label = { Text(stringResource(id = R.string.search_bar_placeholder)) },
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
            trailingIcon = if (searchText.isNotBlank()) trailingIconView else null,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                if (searchText.isNotBlank())
                    onSearchClicked(searchText)
                isUserTyping.value = false
                keyboardController?.hide()
                focusManager.clearFocus()
            })
        )
        if (searchText.isNotBlank() && isUserTyping.value) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .clip(shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                    .heightIn(0.dp, 138.dp)
            ) {
                items(leagues) { item ->
                    Surface(modifier = Modifier.clickable {
                        searchText = item
                        onSearchClicked(item)
                        isUserTyping.value = false
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 16.dp),
                            text = AnnotatedString(item),
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "AutoCompleteSearchBar")
@Composable
fun AutoCompleteSearchBarPreview() {
    AutoCompleteSearchBar(
        modifier = Modifier.fillMaxWidth(),
        items = listOf("Liga", "Ligue 1", "Premier League", "Bundesliga", "Eredivisie", "Serie A"),
        onSearchClicked = {},
    )
}