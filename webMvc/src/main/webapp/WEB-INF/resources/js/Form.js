$(document).ready(function() {

	$('#save').on('click', function() {
		
var id = $("#uid").val();
		var name = $("#uname").val();
		
		
		var jsonParameter = {
			"uId" : id,
			"uName":name
			
		};
alert("before ajax");
		
		$.ajax({
			
			type : 'POST',
			contentType : 'application/json',
			url : 'addUserData',
			data : JSON.stringify(jsonParameter),

			success : function(result) {
				if (result.status == "SUCCESS") {
					successMsgFun(result.message);
					
					location.href = "second";
					
				} else {
					$('#save').prop('disabled', false);
									}
			}
		})

		});
	})