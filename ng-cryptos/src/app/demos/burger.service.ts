import { Injectable } from '@angular/core';
import {BunService} from "./bun.service";
import {SteakService} from "./steak.service";
import {SaladService} from "./salad.service";

@Injectable() // injection de dependance
export class BurgerService {

  constructor(public bunService: BunService,
              public steakService: SteakService,
              public  saladService: SaladService) {

  }


  getPrice(){
    return 10;
}



}
