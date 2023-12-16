submitButton = document.getElementById("i_submit");
if(submitButton != null) {
	submitButton.onclick = function(event) {
		var file = document.getElementById("i_file").files[0];
		if(file != null) {
			var fileSize = file.size;
			if(fileSize>67108864) {
				alert("Cafe24 호스팅 서버의 데이터베이스 최대 패킷의 크기는 64MB입니다.!");
				return false;
			}else if(fileSize>10485760*2){
		        var isConfirm = confirm(fileSize +" Bytes\n업로드 파일의 크기가 20MB를 넘어섭니다.\n업로드 하시겠습니까?");
		        if(isConfirm) {
		        	//nothing 	//form submit
		        }else {
		        	return false;	//form submit cancel
		        }
		    }else{
		        //alert(fileSize +" Bytes\n업로드 할 수 있습니다.!");
		    	//nothing
		    }
		}
	}
};
