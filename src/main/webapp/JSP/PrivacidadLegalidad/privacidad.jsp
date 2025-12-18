<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">

<head>
  <jsp:include page="/INC/cabecera.jsp">
    <jsp:param name="titulo" value="Política de Privacidad" />
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

      <form style="margin-top: 18px" method="post" action= "${contexto}/FrontController">
        <button type="submit" name="accion" value="Iniciar" class="btn btn_rojo">Portal Empleados</button>
      </form>
    </div>
  </div>
</nav>

<!-- Contenido Principal -->
<div class="privacidad-container">
  <div class="card card-privacidad shadow-sm">
    <h2 class="text-center">Política de Privacidad, Aviso Legal y Cookies</h2>
    <p>
      <strong>1. Política de cookies:</strong> Boxing Gym Manager no utiliza cookies para recoger información de los usuarios.
      Únicamente se utilizan cookies propias, de sesión, con finalidad técnica (aquellas que permiten al usuario la navegación a través del sitio web y la utilización de las diferentes opciones y servicios que en ella existen).
    </p>
    <p>
      <strong>2. Política de privacidad:</strong> A través de este sitio web no se recaban datos de carácter personal de los usuarios sin su conocimiento, ni se ceden a terceros.
      Boxing Gym Manager no utiliza cookies para recoger informaci&oacute;n de los usuarios, ni registra las direcciones IP de acceso. Únicamente se utilizan cookies propias,  de sesión, con finalidad técnica (aquellas que permiten al usuario la navegación a través del sitio web y la utilización de las diferentes opciones y servicios que en ella existen).
      Boxing Gym Manager contiene enlaces a sitios web de terceros, cuyas políticas de privacidad son ajenas a la de Boxing Gym Manager. Al acceder a tales sitios web usted puede decidir si acepta sus políticas de privacidad y de cookies. Con carácter general, si navega por internet usted puede aceptar o rechazar las cookies de terceros desde las opciones de configuración de su navegador.
      <br>
      2.1 Información básica sobre protección de datos A continuación le informamos sobre la política de protección de datos de Boxing Gym Manager Responsable del tratamiento: Los datos de carácter personal que se pudieran recabar directamente del interesado serán tratados de forma confidencial y quedarán incorporados a la correspondiente actividad de tratamiento titularidad de Boxing Gym Manager.
      <br>
      2.2 Finalidad La finalidad del tratamiento de los datos corresponde a cada una de las actividades de tratamiento que realiza Boxing Gym Manager.
      <br>
      2.3 Legitimación El tratamiento de sus datos se realiza para el cumplimiento de obligaciones legales por parte de Boxing Gym Manager, para el cumplimiento de misiones realizada en interés público o en el ejercicio de poderes públicos conferidos a Boxing Gym Manager, así como cuando la finalidad del tratamiento requiera su consentimiento, que habrá de ser prestado mediante una clara acción afirmativa.
      <br>
      2.4 Conservación de datos Los datos personales proporcionados se conservarán durante el tiempo necesario para cumplir con la finalidad para la que se recaban y para determinar las posibles responsabilidades que se pudieran derivar de la finalidad, además de los períodos establecidos en la normativa de archivos y documentación.
      <br>
      2.5 Comunicación de datos Con carácter general no se comunicarán los datos personales a terceros, salvo obligación legal, entre las que pueden estar las comunicaciones al Defensor del Pueblo, Jueces y Tribunales, interesados en los procedimientos relacionados con la reclamaciones presentadas.
      <br>
      2.6 Derechos de los interesados Cualquier persona tiene derecho a obtener confirmación sobre los tratamientos que de sus datos que se llevan a cabo por Boxing Gym Manager. Puede ejercer sus derechos de acceso, rectificación, supresión y portabilidad de sus datos, de limitación y oposición a su tratamiento, así como a no ser objeto de decisiones basadas únicamente en el tratamiento automatizado de sus datos, cuando procedan, ante: Boxing Gym Manager,Tel&eacute;fono:  +34 123 456 789, e-mail: boxinggymmanager@gmail.com
    </p>

    <p>
      <strong> 3. Aviso legal</strong>
      <br>
      3.1 Propiedad intelectual e industrial El diseño del portal y sus códigos fuente, así como los logos, marcas y demás signos distintivos que aparecen en el mismo pertenecen a Boxing Gym Manager y están protegidos por los correspondientes derechos de propiedad intelectual e industrial.
      <br>
      3.2 Responsabilidad de los contenidos Boxing Gym Manger no se hace responsable de la legalidad de otros sitios web de terceros desde los que pueda accederse al portal. Boxing Gym Manager tampoco responde por la legalidad de otros sitios web de terceros, que pudieran estar vinculados o enlazados desde este portal.
      Boxing Gym Manager se reserva el derecho a realizar cambios en el sitio web sin previo aviso, al objeto de mantener actualizada su información, añadiendo, modificando, corrigiendo o eliminando  los contenidos publicados o el diseño del portal. Boxing Gym Manager no será responsable del uso que terceros hagan de la información publicada en el portal, ni tampoco de los daños sufridos o pérdidas económicas que, de forma directa o indirecta, produzcan o puedan producir perjuicios económicos, materiales o sobre datos, provocados por el uso de dicha información.
      <br>
      3.3 Reproducción de contenidos Se prohíbe la reproducción total o parcial de los contenidos publicados en el portal. No obstante, los contenidos que sean considerados como datos abiertos en la Sede Electrónica, publicados según lo previsto en el Real Decreto 1495/2011, de 24 de octubre, de desarrollo de la Ley 37/2007, sobre reutilización de la información del sector público, para el ámbito del sector público estatal, podrán ser objeto de reproducción.
      <br>
      3.4 Transparencia A través de la información publicada en el portal de transparencia, Boxing Gym Manager atiende de forma periódica y actualizada el principio de publicidad activa establecido por la Ley 19/2013, de 9 de diciembre, de transparencia, acceso a la información pública y buen gobierno, con los mecanismos adecuados para facilitar la accesibilidad, la interoperabilidad, la calidad y la reutilización de la información, así como su identificación y localización.
      <br>
      3.5 Ley aplicable La ley aplicable en caso de disputa o conflicto de interpretación de los términos que conforman este aviso legal, así como cualquier cuestión relacionada con los servicios del presente portal, será la ley española.
    </p>


  </div>
</div>

<jsp:include page="/INC/footer.jsp"/>



</body>
</html>

