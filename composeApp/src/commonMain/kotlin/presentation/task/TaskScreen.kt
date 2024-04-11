package presentation.task

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.TaskAction
import domain.ToDOTask

const val DEFAULT_TITLE = "Enter the Title"
const val DEFAULT_DESCRIPTION = "Add some description"

data class TaskScreen(val task: ToDOTask? = null) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<TaskViewModel>()
        var currentTitle by remember {
            mutableStateOf(task?.title ?: "")
        }
        var currentDescription by remember {
            mutableStateOf(task?.description ?: "")
        }
        var isPlaceholderVisible by remember { mutableStateOf("".isEmpty()) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        TextField(
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = 12.dp),
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            ),
                            singleLine = true,
                            value = currentTitle,
                            onValueChange = {
                                currentTitle = it
                            },
                            placeholder = {
                                Text(DEFAULT_TITLE)
                            },
                            trailingIcon = {
                                if (currentTitle.isNotEmpty()) {
                                    IconButton(onClick = { currentTitle = "" }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            },
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back Arrow",
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                if (currentTitle.isNotEmpty() && currentDescription.isNotEmpty()) {
                    FloatingActionButton(
                        onClick = {
                            if (task != null) {
                                viewModel.setAction(
                                    action = TaskAction.Update(
                                        ToDOTask().apply {
                                            _id = task._id
                                            title = currentTitle
                                            description = currentDescription
                                        },
                                    ),
                                )
                            } else {
                                viewModel.setAction(
                                    action = TaskAction.Add(
                                        ToDOTask().apply {
                                            title = currentTitle
                                            description = currentDescription
                                        },
                                    ),
                                )
                            }
                            navigator.pop()
                        },
                        shape = RoundedCornerShape(size = 12.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Checkmark Icon",
                        )
                    }
                }
            },
        ) { padding ->
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 24.dp)
                    .padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding(),
                    ),
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                value = currentDescription,
                onValueChange = { description -> currentDescription = description },
                placeholder = {
                    Text(DEFAULT_DESCRIPTION)
                },
                trailingIcon = {
                    if (currentDescription.isNotEmpty()) {
                        IconButton(onClick = { currentDescription = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null,
                            )
                        }
                    }
                },
            )
        }
    }
}
