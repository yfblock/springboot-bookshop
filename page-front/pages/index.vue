<template>
  <!-- banner -->
  <div class="bg-cover bg-no-repeat bg-center py-36" style="background-image: url('assets/images/banner-bg.jpg');">
    <div class="container">
      <h1 class="text-6xl text-gray-800 font-medium mb-4 capitalize">
        best choice for <br> books trading
      </h1>
      <p></p>
      <div class="mt-12">
        <NuxtLink to="/shop_list" class="bg-primary border border-primary text-white px-8 py-3 font-medium 
                    rounded-md hover:bg-transparent hover:text-primary">现在购买</NuxtLink>
      </div>
    </div>
  </div>
  <!-- ./banner -->

  <!-- features -->
  <div class="container py-16">
    <div class="w-10/12 grid grid-cols-1 md:grid-cols-3 gap-6 mx-auto justify-center">
      <div class="border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5">
        <img src="assets/images/icons/delivery-van.svg" alt="Delivery" class="w-12 h-12 object-contain">
        <div>
          <h4 class="font-medium capitalize text-lg">免费包邮</h4>
          <p class="text-gray-500 text-sm">订单超过 200 RMB</p>
        </div>
      </div>
      <div class="border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5">
        <img src="assets/images/icons/money-back.svg" alt="Delivery" class="w-12 h-12 object-contain">
        <div>
          <h4 class="font-medium capitalize text-lg">商品折扣</h4>
          <p class="text-gray-500 text-sm">每天都有特价书籍</p>
        </div>
      </div>
      <div class="border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5">
        <img src="assets/images/icons/service-hours.svg" alt="Delivery" class="w-12 h-12 object-contain">
        <div>
          <h4 class="font-medium capitalize text-lg">24 小时</h4>
          <p class="text-gray-500 text-sm">不间断服务</p>
        </div>
      </div>
    </div>
  </div>
  <!-- ./features -->
</template>
