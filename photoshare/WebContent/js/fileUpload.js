var urlUploadCount = 1;

function addURLUpload() {
	// Maximum of 10 links
	if(urlUploadCount >= 10)
		return;
	
	++urlUploadCount;
	
	var url_upload = document.createElement("input");
	url_upload.class = "url_upload";
	url_upload.placeholder = "URL to photo";
	
	var list = document.getElementById("url_upload_list");
	
	list.appendChild(url_upload);
	
	document.getElementById("remove_url_upload").style.display = "inline-block";
};

function removeURLUpload() {
	--urlUploadCount;
	
	var list = document.getElementById("url_upload_list");
	
	list.removeChild(list.lastChild);
	
	if(urlUploadCount === 1) {
		document.getElementById("remove_url_upload").style.display = "none";
		
		return false;
	} else {
		return true;
	}
};

function createNewAlbumModal() {
	document.getElementById("album_name").value = "";
	
	// Remove all URL upload fields
	while(urlUploadCount > 1)
		removeURLUpload();
	
	document.getElementsByClassName("url_upload")[0].value = "";
};

function resetForm(id) {
	var form = document.getElementById(id);
	
	form.value = null;
};