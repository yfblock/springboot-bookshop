import { isNumberObject } from "util/types";

const BASE_API_URL = 'http://localhost:8081/api'
export const BASE_STATIC_URL = BASE_API_URL;

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
    return request('/user/update_userinfo', data);
}

export async function getAddresses() {
    return request('/user/get_addresses');
}

export async function addAddress(formData: any) {
    return request('/user/add_address', formData);
}


export async function deleteAddress(id: Number) {
    return request('/user/delete_address', {id});
}

export async function getUserList() {
    return request('/user/get_user_list');
}

export async function deleteUser(id: number) {
    return request('/user/delete_user', {id});
}

export async function getUserGroups() {
    return request('/user/get_user_groups');
}

export async function updateUserGroup(id: number, group: number) {
    return request('/user/update_user_group', {id, group});
}

export async function getSystemLogs() {
    return request('/log/get_logs');
}

export async function addUserGroup(formData: any) {
    return request('/user/add_user_group', formData);
}

export async function deleteUserGroup(id: number) {
    return request('/user/delete_user_group', {id});
}

export async function getGoodTypes() {
    return request('/good/get_good_types');
}

export async function deleteGoodType(id: number) {
    return request('/good/delete_good_type', {id});
}

export async function addGoodType(formData: any) {
    return request('/good/add_good_type', formData);
}

export async function addGood(formData: any) {
    return request('/good/add_good', formData);
}

export async function getUserGoods() {
    return request('/good/get_user_goods');
}
