<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | BuyRemo&#8482 </title>
   	<link href="../../../css/bootstrap.min.css" rel="stylesheet">
	<link href="../../../css/font-awesome.min.css" rel="stylesheet">
	<link href="../../../css/prettyPhoto.css" rel="stylesheet">
	<link href="../../../css/price-range.css" rel="stylesheet">
	<link href="../../../css/animate.css" rel="stylesheet">
	<link href="../../../css/main.css" rel="stylesheet">
	<link href="../../../css/responsive.css" rel="stylesheet">
	<link href="../../../css/sweet-alert.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="../../../images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../../../images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../../../images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../../../images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../../../images/ico/apple-touch-icon-57-precomposed.png">
    <style>
    .inputButton{
    	background: #FE980F;
    	border: medium none;
    	border-radius: 0;
   	 	color: #FFFFFF;
    	display: block;
    	font-family: 'Roboto', sans-serif;
    	padding: 6px 25px;
    }
    </style>
</head><!--/head-->
<body>
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="#"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i> info@domain.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<c:if test='${empty sessionScope.userSession}'>
				<div class="header-middle"><!--header-middle-->
					<div class="container">
						<div class="row">
							<div class="col-sm-4">
								<div class="logo pull-left">
									<a href="/buyremo/index"><img src="../../../images/home/logo.png" alt="" /></a>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="shop-menu pull-right">
									<ul class="nav navbar-nav">
										<li><a href="/buyremo/login"><i class="fa fa-user"></i> Account</a></li>
										<li><a href="/buyremo/login"><i class="fa fa-star"></i> Aspirations</a></li>
										<!-- <li><a href="/login"><i class="fa fa-crosshairs"></i> Checkout</a></li>
										<li><a href="/login"><i class="fa fa-shopping-cart"></i> Cart</a></li> -->
										<li><a href="/buyremo/login"><i class="fa fa-lock"></i> Login</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div><!--/header-middle-->
			</c:if>
			<c:if test='${not empty sessionScope.userSession}'>
				<div class="header-middle"><!--header-middle-->
					<div class="container">
						<div class="row">
							<div class="col-sm-4">
								<div class="logo pull-left">
									<a href="/buyremo/userHome" class="active"><img src="../../../images/home/logo.png" alt="" /></a>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="shop-menu pull-right">
									<c:if test='${userType eq "PARENT"}'>
										<ul class="nav navbar-nav">
											<li><a href="/buyremo/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
											<li><a href="/buyremo/getAspirations" class="active"><i class="fa fa-star"></i>Aspirations</a></li>
											<!-- <li><a href="/cart"><i class="fa fa-shopping-cart"></i>My Cart</a></li>
											<li><a href="/checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li> -->
											<li><a href="/buyremo/logout"><i class="fa fa-lock"></i>Logout</a></li>
										</ul>
									</c:if>
									<c:if test='${userType eq "DEPENDANT"}'>
										<ul class="nav navbar-nav">
											<li><a href="/buyremo/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
											<li><a href="/buyremo/getMyAspirations" class="active"><i class="fa fa-star"></i>Aspirations</a></li>
											<!-- <li><a href="/cart"><i class="fa fa-shopping-cart"></i>My Cart</a></li> -->
											<!-- <li><a href="/userCheckout"><i class="fa fa-crosshairs"></i> Checkout</a></li> -->
											<li><a href="/buyremo/logout"><i class="fa fa-lock"></i>Logout</a></li>
										</ul>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div><!--/header-middle-->
			</c:if>
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-8">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<c:if test='${userType eq "PARENT"}'>
									<li><a href="/buyremo/userHome" class="active">Home</a></li>
									<!-- <li class="dropdown"><a href="#">Shop Categories<i class="fa fa-angle-down"></i></a>
	                                   <ul role="menu" class="sub-menu">
	                                        <li><a href="#" id="fashion" name="fashion">Fashion</a></li>
											<li><a href="#" id="electronics" name="electronics">Electronics</a></li> 
											<li><a href="#" id="mobiles" name="mobiles">Mobiles</a></li> 
											<li><a href="#" id="games" name="games">Games</a></li> 
											<li><a href="#" id="toys" name="toys">Toys</a></li> 
											<li><a href="#" id="tickets" name="tickets">Tickets</a></li> 
											<li><a href="#" id="books" name="books">Books</a></li> 
	                                    </ul>
	                                </li> -->
									<li><a href="/buyremo/inviteDependants">Invite Dependants</a></li>
                                </c:if>
                                <c:if test='${userType eq "DEPENDANT"}'>
                                	<li><a href="/buyremo/userHome" class="active">Home</a></li>
									<li class="dropdown"><a href="#">Shop Categories<i class="fa fa-angle-down"></i></a>
	                                   <ul role="menu" class="sub-menu">
	                                        <li><a href="#" id="fashion" name="fashion">Fashion</a></li>
											<li><a href="#" id="electronics" name="electronics">Electronics</a></li> 
											<li><a href="#" id="mobiles" name="mobiles">Mobiles</a></li> 
											<li><a href="#" id="games" name="games">Games</a></li> 
											<li><a href="#" id="toys" name="toys">Toys</a></li> 
											<li><a href="#" id="tickets" name="tickets">Tickets</a></li> 
											<li><a href="#" id="books" name="books">Books</a></li> 
	                                    </ul>
	                                </li>
                                </c:if>                               
							<c:if test='${empty sessionScope.userSession}'> 
                                	<li><a href="/buyremo/index" class="active">Home</a></li>
									<li class="dropdown"><a href="#">Shop Categories<i class="fa fa-angle-down"></i></a>
	                                   <ul role="menu" class="sub-menu">
	                                        <li><a href="#" id="fashion" name="fashion">Fashion</a></li>
											<li><a href="#" id="electronics" name="electronics">Electronics</a></li> 
											<li><a href="#" id="mobiles" name="mobiles">Mobiles</a></li> 
											<li><a href="#" id="games" name="games">Games</a></li> 
											<li><a href="#" id="toys" name="toys">Toys</a></li> 
											<li><a href="#" id="tickets" name="tickets">Tickets</a></li> 
											<li><a href="#" id="books" name="books">Books</a></li> 
	                                    </ul>
	                                </li>
	                                <li><a href="/buyremo/inviteDependants">Invite Dependants</a></li>
                                </c:if>                            
							</ul>
						</div>
					</div>
					<c:if test='${userType eq "DEPENDANT"}'>
						<div class="col-sm-4">
							<div class="search_box pull-right">
								<input type="text" name="searchItem" id="searchItem" placeholder="Search" required="required"/>
								<button type="submit" class="btn btn-warning" name="searchButton" id="searchButton">Go</button>
							</div>						
						</div>
					</c:if>
					<c:if test='${empty sessionScope.userSession}'>
						<div class="col-sm-4">
							<div class="search_box pull-right">
								<input type="text" name="searchItem" id="searchItem" placeholder="Search" required="required"/>
								<button type="submit" class="btn btn-warning" name="searchButton" id="searchButton">Go</button>
							</div>						
						</div>
					</c:if>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form action="/buyremo/loginSubmit" method="POST">
							<input type="email" name="loginId" id="loginId" value="" placeholder="Type Email Address" required="required"/>
							<input type="password" name="loginPassword" id="loginPassword" placeholder="Password" onKeypress="capLock(event)" required="required"/>
							<div id="notifications"></div>
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<span  class="pull-right"><a href="/forgotPassword">Forgot Password</a></span>
							<span><div id="CAPS" style="visibility:hidden;color:green">Caps Lock is on.</div> </span>	
							<button class="btn btn-default" type="submit">Login</button>
						</form>
						
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>Dependant Signup!</h2>
						<form method="POST" action="/buyremo/dependantSignUp">
							<input type="text" name="userName" id="userName" value="${user.userName}" placeholder="Name" required="required"/>
							<sform:errors path="userName" cssclass="error"></sform:errors>
							<input type="hidden" name="id" id="id" value="${user.id}"  placeholder="" />
							<input type="email" name="emailId" id="emailId" value="${user.emailId}"  placeholder="Email Address" required="required"/>
							<input type="password" name="password" id="password" placeholder="Password" onKeypress="capLock(event)" required="required"/>
							<input type="text" name="mobileNumber" id="mobileNumber" placeholder="Mobile Number" required="required"/>
							<input type="text" name="hno" id="hno" placeholder="House Number" required="required"/>
							<input type="text" name="addressLine1" id="addressLine1" placeholder="Address Line 1" required="required"/>
							<input type="text" name="addressLine2" id="addressLine2" placeholder="Address Line 2"/>
							<input type="text" name="zip" id="zip" placeholder="Zip Code" required="required"/>
							<span><div id="CAPS" style="visibility:hidden;color:green">Caps Lock is on.</div> </span>
							<h5><font color="red">${signUpMessage}</font></h5>
							<h5><font color="red">${error}</font></h5>		
							<button type="submit" class="btn btn-default">SignUp</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
	<footer id="footer"><!--Footer-->
	<div class="footer-top">
		<div class="container">
			<div class="row">
				<div class="col-sm-2">
					<div class="companyinfo">
						<h2>BuyRemo&#8482 </h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
					</div>
				</div>
				<div class="col-sm-7">
					<div class="col-sm-3">
						<div class="video-gallery text-center">
							<a href="#">
								<div class="iframe-img">
									<img src="../../../images/home/iframe1.png" alt="" />
								</div>
								<div class="overlay-icon">
									<i class="fa fa-play-circle-o"></i>
								</div>
							</a>
							<p>Circle of Hands</p>
							<h2>24 DEC 2014</h2>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="address">
						<img src="../../../images/home/map.png" alt="" />
						<p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="footer-widget">
		<div class="container">
			<div class="row">
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>Service</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="#">Online Help</a></li>
							<li><a href="#">Contact Us</a></li>
							<li><a href="#">Order Status</a></li>
							<li><a href="#">Change Location</a></li>
							<li><a href="#">FAQs</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>Quick Shop</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="#">T-Shirt</a></li>
							<li><a href="#">Mens</a></li>
							<li><a href="#">Womens</a></li>
							<li><a href="#">Gift Cards</a></li>
							<li><a href="#">Shoes</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>Policies</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="#">Terms of Use</a></li>
							<li><a href="#">Privecy Policy</a></li>
							<li><a href="#">Refund Policy</a></li>
							<li><a href="#">Billing System</a></li>
							<li><a href="#">Ticket System</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>About BuyRemo</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="#">Company Information</a></li>
							<li><a href="#">Careers</a></li>
							<li><a href="#">Store Location</a></li>
							<li><a href="#">Affillate Program</a></li>
							<li><a href="#">Copyright</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-3 col-sm-offset-1">
					<div class="single-widget">
						<h2>About BuyRemo</h2>
						<form action="#" class="searchform">
							<input type="text" placeholder="Your email address" />
							<button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
							<p>Get the most recent updates from <br />our site and be updated your self...</p>
						</form>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="footer-bottom">
		<div class="container">
			<div class="row">
				<p class="pull-left">Copyright © 2016 BuyRemo&#8482  Inc. All rights reserved.</p>
				<!-- <p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p> -->
			</div>
		</div>
	</div>
	
