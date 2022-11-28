function changeIdiom(selectedLanguage) {
	const form = document.getElementById('createAccountForm');
	form.action = form.action + '/changeIdiom';
	form.submit();	
}1