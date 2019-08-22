/**
 * 
 */


//프로그램 실행시

$('.topBarButton').focusout(function() {
	$(this).css('border','none');
});

$(document).ready(function() {
	

	
	//친구 목록 모달
	var fListModalLayer = $("#fListModalLayer");
	var fListModalLink = $("#fListModalLink");
	var fListModalCont = $("#fListModalContent");
	var fListMarginLeft = fListModalCont.outerWidth() / 2;
	var fListMarginTop = fListModalCont.outerHeight() / 2;

	fListModalLink.click(function() {
		fListModalLayer.fadeIn("slow");
//		fListModalLayer.css.display = "block";
		fListModalCont.css({
			"margin-top" : -marginTop,
			"margin-left" : -marginLeft
		});
		$(this).blur();
//		$("#fListModalContent > a").focus();
		return false;
	});

	$("#fListModalContent > button").click(function() {
		fListModalLayer.fadeOut("slow");
//		fListModalLink.focus();
	});
	
	//그룹원 목록
	var gListModalLayer = $("#gListModalLayer");
	var gListModalLink = $("#gListModalLink");
	var gListModalCont = $("#gListModalContent");
	var gListMarginLeft = gListModalCont.outerWidth() / 2;
	var gListMarginTop = gListModalCont.outerHeight() / 2;

	gListModalLink.click(function() {
		gListModalLayer.fadeIn("slow");
//		fListModalLayer.css.display = "block";
		gListModalCont.css({
			"margin-top" : -marginTop,
			"margin-left" : -marginLeft
		});
		$(this).blur();
//		$("#fListModalContent > a").focus();
		return false;
	});

	$("#gListModalContent > button").click(function() {
		gListModalLayer.fadeOut("slow");
//		fListModalLink.focus();
	});
	
	//회비 걷기 
	var requestMoneyLayer = $("#requestMoneyLayer");
	var requestMoneyLink = $("#requestMoney");
	var requestMoneyContent = $("#requestMoneyContent");
	var gListMarginLeft = gListModalCont.outerWidth() / 2;
	var gListMarginTop = gListModalCont.outerHeight() / 2;

	requestMoneyLink.click(function() {
		requestMoneyLayer.fadeIn("slow");
//		fListModalLayer.css.display = "block";
		requestMoneyContent.css({
			"margin-top" : -marginTop,
			"margin-left" : -marginLeft
		});
		$(this).blur();
//		$("#fListModalContent > a").focus();
		return false;
	});

	$("#requestMoneyContent > button").click(function() {
		requestMoneyLayer.fadeOut("slow");
//		fListModalLink.focus();
	});
	
	
});