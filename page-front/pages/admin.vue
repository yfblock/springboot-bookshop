<template>
    <NuxtPage />
</template>
<script setup lang="ts">
definePageMeta({
  layout: "admin",
  middleware: ['adminerror']
});
</script>