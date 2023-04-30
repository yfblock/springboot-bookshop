<template>
    <!-- info -->
    <div class="col-span-9 shadow rounded px-6 pt-5 pb-7">
        <h4 class="text-lg font-medium capitalize mb-4">
            更改密码
        </h4>
        <div class="space-y-4">
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label for="first">原密码</label>
                    <input v-model="formData.oldPassword" type="password" class="input-box rounded-sm">
                </div>
            </div>
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label for="birthday">新密码</label>
                    <input v-model="formData.newPassword" type="password" class="input-box rounded-sm">
                </div>
            </div>
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label for="repassword">确认密码</label>
                    <input v-model="formData.rePassword" type="password" class="input-box rounded-sm">
                </div>
            </div>
        </div>

        <div class="mt-4">
            <button type="button"
                @click="submit()"
                class="py-3 px-6 text-center text-white bg-red-500 rounded hover:bg-red-700 transition font-medium">更改密码</button>
        </div>
    </div>
    <!-- ./info -->
</template>

<script setup lang="ts">
import { useToast } from '~/stores/toast';
const formData = {
    oldPassword: '',
    newPassword: '',
    rePassword: '',
}

const toast = useToast();

async function submit() {
    // console.log(file.value)
    console.log(formData)
    let res = await changePassword(formData);
    console.log(res);
    if(res) {
        toast.push(res['status'] ? 'success' : 'danger', res['msg'],2000);
    }
}
</script>
