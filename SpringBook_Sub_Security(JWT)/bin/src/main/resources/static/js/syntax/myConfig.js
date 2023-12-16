SyntaxHighlighter.defaults["toolbar"] = false; // 툴바없애기
SyntaxHighlighter.defaults["auto-links"] = false; // 자동 링크없애기
SyntaxHighlighter.defaults["tab-size"] = 2; // 탭사이즈를 2로 설정
SyntaxHighlighter.all();
// 줄바꿈을 안하실 분들은 여기 부터 생략하세요.
function SyntaxlineWrap(){ // 줄바꿈을 위한 스크립트
	var wrap = function () {
		var elems = document.getElementsByClassName('syntaxhighlighter');
	        for (var j = 0; j < elems.length; ++j) {
	            var sh = elems[j];
	            var gLines = sh.getElementsByClassName('gutter')[0].getElementsByClassName('line');
	            var cLines = sh.getElementsByClassName('code')[0].getElementsByClassName('line');
	            var stand = 20;
	            for (var i = 0; i < gLines.length; ++i) {
	                var h = $(cLines[i]).height();
	                if (h != stand) {
	                    //console.log(i);
	                    gLines[i].setAttribute('style', 'height:' + h + 'px !important;');
	                }
	            }
	        }
	    };
	    var whenReady = function () {
	        if ($('.syntaxhighlighter').length === 0) {
	            setTimeout(whenReady, 800);
	        } else {
	            wrap();
	        }
	    };
	    whenReady();
	}
	$(function(){
	   $(window).bind("load resize", function(){
	     SyntaxlineWrap();
	   });
	});