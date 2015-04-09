window.onload = function() {
	/* Constants */
	var FADE_FAST = 100;
	var FADE_SLOW = 1000;
	
	/* DOM elements */
	var contextMenu = $("#contextMenu");
	var deleteModalLink = $("#deleteModalLink");
	var deletePhotoButton = $("#deletePhotoButton");
	var addPhotoButton = $("#addPhotoButton");
	var photoModal = $("#photoModal");
	var photoModalPhoto = $("#photoModalPhoto");
	
	/* Strings */
	var STRINGS = {
			"en": {
				add: "Add photo...",
				del: "Delete"
			},
			"fr": {
				add: "Ajoutez une photo...",
				del: "Effacer photo"
			}
	};
	
	/* Photo being hovered */
	hoveredPhoto = undefined;
	
	/* Change strings based on language */
	var setLanguage = function() {
		/* Language */
		var language = navigator.userLanguage || navigator.language;
		language.indexOf("en") >= 0 ? language = "en" : language = "fr";
		
		addPhotoButton.val(STRINGS[language].add);
		deletePhotoButton.val(STRINGS[language].del);
	};
	
	/* Context menu handler */
	var mainContextMenu = function(e) {
		if(e.shiftKey || e.ctrlKey || e.metaKey)
			return;
		
		e.preventDefault();
		
		mainContextMenu.selected = hoveredPhoto;
		
		if(contextMenu.is(":visible")) {
			contextMenu.opacity = 0;
		}

		/* Enable delete button if hovering over an image */
		deletePhotoButton.prop("disabled", hoveredPhoto === undefined);
			
		contextMenu.fadeIn(FADE_FAST);
		
		contextMenu.css({left: e.pageX - 2, top: e.pageY - 2});
	};
	
	/* Delete button click handler */
	var deletePhotoButtonOnClick = function(e) {
		$.ajax({
			url: "deletePhoto.jsp",
			data: {
				photo: $(mainContextMenu.selected).attr("name")
				},
			success: function(r) {
				$(mainContextMenu.selected).fadeOut(FADE_SLOW, function() {
					$(mainContextMenu.selected).remove();
					
					/* Remove photo from database */
					mainContextMenu.selected = undefined;
					hoveredPhoto = undefined;
				});
			},
			error: function(r) {
				alert("Sorry, something went wrong. The photo was not deleted.");
			}
		});
	};
	
	/* Context menu hover handler */
	var deleteModalLinkOnClick = function(e) {
		$(hoveredPhoto).fadeOut(FADE_FAST, function() {
			$(hoveredPhoto).remove();
		});
	};
	
	/* Left click handler */
	var click = function(e) {
		if(contextMenu.is(":focus"))
			return;
		
		if(contextMenu.is(":visible"))
			contextMenu.fadeOut(FADE_FAST);
	};
	
	/* Mouseover handler */
	var onPhotoMouseOver = function(e) {
		hoveredPhoto = this;
	};
	
	/* Mouseout handler */
	var onPhotoMouseOut = function(e) {
		hoveredPhoto = undefined;
	};
	
	/* Photo click handler */
	var onPhotoClick = function(e) {
		location.href = "#photoModal";

		photoModalPhoto.prop("src", $(this).children(0).prop("src"));
	};
	
	/* Attach action listeners */
	document.addEventListener("contextmenu", mainContextMenu);
	document.addEventListener("click", click);
	
	/* Action listeners for photos */
	var photoClass = document.getElementsByClassName("photo");
	
	for(var i = 0; i < photoClass.length; ++i) {
		photoClass[i].addEventListener("mouseover", onPhotoMouseOver);
		photoClass[i].addEventListener("mouseout", onPhotoMouseOut);
		photoClass[i].addEventListener("click", onPhotoClick);
	}
	
	deletePhotoButton.on("click", deletePhotoButtonOnClick);
	deleteModalLink.on("click", deleteModalLinkOnClick);
	
	setLanguage();
};