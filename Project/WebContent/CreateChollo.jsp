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

	<form method="POST" action="CreateChollo.do">
		<div class="box border shadow-sm p-3 mb-5 ">
			<span class="label label-default">Title: </span>
			<div>
				<input type="text" class="form-control input-lg" name="title"
					value="">
			</div> <br>
			<span class="label label-default">Description: </span>
			<div>
				<input type="text" class="form-control input-lg" name="description"
					value="">
			</div> <br>
			<span class="label label-info">Link: </span> 
			<div>
				<input type="url" class="form-control input-lg" name="link"
					value="">
			</div> <br>
			<div class="form-group">
				<div>
					<button type="submit" class="confirmButton btn btn-success">CREATE</button>
				</div>
			</div>
		</div>
	</form>

</body>

</html>