<script setup>
import {ref, watch} from 'vue';
import { obtenerUbicacion } from '../../connection/api.js';
import ListaBeneficio from '../beneficios/listaBeneficio.vue';



const props = defineProps({
    empleado: Object
});

const ubicacion = ref(null);

const emit = defineEmits(["cerrar"]);

watch(
    () => props.empleado, async (nuevoEmpleado) =>{
        if(!nuevoEmpleado) return;
        const datos = await obtenerUbicacion(nuevoEmpleado.ciudad);
        if (datos.length > 0){
            ubicacion.value = datos[0]
        }
    }, {immediate: true}
)

</script>

<template>

    <div class="fixed inset-0 bg-black/50 flex justify-center items-center">

        <div class="bg-white rounded-xl p-8 w-600px">

            <h2 class="text-2xl font-bold mb-6">Detalle del empleado</h2>

            <div class="space-y-3">

                <p><strong>Nombre:</strong> {{ empleado.nombre }}</p>
                <p><strong>Apellido:</strong> {{ empleado.apellido }}</p>
                <p><strong>Correo:</strong> {{ empleado.correo }}</p>
                <p><strong>Ciudad:</strong> {{ empleado.ciudad }}</p>
                <p><strong>Cargo:</strong> {{ empleado.posicion }}</p>

                <hr class="my-4">
                <h4 class="font-bold text-lg">ubicacion</h4>
                <p v-if="ubicacion"><strong>Latitud:</strong>{{ubicacion.lat}}</p>
                <p v-if="ubicacion"><strong>longitud:</strong>{{ubicacion.lon}}</p>
                <ListaBeneficio
                :empleado="empleado"/>
                <FormularioBeneficio
                    v-if="mostrarFormulario"
                    :empleado="props.empleado"
                    @cerrar="mostrarFormulario = false"
                    @guardar="guardarBeneficio" />

            </div>

            <div class="mt-8 flex justify-end">

                <button @click="emit('cerrar')" class="bg-gray-600 text-white px-4 py-2 rounded">Cerrar</button>
            </div>

        </div>

    </div>

</template>