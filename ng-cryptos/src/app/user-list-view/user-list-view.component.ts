import {Component, OnInit} from '@angular/core';
import {DataService} from "../data-service.service";
import {User} from "../demos/model/user";
import {Wallet} from "../demos/model/wallet";

@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  users: User[];
  selectedUser: User;
  selectedWallet : Wallet;
  createdWallet: Wallet = new Wallet();
  deletedWallet: Wallet = new Wallet();
  createdUser: User = new User();

  constructor(public dataService: DataService) {

    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users: ', users));
  }


  ngOnInit() {

  }

  delete(){
    this.selectedUser.wallets.splice(1,1);
  }

  details(user: User) {
    this.selectedUser = user;
    this.createdWallet.user = user;
    this.createdWallet.name = user.name + " 's wallet"
    this.deletedWallet.name = user.name + " 's wallet"

    console.log('You selected ', user);

    this.dataService.fetchUserWithWallet(user)
      .then(fullUser => this.selectedUser = fullUser)
      .then(console.log);
  }

  /*details(wallet: Wallet) {
    this.selectedWallet= wallet;
  }*/



  createWallet() {
    this.dataService.createWallet(this.createdWallet)
      .then(() => this.selectedUser.wallets.push(
        Object.assign({}, this.createdWallet))
      )
      .catch(e => alert(e.message))

  }

  createUser() {
    this.dataService.createUser(this.createdUser)
      .then(() => this.users.push(
        Object.assign({}, this.createdUser)))
      .catch(e => alert(e.message))

  }



  deleteWallet() {
   this.dataService.deleteWallet(this.deletedWallet)
   //.then(() => this.selectedWallet.splice(1,this.selectedWallet.find(line => line.id)))
   .catch(e => alert(e.message))


  }
/*
  renameWallet() {
    this.dataService.
      .then(() => this.selectedUser.wallets.splice(1,1))
      .catch(e => alert(e.message))

  }*/
}
