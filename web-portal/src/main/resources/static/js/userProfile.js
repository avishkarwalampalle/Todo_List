function openTaskForm() {
   document.getElementById("TaskForm").style.display = "block";
}

function closeTaskForm(){
   document.getElementById("TaskForm").style.display = "none";
}

function openEditForm(task) {
    document.getElementById('taskId').value = task.id;
    document.getElementById('userEmail').value = task.userEmail;
    document.getElementById('title').value = task.title;
    document.getElementById('description').value = task.description;
    document.getElementById('dueDate').value = task.dueDate;
    document.getElementById(task.category).checked = true;
    document.getElementById(task.status).checked = true;
    document.getElementById('editForm').action = '/todo-app/profile/updateTask/'+task.id;
    document.getElementById('editForm').style.display = 'block';
}

function closeEditForm() {
    document.getElementById('editForm').style.display = 'none';
}