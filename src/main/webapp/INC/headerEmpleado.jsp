<%--<nav class="navbar navbar-expand-lg">--%>
<%--  <div class="container ">--%>
<%--    <a class="navbar-brand" href="${contexto}/EmpleadoController">Boxing Gym Manager</a>--%>
<%--    <img src="${contexto}/Images/Logo_Proyecto2.png" class="img_logo" alt="logo app">--%>
<%--    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">--%>
<%--      <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>

<%--    <div class="collapse navbar-collapse" id="navMenu">--%>
<%--      <ul class="navbar-nav ms-auto">--%>
<%--        <img src="${contexto}/Images/perfil.png" class="perfil_img" alt="Imagen Perfil"/>--%>
<%--        <p class="texto_perfil">Bienvenido, ${sessionScope.empleado.nombre} (Empleado)</p>--%>
<%--      </ul>--%>


<%--    </div>--%>
<%--  </div>--%>
<%--</nav>--%>

<nav class="navbar navbar-expand-lg bg-dark shadow-sm">
  <div class="container-fluid px-4">
    <!-- Logo e inicio -->
    <a class="navbar-brand d-flex align-items-center" href="${contexto}/EmpleadoController">
      <img src="${contexto}/Images/Logo_Proyecto2.png" alt="Logo" width="70" height="70" class="d-inline-block align-text-top me-2">
      <span class="fs-5 fw-semibold">Boxing Gym Manager</span>
    </a>

    <!-- Botón toggle para responsive -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu" aria-controls="navMenu" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Contenido colapsable -->
    <div class="collapse navbar-collapse justify-content-end" id="navMenu">
      <ul class="navbar-nav align-items-center">
        <li class="nav-item me-2">
          <img src="${contexto}/Images/perfil.png" alt="Perfil" class="rounded-circle shadow-sm" width="36" height="36">
        </li>
        <li class="nav-item">
          <span class="navbar-text text-white small">
            Bienvenido, <strong>${sessionScope.empleado.nombre}</strong> (Empleado)
          </span>
        </li>
      </ul>
    </div>
  </div>
</nav>
