<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>BestBuy</title>
<meta charset="utf-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/styles.css">
</head>

<body>

	<!-- MAIN NAVABAR (LOGO, SEARCH, ADD GANGA AND ACCESS) -->
	<nav
		class="navbar-flex navbar navbar-expand-sm navbar-light navbar-back border shadow-sm">
		<a class="navbar-brand" href="/Project"><img src="images/pageLogo2.png"
			alt="pageLogo" height="100" width="100"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="Likes.do">Order
						by likes</a></li>
				<li class="nav-item"><a class="nav-link" href="Soldout.do">Show
						available</a></li>
				<!-- <li class="nav-item"><a class="nav-link" href="#">Link</a></li> -->
			</ul>
		</div>

		<!-- MODAL AND USER BUTTONS (ADD GANGA AND ACCESS) -->
		<div class="navbar-flex input-group input-container">
			<form method="GET" action="Searches.do">
				<div class="contanier-flex col-sm-20">
					<input type="text" class="form-control" placeholder="Search"
						name="search">
					<button class="btn btn-dark btn-outline-light" type="submit">Search</button>
				</div>
			</form>

			<c:choose>
				<c:when test="${empty user}">
					<div>
						<button type="button" class="form-control btn btn-success border"
							data-toggle="modal" data-target="#ModalLoginForm">Create
							chollo</button>
					</div>
					<div>
						<button type="button" id="access"
							class="form-control btn btn-success" data-toggle="modal"
							data-target="#ModalLoginForm">Access</button>
					</div>

				</c:when>
				<c:otherwise>
					<form method="GET" action="CreateChollo.do">
						<div>
							<button type="submit" class="form-control btn btn-success border">Create
								chollo</button>
						</div>
					</form>
					<form method="GET" action="ChollosUser.do">
						<div>
							<button type="submit" class="form-control btn btn-info border">My
								chollos</button>
						</div>
					</form>
					<div>
						<button type="button" id="access"
							class="form-control btn btn-info" data-toggle="modal"
							data-target="#ModalEditAccount">${user.username}</button>
					</div>
					<form method="GET" action="LogoutServlet.do">
						<div>
							<button type="submit" class="form-control btn btn-default">Logout</button>
						</div>
					</form>
				</c:otherwise>
			</c:choose>


			<!-- Modal HTML Markup -->
			<div id="ModalEditAccount" class="modal fade">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title">Profile</h1>
						</div>
						<div class="modal-body">
							<form method="GET" action="EditAccount.do">
								<input type="hidden" name="_token" value="">
								<div class="form-group">
									<label class="control-label">E-Mail Address</label>
									<div>
										<input type="email" class="form-control input-lg" name="email"
											value="">
									</div>
									<span class="label label-default">Current email: </span> <label
										class="control-label">${user.email}</label>
								</div>
								<div class="form-group">
									<div>
										<input type="password" class="form-control input-lg"
											name="password">
									</div>
									<span class="label label-default">Current password: </span> <label
										class="control-label">${user.password}</label>
								</div>
								<div class="form-group">
									<div>
										<button type="submit" class="btn btn-success ">Edit</button>
									</div>
								</div>
							</form>
							<form method="GET" action="DeleteAccount.do">
								<div>
									<button type="submit" class="btn btn-danger ">Delete
										account</button>
								</div>
							</form>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->




			<!-- Modal HTML Markup -->
			<div id="ModalLoginForm" class="modal fade">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title">Login</h1>
						</div>
						<div class="modal-body">
							<form method="POST" action="SignInServlet.do">
								<input type="hidden" name="_token" value="">
								<div class="form-group">
									<label class="control-label">E-Mail Address</label>
									<div>
										<input type="email" class="form-control input-lg" name="email"
											value="">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label">Password</label>
									<div>
										<input type="password" class="form-control input-lg"
											name="password">
									</div>
								</div>
								<div class="form-group">
									<div>
										<button type="submit" class="btn btn-success ">Login</button>
									</div>
								</div>
							</form>
							<h1>Or Signup!</h1>
							<form method="POST" action="SignUpServlet.do">
								<input type="hidden" name="_token" value="">
								<div class="form-group">
									<label class="control-label">Username</label>
									<div>
										<input type="text" class="form-control input-lg" name="name"
											value="">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label">E-Mail Address</label>
									<div>
										<input type="email" class="form-control input-lg" name="email"
											value="">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label">Password</label>
									<div>
										<input type="password" class="form-control input-lg"
											name="password">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label">Confirm Password</label>
									<div>
										<input type="password" class="form-control input-lg"
											name="password_confirmation">
									</div>
								</div>
								<div class="form-group">
									<div>
										<button type="submit" class="btn btn-success">
											Register</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
	</nav>

	<!-- CHOLLOS STRUCTURE -->
	<div class="flex-container">

		<!-- CHOLLO IN JSP -->
		<c:forEach var="chollo" items="${chollosList}">
			<c:choose>
				<c:when test="${chollo.soldout=='1'}">
					<div class="chollo-indv border shadow-sm p-3 mb-5 bg-white rounded">
						<div>
							<img class="chollo-logo desaturate"
								src="https://thumbs.dreamstime.com/b/vector-trendy-minimal-flat-design-special-offer-sign-geometric-circle-banners-vivid-transparent-retro-poster-s-style-vintage-pink-102438651.jpg">
						</div>
						<div>
							<a href="${chollo.link}"><button
									class="form-control btn-default" type="submit">SEE
									CHOLLO</button></a>
						</div>
						<div>
							<h4>${chollo.title}</h4>
						</div>
						<div>
							<p>${chollo.description}</p>
						</div>
						<c:choose>
							<c:when test="${empty user}">
								<div class="contanier-flex">
									<a data-toggle="modal" data-target="#ModalLoginForm"><button
											type="submit">&#128402;</button></a>
									<p>${chollo.likes}</p>
								</div>
							</c:when>
							<c:otherwise>
								<form action="Likes.do?idc=${chollo.id}&idu=${user.id}"
									method="post">
									<div class="contanier-flex">
										<button type="submit">&#128402;</button>
										<p>${chollo.likes}</p>
									</div>
								</form>
							</c:otherwise>
						</c:choose>
						<div>
							<p>SOLDOUT</p>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="chollo-indv border shadow-sm p-3 mb-5 bg-white rounded">
						<div>
							<img class="chollo-logo"
								src="https://thumbs.dreamstime.com/b/vector-trendy-minimal-flat-design-special-offer-sign-geometric-circle-banners-vivid-transparent-retro-poster-s-style-vintage-pink-102438651.jpg">
						</div>
						<div>
							<a href="${chollo.link}"><button
									class="form-control btn btn-success" type="submit">SEE
									CHOLLO</button></a>
						</div>
						<div>
							<h4>${chollo.title}</h4>
						</div>
						<div>
							<p>${chollo.description}</p>
						</div>
						<c:choose>
							<c:when test="${empty user}">
								<div class="contanier-flex">
									<a data-toggle="modal" data-target="#ModalLoginForm"><button
											type="submit">&#128077;</button></a>
									<p>${chollo.likes}</p>
								</div>
							</c:when>
							<c:otherwise>
								<form action="Likes.do?idc=${chollo.id}&idu=${user.id}"
									method="post">
									<div class="contanier-flex">
										<button type="submit">&#128077;</button>
										<p>${chollo.likes}</p>
									</div>
								</form>
							</c:otherwise>
						</c:choose>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>

</body>

</html>
