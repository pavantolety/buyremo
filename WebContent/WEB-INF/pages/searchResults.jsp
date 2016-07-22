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
								<a href="/index"><img src="../../images/home/logo.png" alt="" /></a>
							</div>
						</div>
						<div class="col-sm-8">
							<div class="shop-menu pull-right">
								<ul class="nav navbar-nav">
									<li><a href="/index"><i class="fa fa-user"></i> Account</a></li>
									<li><a href="/login"><i class="fa fa-star"></i> Aspirations</a></li>
									<li><a href="/checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li>
									<li><a href="/cart"><i class="fa fa-shopping-cart"></i> Cart</a></li>
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
										<li><a href="/getMyAspirations"><i class="fa fa-star"></i>My Aspirations (${depdtAspirationCount})</a></li>
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
	
	<section id="advertisement">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <c:if test='${not empty sessionScope.userSession}'>
				  	<li><a href="/userHome">Home</a></li>
				  	 <li class="active">Search Results</li>
				  </c:if>
				  <c:if test='${empty sessionScope.userSession}'>
				  	 <li><a href="/index">Home</a></li>
				  	 <li class="active">Search Results</li>
				  </c:if>
				  
				</ol>
			</div>
		</div>
	</section>
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Sportswear
										</a>
									</h4>
								</div>
								<div id="sportswear" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="">Nike </a></li>
											<li><a href="">Under Armour </a></li>
											<li><a href="">Adidas </a></li>
											<li><a href="">Puma</a></li>
											<li><a href="">ASICS </a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#mens">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Mens
										</a>
									</h4>
								</div>
								<div id="mens" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="">Fendi</a></li>
											<li><a href="">Guess</a></li>
											<li><a href="">Valentino</a></li>
											<li><a href="">Dior</a></li>
											<li><a href="">Versace</a></li>
											<li><a href="">Armani</a></li>
											<li><a href="">Prada</a></li>
											<li><a href="">Dolce and Gabbana</a></li>
											<li><a href="">Chanel</a></li>
											<li><a href="">Gucci</a></li>
										</ul>
									</div>
								</div>
							</div>
							
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#womens">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Womens
										</a>
									</h4>
								</div>
								<div id="womens" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="">Fendi</a></li>
											<li><a href="">Guess</a></li>
											<li><a href="">Valentino</a></li>
											<li><a href="">Dior</a></li>
											<li><a href="">Versace</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Kids</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Fashion</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Households</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Interiors</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Clothing</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Bags</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Shoes</a></h4>
								</div>
							</div>
						</div><!--/category-productsr-->
					
						<div class="brands_products"><!--brands_products-->
							<h2>Brands</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
									<li><a href=""> <span class="pull-right">(50)</span>Acne</a></li>
									<li><a href=""> <span class="pull-right">(56)</span>Grüne Erde</a></li>
									<li><a href=""> <span class="pull-right">(27)</span>Albiro</a></li>
									<li><a href=""> <span class="pull-right">(32)</span>Ronhill</a></li>
									<li><a href=""> <span class="pull-right">(5)</span>Oddmolly</a></li>
									<li><a href=""> <span class="pull-right">(9)</span>Boudestijn</a></li>
									<li><a href=""> <span class="pull-right">(4)</span>Rösch creative culture</a></li>
								</ul>
							</div>
						</div><!--/brands_products-->
						
						<div class="price-range"><!--price-range-->
							<h2>Price Range</h2>
							<div class="well">
								 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
								 <b>$ 0</b> <b class="pull-right">$ 600</b>
							</div>
						</div><!--/price-range-->
						
						<div class="shipping text-center"><!--shipping-->
							<img src="images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
						
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<h2 class="title text-center">Featured Items</h2>
					<div class="features_items" id="scrollbox" style="height:1200px;"><!--features_items-->
						<div id="productData"></div>
					</div><!--features_items-->
				</div>
			</div>
		</div>
	</section>
	
	</tiles:putAttribute>
