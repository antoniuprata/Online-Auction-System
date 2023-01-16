import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuctionItem } from '../model';

@Component({
  selector: 'auction-items',
  templateUrl: './auction-items.component.html',
  styleUrls: ['./auction-items.component.scss']
})
export class AuctionItemsComponent implements OnInit {
  auctionItems: AuctionItem[];

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.getAuctionItems();
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
