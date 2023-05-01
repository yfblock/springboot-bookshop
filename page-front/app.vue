<template>
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>

<style>
@import url("//at.alicdn.com/t/c/font_4043163_tnu2cqdowuc.css");
@import url("https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap");
@import url("./public/assets/css/main.css");
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&family=Roboto:wght@400;500;700&display=swap");

@tailwind base;
@tailwind components;
@tailwind utilities;

.icon {
  font-family: "iconfont" !important;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

/* @layer base {
  body {
    @apply font-poppins;
  }
  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    @apply font-roboto;
  }
}

@layer components {
  .size-selector input:checked + label {
    @apply bg-primary text-white;
  }
  .color-selector input:checked + label {
    @apply ring-2 ring-primary;
  }

  .input-box {
    @apply block w-full border border-gray-300 px-4 py-3 text-gray-600 text-sm rounded placeholder-gray-400 focus:border-primary focus:ring-0;
  }
} */

</style>

<script setup>
import { onMounted } from 'vue'
import { 
  initAccordions, 
  initCarousels, 
  initCollapses, 
  initDials, 
  initDismisses, 
  initDrawers, 
  initDropdowns, 
  initModals, 
  initPopovers, 
  initTabs, 
  initTooltips } from 'flowbite'
import { useToast } from './stores/toast';
import { useUser } from '~/stores/user';
import { getUserInfo } from '~/utils/requests';

const toast = useToast();
const user = useUser();

// initialize components based on data attribute selectors
onMounted(() => {
  initAccordions();
  initCarousels();
  initCollapses();
  initDials();
  initDismisses();
  initDrawers();
  initDropdowns();
  initModals();
  initPopovers();
  initTabs();
  initTooltips();

  let div = document.createElement("div");
  div.setAttribute("class", "fixed top-40 right-5 flex flex-col w-full max-w-xs");
  document.body.appendChild(div);
  toast.toastWrapper = div;
})

// 可以在这里初始化 stores 里的信息， 比如: 获取登录状态。

let res = await getUserInfo();

if(res['status']) {
  user.updateLogin(res['data']);
}
</script>