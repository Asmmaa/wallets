import { Component, OnInit } from '@angular/core';
import {BurgerService} from "../burger.service";

@Component({
  selector: 'app-attributes',
  templateUrl: './attributes.component.html',
  styleUrls: ['./attributes.component.css']
})
export class AttributesComponent implements OnInit {


  isDisabled = true; // attribute ofthe class
  height =0;

  constructor(public burgerService: BurgerService) {

    burgerService.bunService.getHeight()
      .then(result => this.height = result)
  }

  ngOnInit() {
    let b=document.querySelector('button'); // que le premier
    b.disabled = true;
  }

  toggle(){
    this.isDisabled = !this.isDisabled;
  }

}
