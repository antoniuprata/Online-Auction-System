import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { AuctionItem } from '../model';

@Component({
  selector: 'app-won-auctions',
  templateUrl: './won-auctions.component.html',
  styleUrls: ['./won-auctions.component.scss']
})
export class WonAuctionsComponent implements OnInit {
  auctionItems: any;
  currentUser: any;

  constructor(private httpClient: HttpClient, private router: Router, private authentificationService: AuthenticationService) { }

  ngOnInit(): void {
    this.currentUser = this.authentificationService.currentUserValue;
    this.getWonItems();
    console.log(this.auctionItems);
  }

  getWonItems() {
    return this.httpClient.post<any>('http://localhost:8080/product/wonProducts', {emailUser: this.currentUser.email}, { responseType: 'json'})
      .subscribe((data) => {
        console.log(data);
        this.auctionItems = data;
        console.log('AUCTION ITEMSs', this.auctionItems);
      });
  }

  viewListing(id: number) {
    this.router.navigate(['/view-listing', id]);
  }
}
