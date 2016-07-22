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
											<li><a href="/getAspirations"><i class="fa fa-star"></i>Aspirations  (${aspirationCount})</a></li>
											<li><a href="/cart"  class="active"><i class="fa fa-shopping-cart"></i>My Cart  (${cartCount})</a></li>
											<li><a href="/checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li>
											<li><a href="/logout"><i class="fa fa-lock"></i>Logout</a></li>
										</ul>
									</c:if>
									<c:if test='${userType eq "DEPENDANT"}'>
										<ul class="nav navbar-nav">
											<li><a href="/userHome" class="active"><i class="fa fa-user"></i><c:out value="${sessionScope.userSession.userName}" /></a></li>
											<li><a href="/getMyAspirations"><i class="fa fa-star"></i>My Aspirations|(${depdtAspirationCount})</a></li>
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
									<c:if test='${empty sessionScope.userSession}'> 
	                                	<li><a href="/index" class="active">Home</a></li>
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
		
		<div id="contact-page" class="container">
    	<div class="bg">
	    	<div class="row">    		
	    		<div class="col-sm-12">    			   			
					<h2 class="title text-center">Add Aspiration</h2>
				</div>			 		
			</div>  
			<div class="row">    		
	    		<div class="col-sm-12">    			   			
					<br>
				</div>			 		
			</div> 			
    		<div class="row">  	
	    		<div class="col-sm-8">
	    			<div class="contact-form">
	    				<div class="status alert alert-success" style="display: none"></div>
	    				<c:if test='${empty sessionScope.userSession}'>
					    	<form id="main-contact-form" class="contact-form row" name="contact-form" method="post">
					            <div class="form-group col-md-5">
					            <div class="shopper-info">
					            	<p>Parent Email</p>
					                <input type="email" name="email" id="emailId"class="form-control" required="required" placeholder="Email">
					                <br>
					                <textarea name="message" id="message" required="required" class="form-control" rows="8" placeholder="Your Message Here"></textarea>
					            </div>
					            </div>                      
					            <div class="form-group col-md-1">
					               
					            </div>
					            <div class="form-group col-md-6">
					            	<div class="shopper-informations">
										<div class="row">
							               <div class="shopper-info">
											<p>Shopper info</p>									
												<input type="text" placeholder="Name" id="aspirantName" class="form-control"><br>
												<input type="text" placeholder="Email*"  id="aspirantEmail" class="form-control"><br>
												<input type="text" placeholder="Title"  id="title" class="form-control"><br>
												<input type="text" placeholder="Telephone/Mobile*"  id="mobileNumber" class="form-control"><br>
												<input type="text" placeholder="Address 1 *" id="addressLine1" class="form-control"><br>
												<input type="text" placeholder="Address 2"  id="addressLine2" class="form-control"><br>
												<input type="text" placeholder="Zip" id="zip" class="form-control"><br>
											</div>
										</div>
									</div>
									
									<button name="submit" class="btn btn-primary pull-right" onclick="requestAnonymousAspiration()">Submit</button>
					            </div>
					        </form>
						</c:if>
						<c:if test='${not empty sessionScope.userSession}'>
							<input type="hidden" name="email" class="form-control" required="required" placeholder="Email" value="${userType}">
							<c:if test='${userType eq "PARENT"}'>
								<form id="main-contact-form" class="contact-form row" name="contact-form" method="post">
						            <div class="form-group col-md-12">
						                <input type="email" name="email" class="form-control" required="required" placeholder="Email">
						            </div>
						            <div class="form-group col-md-12">
						                <input type="text" name="subject" class="form-control" required="required" placeholder="Subject">
						            </div>                    
						            <div class="form-group col-md-12">
						                <input type="submit" name="submit" class="btn btn-primary pull-right" value="Submit">
						            </div>
						        </form>
					        </c:if>
					        <c:if test='${userType eq "DEPENDANT"}'>
								<form id="main-contact-form" class="contact-form row" name="contact-form" method="post">
						            <div class="form-group col-md-12">
						                <input type="parentEmail" name="parentEmail" id="parentEmail" class="form-control" required="required" placeholder="Email">
						                <input type="hidden" name="email" class="form-control" required="required" placeholder="Email" value="${user.userEmail}">
						            </div>
						            <div id="notifications"></div>
						            <div class="form-group col-md-12">
						                <textarea name="message" id="message" required="required" class="form-control" rows="8" placeholder="Your Message Here"></textarea>
						            </div>                      
						            <div class="form-group col-md-12">
						                <button name="submit" class="btn btn-primary pull-right" onclick="requestAspiration()">Submit</button>
						            </div>
						        </form>
					        </c:if>
					        <%-- <form id="main-contact-form" class="contact-form row" name="contact-form" method="post">
						            <div class="form-group col-md-12">
						                <input type="parentEmail" name="parentEmail" id="parentEmail" class="form-control" required="required" placeholder="Email">
						                <input type="hidden" name="email" class="form-control" required="required" placeholder="Email" value="${user.userEmail}">
						            </div>
						            <div id="notifications"></div>
						            <div class="form-group col-md-12">
						                <textarea name="message" id="message" required="required" class="form-control" rows="8" placeholder="Your Message Here"></textarea>
						            </div>                      
						            <div class="form-group col-md-12">
						                <button name="submit" class="btn btn-primary pull-right" onclick="requestAspiration()">Submit</button>
						            </div>
						        </form> --%>
					    </c:if>
	    			</div>
	    		</div>    			
	    	</div>  
    	</div>	
    </div><!--/#contact-page-->
	</tiles:putAttribute>
