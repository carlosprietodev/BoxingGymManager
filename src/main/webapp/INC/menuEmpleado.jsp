<!-- Sidebar -->
    <nav id="sidebar" class="d-flex flex-column p-3">
        <ul class="nav nav-pills flex-column mb-auto">
            <li>
                <form method="post" action=" ${contexto}/EmpleadoController" class="w-100">
                    <div class="d-flex">
                        <i class='bx  bx-plus bx-sm icono'  > </i>
                        <button  type="submit" class="nav-link" name="accion" value="Nuevo">
                            Nuevo Cliente
                        </button>
                    </div>

                </form>

            </li>
            <li>
                <form method="post" action=" ${contexto}/EmpleadoController" class="w-100">
                    <div class="d-flex">
                        <i class='bx  bx-user-circle bx-sm icono'  ></i>
                        <button  type="submit" class="nav-link" name="accion" value="Ver">
                            Ver Clientes
                        </button>
                    </div>
                </form>

            </li>

            <li>
                <form method="post" action="${contexto}/EmpleadoController" class="w-100">
                    <div class="d-flex">
                        <i class='bx  bx-notification bx-sm icono'  ></i>
                        <button type="submit" name="accion" value="VerPeticiones" class="nav-link">
                            Peticiones Pendientes
                        </button>
                    </div>

                </form>

            </li>

            <li>
                <form method="post" action="${contexto}/EmpleadoController" class="w-100">
                    <div class="d-flex">
                        <i class='bx  bx-bar-chart-square bx-sm icono'  ></i>
                        <button type="submit" name="accion" value="VerEstadisticas" class="nav-link">
                            Ver Estad&iacute;sticas
                        </button>
                    </div>

                </form>

            </li>

            <li>
                <form method="post" action="${contexto}/EmpleadoController" class="w-100">
                    <div class="d-flex">
                        <i class='bx  bx-dumbbell bx-sm icono'  ></i>
                        <button type="submit" name="accion" value="VerEntrenamientos" class="nav-link">
                            Ver Entrenamientos
                        </button>
                    </div>

                </form>

            </li>



            <li>
                <form method="post" action="${contexto}/FrontController" class="w-100">
                    <div class="d-flex">
                        <i class="bi bi-box-arrow-right icono fs-3 me-3"></i>
                        <button type="submit" name="accion" value="Cerrar" class="btn btn-outline-danger w-50">
                            Cerrar Sesi&oacute;n
                        </button>
                    </div>

                </form>

            </li>


        </ul>
    </nav>



