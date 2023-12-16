// Style Builder - Open Button event
$("#cmdShowStyleSwitcher").click(function(){
	if($("#divStyleSwitcher").hasClass("opened")){
		$("body").removeClass("style-switcher-in");
		$("#divStyleSwitcher").removeClass("opened");
	}
	else{
		$("body").addClass("style-switcher-in");
		$("#divStyleSwitcher").addClass("opened");
	}
	return false;
});

//// SCHEMES
var scheme = $.cookie('scheme');

if(scheme == "red") {
	// Select option
	$("#cmdSchemeRed").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-red.css");
}
else if(scheme == "violet") {
	// Select option
	$("#cmdSchemeViolet").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-violet.css");
}
else if(scheme == "blue") {
	// Select option
	$("#cmdSchemeBlue").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-blue.css");
}
else if(scheme == "green") {
	// Select option
	$("#cmdSchemeGreen").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-green.css");
}
else if(scheme == "yellow") {
	// Select option
	$("#cmdSchemeYellow").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-yellow.css");
}
else if(scheme == "orange") {
	// Select option
	$("#cmdSchemeOrange").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-orange.css");
}

// SPECIAL SCHEMES
else if(scheme == "bw") {
	// Select option
	$("#cmdSchemeBW").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-bw.css");
}
else if(scheme == "dark") {
	// Select option
	$("#cmdSchemeDark").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-dark.css");
}
else if(scheme == "flat") {
	// Select option
	$("#cmdSchemeFlat").addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-flat.css");
}

//// BODY BACKGROUNDS
var bodyBg = $.cookie('body-bg');

if (bodyBg == "body-bg-1") {
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-1");
}
else if (bodyBg == "body-bg-2") {
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-2");
}
else if (bodyBg == "body-bg-3") {
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-3");
}
else if (bodyBg == "body-bg-4") {
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-4");
}
else if (bodyBg == "body-bg-5") {
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-5");
}
else if (bodyBg == "body-bg-6") {
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-6");
}
else if (bodyBg == "body-bg-7") {
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-7");
}

//// LAYOUT
var layoutStyle = $.cookie('layout');
// Select option
$("#cmbLayoutStyle").find('option[value="'+ layoutStyle +'"]').attr("selected",true)

// Set option
if(layoutStyle == 2){
	$(".body-wrap").addClass("body-boxed");	
}
else{
	$(".body-wrap").removeClass("body-boxed");
}

//// LAYOUT COLOR
var layoutColor = $.cookie('scheme');
// Select option
$("#cmbLayoutColor").find('option[value="'+ layoutColor +'"]').attr("selected",true)

//// HEADER
var headerStyle = $.cookie('header-style');
// Select option
$("#cmbHeaderStyle").find('option[value="'+ headerStyle +'"]').attr("selected",true)

// Set option
if(headerStyle == 2){
	$("#cmbTopHeaderColor").prop("disabled", true);
	$("#divHeaderWrapper").empty();
	$("#divHeaderWrapper").load("header-standard-2", function(){
		$('.dropdown-toggle').dropdownHover();
	});
	$('.dropdown-toggle').dropdownHover();
}
else if(headerStyle == 3){
	$("#cmbTopHeaderColor").prop("disabled", false);
	$("#divHeaderWrapper").empty();
	$("#divHeaderWrapper").load("header-standard-3", function(){
		$('.dropdown-toggle').dropdownHover();
		if(topHeaderColor == "dark"){
			$(".top-header").addClass("top-header-dark");	
		}
	});
}
else if(headerStyle == 4){
	$("#cmbTopHeaderColor").prop("disabled", true);
	$("#divHeaderWrapper").empty();
	$("#divHeaderWrapper").load("header-cover-1", function(){
		$('.dropdown-toggle').dropdownHover();
	});
	$.removeCookie('header-style');
}
else if(headerStyle == 1){
	$("#cmbTopHeaderColor").prop("disabled", true);
	$("#divHeaderWrapper").empty();
	$("#divHeaderWrapper").load("header-standard-1", function(){
		$('.dropdown-toggle').dropdownHover();
	});
}

//// NAVBAR SHADOW
var navShadow = $.cookie('nav-shadow');
// Select option
$("#cmbNavShadow").find('option[value="'+ navShadow +'"]').attr("selected", true);

// Set option
if(navShadow == 2){
	$(".navbar-wp").addClass("navbar-shadow");
}