</tiles:insertDefinition>
<script>
	function requestAspiration(){
		var aspirationData = JSON.parse(localStorage.getItem("aspiration"));
		var setData ={
			productId : aspirationData.productId,
			productName: aspirationData.productName,
			productDesc: aspirationData.productDesc,
			productPrice: aspirationData.productPrice,
			categoryName: aspirationData.categoryName,
			categoryId: aspirationData.categoryId,
			galleryUrl: aspirationData.galleryUrl,
			viewItemUrl: aspirationData.viewItemUrl,
			currencyId: aspirationData.currencyId,
			parentEmail : $("#parentEmail").val(),
			message : $("#message").val()
		}
		$.ajax({            	  
			type : "POST",
			url : "/requestAspiration.json",
			data : setData,            
			success : function(responseData) {
				 if('success'== responseData.status){               
				 }else{
				 	 $("#notifications").notify("Email you entered is not a registered Buyremo Id.",{ position:"right top" });               
				 }             	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});  
	}
</script>
<script>
	function requestAnonymousAspiration(){
		var aspirationData = JSON.parse(localStorage.getItem("aspiration"));
		var setData ={
			productId : aspirationData.productId,
			productName: aspirationData.productName,
			productDesc: aspirationData.productDesc,
			productPrice: aspirationData.productPrice,
			categoryName: aspirationData.categoryName,
			categoryId: aspirationData.categoryId,
			galleryUrl: aspirationData.galleryUrl,
			viewItemUrl: aspirationData.viewItemUrl,
			currencyId: aspirationData.currencyId,
			parentEmail : $("#emailId").val(),
			message : $("#message").val(),
			aspirantName :  $("#aspirantName").val(),
			aspirantEmail :  $("#aspirantEmail").val(),
			mobileNumber :  $("#mobileNumber").val(),
			addressLine1 :  $("#addressLine1").val(),
			addressLine2 :  $("#addressLine2").val(),
			zip :  $("#zip").val()
		}
		$.ajax({            	  
			type : "POST",
			url : "/requestAnonymousAspiration.json",
			data : setData,            
			success : function(responseData) {
				 if('success'== responseData.status){               
				 }else{
				 	 $("#notifications").notify("Email you entered is not a registered Buyremo Id.",{ position:"right top" });               
				 }             	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});  
	}
</script>