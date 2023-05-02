<template>
    <!-- shop wrapper -->
    <div class="container grid md:grid-cols-4 grid-cols-2 gap-6 pt-4 pb-16 items-start">
        <!-- ./sidebar -->
        <div class="col-span-1 bg-white px-4 pb-6 shadow rounded overflow-hiddenb hidden md:block pt-4">
            <div class="divide-y divide-gray-200 space-y-5">
                <div>
                    <h3 class="text-xl text-gray-800 mb-3 uppercase font-medium">所有类别</h3>
                    <div class="space-y-2">
                        <div class="flex items-center" v-for="(item, index) in goodTypes">
                            <input type="checkbox"
                                v-model="selected"
                                @change="onChange()"
                                :value="item['id']"
                                class="text-primary focus:ring-0 rounded-sm cursor-pointer">
                            <label for="cat-1" class="text-gray-600 ml-3 cusror-pointer">{{ item['typeName'] }}</label>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- products -->
        <div class="col-span-3">
            <div class="grid md:grid-cols-3 grid-cols-2 gap-6">
                <template v-for="(item, index) in goodList">
                    <div class="bg-white shadow rounded overflow-hidden group">
                        <div class="relative">
                            <img :src="BASE_STATIC_URL + item['cover']" alt="product 1" class="w-full h-40">
                            <div class="absolute inset-0 bg-black bg-opacity-40 flex items-center 
                            justify-center gap-2 opacity-0 group-hover:opacity-100 transition">
                            </div>
                        </div>
                        <div class="pt-4 pb-3 px-4">
                            <NuxtLink :to="'/buy?id=' + item['id']">
                                <h4 class="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
                                    {{ item['name'] }}</h4>
                            </NuxtLink>
                            <div class="flex items-baseline mb-1 space-x-2">
                                <p class="text-xl text-primary font-semibold">￥{{ item['price'] }}</p>
                            </div>
                        </div>
                        <NuxtLink :to="'/buy?id=' + item['id']"
                            class="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition">点击下单</NuxtLink>
                    </div>
                </template>
            </div>
        </div>
        <!-- ./products -->
    </div>
    <!-- ./shop wrapper -->
</template>

<script setup lang="ts">

let originList: any = [];
let goodList = ref<any>([]);
let goodTypes = ref<any>([]);
let selected = ref<number[]>([]);
const route = useRoute();

let searchData = ref<any>("");
if(route.query.search) {
    searchData.value = route.query.search;
}

const toast = useToast();

async function fetchGoodTypes() {
    let res = await getGoodTypes();
    if (res && res['status']) {
        goodTypes.value = res['data'];
    }
}

async function updateSearch() {
    console.log(searchData);
    goodList.value = originList.filter((item: any) => {
        console.log(item['name'].indexOf(searchData.value) > -1);
        return selected.value.find((id) => id == item['type']) && item['name'].indexOf(searchData.value) > -1;
    });
}

async function fetchGoods() {
    let res = await getAvailableGoods();
    if (res && res['status']) {
        originList = res['data'];
        await updateSearch();
        // goodList.value = res['data'];
    }
}

async function onChange() {
    await updateSearch();
}

onBeforeRouteUpdate(async (to) => {
    searchData.value = to.query.search;
    await updateSearch();
})

await fetchGoodTypes();
await fetchGoods();

let arr: any[] = [];
goodTypes.value.forEach((value: any) => {
    arr.push(value['id']);
})
selected.value = arr;
await updateSearch();
</script>
