<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Country List</title>
</head>
<body>
We operate in these countries:
<ul>
    <c:forEach items="${countries}" var="country">
        <li>${country.value}</li>
    </c:forEach>
</ul>
<c:set var="user" value="sessiontest" scope="session" />
<p><c:out value="${sessionScope.user}" /></p>
<p><c:out value="${sessionScope.product.name}" /></p>
</body>
</html>