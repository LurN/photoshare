var changeButton;
var emailField;
var loginButton;
var signupButton;

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

	/* Attach handlers */
	changeButton.on("click", changeButtonHandler);
}

/* Signup button handler */
function changeButtonHandler(e) {
	isLoggingIn = !isLoggingIn;

	if(isLoggingIn) {
		/* User wants to log in */
		$(document.getElementById("loginCreateAccount")).text(" Login ");
		emailField.css("display", "none");
		emailField.attr("required", "false");
		signupButton.css("display", "none");
		loginButton.css("display", "inline-block");
		changeButton.text("Sign up");
	} else {
		/* User wants to sign up */
		$(document.getElementById("loginCreateAccount")).text(" Create new account ");
		emailField.css("display", "inline-block");
		emailField.attr("required", "true");
		signupButton.css("display", "inline-block");
		loginButton.css("display", "none");
		changeButton.text("Login");
	}

	changeButton.blur();
}