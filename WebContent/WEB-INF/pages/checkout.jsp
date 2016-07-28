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
								<a href="/buyremo/index"><img src="images/home/logo.png" alt="" /></a>
							</div>
						</div>
						<div class="col-sm-8">
							<div class="shop-menu pull-right">
								<ul class="nav navbar-nav">
									<li><a href="/buyremo/index"><i class="fa fa-user"></i> Account</a></li>
									<li><a href="/buyremo/login"><i class="fa fa-star"></i> Aspirations</a></li>
									<li><a href="/buyremo/checkout" class="active"><i class="fa fa-crosshairs"></i> Checkout</a></li>
									<li><a href="/buyremo/cart"><i class="fa fa-shopping-cart"></i> Cart</a></li>
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
										<li><a href="/buyremo/cart" ><i class="fa fa-shopping-cart"></i>My Cart  (${cartCount})</a></li>
										<li><a href="/buyremo/checkout" class="active"><i class="fa fa-crosshairs"></i> Checkout</a></li>
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
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <c:if test='${not empty sessionScope.userSession}'>
				  	<li><a href="/buyremo/userHome">Home</a></li>
				  </c:if>
				  <c:if test='${empty sessionScope.userSession}'>
				  	 <li><a href="/buyremo/index">Home</a></li>
				  </c:if>
				  <li class="active">Checkout</li>				  
				</ol>
			</div>

			<c:if test='${not empty sessionScope.userSession}'>
				<div class="shopper-informations">
					<div class="row">
						<div class="col-sm-4">
							<div class="shopper-info">
								<p>Shopper info</p>								
								<form>
									<input type="text" placeholder="Name" class="userName">
									<input type="text" placeholder="Email*" class="userEmail">
									<input type="text" placeholder="Title">
									<input type="text" placeholder="Telephone/Mobile*" class="userMobile">
									<input type="text" placeholder="Address 1 *" class="userAdd1">
									<input type="text" placeholder="Address 2" class="userAdd2">
									<input type="text" placeholder="Zip" class="userZip">
								</form>
							</div>
						</div>
						<div class="col-sm-5">
							<div class="shopper-info">
								<p>Ship To</p>							
								<form>
									<select id ="userNames" onchange="getDetails()"></select>
									<input type="text" placeholder="Name" class="depName">
									<input type="text" placeholder="Email*" class="depEmail">
									<input type="text" placeholder="Title" class="">
									<input type="text" placeholder="Telephone/Mobile*" class="depMobile">
									<input type="text" placeholder="Address 1 *" class="depAdd1">
									<input type="text" placeholder="Address 2" class="depAdd2">
									<input type="text" placeholder="Zip" class="depZip">
								</form>								
							</div>
						</div>
						<div class="col-sm-3">
							<div class="order-message">
								<p>Shipping Order</p>
								<textarea name="message"  placeholder="Notes about your order, Special Notes for Delivery" rows="16"></textarea>
								<label><input type="checkbox">  Bill to Shipping address</label>
							</div>
						</div>					
					</div>
				</div>
			</c:if>
			<c:if test='${empty sessionScope.userSession}'>
				<div class="checkout-options">
					<h3>New User!</h3>
					<p>Checkout options</p>
					<ul class="nav">
						<li>
							<label><input type="checkbox"> Register Account</label>
						</li>
						<li>
							<label><input type="checkbox"> Guest Checkout</label>
						</li>
					</ul>
				</div><!--/checkout-options-->
	
				<div class="register-req">
					<p>Please use Register And Checkout to easily get access to your order history, or use Checkout as Guest</p>
				</div><!--/register-req-->
	
				<div class="shopper-informations">
					<div class="row">
						<div class="col-sm-4">
							<div class="shopper-info">
								<p>Shopper info</p>								
								<form>	
									<input type="text" placeholder="Name" >							
									<input type="text" placeholder="Email*">
									<input type="text" placeholder="Title">
									<input type="text" placeholder="Telephone/Mobile*">
									<input type="text" placeholder="Address 1 *">
									<input type="text" placeholder="Address 2">
									<input type="text" placeholder="Zip">
								</form>								
							</div>
						</div>
						<div class="col-sm-5">
							<div class="shopper-info">
								<p>Ship To</p>							
								<form>
									<input type="text" placeholder="Name">
									<input type="text" placeholder="Email*">
									<input type="text" placeholder="Title">
									<input type="text" placeholder="Telephone/Mobile*">
									<input type="text" placeholder="Address 1 *">
									<input type="text" placeholder="Address 2">
									<input type="text" placeholder="Zip">
								</form>								
							</div>
						</div>
						<div class="col-sm-3">
							<div class="order-message">
								<p>Shipping Order</p>
								<textarea name="message"  placeholder="Notes about your order, Special Notes for Delivery" rows="16"></textarea>
								<label><input type="checkbox"> Bill to Shipping address</label>
							</div>	
						</div>					
					</div>
				</div>
			</c:if>
			
			<div class="review-payment">
				<h2>Review & Payment</h2>
			</div>

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
			<div class="payment-options">
				<span>
					<label><input type="checkbox"> Net Banking Transfer</label>
				</span>
				<span>
					<label><input type="checkbox"> Card Payment</label>
				</span>
				<span>
					<label><input type="checkbox"> Paypal</label>
				</span>
			</div>			
			<a class="btn btn-primary" href="">Cancel Request</a>
			<a class="btn btn-primary" href="">Continue to Payment</a>
			<br>
			<br>			
		</div>
	</section> <!--/#cart_items-->
	</tiles:putAttribute>
</tiles:insertDefinition>
<script>
	$(document).ready(function(){
		var getDependants = '/buyremo/getDependants.json'
		$.ajax({            	  
			url : getDependants,
			type : "GET",                 
			contentType: "application/json",
			crossDomain: true,                  
			success : function(data) {
			  $('#userNames').html("");
			  $('.userName').val(data.user.userName);
			  $('.userEmail').val(data.user.emailId);
			  $('.userMobile').val(data.user.mobileNumber);
			  $('.userAdd1').val(data.user.addressLine1);
			  $('.userAdd2').val(data.user.addressLine2);
			  $('.userZip').val(data.user.zip);
			  $('#userNames').append('<option value="" read-only>Select Dependent</option>');
			  data.dependants.forEach(function(dependant) {
			  	$('#userNames').append('<option value="'+dependant.id+'">'+dependant.userName+'</option>');
			  	localStorage.setItem(dependant.id, JSON.stringify(dependant));
			  });             	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});              
	});
	
	function getDetails() {
		var dependant = localStorage.getItem($('#userNames').val());
		dependant =JSON.parse(dependant);
		$('.depName').val(dependant.userName);
		$('.depEmail').val(dependant.emailId);
		$('.depMobile').val(dependant.mobileNumber);
		$('.depAdd1').val(dependant.addressLine1);
		$('.depAdd2').val(dependant.addressLine2);
		$('.depZip').val(dependant.zip);
	}
</script>