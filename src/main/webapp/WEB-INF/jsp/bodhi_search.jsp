<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="${pageContext.request.contextPath}/bootstrap/favicon/favicon.ico">

<title>Bodhi Admin Search Page</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/landing/theme.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="${pageContext.request.contextPath}/bootstrap/js/ie-emulation-modes-warning.js"></script>


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body role="document">

  <!-- Fixed navbar -->
    <%@include file="header.jsp" %>

	<div class="container theme-showcase" role="main">

		<form class="form-signin" action="/bodhi/admin/searchResults"
			role="form">
			<h2 class="form-signin-heading">Search library</h2>
			<input type="search" name='searchBookName' class="form-control"
				placeholder="Title of the book" autofocus> <br></br> <input
				type="search" name='searchAuthorName' class="form-control"
				placeholder="Author(s) First, Last or Middle Name"> <br></br>
			<input type="search" name='searchBookId' class="form-control"
				placeholder="Enter Book Id"> <br></br>
			<button class="btn btn-lg btn-primary" type="submit">Search</button>
			<button class="btn btn-lg btn-primary" type="reset">Reset</button>
			
		</form>


		<c:choose>
			<c:when test="${bookresults=='nomatchingrecords'}">
				<div class="page-header">
					<h1>Search results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>No matching results found. Please change your search
										criteria and try again.</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${not empty bookresults}">
				<div class="page-header">
					<h1>Search results</h1>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Title</th>
									<th>Author(s)</th>
									<th>Book ID</th>
									<th>Branch ID</th>
									<th>Available at this Branch</th>
									<th>Currently Available</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach var="book" items="${bookresults}"
										varStatus="bookIndex">
										<tr>
											<td>${bookIndex.index}</td>
											<td>${book.title}</td>
											<td>${book.author_name}</td>
											<td>${book.book_id}</td>
											<td>${book.branch_id}</td>
											<td>${book.no_of_copies}</td>
											<c:if test="${empty book.copies_avl}">
											    <td>${book.no_of_copies}</td>
											</c:if>
											<c:if test="${not empty book.copies_avl}">
											    <td>${book.copies_avl}</td>
											</c:if>
											
										</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<br />
			</c:otherwise>
		</c:choose>









	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
