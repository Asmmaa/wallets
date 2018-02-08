import {Component, OnInit} from '@angular/core';
import {Wallet} from "../demos/model/wallet";
import {PricingService} from "../pricing.service";

@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {

  money = 0; // attribut de la class pas besoin de let

  wallet = new Wallet(); // alt+enter: import


  constructor(public pricingService: PricingService) {
    this.wallet.pricingService = pricingService;
    pricingService.loadPrices()  //resultat d'une promesse
      .then(response => console.log(response))
  .then( () => this.initWallet())

  }

  ngOnInit() {
  }

  deposit(value: string) {
    /*this.money += parseFloat(value);*/
    console.log(value);
    this.wallet.deposit(parseFloat(value));

  }

  buy(value: string, symb: string) {

    console.log(value);
    this.wallet.buy(parseFloat(value), symb);

    console.log(this.wallet);
  }

  sell(value: string, symb: string) {

    console.log(value);
    this.wallet.sell(parseFloat(value), symb);

    console.log(this.wallet);
  }

  initWallet(){
    this.deposit("100000");
    this.buy('2','BTC');
  }

  getColor(symbol) {
    return this.pricingService.getColor(symbol);
  }

  updatePrices(){
    return this.pricingService.loadPrices;
  }


}
