import { Injectable } from '@angular/core';
import {User} from "./demos/model/user";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class DataService {

  constructor(public  http: HttpClient) {


  }


  fetchUsers():Promise<User[]>{

    let url = 'http://localhost:8080/cryptos/api/users'
    return this.http
      .get(url)
      .toPromise()
      .then(data => data as User[]) // attendre qu'on aie toute les réponses
  }

}
