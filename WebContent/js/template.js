map.addMarker({
	lat : -12.043333,
	lng : -77.03,
	title : 'Lima',
	details : {
		database_id : 42,
		author : 'HPNeo'
	},
	animation : google.maps.Animation.DROP, //bounce animation
	icon : "http://www.sanwebe.com/assets/google-map-markers-infowindows/icons/pin_green.png",
	click : function(e) {
		if (console.log)
			console.log(e);
		alert('You clicked in this marker');
		map.setCenter(e.latLng.lat(), e.latLng.lng());
	},
	mouseover : function(e) {
		if (console.log)
			console.log(e);
		map.setCenter(e.latLng.lat(), e.latLng.lng());
	}
});
map.addMarker({
	lat : -12.042,
	lng : -77.028333,
	title : 'Marker with InfoWindow',
	infoWindow : {
		content : '<p>HTML Content</p>'
	},
	animation : google.maps.Animation.DROP, //bounce animation
	icon : "https://cdn0.iconfinder.com/data/icons/business-tab-bar-icons/60/Pagoda.png",
});
