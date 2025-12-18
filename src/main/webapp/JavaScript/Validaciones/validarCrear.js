// SCRIPT DE VALIDACION PARA LA VISTA DE CREAR CLIENTE

let emailValido = false;
let dniValido = false;


function mostrarMensaje(campo, mensaje) {
    const mensajeDiv = document.getElementById("mensaje_" + campo.id.split('_')[0]);
    mensajeDiv.textContent = mensaje;
    mensajeDiv.style.color = "gray"; // Color neutro al principio
}

function actualizarMensaje(campo, valido) {
    const mensajeDiv = document.getElementById("mensaje_" + campo.id.split('_')[0]);
    if (valido) {
        mensajeDiv.style.color = "green";
    } else {
        mensajeDiv.style.color = "red";
    }

}

function validarNombre(nombre) {
    // Letras minúsculas y mayúsculas, incluyendo vocales acentuadas y la letra "ñ"
    // Espacios en blanco, Un mínimo de 3 caracteres y un máximo de 20
    let regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{3,20}$/;
    let valido = regex.test(nombre.value)
    if (valido) {
        nombre.classList.add("valido");
        nombre.classList.remove("no_valido");
    } else {
        nombre.classList.add("no_valido");
        nombre.classList.remove("valido");
    }



}


function validarApellidos(apellidos) {
    // Letras minúsculas y mayúsculas del alfabeto español, incluyendo vocales acentuadas y la letra "ñ"
    // Espacios en blanco para apellidos compuestos, guiones para apellidos como García-López, Un mínimo de 3 caracteres y un máximo de 30
    let regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s-]{3,30}$/;
    let valido = regex.test(apellidos.value)
    if (valido) {
        apellidos.classList.add("valido");
        apellidos.classList.remove("no_valido");
    } else {
        apellidos.classList.add("no_valido");
        apellidos.classList.remove("valido");
    }


}

function validarTelefono(telefono) {
    let regex = /^[6789]\d{8}$/;
    let valido = regex.test(telefono.value)
    if (valido) {
        telefono.classList.add("valido");
        telefono.classList.remove("no_valido");
    } else {
        telefono.classList.add("no_valido");
        telefono.classList.remove("valido");
    }

}
function validarLocalidad(input) {
    //   Permite letras mayúsculas y minúsculas, incluyendo acentos y la letra "ñ".
    // Permite espacios para nombres de localidades compuestos.
    // Requiere un mínimo de dos caracteres y no permite números ni caracteres especiales.

    let regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{2,30}$/;
    let valido = regex.test(input.value)
    if (valido) {
        input.classList.add("valido");
        input.classList.remove("no_valido");
    } else {
        input.classList.add("no_valido");
        input.classList.remove("valido");
    }

}

async function validarEmail(input_email) {
    let regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/;
    let valido = regex.test(input_email.value);

    // si cumple la regex llamo a comprobarEmail
    if(valido){
        input_email.classList.add("valido");
        input_email.classList.remove("no_valido");
        await comprobarEmail();

    }else{
        input_email.classList.add("no_valido");
        input_email.classList.remove("valido");
        emailValido = false;
    }

    checkElementos();
}

async function validarNIF(dni) {

    let regex = /^\d{8}[A-Z]$/;
    let valido = regex.test(dni.value);

    // si cumple la regex llamo a comprobarDni
    if (valido) {
        dni.classList.add("valido");
        dni.classList.remove("no_valido");
        await comprobarDni();
    } else {
        dni.classList.add("no_valido");
        dni.classList.remove("valido");
        dniValido= false;
    }

    checkElementos();
}

function validarSelect() {
    let select = document.getElementById("sel_entrenamiento_cliente");
    return select.value !== "";
}



const url = 'AjaxController';

// Función para habilitar/deshabilitar el botón de crear cliente
//function validarEmailDni() {
   // const btnCrearCliente = document.getElementById('btn_crear');
   // if (emailValido) {
 //    } else {
    //    btnCrearCliente.setAttribute('disabled', 'disabled');
  //  }
//}

// Función asincrónica para validar el email, la cual hace una peticion al servlet Ajax de Java
async function comprobarEmail() {
    const input_email = document.getElementById('email_cliente');
    const data = new URLSearchParams();
    data.append("accion", "verEmail");
    const valorEmail = input_email.value.trim(); // Guardamos el valor sin espacios

    const alerta = document.getElementById("alerta");
    // Solo hacemos el fetch por ajax si el email no está vacío
    if (valorEmail !== "") {
        data.append("email", valorEmail);

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Accept-Charset': 'utf-8'
                },
                body: data.toString()
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const respuestaJson = await response.json();
            console.log("Respuesta del servidor:", respuestaJson);



            // Validar si el email está disponible
            if (respuestaJson.disponible === "no") {
                alerta.style.display = 'block';
                alerta.innerHTML = "";
                alerta.innerHTML = "Ya existe un cliente con ese Email.";
                input_email.classList.add("no_valido");
                emailValido = false; // Email no válido
                setTimeout(() => {
                    alerta.style.display = 'none';
                }, 5000);
            } else {
                input_email.classList.remove("no_valido");
                input_email.classList.add("valido");
                alerta.style.display = 'none';
                emailValido = true; // El email es válido
            }

        } catch (error) {
            console.error("Error en la validación del email:", error);
        }
    } else {
        // Si el email está vacío, lo ponemos a false
        emailValido = false;
        input_email.classList.add("no_valido");
        alerta.style.display = 'block';
        alerta.innerHTML = "";
        alerta.innerHTML = "El Email no puede estar vacío.";
        setTimeout(() => {
            alerta.style.display = 'none';
        }, 5000);
    }


}

