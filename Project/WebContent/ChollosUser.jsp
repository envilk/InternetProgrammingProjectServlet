<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
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

	<div class="flex-container">
		<form method="POST" action="ChollosUser.do">
			<div>
				<button type="submit" class="confirmButton btn btn-success ">RETURN</button>
			</div>
		</form>
		<!-- CHOLLO IN JSP -->
		<c:forEach var="chollo" items="${chollosUser}">
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
						<a href="<c:url value='EditChollo.do?id=${chollo.id}'/>">EDIT
							CHOLLO</a> <br> <a
							href="<c:url value='DeleteCholloConfirmation.do?id=${chollo.id}'/>">DELETE
							CHOLLO</a>
						<div>
							<h4>${chollo.title}</h4>
						</div>
						<div>
							<p>${chollo.description}</p>
						</div>
						<form action="Soldout.do" method="POST">
							<input type="hidden" name="id" value="${chollo.id}" />
							<button type="submit" class="form-control btn btn-outline-success">SET
								AS AVAILABLE</button>
						</form>
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
									class="form-control btn-success" type="submit">SEE
									CHOLLO</button></a>
						</div>
						<a href="<c:url value='EditChollo.do?id=${chollo.id}'/>">EDIT
							CHOLLO</a> <br> <a
							href="<c:url value='DeleteCholloConfirmation.do?id=${chollo.id}'/>">DELETE
							CHOLLO</a>
						<div>
							<h4>${chollo.title}</h4>
						</div>
						<div>
							<p>${chollo.description}</p>
						</div>
						<form action="Soldout.do" method="POST">
							<input type="hidden" name="id" value="${chollo.id}" />
							<button type="submit" class="form-control btn btn-dark">SET
								AS SOLDOUT</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>

	</div>

</body>

</html>