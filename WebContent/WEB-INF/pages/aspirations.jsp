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
									<li><a href="/login"><i class="fa fa-star"></i> Aspirations|${aspirationCount}</a></li>
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
										<li><a href="/getAspirations" class="active"><i class="fa fa-star"></i>Aspirations  (${aspirationCount})</a></li>
										<li><a href="/cart"><i class="fa fa-shopping-cart"></i>My Cart  (${cartCount})</a></li>
										<li><a href="/checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li>
										<li><a href="/logout"><i class="fa fa-lock"></i>Logout</a></li>
									</ul>
								</c:if>
								<c:if test='${userType eq "DEPENDANT"}'>
									<ul class="nav navbar-nav">
										<li><a href="/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
										<li><a href="/getMyAspirations" class="active"><i class="fa fa-star"></i>My Aspirations  (${depdtAspirationCount})</a></li>
										<!-- <li><a href="#"><i class="fa fa-shopping-cart"></i>My Cart</a></li> -->
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
	
	<section>
		<div class="container">
			<div class="row">				
				<div class="col-sm-12">
					<h2 class="title text-center">My Aspirations</h2>
						<div class="features_items" id="scrollbox" style="height:1200px;">
							<c:choose>
								<c:when test="${aspirationCount eq 0}">
									<div class="alert alert-warning">
										<h4><strong>Nothing here!</strong> Seems there are no aspirations added to your account.</h4>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="aspiration" items="${aspirationsList}" >
										<div class="col-sm-3">
											<div class="product-image-wrapper">
												<div class="single-products">
													<div class="single-products">
														<div class="productinfo text-center">
															<img src="<c:out value="${aspiration.galleryUrl}"/>" alt="" style="height:168px;width:149px;" />
															<h2>$<c:out value="${aspiration.productPrice}"/></h2>
															<p><c:out value="${aspiration.productName}"/></p>
															
														</div>
													</div>
													<c:if test='${userType eq "PARENT"}'>
														<div class="product-overlay">
															<div class="overlay-content">
																<h2><c:out value="${aspiration.message}"/></h2>
																<!-- <p>Easy Polo Black Edition</p> -->
																<p>Requested by:<strong><c:out value="${aspiration.aspirantName}"/></strong></p>
																<button class="btn btn-default add-to-cart" onclick="buyItem(<c:out value="${aspiration.aspirationId}"/>)"><i class="fa fa-shopping-cart"></i>Move to Cart</button>
															</div>
														</div>
													</c:if>
												</div>
												<div class="choose">
													<ul class="nav nav-pills nav-justified">
														<li><a href="" onclick="removeAspiration(<c:out value="${aspiration.aspirationId}"/>)"><i class="fa fa-plus-square"></i>Remove Aspiration</a></li>
														<li><a href="<c:out value="${aspiration.viewItemUrl}"/>"><i class="fa fa-plus-square"></i>Review Item in Merchant Site</a></li>
													</ul>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div><!--features_items-->
				</div>
			</div>
	</section>
	</tiles:putAttribute>
</tiles:insertDefinition>
<script>
	function removeAspiration(id){
		$.ajax({            	  
			type : "POST",
			url : "/removeAspiration/"+id+".json",
			dataType : "json",            
			success : function(data) {
			  //alert(JSON.stringify(data));
			  window.location.reload();               	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});
	}
	
	function buyItem(id){
		$.ajax({            	  
			type : "POST",
			url : "/buyAspiration/"+id+".json",
			dataType : "json",            
			success : function(data) {
			  window.location.reload();			                  	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});
	}
</script>