</tiles:insertDefinition>
<script type="text/javascript">
$(document).ready(function () {
   	var searchKey = localStorage.getItem("searchString"); 
   	if(searchKey!=""){ 	   		
		var val = searchKey;
		var url = "http://svcs.ebay.com/services/search/FindingService/v1";
		url += "?OPERATION-NAME=findItemsByKeywords";
		url += "&SERVICE-VERSION=1.0.0";
		url += "&SECURITY-APPNAME=Labsf98a6-b419-4c0e-b20c-a3674b94530";
		url += "&GLOBAL-ID=EBAY-US";
		//url += "&GLOBAL-ID=EBAY-IN";
		url += "&RESPONSE-DATA-FORMAT=JSON";
		//url += "&callback=_cb_findItemsByKeywords";
		url += "&REST-PAYLOAD";
		url += "&keywords=" + val;
		url += "&paginationInput.entriesPerPage=30";
		
		//Fecthing products from ebay
    
		$.ajax({
		  url : url,
		  dataType : 'jsonp',
		  success : function(data) {
			items = data.findItemsByKeywordsResponse[0].searchResult[0].item || [];
			var j=0;
			for (var i = 0; i < items.length; ++i) {
				var item = items[i];
				var itemId = item.itemId+"";
				var title = item.title+"";
				var gallery_pic = item.galleryURL+"";
				var view_pic = item.viewItemURL;
				var currencyId = items[i].sellingStatus[0].convertedCurrentPrice[0].currencyId;
				var viewitem = item.viewItemURL;
				var itemPrice = items[i].sellingStatus[0].currentPrice[0].__value__;
				var primaryCategoryId = items[i].primaryCategory[0].categoryId;
				var primaryCategoryName = items[i].primaryCategory[0].categoryName;
				var originalPrice;
				var discountPrice = 0;
				var merchantLogo = "images/ebay.png";
				//originalPrice = parseFloat(itemPrice).toFixed(2);
				//itemPrice=((originalPrice-discountPrice).toFixed(2));
				//console.log(originalPrice);
				//++j;
				//Sdiscount=parseFloat(discountPrice+discount).toFixed(2);
				//alert(itemId);
				
				var prodDescUrl = "http://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=JSON&appid=Labsf98a6-b419-4c0e-b20c-a3674b94530&siteid=0&version=515&ItemID="+itemId+"&IncludeSelector=TextDescription";
			  
				$.ajax({            	  
					url : prodDescUrl,
					type : "GET",
					dataType : "jsonp",                  
					contentType: "application/json",
					crossDomain: true,                  
					success : function(data) {
					  //alert(JSON.stringify(data));                	  
					}, error : function (data){
					 // alert(JSON.stringify(data));                	  
					}
				});              
 
			var oneItem = {
               ITEM_ID  :itemId,
               NAME : title,
               DESCRIPTION : title,
               GALLERY_PIC : gallery_pic,
               VIEW_PIC : view_pic,
               CATEGORY_ID : primaryCategoryId,
               CATEGORY_NAME : primaryCategoryName,
               CURRENCY_ID : currencyId,
               FINAL_PRICE : itemPrice,
               //ORIGINAL_PRICE:originalPrice,
               //DISCOUNT_PRICE : discountPrice
             };
             var str=oneItem.NAME+"";
             result = str.split(" ");
             console.log(result);
		  $('<div class="col-sm-4">\
				<div class="product-image-wrapper">\
					<div class="single-products">\
						<div class="productinfo text-center">\
							<input type="hidden" name="itemId" id="itemId'+oneItem.ITEM_ID +'" value="'+oneItem.ITEM_ID+'">\
							<input type="hidden" name="itemId" id="itemName'+oneItem.ITEM_ID +'" value="'+oneItem.NAME+'">\
							<input type="hidden" name="itemId" id="itemDesc'+oneItem.ITEM_ID +'" value="'+oneItem.DESCRIPTION+'">\
							<input type="hidden" name="itemId" id="itemGallery'+oneItem.ITEM_ID +'" value="'+oneItem.GALLERY_PIC+'">\
							<input type="hidden" name="itemId" id="itemPic'+oneItem.ITEM_ID +'"value="'+oneItem.VIEW_PIC+'">\
							<input type="hidden" name="itemId" id="itemCatId'+oneItem.ITEM_ID +'" value="'+oneItem.CATEGORY_ID+'">\
							<input type="hidden" name="itemId" id="itemCatName'+oneItem.ITEM_ID +'"value="'+oneItem.CATEGORY_NAME+'">\
							<input type="hidden" name="itemId" id="itemCurr'+oneItem.ITEM_ID +'" value="'+oneItem.CURRENCY_ID+'">\
							<input type="hidden" name="itemId" id="itemPrice'+oneItem.ITEM_ID +'" value="'+oneItem.FINAL_PRICE+'">\
							<img src='+oneItem.GALLERY_PIC+' alt="" style="height:168px;width:149px;"/>\
							<h2>$'+oneItem.FINAL_PRICE+'</h2>\
							<p>'+oneItem.NAME+'</p>\
							<button class="btn btn-default add-to-cart" onclick="saveAspiration('+oneItem.ITEM_ID+')"><i class="fa fa-shopping-cart"></i>Add to Aspitations</button>\
						</div>\
					</div>\
				</div>\
		  </div>').appendTo("#productData");
			}				
		},
		error : function(data) {
			  //alert("ERROR while fetching records from BestBuy :::"+JSON.stringify(data));
		}
		}); 
   	   	
   	//End of fetching products from ebay.    		
   	
       }  
       //setTimeout(function(){  window.location.href = "/aspirationDiscovery/"+val; }, 12000);
   	else{
   		alert("Please search with valid details");
   	}
   });      
</script>
<script>
	function saveAspiration(id){
		var aspiration={
			productId : id,
			productName: $("#itemName"+id).val(),
			productDesc: $("#itemDesc"+id).val(),
			productPrice: $("#itemPrice"+id).val(),
			categoryName: $("#itemCatName"+id).val(),
			categoryId: $("#itemCatId"+id).val(),
			galleryUrl: $("#itemGallery"+id).val(),
			viewItemUrl: $("#itemPic"+id).val(),
			currencyId: "USD"
		}
		if(aspiration != null){
			localStorage.setItem("aspiration", JSON.stringify(aspiration));
			window.location.href = '/addItem';
		}
	}
</script>