</footer><!--/Footer-->
<script src="../../../js/jquery.js"></script>
<script src="../../../js/bootstrap.min.js"></script>
<script src="../../../js/jquery.scrollUp.min.js"></script>
<script src="../../../js/price-range.js"></script>
<script src="../../../js/jquery.prettyPhoto.js"></script>
<script src="../../../js/main.js"></script>
<script src="../../../js/enscroll-0.6.0.min.js"></script>
<script src="../../../js/notify.min.js"></script>
<script src="../../../js/sweet-alert.min.js"></script>
<script>
	$('#scrollbox').enscroll({
	   showOnHover: false,
	   verticalTrackClass: 'track3',
	   verticalHandleClass: 'handle3'
	});
</script>
<script type="text/javascript">
	$('#searchButton').click(function(){
		var searchKey = $("#searchItem").val();
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	 $('#fashion').click(function(){
		var searchKey = 'fashion';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	 $('#electronics').click(function(){
		var searchKey = 'electronics';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	 $('#games').click(function(){
		var searchKey = 'games';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	 $('#toys').click(function(){
		var searchKey = 'toys';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	 $('#tickets').click(function(){
		var searchKey = 'tickets';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	 $('#books').click(function(){
		var searchKey = 'books';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	 $('#mobiles').click(function(){
		var searchKey = 'mobiles';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/searchResults';
		}
	 });
	 
	 $('#kids').click(function(){
		var searchKey = 'kids';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	  $('#households').click(function(){
		var searchKey = 'households';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	  $('#interiors').click(function(){
		var searchKey = 'interiors';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/searchResults';
		}
	 });
	 
	  $('#clothing').click(function(){
		var searchKey = 'clothing';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	  $('#bags').click(function(){
		var searchKey = 'bags';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
	 
	  $('#shoes').click(function(){
		var searchKey = 'shoes';
		localStorage.setItem("searchString", searchKey);
		if(searchKey == null){			
				window.location.href = '/buyremo/index';			
		}else{
			window.location.href = '/buyremo/searchResults';
		}
	 });
</script>
<script>
	function capLock(e){
		 kc = e.keyCode?e.keyCode:e.which;
		 sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false);
		 if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk))
		  document.getElementById('CAPS').style.visibility = 'visible';
		 else
		  document.getElementById('CAPS').style.visibility = 'hidden';
		}
</script>