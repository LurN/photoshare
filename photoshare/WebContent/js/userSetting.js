var changeButton;
var emailField;
var loginButton;
var signupButton;
var loginCreateAccount;
var actionForm;

/*
True: User is in login menu.
False: User is in sign up menu.
*/
var isLoggingIn = true;

function start() {
	/* DOM elements */
	changeButton = $(document.getElementById("changeButton"));
	emailField = $(document.getElementById("emailField"));
	loginButton = $(document.getElementById("loginButton"));
	signupButton = $(document.getElementById("signupButton"));
	loginCreateAccount = $(document.getElementById("loginCreateAccount"));
	actionForm = $(document.getElementById("actionForm"));

	/* Attach handlers */
	changeButton.on("click", changeButtonHandler);
}

/* Signup button handler */
function changeButtonHandler(e) {
	isLoggingIn = !isLoggingIn;

	if(isLoggingIn) {
		/* User wants to log in */
		loginCreateAccount.text(" Login ");
		emailField.css("display", "none");
		emailField.attr("required", "false");

		signupButton.css("display", "none");
		loginButton.css("display", "inline-block");

		changeButton.text("Sign up");

		actionForm.attr("action", "loginServlet");
	} else {
		/* User wants to sign up */
		loginCreateAccount.text(" Create new account ");
		emailField.css("display", "inline-block");
		emailField.attr("required", "true");

		signupButton.css("display", "inline-block");
		loginButton.css("display", "none");

		changeButton.text("Login");

		actionForm.attr("action", "createAccountServlet");
	}

	changeButton.blur();
}