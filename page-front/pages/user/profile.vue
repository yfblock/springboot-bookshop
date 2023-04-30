<template>
    <!-- info -->
    <div class="col-span-9 shadow rounded px-6 pt-5 pb-7">
        <h4 class="text-lg font-medium capitalize mb-4">
            Profile information
        </h4>
        <div class="space-y-4">
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label for="email">邮箱</label>
                    <input type="email" name="email" id="email" class="input-box" v-model="formData.email">
                </div>
                <div>
                    <label for="phone">手机号</label>
                    <input type="text" name="phone" id="phone" class="input-box" v-model="formData.phone">
                </div>
            </div>

            <div class="grid gap-4">
                <div>
                    <label for="email">头像</label>
                </div>
                <div class="flex items-center justify-center w-full">
                    <label for="dropzone-file" class="flex flex-col items-center justify-center w-full h-64 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                        <div class="flex flex-col items-center justify-center pt-5 pb-6">
                            <svg aria-hidden="true" class="w-10 h-10 mb-3 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12"></path></svg>
                            <p class="mb-2 text-sm text-gray-500 dark:text-gray-400"><span class="font-semibold">Click to upload</span> or drag and drop</p>
                            <p class="text-xs text-gray-500 dark:text-gray-400">SVG, PNG, JPG or GIF (MAX. 800x400px)</p>
                        </div>
                        <input @change="selectFile" id="dropzone-file" type="file" class="hidden" />
                    </label>
                </div>
            </div>
        </div>

        <div class="mt-4">
            <button type="submit"
                @click="submit()"
                class="py-3 px-6 text-center text-white bg-red-500 rounded hover:bg-red-700 transition font-medium">保存修改</button>
        </div>
    </div>
    <!-- ./info -->
</template>

<script setup>
import { useToast } from '~/stores/toast';
import { useUser } from '~/stores/user';

const user = useUser();

const formData = {
    email: user.userInfo['email'],
    phone: user.userInfo['phone']
}

let file = ref(null)
const toast = useToast();

async function selectFile(e) {
    const ufile = e.target.files[0];

    console.log(ufile);

    if (!ufile) return;

    // uploadFile(file);
    file.value = ufile;
}

async function submit() {
    // console.log(file.value)
    let uploadResult = await uploadFile(file.value);
    if(!uploadResult['status']) {
        toast.push('danger', uploadResult['msg'], 2000);
        return;
    }
    const newFormData = {
        avatar: uploadResult['data'] ? uploadResult['data'].replace(BASE_STATIC_URL, ''): user.userInfo['avatar'],
        ...formData
    };

    updateUserInfo(newFormData).then(res => {
        if(res) {
            toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
            if(res['status']) {
                user.updateLogin({
                    email: newFormData.email,
                    phone: newFormData.phone,
                    avatar: newFormData.avatar,
                    ...user.userInfo
                });
            }
        } else {
            toast.push('danger', '网络异常', 2000);
        }
    });
}
</script>