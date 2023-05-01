<template>
  <nav class="py-1 fixed z-30 w-full bg-white border-b border-gray-200 dark:bg-gray-800 dark:border-gray-700">
    <div class="px-3 py-3 lg:px-5 lg:pl-3">
      <div class="flex items-center justify-between">
        <div class="flex items-center justify-start">
          <a href="/" class="flex ml-2 md:mr-24">
            <img src="/assets/logo.svg" class="h-8 mr-3" alt="FlowBite Logo" />
          </a>
        </div>
        <div class="flex items-center mr-10">
          <NuxtLink to="/" class="mr-6 hover:text-primary cursor-pointer">
            去前台
          </NuxtLink>
          <!-- Profile -->
          <div class="flex items-center">
            <dropdown text="Bottom" class="justify-center">
              <template #trigger>
                <div 
                  class="cursor-pointer flex items-center text-sm font-medium text-gray-900 rounded-full hover:text-blue-600 dark:hover:text-blue-500 md:mr-0 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:text-white">
                  <span class="sr-only">打开用户菜单</span>
                  <img class="w-8 h-8 rounded-full mr-4" v-if="user.userInfo.avatar" :src="BASE_STATIC_URL + user.userInfo.avatar"
                    alt="user photo" />
                    {{ user.userInfo.username }}
                  <img v-if="!user.userInfo.avatar" src="/assets/images/avatar.png" alt="profile"
                      class="w-8 h-8 rounded-full mr-4">
                </div>
              </template>
              <div
                class="z-50 mt-4 text-base list-none divide-y rounded shadow mr">
                <div class="px-4 pb-3" role="none">
                  <p class="text-sm text-gray-900 dark:text-white" role="none">
                    {{ user.userInfo.username }}
                  </p>
                  <p class="text-sm font-medium text-gray-900 truncate dark:text-gray-300" role="none">
                    {{ user.userInfo.email }}
                  </p>
                </div>
                <ul class="py-1" role="none">
                  <li>
                    <NuxtLink to="/admin"
                      class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-600 dark:hover:text-white"
                      role="menuitem">主菜单</NuxtLink>
                  </li>
                  <li>
                    <NuxtLink to="/user/profile"
                      class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-600 dark:hover:text-white"
                      role="menuitem">个人中心</NuxtLink>
                  </li>
                  <li>
                    <a href="#"
                      @click="logout()"
                      class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-600 dark:hover:text-white"
                      role="menuitem">退出登陆</a>
                  </li>
                </ul>
              </div>
            </dropdown>
            
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { useUser } from '~/stores/user';
import { useToast } from '~/stores/toast';
import { logout as userLogout, BASE_STATIC_URL } from '~/utils/requests';
import { Dropdown } from 'flowbite-vue'

const router = useRouter();
const user = useUser();
const toast = useToast();

const logout = async () => {
    await userLogout();
    user.logined = false;
    toast.push('success', '退出成功', 2000);
    router.push("/");
}
</script>