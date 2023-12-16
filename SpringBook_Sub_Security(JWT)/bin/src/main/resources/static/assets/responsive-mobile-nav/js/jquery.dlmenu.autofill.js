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
});