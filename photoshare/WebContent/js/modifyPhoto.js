window.onload = function() {
	/* Constants */
	var FADE_FAST = 100;
	
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
	
	/* Change strings based on language */
	var setLanguage = function() {
		/* Language */
		var language = navigator.userLanguage || navigator.language;
		language.indexOf("en") >= 0 ? language = "en" : language = "fr";
		
		$("#addPhotoButton").val(STRINGS[language].add);
		$("#deletePhotoButton").val(STRINGS[language].del);
	};
	
	/* DOM elements */
	var contextMenu = $("#contextMenu");
	var deleteModalLink = $("#deleteModalLink");
	
	/* Photo being hovered */
	var hoveredPhoto;
	
	/* Context menu handler */
	var mainContextMenu = function(e) {
		if(e.shiftKey || e.ctrlKey || e.metaKey)
			return;
		
		e.preventDefault();
		
		if(contextMenu.is(":visible")) {
			contextMenu.opacity = 0;
		}
		
		/* TODO: Enable / disable delete item if user is over photo */
			
		contextMenu.fadeIn(FADE_FAST);
		
		contextMenu.css({left: e.pageX - 2, top: e.pageY - 2});
	};
	
	/* Context menu hover handler */
	var deleteModalLinkOnClick = function(e) {
		$(hoveredPhoto).fadeOut(FADE_FAST, function() {
			$(hoveredPhoto).remove();
			hoveredPhoto = null;
		});
	};
	
	/* Left click handler */
	var click = function(e) {
		if(contextMenu.is(":focus"))
			return;
		
		if(contextMenu.is(":visible")) {
			contextMenu.fadeOut(FADE_FAST);
		}
	};
	
	/* Mouseover handler */
	var onPhotoMouseOver = function(e) {
		hoveredPhoto = this;
	};
	
	/* Mouseout handler */
	var onPhotoMouseOut = function(e) {
		hoveredPhoto = null;
	};
	
	/* Attach action listeners */
	document.addEventListener("contextmenu", mainContextMenu);
	document.addEventListener("click", click);
	
	/* Action listeners for photos */
	var photoClass = document.getElementsByClassName("photo");
	
	for(var i = 0; i < photoClass.length; ++i) {
		photoClass[i].addEventListener("mouseover", onPhotoMouseOver);
		photoClass[i].addEventListener("mouseout", onPhotoMouseOut);
	}
	
	deleteModalLink.onclick = deleteModalLinkOnClick;
	
	setLanguage();
};