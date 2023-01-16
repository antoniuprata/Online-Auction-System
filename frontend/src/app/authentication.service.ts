import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from './model';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`http://localhost:8080/user/login`, { email: username, password: password })
            .pipe(map(user => {
              if (user.email === username) {
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.currentUserSubject.next(user);
              }
              this.reloadPage();
              return user;
            }));
    }

    logout() {
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }

    register(name: string, phoneNumber: string, username: string, password: string) {
      return this.http.post(`http://localhost:8080/user/signup`, { name: name, email: username, password: password, phone: phoneNumber })
      .pipe(map(user => {
        return user;
      }));
    }

    reloadPage() {
      setTimeout(()=>{
        window.location.reload();
      }, 100);
  }
}
