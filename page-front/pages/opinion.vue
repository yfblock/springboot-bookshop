<template>
    <!-- breadcrumb -->
    <div class="container py-4 flex items-center gap-3">
        <a href="../index.html" class="text-primary text-base">
            <i class="fa-solid fa-house"></i>
        </a>
        <span class="text-sm text-gray-400">
            <i class="fa-solid fa-chevron-right"></i>
        </span>
        <p class="text-gray-600 font-medium">意见反馈</p>
    </div>
    <!-- ./breadcrumb -->

    <!-- wrapper -->
    <div class="container flex items-center gap-6 pt-4 pb-16 justify-center">
        <div class="flex-grow"></div>
        <div class="flex-grow">
            <div>
                <label for="email">联系人</label>
                <input type="email" v-model="formData.people" placeholder="请输入联系人" class="input-box rounded mt-2 border-gray-300">
            </div>
            <div class="mt-3">
                <label for="phone">联系方式</label>
                <input type="text" v-model="formData.phone" placeholder="请输入联系方式" class="input-box rounded mt-2 border-gray-300">
            </div>
            <div class="mt-3">
                <label for="phone">意见</label>
                <textarea rows="4" v-model="formData.content" class="block p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500" placeholder="请输入意见信息"></textarea>
            </div>
            <div class="mt-3">
            <button type="submit"
                @click="submit()"
                class="py-3 px-6 text-center text-white bg-red-500 rounded hover:bg-red-700 transition font-medium">发布商品</button>
        </div>
        </div>
        <div class="flex-grow"></div>
    </div>
    <!-- ./wrapper -->
</template>

<script setup lang="ts">
const toast = useToast();
let formData = ref({
    people: '',
    phone: '',
    content: '',
});

const submit = async () => {
    let res = await addOpinion(formData.value);
    
    if(res) {
        toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
        if(res['status']) {
            formData.value = {
                people: '',
                phone: '',
                content: '',
            }
        }
    } else {
        toast.push('danger', '网络异常', 2000);
    }
}
</script>