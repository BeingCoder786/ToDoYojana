package domain

sealed class TaskAction {
    data class Add(val task: ToDOTask) : TaskAction()
    data class Update(val task: ToDOTask) : TaskAction()
    data class Delete(val task: ToDOTask) : TaskAction()
    data class SetCompleted(val task: ToDOTask, val completed: Boolean) : TaskAction()
    data class SetFavorite(val task: ToDOTask, val isFavorite: Boolean) : TaskAction()
}
