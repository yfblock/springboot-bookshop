<template>
  <!-- header -->
  <header class="py-4 shadow-sm bg-white">
    <div class="container flex items-center justify-between">
      <NuxtLink to="/">
        <img src="/assets/logo.svg" alt="Logo" class="w-32">
      </NuxtLink>

      <div class="w-full max-w-xl relative flex">
        <span class="absolute left-3 top-3 text-lg text-gray-400">
          <i class="icon icon-search"></i>
        </span>
        <input type="text" name="search" id="search"
          v-model="searchData"
          class="w-full border border-primary border-r-0 pl-8 py-3 pr-3 rounded-l-md focus:outline-none"
          placeholder="请输入需要搜索的内容" />
        <button @click="search" class="bg-primary border border-primary text-white px-8 rounded-r-md hover:bg-transparent hover:text-primary transition whitespace-nowrap">搜索</button>
      </div>

      <div class="flex items-center space-x-4">
        <div v-if="user.logined">
            <NuxtLink to="/user/profile" class="text-center text-gray-700 hover:text-primary transition relative">
              <div class="text-2xl">
                <i class="icon icon-people"></i>
              </div>
              <div class="text-xs leading-3">{{ user.userInfo['username'] }}</div>
            </NuxtLink>
        </div>
        <div v-else>
          <NuxtLink to="/login" class="text-black rounded-sm hover:bg-gray-300 py-2 px-2.5 transition whitespace-nowrap">登陆</NuxtLink>
          <NuxtLink to="/register" class="text-black rounded-sm hover:bg-gray-300 py-2 px-2.5 transition whitespace-nowrap">注册</NuxtLink>
        </div>
      </div>
    </div>
  </header>
  <!-- ./header -->

  <!-- navbar -->
  <nav class="bg-gray-800">
    <div class="container flex">
      <div class="px-8 py-4 bg-primary flex items-center cursor-pointer relative group">
        <span class="text-white">
          <i class="fa-solid fa-bars"></i>
        </span>
        <span class="capitalize ml-2 text-white">所有类别</span>

        <!-- dropdown -->
        <div
          class="absolute w-full left-0 top-full bg-white shadow-md pt-3 divide-y divide-gray-300 divide-dashed opacity-0 group-hover:opacity-100 transition duration-300 invisible group-hover:visible">
          <div v-for="(item, index) in goodTypes">
            <a href="#" class="flex items-center px-6 py-3 hover:bg-gray-100 transition bg-white">
              <span class="ml-6 text-grahtmly-600 text-sm">{{ item['typeName'] }}</span>
            </a>
          </div>
        </div>
      </div>

      <div class="flex items-center justify-between flex-grow pl-12">
        <div class="flex items-center space-x-8 capitalize">
          <NuxtLink href="/" class="text-gray-200 hover:text-white transition">首页</NuxtLink>
          <NuxtLink href="/shop_list" class="text-gray-200 hover:text-white transition">商城列表</NuxtLink>
        </div>
      </div>
    </div>
  </nav>
  <!-- ./navbar -->
</template>

<script setup lang="ts">
import { useUser } from '~/stores/user';
import { useToast } from '~/stores/toast';

const user = useUser();
const toast = useToast();
const router = useRouter();

let searchData = "";
let goodTypes = ref([]);

async function fetchGoodTypes() {
    let res = await getGoodTypes();
    if (res && res['status']) {
        console.log(res['data'])
        goodTypes.value = res['data'];
    }
}

async function search() {
  // await navigateTo({
  //   path: '/shop_list',
  //   query: {
  //     search: searchData
  //   },
  //   replace: true
  // })
  router.push({
    path: '/shop_list',
    query: {
      search: searchData
    },
    force: true,
    replace: true
  })
}

await fetchGoodTypes();
</script>
