<script setup>

import { ref, watch } from "vue";
import FormularioBeneficio from "./formularioBeneficio.vue";
import {obtenerBeneficiosEmpleado,crearBeneficio,eliminarBeneficio, actualizarBeneficio} from "@/connection/api";

const mostrarFormulario = ref(false);
const beneficioSeleccionado = ref(null);
const props = defineProps({
    empleado: Object
});

const beneficios = ref([]);

async function cargarBeneficios() {

    if (!props.empleado) return;

    beneficios.value = await obtenerBeneficiosEmpleado(
        props.empleado.id_empleado
    );

}

watch(

    () => props.empleado,

    async (nuevoEmpleado) => {

        if (!nuevoEmpleado) return;

        await cargarBeneficios();

    },

    { immediate: true }

);

async function guardarBeneficio(beneficio){

    if(beneficio.id_beneficio){

        await actualizarBeneficio(
            beneficio.id_beneficio,
            beneficio
        );

    }else{

        await crearBeneficio(beneficio);

    }

    beneficioSeleccionado.value=null;

    mostrarFormulario.value=false;

    await cargarBeneficios();

}

async function borrarBeneficio(id) {

    if (!confirm("¿Desea eliminar este beneficio?")) {
        return;
    }

    await eliminarBeneficio(id);

    await cargarBeneficios();

}

function editarBeneficio(beneficio){
    beneficioSeleccionado.value={...beneficio};
    mostrarFormulario.value=true
}

</script>

<template>

    <div class="mt-6">

        <h3 class="text-xl font-bold mb-4">Beneficios</h3>

        <div class="mb-4">

            <button @click="mostrarFormulario = true" class="bg-green-600 text-white px-4 py-2 rounded-lg cursor-pointer hover:bg-green-400">Agregar beneficio</button>

        </div>

        <div
            v-if="beneficios.length == 0"
            class="text-gray-500">

            Este empleado no tiene beneficios registrados.

        </div>

        <table
            v-else
            class="w-full border">

            <thead class="bg-gray-200">

                <tr>

                    <th class="p-2">Beneficio</th>

                    <th class="p-2">Monto</th>

                    <th class="p-2">Acciones</th>

                </tr>

            </thead>

            <tbody>

                <tr
                    v-for="beneficio in beneficios"
                    :key="beneficio.id_beneficio">

                    <td class="p-2">
                        {{beneficio.nombre_beneficio}}
                    </td>

                    <td class="p-2">
                        $ {{ beneficio.monto }}
                    </td>

                    <td class="p-2 flex gap-2">

                        <button @click="editarBeneficio(beneficio)" class="bg-yellow-500 hover:bg-yellow-600 text-white px-3 py-1 rounded cursor-pointer">Editar</button>

                        <button @click="borrarBeneficio(beneficio.id_beneficio)" class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded cursor-pointer">Eliminar</button>

                    </td>

                </tr>

            </tbody>

        </table>

    </div>

    <FormularioBeneficio
        v-if="mostrarFormulario"
        :empleado="props.empleado"
        :beneficio="beneficioSeleccionado"
        @cerrar="mostrarFormulario = false"
        @guardar="guardarBeneficio"/>

</template>