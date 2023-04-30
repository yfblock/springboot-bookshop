<template>
    <!-- <footer
        class="p-4 my-6 mx-4 bg-white rounded-lg shadow md:flex md:items-center md:justify-between md:p-6 xl:p-8 dark:bg-gray-800">
        NZPlatform 后台管理页面
    </footer> -->
    <p class="my-10 text-sm text-center text-gray-500">
        &copy; 2019-{{ year }} NZPlatform. All rights reserved.
    </p>
</template>

<script setup>
let year = new Date().getFullYear();
console.log(year);
</script>