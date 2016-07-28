<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | BuyRemo&#8482 </title>
   	<tiles:insertAttribute name="pageStyles" ignore="true"/>	
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
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
	<tiles:insertAttribute name="body" ignore="true"/>
	<tiles:insertAttribute name="footer" ignore="true"/>
</body>
</html>