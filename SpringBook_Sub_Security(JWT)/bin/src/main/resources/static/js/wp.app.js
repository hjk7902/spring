$(document).ready(function() {

	// WP Mobile Nav (Second method besides Bootstrap standrd menu) - Populating mobile nav list
	var navbarContainer = $('.navbar-nav');
	var linkTextLevel1;
	var linkTextLevel2;
	var linkTextLevel3;
	var navList;
	var navListEnd = '</ul>';

	// Get first level items
	$('.navbar-nav > li.dropdown').each(function(index) {
		// Meganav list items
		if($(this).hasClass('dropdown-meganav')){
			linkTextLevel1 = $(this).find('a:first').text();
			linkUrlLevel1 = $(this).find('a:first').attr('href');
			navList = '<li><a href="'+ linkUrlLevel1 +'">'+ linkTextLevel1 +'</a>';

			// Get second level items
			if($(this).find('.mega-nav-section').length > 0){
				navList = navList + '<ul class="dl-submenu">';

				$(this).find('.mega-nav-section').each(function(index){
					linkTextLevel2 = $(this).find('.mega-nav-section-title').text();
					linkUrlLevel2 = $(this).find('a:first').attr('href');
					navList = navList + '<li><a href="'+ linkUrlLevel2 +'">'+ linkTextLevel2 +'</a>';

					// Get third level items
					if($(this).find('.mega-nav-ul').length > 0){
						navList = navList + '<ul class="dl-submenu">';
						
						$(this).find('.mega-nav-ul > li').each(function(index){
							linkTextLevel3 = $(this).find('a').text();
							linkUrlLevel3 = $(this).find('a:first').attr('href');
							navList = navList + '<li><a href="'+ linkUrlLevel3 +'">'+ linkTextLevel3 +'</a></li>';
						});

						navList = navList + navListEnd;
						navList = navList + '</li>';
					} 
				});
			}

		}

		// Normal nav list items
		if(!$(this).hasClass('dropdown-search') && !$(this).hasClass('dropdown-meganav')){
			linkTextLevel1 = $(this).find('a:first').text();
			linkUrlLevel1 = $(this).find('a:first').attr('href');
			navList = '<li><a href="'+ linkUrlLevel1 +'">'+ linkTextLevel1 +'</a>';

			// Get second level items
			if($(this).find('ul > li').length > 0){
				navList = navList + '<ul class="dl-submenu">';

				$(this).children().children().each(function(index){
					linkTextLevel2 = $(this).find('a:first').text();
					linkUrlLevel2 = $(this).find('a:first').attr('href');
					navList = navList + '<li><a href="'+ linkUrlLevel2 +'">'+ linkTextLevel2 +'</a>';

					// Get third level items
					if($(this).find('ul > li').length > 0){
						navList = navList + '<ul class="dl-submenu">';
						
						$(this).children().children().each(function(index){
							linkTextLevel3 = $(this).find('a').text();
							linkUrlLevel3 = $(this).find('a:first').attr('href');
							navList = navList + '<li><a href="'+ linkUrlLevel3 +'">'+ linkTextLevel3 +'</a></li>';
						});

						navList = navList + navListEnd;
						navList = navList + '</li>';
					} 
				});
			} 		
		}

		navList = navList + navListEnd;
		navList = navList + '</li>';

		// Append and create list menu
		$('#dl-menu > ul.dl-menu').append(navList + '</ul>');		
	});
	// Initializing mobile menu
	$('#dl-menu').dlmenu({
        animationClasses:{
        	classin: 'dl-animate-in-2', 
        	classout: 'dl-animate-out-2' 
        }
    });
    // Bootstrap - Dropdown Hover
   	function allowDropdownHover() {
   		if($(window).width() > 767){
	    	$('.dropdown-toggle').dropdownHover();
	    }	
    }
    allowDropdownHover();
    window.onresize = allowDropdownHover; // Call the function on resize

	//Carousels
	$('.carousel').carousel({
		interval: 5000,
		pause	: 'hover'
	});
	// Owl carousel
	if($(".owl-carousel-single").length > 0){
		$(".owl-carousel-single").owlCarousel({
			//items : 4,
			lazyLoad : true,
			pagination : false,
			autoPlay: 10000,
			singleItem: true,
			stopOnHover: true
		}); 
	}
	if($(".owl-carousel-2").length > 0){
		// Owl Carousel
		$(".owl-carousel-2").owlCarousel({
			items : 2,
			lazyLoad : true,
			pagination : false,
			autoPlay: 10000,
			stopOnHover: true
		}); 
	}
	if($(".owl-carousel-3").length > 0){
		// Owl Carousel
		$(".owl-carousel-3").owlCarousel({
			items : 3,
			lazyLoad : true,
			pagination : false,
			autoPlay: 10000,
			stopOnHover: true
		}); 
	}
	if($(".owl-carousel-4").length > 0){
		// Owl Carousel
		$(".owl-carousel-4").owlCarousel({
			items : 4,
			lazyLoad : true,
			pagination: false,			
			autoPlay: 10000,
			stopOnHover: true
		}); 
	}
	if($(".owl-carousel-5").length > 0){
		// Owl Carousel
		$(".owl-carousel-5").owlCarousel({
			items : 5,
			lazyLoad : true,
			pagination : false,
			autoPlay: 10000,
			stopOnHover: true
		}); 
	}
	// Sortable list
	$('#ulSorList').mixitup();
	// Fancybox
	$(".theater").fancybox();
	// Fancybox	
	$(".ext-source").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'autoScale'     	: false,
		'type'				: 'iframe',
		'width'				: '50%',
		'height'			: '60%',
		'scrolling'   		: 'no'
	});
	// Stellar JS
	$(".prlx-bg").stellar();
	// Isotope Masonry
	if($('.wp-masonry-wrapper').length > 0){
		$('.wp-masonry-wrapper').isotope({
		    itemSelector: '.wp-masonry-block',
		    masonry: {
		      columnWidth: '.wp-masonry-block',
		      gutter: '.wp-masonry-gutter'
		    }
		  });
	}
	// Milestone counter
	$('.milestone-count').countTo({
        //from: 50,
        //to: 250,
        //speed: 1000,
        //refreshInterval: 50,
        formatter: function (value, options) {
            return value.toFixed(options.decimals);
        },
        onUpdate: function (value) {
            console.debug(this);
        },
        onComplete: function (value) {
            console.debug(this);
        }
    });
    // Range sliders
    if($('.range-slider-wrapper').length > 0){
    	var customToolTip = $.Link({
			target: '-tooltip-<div class="tooltip"></div>',
			method: function(value){
				$(this).html(
					'<span>' + parseFloat(value) + '</span>'
				);
			}
		});
		
    	// Range slider 1 - With fixed intervals
        $(".range-slider-1").noUiSlider({
			start: [300, 5000],
			snap: true,
			range: {
				'min': 0,
				'10%': 100,
				'20%': 500,
				'30%': 1000,
				'40%': 1500,
				'60%': 3000,
				'70%': 3500,
				'80%': 4000,
				'90%': 4500,
				'max': 5000
			}
		});
        // Range slider with connect option
        $(".range-slider-connect-1").noUiSlider({
			start: [300, 5000],
			connect: true,
			snap: true,
			range: {
				'min': 0,
				'10%': 100,
				'20%': 500,
				'30%': 1000,
				'40%': 1500,
				'60%': 3000,
				'70%': 3500,
				'80%': 4000,
				'90%': 4500,
				'max': 5000
			}
		});
		// Range slider with connect option + tooltip
        $(".range-slider-connect-2").noUiSlider({
			start: [500, 4000],
			connect: true,
			snap: true,
			range: {
				'min': 0,
				'10%': 100,
				'20%': 500,
				'30%': 1000,
				'40%': 1500,
				'60%': 3000,
				'70%': 3500,
				'80%': 4000,
				'90%': 4500,
				'max': 5000
			},
			// Add tooltip
			serialization: {
				lower: [ customToolTip ],
				upper: [ customToolTip ]
			}
		});

        $(".range-slider-2").noUiSlider({
			start: [300, 5000],
			snap: true,
			range: {
				'min': 0,
				'10%': 100,
				'20%': 500,
				'30%': 1000,
				'40%': 1500,
				'60%': 3000,
				'70%': 3500,
				'80%': 4000,
				'90%': 4500,
				'max': 5000
			},
			// Add tooltip
			serialization: {
				lower: [ customToolTip ],
				upper: [ customToolTip ]
			}
		});

		// Simple slider - With fixed intervals
		$('.simple-slider-1').noUiSlider({
			start: [ 500 ],
			range: {
				'min': [     0 ],
				'10%': [   500,  500 ],
				'50%': [  4000, 1000 ],
				'max': [ 10000 ]
			}
		});

		$('.simple-slider-2').noUiSlider({
			start: [ 500 ],
			range: {
				'min': [     0 ],
				'10%': [   500,  500 ],
				'50%': [  4000, 1000 ],
				'max': [ 10000 ]
			},
			// Add tooltip
			serialization: {
				lower: [ customToolTip ]
			}
		});

		// Simple slider - Connect lower
		$('.simple-slider-connect-1').noUiSlider({
			start: [ 2500 ],
			connect: "lower",
			range: {
				'min': [     0 ],
				'10%': [   500,  500 ],
				'50%': [  4000, 1000 ],
				'max': [ 10000 ]
			}
		});
		// Simple slider - Connect lower + tooltip
		$('.simple-slider-connect-2').noUiSlider({
			start: [ 2500 ],
			connect: "lower",
			range: {
				'min': [     0 ],
				'10%': [   500,  500 ],
				'50%': [  4000, 1000 ],
				'max': [ 10000 ]
			},
			// Add tooltip
			serialization: {
				lower: [ customToolTip ]
			}
		});
    }
	// Scroll to top
	$().UItoTop({ easingType: 'easeOutQuart' });
	// Inview animations
	$.fn.waypoint.defaults = {
		context: window,
		continuous: true,
		enabled: true,
		horizontal: false,
		offset: 300,
		triggerOnce: false
	}	
	$('.animate-in-view, .pie-chart').waypoint(function(direction) {
		// Easy Pie Chart
		$(".pie-chart").easyPieChart({
			size:150,
			easing: 'easeOutBounce',
			onStep: function(from, to, percent) {
				$(this.el).find('.percent').text(Math.round(percent));
			},
			barColor: '#FFF',
			delay: 3000,
			trackColor:'rgba(255,255,255,0.2)',
			scaleColor:false,
			lineWidth:16,
			lineCap:'butt'
		});
	});
	// Search
	if($('#btnSearch').length > 0){
		// Search function
		$("#btnSearch").click(function(){
			if($("#divSearch").is(":visible")){
				$("#divSearch").removeClass('animated slideInDown');
				$("#divSearch").addClass('animated slideOutUp');
				$('#divSearch').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
					$(this).hide();
					$("#divSearch").removeClass('animated slideOutUp');
				});
			} else {
				$("#divSearch").show().addClass('animated slideInDown');
			}
			return false;	
		});
		$("#cmdCloseSearch").click(function(){
			if($("#divSearch").is(":visible")){
				$("#divSearch").removeClass('animated slideInDown');
				$("#divSearch").addClass('animated slideOutUp');
				$('#divSearch').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
					$(this).hide();
					$("#divSearch").removeClass('animated slideOutUp');
				});
			}
		});
		
		// Keyboard shortcuts
		$('html').keyup(function(e){
			if(e.keyCode == 27){
				if($("#divSearch").is(":visible")){
					$("#divSearch").removeClass('animated slideInDown');
					$("#divSearch").addClass('animated slideOutUp');
					$('#divSearch').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
						$(this).hide();
						$("#divSearch").removeClass('animated slideOutUp');
					});
				}
			}
		});
	}
	// Hover direction animation	
	$(".animate-hover-slide-4 .figure").each( function(){ 
		$(this).hoverdir({
			hoverDelay: 50,
			inverse: 	false
		}); 
	});
	// Custom animations
	$(".animate-click").click(function(){
		var animateIn = $(this).data("animate-in");
		var animateOut = $(this).data("animate-out");
		var animatedElement = $(this).find(".animate-wr");

		console.log(animateIn+'-'+animateOut);

		if(animateIn != undefined){
			console.log('incepem animatia');
			if(animatedElement.is(":hidden")){
				console.log('start IN');
				animatedElement.addClass(animateIn);
				animatedElement.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
					animatedElement.removeClass(animateIn);
				});
			}
		}
	});
	
	$(".animate-hover").hover(function(){
		var animation = $(this).data("animate-in");
		if(animation != undefined || animation != ""){
			$(this).find(".animate-wr").addClass(animation);
		}
	});

	$(".animate-click").click(function(){
		var animation = $(this).data("animate-in");
		if(animation != undefined || animation != ""){
			$(this).find(".animate-wr").addClass(animation);
		}
	});

	// Main nav for mobiles - left
	$(".navbar-toggle-mobile-nav, #btnHideMobileNav").click(function(){
		if($("#navMobile").is(":visible")){
			$("#navMobile").removeClass('animated bounceInLeft');
			$("#navMobile").addClass('animated bounceOutLeft');
			$('#navMobile').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
				$(this).hide();
				$("#navMobile").removeClass('animated bounceOutLeft');
			});
			$("body").removeClass("nav-menu-in");
		}
		else{
			$("#navMobile").show().addClass('animated bounceInLeft');
			$("body").addClass("nav-menu-in");
		}
		return false;	
	});	
 
	// Slide bar - right
	$("#cmdAsideMenu, #btnHideAsideMenu, .navbar-toggle-aside-menu").click(function(){
		if($("#asideMenu").is(":visible")){
			$("#asideMenu").removeClass('animated bounceInRight');
			$("#asideMenu").addClass('animated bounceOutRight');
			$('#asideMenu').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
				$(this).hide();
				$("#asideMenu").removeClass('animated bounceOutRight');			
			});
		}
		else{
			$("#asideMenu").show().addClass('animated bounceInRight');
		}
		return false;	
	});	

	// Close opened element when body is clicked
	// $(document).mouseup(function(e){
 //    	var container = $(".aside-menu");

 //    	if(!container.is(e.target) && container.has(e.target).length === 0){
 //        	if(container.is(":visible")){
	// 			container.hide();
	// 			$("body").removeClass("nav-menu-in");
	// 		}
 //    	}
	// });

	// Calendar
	$("#filter_date_in, #filter_date_out").datepicker({
        dateFormat: 'MM dd, yy',
        minDate: 0,
        showOtherMonths: true
    });

    // Step process
    $("ol.progtrckr").each(function(){
        $(this).attr("data-progtrckr-steps", $(this).children("li").length);
    });

    // Optional filters
    $("#btnToggleOptionalFIlters").click(function(){
    	var animateIn = $(this).data("animate-in");
	    var animateOut = $(this).data("animate-out");

    	if($(this).hasClass("opened")){
    		$(".hidden-form-filters").addClass('hide');
    		$(this).removeClass("opened");
    	} else{
	    	$(this).addClass("opened");
	    	$(".hidden-form-filters").removeClass("hide");
	    }
	    return false;
    });

    // Layer Slider Dynamic- Set height to fit navbar
    if($(".layer-slider-dynamic").length > 0){
    	layerSliderDynamic();
    }

    // Layer Slider Fullsize
    if($(".layer-slider-fullsize").length > 0){
    	layerSliderFullsize();
    }

    // Window resize events
    $(window).resize(function() {
    	if($(".layer-slider-dynamic").length > 0){
	    	layerSliderDynamic();
	    }
		if($(".layer-slider-fullsize").length > 0){
	    	layerSliderFullsize();
	    }
	});
    
    // Functions
    function layerSliderDynamic(){
    	var windowHeight = $(window).height();
    	var headerHight = $("#divHeaderWrapper").height();
    	var newSliderHeight = windowHeight - headerHight;
    	$("#layerslider").css({"height": newSliderHeight + "px"});
    }
    function layerSliderFullsize(){
    	var windowHeight = $(window).height();
    	$("#layerslider").css({"height": windowHeight + "px"});
    }


    var screenRes = $(window).width(),
        screenHeight = $(window).height(),
        html = $('html');

    // IE<8 Warning
    if (html.hasClass("ie8")) {
        $("body").empty().html('Please, Update your Browser to at least IE9');
    }

    // Disable Empty Links
    $("[href=#]").click(function(event){
        event.preventDefault();
    });

    // Body Wrap
    $(".body-wrap").css("min-height", screenHeight);
    $(window).resize(function() {
        screenHeight = $(window).height();
        $(".body-wrap").css("min-height", screenHeight);
    });

    // styled Select, Radio, Checkbox
    if ($("select").hasClass("select_styled")) {
        cuSel({changedEl: ".select_styled", visRows: 8, scrollArrows: true});
    }

    // Rating Stars
    var star = $(".rating span.star");

    star.hover(
        function() {
            $(this).addClass("over");
            $(this).prevAll().addClass("over");
        }
        , function() {
            $(this).removeClass("over");
            $(this).prevAll().removeClass("over");
        }
    );
    star.click( function() {
        $(this).parent().children(".star").removeClass("voted");
        $(this).prevAll().addClass("voted");
        $(this).addClass("voted");
    });

    // Tooltip & Popover
    $('.ttip').tooltip({
    	placement: $(this).data('toggle'),
    	html: true
    });

    $('.pop').popover({
    	placement: 'right',
    	html: true
    });

    // Hover animations
    $('.hov').hoverup({
		// You could set options in here too.
	});
});