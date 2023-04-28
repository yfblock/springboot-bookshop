export const state = () => ({
    counter: 0,
    logined: false
})

export const mutations = {
    increment(state, { counter }) {
        state.counter++
    },
    updateLogin(state, logined) {
        state.logined = logined;
    }
}

export const actions = {
    fetchLogin({ commit }) {
        console.log("fetch Login");
        // console.log(commit);
        return async () => {
            // commit('updateArticleTitle', response.data)
        };
    }
}