<template>
    <div class="bg-gray-100 min-h-screen flex flex-col">
        <AppHeader />
        <div class="flex-grow flex flex-col">
            <slot />
        </div>
        <AppFooter />
    </div>
</template>

<script setup lang="ts">
import { useUser } from '~/stores/user';
import { getUserInfo } from '~/utils/requests';
// 可以在这里初始化 stores 里的信息， 比如: 获取登录状态。
const user = useUser();

let res = await getUserInfo();

if(res['status']) {
  user.updateLogin(res['data']);
}
</script>