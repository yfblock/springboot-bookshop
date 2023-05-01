<template>
<div class="p-4 bg-white block sm:flex items-center justify-between border-b border-gray-200 lg:mt-1.5 dark:bg-gray-800 dark:border-gray-700">
    <div class="w-full mb-1">
        <div class="mb-4">
            <nav class="flex mb-5" aria-label="Breadcrumb">
                <ol class="inline-flex items-center space-x-1 text-sm font-medium md:space-x-2">
                  <li class="inline-flex items-center">
                    <a href="#" class="inline-flex items-center text-gray-700 hover:text-primary-600 dark:text-gray-300 dark:hover:text-white">
                      <svg class="w-5 h-5 mr-2.5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path></svg>
                      用户列表
                    </a>
                  </li>
                </ol>
            </nav>
            <h1 class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white">所有用户</h1>
        </div>
        <div class="sm:flex">
            <div class="items-center hidden mb-3 sm:flex sm:divide-x sm:divide-gray-100 sm:mb-0 dark:divide-gray-700">
                <label for="users-search" class="sr-only">搜索</label>
                <div class="relative mt-1 lg:w-64 xl:w-96">
                    <input type="text" @change="onSearchChange" name="email" id="users-search" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500" placeholder="搜索用户">
                </div>
                <button class="btn block w-1/4 bg-green-500 text-white p-2 ml-2 rounded"> 搜索 </button>
            </div>
        </div>
    </div>
</div>
<div class="flex flex-col">
    <div class="overflow-x-auto">
        <div class="inline-block min-w-full align-middle">
            <div class="overflow-hidden shadow">
                <table class="min-w-full divide-y divide-gray-200 table-fixed dark:divide-gray-600">
                    <thead class="bg-gray-100 dark:bg-gray-700">
                        <tr>
                            <th scope="col" class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400">
                                用户名
                            </th>
                            <th scope="col" class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400">
                                邮箱
                            </th>
                            <th scope="col" class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400">
                                联系电话
                            </th>
                            <th scope="col" class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400">
                                角色
                            </th>
                            <th scope="col" class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400">
                                操作
                            </th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200 dark:bg-gray-800 dark:divide-gray-700">
                        <template v-for="(item, index) in userList">
                            <tr class="hover:bg-gray-100 dark:hover:bg-gray-700">
                            <td class="flex items-center p-4 mr-12 space-x-6 whitespace-nowrap">
                                <img class="w-10 h-10 rounded-full" v-if="item['avatar']" :src="BASE_STATIC_URL + item['avatar']"/>
                                <img v-if="!item['avatar']" src="/assets/images/avatar.png" alt="profile"
                                    class="w-10 h-10 rounded-full">
                                <div class="text-sm font-normal text-gray-500 dark:text-gray-400">
                                    <div class="text-base font-semibold text-gray-900 dark:text-white">{{ item['username'] }}</div>
                                </div>
                            </td>
                            <td class="max-w-sm p-4 overflow-hidden text-base font-normal text-gray-500 truncate xl:max-w-xs dark:text-gray-400">{{ item['email'] }}</td>
                            <td class="p-4 text-base font-medium text-gray-900 whitespace-nowrap dark:text-white">{{ item['phone'] }}</td>
                            <td class="p-4 text-base font-medium text-gray-900 whitespace-nowrap dark:text-white">{{ item['group'] }}</td>
                            <td class="p-4 space-x-2 whitespace-nowrap">
                                <button type="button" @click="modifyGroup(item['id'])" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-600 rounded-lg hover:bg-blue-800 focus:ring-4">
                                    修改用户组
                                </button>
                                <button @click="deleteU(item['id'])" type="button" class="inline-flex align-middle items-center px-3 py-2 text-sm font-medium text-center text-white bg-red-600 rounded-lg hover:bg-red-800 focus:ring-4 focus:ring-red-300 dark:focus:ring-red-900">
                                    <svg class="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z" clip-rule="evenodd"></path></svg>
                                    删除
                                </button>
                            </td>
                        </tr>
                        </template>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="sticky bottom-0 right-0 flex justify-center items-center w-full p-4 bg-white border-t border-gray-200 dark:bg-gray-800 dark:border-gray-700">
    <div class="flex items-center mb-4 sm:mb-0">
        <a href="#" class="inline-flex justify-center p-1 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
        </a>
        <a class="inline-flex p-1 mx-1">
            1
        </a>
        <a href="#" class="inline-flex justify-center p-1 mr-2 text-gray-500 rounded cursor-pointer hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clip-rule="evenodd"></path></svg>
        </a>
        <span class="text-sm font-normal text-gray-500 dark:text-gray-400">正在显示 <span class="font-semibold text-gray-900 dark:text-white">1-{{ userList.length }}</span> of <span class="font-semibold text-gray-900 dark:text-white">{{ userList.length }}</span></span>
    </div>
</div>

<Modal v-if="modifyModelShow" @close="modifyModelShow = false">
    <template #header>
    <div class="flex items-center text-lg">
        修改用户组
    </div>
    </template>
    <template #body>
    <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
        <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">请选择要设置的用户组</label>
            <select v-model="groupSelected" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                <option v-for="(item, index) in userGroups" :value="item['id']" :selected="item['group'] == modifyUser['group']">{{ item['group'] }}</option>
            </select>
    </p>
    </template>
    <template #footer>
    <div class="flex justify-between">
        <button @click="modifyModelShow = false" type="button" class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">
        取消
        </button>
        <button @click="confirmModify" type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
        确认
        </button>
    </div>
    </template>
</Modal>

</template>

<script setup lang="ts">
import { useToast } from '~/stores/toast';
import { Modal } from 'flowbite-vue'

const toast = useToast();
let userList = ref<any>([]);
let userGroups = ref<any>([]);
let modifyModelShow = ref(false);
let originList: any[] = [];
let modifyUser = ref<any>({});
let groupSelected = ref(0);

const searchValue = () => {
    userList.value = originList.filter((v) => {
        return v['username'].includes(search);
    })
}

let search = "";

const fetchUsers = async () => {
    let res = await getUserList();
    if(res && res['status']) {
        originList = res['data'];
        // userList.value = res['data'];
        searchValue();
    }
}

const fetchUserGroups = async () => {
    let res = await getUserGroups();
    if(res && res['status']) {
        userGroups.value = res['data'];
    }
}

console.log(userList);

definePageMeta({
  layout: "admin",
});

const onSearchChange = (e: any) => {
    let value = e.target.value;
    console.log(value);
    search = value;
    searchValue();
}

const deleteU = async (id: number) => {
    let res = await deleteUser(id);
    if(res) {
        toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
        if(res['status']) {
            await fetchUsers();
        }
    } else {
        toast.push('danger', '网络异常', 2000);
    }
}

const modifyGroup = async (id: number) => {
    await fetchUserGroups();
    let user = originList.find((v) => v.id == id);
    if(!user) return;
    modifyUser.value = user;
    modifyModelShow.value = true;
    let group = userGroups.value.find((v: any) => v['group'] == user['group']);
    if(group) {
        groupSelected.value = group['id'];
    }
}

const confirmModify = async () => {
    let res = await updateUserGroup(modifyUser.value['id'], groupSelected.value);
    if(res) {
        toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
        if(res['status']) {
            await fetchUsers();
        }
    } else {
        toast.push('danger', '网络异常', 2000);
    }
    modifyModelShow.value = false;
}

await fetchUsers();
</script>