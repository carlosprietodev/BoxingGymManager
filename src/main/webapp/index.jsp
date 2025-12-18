
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="estilo" value="${contexto}/CSS/style.css" scope="application"/>
<html>

<head>
    <jsp:include page="/INC/cabecera.jsp">
        <jsp:param name="titulo" value="Boxing Gym Manager" />
        <jsp:param name="estilo" value="${estilo}" />
    </jsp:include>
    <script type="module" src="${contexto}/JavaScript/Validaciones/validacion.js"></script>
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
                <li class="nav-item"><a class="nav-link" href="#">Inicio</a></li>
                <li class="nav-item"><a class="nav-link" href="#trainings">Entrenamientos</a></li>
                <li class="nav-item"><a class="nav-link" href="#contact">Contacto</a></li>
            </ul>

            <form style="margin-top: 18px" method="post" action= "${contexto}/FrontController">
                <button type="submit" name="accion" value="Iniciar" class="btn btn_rojo">Portal Empleados</button>
             </form>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section class="hero">
    <div class="content">
        <h1 class="display-4">¡Lleva tu boxeo al siguiente nivel!</h1>
        <p class="lead">Descubre nuestros entrenamientos y contáctanos para empezar.</p>
        <a href="#contact" class="btn btn_rojo btn-lg">Empieza ahora</a>
    </div>
</section>

<!-- Entrenamientos Disponibles -->
<section id="trainings" class="section-surface py-5">
    <div class="container">
        <h2 class="mb-5 text-center fw-bold text-danger">Entrenamientos Disponibles</h2>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
            <div class="col">
                <div class="card h-100 shadow-sm border-0 card-hover">
                    <img src="${contexto}/Images/Boxeo/basico.jpg" class="card-img-top img-fluid rounded-top" alt="Entrenamiento Básico">
                    <div class="card-body">
                        <h5 class="card-title text-center text-danger fw-semibold">Entrenamiento Básico</h5>
                        <p class="card-text text-muted small">
                            Enfocado en aprender los fundamentos sin contacto, con énfasis en desplazamientos, posiciones y movimientos básicos en un entorno seguro y progresivo.
                        </p>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100 shadow-sm border-0 card-hover">
                    <img src="${contexto}/Images/Boxeo/contacto.jpg" class="card-img-top img-fluid rounded-top" alt="Contacto Dirigido">
                    <div class="card-body">
                        <h5 class="card-title text-center text-danger fw-semibold">Contacto Dirigido</h5>
                        <p class="card-text text-muted small">
                            Para personas de nivel medio y avanzado que buscan mejorar habilidades de combate, reflejos y estrategias de ataque y defensa en un entorno supervisado.
                        </p>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100 shadow-sm border-0 card-hover">
                    <img src="${contexto}/Images/Boxeo/cardiobox.jpg" class="card-img-top img-fluid rounded-top" alt="CardioBox">
                    <div class="card-body">
                        <h5 class="card-title text-center text-danger fw-semibold">CardioBox</h5>
                        <p class="card-text text-muted small">
                            Sesiones enfocadas en mejorar la resistencia cardiovascular y tonificar el cuerpo mediante ejercicios de velocidad y trabajo en saco, aptas para todos los niveles y con un enfoque divertido y energético.
                        </p>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100 shadow-sm border-0 card-hover">
                    <img src="${contexto}/Images/Boxeo/tecnico.jpg"  class="card-img-top img-fluid rounded-top" alt="Técnico Especializado">
                    <div class="card-body">
                        <h5 class="card-title text-center text-danger fw-semibold">Técnico Especializado</h5>
                        <p class="card-text text-muted small">
                            Rutinas para mejorar la agilidad de pies, combinaciones avanzadas y técnica de golpeo, que pueden incluir trabajo con manoplas y sparring controlado.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- Formulario de Contacto -->
<section id="contact" class="py-5">
    <div class="container">
        <h1 class="mb-4 text-center" style="color: var(--color-surface)">Formulario de Contacto</h1>
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-6">
                <form method="post" action="${contexto}/PeticionController" id="form_contacto" class="formulario_contacto">
                    <div class="mb-3">
                        <label for="input_nombre" class="form-label estilo_label">Nombre:</label>
                        <input type="text" class="form-control" id="input_nombre" name="nombre" placeholder="Tu nombre">
                        <div id="mensaje_nombre" class="form-text"></div>
                    </div>

                    <div class="mb-3">
                        <label for="input_email" class="form-label estilo_label">Email:</label>
                        <input type="email" class="form-control" id="input_email" name="email" placeholder="tu@correo.com">
                        <div id="mensaje_email" class="form-text"></div>
                    </div>
                    <div class="mb-3">
                        <label for="input_telefono" class="form-label estilo_label">Teléfono:</label>
                        <input type="text" class="form-control" id="input_telefono" name="telefono">
                        <div id="mensaje_telefono" class="form-text"></div>
                    </div>

                    <div class="mb-3">
                        <label for="sel_entrenamiento" class="form-label estilo_label">Entrenamiento deseado:</label>
                        <select class="form-select" id="sel_entrenamiento" name="entrenamiento" >
                            <option value = "" disabled selected>Selecciona una opci&oacute;n</option>
                            <option value="BASICO">B&aacute;sico</option>
                            <option value="CONTACTO_DIRIGIDO">Contacto Dirigido</option>
                            <option value="CARDIOBOX">CardioBox</option>
                            <option value="TECNICO_ESPECIALIZADO">T&eacute;cnico Especializado</option>

                        </select>
                    </div>

                    <div class="form-check mb-3">
                        <input class="form-check-input" type="checkbox" id="checkbox_terminos">
                        <label class="form-check-label text-white" for="checkbox_terminos">
                            Acepto los t&eacute;rminos de uso y pol&iacute;tica de privacidad del sitio.
                        </label>
                    </div>


                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn_rojo w-50" disabled id="btn_enviar" name="accion" value="Enviar">Enviar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="/INC/footer.jsp"/>


</body>
</html>