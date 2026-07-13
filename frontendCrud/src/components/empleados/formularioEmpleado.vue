<script setup>

import {ref, watch} from "vue";

const emision = defineEmits(["cerrar", "guardar"]);

const props = defineProps({
    empleado:{
        type: Object,
        default: null
    }
})

const empleado = ref({
    nombre: "",
    apellido: "",
    correo: "",
    ciudad: "",
    posicion: ""
})

watch(
    () => props.empleado,(nuevoEmpleado) => {
        if (nuevoEmpleado){
            empleado.value= {...nuevoEmpleado};
        }
    }, {immediate: true}
);

function guardarEmpleado(){
    emision("guardar", empleado.value);
}

</script>

<template>

    <div class="fixed inset-0 bg-black/50 flex justify-center items-center">
        <div class="bg-white rounded-xl p-8 w-125">
            <h2 class="text-2xl font-bold mb-6">Nuevo empleado</h2>
            <div class="space-y-4">
                <input
                v-model="empleado.nombre"
                placeholder="Nombre"
                class="w-full border rounded-lg p-3">

                <input
                v-model="empleado.apellido"
                placeholder="Apellido"
                class="w-full border rounded-lg p-3">

                <input
                v-model="empleado.correo"
                placeholder="Correo"
                class="w-full border rounded-lg p-3">

                <input
                v-model="empleado.ciudad"
                placeholder="Ciudad"
                class="w-full border rounded-lg p-3">

                <input
                v-model="empleado.posicion"
                placeholder="Posición"
                class="w-full border rounded-lg p-3">
            </div>
            <div class="flex justify-end gap-3 mt-6">
                <button class="bg-gray-500 text-white px-5 py-2 rounded-lg" @click="$emit('cerrar')">Cancelar</button>
                <button @click="guardarEmpleado" class="bg-blue-600 text-white px-5 py-2 rounded-xl">guardar</button>
            </div>
        </div>
    </div>





</template>