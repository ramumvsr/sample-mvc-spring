function signin() {
	var username = $("#username").val();
	var password = $("#password").val();
	if(username=="123" && password=="123")
		window.location.href = "second";


	if ($("#sign").valid()) {
		
	} else {
		$(".error_login label").text("");
		return false;
	}

}




document.addEventListener('DOMContentLoaded', function() {
	document.getElementById("signin").addEventListener("click", signin);
});

$(document).ready(function() {
	$(window).resize(centerLogin);
	centerLogin();

	$("#sign").validate({
		rules: {
			username: "required",
			password: "required"
		},

		messages: {
			username: "Please Enter Username",
			password: "Please Enter Password"
		},
		errorPlacement: function(error, element) {
			if (element.attr("name") === "username") {
				error.appendTo(".usernameError");
			} else if (element.attr("name") === "password") {
				error.appendTo(".passwordError");
			} else {
				error.insertAfter(element);
			}
		}
	});
});


$(document).keypress(function(e) {
	if (e.which == 13) {
		signin();
	}
});

function centerLogin() {
	$('.account-wall-main').hide();
	$('.account-wall-main').css({
		position: 'absolute',
		left: ($(window).width() - $('.account-wall-main').outerWidth()) / 2,
		top: ($(window).height() - $('.account-wall-main').outerHeight()) / 2
	});
	setTimeout(function() {
		$('.account-wall-main').fadeIn('fast');
	}, 500);
}