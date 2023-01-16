import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { AuctionItem } from '../model';

@Component({
  selector: 'app-my-auctions',
  templateUrl: './my-auctions.component.html',
  styleUrls: ['./my-auctions.component.scss']
})
export class MyAuctionsComponent implements OnInit {
  auctionItems: AuctionItem[];
  currentUser: any;

  constructor(private httpClient: HttpClient, private router: Router, private authentificationService: AuthenticationService) { }

  ngOnInit(): void {
    this.getAuctionItems();
    this.currentUser = this.authentificationService.currentUserValue;
  }

  getAuctionItems() {
    this.httpClient.get<AuctionItem[]>('http://localhost:8080/product')
      .subscribe((data) => {
        this.auctionItems = data;
      });
  }

  viewListing(id: number) {
    this.router.navigate(['/view-listing', id]);
  }

}
