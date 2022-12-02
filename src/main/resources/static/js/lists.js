const confirmDeleteModal = document.getElementById('confirmDeleteModal');
confirmDeleteModal.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget;
    
    const modalTitleValue = button.getAttribute('data-bs-modal-title');
    const confirmDeleteModalTitleField = document.getElementById('confirmDeleteModalTitle');
    confirmDeleteModalTitleField.innerHTML = modalTitleValue;
    
    const modalTextValue = button.getAttribute('data-bs-modal-text');
    const confirmDeleteModalTextField = document.getElementById('confirmDeleteModalText');
    confirmDeleteModalTextField.innerHTML = modalTextValue;
    
    var deleteItemUrlValue = button.getAttribute('data-bs-delete-url');
	const urlField = document.getElementById('confirmDeleteModalDeleteUrlHiddenField');
	const idListToken = 'IDLIST';
	
	if (deleteItemUrlValue.includes(idListToken)) {
		deleteItemUrlValue = deleteItemUrlValue.replace(idListToken, getSelectedItems().toString());
	}
	urlField.value = deleteItemUrlValue;

    document.getElementById('confirmDeleteModalDeleteButton').onclick = function() {
		showLoadingDiv();
		window.location.href = urlField.value;
    };
});

function changePageSize() {
	document.getElementById('changePageSizeForm').submit();
}

function updateDeleteAll() {
	if (countSelectedItems() > 1) {
		document.getElementById('deleteAllButton').classList.remove('disabled');
	} else {
		document.getElementById('deleteAllButton').classList.remove('disabled');
		document.getElementById('deleteAllButton').classList.add('disabled');
	}
}

function countSelectedItems() {
	return getSelectedItems().length;
}

function getSelectedItems() {
	var selectedItems = [];
	
	let checkBoxList = document.querySelectorAll("input[type=checkbox]");
	for (var i = 0; i < checkBoxList.length; i++) {
		if (checkBoxList[i].id.includes('selectItem_#') && checkBoxList[i].checked) {
			selectedItems.push(checkBoxList[i].id.replace('selectItem_#', ''));
		}
    }
	return selectedItems;
}

function selectOrUnselectAllItems() {
	let checkBoxList = document.querySelectorAll("input[type=checkbox]");
	let valueToApply = document.getElementById('checkAllItems');
	for (var i = 0; i < checkBoxList.length; i++) {
		if (checkBoxList[i].id.includes('selectItem_#')) {
			checkBoxList[i].checked = valueToApply.checked;
		}
	}
}