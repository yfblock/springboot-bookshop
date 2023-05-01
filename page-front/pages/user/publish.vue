<template>
    <!-- info -->
    <div class="col-span-9 shadow rounded px-6 pt-5 pb-7">
        <h4 class="text-lg font-medium capitalize mb-4">
            发布商品
        </h4>
        <div class="space-y-4">
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label>商品名称</label>
                    <input type="email" class="input-box" v-model="formData.name">
                </div>
                <div>
                    <label>商品价格</label>
                    <input type="number" min="0.00" class="input-box" v-model="formData.price">
                </div>
                <div>
                    <label>商品类别</label>
                    <select v-model="formData.type" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <option v-for="(item, index) in goodTypes" :value="item['id']">{{ item['typeName'] }}</option>
                    </select>
                </div>
                <div class="col-span-2">
                    <label>商品描述</label>
                    <textarea rows="4" v-model="formData.descript" class="block p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500" placeholder="请输入商品描述"></textarea>
                </div>
            </div>

            <div class="grid gap-4">
                <div>
                    <label for="email">商品封面</label>
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
                class="py-3 px-6 text-center text-white bg-red-500 rounded hover:bg-red-700 transition font-medium">发布商品</button>
        </div>
    </div>
    <!-- ./info -->
</template>

<script setup>
import { useToast } from '~/stores/toast';
import { useUser } from '~/stores/user';

const user = useUser();

const formData = ref({
    name: '',
    price: 0,
    descript: '',
    type: 1
});

let file = ref(null)
let goodTypes = ref([]);
const toast = useToast();

async function fetchGoodTypes() {
    let res = await getGoodTypes();
    if (res && res['status']) {
        console.log(res['data'])
        goodTypes.value = res['data'];
    }
}

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
        cover: uploadResult['data'].replace(BASE_STATIC_URL, ''),
        ...formData.value
    };

    addGood(newFormData).then(res => {
        if(res) {
            toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
            if(res['status']) {
                formData.value = {
                    name: '',
                    price: 0,
                    descript: '',
                    type: 1
                }
            }
        } else {
            toast.push('danger', '网络异常', 2000);
        }
    });
}

await fetchGoodTypes();
</script>