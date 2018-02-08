import {PricingService} from "../../pricing.service";

export class Line {


  constructor(public symbol: string, public quantity: number, public value: number) {

  }

}

export class Wallet {

  lines: Line[] = [];
  pricingService: PricingService;

  deposit(dollars: number) {
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    if (usdLine === undefined) {
      this.lines.push(new Line('USD', dollars, dollars));
    }
    else {
      usdLine.quantity += dollars;
      usdLine.value = usdLine.quantity;

    }
  }

  buy(quantity: number, symbol: string) {
    let line = this.lines.find(coin => coin.symbol === 'USD');
    line.quantity = line.quantity - this.pricingService.priceToDollar(quantity, symbol);
    line.value = line.value - this.pricingService.priceToDollar(quantity, symbol);
    let symbolLine = this.lines.find(line => line.symbol === symbol);
    if (symbolLine === undefined) {
      this.lines.push(new Line(symbol, quantity, this.pricingService.priceToDollar(quantity, symbol)));
    }
    else {
      symbolLine.quantity += quantity;
      symbolLine.value += this.pricingService.priceToDollar(quantity, symbol);

    }

  }


  sell(quantity: number, symbol: string) {
    let symbolLine = this.lines.find(line => line.symbol === symbol);
    if (symbolLine.quantity !== 0 && symbolLine !== undefined) {
      symbolLine.quantity -= quantity;
      symbolLine.value -= this.pricingService.priceToDollar(quantity, symbol);


      let line = this.lines.find(coin => coin.symbol === 'USD');
      line.quantity = line.quantity + this.pricingService.priceToDollar(quantity, symbol);
      line.value = line.value + this.pricingService.priceToDollar(quantity, symbol);

    }
  }


//total dollar
  totalDollar(): number {
    let total = 0;
    let Line = this.lines;
    for (let i = 0; i < Line.length; i++) {
      let current = Line[i];
      console.log(current.symbol);
      if (current.symbol === 'USD') {

        total = total + current.quantity;
      }

      else {
        total = total + this.pricingService.priceToDollar(current.quantity, current.symbol);
      }
    }
    return total;
  }


//Order
  orderDollar() {
    let line = this.lines;

    line.sort(function (a, b) {
      return a.value - b.value;
    });
    console.log(line);
  }

    /*  //Order
     orderDollar() {
     let line = this.lines;
     for (let i = 0; i < line.length; i++) {
     let current = line[i];
     if (current.symbol !== 'USD') {
     current.quantity = this.pricingService.priceToDollar(current.quantity, current.symbol);
     }

     for (let i = 0; i < line.length; i++) {
     let current = line[i];
     if (line[i].quantity < line[i + 1].quantity) {
     line[i] = line[i + 1];
     line[i + 1] = current;
     }
     /!*line[i].quantity = line[i + 1].quantity;

     line[i + 1].quantity = current.quantity;*!/
     }

     }
     }*/


  }

/*const wallet = new Wallet();

 wallet.deposit(50000);
 //console.log(wallet);

 wallet.buy(30000, 'XRP');
 wallet.buy(2, 'BCH');
 console.log(wallet.lines);

 wallet.sell(1.2, 'BTC');*/
