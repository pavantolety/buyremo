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
								<li><a href=""><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
								<li><a href=""><i class="fa fa-envelope"></i> info@domain.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href=""><i class="fa fa-facebook"></i></a></li>
								<li><a href=""><i class="fa fa-twitter"></i></a></li>
								<li><a href=""><i class="fa fa-linkedin"></i></a></li>
								<li><a href=""><i class="fa fa-dribbble"></i></a></li>
								<li><a href=""><i class="fa fa-google-plus"></i></a></li>
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
								<a href="/buyremo/index"><img src="images/home/logo.png" alt="" /></a>
							</div>
						</div>
						<div class="col-sm-8">
							<div class="shop-menu pull-right">
								<ul class="nav navbar-nav">
									<li><a href="/buyremo/index"><i class="fa fa-user"></i> Account</a></li>
									<li><a href="/buyremo/login"><i class="fa fa-star"></i> Aspirations</a></li>
									<!-- <li><a href="/buyremo/checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li>
									<li><a href="/buyremo/cart" class="active"><i class="fa fa-shopping-cart"></i> Cart</a></li> -->
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
								<a href="/buyremo/userHome" class="active"><img src="images/home/logo.png" alt="" /></a>
								<input type="hidden" value="${userType}">
							</div>
						</div>
						<div class="col-sm-8">
							<div class="shop-menu pull-right">
								<c:if test='${userType eq "PARENT"}'>
									<ul class="nav navbar-nav">
										<li><a href="/buyremo/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
										<li><a href="/buyremo/getAspirations"><i class="fa fa-star"></i>Aspirations  (${aspirationCount})</a></li>
										<li><a href="/buyremo/cart"  class="active"><i class="fa fa-shopping-cart"></i>My Cart  (${cartCount})</a></li>
										<li><a href="/buyremo/checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li>
										<li><a href="/buyremo/logout"><i class="fa fa-lock"></i>Logout</a></li>
									</ul>
								</c:if>
								<c:if test='${userType eq "DEPENDANT"}'>
									<ul class="nav navbar-nav">
										<li><a href="/buyremo/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
										<li><a href="/buyremo/getMyAspirations"><i class="fa fa-star"></i>My Aspirations|(${depdtAspirationCount})</a></li>
										<!-- <li><a href="#"><i class="fa fa-shopping-cart"></i>My Cart</a></li> -->
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
									<li><a href="/inviteDependants">Invite Dependants</a></li>
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

	<section id="cart_items">
		<div class="container">
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description">Description</td>
							<td class="price">Price</td>
							<td class="total">Total</td>
							<td class="price"></td>							
							<td></td>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test='${cartCount eq 0}'>
							<tr>
								<td>
									<strong>Nothing here!</strong> Seems there are no aspirations in your cart.
								</td>
							</tr>
						</c:when>
						<c:otherwise>
						<c:forEach var="aspiration" items="${aspirationsList}" >
							<tr>
								<td class="cart_product">
									<a href=""><img src="<c:out value="${aspiration.galleryUrl}"/>" alt=""></a>
								</td>
								<td class="cart_description">
									<h4><a href=""><c:out value="${aspiration.productName}"/></a></h4>
									<p>Product Id:<c:out value="${aspiration.productId}"/> </p>
								</td>
								<td class="cart_price">
									<p>$<c:out value="${aspiration.productPrice}"/></p>
								</td>
								<td class="cart_total">
									<p class="cart_total_price" id="productPrice">$<c:out value="${aspiration.productPrice}"/></p>
								</td>
								<td class="cart_detete">
									<a class="btn btn-default check_out" href="" onclick="moveItemFromCart(<c:out value="${aspiration.aspirationId}"/>)">Move to Aspirations</a>
									<br>
									<a class="btn btn-default check_out" href="<c:out value="${aspiration.viewItemUrl}"/>" >View in Merchant Site</a>
								</td>
								<td class="cart_delete">
									<a class="cart_quantity_delete" href="" onclick="removeItemFromCart(<c:out value="${aspiration.aspirationId}"/>)"><i class="fa fa-times"></i></a>
								</td>
							</tr>
						</c:forEach>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</section> <!--/#cart_items-->

	<section id="do_action">
		<div class="container">
			<div class="heading">
				<h3>What would you like to do next?</h3>
				<p>Choose if you have a discount code or reward points you want to use or would like to estimate your delivery cost.</p>
			</div>
			<div class="row">
				<!-- <div class="col-sm-6">
					<div class="chose_area">
						<ul class="user_option">
							<li>
								<input type="checkbox">
								<label>Use Coupon Code</label>
							</li>
							<li>
								<input type="checkbox">
								<label>Use Gift Voucher</label>
							</li>
							<li>
								<input type="checkbox">
								<label>Estimate Shipping & Taxes</label>
							</li>
						</ul>
					</div>
				</div> -->
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Cart Sub Total <span>$<c:out value="${totalAmount}"/></span></li>
							<li>VAT <span>NA</span></li>
							<li>Shipping Cost <span>NA</span></li>
							<li>Total <span>$<c:out value="${totalAmount}"/></span></li>
						</ul>
							<a class="btn btn-default update" href="">Update</a>
							<a class="btn btn-default check_out" href="/checkout">Check Out</a>
					</div>
				</div>
			</div>
		</div>
	</section><!--/#do_action-->
	</tiles:putAttribute>
</tiles:insertDefinition>
<script>
	function removeItemFromCart(id){
		$.ajax({            	  
			type : "POST",
			url : "/buyremo/removeCartItem/"+id+".json",
			dataType : "json",            
			success : function(data) {
			  //alert(JSON.stringify(data));
			  location.reload();               	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});
	}
</script>
<script>
	function moveItemFromCart(id){
		$.ajax({            	  
			type : "POST",
			url : "/buyremo/moveToAspirations/"+id+".json",
			dataType : "json",            
			success : function(data) {
			  //alert(JSON.stringify(data));
			 location.reload();               	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});
	}
</script>