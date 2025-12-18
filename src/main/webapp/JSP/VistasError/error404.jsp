<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">

<head>
  <jsp:include page="/INC/cabecera.jsp">
    <jsp:param name="titulo" value="Error 404" />
    <jsp:param name="estilo" value="${estilo}" />
  </jsp:include>


</head>
<body>

<!-- Header -->
<nav class="navbar navbar-expand-lg">
  <div class="container">
    <a class="navbar-brand" href="${contexto}/FrontController">Boxing Gym Manager</a>
    <img src="${contexto}/Images/Logo_Proyecto2.png" class="img_logo" alt="logo app">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navMenu">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="${contexto}/FrontController">Inicio</a></li>
      </ul>
    </div>
  </div>
</nav>


<div class="privacidad-container">
  <div class="card card-privacidad shadow-sm">
    <h2 class="text-center text-danger fw-bold">Error 404. Disculpe las molestias.</h2>
    <div class="d-flex justify-content-center">
      <img src="${contexto}/Images/Error/error404.png" class="w-50" alt="error 404"/>

    </div>



  </div>
</div>



<jsp:include page="/INC/footer.jsp"/>

</body>
</html>
