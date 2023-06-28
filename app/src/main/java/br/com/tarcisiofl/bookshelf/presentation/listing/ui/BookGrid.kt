package br.com.tarcisiofl.bookshelf.presentation.listing.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.presentation.listing.ui.BookListItem

@Composable
fun BookGrid(
    modifier: Modifier = Modifier, books: LazyPagingItems<Book>, onBookClick: (Book) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(books.itemCount) { index ->
            val book = books[index]
            if (book != null) {
                BookListItem(book = book, onBookClick = onBookClick)
            }
        }
    }
}