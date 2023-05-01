import { defineStore } from 'pinia'

export const useUser = defineStore('user', {
  state: () => {
    return { 
      logined: false,
      fresh: false,
      userInfo: {
        username: '',
        avatar: '',
        email: '',
        phone: '',
        level: 0
      }
    }
  },
  // could also be defined as
  // state: () => ({ count: 0 })
  actions: {
    setLogin(userInfo: any) {
      this.logined = true;
      this.userInfo = userInfo;
    },
    updateLogin(userInfo: any) {
      userInfo['avatar'] = userInfo['avatar'];
      console.log(userInfo['avatar']);
      this.logined = true;
      this.userInfo = userInfo;
      this.fresh = true;
    }
  },
})


// if (import.meta.hot) {
//   import.meta.hot.accept(acceptHMRUpdate(useUser, import.meta.hot))
// }
