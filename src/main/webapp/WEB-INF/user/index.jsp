<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>

<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login and Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
</head>
<body>
<section class="section">
<div class="container">
<h1 class="title">Welcome</h1>

<div class="columns is-mobile">
	<div class="column is-half is-mobile is-dark">
		<h2 class="title is-primary">Register</h2>
	<form:form action="/registration" method="post" modelAttribute="newUser">
    <p>
        <form:label path="username">Username</form:label>
        <form:errors path="username"/>
        <form:input  class="input is-primary" path="username"/>
    </p>
    <p>
        <form:label path="email">E-mail</form:label>
        <form:errors path="email"/>
        <form:input class="input is-primary"  type="email" path="email"/>
    </p>
    <p>
        <form:label path="password"> Create Password</form:label>
        <form:errors path="password"/>
        <form:input class="input is-primary"  path="password"/>
    </p>
    <p>
        <form:label path="confirm">Confirm Password</form:label>
        <form:errors path="confirm"/>     
        <form:input class="input is-primary" path="confirm"/>
    </p>    
    <input type="submit" value="Submit"/>
</form:form>    
	
	
	</div>
	<div class="column is-half is-mobile is-dark">
	<h2 class="title is-primary">Login</h2>
		<form:form action="/login" method="post" modelAttribute="newLogin">

    <p>
        <form:label path="email">E-mail</form:label>
        <form:errors path="email"/>
        <form:input class="input is-primary"  type="email" path="email"/>
    </p>
    <p>
        <form:label path="password">Password</form:label>
        <form:errors path="password"/>
        <form:input class="input is-primary"  path="password"/>
    </p>
        <input type="submit" value="Submit"/>
</form:form>    
	
	</div>
</div>
</div>
</section>
</body>
</html>
