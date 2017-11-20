
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
	<!-- Hamburger Menu -->
	<button type="button" name="hamburger-menu"
		class="navbar-toggler navbar-toggler-right" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle Navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<!-- Brand or LOGO -->
	<a href="#" class="navbar-brand">Brand</a>
	<!-- Nav list items -->
	<div class="collapse navbar-collapse" id="navbarSupportedContent">

		<!-- Anchor Tags using as LIST: Margin Right AUTO  -->
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#">Home</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">
					Disabled</a></li>

			<!-- Drop down elements -->
			<li class="nav-item dropdown"><a href="#"
				class="nav-link dropdown-toggle" id="dropdownMenuLink"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					dropdown </a>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<a href="#" class="dropdown-header"> Yoo</a> <span
						class="dropdown-divider"></span> <a href="#" class="dropdown-item">Action1</a>
					<a href="#" class="dropdown-item">Action2</a> <a href="#"
						class="dropdown-item">Action3</a>
				</div></li>
		</ul>

		<!-- Anchor Tags using inside DIV -->
		<div class="navbar-nav">
			<a href="#" class="nav-item nav-link active"> Home2</a> <a href="#"
				class="nav-item nav-link">Link2</a> <a href="#"
				class="nav-item nav-link disabled">Disabled2</a>
		</div>

		<!-- Form related tags -->

		<form class="form-inline my-2 my-lg-0" action="index.html"
			method="post">
			<input type="text" name="search" placeholder="search"
				class="form-control mr-sm-2">
			<button type="submit" name="button"
				class="btn btn-outline-success my-2 my-sm-0">Search</button>
		</form>

	</div>

</nav>
