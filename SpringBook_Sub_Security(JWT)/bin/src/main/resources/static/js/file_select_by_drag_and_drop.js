var target = document.getElementById("droparea");
if(target!=null) {
	target.ondragover = function(event) {
	    if(event.preventDefault) event.preventDefault();
	    return false;
	}
	
	target.ondragenter = function(event){
	    target.classList.add('hover');
	    if(event.preventDefault) event.preventDefault();
	    return false;
	}
	
	target.ondragleave = function(event){
	    target.classList.remove('hover');
	    if(event.preventDefault) event.preventDefault(); 
	    return false;
	}
	 
	target.ondragend = function(event){
	    if(event.preventDefault) event.preventDefault();
	    return false;
	}
	
	target.ondrop = function(event){
	    if(event.preventDefault) event.preventDefault();
	    target.classList.remove('hover');
	    var filedata = event.dataTransfer.files[0];
	    document.getElementById("i_file").value = "";
	}
}