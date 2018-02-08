import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  formModel = {
    name : '',
    limit : 1000,
    level: 'Noob'
  };

  //levels = ['Noob', 'Expert', 'Master'];

  levels = [
    {text: 'Noob', id : 1},
    {text: 'Expert', id : 2},
    {text: 'Master', id : 4},
    ]

  constructor() {
  }

  ngOnInit() {
  }

  createNewWallet() {
    console.log(this.formModel);

  }


  isDisabled(){
    return this.formModel.name==='';
  }

  isLimit(){
    return this.formModel.limit-this.formModel.name.length;
  }
}
