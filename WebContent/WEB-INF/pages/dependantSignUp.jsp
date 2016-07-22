<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="user.definition">
	<tiles:putAttribute name="body">
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
									<a href="/index"><img src="images/home/logo.png" alt="" /></a>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="shop-menu pull-right">
									<ul class="nav navbar-nav">
										<li><a href="/login"><i class="fa fa-user"></i> Account</a></li>
										<li><a href="/login"><i class="fa fa-star"></i> Aspirations</a></li>
										<li><a href="/login"><i class="fa fa-crosshairs"></i> Checkout</a></li>
										<li><a href="/login"><i class="fa fa-shopping-cart"></i> Cart</a></li>
										<li><a href="/login"><i class="fa fa-lock"></i> Login</a></li>
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
									<a href="/userHome" class="active"><img src="images/home/logo.png" alt="" /></a>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="shop-menu pull-right">
									<c:if test='${userType eq "PARENT"}'>
										<ul class="nav navbar-nav">
											<li><a href="/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
											<li><a href="/getAspirations" class="active"><i class="fa fa-star"></i>Aspirations</a></li>
											<li><a href="/cart"><i class="fa fa-shopping-cart"></i>My Cart</a></li>
											<li><a href="/checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li>
											<li><a href="/logout"><i class="fa fa-lock"></i>Logout</a></li>
										</ul>
									</c:if>
									<c:if test='${userType eq "DEPENDANT"}'>
										<ul class="nav navbar-nav">
											<li><a href="/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
											<li><a href="/getMyAspirations" class="active"><i class="fa fa-star"></i>Aspirations</a></li>
											<!-- <li><a href="/cart"><i class="fa fa-shopping-cart"></i>My Cart</a></li> -->
											<!-- <li><a href="/userCheckout"><i class="fa fa-crosshairs"></i> Checkout</a></li> -->
											<li><a href="/logout"><i class="fa fa-lock"></i>Logout</a></li>
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
									<li><a href="/userHome" class="active">Home</a></li>
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
									<li><a href="/inviteDependants">Invite Dependants</a></li>
                                </c:if>
                                <c:if test='${userType eq "DEPENDANT"}'>
                                	<li><a href="/userHome" class="active">Home</a></li>
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
						<form action="/loginSubmit" method="POST">
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
						<form method="POST" action="/dependantSignUp">
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
	
	
	</tiles:putAttribute>
</tiles:insertDefinition>
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