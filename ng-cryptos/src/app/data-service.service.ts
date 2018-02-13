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
  createWallet(wallet: Wallet){
    let url = 'http://localhost:8080/cryptos/api/wallets';

    let dto= { // Data Transfer Object pour Jax-B
      name: wallet.name,
      user: wallet.user
    };

    console.log(dto);
    return this.http
      .post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)', data))
     // .catch(e => console.error('Fail :(',e))
}

  createUser(user: User){
    let url = 'http://localhost:8080/cryptos/api/users'

    let dto= { // Data Transfer Object pour Jax-B
      name:user.name,
    };

    console.log(dto);
    return this.http
      .post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)', data))
      .catch(e => console.error('Fail :(',e))
  }

 /* createUserWallet(wallet: Wallet){
    let url = 'http://localhost:8080/cryptos/api/wallets';

    let dto= { // Data Transfer Object pour Jax-B
      name: wallet.name,
      user: wallet.user
    };

    console.log(dto);
    return this.http
      .post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)', data))
    // .catch(e => console.error('Fail :(',e))
  }*/

  deleteWallet(wallet: Wallet){
    let url = 'http://localhost:8080/cryptos/api/users/' + wallet.user.id +'/' + wallet.user;
    let dto= { // Data Transfer Object pour Jax-B
      name: wallet.name,
      user: wallet.user
    };

    console.log(dto);
    return this.http
      .delete(url)
      .toPromise()
      .then(data => console.log('Success :)', data))
    // .catch(e => console.error('Fail :(',e))
  }


}
