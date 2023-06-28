package br.com.tarcisiofl.bookshelf.presentation.details.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.tarcisiofl.bookshelf.R
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.ui.theme.outlineColor
import br.com.tarcisiofl.bookshelf.utils.formatWithCommas

@Composable
fun BookCardInformation(book: Book) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        InfoCard(
            title = stringResource(id = R.string.languages_label),
            text = book.languages.formatWithCommas().uppercase()
        )
        InfoCard(
            title = stringResource(id = R.string.copyright_label),
            text = book.copyright.toString().uppercase()
        )
        InfoCard(
            title = stringResource(id = R.string.media_type_label),
            text = book.mediaType
        )
    }
}

@Composable
fun InfoCard(title: String, text: String) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .size(100.dp, 64.dp)
            .clip(MaterialTheme.shapes.medium),
        elevation = 8.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.outlineColor),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
        }
    }
}