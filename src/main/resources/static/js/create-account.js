function changeIdiom() {
	const form = document.getElementById('createAccountForm');
	form.action = form.action + '/changeIdiom';
	form.submit();	
}