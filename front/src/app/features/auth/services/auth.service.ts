import { Injectable } from '@angular/core';
import {RegisterRequest} from "../interfaces/registerRequest";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LoginRequest} from "../interfaces/loginRequest";
import {LoginResponse} from "../interfaces/loginResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  public login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>('/api/auth/login', loginRequest);
  }

  public register(signupRequest: RegisterRequest): Observable<string> {
    return this.http.post<string>('/api/auth/register', signupRequest);
  }
}
