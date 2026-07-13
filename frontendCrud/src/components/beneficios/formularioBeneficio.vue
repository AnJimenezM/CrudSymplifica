<script setup>

import { ref, watch} from "vue";

const emit = defineEmits(["guardar","cerrar"]);

const props = defineProps({

    empleado:Object,

    beneficio:{
        type: Object,
        default: null
    }

});

const beneficio = ref({

    nombre_beneficio:"",
    monto:""

});

watch(
    () => props.beneficio, (nuevoBeneficio) =>{
        if (nuevoBeneficio){
            beneficio.value={...nuevoBeneficio};
        } else{
            beneficio.value ={
                nombre_beneficio:"",
                monto:""
            }
        }
    }, {immediate:true}

    
)

function guardar(){

    emit("guardar",{

        ...beneficio.value,

        empleado:{

            id_empleado:props.empleado.id_empleado

        }

    });

}

</script>

<template>

    <div class="fixed inset-0 bg-black/40 flex justify-center items-center">

        <div class="bg-white rounded-xl p-8 w-125">

            <h2 class="text-2xl font-bold mb-6">Nuevo beneficio</h2>

            <div class="space-y-4">

                <input
                v-model="beneficio.nombre_beneficio"
                placeholder="Nombre"
                class="border rounded-lg p-3 w-full">

                <input
                v-model="beneficio.monto"
                type="number"
                placeholder="Monto"
                class="border rounded-lg p-3 w-full">

            </div>

            <div class="flex justify-end gap-3 mt-6">

                <button class="bg-gray-500 text-white px-5 py-2 rounded" @click="$emit('cerrar')">Cancelar</button>

                <button  @click="guardar" class="bg-blue-600 text-white px-5 py-2 rounded">Guardar</button>

            </div>

        </div>

    </div>

</template>