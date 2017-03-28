<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/resources/Duo-Web-v2.js" var="DuoWebv2js" />
<script src="${DuoWebv2js}"></script>

<title>Duo</title>
</head>
<body>

<iframe id="duo_iframe"
        data-host="api-2aa3cc6c.duosecurity.com"
        data-sig-request="${genaratedSignedRequest}"
        data-post-action="duo-verify">
</iframe>
<style>
  #duo_iframe {
    width: 100%;
    min-width: 304px;
    max-width: 620px;
    height: 330px;
    border: none;
  }
</style>

</body>
</html>