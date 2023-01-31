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
	var inputEl = $("#employeeId")
	var btnEl = $("input[type=button]")
	var resultEl = $("#result")
	
	setInterval(function() {
		clockEl.text(new Date());
	}, 1000);
	
	btnEl.click(function() {
		var empid = inputEl.val();
		console.log(empid);
		// 비동기 요청을 함
		$.ajax({
			type: "GET",
			url: "hr/json/" + empid,
			dataType: "json",
			success: function(result, status) {
				// resultEl.text(JSON.stringify(result));
				resultEl.text(result["firstName"]);
			},
			error: function(error) {
				console.log(error);
			}
		})
	});
});
</script>
</head>
<body>
<div id="clock"></div>
<input type="number" id="employeeId">
<input type="button" value="비동기 요청">
<div id="result"></div>
</body>
</html>