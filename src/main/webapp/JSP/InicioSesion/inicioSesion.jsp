<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">

<head>
  <jsp:include page="/INC/cabecera.jsp">
    <jsp:param name="titulo" value="Inicio Sesi&oacute;n" />
    <jsp:param name="estilo" value="${estilo}" />
  </jsp:include>
  <script type="module" src="${contexto}/JavaScript/Validaciones/validarLogin.js"></script>

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

<!-- Inicio Sesion-->
<section id="login" class="py-4 d-flex align-items-center">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-12 col-md-8 col-lg-6">
        <div class="card bg-dark text-white p-4">
          <div class="card-body">
            <h3 class="card-title text-center mb-4" style="color: var(--color-surface)">Iniciar Sesión</h3>
            <form method="post" action="${contexto}/FrontController" id="form_login" class="form_inicio">

              <div class="mb-3 text-center">
                <label for="email_empleado" class="form-label estilo_label">Email:</label>
                <div class="d-flex justify-content-center">
                  <input type="text"
                         class="input_login"
                         id="email_empleado"
                         name="email_login"
                         placeholder="Introduce tu Email">
                </div>
              </div>

              <div class="mb-3 text-center">
                <label for="password_empleado" class="form-label estilo_label">Contraseña:</label>
                <div class="d-flex justify-content-center">
                  <input type="password"
                         class="input_login"
                         id="password_empleado"
                         name="password_login"
                         placeholder="Introduce tu Contraseña">
                </div>
              </div>

              <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn_rojo w-25" id="btn_login" name="accion" value="Entrar" disabled>
                  Entrar
                </button>
              </div>

              <div class="text-center mt-3 aviso">
                <p>${requestScope.aviso}</p>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Alerta oculta  -->
<div id="alerta" class="alert alert-warning w-50 text-center position-fixed top-0 start-50 translate-middle-x"
     style="display: none; margin-top: 50px; width: 50px" role="alert">


</div>

<jsp:include page="/INC/footer.jsp"/>

</body>
</html>
