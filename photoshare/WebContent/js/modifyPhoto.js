window.onload = function() {
	/* Constants */
	var FADE_FAST = 100;
	
	/* Elements */
	var contextMenu = $("#contextMenu");
	
	/* Context menu handler */
	var mainContextMenu = function(e) {
		if(e.shiftKey || e.ctrlKey || e.metaKey)
			return;
		
		e.preventDefault();
		
		if(contextMenu.is(":visible")) {
			contextMenu.opacity = 0;
		}
		
		contextMenu.fadeIn(FADE_FAST);
		
		contextMenu.css({left: e.pageX - 2, top: e.pageY - 2});
	};
	
	/* Context menu hover handler */
	var mainContextMenuHover = function(e) {
		switch($(this).attr(value)) {
		case "Delete":
			console.log("delete");
			break;
		}
	};
	
	/* Context menu hover handler */
	var mainContextMenuClick = function(e) {
		e.preventDefault();
		
		switch($(this).attr("value")) {
		case "Delete":
			console.log("Deleted.");
			break;
		}
	};
	
	/* Left click handler */
	var click = function(e) {
		if(contextMenu.is(":focus"))
			return;
		
		if(contextMenu.is(":visible")) {
			contextMenu.fadeOut(FADE_FAST);
		}
	};
	
	/* Attach action listeners */
	document.addEventListener("contextmenu", mainContextMenu);
	document.addEventListener("click", click);
	//$(".contextMenuItem").addEventListener("hover", mainContextMenuHover);
	contextMenu.onclick = mainContextMenuClick;
};