import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Logindetails } from '../logindetails';

@Injectable({
  providedIn: 'root'
})
export class DataserviceService {

  product_id : any
  defaultproduct : any
  username=''
  password=''
  credentials = {username: this.username, password: this.password};

  private messagesource = new BehaviorSubject<any>(this.credentials)
 
  currentMessage = this.messagesource.asObservable();

  private messagesourceproductid = new BehaviorSubject<any>(this.product_id)
 
  currentProductid = this.messagesourceproductid.asObservable();
  
  constructor() { }

  changeMessage(message: any)
  {
    this.messagesource.next(message)
    console.log( this.messagesource.value)
  }

  changeProductid(message: any)
  {
    this.messagesourceproductid.next(message)
    console.log( "id of the product: "+this.messagesourceproductid.value)
  }
  
}
