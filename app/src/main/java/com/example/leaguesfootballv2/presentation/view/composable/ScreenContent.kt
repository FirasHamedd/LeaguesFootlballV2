package com.example.leaguesfootballv2.presentation.view.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import com.example.leaguesfootballv2.R

private const val EMPTY_STRING = ""

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    toolbarTitle: String = EMPTY_STRING,
    toolbarIcon: Painter? = null,
    contentDescription: String? = null,
    hasToolbar: Boolean = true,
    onBackClicked: () -> (Unit) = {},
    content: @Composable (padding: PaddingValues) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            if (hasToolbar) {
                ToolbarComponent(
                    backgroundColor = colorResource(
                        id = R.color.purple_500
                    ),
                    contentColor = colorResource(id = R.color.white),
                    title = {
                        Text(text = toolbarTitle.uppercase())
                    },
                    icon = toolbarIcon,
                    contentDescription = contentDescription,
                    onBackClicked = onBackClicked,
                )
            }
        },
        content = content,
    )
}

@Composable
fun ToolbarComponent(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    icon: Painter? = null,
    contentDescription: String? = null,
    title: @Composable () -> Unit,
    onBackClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier.statusBarsPadding(),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        title = title,
        navigationIcon = {
            icon?.let {
                IconButton(
                    onClick = onBackClicked
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = contentDescription
                    )
                }
            }
        },
    )
}