// NAVBAR - DROPDOWN ARROW
var dropdownArrow = $.cookie('dropdown-arrow');
// Select option
$("#cmbNavDropdownArrow").find('option[value="'+ dropdownArrow +'"]').attr("selected", true);

// Set option
if(dropdownArrow == 1){
	$(".navbar-wp").removeClass("navbar-arrow");
}

//// TOP HEADER - COLOR
var topHeaderColor = $.cookie('top-header-color');
// Select option
$("#cmbTopHeaderColor").find('option[value="'+ topHeaderColor +'"]').attr("selected", true)

// Set option
if(topHeaderColor == "dark"){
	$(".top-header").addClass("top-header-dark");	
}

//// SECTION TITLE - STYLE
var sectionTitleStyle = $.cookie('section-title-style');
// Select option
$("#cmbSectionTitleStyle").find('option[value="'+ sectionTitleStyle +'"]').attr("selected", true);

// Set option
if(sectionTitleStyle == 2){
	$("#cmbSectionTitleColor").prop("disabled", false);
	$(".section-title-wr").removeClass("style-3");
	$(".section-title-wr").addClass("style-2 base base-alt light dark");	
}
else if(sectionTitleStyle == 3){
	$("#cmbSectionTitleColor").prop("disabled", true);
	$(".section-title-wr").removeClass("style-2 base base-alt light dark");
	$(".section-title-wr").addClass("style-3");
}
else if(sectionTitleStyle == 1){
	$("#cmbSectionTitleColor").prop("disabled", true);
	$(".section-title-wr").removeClass("style-2 base base-alt light dark");
	$(".section-title-wr").removeClass("style-3");
}

// SECTION TITLE - COLOR
var sectionTitleColor = $.cookie('section-title-color');
// Select option
$("#cmbSectionTitleColor").find('option[value="'+ sectionTitleColor +'"]').attr("selected", true);

// Set option
if(sectionTitleColor == 2){
	$(".section-title-wr").removeClass("base light dark");
	$(".section-title-wr").addClass("base-alt");
	$.cookie('section-title-color', 2, { expires:date});	
}
else if(sectionTitleColor == 3){
	$(".section-title-wr").removeClass("base base-alt dark");
	$(".section-title-wr").addClass("light");
	$.cookie('section-title-color', 3, { expires:date});	
}
else if(sectionTitleColor == 4){
	$(".section-title-wr").removeClass("base base-alt light");
	$(".section-title-wr").addClass("dark");
	$.cookie('section-title-color', 3, { expires:date});	
}
else if(sectionTitleColor == 1){
	$(".section-title-wr").removeClass("base-alt light dark");
	$(".section-title-wr").addClass("base");
	$.cookie('section-title-color', 1, { expires:date});	
}


// BODY BACKGROUNDS
var background = $.cookie('background');
if (background == 'body-bg-1') {
	$("body").addClass("body-bg-1");
}
else if (background == 'body-bg-base') {
	$("body").addClass("body-bg-base");
}
else if (background == 'body-bg-3') {
	$("body").addClass("body-bg-3");
}
else if (background == 'body-bg-4') {
	$("body").addClass("body-bg-4");
}
else if (background == 'body-bg-5') {
	$("body").addClass("body-bg-5");
}
else if (background == 'body-bg-6') {
	$("body").addClass("body-bg-6");
}


// Cookie expire date
var date = new Date();
date.setTime(date.getTime() + (5 * 60 * 1000));


//// -- Click Events

// LAYOUT STYLE
$("#cmbLayoutStyle").change(function(){
	if($("#cmbLayoutStyle").val() == 2){
		$(".body-wrap").addClass("body-boxed");	
		$.cookie('layout', 2, { expires:date});
	}
	else{
		$(".body-wrap").removeClass("body-boxed");
		$.cookie('layout', 1, { expires:date});
	}
});

// LAYOUT COLOR
$("#cmbLayoutColor").change(function(){
	if($("#cmbLayoutColor").val() == 2){
		$("#wpStylesheet").attr("href", "css/global-style-dark.css");
		$.cookie('scheme', 'dark', { expires:date});
	}
	else{
		$("#wpStylesheet").attr("href", "css/global-style.css");
		$.cookie('scheme', 'blue', { expires:date});
	}
});


