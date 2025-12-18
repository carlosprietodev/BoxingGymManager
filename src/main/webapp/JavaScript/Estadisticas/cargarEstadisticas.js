// SCRIPT PARA CARGAR LAS ESTADISTICAS MEDIANTE AJAX AL CONTROLADOR EstadisticasAjax
const url = "EstadisticasAjax";

async function obtenerClientesEntrenamiento() {
    const parametros = new URLSearchParams();
    parametros.append("accion", "clientesEntrenamiento");
    try {
        let response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept-Charset': 'utf-8'
            },
            body: parametros.toString()

        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        let data = await response.json();

        const ctx = document.getElementById('graficoPieEntrenamiento').getContext('2d');
        new Chart(ctx, {
            type: 'pie',
            data: {
                labels: data.labels,
                datasets: [{
                    data: data.valores,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)'
                    ],
                    borderColor: 'white',
                    borderWidth: 2
                }]
            },
            options: {
                responsive: false,
                plugins: {
                    legend: {
                        position: 'right'
                    },
                    title: {
                        display: true,
                        text: 'Gráfico de Clientes por Entrenamiento'
                    }
                }
            }
        });

        console.log("Clientes por Entrenamiento obtenidos del servidor:",data);

    } catch (error) {
        console.log("Error al obtener los clientes por entrenamiento", error);
    }
}


async function obtenerClientesLocalidad() {
    const parametros = new URLSearchParams();
    parametros.append("accion", "clientesLocalidad");

    try {
        let response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept-Charset': 'utf-8'
            },
            body: parametros.toString()
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        let data = await response.json();
        const ctx = document.getElementById('graficoLocalidad').getContext('2d');

        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: data.labels,
                datasets: [{
                    label: 'Clientes por Localidad',
                    data: data.valores,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)'
                    ],
                    borderColor: 'white',
                    borderWidth: 2
                }]
            },
            options: {
                responsive: false,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                plugins: {
                    legend: {
                        display: true
                    },
                    title: {
                        display: true,
                        text: 'Gráfico de Clientes por Localidad'
                    }
                }
            }
        });

        console.log("Clientes por Localidad obtenidos del servidor:", data);

    } catch (error) {
        console.log("Error al obtener los clientes por localidad", error);
    }
}


document.addEventListener("DOMContentLoaded", async function () {
    await obtenerClientesEntrenamiento();
    await obtenerClientesLocalidad();
})