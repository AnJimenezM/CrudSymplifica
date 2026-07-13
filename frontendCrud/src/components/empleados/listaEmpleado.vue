<script setup>

import { ref, onMounted } from "vue";
import { obtenerDatos } from "../../connection/api.js";


const emit = defineEmits(["detalle", "editar", "eliminar"]);
const empleados = ref([]);


async function cargarEmpleado() {
    empleados.value = await obtenerDatos();
    
}


onMounted(() => {
    cargarEmpleado();
});

defineExpose({
    cargarEmpleado
});
</script>

<template>

    <div class="bg-white shadow-lg overflow-hidden">
        <div class="overflow-x-auto">

            <table class="min-w-full w-full">

                <thead class="bg-blue-600 text-white">

                    <tr>

                        <th class="p-4">ID</th>
                        <th class="p-4">Nombre</th>
                        <th class="p-4">Apellido</th>
                        <th class="p-4">Ciudad</th>
                        <th class="p-4">Cargo</th>
                        <th class="p-4">Acciones</th>

                    </tr>

                </thead>

                <tbody>

                    <tr
                        v-for="empleado in empleados"
                        :key="empleado.id_empleado"
                        class="border-b hover:bg-gray-50">

                        <td class="p-4 text-center">{{ empleado.id_empleado }}</td>
                        <td class="p-4 text-center">{{ empleado.nombre }}</td>
                        <td class="p-4 text-center">{{ empleado.apellido }}</td>
                        <td class="p-4 text-center">{{ empleado.ciudad }}</td>
                        <td class="p-4  text-center">{{ empleado.posicion }}</td>

                        <td class="p-4 ">

                            <div class="flex gap-2 justify-center">

                                <button @click="$emit('detalle', empleado)"class="bg-blue-500 hover:bg-blue-600 text-white px-3 py-1 rounded cursor-pointer">detalle</button>

                                <button @click="$emit('editar', empleado)"class="bg-yellow-500 hover:bg-yellow-600 text-white px-3 py-1 rounded cursor-pointer">Editar</button>

                                <button @click="$emit('eliminar', empleado.id_empleado)"class="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded cursor-pointer">Eliminar</button>

                                

                            </div>



                        </td>

                    </tr>

                </tbody>

            </table>


        </div>

    </div>

</template>