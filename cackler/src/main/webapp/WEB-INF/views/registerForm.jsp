<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
    <head>
        <title>Spitter</title>
        <link rel="stylesheet" type="text/css" 
              href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <a href="register?myLocale=en">English</a> | <a href="register?myLocale=fr">French</a>
        <h1>Register</h1>

        <sf:form method="POST" commandName="spitter">
            <sf:errors path="*" element="div" cssClass="errors"></sf:errors>

            <sf:label path="firstName" cssErrorClass="error"><spring:message code="first.name"/></sf:label>:
            <sf:input path="firstName" cssErrorClass="error"/><br/>

            <sf:label path="lastName" cssErrorClass="error"><spring:message code="last.name"/></sf:label>:
            <sf:input path="lastName" cssErrorClass="error"/><br/>

            <sf:label path="email" cssErrorClass="error"><spring:message code="email"/></sf:label>:
            <sf:input path="email" cssErrorClass="error"/><br/>

            <sf:label path="username" cssErrorClass="error"><spring:message code="username"/></sf:label>:
            <sf:input path="username" cssErrorClass="error"/><br/>

            <sf:label path="password" cssErrorClass="error"><spring:message code="password"/></sf:label>:
            <sf:password path="password" cssErrorClass="error"/><br/>            

            <input type="submit" value="Register" />
        </sf:form>
    </body>
</html>
