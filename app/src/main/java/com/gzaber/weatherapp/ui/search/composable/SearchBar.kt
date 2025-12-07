package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.R

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchTextCleared: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = stringResource(R.string.search_icon_content_description)
            )
        },
        trailingIcon = {
            IconButton(onClick = onSearchTextCleared) {
                Icon(
                    painter = painterResource(R.drawable.ic_clear),
                    contentDescription = stringResource(R.string.clear_search_text_content_description)
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
    )
}