// Función asincrónica para validar el dni del cliente, la cual hace una peticion al servlet AjaxController de Java
async function comprobarDni() {
    const input_dni = document.getElementById('dni_cliente');
    const data = new URLSearchParams();
    data.append("accion", "verDni");
    const valorDni = input_dni.value.trim(); // Guardamos el valor sin espacios

    const alerta = document.getElementById("alerta");
    // Solo hacemos el fetch por ajax si el email no está vacío
    if (valorDni !== "") {
        data.append("dni", valorDni);

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Accept-Charset': 'utf-8'
                },
                body: data.toString()
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const respuestaJson = await response.json();
            console.log("Respuesta del servidor:", respuestaJson);

            // Validar si el dni está disponible
            if (respuestaJson.disponible === "no") {
                alerta.style.display = 'block';
                alerta.innerHTML = "";
                alerta.innerHTML = "Ya existe un cliente con ese Dni.";
                input_dni.classList.add("no_valido");
                dniValido = false; // dni no válido
                setTimeout(() => {
                    alerta.style.display = 'none';
                }, 5000);
            } else {
                input_dni.classList.remove("no_valido");
                input_dni.classList.add("valido");
                alerta.style.display = 'none';
                dniValido = true; // El email es válido
            }

        } catch (error) {
            console.error("Error en la validación del Dni:", error);
        }
    } else {
        // Si el email está vacío, lo ponemos a false
        dniValido = false;
        input_dni.classList.add("no_valido");
        alerta.style.display = 'block';
        alerta.innerHTML = "";
        alerta.innerHTML = "El Dni no puede estar vacío.";
        setTimeout(() => {
            alerta.style.display = 'none';
        }, 5000);
    }
}

// funcion para comprobar todos los elementos del formulario
function checkElementos(){
    let form_cliente = document.getElementById("form_cliente");
    const inputs = Array.from(form_cliente.querySelectorAll("input"));

    let todosValidos = true;

    // Validar inputs con clase .valido
    inputs.forEach((input)=>{
        if (!input.classList.contains("valido")) {
            todosValidos = false;
        }
    });

    // Validar select
    if (!validarSelect() || !emailValido || !dniValido) {
        todosValidos = false;
    }

    let btn_crear = document.getElementById("btn_crear");
    btn_crear.disabled = !todosValidos;
}





// eventos que se aplican cuando todo el DOM de la pagina este cargado
document.addEventListener("DOMContentLoaded", function (){
    // Ejecutar la validación cada vez que el usuario escriba o borre
    //document.getElementById("email_cliente").addEventListener("blur", comprobarEmail);
    //document.getElementById("dni_cliente").addEventListener("blur", comprobarDni);

    document.getElementById("nombre_cliente").addEventListener("focus", (event) => {
        mostrarMensaje(event.target, "El nombre debe contener entre 3-20 caracteres, únicamente letras y puede incluir espacios y acentos.");
    });
    document.getElementById("nombre_cliente").addEventListener("input", (event) => {
        validarNombre(event.target);
        checkElementos();
    });

    document.getElementById("apellidos_cliente").addEventListener("focus", (event) => {
        mostrarMensaje(event.target, " Los apellidos deben contener entre 3-20 caracteres, únicamente letras y puede incluir espacios y acentos.");
    });
    document.getElementById("apellidos_cliente").addEventListener("input", (event) => {
        validarApellidos(event.target);
        checkElementos();
    });

    document.getElementById("email_cliente").addEventListener("focus", (event) => {
        mostrarMensaje(event.target, "El Email debe contener un @ y una única extensión.");
    });
    document.getElementById("email_cliente").addEventListener("input", async (event) => {
        await validarEmail(event.target);
    });

    document.getElementById("dni_cliente").addEventListener("focus", (event) => {
        mostrarMensaje(event.target, "El dni debe contener 8 dígitos y una letra.");
    });
    document.getElementById("dni_cliente").addEventListener("input", async (event) => {
        await validarNIF(event.target);
    });
    document.getElementById("telefono_cliente").addEventListener("focus", (event) => {
        mostrarMensaje(event.target, "El teléfono debe comenzar por 6,7,8 o 9 y contener 9 dígitos. ");
    });
    document.getElementById("telefono_cliente").addEventListener("input", (event) => {
        validarTelefono(event.target);
        checkElementos();
    });

    document.getElementById("localidad_cliente").addEventListener("focus", (event) => {
        mostrarMensaje(event.target, "La localidad debe contener entre 2 y 30 caracteres, únicamente letras y puede incluir espacios y acentos.");
    });
    document.getElementById("localidad_cliente").addEventListener("input", (event) => {
        validarLocalidad(event.target);
        checkElementos();
    });

    document.getElementById("sel_entrenamiento_cliente").addEventListener("change", () => {
        checkElementos();
    });
})