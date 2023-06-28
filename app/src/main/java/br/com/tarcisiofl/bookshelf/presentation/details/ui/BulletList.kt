package br.com.tarcisiofl.bookshelf.presentation.details.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BulletList(
    modifier: Modifier = Modifier,
    style: TextStyle,
    indent: Dp = 20.dp,
    lineSpacing: Dp = 0.dp,
    items: List<String>,
) {
    Column(modifier = modifier) {
        items.forEach {
            Row {
                Text(
                    text = "\u2022",
                    style = style.copy(textAlign = TextAlign.Center),
                    modifier = Modifier.width(indent),
                )
                Text(
                    text = it,
                    style = style,
                    modifier = Modifier.weight(1f, fill = true),
                )
            }
            if (lineSpacing > 0.dp && it != items.last()) {
                Spacer(modifier = Modifier.height(lineSpacing))
            }
        }
    }
}

@Preview
@Composable
fun PreviewBulletList() {
    val myList = listOf(
        "Bildungsromans",
        "City and town life -- Fiction",
        "Didactic fiction",
        "Domestic fiction",
        "England -- Fiction",
        "Love stories",
        "Married people -- Fiction",
        "Young women -- Fiction"
    )
    BulletList(
        modifier = Modifier.padding(24.dp),
        style = MaterialTheme.typography.body1,
        lineSpacing = 8.dp,
        items = myList
    )
}




