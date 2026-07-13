

const url = "http://localhost:8080"

export async function obtenerDatos() {
    const response = await fetch(`${url}/employees`)
    return await response.json();
}

export async function crearEmpleado(empleado) {
    const response = await fetch(`${url}/employees`,
        {
            method: "POST",
            headers:{
                "Content-Type": "application/json"
            }, body: JSON.stringify(empleado)
        });
        return await response.json();
}

export async function actualizarEmpleado(id, empleado) {
    const response = await fetch(`${url}/employees/${id}`,{
        method: "PUT",
        headers:{
            
            "Content-Type": "application/json"
        }, body: JSON.stringify(empleado)
    });
    return await response.json()
}
export async function eliminarEmpleado(id) {
    await fetch(`${url}/employees/${id}`,
        {
            method: "DELETE"
        });
}

export async function obtenerUbicacion(ciudad) {
    const response = await fetch(`${url}/employees/ubicacion/${ciudad}`);
    return await response.json();
}



export async function obtenerBeneficiosEmpleado(id) {

    const response = await fetch(`${url}/beneficios/employees/${id}`);

    return await response.json();

}


export async function crearBeneficio(beneficio){

    const response = await fetch(`${url}/beneficios`,{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body: JSON.stringify(beneficio)

    });

    return await response.json();

}


export async function eliminarBeneficio(id) {

    await fetch(`${url}/beneficios/${id}`, {

        method: "DELETE"

    });

}

export async function actualizarBeneficio(id, beneficio){

    const response = await fetch(`${url}/beneficios/${id}`,{

        method:"PUT",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(beneficio)

    });

    return await response.json();

}