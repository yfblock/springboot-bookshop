<template>
  <div class="col-span-9 grid grid-cols-3 gap-4">
    <template v-for="(item, index) in myGoods">
      <div class="bg-white shadow rounded overflow-hidden group">
      <div class="relative">
        <img v-if="item['cover']" :src="BASE_STATIC_URL + item['cover']" alt="product 1" class="w-full h-40">
        <div v-else class="w-full"></div>
        <div class="absolute inset-0 bg-black bg-opacity-40 flex items-center 
                    justify-center gap-2 opacity-0 group-hover:opacity-100 transition">
          <a href="#"
            class="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
            title="view product">
            <i class="fa-solid fa-magnifying-glass"></i>
          </a>
          <a href="#"
            class="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
            title="add to wishlist">
            <i class="fa-solid fa-heart"></i>
          </a>
        </div>
      </div>
      <div class="pt-4 pb-3 px-4">
        <a href="#">
          <h4 class="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">{{ item['name'] }}</h4>
        </a>
        <div class="flex items-baseline mb-1 space-x-2">
          <p class="text-xl text-primary font-semibold">￥{{ item['price'] }}</p>
        </div>
        <div class="flex items-center">
          <div class="flex gap-1 text-sm text-yellow-400">
            <span><i class="fa-solid fa-star"></i></span>
            <span><i class="fa-solid fa-star"></i></span>
            <span><i class="fa-solid fa-star"></i></span>
            <span><i class="fa-solid fa-star"></i></span>
            <span><i class="fa-solid fa-star"></i></span>
          </div>
        </div>
      </div>
      <a href="#"
        class="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition">下架</a>
    </div>
    </template>
  </div>
</template>

<script setup lang="ts">
let myGoods = ref<any>([]);

async function fetchGoods() {
  let res = await getUserGoods();
  if (res && res['status']) {
    myGoods.value = res['data'];
  }
}

await fetchGoods();
</script>