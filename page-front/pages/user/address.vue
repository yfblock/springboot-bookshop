<template>
    <!-- info -->
    <div class="col-span-9 grid grid-cols-3 gap-4">
        <template v-for="(item, _index) in addressList">
            <div class="shadow rounded bg-white px-4 pt-6 pb-8">
                <div class="flex items-center  mb-4">
                    <h3 class="flex-grow font-medium text-gray-800 text-lg">{{ item['title'] }}</h3>
                    <a href="#" class="text-primary" @click="deleteAddr(item['id'])">删除</a>
                </div>
                <div class="space-y-1">
                    <h4 class="text-gray-700 font-medium">{{ item['people'] }}</h4>
                    <p class="text-gray-800">{{ item['address'] }}</p>
                    <p class="text-gray-800">{{ item['phone'] }}</p>
                </div>
            </div>
        </template>
        <div class="w-full col-span-3">
            <button class="bg-primary px-4 w-32 h-12 text-white rounded" @click="showModal()"> 添加新地址 </button>
        </div>
    </div>
    <Modal size="xl" v-if="isShowModal" @close="closeModal">
        <template #header>
            <div class="flex items-center text-lg">
            添加新地址
            </div>
        </template>
        <template #body>
            <div>
                <label>标题</label>
                <input type="email" v-model="formData.title" class="input-box rounded border-gray-300">
            </div>
            <div class="mt-5">
                <label>联系人</label>
                <input type="text" v-model="formData.people" class="input-box rounded border-gray-300">
            </div>
            <div class="mt-5">
                <label>联系电话</label>
                <input type="text" v-model="formData.phone" class="input-box rounded border-gray-300">
            </div>
            <div class="mt-5">
                <label>详细地址</label>
                <textarea class="input-box rounded border-gray-300" v-model="formData.address"></textarea>
                <!-- <input type="textarea" name="phone" id="phone" class="input-box rounded border-gray-300"> -->
            </div>
        </template>
        <template #footer>
            <div class="flex justify-between">
            <button @click="closeModal" type="button" class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">
                取消
            </button>
            <button @click="add" type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                确认
            </button>
            </div>
        </template>
        </Modal>
    <!-- ./info -->
</template>

<script setup lang="ts">
import { useToast } from '~/stores/toast';
import { Modal } from 'flowbite-vue'

const toast = useToast();
let addressList = ref([]);

let formData = reactive({
    title: '',
    people: '',
    address: '',
    phone: ''
})

async function updateAddressList() {
    const res = await getAddresses();

    if(res && res['status']) {
        addressList.value = res['data'];
    }
}

async function deleteAddr(id: Number) {
    let res = await deleteAddress(id);

    if(res) {
        toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
        await updateAddressList();
    } else {
        toast.push('danger', '网络异常', 2000);
    }
}

async function add() {
    let res = await addAddress(formData);

    if(res) {
        toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
        isShowModal.value = false;
        await updateAddressList();
    } else {
        toast.push('danger', '网络异常', 2000);
    }
}

const isShowModal = ref(false)
function closeModal() {
  isShowModal.value = false
}
function showModal() {
  formData = {
    title: '',
    people: '',
    address: '',
    phone: ''
  }
  isShowModal.value = true
}

await updateAddressList();
</script>