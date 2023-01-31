<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Request</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
$(function() {
	// 여기에 코드를 작성하면 HTML 문서가 로드된 후 실행
	// console.log("Hello")
	var clockEl = $("#clock");
	var resultEl = $("#result")
	
	setInterval(function() {
		clockEl.text(new Date());
	}, 1000);
	
	$('#form').submit(function(event) {
		console.log($(this).serialize())
		$.ajax({
			url: "hr/ajax",
			type: "POST",
			data: $(this).serialize(),
			dataType: "json",
			success: function(result, status) {
				resultEl.text(result["firstName"])
			},
			error: function(error) {
				console.log(error)
			}
		});
		event.preventDefault();
	});
});
</script>
</head>
<body>
<div id="clock"></div>
<form id="form" method="post">
<input type="number" name="employeeId">
<input type="submit" value="비동기 요청">
</form>
<div id="result"></div>
</body>
</html>