// HEADER
$("#cmbHeaderStyle").change(function(){
	if($(this).val() == 2){
		$("#cmbTopHeaderColor").prop("disabled", true);
		$("#divHeaderWrapper").empty();
		$("#divHeaderWrapper").load("header-standard-2", function(){
			$('.dropdown-toggle').dropdownHover();
		});
		$.cookie('header-style', 2, { expires:date});	
	}
	else if($(this).val() == 3){
		$("#cmbTopHeaderColor").prop("disabled", false);
		$("#divHeaderWrapper").empty();
		$("#divHeaderWrapper").load("header-standard-3", function(){
			$('.dropdown-toggle').dropdownHover();
		});
		$.cookie('header-style', 3, { expires:date});	
	}
	else if($(this).val() == 4){
		$("#cmbTopHeaderColor").prop("disabled", true);
		$("#divHeaderWrapper").empty();
		$("#divHeaderWrapper").load("header-cover-1");
		$.cookie('header-style', 4, { expires:date});
		document.location.href = "homepage-cover"	
	}
	else if($(this).val() == 1){
		$("#cmbTopHeaderColor").prop("disabled", true);
		$("#divHeaderWrapper").empty();
		$("#divHeaderWrapper").load("header-standard-1", function(){
			$('.dropdown-toggle').dropdownHover();
		});
		$.cookie('header-style', 1, { expires:date});	
	}
});

// NAVBAR SHADOW
$("#cmbNavShadow").change(function(){
	if($("#cmbNavShadow").val() == 2){
		$(".navbar-wp").addClass("navbar-shadow");
		$.cookie('nav-shadow', 2, {expires:date});
	}
	else{
		$(".navbar-wp").removeClass("navbar-shadow");
		$.cookie('nav-shadow', 1, {expires:date});
	}
});

// NAVBAR - DROPDOWN ARROW
$("#cmbNavDropdownArrow").change(function(){
	if($("#cmbNavDropdownArrow").val() == 2){
		$(".navbar-wp").addClass("navbar-arrow");
		$.cookie('dropdown-arrow', 2, {expires:date});
	}
	else{
		$(".navbar-wp").removeClass("navbar-arrow");
		$.cookie('dropdown-arrow', 1, {expires:date});
	}
});

// TOP HEADER - COLOR
$("#cmbTopHeaderColor").change(function(){
	if($(this).val() == 2){
		$(".top-header").addClass("top-header-dark");
		$.cookie('top-header-color', 'dark', {expires:date});	
	}
	else{
		$(".top-header").removeClass("top-header-dark");
		$.cookie('top-header-color', 'light', {expires:date});	
	}
});

// SECTION TITLE - STYLE
$("#cmbSectionTitleStyle").change(function(){
	if($(this).val() == 2){
		$("#cmbSectionTitleColor").prop("disabled", false);
		$(".section-title-wr").removeClass("style-3");
		$(".section-title-wr").addClass("style-2 base base-alt light dark");
		$.cookie('section-title-style', 2, { expires:date});	
	}
	else if($(this).val() == 3){
		$("#cmbSectionTitleColor").prop("disabled", true);
		$(".section-title-wr").removeClass("style-2 base base-alt light dark");
		$(".section-title-wr").addClass("style-3");
		$.cookie('section-title-style', 3, { expires:date});	
	}
	else if($(this).val() == 1){
		$("#cmbSectionTitleColor").prop("disabled", true);
		$(".section-title-wr").removeClass("style-2 base base-alt light dark");
		$(".section-title-wr").removeClass("style-3");
		$.cookie('section-title-style', 1, { expires:date});	
	}
});

// SECTION TITLE - COLOR
$("#cmbSectionTitleColor").change(function(){
	if($(this).val() == 2){
		$(".section-title-wr").removeClass("base light dark");
		$(".section-title-wr").addClass("base-alt");
		$.cookie('section-title-color', 2, { expires:date});	
	}
	else if($(this).val() == 3){
		$(".section-title-wr").removeClass("base base-alt dark");
		$(".section-title-wr").addClass("light");
		$.cookie('section-title-color', 3, { expires:date});	
	}
	else if($(this).val() == 4){
		$(".section-title-wr").removeClass("base base-alt light");
		$(".section-title-wr").addClass("dark");
		$.cookie('section-title-color', 3, { expires:date});	
	}
	else if($(this).val() == 1){
		$(".section-title-wr").removeClass("base-alt light dark");
		$(".section-title-wr").addClass("base");
		$.cookie('section-title-color', 1, { expires:date});	
	}
});

