<template>
    <AdminHeader />
    <div class="flex pt-16 overflow-hidden bg-gray-50 dark:bg-gray-900">

        <AdminSideBar />

        <div id="main-content" class="relative w-full h-full overflow-y-auto bg-gray-50 ml-64 dark:bg-gray-900">
            <main class="bg-gray-50 dark:bg-gray-900">
                <slot />
            </main>
            <AdminFooter />
            <!-- {{ if .Params.footer }} {{ partial "footer-dashboard" . }} {{ end }} -->
        </div>
    </div>
    
</template>

<script setup lang="ts">
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
</script>