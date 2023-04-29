const BASE_API_URL = 'http://localhost:8081/api'

async function request(url: string, data: any = {}) {
    return fetch(BASE_API_URL + url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: new URLSearchParams(data),
    }).then(function (data) {
        return data.json();
    })

    // return useFetch(url, {
    //     method: 'POST',
    //     body: data,
    //     baseURL: BASE_API_URL,
    //     headers: {
    //         'Content-Type': 'application/x-www-form-urlencoded'
    //     },
    //     onRequest({ request, options }) {
    //         const data: any = options.body;
    //         options.body = new URLSearchParams(data);
    //     },
    // })
}

export async function login(formData: any) {
    return request('/user/login', formData);
}

export async function getUserInfo() {
    request('http://localhost:3000/fdata')
}