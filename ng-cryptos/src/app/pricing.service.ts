import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Coin} from "./demos/model/prices";

@Injectable()
export class PricingService {


  //Asynchrone
  coins: Coin [];

  constructor(public http: HttpClient) {
  }

  getColor(symbol) {
    let line=this.coins.find(coin => coin.symbol === symbol)
    if (line !== undefined) {
      if (line.up === true) {
        return 'green'
      }
      else {
        return 'red'
      }
    }
    else { return 'black'}
  }


  loadPrices() { // return a promise
    let url = 'https://api.coinmarketcap.com/v1/ticker/?limit=10';


    function mapper(coin) { // chaque element est transformé
      return {
        name: coin.name,
        symbol: coin.symbol,
        price: coin.price_usd,
        up: coin.percent_change_1h > 0 ? true : false
      }
    }


    // ACCES SUR INTENet asynchrone
    return this.http.get(url) // return une promesse

      .toPromise()
      .then(internetCoins => (internetCoins as any).map(mapper)) // recupere
      .then(joliCoins => {
        this.coins = joliCoins; // les remplis dans mon coins
        return joliCoins; // sans return on a undefined
      });


    /*.toPromise()
     .then(internetCoins => (internetCoins as any).map(mapper)) // recupere
     .then(joliCoins => {
     this.coins = joliCoins; // les remplis dans mon coins
     return joliCoins; // sans return on a undefined
     });
     */


    /*.toPromise()
     .then(internetCoins => {
     this.coins = (internetCoins as any).map(coin => ({
     name: coin.name,
     symbol: coin.symbol,
     price: coin.price_usd
     }));
     return this.coins;
     });*/


  }


  priceToDollar(quantity, symbol) {

    for (var i = 0; i < this.coins.length; i++) {
      let current = this.coins[i];

      if (symbol === current.symbol) {
        return quantity * current.price;
        //console.log(priceDollar);

      }

    }
  }
}

