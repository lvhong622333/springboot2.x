function urlSearch() {
	$("#urlInput").removeClass("active");
	$("#urlSearch").addClass("active");
	$("#urlSearchText").show();
	$("#urlInputText").hide();
}
function urlInput() {
	$("#urlSearch").removeClass("active");
	$("#urlInput").addClass("active");
	$("#urlSearchText").hide();
	$("#urlInputText").show();
}