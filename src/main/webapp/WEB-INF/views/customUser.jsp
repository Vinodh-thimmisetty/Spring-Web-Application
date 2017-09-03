<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Hello World!</h2>
	<div>
		<c:set var="currentUser" value="${user}"></c:set>
		<%-- <c:set var="currentUser" value="${httpServletRequest.userPrincipal}"></c:set> --%>
		Logged In User First Name ::
		<c:out value="${currentUser.email}"></c:out>
		<br /> Logged In User Last Name ::
		<c:out value="${currentUser.firstName}"></c:out>


	</div>
</body>
</html>
