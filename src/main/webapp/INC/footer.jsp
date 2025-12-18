<!-- Footer -->
<footer class=" text-white py-4 ">
    <div class="container ">
        <div class="row text-center text-md-start justify-content-center">
            <!-- Contacto -->
            <div class="col-12 col-md-4 mb-4 mb-md-0" aria-labelledby="contact-info">
                <p id="contact-info " class=" fs-5 fw-semibold mb-3 text-center">Cont&aacute;ctanos</p>
                <p class="fs-6 text-center estilo_footer">
                    Email: boxinggymmanager@gmail.com
                </p>
                <p class="fs-6 text-center estilo_footer">
                    Tel&eacute;fono: +34 123 456 789
                </p>
            </div>

            <!-- Redes Sociales -->
            <div class="col-12 col-md-4 mb-4 mb-md-0" aria-labelledby="social-media">
                <p id="social-media" class="fs-5 fw-semibold mb-3 text-center">S&iacute;guenos en:</p>
                <div class="d-flex justify-content-center justify-content-md-center gap-3">
                    <a href="https://instagram.com" target="_blank" aria-label="Instagram">
                        <img src="${contexto}/Images/icons8-instagram.svg" alt="Instagram" class="img-fluid" style="width: 32px; transition: transform 0.2s;" onmouseover="this.style.transform='scale(1.1)'" onmouseout="this.style.transform='scale(1)'" />
                    </a>
                    <a href="https://facebook.com" target="_blank" aria-label="Facebook">
                        <img src="${contexto}/Images/icono_face.svg" alt="Instagram" class="img-fluid" style="width: 32px; transition: transform 0.2s;" onmouseover="this.style.transform='scale(1.1)'" onmouseout="this.style.transform='scale(1)'" />
                    </a>
                    <a href="https://x.com" target="_blank" aria-label="Red Social X">
                        <img src="${contexto}/Images/x.svg" alt="Instagram" class="img-fluid" style="width: 32px; transition: transform 0.2s;" onmouseover="this.style.transform='scale(1.1)'" onmouseout="this.style.transform='scale(1)'" />
                    </a>


                </div>
            </div>

            <!-- Enlaces legales -->
            <div class="col-12 col-md-4" aria-labelledby="legal-links">
                <p id="legal-links" class="fs-5 fw-semibold mb-3 text-center">Legal</p>
                <ul class="list-unstyled">
                    <li>
                        <form method="post" action="${contexto}/FrontController" class="d-flex justify-content-center">
                            <div class="d-flex- justify-content-center">
                            <button type="submit" class="text-white d-block mb-2 estilo_footer" name="accion" value="Privacidad">Aviso Legal</button>
                            </div>
                        </form>

                    </li>
                    <li>
                        <form method="post" action="${contexto}/FrontController" class="d-flex justify-content-center">
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="text-white  d-block mb-2 estilo_footer" name="accion" value="Privacidad">Pol&iacute;tica de Privacidad</button>
                            </div>

                        </form>

                    </li>
                </ul>
            </div>
        </div>

        <!-- Derechos reservados -->
        <div class="row mt-2">
            <div class="col text-center">
                <p class="mb-0">&copy; 2025 Boxing Gym Manager. Todos los derechos reservados.</p>
            </div>
        </div>
    </div>
</footer>

