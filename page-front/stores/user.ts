import { defineStore } from 'pinia'

export const userUser = defineStore('user', {
  state: () => {
    return { logined: false }
  },
  // could also be defined as
  // state: () => ({ count: 0 })
  actions: {
    login() {
      this.logined = true;
    },
  },
})


// if (import.meta.hot) {
//   import.meta.hot.accept(acceptHMRUpdate(useUser, import.meta.hot))
// }
