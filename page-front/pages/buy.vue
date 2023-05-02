<template>
    <div class="container pt-4 pb-16 items-center flex justify-center flex-col" v-if="!route.query.id">
        <div class="text-2xl mt-4">商品不存在</div>
    </div>
    <div class="container grid md:grid-cols-5 grid-cols-2 gap-6 pt-4 pb-16 items-center" v-else>
        <!-- ./sidebar -->
        <div class="col-span-1"></div>
        <!-- products -->
        <div class="col-span-3">
            <div class="my-4"><h1>商品详情</h1></div>
            <div class="grid grid-rows-3 grid-cols-2 gap-6">
                <div class="rounded overflow-hidden group row-span-3">
                    <div class="relative">
                        <img :src="BASE_STATIC_URL + good['cover']" alt="product 1" class="w-full h-full">
                        <div class="absolute inset-0 bg-black bg-opacity-40 flex items-center 
                        justify-center gap-2 opacity-0 group-hover:opacity-100 transition">
                        </div>
                    </div>
                </div>
                <div class="bg-white shadow rounded overflow-hidden group">
                    <div class="pt-4 pb-3 px-4">
                        <a href="#">
                            <h4 class="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
                               {{ good['name'] }}</h4>
                        </a>
                        <div>
                            {{ good['descript'] }}
                        </div>
                        <div class="flex items-baseline mb-1 space-x-2">
                            <p class="text-xl text-primary font-semibold">￥{{ good['price'] }}</p>
                        </div>
                    </div>
                </div>

                <div class="bg-white p-4">
                    <div class="my-2 text-lg">请选择您的邮寄地址</div>
                    <select @change="onChange" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <option selected hidden>请选择您的地址</option>
                        <option v-for="(item, index) in address" :value="item['id']">{{ item['title'] }}</option>
                    </select>
                    <div v-if="selectedAddress">
                        {{ selectedAddress['address'] }}
                    </div>
                </div>

                <div>
                    <button @click="submit()" class="w-full bg-primary text-white py-3 rounded">购买</button>
                </div>
            </div>
        </div>
        <!-- ./products -->
    </div>
</template>

<script setup lang="ts">
const route = useRoute();
let address: any = [];
let selectedAddress = ref<any>({});
let good: any = {};
const toast = useToast();
const router = useRouter();

async function getGood() {
    let res = await getGoodById(route.query.id)
    if(res && res['status']) {
        good = res['data']
    }
}

async function fetchAddresses() {
    let res = await getAddresses()
    if(res && res['status']) {
        address = res['data']
    }
}

async function onChange(e: any) {
    let value = e.target.value;
    selectedAddress.value = address.find((item:any) => item['id'] == value);
}

async function submit() {
    let res = await buyGood(good['id'], selectedAddress.value['id']);

    if(res) {
        toast.push(res['status'] ? 'success' : 'danger', res['msg'], 2000);
        await router.push('/user/mybuy');
    } else {
        toast.push('danger', '网络异常', 2000);
    }
}

await getGood();
await fetchAddresses();

console.log(address)
console.log(good)
</script>