// BODY BACKGROUNDS
$("#cmdBodyBg1").click(function(){
	// Select option
	$("#cmdBodyBg1, #cmdBodyBg2, #cmdBodyBg3, #cmdBodyBg4, #cmdBodyBg5, #cmdBodyBg6, #cmdBodyBg7").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-1");
	$.cookie('body-bg', 'body-bg-1', { expires:date});
	return false;
});
$("#cmdBodyBg2").click(function(){
	// Select option
	$("#cmdBodyBg1, #cmdBodyBg2, #cmdBodyBg3, #cmdBodyBg4, #cmdBodyBg5, #cmdBodyBg6, #cmdBodyBg7").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-2");
	$.cookie('body-bg', 'body-bg-2', { expires:date});
	return false;
});
$("#cmdBodyBg3").click(function(){
	// Select option
	$("#cmdBodyBg1, #cmdBodyBg2, #cmdBodyBg3, #cmdBodyBg4, #cmdBodyBg5, #cmdBodyBg6, #cmdBodyBg7").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-3");
	$.cookie('body-bg', 'body-bg-1', { expires:date});
	return false;
});
$("#cmdBodyBg4").click(function(){
	// Select option
	$("#cmdBodyBg1, #cmdBodyBg2, #cmdBodyBg3, #cmdBodyBg4, #cmdBodyBg5, #cmdBodyBg6, #cmdBodyBg7").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-4");
	$.cookie('body-bg', 'body-bg-4', { expires:date});
	return false;
});
$("#cmdBodyBg5").click(function(){
	// Select option
	$("#cmdBodyBg1, #cmdBodyBg2, #cmdBodyBg3, #cmdBodyBg4, #cmdBodyBg5, #cmdBodyBg6, #cmdBodyBg7").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-5");
	$.cookie('body-bg', 'body-bg-5', { expires:date});
	return false;
});
$("#cmdBodyBg6").click(function(){
	// Select option
	$("#cmdBodyBg1, #cmdBodyBg2, #cmdBodyBg3, #cmdBodyBg4, #cmdBodyBg5, #cmdBodyBg6, #cmdBodyBg7").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-6");
	$.cookie('body-bg', 'body-bg-6', { expires:date});
	return false;
});
$("#cmdBodyBg7").click(function(){
	// Select option
	$("#cmdBodyBg1, #cmdBodyBg2, #cmdBodyBg3, #cmdBodyBg4, #cmdBodyBg5, #cmdBodyBg6, #cmdBodyBg7").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("body").removeClass("body-bg-1 body-bg-2 body-bg-3 body-bg-4 body-bg-5 body-bg-6 body-bg-7");
	$("body").addClass("body-bg-7");
	$.cookie('body-bg', 'body-bg-7', { expires:date});
	return false;
});

// SCHEMES
$("#cmdSchemeRed").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-red.css");
	$.cookie('scheme', 'red', { expires:date});
	return false;
});
$("#cmdSchemeViolet").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-violet.css");
	$.cookie('scheme', 'violet', { expires:date});
	return false;
});
$("#cmdSchemeBlue").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-blue.css");
	$.cookie('scheme', 'blue', { expires:date});
	return false;
});
$("#cmdSchemeGreen").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-green.css");
	$.cookie('scheme', 'green', { expires:date});
	return false;
});
$("#cmdSchemeYellow").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-yellow.css");
	$.cookie('scheme', 'yellow', { expires:date});
	return false;
});
$("#cmdSchemeOrange").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-orange.css");
	$.cookie('scheme', 'orange', { expires:date});
	return false;
});

// SPECIAL SCHEMES
$("#cmdSchemeBW").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-bw.css");
	$.cookie('scheme', 'bw', { expires:date});
	return false;
});
$("#cmdSchemeDark, #cmbLayoutColor").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-dark.css");
	$.cookie('scheme', 'dark', { expires:date});
	return false;
});
$("#cmdSchemeFlat").click(function(){
	// Select option
	$("#cmdSchemeRed, #cmdSchemeViolet, #cmdSchemeBlue, #cmdSchemeGreen, #cmdSchemeYellow, #cmdSchemeOrange, #cmdSchemeBW, #cmdSchemeDark, #cmdSchemeFlat").removeClass("active");
	$(this).addClass("active");

	// Set option
	$("#wpStylesheet").attr("href", "css/global-style-flat.css");
	$.cookie('scheme', 'flat', { expires:date});
	return false;
});