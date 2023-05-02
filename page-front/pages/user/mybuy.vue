<template>
    <div class="col-span-9 grid grid-cols-3 gap-4">
        <template v-for="(item, index) in myGoods">
            <div class="bg-white shadow rounded overflow-hidden group">
                <div class="relative">
                    <img v-if="item['cover']" :src="BASE_STATIC_URL + item['cover']" alt="product 1" class="w-full h-40">
                    <div v-else class="w-full"></div>
                </div>
                <div class="pt-4 pb-3 px-4">
                    <a href="#">
                        <h4 class="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">{{
                            item['name'] }}</h4>
                    </a>
                    <div class="flex items-baseline mb-1 space-x-2">
                        <p class="text-xl text-primary font-semibold">￥{{ item['price'] }}</p>
                    </div>
                </div>
            </div>
        </template>
    </div>
</template>
  
<script setup lang="ts">
let myGoods = ref<any>([]);
let toast = useToast();

async function fetchGoods() {
    let res = await getMyBuy();
    if (res && res['status']) {
        console.log(res['data']);
        myGoods.value = res['data'];
    }
}

await fetchGoods();
</script>