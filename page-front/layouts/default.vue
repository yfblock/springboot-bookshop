<template>
    <div class="bg-gray-100 min-h-screen flex flex-col">
        <AppHeader />
        <div class="flex-grow flex flex-col">
            <slot />
        </div>
        <AppFooter />
    </div>
</template>