<template>
  <section class="bg-gray-50 dark:bg-gray-900">
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
      <div
        class="w-full bg-white rounded shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
          注册你的账号
          </h1>
          <form class="space-y-4 md:space-y-6" action="">
            <div>
              <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                邮箱
              </label>
              <input type="email" name="email" id="email"
                v-model="formData.username"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="请输入用户名">
            </div>
            <div>
              <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">密码</label>
              <input type="password" name="password" id="password" placeholder="••••••••"
                v-model="formData.password"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
            </div>
            <div>
              <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">请再次输入密码</label>
              <input type="password" name="confirm-password" id="confirm-password" placeholder="••••••••"
                v-model="formData.rePassword"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
            </div>
            <button type="button"
              @click="signup()"
              class="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">注册</button>
              <p class="text-sm font-light text-gray-500 dark:text-gray-400">
              已有账号? 现在<NuxtLink href="/login" class="font-medium text-primary-600 hover:underline dark:text-primary-500">
                登陆</NuxtLink>
            </p>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
import { useUser } from '~/stores/user';
import { useToast } from '~/stores/toast';
import { register } from '~/utils/requests';
import { onMounted } from 'vue'

const user = useUser();
const toast = useToast();
const router = useRouter();

// 表单数据
let formData = reactive({
  username: '',
  password: '',
  rePassword: ''
});

let div: any;
onMounted(() => {
  div = document.createElement("div");
  div.setAttribute("class", "fixed top-40 right-5 flex flex-col w-full max-w-xs");
  document.body.appendChild(div);
})

const signup = () => {
  register(formData).then(data => {
    if(data['status']) {
      toast.push('success', data['msg'], 2000);
      router.push('/login');
    } else {
      toast.push('danger', data['msg'], 2000);
    }
    console.log("response", data);
  })
}
</script>
