<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Any Tracking related stuff or CSS or JS content must load in HEAD section -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Encoding -->
<meta charset="utf-8">
<!-- Support for IE recent version(Google Query) -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Title of a Page -->
<title>Bootstrap Concepts</title>
<meta name="description"
	content="This page holds information of different Courses available which can be monitored by ADMIN">
<!-- To ensure proper rendering and touch zooming on mobile devices -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="robots" content="all,follow">

<!-- Any JavaScript is advised to place at the end of the document so the pages load faster -->
<!-- jQuery library -->
<script type="text/javascript"
	src="/Spring-Security/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="/Spring-Security/webjars/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/jquery-ui/jquery-ui.css">

<script type="text/javascript"
	src="/Spring-Security/webjars/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript"
	src="/Spring-Security/webjars/bootstrapvalidator/js/bootstrapValidator.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/bootstrap/css/bootstrap.css">

<script type="text/javascript"
	src="/Spring-Security/webjars/popper.js/dist/umd/popper.js"></script>


<script type="text/javascript"
	src="/Spring-Security/webjars/tablesorter/js/jquery.tablesorter.js"></script>
<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/tablesorter/css/theme.default.css" />

<link rel="stylesheet" type="text/css"
	href="/Spring-Security/webjars/font-awesome/css/font-awesome.css" />

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.js"></script>

<style type="text/css">
html, body, #map_canvas {
	width: 100%;
	height: 100%;
	margin: 0;
}
</style>
</head>
<body>
	<script type="text/javascript">
		$(function() {

			// variable definitions
			var center, mapOptions, map, marker, infowindow;

			// Initial position of the map
			center = new google.maps.LatLng(17.449783, 78.385645);

			function initialize() {
				// define map options
				mapOptions = {
					zoom : 5,
					center : center,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};

				// Attach the map to the document
				map = new google.maps.Map(
						document.getElementById('map_canvas'), mapOptions);

				// The marker details
				marker = new google.maps.Marker({
					position : new google.maps.LatLng(17.449783, 78.385645),
					map : map,
					title : 'marker7'
				});

				// define infowindow options
				infowindow = new google.maps.InfoWindow(
						{
							maxWidth : 450,
							disableAutoPan : false,
							content : '<div><a class="dialog" href="https://maps.google.com/" target="_blank"></a></div>',
						});

				// define the marker click functions
				google.maps.event
						.addListener(
								marker,
								'click',
								function() {
									infowindow.open(map, marker);
									google.maps.event
											.addListener(
													infowindow,
													'domready',
													function() {
														$(".dialog")
																.html(
																		'<img id="theImg" src="https://goo.gl/phmzis" />');
													});
								});
			}

			google.maps.event.addDomListener(window, 'load', initialize);

			// Bar Chart
			//bar
			var ctxB = document.getElementById("barChart").getContext('2d');
			var myBarChart = new Chart(ctxB, {
				type : 'bar',
				data : {
					labels : [ "Submitted", "Accepted", "Declined", "Closed" ],
					datasets : [ {
						label : '# of Votes',
						data : [ 12, 19, 3, 5 ],
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)' ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)' ],
						borderWidth : 1
					} ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				}
			});
		});
	</script>

	<!-- begin Header -->
	<div id="header">

		<!-- begin  Navigation bar-->
		<nav class="navbar navbar-light navbar-fixed-top"
			style="background-color: #e3f2fd;">

			<!-- Hamburger Menu starts-->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#adminNavbarToggler" aria-controls="adminNavbarToggler"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Hamburger Menu ends-->

			<!-- begin nav bar links -->
			<div class="collapse navbar-collapse" id="adminNavbarToggler">

				<!-- begin Nav bar Brand -->
				<div class="navbar-header">
					<a href="#" class="navbar-brand"> <img
						src="https://v4-alpha.getbootstrap.com/assets/brand/bootstrap-solid.svg"
						width="30" height="30" class="d-inline-block align-top" alt="">
						<b>Course Admin</b>
					</a>
				</div>
				<!-- end Nav bar Brand -->

				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li>
						<form class="form-inline my-2 my-lg-0">
							<input class="form-control mr-sm-2" type="text"
								placeholder="Search">
							<button class="btn btn-outline-success my-2 my-sm-0"
								type="submit">Search</button>
						</form>
					</li>
				</ul>
			</div>
			<!-- end nav bar links -->
		</nav>
		<!-- end Navigation bar -->
	</div>
	<!-- End Header -->
	<div class="container">
		<canvas id="barChart"></canvas>
	</div>

	<div class="container-fluid" id="map_canvas"></div>

	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDK828-2olYQSRxablCGSi-IM3BbWJHcsI&callback"
		type="text/javascript"></script>

</body>
</html>