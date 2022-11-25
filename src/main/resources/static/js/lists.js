const confirmDeleteModal = document.getElementById('confirmDeleteModal')
confirmDeleteModal.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget;
    const idToDelete = button.getAttribute('data-bs-id');
    const idField = document.getElementById('confirmDeleteModalIdHiddenField');
    idField.value = idToDelete;

    document.getElementById('confirmDeleteModalDeleteButton').onclick = function() {
      alert('Will remove id ' + idField.value);
      // Post to controller
      // confirmDeleteModal.hide();
      // $('#confirmDeleteModal').modal('hide')
    };
});