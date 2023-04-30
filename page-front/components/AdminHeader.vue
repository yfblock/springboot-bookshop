<template>
  <nav class="py-1 fixed z-30 w-full bg-white border-b border-gray-200 dark:bg-gray-800 dark:border-gray-700">
    <div class="px-3 py-3 lg:px-5 lg:pl-3">
      <div class="flex items-center justify-between">
        <div class="flex items-center justify-start">
          <a href="/" class="flex ml-2 md:mr-24">
            <img src="/assets/logo.svg" class="h-8 mr-3" alt="FlowBite Logo" />
          </a>
        </div>
        <div class="flex items-center">
          <!-- Profile -->
          <div class="flex items-center ml-3">
            <div>
              <button type="button"
                class="flex text-sm bg-gray-800 rounded-full focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
                id="user-menu-button-2" aria-expanded="false" data-dropdown-toggle="dropdown-2">
                <span class="sr-only">打开用户菜单</span>
                <img class="w-8 h-8 rounded-full" :src="BASE_STATIC_URL + user.userInfo.avatar"
                  alt="user photo">
              </button>
            </div>
            <!-- Dropdown menu -->
            <div
              class="z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded shadow dark:bg-gray-700 dark:divide-gray-600"
              id="dropdown-2">
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
                  <a href="#"
                    @click="logout()"
                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-600 dark:hover:text-white"
                    role="menuitem">退出登陆</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { useUser } from '~/stores/user';
import { useToast } from '~/stores/toast';
import { logout as userLogout, BASE_STATIC_URL } from '~/utils/requests';
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