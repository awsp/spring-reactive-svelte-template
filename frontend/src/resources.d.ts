/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.1.1185 on 2023-03-06 16:55:42.

export interface Article extends BaseEntity {
    title: string;
    body?: string;
    author?: Author;
}

export interface Author {
    firstName?: string;
    lastName?: string;
    email: string;
}

export interface BaseEntity {
    id?: number;
}

export interface AuthRequest {
    username: string;
    password: string;
}

export interface AuthResponse {
    token?: string;
}

export interface RefreshToken {
    id?: number;
    map?: string;
    token?: string;
    expiration?: Date;
}

export const enum AuthStatus {
    LOGIN = "LOGIN",
    UNAUTHENTICATED = "UNAUTHENTICATED",
    LOGOUT = "LOGOUT",
}
