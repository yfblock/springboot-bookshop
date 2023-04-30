export default defineNuxtRouteMiddleware((to, from) => {
  console.log("middleware to", to);
  console.log('from', from);
  if (to.params.id === '1') {
    throw createError({ statusCode: 404, statusMessage: 'Page Not Found' })
  }

  // if (!auth.value.isAuthenticated) {
  //     return navigateTo('/login')
  // }
  // return navigateTo('/dashboard')
})