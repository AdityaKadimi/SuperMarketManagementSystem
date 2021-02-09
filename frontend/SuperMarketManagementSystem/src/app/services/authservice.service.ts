import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

export class User{
  constructor(
    public status:string,
     ) {}
  
}


@Injectable({
  providedIn: 'root'
})


export class AuthserviceService {

  constructor(
    private httpClient:HttpClient
  ) { 
     }

  authenticate(credentials, callback) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password) });
    return this.httpClient.get<User>('/api/admin/validateLogin',{headers: headers}).subscribe(
      
      userData => {
        console.log(JSON.stringify(userData));
        console.log(userData)
       sessionStorage.setItem('username',credentials.username);
       return callback && callback();
      },
      error => {
        sessionStorage.removeItem('username');
        return callback && callback();
      }
   
    );
  }

  // authenticate(username, password) {
  //   if (username === "admin123" && password === "password") {
  //     sessionStorage.setItem('username', username)
  //     return true;
  //   } else {
  //     return false;
  //   }
  // }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }


}
