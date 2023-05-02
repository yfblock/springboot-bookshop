<template>
    <div class="p-4 bg-white block sm:flex items-center justify-between border-b border-gray-200 lg:mt-1.5 dark:bg-gray-800 dark:border-gray-700">
        <div class="w-full mb-1">
            <div class="mb-4">
                <nav class="flex mb-5" aria-label="Breadcrumb">
                    <ol class="inline-flex items-center space-x-1 text-sm font-medium md:space-x-2">
                    <li class="inline-flex items-center">
                        <a href="#" class="inline-flex items-center text-gray-700 hover:text-primary-600 dark:text-gray-300 dark:hover:text-white">
                        <svg class="w-5 h-5 mr-2.5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path></svg>
                        意见反馈
                        </a>
                    </li>
                    </ol>
                </nav>
                <h1 class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white">意见反馈</h1>
            </div>
        </div>
    </div>
    <div class="flex flex-col bg-white">
        <template v-for="(item, index) in opinions">
            <div class="px-12 flex flex-row mt-3">
                <div class="mr-4">{{ item['id'] }}</div>
                <div class="mr-4">{{ item['people'] }}</div>
                <div class="mr-4">{{ item['phone'] }}</div>
                <div class="flex-grow">{{ item['content'] }}</div>
                <div>{{ item['createTime'].replace('T', ' ') }}</div>
            </div>
        </template>
    </div>
</template>

<script setup lang="ts">
let opinions = ref<any>([]);

let res = await getOpinions();
if(res && res['status']) {
    opinions.value = res['data'];
}
</script>