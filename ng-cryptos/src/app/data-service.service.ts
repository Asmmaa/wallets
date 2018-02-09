import { Injectable } from '@angular/core';
import {User} from "./demos/model/user";
import {HttpClient} from "@angular/common/http";
import {Wallet} from "./demos/model/wallet";

@Injectable()
export class DataService {

  constructor(public  http: HttpClient) {


  }


  fetchUsers():Promise<User[]> {

    let url = 'http://localhost:8080/cryptos/api/users'
    return this.http
      .get(url)
      .toPromise()
      .then(data => data as User[]) // attendre qu'on aie toute les réponses

  }

  fetchUserWithWallet(user:User) {

    let url = 'http://localhost:8080/cryptos/api/users/' + user.id
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with wallet', data); // attendre qu'on aie toute les réponses
    return data as User
  })

  }



}
