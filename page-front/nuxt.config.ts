// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    // runtimeConfig: {
    //     public: {
    //         apiBase: 
    //     }
    // },
    modules: [
        '@nuxtjs/tailwindcss',
        '@pinia/nuxt',
        // [
        //     '@pinia/nuxt', 
        //     {
        //         autoImports: ['defineStore', 'acceptHMRUpdate'],
        //     }
        // ],
    ],
    imports: {
        dirs: ['./stores'],
    },
    // pinia: {
    //     autoImports: [
    //         // automatically imports `defineStore`
    //         'defineStore', // import { defineStore } from 'pinia'
    //         ['defineStore', 'definePiniaStore'], // import { defineStore as definePiniaStore } from 'pinia'
    //     ],
    // },
    pinia: {
        autoImports: ['defineStore', 'acceptHMRUpdate'],
    },
})
