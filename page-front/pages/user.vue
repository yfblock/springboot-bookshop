<template>
    <!-- breadcrumb -->
    <div class="container py-4 flex items-center gap-3">
        <a href="../index.html" class="text-primary text-base">
            <i class="fa-solid fa-house"></i>
        </a>
        <span class="text-sm text-gray-400">
            <i class="fa-solid fa-chevron-right"></i>
        </span>
        <p class="text-gray-600 font-medium">用户中心</p>
    </div>
    <!-- ./breadcrumb -->

    <!-- account wrapper -->
    <div class="container grid grid-cols-12 items-start gap-6 pt-4 pb-16">

        <!-- sidebar -->
        <div class="col-span-3">
            <div class="px-4 py-3 shadow flex items-center gap-4">
                <div class="flex-shrink-0">
                    <img v-if="user.userInfo.avatar" :src="BASE_STATIC_URL + user.userInfo.avatar" alt="profile"
                        class="rounded-full w-14 h-14 border border-gray-200 p-1 object-cover">
                    <img v-if="!user.userInfo.avatar" src="../assets/images/avatar.png" alt="profile"
                        class="rounded-full w-14 h-14 border border-gray-200 p-1 object-cover">
                </div>
                <div class="flex-grow">
                    <p class="text-gray-600">你好,</p>
                    <h4 class="text-gray-800 font-medium">{{ user.userInfo.username }}</h4>
                </div>
            </div>

            <div class="mt-6 bg-white shadow rounded p-4 divide-y divide-gray-200 space-y-4 text-gray-600">
                <div class="space-y-1 pl-8">
                    <NuxtLink to="/user/profile" :class="route.name.endsWith('profile') ? 'text-primary': 'hover:text-primary'" class="relative block font-medium capitalize transition">
                        <span class="absolute -left-8 top-0 text-base">
                            <i class="fa-regular fa-address-card"></i>
                        </span>
                        个人信息
                    </NuxtLink>
                    <NuxtLink to="/user/address" :class="route.name.endsWith('address') ? 'text-primary': 'hover:text-primary'" class="relative block capitalize transition">
                        管理地址
                    </NuxtLink>
                    <NuxtLink to="/user/change_pass" :class="route.name.endsWith('change_pass') ? 'text-primary': 'hover:text-primary'" class="relative block capitalize transition">
                        更改密码
                    </NuxtLink>
                </div>

                <div class="space-y-1 pl-8 pt-4">
                    <a href="#" class="relative hover:text-primary block font-medium capitalize transition">
                        <span class="absolute -left-8 top-0 text-base">
                            <i class="fa-solid fa-box-archive"></i>
                        </span>
                        My order history
                    </a>
                    <a href="#" class="relative hover:text-primary block capitalize transition">
                        My returns
                    </a>
                    <a href="#" class="relative hover:text-primary block capitalize transition">
                        My Cancellations
                    </a>
                    <a href="#" class="relative hover:text-primary block capitalize transition">
                        My reviews
                    </a>
                </div>

                <div class="space-y-1 pl-8 pt-4">
                    <a href="#" class="relative hover:text-primary block font-medium capitalize transition">
                        <span class="absolute -left-8 top-0 text-base">
                            <i class="fa-regular fa-credit-card"></i>
                        </span>
                        Payment methods
                    </a>
                    <a href="#" class="relative hover:text-primary block capitalize transition">
                        voucher
                    </a>
                </div>

                <div class="space-y-1 pl-8 pt-4">
                    <a href="#" class="relative hover:text-primary block font-medium capitalize transition">
                        <span class="absolute -left-8 top-0 text-base">
                            <i class="fa-regular fa-heart"></i>
                        </span>
                        My wishlist
                    </a>
                </div>

                <div class="space-y-1 pl-8 pt-4">
                    <a @click="logout()" href="#" class="relative hover:text-primary block font-medium capitalize transition">
                        <span class="absolute -left-8 top-0 text-base">
                            <i class="fa-regular fa-arrow-right-from-bracket"></i>
                        </span>
                        退出登录
                    </a>
                </div>

            </div>
        </div>
        <!-- ./sidebar -->

        <NuxtPage />

    </div>
    <!-- ./account wrapper -->
</template>

<script setup>
import { useUser } from '~/stores/user';
import { useToast } from '~/stores/toast';
import { logout as userLogout, BASE_STATIC_URL } from '~/utils/requests';
const router = useRouter();
const route = useRoute();
const user = useUser();
const toast = useToast();
if (!user.logined) {
    router.push("/login");
}

console.log(user.userInfo.avatar);

const logout = async () => {
    await userLogout();
    user.logined = false;
    toast.push('success', '退出成功', 2000);
    router.push("/");
}
</script>