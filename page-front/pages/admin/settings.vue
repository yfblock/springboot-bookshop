<template>
    <div class="p-4 bg-white block sm:flex items-center justify-between border-b border-gray-200 lg:mt-1.5 dark:bg-gray-800 dark:border-gray-700">
        <div class="w-full mb-1">
            <div class="mb-4">
                <nav class="flex mb-5" aria-label="Breadcrumb">
                    <ol class="inline-flex items-center space-x-1 text-sm font-medium md:space-x-2">
                    <li class="inline-flex items-center">
                        <a href="#" class="inline-flex items-center text-gray-700 hover:text-primary-600 dark:text-gray-300 dark:hover:text-white">
                        <svg class="w-5 h-5 mr-2.5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path></svg>
                        系统设置
                        </a>
                    </li>
                    </ol>
                </nav>
                <h1 class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white">系统设置</h1>
            </div>
        </div>
    </div>
    <div class="flex flex-col bg-white">
        <div class="grid grid-cols-6 gap-6 m-20">
            <div class="col-span-6 sm:col-span-6">
                <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">备案号</label>
                <input type="text" v-model="formData.beian" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500" placeholder="请输入备案号" required>
            </div>
            <div class="col-span-6 sm:col-span-6 w-1/6">
                <button @click="submit()" class="btn bg-primary text-white py-3 rounded w-full">提交</button>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
const toast = useToast();
let formData = ref<any>({
    beian: ''
})

const fetchSettings = async () => {
    let res = await getSettings();
    if(res && res['status']) {
        let obj: any = {};
        for(let i in res['data']) {
            // opinions.value = res['data'];
            obj[res['data'][i]['name']] = res['data'][i]['value'];
        }
        formData.value = obj;
        console.log(obj);
    }
}

const submit = async () => {
    for(let i in formData.value) {
        let res = await updateSetting(i, formData.value[i]);
        if(!res || !res['status']) {
            toast.push('danger', res['message'], 2000);
            return;
        }
    }
    toast.push('success', '保存成功', 2000);
    await fetchSettings();
}

await fetchSettings();
</script>