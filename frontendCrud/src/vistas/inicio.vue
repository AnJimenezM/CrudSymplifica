<script setup>
import { ref } from 'vue'; 
import listaEmpleado from '../components/empleados/listaEmpleado.vue';
import FormularioEmpleado from '../components/empleados/formularioEmpleado.vue';
import detalleEmpleado from '../components/empleados/detalleEmpleado.vue';
import { crearEmpleado, actualizarEmpleado, eliminarEmpleado as eliminarRegistro } from '../connection/api.js'; 


const listaRef = ref(null);
const mostrarFormulario = ref(false);

const mostrarDetalle = ref(false);
const empleadoDetalle = ref(null);
const empleadoSeleccionado = ref(null);

function editarEmpleado(empleado){
    empleadoSeleccionado.value = empleado;
    mostrarFormulario.value = true;
}

async function guardarEmpleado(empleado) {
    try {
        if (empleado.id_empleado){
            await actualizarEmpleado(empleado.id_empleado, empleado)
        } else{
            await crearEmpleado(empleado);
        }
        

        mostrarFormulario.value = false;
        empleadoSeleccionado.value = null;
        listaRef.value.cargarEmpleado();

    } catch (error) {
        console.error(error);
    }
}

async function eliminar(id) {
    const confirmacion = confirm("Deseas eliminar el empleado?");

    if (!confirmacion) return;
    try {
        await eliminarRegistro(id)
        listaRef.value.cargarEmpleado();
    } catch (error) {
        console.error(error)
    }
}

function verEmpleado(empleado){
    empleadoDetalle.value = empleado;
    mostrarDetalle.value = true;
}
</script>

<template>

    <div class="bg-yellow-500">
        <div class="flex justify-between items-center">

            <h1 class="text-3xl font-bold text-gray-600">Lista de empleados</h1>
            <button @click="mostrarFormulario = true" class="bg-blue-700 text-white px-8 py-4 hover:bg-blue-800 cursor-pointer h-18">Crear empleado</button>

        </div>
        
        <listaEmpleado ref="listaRef"
        @detalle="verEmpleado"
        @editar="editarEmpleado"
        @eliminar = "eliminar"/>
        <formularioEmpleado
        v-if = "mostrarFormulario"
        :empleado="empleadoSeleccionado"
        @cerrar="mostrarFormulario = false"
        @guardar="guardarEmpleado" />

        <detalleEmpleado
        v-if="mostrarDetalle"
        :empleado="empleadoDetalle"
        @cerrar="mostrarDetalle = false "/>

    </div>



</template>
