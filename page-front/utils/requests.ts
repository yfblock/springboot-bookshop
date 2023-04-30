import { isNumberObject } from "util/types";

const BASE_API_URL = 'http://localhost:8081/api'

async function request(url: string, data: any = {}) {
    return fetch(BASE_API_URL + url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: new URLSearchParams(data),
        credentials:'include'
    }).then(function (response) {
        return response.json();
    }).then(res => {
        return res['timestamp']?null:res;
        // return res['status'] instanceof Boolean ? res : null;
    })
}

export async function uploadFile(file: any) {
    let formData = new FormData()
    formData.append('file',  file)
    return fetch(BASE_API_URL + '/file/upload', {
        method: 'POST',
        body: formData,
        credentials:'include'
    }).then(function (response) {
        return response.json();
    })
}


export async function login(formData: any) {
    return request('/user/login', formData);
}

export async function register(formData: any) {
    return request('/user/register', formData);
}

export async function logout() {
    return request('/user/logout');
}

export async function changePassword(formData: any) {
    return request('/user/change_password', formData);
}

export async function getUserInfo() {
    return request('/user/get_login_status');
}

export async function updateUserInfo(data: any) {
    return request('/user/upate_userinfo', data);
}
