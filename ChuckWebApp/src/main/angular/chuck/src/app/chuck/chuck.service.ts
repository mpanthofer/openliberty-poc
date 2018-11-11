import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chuck } from './chuck.model'

@Injectable({
  providedIn: 'root',
})
export class ChuckService {
    private baseUrl: string = "/chuck/rest";

    public constructor(private http: HttpClient) { }

    public getRandomJoke(): Observable<Chuck> {
        return this.http.get<Chuck>(this.baseUrl + '/chucks');
    }
}