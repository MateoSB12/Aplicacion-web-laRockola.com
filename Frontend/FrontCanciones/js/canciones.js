const url = "http://localhost:8080/WebApplication_laRockola/api/canciones"
const contenedor = document.querySelector('tbody')
let resultados = ''

const modalCanciones = new bootstrap.Modal(document.getElementById('modalCancion'))
const formCanciones = document.querySelector('form')
const nombreCancion = document.getElementById('nombre')
const idAlbum = document.getElementById('id_album')
const duracion = document.getElementById('duracion')
const idCancion = document.getElementById('id')

let opcion = ''

btnCrear.addEventListener('click', () => {
    
    nombreCancion.value = ''
    idAlbum.value = ''
    duracion.value = ''
    idCancion.value = ''
    idCancion.disabled = false
    modalCanciones.show()
    opcion = 'crear'
})


const ajax = (options) => {
    let { url, method, success, error, data } = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", (e) => {
        if (xhr.readyState !== 4) return;

        if (xhr.status >= 200 && xhr.status < 300) {
            let json = JSON.parse(xhr.responseText);
            success(json);
        } else {
            let message = xhr.statusText || "Ocurrió un error";
            error(`Error ${xhr.status}: ${message}`);
        }
    });

    xhr.open(method || "GET", url);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
};

const getAll = () => {
    ajax({
        url: url,
        method: "GET",
        success: (res) => {
            console.log(res);

            res.forEach((canciones) => {
                resultados += `<tr>
                        <td width="10%">${canciones.id_can}</td>
                        <td width="15%">${canciones.nombre_can}</td>
                        <td width="25%">${canciones.id_alb}</td>
                        <td width="15%">${canciones.duracion_can}</td>
                        <td class="text-center" width="20%"><a class="btnEditar btn btn-primary">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td>
                    </tr>`
            });

            contenedor.innerHTML = resultados
        },
        error: (err) => {
            console.log(err);
            $table.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`);
        },
    });
};

document.addEventListener("DOMContentLoaded", getAll);

document.addEventListener("click", (e) => {

    if (e.target.matches(".btnBorrar")) {
        const fila = e.target.parentNode.parentNode
        const id = fila.firstElementChild.innerHTML
        console.log(id)
        alertify.confirm(`¿Estás seguro de eliminar el id ${id}?`,
            function () {
                ajax({
                    url: url + "/" + id,
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    success: (res) => location.reload(),
                    error: (err) => alert(err),
                });
                alertify.success('Registro eliminado')
            },
            function () {
                alertify.error('Cancel')
            });



    }
    if (e.target.matches(".btnEditar")) {
        const fila = e.target.parentNode.parentNode
        idCancion.value = fila.children[0].innerHTML
        nombreCancion.value = fila.children[1].innerHTML
        idAlbum.value = fila.children[2].innerHTML
        duracion.value = fila.children[3].innerHTML
        idCancion.disabled = true
        opcion = 'editar'
        modalCanciones.show()
    }
})


formCanciones.addEventListener('submit', (e) => {
    console.log("Metodo")
    e.preventDefault();
    let metodo = "POST"
    if (opcion == 'editar') {
        metodo = "PUT"

    }
    console.log("Entro en submit")
    ajax({
        url: url,
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => location.reload(),
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: {
            "duracion_can": duracion.value,
            "id_alb": idAlbum.value,
            "id_can": idCancion.value,
            "nombre_can": nombreCancion.value,
        },
    });
    modalCanciones.hide()
})



