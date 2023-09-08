import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Users} from "../interfaces/users";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) { }

  public getById(id: string): Observable<Users> {
    return this.httpClient.get<Users>(`${this.pathService}/${id}`);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.pathService}/${id}`);
  }
}
