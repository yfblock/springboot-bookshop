import { defineStore } from 'pinia'
import { Toast } from 'flowbite-vue'
import { onMounted, createVNode, render } from 'vue'


export const useToast = defineStore('toast', {
  state: () => {
    return { toastWrapper: null }
  },
  // could also be defined as
  // state: () => ({ count: 0 })
  actions: {
    push(type: string, message: string, duration: number) {
      if(this.toastWrapper == null) return;
      const NODE = createVNode(Toast, { type, class: 'tasition mr-5 mt-2' ,closable: true, divide: true }, message);
      const child = document.createElement("div");
      this.toastWrapper.appendChild(child);
      render(NODE, child);

      setTimeout(() => {
        console.log('clear');
        render(null, child);
      }, duration)
    }
  },
})

